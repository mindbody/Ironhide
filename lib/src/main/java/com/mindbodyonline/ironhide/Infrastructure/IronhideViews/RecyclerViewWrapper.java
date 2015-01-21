package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Wrapper for RecyclerViewActions that act specifically on RecyclerViews.
 *
 * ViewActions to interact RecyclerView. RecyclerView works differently than AdapterView. In fact, RecyclerView is not an AdapterView anymore, hence it can't be used in combination with onData(Matcher).
 */
public class RecyclerViewWrapper<T> extends BaseView<T> {

    public RecyclerViewWrapper(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public RecyclerViewWrapper(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
    }

    public RecyclerViewWrapper(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    @Override
    public RecyclerViewWrapper<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public RecyclerViewWrapper<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
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
