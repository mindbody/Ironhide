package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.net.Uri;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.EnterTextAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.SetCursorAction;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;

/**
 * Extends BaseView
 * Simple element that allows text interaction
 * Implements methods such as typeText, enterText, checkHintText
 * Only use this element when dealing with an editable text field (e.g.: an EditText View)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class TextField<T> extends BaseView<T> {

    public TextField(Class<T> type, int resourceId) {
        this.type = type;
        id = resourceId;
    }

    public TextField(Class<T> type, int resourceId, int stringResourceId) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
    }

    public TextField(Class<T> type, int resourceId, int stringResourceId, String displayText) {
        this.type = type;
        id = resourceId;
        stringId = stringResourceId;
        text = displayText;
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
        return checkMatches(BaseViewMatchers.withHintText(stringId));
    }

    public T withHintText(String string) {
        return checkMatches(BaseViewMatchers.withHintText(string));
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
}
