package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;

/**
 * Created by Barbara.Wong on 7/21/2014.
 */
public class BaseViewActions {

    public BaseViewActions() {

    }

    public static ViewAction swipeDownFast() {
        return new GeneralSwipeAction(Swipe.FAST, GeneralLocation.TOP_CENTER, GeneralLocation.BOTTOM_CENTER, Press.FINGER);
    }

    public static ViewAction swipeDownSlow() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.TOP_CENTER, GeneralLocation.BOTTOM_CENTER, Press.FINGER);
    }

    public static ViewAction swipeUpFast() {
        return new GeneralSwipeAction(Swipe.FAST, GeneralLocation.BOTTOM_CENTER, GeneralLocation.TOP_CENTER, Press.FINGER);
    }

    public static ViewAction swipeUpSlow() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER, GeneralLocation.TOP_CENTER, Press.FINGER);
    }

    public static ViewAction swipeFullRight() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.CENTER_LEFT, GeneralLocation.CENTER_RIGHT, Press.FINGER);
    }

    public static ViewAction swipeFullLeft() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.CENTER_RIGHT, GeneralLocation.CENTER_LEFT, Press.FINGER);
    }

    public static ViewAction swipeUpSlowHalf(){
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER, GeneralLocation.CENTER, Press.FINGER);
    }
}