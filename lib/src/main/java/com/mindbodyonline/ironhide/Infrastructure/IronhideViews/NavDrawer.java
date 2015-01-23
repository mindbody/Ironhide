package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.graphics.pdf.PdfDocument;
import android.support.test.espresso.Root;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Created by Barbara.Wong on 8/14/2014.
 */
public class NavDrawer<T extends PageObject> extends BaseView<T>{

    private NavDrawer(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    public NavDrawer(int resourceId) {
        super(resourceId);
    }

    public NavDrawer(Matcher<View> selector) {
        super(selector);
    }

    public NavDrawer(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    public NavDrawer(String displayText) {
        super(displayText);
    }

    @Override
    public NavDrawer<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public NavDrawer<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }

    @Override
    public <E extends PageObject> NavDrawer<E> goesTo(Class<E> type) {
        return new NavDrawer<E>(type, getSelector());
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
