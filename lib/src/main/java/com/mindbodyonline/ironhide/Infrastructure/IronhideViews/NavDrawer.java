package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;

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
}
