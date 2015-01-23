package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.net.Uri;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.EnterTextAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.SetCursorAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.TextViewMatchers;
import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

/**
 * Extends BaseView
 * Simple element that allows text interaction
 * Implements methods such as typeText, enterText, checkHintText
 * Only use this element when dealing with an editable text field (e.g.: an EditText View)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class TextField<T extends PageObject> extends BaseView<T> {

    private TextField(Class<T> type, Matcher<View> selector) {
        super(type, selector);
    }

    public TextField(int resourceId) {
        super(resourceId);
    }

    public TextField(int IGNORED, int stringResourceId) {
        super(IGNORED, stringResourceId);
    }

    public TextField(int resourceId, int stringResourceId, String displayText) {
        super(displayText);
    }

    public TextField(Matcher<View> selector) {
        super(selector);
    }

    @Override
    public TextField<T> changeRoot() {
        super.changeRoot();
        return this;
    }

    @Override
    public TextField<T> inRoot(Matcher<Root> rootMatcher) {
        super.inRoot(rootMatcher);
        return this;
    }

    @Override
    public <E extends PageObject> TextField<E> goesTo(Class<E> type) {
        return new TextField<E>(type, getSelector());
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
     * Method to enter text by simulating a user inputting to the android keyboard.
     * Useful when interacting with an EditText View which uses an OnTextChangedListener
     * @param toType Text to type.
     * @return The model reached by interacting with this element.
     */
    public T enterText(String toType) {
        return performAction(enterTextAction(toType));
    }

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
        return checkMatches(ViewMatchers.withHint(stringId));
    }

    public T withHintText(String string) {
        return checkMatches(ViewMatchers.withHint(string));
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
}
