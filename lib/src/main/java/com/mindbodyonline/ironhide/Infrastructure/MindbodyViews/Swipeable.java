package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.Swiper;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.action.Swipe.FAST;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.getSwipe;

/**
 * Simple element that allows to perform a swipe on the screen.
 * Allows fast/slow versions of Espresso's swipes, as well as changing the number of swipes done in
 *  one function call.
 * Use this when the main purpose of the element will be to swipe the screen
 *
 * @param <T> The model the current element will return when interacted with
 */
public class Swipeable<T extends PageObject> extends BaseView<T> {

    public static final int DEFAULT_TIMES = 1;
    public static final Swiper DEFAULT_SPEED = FAST;

    /** @see BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Swipeable(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    /** @see BaseView#BaseView(Class, int) */
    public Swipeable(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> Swipeable<E> goesTo(Class<E> type) {
        return new Swipeable<>(type, getSelector());
    }

    /**
     * {@link Swipeable#swipe(
     *  android.support.test.espresso.action.Swiper,
     *  com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection,
     *  int)}
     * with {@link Swipeable#DEFAULT_SPEED}
     * and {@link Swipeable#DEFAULT_TIMES}
     */
    public T swipe(SwipeAction.SwipeDirection direction) {
        return swipe(DEFAULT_SPEED, direction, DEFAULT_TIMES);
    }

    /**
     * {@link Swipeable#swipe(
     *  android.support.test.espresso.action.Swiper,
     *  com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection,
     *  int)}
     * with {@link Swipeable#DEFAULT_TIMES}
     */
    public T swipe(Swiper speed, SwipeAction.SwipeDirection direction) {
        return swipe(speed, direction, DEFAULT_TIMES);
    }

    /**
     * {@link Swipeable#swipe(
     *  android.support.test.espresso.action.Swiper,
     *  com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection,
     *  int)}
     * with {@link Swipeable#DEFAULT_SPEED}
     */
    public T swipe(SwipeAction.SwipeDirection direction, int times) {
        return swipe(DEFAULT_SPEED, direction, times);
    }

    /**
     * Performs a generic swipe action on the element
     * @param speed the speed of the swipe action
     * @param direction the direction of the swipe action
     * @param times the number of times to perform the swipe action
     * @return  The model reached by interacting with this element.
     */
    public T swipe(Swiper speed, SwipeAction.SwipeDirection direction, int times) {
        ViewAction swipe = getSwipe(speed, direction);
        while (times-- > 0)
            performAction(swipe);

        return returnGeneric();
    }

    /**
     * Root Matchers return Swipeable
     */

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> changeRoot() {
        return (Swipeable<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inRoot(Matcher<Root> rootMatcher) {
        return (Swipeable<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inDialogRoot() {
        return (Swipeable<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inPlatformPopup() {
        return (Swipeable<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inTouchableRoot() {
        return (Swipeable<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Swipeable<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Swipeable<T> inFocusableRoot() {
        return (Swipeable<T>) super.inFocusableRoot();
    }
}
