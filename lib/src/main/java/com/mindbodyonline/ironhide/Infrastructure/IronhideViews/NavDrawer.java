package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.contrib.DrawerMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * An element to interact with a {@link android.support.v4.widget.DrawerLayout}.
 * Provides actions {@link NavDrawer#openDrawer(int)}
 * and {@link NavDrawer#closeDrawer(int)}, and checks
 * {@link NavDrawer#isOpen()} and {@link NavDrawer#isClosed()}
 */
public class NavDrawer<T extends PageObject> extends BaseView<T> {

    // the resource id for the navigation drawer
    private final int drawerLayoutId;

    /**
     * @see BaseView#BaseView(Class, org.hamcrest.Matcher)
     * @param resourceId the resource id for the navigation drawer for open/close drawer actions
     */
    public NavDrawer(Class<T> type, int resourceId, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
        this.drawerLayoutId = resourceId;
    }

    /** @see BaseView#BaseView(int) */
    public NavDrawer(int resourceId) {
        super(resourceId);
        this.drawerLayoutId = resourceId;
    }
    
    // Compatibility constructor
    
    public NavDrawer(Class<T> type, int resourceId) {
        this(type, resourceId, ViewMatchers.withId(resourceId));
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> NavDrawer<E> goesTo(Class<E> type) {
        return new NavDrawer<>(type, drawerLayoutId, getSelector());
    }

    /** @see android.support.test.espresso.contrib.DrawerActions#openDrawer(int) */
    public T openDrawer() {
        openDrawer(drawerLayoutId);
        return returnGeneric();
    }

    /** @see android.support.test.espresso.contrib.DrawerActions#closeDrawer(int) */
    public T closeDrawer() {
        closeDrawer(drawerLayoutId);
        return returnGeneric();
    }

    /**
     * Checks to see if the Navigation Drawer is open.
     * @return The model reached by interacting with this element.
     */
    public T isOpen() {
        return checkMatches(DrawerMatchers.isOpen());
    }

    /**
     * Checks to see if the Navigation Drawer is closed.
     * @return The model reached by interacting with this element.
     */
    public T isClosed() {
        return checkMatches(DrawerMatchers.isClosed());
    }

    /**
     * Root Matchers return NavDrawer
     */

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> changeRoot() {
        return (NavDrawer<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> inRoot(Matcher<Root> rootMatcher) {
        return (NavDrawer<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> inDialogRoot() {
        return (NavDrawer<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> inPlatformPopup() {
        return (NavDrawer<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> inTouchableRoot() {
        return (NavDrawer<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (NavDrawer<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public NavDrawer<T> inFocusableRoot() {
        return (NavDrawer<T>) super.inFocusableRoot();
    }
}
