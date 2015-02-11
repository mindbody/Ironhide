package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.net.Uri;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.TextViewMatchers;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

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
    public TextField(int resourceId) {
        super(resourceId);
    }

    /** @see BaseView#BaseView(int, int) */
    public TextField(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    /** @see BaseView#BaseView(String) */
    public TextField(String displayText) {
        super(displayText);
    }

    /** @see BaseView#BaseView(org.hamcrest.Matcher) */
    public TextField(Matcher<View> selector) {
        super(selector);
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
     * Opens the link that matches the link and uri matchers.
     * @param linkTextMatcher Link matcher to check match against.
     * @param uriMatcher URI matcher to match against
     * @return The model reached by interacting with this element.
     */
    public T openLink(Matcher<String> linkTextMatcher, Matcher<Uri> uriMatcher) {
        return performAction(ViewActions.openLink(linkTextMatcher, uriMatcher));
    }

    /**
     * Open the link that matches the link matcher.
     * @param linkTextMatcher Link matcher to check match against.
     * @return The model reached by interacting with this element.
     */
    public T openLinkWithText(Matcher<String> linkTextMatcher) {
        return performAction(ViewActions.openLinkWithText(linkTextMatcher));
    }

    /**
     * Open the link with the given link text.
     * @param linkText Text to match against
     * @return The model reached by interacting with this element.
     */
    public T openLinkWithText(String linkText) {
        return performAction(ViewActions.openLinkWithText(linkText));
    }

    /**
     * Open the link that matches the uri matcher.
     * @param uriMatcher URI matcher to match against.
     * @return The model reached by interacting with this element.
     */
    public T openLinkWithUri(Matcher<Uri> uriMatcher) {
        return performAction(ViewActions.openLinkWithUri(uriMatcher));
    }

    /**
     * Open the link that matches the given uri text.
     * @param uri Uri to match against
     * @return The model reached by interacting with this element.
     */
    public T openLinkWithUri(String uri) {
        return performAction(ViewActions.openLinkWithUri(uri));
    }

    /**
     * More matchers
     */

    /**
     * Checks if the element has any links in it.
     * @return The model reached by interacting with this element.
     */
    public T hasLinks() {
        return checkMatches(ViewMatchers.hasLinks());
    }

    /**
     * Checks to see if the element contains the string with the given resourceId.
     * @param resourceId Resource ID of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T containsString(int resourceId) {
        return checkMatches(TextViewMatchers.containsString(resourceId));
    }

    /**
     * Checks to see if a TextView's text ends with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return  The model reached by interacting with this element.
     */
    public T endsWith(int resourceId) {
        return checkMatches(TextViewMatchers.endsWith(resourceId));
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring case) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return  The model reached by interacting with this element.
     */
    public T equalToIgnoringCase(int resourceId) {
        return checkMatches(TextViewMatchers.equalToIgnoringCase(resourceId));
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring white space around words) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return   The model reached by interacting with this element.
     */
    public T equalToIgnoringWhiteSpace(int resourceId) {
        return checkMatches(TextViewMatchers.equalToIgnoringWhiteSpace(resourceId));
    }

    /**
     * Checks to see if a TextView's text is empty or null.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  The model reached by interacting with this element.
     */
    public T isEmptyOrNullString() {
        return checkMatches(TextViewMatchers.isEmptyOrNullString());
    }

    /**
     * Checks to see if a TextView's text is empty.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  The model reached by interacting with this element.
     */
    public T isEmptyString() {
        return checkMatches(TextViewMatchers.isEmptyString());
    }

    /**
     * Checks to see if a TextView's text starts with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return   The model reached by interacting with this element.
     */
    public T startsWith(final int resourceId) {
        return checkMatches(TextViewMatchers.startsWith(resourceId));
    }

    /**
     * Checks to see if the Element has ellipsized text
     * @return The model reached by interacting with this element.
     */
    @SuppressWarnings("unchecked")
    public T hasEllipsizeText(){
        return checkMatches(LayoutMatchers.hasEllipsizedText());
    }

    /**
     * Checks to see if the element has multiline text.
     * @return The model reached by interacting with this element.
     */
    @SuppressWarnings("unchecked")
    public T hasMultilineText(){
        return checkMatches(LayoutMatchers.hasMultilineText());
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
