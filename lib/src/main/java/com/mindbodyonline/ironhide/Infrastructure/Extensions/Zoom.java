package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import static com.android.support.test.deps.guava.base.Preconditions.checkNotNull;
import static com.android.support.test.deps.guava.base.Preconditions.checkElementIndex;

import android.annotation.TargetApi;
import android.support.test.espresso.InjectEventSecurityException;
import android.support.test.espresso.UiController;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

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

    private static MotionEvent.PointerProperties[] properties;

    static {
        properties = new MotionEvent.PointerProperties[] { new MotionEvent.PointerProperties(), new MotionEvent.PointerProperties() };

        properties[0].toolType = properties[1].toolType = MotionEvent.TOOL_TYPE_FINGER;
        properties[0].id = 0;
        properties[1].id = 1;
    }

    private static final String TAG = Zoom.class.getSimpleName();

    /** The number of motion events to send for each zoom. */
    private static final int ZOOM_EVENT_COUNT = 10;

    /** Length of time a "fast" zoom should last for, in milliseconds. */
    private static final int ZOOM_FAST_DURATION_MS = 100;

    /** Length of time a "slow" zoom should last for, in milliseconds. */
    private static final int ZOOM_SLOW_DURATION_MS = 1500;

    private static float linearInterpolation(float alpha, float a, float b) {
        return alpha * a + (1 - alpha) * b;
    }

    private static Zoomer.Status sendLinearZoom(UiController uiController, float[][] startCoordinates,
                                                 float[][] endCoordinates, float[] precision, int duration) {
        checkNotNull(uiController);
        checkNotNull(startCoordinates);
        checkNotNull(endCoordinates);
        checkNotNull(precision);

        MotionEvent.PointerCoords[] coordinates = new MotionEvent.PointerCoords[] {new MotionEvent.PointerCoords(), new MotionEvent.PointerCoords()};
        coordinates[0].pressure = coordinates[0].size = coordinates[1].pressure = coordinates[1].size = 1;

        coordinates[0].x = startCoordinates[0][0];
        coordinates[0].y = startCoordinates[0][1];
        coordinates[1].x = startCoordinates[1][0];
        coordinates[1].y = startCoordinates[1][1];

        long startTime = SystemClock.uptimeMillis();
        MotionEvent touch1 = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, startCoordinates[0][0], startCoordinates[0][1], 1, 1, 0, precision[0], precision[1], 0, 0);
        //MotionEvent touch2 = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_POINTER_DOWN | 1 << MotionEvent.ACTION_POINTER_INDEX_SHIFT, startCoordinates[1][0], startCoordinates[1][1], 1);
        MotionEvent touch2 = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_POINTER_2_DOWN, 2, properties, coordinates, 0, 0, precision[0], precision[1], 0, 0, 0, 0);

        try {
            boolean downInject1 = uiController.injectMotionEvent(touch1),
                    downInject2 = uiController.injectMotionEvent(touch2), // this one is failing
                    moveInject = true;
            Log.d("Zoom", String.format("Clicked on location primary (%f, %f), secondary (%f, %f)", touch1.getX(), touch1.getY(), touch2.getX(), touch2.getY()));

            for (int i = 0; i < ZOOM_EVENT_COUNT; i++) {
                float alpha = (float)i / 10;


                // TODO: use linearInterpolation(alpha, a, b)
                coordinates[0].x = (1 - alpha) * startCoordinates[0][0] + alpha * endCoordinates[0][0];
                coordinates[0].y = (1 - alpha) * startCoordinates[0][1] + alpha * endCoordinates[0][1];
                coordinates[1].x = (1 - alpha) * startCoordinates[1][0] + alpha * endCoordinates[1][0];
                coordinates[1].y = (1 - alpha) * startCoordinates[1][1] + alpha * endCoordinates[1][1];

                MotionEvent move = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, 2, properties, coordinates, 0, 0, precision[0], precision[1], 0, 0, 0, 0);
                moveInject = moveInject && uiController.injectMotionEvent(move);

                Log.d("Zoom", String.format("Moved primary to (%f, %f) and secondary (%f, %f)", move.getX(), move.getY(), move.getX(1), move.getY(1)));

                // TODO wait for the next move event so that the zoom lasts as long as duration
                //uiController.loopMainThreadForAtLeast(startTime + (long)(alpha * duration) - SystemClock.uptimeMillis());
            }


            coordinates[0].x = endCoordinates[0][0];
            coordinates[0].y = endCoordinates[0][1];
            coordinates[1].x = endCoordinates[1][0];
            coordinates[1].y = endCoordinates[1][1];

            MotionEvent untouch2 = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_POINTER_2_UP, 2, properties, coordinates, 0, 0, precision[0], precision[1], 0, 0, 0, 0);
//            MotionEvent untouch2 = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_POINTER_UP | 1 << MotionEvent.ACTION_POINTER_INDEX_SHIFT, endCoordinates[1][0], endCoordinates[1][0], 1, 1, 0, precision[0], precision[1], 0, 0);
            MotionEvent untouch1 = MotionEvent.obtain(startTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, endCoordinates[0][0], endCoordinates[0][1], 1, 1, 0, precision[0], precision[1], 0, 0);

            boolean upInject2 = uiController.injectMotionEvent(untouch2),
                    upInject1 = uiController.injectMotionEvent(untouch1);

            Log.d("Zoom", "Down: (" + downInject1 + ", " + downInject2 + "), Move: " + moveInject + ", Up: (" + upInject1 + ", " + upInject2 + ")");

            if (downInject1 && downInject2 && moveInject && upInject1 && upInject2)
                return Status.SUCCESS;
        } catch (InjectEventSecurityException e) {
            Log.e("Zoom", Log.getStackTraceString(e));
        }
        return Status.FAILURE;
    }

}
