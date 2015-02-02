package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import static com.android.support.test.deps.guava.base.Preconditions.checkNotNull;
import static com.android.support.test.deps.guava.base.Preconditions.checkElementIndex;

import android.support.test.espresso.UiController;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Executes different zoom types to given positions.
 */
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

    private static final String TAG = Zoom.class.getSimpleName();

    /** The number of motion events to send for each zoom. */
    private static final int ZOOM_EVENT_COUNT = 10;

    /** Length of time a "fast" zoom should last for, in milliseconds. */
    private static final int ZOOM_FAST_DURATION_MS = 100;

    /** Length of time a "slow" zoom should last for, in milliseconds. */
    private static final int ZOOM_SLOW_DURATION_MS = 1500;

    private static float[][] interpolate(float[] start, float[] end, int steps) {
        checkElementIndex(1, start.length);
        checkElementIndex(1, end.length);

        float[][] res = new float[steps][2];

        for (int i = 1; i < steps + 1; i++) {
            res[i - 1][0] = start[0] + (end[0] - start[0]) * i / (steps + 2f);
            res[i - 1][1] = start[1] + (end[1] - start[1]) * i / (steps + 2f);
        }

        return res;
    }

    private static Zoomer.Status sendLinearZoom(UiController uiController, float[][] startCoordinates,
                                                 float[][] endCoordinates, float[] precision, int duration) {
            checkNotNull(uiController);
            checkNotNull(startCoordinates);
            checkNotNull(endCoordinates);
            checkNotNull(precision);

            float[][][] steps = new float[][][]{
                    interpolate(startCoordinates[0], endCoordinates[0], ZOOM_EVENT_COUNT),
                    interpolate(startCoordinates[1], endCoordinates[1], ZOOM_EVENT_COUNT)
            };
            final int delayBetweenMovements = duration / steps.length;

            MotionEvent downEvents[] = ZoomMotionEvents.sendDownPair(uiController, steps[0], precision).down;
            try {
                for (int i = 1; i < steps.length; i++) {
                    if (!ZoomMotionEvents.sendMovementPair(uiController, downEvents, steps[i], precision)) {
                        Log.e(TAG, "Injection of move event as part of the zoom failed. Sending cancel event.");
                        ZoomMotionEvents.sendCancelPair(uiController, downEvents, precision);
                        return Zoomer.Status.FAILURE;
                    }

                    long desiredTime = downEvents[0].getDownTime() + delayBetweenMovements * i;
                    long timeUntilDesired = desiredTime - SystemClock.uptimeMillis();
                    if (timeUntilDesired > 10) {
                        uiController.loopMainThreadForAtLeast(timeUntilDesired);
                    }
                }

                if (!ZoomMotionEvents.sendUpPair(uiController, downEvents, endCoordinates[0])) {
                    Log.e(TAG, "Injection of up event as part of the zoom failed. Sending cancel event.");
                    ZoomMotionEvents.sendCancelPair(uiController, downEvents, precision);
                    return Status.FAILURE;
                }
            } finally {
                downEvents[0].recycle();
                downEvents[1].recycle();
            }
            return Status.SUCCESS;
    }

}
