package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.annotation.TargetApi;
import android.os.SystemClock;
import android.support.test.espresso.InjectEventSecurityException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.util.Log;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.ViewConfiguration;

import com.android.support.test.deps.guava.annotations.VisibleForTesting;

import java.util.Arrays;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_2_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_2_UP;
import static android.view.MotionEvent.ACTION_UP;
import static android.view.MotionEvent.TOOL_TYPE_FINGER;
import static com.android.support.test.deps.guava.base.Preconditions.checkNotNull;

/**
 * Facilitates sending of motion events to a {@link UiController}.
 */
@TargetApi(14)
final class ZoomMotionEvents {

    @VisibleForTesting
    static final int MAX_CLICK_ATTEMPTS = 3;

    public static final int MIN_LOOP_TIME = 10; /* milliseconds */

    // default values for MotionEvent.obtain
    private static final int DEFAULT_BUTTON_STATE = 0;
    private static final int DEFAULT_DEVICE_ID = 0;
    private static final int DEFAULT_EDGE_FLAGS = 0;
    private static final int DEFAULT_FLAGS = 0;
    private static final int DEFAULT_META_STATE = 0;
    private static final int DEFAULT_PRESSURE = 1;
    private static final int DEFAULT_SIZE = 1;
    private static final int DEFAULT_SOURCE = 0;

    private static final String TAG = ZoomMotionEvents.class.getSimpleName();

    private static PointerProperties[] defaultProperties;

    static {
        defaultProperties = new PointerProperties[]{ new PointerProperties(), new PointerProperties() };
        defaultProperties[0].id = 0;
        defaultProperties[1].id = 1;
        defaultProperties[0].toolType = defaultProperties[1].toolType = TOOL_TYPE_FINGER;
    }


    private UiController uiController;
    private float[] precision;
    public long downTime;

    private MotionEvent[] downEvents;

    public ZoomMotionEvents(UiController uiController, float[] precision) {
        checkNotNull(uiController);
        checkNotNull(precision);

        this.uiController = uiController;
        this.precision = precision;
    }

    public boolean sendDownPair(float[][] coordinates) {
        checkNotNull(coordinates);

        for (int retry = 0; retry < MAX_CLICK_ATTEMPTS; retry++) {
            try {
                downTime = SystemClock.uptimeMillis();
                downEvents = new MotionEvent[] {
                        obtainWrapper(ACTION_DOWN, coordinates[0][0], coordinates[0][1], precision),
                        obtainWrapper(ACTION_POINTER_2_DOWN, coordinates, precision)
                };

                long isTapAt = downTime + (ViewConfiguration.getTapTimeout() / 2);

                boolean injectEventsSucceeded = uiController.injectMotionEvent(downEvents[0])
                                             && uiController.injectMotionEvent(downEvents[1]);

                while (isTapAt - SystemClock.uptimeMillis() > MIN_LOOP_TIME) {
                    // Sleep only a fraction of the time, since there may be other events in the UI queue
                    // that could cause us to start sleeping late, and then oversleep.
                    uiController.loopMainThreadForAtLeast( (isTapAt - SystemClock.uptimeMillis()) / 4);
                }

                if (injectEventsSucceeded)
                    return true;

                downEvents[0].recycle();
                downEvents[1].recycle();
                downEvents[0] = null;
                downEvents[1] = null;

            } catch (InjectEventSecurityException e) {
                Log.e(TAG, Log.getStackTraceString(getPerformException("Send down motion events", e)));
            }
        }
        Log.e(TAG, Log.getStackTraceString(getPerformException(String.format("click (after %d attempts)", MAX_CLICK_ATTEMPTS))));
        return false;
    }

    public boolean sendMovementPair(float[][] coordinates) {
        checkNotNull(coordinates);

        MotionEvent motionEvent = null;
        try {
            motionEvent = obtainWrapper(ACTION_MOVE, coordinates, precision);
            boolean injectEventSucceeded = uiController.injectMotionEvent(motionEvent);

            if (!injectEventSucceeded) {
                Log.e(TAG, String.format("Injection of motion event failed (corresponding down events: %s and %s)",
                        downEvents[0].toString(), downEvents[1].toString()));
                return false;
            }
        } catch (InjectEventSecurityException e) {
            Log.e(TAG, String.format("Injection of motion event failed (corresponding down events: %s and %s)",
                    downEvents[0].toString(), downEvents[1].toString()));
            return false;
        } finally {
            if (motionEvent != null)
                motionEvent.recycle();
        }

        return true;
    }

