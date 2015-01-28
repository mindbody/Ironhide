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
 * Created by Kyle.Lozier on 1/28/2015.
 */
public class SwipeAction {

    private static HashMap<Pair<Swiper, SwipeDirection>, GeneralSwipeAction> bufferedSwipeActions;

    static {
        bufferedSwipeActions = new HashMap<Pair<Swiper, SwipeDirection>, GeneralSwipeAction>(SwipeDirection.values().length * Swipe.values().length);
    }

    public static GeneralSwipeAction getSwipe(Swiper speed, SwipeDirection direction) {
        Pair<Swiper, SwipeDirection> key = new Pair<Swiper, SwipeDirection>(speed, direction);

        if (!bufferedSwipeActions.containsKey(key))
            bufferedSwipeActions.put(key, generateSwipe(speed, direction));

        return bufferedSwipeActions.get(key);
    }

    private static GeneralSwipeAction generateSwipe(Swiper speed, SwipeDirection direction) {
        return new GeneralSwipeAction(speed, direction.start, direction.end, Press.FINGER);
    }

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
