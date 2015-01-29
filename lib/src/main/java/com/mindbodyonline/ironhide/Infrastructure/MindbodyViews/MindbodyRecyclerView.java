package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Wrapper for RecyclerViewActions that act specifically on RecyclerViews.
 *
 * ViewActions to interact RecyclerView. RecyclerView works differently than AdapterView. In fact, RecyclerView is not an AdapterView anymore, hence it can't be used in combination with onData(Matcher).
 */
public class MindbodyRecyclerView<T extends PageObject> extends MindbodyView<T> {

    public MindbodyRecyclerView(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    public MindbodyRecyclerView(int resourceId) {
        super(resourceId);
    }

    public MindbodyRecyclerView(Matcher<View> selector) {
        super(selector);
    }
    public MindbodyRecyclerView(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> MindbodyRecyclerView<E> goesTo(Class<E> type) {
        return new MindbodyRecyclerView<E>(type, getSelector());
    }

    public T actionOnHolderItem(Matcher<? extends RecyclerView.ViewHolder> viewHolderMatcher, ViewAction viewAction) {
        return performAction(RecyclerViewActions.actionOnHolderItem(viewHolderMatcher, viewAction));
    }

    public T actionOnItem(Matcher<View> itemViewMatcher, ViewAction viewAction) {
        return performAction(RecyclerViewActions.actionOnItem(itemViewMatcher, viewAction));
    }

    public T actionOnItemAtPosition(int position, ViewAction viewAction) {
        return performAction(RecyclerViewActions.actionOnItemAtPosition(position, viewAction));
    }

    public T scrollTo(Matcher<View> itemViewMatcher) {
        return performAction(RecyclerViewActions.scrollTo(itemViewMatcher));
    }

    public T scrollToHolder(Matcher<? extends RecyclerView.ViewHolder> viewHolderMatcher) {
        return performAction(RecyclerViewActions.scrollToHolder(viewHolderMatcher));
    }

    public T scrollToPosition(int position) {
        return performAction(RecyclerViewActions.scrollToPosition(position));
    }

    /**
     * Root Matchers return Recycler
     */

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> changeRoot() {
        return (MindbodyRecyclerView<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> inRoot(Matcher<Root> rootMatcher) {
        return (MindbodyRecyclerView<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> inDialogRoot() {
        return (MindbodyRecyclerView<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> inPlatformPopup() {
        return (MindbodyRecyclerView<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> inTouchableRoot() {
        return (MindbodyRecyclerView<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (MindbodyRecyclerView<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public MindbodyRecyclerView<T> inFocusableRoot() {
        return (MindbodyRecyclerView<T>) super.inFocusableRoot();
    }
}
