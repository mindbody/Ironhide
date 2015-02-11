package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.annotation.TargetApi;
import android.os.SystemClock;
import android.support.test.espresso.UiController;
import android.util.Log;

import static com.android.support.test.deps.guava.base.Preconditions.checkNotNull;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoomer.Status.FAILURE;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoomer.Status.SUCCESS;

/**
 * Executes different zoom types to given positions.
 */
@TargetApi(14)
public enum Zoom implements Zoomer {

    /** zooms quickly between the co-ordinates. */
    FAST {
        @Override
        public Status sendZoom(UiController uiController, float[][] startCoordinates, float[][] endCoordinates, float[] precision) {
            return sendLinearZoom(uiController, startCoordinates, endCoordinates, precision,
                    ZOOM_FAST_DURATION_MS);
        }
    },

    /** zooms deliberately slowly between the co-ordinates, to aid in visual debugging. */
    SLOW {
        @Override
        public Status sendZoom(UiController uiController, float[][] startCoordinates, float[][] endCoordinates, float[] precision) {
            return sendLinearZoom(uiController, startCoordinates, endCoordinates, precision,
                    ZOOM_SLOW_DURATION_MS);
        }
    };

    /** The number of motion events to send for each zoom. */
    private static final int ZOOM_EVENT_COUNT= 10;

    /** Length of time a "fast" zoom should last for, in milliseconds. */
    private static final int ZOOM_FAST_DURATION_MS = 100;

    /** Length of time a "slow" zoom should last for, in milliseconds. */
    private static final int ZOOM_SLOW_DURATION_MS = 1500;

    private static final String TAG = Zoom.class.getSimpleName();

    private static float linearInterpolation(float alpha, float a, float b) {
        return (1 - alpha) * a + alpha * b;
    }

    private static float[][] linearInterpolation(float alpha, float[][] a, float[][] b) {
        return new float[][]{
                new float[]{
                        linearInterpolation(alpha, a[0][0], b[0][0]),
                        linearInterpolation(alpha, a[0][1], b[0][1])
                },
                new float[]{
                        linearInterpolation(alpha, a[1][0], b[1][0]),
                        linearInterpolation(alpha, a[1][1], b[1][1])
                }
        };
    }

    private static Zoomer.Status sendLinearZoom(UiController uiController, float[][] startCoordinates, float[][] endCoordinates, float[] precision, int duration) {
        checkNotNull(uiController);
        checkNotNull(startCoordinates);
        checkNotNull(endCoordinates);
        checkNotNull(precision);

        ZoomMotionEvents manager = new ZoomMotionEvents(uiController, precision);
        boolean injectSuccess = manager.sendDownPair(startCoordinates);

        for (int i = 0; i < ZOOM_EVENT_COUNT; i++) {
            float alpha = (float)i / ZOOM_EVENT_COUNT;
            if (!manager.sendMovementPair(linearInterpolation(alpha, startCoordinates, endCoordinates))) {
                Log.e(TAG, "Injection of move event as part of the zoom failed. Sending cancel event.");
                manager.sendCancelPair(linearInterpolation(alpha, startCoordinates, endCoordinates));
                return FAILURE;
            }

            long loopDuration = manager.downTime + (long)(alpha * duration) - SystemClock.uptimeMillis();
            if (loopDuration > ZoomMotionEvents.MIN_LOOP_TIME)
                uiController.loopMainThreadForAtLeast(loopDuration);
        }

        injectSuccess = injectSuccess && manager.sendUpPair(endCoordinates);

        if (injectSuccess)
            return SUCCESS;
        else {
            Log.e(TAG, "Injection of up event as part of the zoom failed. Sending cancel event.");
            manager.sendCancelPair(endCoordinates);
            return FAILURE;
        }
    }

}
