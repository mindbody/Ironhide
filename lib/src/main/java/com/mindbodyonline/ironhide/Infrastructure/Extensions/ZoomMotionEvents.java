package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import static android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT;
import static com.android.support.test.deps.guava.base.Preconditions.checkNotNull;

import android.annotation.TargetApi;
import android.os.SystemClock;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.InjectEventSecurityException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.internal.runner.tracker.UsageTrackerRegistry;
import android.util.Log;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;
import android.view.ViewConfiguration;

import com.android.support.test.deps.guava.annotations.VisibleForTesting;

import org.hamcrest.Matcher;

/**
 * Facilitates sending of motion events to a {@link UiController}.
 */
@TargetApi(14)
final class ZoomMotionEvents {

    private static final String TAG = ZoomMotionEvents.class.getSimpleName();

    private static PointerProperties[] defaultProperties;

    static {
        defaultProperties = new PointerProperties[]{ new PointerProperties(), new PointerProperties() };
        defaultProperties[0].id = 0;
        defaultProperties[1].id = 1;
        defaultProperties[0].toolType = defaultProperties[1].toolType = MotionEvent.TOOL_TYPE_FINGER;
    }

    static PointerCoords[] getCoords(float[][] coordinates) {
        PointerCoords[] pointerCoords = new PointerCoords[]{ new PointerCoords(), new PointerCoords() };
        pointerCoords[0].pressure = pointerCoords[1].pressure = 1;
        pointerCoords[0].size = pointerCoords[1].size = 1;

        pointerCoords[0].x = coordinates[0][0];
        pointerCoords[0].y = coordinates[0][1];
        pointerCoords[1].x = coordinates[1][0];
        pointerCoords[1].y = coordinates[1][1];

        return pointerCoords;
    }

    @VisibleForTesting
    static final int MAX_CLICK_ATTEMPTS = 3;

    private ZoomMotionEvents() {
        // Shouldn't be instantiated
    }

    static DownResultHolder sendDownPair(UiController uiController, float[][] coordinates, float[] precision) {
        checkNotNull(uiController);
        checkNotNull(coordinates);
        checkNotNull(precision);

        for (int retry = 0; retry < MAX_CLICK_ATTEMPTS; retry++) {
            MotionEvent motionEvents[] = null;
            try {
                long downTime = SystemClock.uptimeMillis();
                motionEvents = new MotionEvent[] {
                        MotionEvent.obtain(downTime,
                                SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_DOWN,
                                1,
                                defaultProperties,
                                getCoords(coordinates),
                                0, 0,
                                precision[0], precision[1],
                                0, 0, 0, 0),
                        MotionEvent.obtain(downTime, SystemClock.uptimeMillis(),
                                MotionEvent.ACTION_POINTER_DOWN + defaultProperties[1].id << ACTION_POINTER_INDEX_SHIFT,
                                2,
                                defaultProperties,
                                getCoords(coordinates),
                                0, 0,
                                precision[0], precision[1],
                                0, 0, 0, 0)
                };

                long isTapAt = downTime + (ViewConfiguration.getTapTimeout() / 2);

                boolean injectEventSucceeded = uiController.injectMotionEvent(motionEvents[0])
                                            && uiController.injectMotionEvent(motionEvents[1]);

                while (true) {
                    long delayToBeTap = isTapAt - SystemClock.uptimeMillis();
                    if (delayToBeTap <= 10) {
                        break;
                    }
                    // Sleep only a fraction of the time, since there may be other events in the UI queue
                    // that could cause us to start sleeping late, and then oversleep.
                    uiController.loopMainThreadForAtLeast(delayToBeTap / 4);
                }

                boolean longPress = false;
                if (SystemClock.uptimeMillis() > (downTime + ViewConfiguration.getLongPressTimeout())) {
                    longPress = true;
                    Log.e(TAG, "Overslept and turned a tap into a long press");
                    UsageTrackerRegistry.getInstance().trackUsage("Espresso.Tap.Error.tapToLongPress");
                }

                if (!injectEventSucceeded) {
                    motionEvents[0].recycle();
                    motionEvents[1].recycle();
                    motionEvents[0] = null;
                    motionEvents[1] = null;
                    continue;
                }

                return new DownResultHolder(motionEvents, longPress);
            } catch (InjectEventSecurityException e) {
                /*new FailureHandler() {
                    @Override
                    public void handle(Throwable throwable, Matcher<View> viewMatcher) {
                        // do something?
                    }
                }.handle(e, null);*/
                throw new PerformException.Builder()
                        .withActionDescription("Send down motion event")
                        .withViewDescription("unknown") // likely to be replaced by FailureHandler
                        .withCause(e)
                        .build();
            }
        }
        throw new PerformException.Builder()
                .withActionDescription(String.format("click (after %s attempts)", MAX_CLICK_ATTEMPTS))
                .withViewDescription("unknown") // likely to be replaced by FailureHandler
                .build();
    }