    public void sendCancelPair(float[][] coordinates) {
        checkNotNull(coordinates);

        MotionEvent motionEvent = null;
        try {
            // Up press.
            motionEvent = obtainWrapper(ACTION_MOVE, coordinates, precision);
            boolean injectEventSucceeded = uiController.injectMotionEvent(motionEvent);

            if (!injectEventSucceeded) {
                String description = String.format("inject cancel event (corresponding down event: %s and %s)", downEvents[0].toString(), downEvents[1].toString());
                throw getPerformException(description);
            }
        } catch (InjectEventSecurityException e) {
            String description = String.format("inject cancel event (corresponding down event: %s and %s)", downEvents[0].toString(), downEvents[1].toString());

            throw getPerformException(description, e);
        } finally {
            if (motionEvent != null)
                motionEvent.recycle();
        }
    }

    public boolean sendUpPair(float[][] coordinates) {
        checkNotNull(coordinates);

        MotionEvent[] motionEvents = null;
        try {
            // Up press.
            motionEvents = new MotionEvent[] {
                    obtainWrapper(ACTION_POINTER_2_UP, coordinates, precision),
                    obtainWrapper(ACTION_UP, coordinates[0][0], coordinates[0][1], precision)
            };

            if ( !(uiController.injectMotionEvent(motionEvents[0]) && uiController.injectMotionEvent(motionEvents[1])) ) {
                Log.e(TAG, String.format("Injection of up event failed (corresponding down events: %s)", Arrays.deepToString(downEvents)));
                return false;
            }
        } catch (InjectEventSecurityException e) {
            String description = String.format("inject up event (corresponding down events: %s and %s)", downEvents[0].toString(), downEvents[1].toString());

            throw getPerformException(description, e);
        } finally {
            if (motionEvents != null) {
                motionEvents[0].recycle();
                motionEvents[1].recycle();
                motionEvents[0] = null;
                motionEvents[1] = null;
            }
        }

        return true;
    }

    /**
     * Helper functions
     */

    private static PointerCoords[] getCoords(float[][] coordinates) {
        PointerCoords[] pointerCoords = new PointerCoords[]{ new PointerCoords(), new PointerCoords() };
        pointerCoords[0].pressure = pointerCoords[1].pressure = 1;
        pointerCoords[0].size = pointerCoords[1].size = 1;

        pointerCoords[0].x = coordinates[0][0];
        pointerCoords[0].y = coordinates[0][1];
        pointerCoords[1].x = coordinates[1][0];
        pointerCoords[1].y = coordinates[1][1];

        return pointerCoords;
    }

    private static PerformException getPerformException(String description, Throwable cause) {
        return new PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription("unknown") // likely to be replaced by FailureHandler
                .withCause(cause)
                .build();
    }

    private static PerformException getPerformException(String description) {
        return new PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription("unknown") // likely to be replaced by FailureHandler
                .build();
    }

    private MotionEvent obtainWrapper(int action, float x, float y, float[] precision) {
        return MotionEvent.obtain(
                downTime, SystemClock.uptimeMillis(),
                action,
                x, y, DEFAULT_PRESSURE, DEFAULT_SIZE,
                DEFAULT_META_STATE,
                precision[0], precision[1],
                DEFAULT_DEVICE_ID, DEFAULT_EDGE_FLAGS);
    }

    // 2D array implies user wishes to pass 2 pointer values
    private MotionEvent obtainWrapper(int action, float[][] coordinates, float[] precision) {
        return MotionEvent.obtain(
                downTime, SystemClock.uptimeMillis(),
                action,
                2, defaultProperties, getCoords(coordinates),
                DEFAULT_META_STATE, DEFAULT_BUTTON_STATE,
                precision[0], precision[1],
                DEFAULT_DEVICE_ID, DEFAULT_EDGE_FLAGS, DEFAULT_SOURCE, DEFAULT_FLAGS);
    }
}