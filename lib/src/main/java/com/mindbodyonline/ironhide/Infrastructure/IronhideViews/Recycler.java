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
 * ViewActions to interact RecyclerView. RecyclerView works differently than AdapterView. In fact, RecyclerView is not an AdapterView anymore, hence it can't be used in combination with onData(Matcher).
 */
public class Recycler<T extends PageObject> extends BaseView<T> {

    public Recycler(Class<T> type, Matcher<View> viewMatcher) {
        super(type, viewMatcher);
    }

    public Recycler(int resourceId) {
        super(resourceId);
    }

    public Recycler(Matcher<View> selector) {
        super(selector);
    }

    public Recycler(int resourceId, int stringResourceId) {
        super(resourceId, stringResourceId);
    }

    @Override
    public Recycler<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public Recycler<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }

    @Override
    public <E extends PageObject> Recycler<E> goesTo(Class<E> type) {
        return new Recycler<E>(type, getSelector());
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
}
