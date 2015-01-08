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
        this.type = type;
        id = resourceId;
    }

    public NavDrawer(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
    }

    public NavDrawer(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public NavDrawer(Class<T> type, String displayText) {
        this.type = type;
        text = displayText;
    }

    public T openDrawer() {

        onView(ViewMatchers.withId(this.id)).perform(actionOpenDrawer());

        pause();

        return returnGeneric();
    }

    /**
     *
     * @return
     */
    public T closeDrawer() {

        onView(ViewMatchers.withId(this.id)).perform(actionCloseDrawer());

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
