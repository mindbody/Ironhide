package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.Press;
import android.support.v4.util.Pair;

import java.util.HashMap;

import static android.support.test.espresso.action.GeneralLocation.BOTTOM_LEFT;
import static android.support.test.espresso.action.GeneralLocation.CENTER;
import static android.support.test.espresso.action.GeneralLocation.TOP_RIGHT;

/**
 * An extension for {@link GeneralZoomAction}.
 * Uses directions rather than start and end locations for easier use and buffers the creation of
 *  ZoomActions so that they do not need to be created multiple times.
 */
public class ZoomAction {

    // the buffer for ZoomAction creation
    private static HashMap<Pair<Zoomer, ZoomDirection>, GeneralZoomAction> bufferedZoomActions;

    /**
     * Initial size is the number of combination of most used zoom speeds
     *  (from {@link android.support.test.espresso.action.Zoom}) and the number of
     *  {@link ZoomDirection}s
     */
    static {
        bufferedZoomActions = new HashMap<Pair<Zoomer, ZoomDirection>, GeneralZoomAction>(ZoomDirection.values().length * Zoom.values().length);
    }

    /**
     * Gets a zoom action.
     *
     * @param speed the speed of the zoom
     * @param direction the direction of the zoom
     * @return  the zoom action requested
     */
    public static GeneralZoomAction getZoom(Zoomer speed, ZoomDirection direction) {
        Pair<Zoomer, ZoomDirection> key = new Pair<Zoomer, ZoomDirection>(speed, direction);

        if (!bufferedZoomActions.containsKey(key))
            bufferedZoomActions.put(key, generateZoom(speed, direction));

        return bufferedZoomActions.get(key);
    }

    /**
     * Creates a new zoom action.
     *
     * @param speed the speed of the zoom
     * @param direction the direction of the zoom
     * @return  the zoom action requested
     */
    private static GeneralZoomAction generateZoom(Zoomer speed, ZoomDirection direction) {
        return new GeneralZoomAction(speed, direction.start, direction.end, Press.FINGER);
    }

    /**
     * An enum to associate directions with start and end locations on a screen.
     */
    public enum ZoomDirection {
        IN  (CENTER, CENTER, TOP_RIGHT, BOTTOM_LEFT),
        OUT (TOP_RIGHT, BOTTOM_LEFT, CENTER, CENTER);

        public CoordinatesProvider[] start, end;

        /**
         * Variadic parameters to simplify the look of this function.
         * First two assumed to be start locations,
         *  next two assumed to be end location,
         *  and the rest are ignored.
         */
        private ZoomDirection(CoordinatesProvider... providers) {
            this.start = new CoordinatesProvider[]{ providers[0], providers[1] };
            this.end = new CoordinatesProvider[]{ providers[2], providers[3] };
        }
    }
}
