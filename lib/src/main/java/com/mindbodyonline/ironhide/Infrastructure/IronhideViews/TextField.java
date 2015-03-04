package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.ResourceStrings;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.TextViewMatchers;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static com.mindbodyonline.ironhide.Infrastructure.Extensions.ResourceStrings.fromId;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.startsWith;

/**
 * Simple element that allows text interaction.
 * Implements methods such as typeText, enterText, checkHintText
 * Only use this element when dealing with an editable text field (e.g. an {@link android.widget.EditText})
 *
 * @param <T> The model the current element will return when interacted with
 */
public class TextField<T extends PageObject> extends BaseView<T> {

    /** @see BaseView#BaseView(Class, org.hamcrest.Matcher) */
    public TextField(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    /** @see BaseView#BaseView(int) */
    public TextField(Class<T> type, int resourceId) {
        super(type, resourceId);
    }

    /** @see BaseView#BaseView(int, int) */
    public TextField(Class<T> type, int IGNORED, int stringResourceId) {
        super(type, IGNORED, stringResourceId);
    }

    /** @see BaseView#BaseView(String) */
    public TextField(Class<T> type, String displayText) {
        super(type, displayText);
    }

    /** {@inheritDoc} */
    @Override
    public <E extends PageObject> TextField<E> goesTo(Class<E> type) {
        return new TextField<>(type, getSelector());
    }

    /**
     * Type the given text into the element.
     * @param toType Text to type
     * @return The model reached by interacting with this element.
     */
    public T typeText(String toType) {
        return performAction(ViewActions.typeText(toType));
    }

    /**
     * Type the given text into the element.
     *
     * @param toType Text to type
     * @return The model reached by interacting with this element.
     */
    public T typeText(int stringIdToType) {
        return performAction(ViewActions.typeText(ResourceStrings.fromId(stringIdToType)));
    }

    /**
     * Clear the text from the element.
     * @return The model reached by interacting with this element.
     */
    public T clearText() {
        return performAction(ViewActions.clearText());
    }

    /**
     * Type the given text into the element. Element is assumed to have focus.
     * @param stringToBeTyped Text to type.
     * @return The model reached by interacting with this element.
     */
    public T typeTextIntoFocusedView(String stringToBeTyped) {
        return performAction(ViewActions.typeTextIntoFocusedView(stringToBeTyped));
    }

    /**
     * Replace the current text in the text field with the given new text.
     * @param newText Text to replace with
     * @return The model reached by interacting with this element.
     */
    public T replaceText(String newText) {
        return performAction(ViewActions.replaceText(newText));
    }

    /**
     * Check to see if the element contains the given hint text.
     * @param stringId Resource id of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T withHintText(int stringId) {
        return checkMatches(ViewMatchers.withHint(stringId));
    }

    /**
     * Check to see if the element contains the given hint text.
     * @param string The string to check for.
     * @return The model reached by interacting with this element.
     */
    public T withHintText(String string) {
        return checkMatches(ViewMatchers.withHint(string));
    }

    /**
     * Check to see if the element contains the given hint text.
     * @param stringMatcher String matcher to check against.
     * @return The model reached by interacting with this element.
     */
    public T withHintText(Matcher<String> stringMatcher) { return checkMatches(ViewMatchers.withHint(stringMatcher));}

    /**
     * More matchers
     */

    /**
     * Checks to see if the element contains the string with the given resourceId.
     *
     * @param resourceId Resource ID of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T hintContainsString(int resourceId) {
        return withHintText(containsString(fromId(resourceId)));
    }

    /**
     * Checks to see if a TextView's text ends with a certain string given the string's resource id.
     *
     * @param resourceId The string's resource id
     * @return The model reached by interacting with this element.
     */
    public T hintEndsWith(int resourceId) {
        return withHintText(endsWith(fromId(resourceId)));
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring case) a certain string given the string's resource id.
     *
     * @param resourceId The string's resource id
     * @return The model reached by interacting with this element.
     */
    public T hintEqualToIgnoringCase(int resourceId) {
        return withHintText(equalToIgnoringCase(fromId(resourceId)));
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring white space around words) a certain string given the string's resource id.
     *
     * @param resourceId The string's resource id
     * @return The model reached by interacting with this element.
     */
    public T hintEqualToIgnoringWhiteSpace(int resourceId) {
        return withHintText(equalToIgnoringWhiteSpace(fromId(resourceId)));
    }

    /**
     * Checks to see if a TextView's text is empty or null.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     *
     * @return The model reached by interacting with this element.
     */
    public T isEmptyOrNullHint() {
        return checkMatches(TextViewMatchers.isEmptyOrNullHint());
    }

    /**
     * Checks to see if a TextView's text is empty.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     *
     * @return The model reached by interacting with this element.
     */
    public T isEmptyHint() {
        return checkMatches(TextViewMatchers.isEmptyHint());
    }

    /**
     * Checks to see if a TextView's text starts with a certain string given the string's resource id.
     *
     * @param resourceId The string's resource id
     * @return The model reached by interacting with this element.
     */
    public T hintStartsWith(final int resourceId) {
        return withHintText(startsWith(fromId(resourceId)));
    }

    /**
     * Root Matchers return LayoutView
     */

    /** {@inheritDoc} */
    @Override
    public TextField<T> changeRoot() {
        return (TextField<T>) super.changeRoot();
    }

    /** {@inheritDoc} */
    @Override
    public TextField<T> inRoot(Matcher<Root> rootMatcher) {
        return (TextField<T>) super.inRoot(rootMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public TextField<T> inDialogRoot() {
        return (TextField<T>) super.inDialogRoot();
    }

    /** {@inheritDoc} */
    @Override
    public TextField<T> inPlatformPopup() {
        return (TextField<T>) super.inPlatformPopup();
    }

    /** {@inheritDoc} */
    @Override
    public TextField<T> inTouchableRoot() {
        return (TextField<T>) super.inTouchableRoot();
    }

    /** {@inheritDoc} */
    @Override
    public TextField<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (TextField<T>) super.inDecorView(decorViewMatcher);
    }

    /** {@inheritDoc} */
    @Override
    public TextField<T> inFocusableRoot() {
        return (TextField<T>) super.inFocusableRoot();
    }
}
