package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.graphics.Point;
import android.os.SystemClock;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.v4.util.Pair;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.ZoomAction;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_UP;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_INDEX_SHIFT;
import static android.view.MotionEvent.TOOL_TYPE_FINGER;
import static android.view.MotionEvent.obtain;

/**
 * Simple element that allows to perform a zoom on the screen.
 * Provides zoomAllOut, zoomAllIn, and a generic zoom method.
 * Use this when the main purpose of the element will be to zoom in/out
 *
 * @param <T> The model the current element will return when interacted with
 */
@TargetApi(14)
public class Zoomable<T extends PageObject> extends BaseView<T> {

    private static final int DEFAULT_ZOOM_TIME = 3000;

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

    private static Point finger1Start;
    private static Point finger1End;
    private static Point finger2Start;
    private static Point finger2End;

    public T zoomAllIn(int phoneMaxX, int phoneMaxY){
        finger1Start = new Point(phoneMaxX/2, phoneMaxY/2);
        finger1End = new Point(phoneMaxX/2, phoneMaxY/3);
        finger2Start = new Point(finger1Start);
        finger2End = new Point(phoneMaxX/2, phoneMaxY);

        zoom(ZOOMS_IN_PER_ALL_ZOOM);

        for (int i = 0; i < SWIPES_PER_ALL_ZOOM_IN; i++)
            performAction(ViewActions.swipeDown());

        return returnGeneric();

    }

    public T zoomAllOut(int phoneMaxX, int phoneMaxY){
        finger1Start = new Point(phoneMaxX/2, phoneMaxY/3);
        finger1End = new Point(phoneMaxX/2, phoneMaxY/2);
        finger2Start = new Point(phoneMaxX/2, phoneMaxY);
        finger2End = new Point(finger1End);

        return zoom(ZOOMS_OUT_PER_ALL_ZOOM);
    }

    // changed this one to private since we will have uninitialized variables if people black box this function
    private T zoom(int numTimes) {
        ViewAction zoom = new ZoomAction(Pair.create(finger1Start, finger2Start), Pair.create(finger1End, finger2End), DEFAULT_ZOOM_TIME);

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
