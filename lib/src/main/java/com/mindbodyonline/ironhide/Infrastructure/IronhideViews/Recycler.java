package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

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
 * ViewActions to interact RecyclerView. RecyclerView works differently than AdapterView.
 * RecyclerView is not an {@link android.widget.AdapterView} anymore, hence it can't be used in
 *  combination with {@link android.support.test.espresso.Espresso#onData(org.hamcrest.Matcher)}
 */
public class Recycler<T extends PageObject> extends BaseView<T> {

    /** @see BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public Recycler(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    /** @see BaseView#BaseView(Class, int) */
    public Recycler(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    /** @see BaseView#BaseView(Class,int, int) */
    public Recycler(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
    }
    
    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> Recycler<E> goesTo(Class<E> type) {
        return new Recycler<>(type, getSelector());
    }

    /**
     * @see android.support.test.espresso.contrib.RecyclerViewActions#actionOnHolderItem(org.hamcrest.Matcher, android.support.test.espresso.ViewAction)
     * @return  The model reached by interacting with this element.
     */
    public T actionOnHolderItem(Matcher<? extends RecyclerView.ViewHolder> viewHolderMatcher, ViewAction viewAction) {
        return performAction(RecyclerViewActions.actionOnHolderItem(viewHolderMatcher, viewAction));
    }

    /**
     * @see android.support.test.espresso.contrib.RecyclerViewActions#actionOnItem(org.hamcrest.Matcher, android.support.test.espresso.ViewAction)
     * @return  The model reached by interacting with this element
     */
    public T actionOnItem(Matcher<View> itemViewMatcher, ViewAction viewAction) {
        return performAction(RecyclerViewActions.actionOnItem(itemViewMatcher, viewAction));
    }

    /**
     * @see android.support.test.espresso.contrib.RecyclerViewActions#actionOnItemAtPosition(int, android.support.test.espresso.ViewAction)
     * @return  The model reached by interacting with this element
     */
    public T actionOnItemAtPosition(int position, ViewAction viewAction) {
        return performAction(RecyclerViewActions.actionOnItemAtPosition(position, viewAction));
    }

    /**
     * @see android.support.test.espresso.contrib.RecyclerViewActions#scrollTo(org.hamcrest.Matcher)
     * @return  The model reached by interacting with this element
     */
    public T scrollTo(Matcher<View> itemViewMatcher) {
        return performAction(RecyclerViewActions.scrollTo(itemViewMatcher));
    }

    /**
     * @see android.support.test.espresso.contrib.RecyclerViewActions#scrollToHolder(org.hamcrest.Matcher)
     * @return  The model reached by interacting with this element
     */
    public T scrollToHolder(Matcher<? extends RecyclerView.ViewHolder> viewHolderMatcher) {
        return performAction(RecyclerViewActions.scrollToHolder(viewHolderMatcher));
    }

    /**
     * @see android.support.test.espresso.contrib.RecyclerViewActions#scrollToPosition(int)
     * @return  The model reached by interacting with this element
     */
    public T scrollToPosition(int position) {
        return performAction(RecyclerViewActions.scrollToPosition(position));
    }

    /**
     * Root Matchers return Recycler
     */

    /** {@inheritDoc} */
    @Override
    public Recycler<T> changeRoot() {
        return (Recycler<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Recycler<T> inRoot(Matcher<Root> rootMatcher) {
        return (Recycler<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Recycler<T> inDialogRoot() {
        return (Recycler<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Recycler<T> inPlatformPopup() {
        return (Recycler<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public Recycler<T> inTouchableRoot() {
        return (Recycler<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public Recycler<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (Recycler<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public Recycler<T> inFocusableRoot() {
        return (Recycler<T>) super.inFocusableRoot();
    }
}
