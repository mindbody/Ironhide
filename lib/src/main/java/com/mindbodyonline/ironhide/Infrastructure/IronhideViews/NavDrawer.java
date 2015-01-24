package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Created by Barbara.Wong on 8/14/2014.
 */
public class NavDrawer<T> extends BaseView<T>{

    public NavDrawer(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    public NavDrawer(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public NavDrawer(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
    }

    public NavDrawer(Class<T> type, String displayText) {
        super(type, displayText);
    }

    public T openDrawer() {

        viewInteraction.perform(actionOpenDrawer());

        pause();

        return returnGeneric();
    }

    /**
     *
     * @return
     */
    public T closeDrawer() {

        viewInteraction.perform(actionCloseDrawer());

        pause();

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
     * Checks to see if the Navigation Drawer is open.
     * @param type The model to return to, used for chaining if a different model is wanted than the default model.
     * @return The model given by the type parameter.
     */
    public <E extends PageObject> E isOpen(Class<E> type) {
        return checkMatches(type, DrawerMatchers.isOpen());
    }

    /**
     * Checks to see if the Navigation Drawer is closed.
     * @return The model reached by interacting with this element.
     */
    public T isClosed() {
        return checkMatches(DrawerMatchers.isClosed());
    }

    /**
     * Checks to see if the Navigation Drawer is closed.
     * @param type The model to return to, used for chaining if a different model is wanted than the default model.
     * @return The model given by the type parameter.
     */
    public <E extends PageObject> E isClosed(Class<E> type) {
        return checkMatches(type, DrawerMatchers.isClosed());
    }

    private ViewAction actionOpenDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(DrawerLayout.class);
            }

            @Override
            public String getDescription() {
                return "open drawer";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).openDrawer(GravityCompat.START);
            }
        };
    }

    private ViewAction actionCloseDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(DrawerLayout.class);
            }

            @Override
            public String getDescription() {
                return "close drawer";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).closeDrawer(GravityCompat.START);
            }
        };
    }

    /**
     * Root Matchers return LayoutView
     */

    @Override
    public NavDrawer<T> changeRoot() {
        return (NavDrawer<T>) super.changeRoot();
    }

    @Override
    public NavDrawer<T> inRoot(Matcher<Root> rootMatcher) {
        return (NavDrawer<T>) super.inRoot(rootMatcher);
    }

    @Override
    public NavDrawer<T> inDialogRoot() {
        return (NavDrawer<T>) super.inDialogRoot();
    }

    @Override
    public NavDrawer<T> inPlatformPopup() {
        return (NavDrawer<T>) super.inPlatformPopup();
    }

    @Override
    public NavDrawer<T> inTouchableRoot() {
        return (NavDrawer<T>) super.inTouchableRoot();
    }

    @Override
    public NavDrawer<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (NavDrawer<T>) super.inDecorView(decorViewMatcher);
    }

    @Override
    public NavDrawer<T> inFocusableRoot() {
        return (NavDrawer<T>) super.inFocusableRoot();
    }
}
