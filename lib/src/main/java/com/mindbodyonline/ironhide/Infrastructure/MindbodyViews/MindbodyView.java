package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;


import android.app.Activity;
import android.net.Uri;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.EspressoKey;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.LayoutAssertions;
import android.support.test.espresso.assertion.PositionAssertions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.DrawerMatchers;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.squareup.spoon.Spoon;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Base Class for all page elements represented in the models for the Connect Test Suite
 * Cannot be instantiated, only serves as a base for other elements
 * Includes all Espresso view matchers, and all view actions that are common to every type of element (e.g.: click(), scrollTo(), etc.)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class MindbodyView<T> {

    protected int id;
    protected int stringId;
    protected String text;
    protected Class<T> type;
    protected Matcher<View> selector;
    protected DataInteraction adapter;
    protected Matcher<Root> rootMatcher = RootMatchers.DEFAULT;

    /**
     * Gets the appropriate selector for the current view element
     * AllOf() ViewMatchers have priority over any other selector, then the order goes as such: String Resource ID, String (Text), XML Android ID
     *
     * @return The highest order selector available for an element
     */
    protected Matcher<View> getSelector() {
        if (selector != null)
            return selector;
        else if (stringId != 0)
            return ViewMatchers.withText(stringId);
        else if (text != null)
            return ViewMatchers.withText(text);
        else
            return ViewMatchers.withId(id);
    }

    /**
     * Performs an Espresso ViewAction on an element
     *
     * @param viewAction The ViewAction to execute
     * @return The model returned by interacting with the element
     */
    protected T performAction(ViewAction viewAction) {
        if (adapter != null)
            adapter.perform(viewAction);
        else
            onView(getSelector()).perform(viewAction);
        return returnGeneric();
    }

    /**
     * Performs an Espresso ViewAction on an element
     *
     * @param type       The type of the PageObject to return
     * @param viewAction The ViewAction to execute
     * @return The model returned by interacting with the element
     */
    protected <E extends PageObject> E performAction(Class<E> type, ViewAction viewAction) {
        if (adapter != null)
            adapter.perform(viewAction);
        else
            onView(getSelector()).perform(viewAction);
        return returnGeneric(type);
    }

    /**
     * Checks if an element matches a certain value using an Espresso ViewMatcher
     *
     * @param viewMatcher The ViewMatcher used to check the element
     * @return The model returned by interacting with the element
     */
    protected T checkMatches(Matcher<? super View> viewMatcher) {
        return checkAssertion(ViewAssertions.matches(viewMatcher));
    }

    /**
     * Checks if an element matches a certain value using an Espresso ViewAssertion
     *
     * @param viewAssertion The ViewAssertion used to check the element
     * @return The model returned by interacting with the element
     */
    protected T checkAssertion(ViewAssertion viewAssertion) {
        if (adapter != null)
            adapter.check(viewAssertion);
        else
            onView(getSelector()).check(viewAssertion);
        return returnGeneric();
    }

    /**
     * Checks if an element matches a certain value using an Espresso ViewMatcher
     *
     * @param type        The type of the PageObject to return
     * @param viewMatcher The ViewMatcher used to check the element
     * @return The model returned by interacting with the element
     */
    protected <E extends PageObject> E checkMatches(Class<E> type, Matcher<? super View> viewMatcher) {
        if (adapter != null)
            adapter.check(ViewAssertions.matches(viewMatcher));
        else
            onView(getSelector()).check(ViewAssertions.matches(viewMatcher));
        return returnGeneric(type);
    }

    /**
     * Used whenever interacting with an element to return the correct following model
     *
     * @return The model returned by interacting with the element
     */
    protected T returnGeneric() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Used whenever interacting with an element to return the correct following model
     *
     * @param type The type of PageObject to return
     * @return The model returned by interacting with the element
     */
    protected <E extends PageObject> E returnGeneric(Class<E> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Changes the root for this view to be anything that is not the default root selected by Espresso.
            * @return this		      * @return this
            **/
//       public MindbodyView<T> changeRoot() {
//           return inRoot(not(is(DEFAULT)));
//    }

    public MindbodyView<T> inRoot(Matcher<Root> rootMatcher) {
        this.rootMatcher = rootMatcher;
        return this;
    }
    /**
     * ViewActions
     */

    public T closeDrawer(int drawerLayoutId) {
        DrawerActions.closeDrawer(drawerLayoutId);

        return returnGeneric();
    }

    public T openDrawer(int drawerLayoutId) {
        DrawerActions.openDrawer(drawerLayoutId);

        return returnGeneric();
    }

    public T scrollTo() {
        return performAction(ViewActions.scrollTo());
    }

    public <E extends PageObject> E scrollTo(Class<E> type) {
        return performAction(type, ViewActions.click());
    }

    public T click() {
        return performAction(ViewActions.click());
    }

    /**
     * Click on the element.
     * @return The model reached by interacting with this element.
     */
    public T click(ViewAction rollbackAction) {
        return performAction(ViewActions.click(rollbackAction));
    }

    public <E extends PageObject> E click(Class<E> type) {
        return performAction(type, ViewActions.click());
    }

    public T pressBack() {
        return performAction(ViewActions.pressBack());
    }

    public T pressImeActionButton() {
        return performAction(ViewActions.pressImeActionButton());
    }

    public T pressMenuKey() {
        return performAction(ViewActions.pressMenuKey());
    }

    public T pressKey(int keyCode) {
        return performAction(ViewActions.pressKey(keyCode));
    }

    public T pressKey(EspressoKey key) {
        return performAction(ViewActions.pressKey(key));
    }

    public T doubleClick() {
        return performAction(ViewActions.doubleClick());
    }

    public T longClick() {
        return performAction(ViewActions.longClick());
    }

    public T closeKeyboard() {
        onView(getSelector()).perform(closeSoftKeyboard());
        return pause();
    }

    public <E extends PageObject> E screenShot(Activity activity, String tag, Class<E> type) {
        Spoon.screenshot(activity, tag);
        return returnGeneric(type);
    }

    /**
     * Opens a link matching the given link text and uri matchers.
     * @param linkTextMatcher Link matcher to check match against.
     * @param uriMatcher URI matcher to match against
     * @return The model reached by interacting with this element
     */
    public T openLink(Matcher<String> linkTextMatcher, Matcher<Uri> uriMatcher){
        return performAction(ViewActions.openLink(linkTextMatcher, uriMatcher));
    }

    /**
     * Opens a link matching the given link text matcher.
     * @param linkTextMatcher Link matcher to check match against.
     * @return The model reached by interacting with this element
     */
    public T openLinkWithText(Matcher<String> linkTextMatcher){
        return performAction(ViewActions.openLinkWithText(linkTextMatcher));
    }

    /**
     * Open a link with the given text.
     * @param linkText Text to match against
     * @return The model reached by interacting with this element
     */
    public T openLinkWithText(String linkText){
        return performAction(ViewActions.openLinkWithText(linkText));
    }

    /**
     * Open a link with the given uri.
     * @param uri Uri to match against
     * @return The model reached by interacting with this element
     */
    public T openLinkWithUri(String uri){
        return performAction(ViewActions.openLinkWithUri(uri));
    }

    /**
     * Open a link with the given uri matcher.
     * @param uriMatcher URI matcher to match against.
     * @return The model reached by interacting with this element
     */
    public T openLinkWithUri(Matcher<Uri> uriMatcher){
        return performAction(ViewActions.openLinkWithUri(uriMatcher));
    }

    /**
     * End ViewActions
     */

    /*============================================================================================*/

    /**
     * ViewMatchers
     */


    public T isOpen() {
        return checkMatches(DrawerMatchers.isOpen());
    }

    public T isClosed() {
        return checkMatches(DrawerMatchers.isClosed());
    }

    public T isDisplayed() {
        return checkMatches(ViewMatchers.isDisplayed());
    }

    public <E extends PageObject> E isDisplayed(Class<E> type) {
        return checkMatches(type, ViewMatchers.isDisplayed());
    }

    public boolean isDisplayedBoolean() {
        try {
            checkMatches(ViewMatchers.isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        } catch (AssertionFailedError e) {
//            if (e.getMessage().contains("Expected: is displayed on the screen to the user")) {
//                return false;
//            }
//            throw e;
            return false;
        }
    }

    public T isNotDisplayed() {
        return checkMatches(not(ViewMatchers.isDisplayed()));
    }

    public T isCompletelyDisplayed() {
        return checkMatches(ViewMatchers.isCompletelyDisplayed());
    }

    public T withText(int stringId) {
        return checkMatches(ViewMatchers.withText(stringId));
    }

    public T withText(String string) {
        return checkMatches(ViewMatchers.withText(string));
    }

    public T withNotText(int stringId) {
        return checkMatches(not(ViewMatchers.withText(stringId)));
    }

    public T withNotText(String string) {
        return checkMatches(not(ViewMatchers.withText(string)));
    }

    public T withText(Matcher<String> stringMatcher) {
        return checkMatches(ViewMatchers.withText(stringMatcher));
    }

    public T isAssignableFrom(Class<? extends View> fromClass) {
        return checkMatches(ViewMatchers.isAssignableFrom(fromClass));
    }

    public T withClassName(Matcher<String> classNameMatcher) {
        return checkMatches(ViewMatchers.withClassName(classNameMatcher));
    }

    public T isDisplayingAtLeast(int areaPercentage) {
        return checkMatches(ViewMatchers.isDisplayingAtLeast(areaPercentage));
    }

    public T isEnabled() {
        return checkMatches(ViewMatchers.isEnabled());
    }

    public T isNotEnabled() {
        return checkMatches(not(ViewMatchers.isEnabled()));
    }

    public T isFocusable() {
        return checkMatches(ViewMatchers.isFocusable());
    }

    public T hasFocus() {
        return checkMatches(ViewMatchers.hasFocus());
    }

    public T hasSibling(MindbodyView<T> sibling) {
        return checkMatches(ViewMatchers.hasSibling(sibling.getSelector()));
    }

    public T withContentDescription(String text) {
        return checkMatches(ViewMatchers.withContentDescription(text));
    }

    public T withContentDescription(Matcher<? extends CharSequence> charSequenceMatcher) {
        return checkMatches(ViewMatchers.withContentDescription(charSequenceMatcher));
    }

    public T withId(int id) {
        return checkMatches(ViewMatchers.withId(id));
    }

    public T withId(Matcher<Integer> integerMatcher) {
        return checkMatches(ViewMatchers.withId(integerMatcher));
    }

    public T withTagKey(int key) {
        return checkMatches(ViewMatchers.withTagKey(key));
    }

    public T withTagKey(int key, Matcher<Object> objectMatcher) {
        return checkMatches(ViewMatchers.withTagKey(key, objectMatcher));
    }

    public T withTagValue(Matcher<Object> tagValueMatcher) {
        return checkMatches(ViewMatchers.withTagValue(tagValueMatcher));
    }

    public T isNotChecked() {
        return checkMatches(ViewMatchers.isNotChecked());
    }

    public T isChecked() {
        return checkMatches(ViewMatchers.isChecked());
    }

    public T hasContentDescription() {
        return checkMatches(ViewMatchers.hasContentDescription());
    }

    public T hasDescendant(MindbodyView<T> descendant) {
        return checkMatches(ViewMatchers.hasDescendant(descendant.getSelector()));
    }

    public T isNotClickable() {
        return checkMatches(not(ViewMatchers.isClickable()));
    }

    public T isClickable() {
        return checkMatches(ViewMatchers.isClickable());
    }

    public T isDescendantOfA(MindbodyView<T> parent) {
        return checkMatches(ViewMatchers.isDescendantOfA(parent.getSelector()));
    }

    public T withEffectiveVisibility(ViewMatchers.Visibility visibility) {
        return checkMatches(ViewMatchers.withEffectiveVisibility(visibility));
    }

    public T withParent(MindbodyView<T> parent) {
        return checkMatches(ViewMatchers.withParent(parent.getSelector()));
    }

    public T withChild(MindbodyView<T> child) {
        return checkMatches(ViewMatchers.withChild(child.getSelector()));
    }

    public T isRoot() {
        return checkMatches(ViewMatchers.isRoot());
    }

    public T supportsInputMethods() {
        return checkMatches(ViewMatchers.supportsInputMethods());
    }

    public T hasImeAction(int imeAction) {
        return checkMatches(ViewMatchers.hasImeAction(imeAction));
    }

    public T hasImeAction(Matcher<Integer> imeActionMatcher) {
        return checkMatches(ViewMatchers.hasImeAction(imeActionMatcher));
    }

    /**
     * TODO: extract this to be more generic similar to TextFieldMatchers
     * Checks to see if the element is a spinner with toString matching given string matcher
     * @return The model reached by interacting with this element.
     */
    public T withSpinnerText(Matcher<String> stringMatcher) {
        return checkMatches(ViewMatchers.withSpinnerText(stringMatcher));
    }

    /**
     * TODO: extract this to be more generic similar to TextFieldMatchers
     * Checks to see if the element is a spinner with toString matching given string matcher
     * @return The model reached by interacting with this element.
     */
    public T withSpinnerText(String text) {
        return checkMatches(ViewMatchers.withSpinnerText(text));
    }

    /**
     * TODO: extract this to be more generic similar to TextFieldMatchers
     * Checks to see if the element is a spinner with toString matching given string matcher
     * @return The model reached by interacting with this element.
     */
    public T withSpinnerText(int resourceid) {
        return checkMatches(ViewMatchers.withSpinnerText(resourceid));
    }


    /**
     * End ViewMatchers
     */

    /*============================================================================================*/

    /**
     * ViewAssertions
     */

    public T doesNotExist() {
        return checkAssertion(ViewAssertions.doesNotExist());
    }

    public T matches(Matcher<View> viewMatcher) {
        return checkAssertion(ViewAssertions.matches(viewMatcher));
    }

    public T selectedDescendantsMatch(Matcher<View> selector, Matcher<View> matcher) {
        return checkAssertion(ViewAssertions.selectedDescendantsMatch(selector, matcher));
    }

    /**
     * End View Assertions
     */

    /**
     * Position-based Assertions
     */

    public T isAbove(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isAbove(matcher));
    }

    public T isBelow(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isBelow(matcher));
    }

    public T isBottomAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isBottomAlignedWith(matcher));
    }

    public T isLeftAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isLeftAlignedWith(matcher));
    }

    public T isLeftOf(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isLeftOf(matcher));
    }

    public T isRightAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isRightAlignedWith(matcher));
    }

    public T isRightOf(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isRightOf(matcher));
    }

    public T isTopAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isTopAlignedWith(matcher));
    }

    /*============================================================================================*/
    /**
     * Layout-based Assertions
     */

    /**
     * Checks whether the entire view hierarchy does not contain ellipsized or cut off text views.
     * @return The model reached by interacting with this element.
     */
    public T noEllipziedText() {
        return checkAssertion(LayoutAssertions.noEllipsizedText());
    }

    /**
     * Checks whether the entire view hierarchy does not contain multiline buttons.
     * @return The model reached by interacting with this element.
     */
    public T noMultilineButtons() {
        return checkAssertion(LayoutAssertions.noMultilineButtons());
    }

    /**
     * Checks that all descendant view matching the selector do not overlap each other.
     * @param selector
     * @return The model reached by interacting with this element.
     */
    public T noOverLaps(Matcher<View> selector){
        return checkAssertion(LayoutAssertions.noOverlaps(selector));
    }

    /**
     * Checks that descendant objects of type TextView or ImageView do not overlap each other.
     * @return The model reached by interacting with this element.
     */
    public T noOverLaps(){
        return checkAssertion(LayoutAssertions.noOverlaps());
    }

    /**
     * End Layout-based Assertions
     */
    /*============================================================================================*/

    /**
     * Misc Helper Methods
     */

    public T pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnGeneric();
    }

    public T pause(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnGeneric();
    }

    public T waitForElement() {

        int pauseTime = 0;

        pause();

        while (pauseTime < 10 && !this.isDisplayedBoolean()) {
            pause();
            pauseTime++;
        }

        return returnGeneric();
    }

    public <E extends PageObject> E waitForElement(Class<E> type) {
        waitForElement();
        return returnGeneric(type);
    }
}
