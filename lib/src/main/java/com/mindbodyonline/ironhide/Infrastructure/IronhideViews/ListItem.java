package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;

/**
 * Simple element that allows to interact with a single item inside a {@link android.widget.ListView} with an {@link android.widget.Adapter}.
 * Enables to interact with a ListItem and the inner Views it contains.
 * This element should never be instantiated - instead get a reference using a {@link ListAdapter}
 *
 * @param <T> The model the current element will return when interacted with
 */
public class ListItem<T extends PageObject> extends BaseView<T> {

    private final DataInteraction adapter;

    /**
     * @see BaseView#BaseView(Class, org.hamcrest.Matcher)
     * Instead instantiates a {@link DataInteraction}
     */
    @SuppressWarnings("unchecked")
    public ListItem(Class<T> type, DataInteraction adapter) {
        super(type, (Matcher) anything());
        this.adapter = adapter;
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> ListItem<E> goesTo(Class<E> type) {
        return new ListItem<>(type, adapter);
    }

    /**
     * Actions are performed on the DataInteraction instead of the ViewInteraction 
     */

    /** {@inheritDoc} */
    @Override
    protected T performAction(ViewAction viewAction) {
        adapter.perform(viewAction);
        return returnGeneric();
    }

    /** {@inheritDoc} */
    @Override
    protected T checkMatches(Matcher<? super View> viewMatcher) {
        return checkAssertion(ViewAssertions.matches(viewMatcher));
    }

    /** {@inheritDoc} */
    @Override
    protected T checkAssertion(ViewAssertion viewAssertion) {
        adapter.check(viewAssertion);
        return returnGeneric();
    }

    /**
     * @return a new instance of ListItem that is the child of this ListItem
     * @see android.support.test.espresso.DataInteraction#onChildView(org.hamcrest.Matcher)
     */
    public ListItem<T> getChild(BaseView<?> toMatch) {
        return new ListItem<>(type, adapter.onChildView(toMatch.getSelector()));
    }

    /**
     * Root Matchers are not supported in ListItem.
     * @see com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter instead
     */

    /** {@inheritDoc} */
    @Override
    public ListItem<T> inRoot(Matcher<Root> rootMatcher) {
        throw new UnsupportedOperationException("It is too late to call this method. Use ListAdapter's root changers instead");
    }
}