    static boolean sendMovementPair(UiController uiController, MotionEvent[] downEvent,
                                    float[][] coordinates, float[] precision) {
        checkNotNull(uiController);
        checkNotNull(downEvent);
        checkNotNull(coordinates);

        MotionEvent motionEvent = null;
        try {
            motionEvent = MotionEvent.obtain(downEvent[0].getDownTime(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_MOVE,
                    2, defaultProperties,
                    getCoords(coordinates),
                    0, 0,
                    precision[0],
                    precision[1],
                    0, 0, 0, 0);
            boolean injectEventSucceeded = uiController.injectMotionEvent(motionEvent);

            if (!injectEventSucceeded) {
                Log.e(TAG, String.format(
                        "Injection of motion event failed (corresponding down events: %s and %s)",
                        downEvent[0].toString(), downEvent[1].toString()));
                return false;
            }
        } catch (InjectEventSecurityException e) {
            throw new PerformException.Builder()
                    .withActionDescription(String.format(
                            "inject motion event (corresponding down events: %s and %s)", downEvent[0].toString(), downEvent[1].toString()))
                    .withViewDescription("unknown") // likely to be replaced by FailureHandler
                    .withCause(e)
                    .build();
        } finally {
            if (null != motionEvent) {
                motionEvent.recycle();
                motionEvent = null;
            }
        }

        return true;
    }

    static void sendCancelPair(UiController uiController, MotionEvent[] downEvents, float[] precision) {
        sendCancelPair(uiController, new float[][] {
                new float[] {downEvents[0].getX(), downEvents[0].getY()},
                new float[] {downEvents[1].getX(), downEvents[1].getY()}
        }, downEvents, precision);
    }

    static void sendCancelPair(UiController uiController, float[][] coordinates, MotionEvent[] downEvents, float[] precision) {
        checkNotNull(uiController);
        checkNotNull(coordinates);
        checkNotNull(downEvents);

        MotionEvent motionEvent = null;
        try {
            // Up press.
            motionEvent = MotionEvent.obtain(downEvents[0].getDownTime(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_MOVE,
                    2, defaultProperties,
                    getCoords(coordinates),
                    0, 0,
                    precision[0],
                    precision[1],
                    0, 0, 0, 0);
            boolean injectEventSucceeded = uiController.injectMotionEvent(motionEvent);

            if (!injectEventSucceeded) {
                throw new PerformException.Builder()
                        .withActionDescription(String.format(
                                "inject cancel event (corresponding down events: %s and %s)", downEvents[0].toString(), downEvents[1].toString()))
                        .withViewDescription("unknown") // likely to be replaced by FailureHandler
                        .build();
            }
        } catch (InjectEventSecurityException e) {
            throw new PerformException.Builder()
                    .withActionDescription(String.format(
                            "inject cancel event (corresponding down event: %s and %s)", downEvents[0].toString(), downEvents[1].toString()))
                    .withViewDescription("unknown") // likely to be replaced by FailureHandler
                    .withCause(e)
                    .build();
        } finally {
            if (null != motionEvent) {
                motionEvent.recycle();
                motionEvent = null;
            }
        }
    }

    static boolean sendUpPair(UiController uiController, MotionEvent[] downEvents, float[] precision) {
        return sendUpPair(uiController, new float[][] {
                new float[] { downEvents[0].getX(), downEvents[0].getY() },
                new float[] { downEvents[1].getX(), downEvents[1].getY() }
        }, downEvents, precision);
    }

    static boolean sendUpPair(UiController uiController, float[][] coordinates, MotionEvent[] downEvents, float[] precision) {
        checkNotNull(uiController);
        checkNotNull(downEvents);
        checkNotNull(coordinates);
        checkNotNull(precision);

        MotionEvent[] motionEvents = null;
        try {
            // Up press.
            motionEvents = new MotionEvent[] {
                    MotionEvent.obtain(downEvents[0].getDownTime(),
                            SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_POINTER_UP,
                            2,
                            defaultProperties,
                            getCoords(coordinates),
                            0, 0,
                            precision[0],
                            precision[1],
                            0, 0, 0, 0),
                    MotionEvent.obtain(downEvents[1].getDownTime(),
                            SystemClock.uptimeMillis(),
                            MotionEvent.ACTION_UP,
                            1,
                            defaultProperties,
                            getCoords(coordinates),
                            0, 0,
                            precision[0],
                            precision[1],
                            0, 0, 0, 0)
            };

            boolean injectEventSucceeded = uiController.injectMotionEvent(motionEvents[0])
                                        && uiController.injectMotionEvent(motionEvents[1]);

            if (!injectEventSucceeded) {
                Log.e(TAG, String.format(
                        "Injection of up event failed (corresponding down events: %s and %s)", downEvents[0].toString(), downEvents[1].toString()));
                return false;
            }
        } catch (InjectEventSecurityException e) {
            throw new PerformException.Builder()
                    .withActionDescription(
                            String.format("inject up event (corresponding down events: %s and %s)", downEvents[0].toString(), downEvents[1].toString()))
                    .withViewDescription("unknown") // likely to be replaced by FailureHandler
                    .withCause(e)
                    .build();
        } finally {
            if (null != motionEvents) {
                motionEvents[0].recycle();
                motionEvents[1].recycle();
                motionEvents[0] = null;
                motionEvents[1] = null;
            }
        }
        return true;
    }

    /**
     * Holds the result of a down motion.
     */
    static class DownResultHolder {
        public final MotionEvent[] down;
        public final boolean longPress;

        DownResultHolder(MotionEvent[] down, boolean longPress) {
            this.down = down;
            this.longPress = longPress;
        }
    }

}