package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.Swiper;
import android.support.v4.util.Pair;

import java.util.HashMap;

import static android.support.test.espresso.action.GeneralLocation.BOTTOM_CENTER;
import static android.support.test.espresso.action.GeneralLocation.CENTER_LEFT;
import static android.support.test.espresso.action.GeneralLocation.CENTER_RIGHT;
import static android.support.test.espresso.action.GeneralLocation.TOP_CENTER;

/**
 * An extension for {@link android.support.test.espresso.action.GeneralSwipeAction}.
 * Uses directions rather than start and end locations for easier use and buffers the creation of
 *  SwipeActions so that they do not need to be created multiple times.
 */
public class SwipeAction {

    // the buffer for SwipeAction creation
    private static HashMap<Pair<Swiper, SwipeDirection>, GeneralSwipeAction> bufferedSwipeActions;

    /**
     * Initial size is the number of combination of most used swipe speeds
     *  (from {@link android.support.test.espresso.action.Swipe}) and the number of
     *  {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection}s
     */
    static {
        bufferedSwipeActions = new HashMap<Pair<Swiper, SwipeDirection>, GeneralSwipeAction>(SwipeDirection.values().length * Swipe.values().length);
    }

    /**
     * Gets a swipe action.
     *
     * @param speed the speed of the swipe
     * @param direction the direction of the swipe
     * @return  the swipe action requested
     */
    public static GeneralSwipeAction getSwipe(Swiper speed, SwipeDirection direction) {
        Pair<Swiper, SwipeDirection> key = new Pair<Swiper, SwipeDirection>(speed, direction);

        if (!bufferedSwipeActions.containsKey(key))
            bufferedSwipeActions.put(key, generateSwipe(speed, direction));

        return bufferedSwipeActions.get(key);
    }

    /**
     * Creates a new swipe action.
     *
     * @param speed the speed of the swipe
     * @param direction the direction of the swipe
     * @return  the swipe action requested
     */
    private static GeneralSwipeAction generateSwipe(Swiper speed, SwipeDirection direction) {
        return new GeneralSwipeAction(speed, direction.start, direction.end, Press.FINGER);
    }

    /**
     * An enum to associate directions with start and end locations on a screen.
     */
    public enum SwipeDirection {
        UP      (BOTTOM_CENTER, TOP_CENTER),
        DOWN    (TOP_CENTER, BOTTOM_CENTER),
        LEFT    (CENTER_RIGHT, CENTER_LEFT),
        RIGHT   (CENTER_LEFT, CENTER_RIGHT);

        public CoordinatesProvider start, end;

        private SwipeDirection(CoordinatesProvider start, CoordinatesProvider end) {
            this.start = start;
            this.end = end;
        }
    }
}
