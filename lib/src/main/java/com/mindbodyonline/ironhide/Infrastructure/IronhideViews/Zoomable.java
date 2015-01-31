package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.graphics.Point;
import android.os.SystemClock;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.ViewActions;
import android.support.v4.util.Pair;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.ZoomAction;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.getSwipe;
import static android.support.test.espresso.action.Swipe.FAST;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection.DOWN;
import static android.support.test.espresso.action.GeneralLocation.*;

/**
 * Simple element that allows to perform a zoom on the screen.
 * Provides zoomAllOut, zoomAllIn, and a generic zoom method.
 * Use this when the main purpose of the element will be to zoom in/out
 *
 * @param <T> The model the current element will return when interacted with
 */
@TargetApi(14)
public class Zoomable<T extends PageObject> extends BaseView<T> {

    private static final int ZOOMS_IN_PER_ALL_ZOOM = 3;

    // number to scroll up from 6am (2 hour increments: 6am-8am-10am-12am)
    private static final int ZOOMS_OUT_PER_ALL_ZOOM = 6;

    //scrolls to 12am
    private static final int SWIPES_PER_ALL_ZOOM_IN = 3;

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Zoomable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(int) */
    public Zoomable(int resourceId) {
        super(resourceId);
    }

    /** @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(org.hamcrest.Matcher) */
    public Zoomable(Matcher<View> selector) {
        super(selector);
    }


    /** {@inheritDoc} */
    @Override
    protected <E extends PageObject> Zoomable<E> goesTo(Class<E> type) {
        return (Zoomable<E>) super.goesTo(type);
    }

    private static CoordinatesProvider finger1Start;
    private static CoordinatesProvider finger1End;
    private static CoordinatesProvider finger2Start;
    private static CoordinatesProvider finger2End;

    public T zoomAllIn(int phoneMaxX, int phoneMaxY){
        finger1Start = CENTER;
        finger1End = TOP_RIGHT;
        finger2Start = CENTER;
        finger2End = BOTTOM_LEFT;

        zoom(ZOOMS_IN_PER_ALL_ZOOM);

        for (int i = 0; i < SWIPES_PER_ALL_ZOOM_IN; i++)
            performAction(getSwipe(FAST, DOWN));

        return returnGeneric();

    }

    public T zoomAllOut(int phoneMaxX, int phoneMaxY){
        finger1Start = TOP_RIGHT;
        finger1End = CENTER;
        finger2Start = BOTTOM_LEFT;
        finger2End = CENTER;

        return zoom(ZOOMS_OUT_PER_ALL_ZOOM);
    }

    // changed this one to private since we will have uninitialized variables if people black box this function
    private T zoom(int numTimes) {
        ViewAction zoom = new ZoomAction(finger1Start, finger2Start, finger1End, finger2End);

        while (numTimes-- > 0)
            performAction(zoom);

        return returnGeneric();
    }

    /**
     * Root Matchers return LayoutView
     */

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> changeRoot() {
        return (Zoomable<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inRoot(Matcher<Root> rootMatcher) {
        return (Zoomable<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inDialogRoot() {
        return (Zoomable<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inPlatformPopup() {
        return (Zoomable<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inTouchableRoot() {
        return (Zoomable<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Zoomable<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Zoomable<T> inFocusableRoot() {
        return (Zoomable<T>) super.inFocusableRoot();
    }
}
