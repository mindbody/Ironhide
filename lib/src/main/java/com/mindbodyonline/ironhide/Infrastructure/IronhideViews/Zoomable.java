package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.annotation.TargetApi;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.ZoomAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.ZoomAction.ZoomDirection;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoomer;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static com.mindbodyonline.ironhide.Infrastructure.Extensions.Zoom.FAST;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.ZoomAction.ZoomDirection.IN;

/**
 * Simple element that allows to perform a zoom on the screen.
 * Provides zoomAllOut, zoomAllIn, and a generic zoom method.
 * Use this when the main purpose of the element will be to zoom in/out
 *
 * @param <T> The model the current element will return when interacted with
 */
@TargetApi(14)
public class Zoomable<T extends PageObject> extends Swipeable<T> {

    public static final Zoomer DEFAULT_ZOOM_SPEED = FAST;
    public static final ZoomDirection DEFAULT_ZOOM_DIRECTION = IN;
    public static final int DEFAULT_ZOOM_TIMES = 1;

    /** @see BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Zoomable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    /** @see BaseView#BaseView(Class, int) */
    public Zoomable(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> Zoomable<E> goesTo(Class<E> type) {
        return new Zoomable<>(type, getSelector());
    }

    public T zoom() {
        return zoom(DEFAULT_ZOOM_SPEED, DEFAULT_ZOOM_DIRECTION, DEFAULT_ZOOM_TIMES);
    }

    public T zoom(Zoomer speed) {
        return zoom(speed, DEFAULT_ZOOM_DIRECTION, DEFAULT_ZOOM_TIMES);
    }

    public T zoom(ZoomDirection direction) {
        return zoom(DEFAULT_ZOOM_SPEED, direction, DEFAULT_ZOOM_TIMES);
    }

    public T zoom(int times) {
        return zoom(DEFAULT_ZOOM_SPEED, DEFAULT_ZOOM_DIRECTION, times);
    }

    public T zoom(Zoomer speed, ZoomDirection direction) {
        return zoom(speed, direction, DEFAULT_ZOOM_TIMES);
    }

    public T zoom(Zoomer speed, int times) {
        return zoom(speed, DEFAULT_ZOOM_DIRECTION, times);
    }

    public T zoom(ZoomDirection direction, int times) {
        return zoom(DEFAULT_ZOOM_SPEED, direction, times);
    }

    public T zoom(Zoomer speed, ZoomDirection direction, int times) {
        ViewAction zoom = ZoomAction.getZoom(speed, direction);
        while (times-- > 0)
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
