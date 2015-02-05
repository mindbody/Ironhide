package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.UiController;
import android.view.MotionEvent;

/**
 * Interface to implement different zoom types.
 */
public interface Zoomer {

    /**
     * The result of the zoom.
     */
    public enum Status {
        /**
         * The zoom action completed successfully.
         */
        SUCCESS,
        /**
         * Injecting the event was a complete failure.
         */
        FAILURE
    }

    /**
     * Zooms from {@code startCoordinates} to {@code endCoordinates} using the given
     * {@code uiController} to send {@link MotionEvent}s.
     *
     * @param uiController a UiController to use to send MotionEvents to the screen.
     * @param startCoordinates a float[][] with x and y co-ordinates of the two starts of the zoom.
     * @param endCoordinates a float[][] with x and y co-ordinates of the two ends of the zoom.
     * @param precision a float[] with x and y values of precision of the tap.
     * @return The status of the zoom.
     */
    public Status sendZoom(UiController uiController, float[][] startCoordinates,
                            float[][] endCoordinates, float[] precision);
}
