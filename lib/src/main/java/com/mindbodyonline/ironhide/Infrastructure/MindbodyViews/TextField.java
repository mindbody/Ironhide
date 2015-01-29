package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;

import android.net.Uri;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.EnterTextAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.SetCursorAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.TextViewMatchers;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;

/**
 * Extends MindbodyView
 * Simple element that allows text interaction
 * Implements methods such as typeText, enterText, checkHintText
 * Only use this element when dealing with an editable text field (e.g.: an EditText View)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class TextField<T extends PageObject> extends MindbodyView<T> {

    public TextField(Class<T> type, int resourceId) {
        super(resourceId);
        this.type = type;
        id = resourceId;
    }

    public TextField(Class<T> type, int resourceId, int stringResourceId) {
        super(resourceId);
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public TextField(Class<T> type, int resourceId, int stringResourceId, String displayText) {
        super(resourceId);
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
        text = displayText;
    }

    public TextField(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public TextField(int resourceId) {
        super(resourceId);
    }

    public TextField(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    public TextField(String displayText) {
        super(displayText);
    }

    public TextField(Matcher<View> selector) {
        super(selector);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends PageObject> TextField<E> goesTo(Class<E> type) {
        return new TextField<E>(type, getSelector());
    }

    public T typeText(String toType) {
        return performAction(ViewActions.typeText(toType));
    }

    public T clearText() {
        return performAction(ViewActions.clearText());
    }

    public T typeTextIntoFocusedView(String stringToBeTyped) {
        return performAction(ViewActions.typeTextIntoFocusedView(stringToBeTyped));
    }

    public T enterText(String toType) {
        return performAction(enterTextAction(toType));
    }

    public T replaceText(String toType) { return performAction(ViewActions.replaceText(toType)); }
    
    public T setCursor() {
        return performAction(setCursorAction());
    }

    private EnterTextAction enterTextAction(String toType) {
        return new EnterTextAction(toType);
    }

    private SetCursorAction setCursorAction() {
        return new SetCursorAction();
    }

    public boolean textFieldWithText(String string) {
        try {
            checkMatches(ViewMatchers.withText(string));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public T withHintText(int stringId) {
        return checkMatches(MindbodyViewMatchers.withHintText(stringId));
    }

    public T withHintText(String string) {
        return checkMatches(MindbodyViewMatchers.withHintText(string));
    }

    public T openLink(Matcher<String> linkTextMatcher, Matcher<Uri> uriMatcher) {
        return performAction(ViewActions.openLink(linkTextMatcher, uriMatcher));
    }

    public T openLinkWithText(Matcher<String> linkTextMatcher) {
        return performAction(ViewActions.openLinkWithText(linkTextMatcher));
    }

    public T openLinkWithUri(Matcher<Uri> uriMatcher) {
        return performAction(ViewActions.openLinkWithUri(uriMatcher));
    }

    /**
     * More matchers
     */


    /**
     * Checks if the element has any links in it.
     *
     * @return The model reached by interacting with this element.
     */
    public T hasLinks() {
        return checkMatches(ViewMatchers.hasLinks());
    }

    /**
     * Checks to see if the element contains the string with the given resourceId.
     *
     * @param resourceId Resource ID of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T containsString(int resourceId) {
        return checkMatches(TextViewMatchers.containsString(resourceId));
    }

    public T endsWith(int resourceId) {
        return checkMatches(TextViewMatchers.endsWith(resourceId));
    }

    public T equalToIgnoringCase(int resourceId) {
        return checkMatches(TextViewMatchers.equalToIgnoringCase(resourceId));
    }

    public T equalToIgnoringWhiteSpace(int resourceId) {
        return checkMatches(TextViewMatchers.equalToIgnoringWhiteSpace(resourceId));
    }

    public T isEmptyOrNullString() {
        return checkMatches(TextViewMatchers.isEmptyOrNullString());
    }

    public T isEmptyString() {
        return checkMatches(TextViewMatchers.isEmptyString());
    }

    public T startsWith(final int resourceId) {
        return checkMatches(TextViewMatchers.startsWith(resourceId));
    }

    public T hasEllipsizeText() {
        return checkMatches(LayoutMatchers.hasEllipsizedText());
    }

    public T hasMultilineText() {
        return checkMatches(LayoutMatchers.hasMultilineText());
    }

    /**
     * Root Matchers return LayoutView
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> changeRoot() {
        return (TextField<T>) super.changeRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> inRoot(Matcher<Root> rootMatcher) {
        return (TextField<T>) super.inRoot(rootMatcher);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> inDialogRoot() {
        return (TextField<T>) super.inDialogRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> inPlatformPopup() {
        return (TextField<T>) super.inPlatformPopup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> inTouchableRoot() {
        return (TextField<T>) super.inTouchableRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> inDecorView(Matcher<View> decorViewMatcher) {
        return (TextField<T>) super.inDecorView(decorViewMatcher);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextField<T> inFocusableRoot() {
        return (TextField<T>) super.inFocusableRoot();
    }
}