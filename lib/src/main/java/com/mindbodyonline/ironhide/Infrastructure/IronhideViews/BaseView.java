package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;


import android.app.Activity;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.EspressoKey;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.PositionAssertions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.DrawerMatchers;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.Matchers.not;

/**
 * Base Class for all page elements represented in the models for the Connect Test Suite
 * Cannot be instantiated, only serves as a base for other elements
 * Includes all Espresso view matchers, and all view actions that are common to every type of element (e.g.: click(), scrollTo(), etc.)
 *
 * @param <T> The model the current element will return when interacted with
 */
public class BaseView<T> {

    protected int id;
    protected int stringId;
    protected String text;
    protected Class<T> type;
    protected Matcher<View> selector;
    protected DataInteraction adapter;

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
     * TODO: Need to find a way to support Root Views if we want total completeness
     */
//    protected T checkRootMatches(Matcher<android.support.test.espresso.Root> viewMatcher) {
//        return checkAssertion(ViewAssertions.matches(viewMatcher));
//    }




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

    /**
     * ViewActions
     */

    /**
     * Close the Navigation Drawer.
     * @param drawerLayoutId The android id of the Drawer Layout
     * @return The model returned by interacting with the Element.
     */
    public T closeDrawer(int drawerLayoutId) {
        DrawerActions.closeDrawer(drawerLayoutId);

        return returnGeneric();
    }

    /**
     * Open the Navigation drawer.
     * @param drawerLayoutId The android id of the Drawer Layout
     * @return The model returned by interacting with the Element.
     */
    public T openDrawer(int drawerLayoutId) {
        DrawerActions.openDrawer(drawerLayoutId);

        return returnGeneric();
    }

    /**
     * Scroll to the element.
     * @return The model returned by interacting with the element.
     */
    public T scrollTo() {
        return performAction(ViewActions.scrollTo());
    }

    /**
     * Scroll to the element, but return the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return     The model returned by interacting with the element.
     */
    public <E extends PageObject> E scrollTo(Class<E> type) {
        return performAction(type, ViewActions.click());
    }

    /**
     * Click on the element.
     * @return The model returned by interacting with the element.
     */
    public T click() {
        return performAction(ViewActions.click());
    }

    /**
     * Click on the element, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return     The model given by the type parameter.
     */
    public <E extends PageObject> E click(Class<E> type) {
        return performAction(type, ViewActions.click());
    }

    /**
     * Press the back button.
     * @return     The model returned by interacting with the element.
     */
    public T pressBack() {
        return performAction(ViewActions.pressBack());
    }

    /**
     * Press the back button, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return    The model given by the type parameter.
     */
    public <E extends PageObject> E pressBack(Class<E> type) {
        return performAction(type, ViewActions.pressBack());
    }

    /**
     * Press the Ime action button.
     * @return     The model returned by interacting with the element.
     */
    public T pressImeActionButton() {
        return performAction(ViewActions.pressImeActionButton());
    }

    /**
     * Press the Ime action button, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return     The model given by the type parameter.
     */
    public <E extends PageObject> E pressImeActionButton(Class<E> type) {
        return performAction(type, ViewActions.pressImeActionButton());
    }

    /**
     * Press the menu button.
     * @return     The model returned by interacting with the element.
     */
    public T pressMenuKey() {
        return performAction(ViewActions.pressMenuKey());
    }

    /**
     * Press the menu button, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return     The model given by the type parameter.
     */
    public <E extends PageObject> E pressMenuKey(Class<E> type) {
        return performAction(type, ViewActions.pressMenuKey());
    }

    /**
     * Press the specified key.
     * @param keyCode The integer value of the key to be pressed.
     * @return     The model returned by interacting with the element.
     */
    public T pressKey(int keyCode) {
        return performAction(ViewActions.pressKey(keyCode));
    }

    /**
     * Press the specified key.
     * @param type The PageObject to return to.
     * @param keyCode The integer value of the key to be pressed.
     * @return   The model given by the type parameter.
     */
    public <E extends PageObject> E pressKey(Class<E> type, int keyCode) {
        return performAction(type, ViewActions.pressKey(keyCode));
    }

    /**
     * Press the specified key.
     * @param key The EspressoKey object representation of the key to be pressed.
     * @return The model returned by interacting with the element.
     */
    public T pressKey(EspressoKey key) {
        return performAction(ViewActions.pressKey(key));
    }

    /**
     * Press the specified key, but return to the specified model (Given by the "type" argument).
     * @param keyCode The EspressoKey object representation of the key to be pressed.
     * @param type The PageObject to return to.
     * @return     The model given by the type parameter.
     */
    public <E extends PageObject> E pressKey(Class<E> type, EspressoKey keyCode) {
        return performAction(type, ViewActions.pressKey(keyCode));
    }

    /**
     * Double click on the element.
     * @return     The model returned by interacting with the element.
     */
    public T doubleClick() {
        return performAction(ViewActions.doubleClick());
    }

    /**
     * Double click on the element, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return    The model given by the type parameter.
     */
    public <E extends PageObject> E doubleClick(Class<E> type) {
        return performAction(type, ViewActions.doubleClick());
    }

    /**
     * Long click on the element.
     * @return     The model returned by interacting with the element.
     */
    public T longClick() {
        return performAction(ViewActions.longClick());
    }

    /**
     * Long click on the element, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return     The model given by the type parameter.
     */
    public <E extends PageObject> E longClick(Class<E> type) {
        return performAction(type, ViewActions.longClick());
    }

    /**
     * Close the on-screen keyboard.
     * @return     The model returned by interacting with the element.
     */
    public T closeKeyboard() {
        onView(getSelector()).perform(closeSoftKeyboard());
        return pause();
    }

    /**
     * Close the on-screen keyboard, but return to the specified model (Given by the "type" argument).
     * @param type The PageObject to return to.
     * @return     The model given by the type parameter.
     */
    public <E extends PageObject> E closeKeyboard(Class<E> type) {
        return performAction(type, ViewActions.closeSoftKeyboard());
    }


    /**
     * End ViewActions
     */

    /*============================================================================================*/

    /**
     * ViewMatchers
     */


    /**
     * Checks to see if the Navigation Drawer is open.
     * @return The model returned by interacting with this element.
     */
    public T isOpen() {
        return checkMatches(DrawerMatchers.isOpen());
    }

    public <E extends PageObject> E isOpen(Class<E> type) {
        return checkMatches(type, DrawerMatchers.isOpen());
    }

    /**
     * Checks to see if the Navigation Drawer is closed.
     * @return The model returned by interacting with this element.
     */
    public T isClosed() {
        return checkMatches(DrawerMatchers.isClosed());
    }

    public <E extends PageObject> E isClosed(Class<E> type) {
        return checkMatches(type, DrawerMatchers.isClosed());
    }

    /**
     * Checks to see if the given object is at least partially displayed on the screen.
     * @return The model returned by interacting with this element.
     */
    public T isDisplayed() {
        return checkMatches(ViewMatchers.isDisplayed());
    }

    /**
     * Checks to see if the given object is at least partially displayed on the screen.
     * @param type The PageObject to return to.
     * @return The model given by the type object.
     */
    public <E extends PageObject> E isDisplayed(Class<E> type) {
        return checkMatches(type, ViewMatchers.isDisplayed());
    }

    /**
     * If this can't be refactored into something cleaner will be removed for Open Source release.
     * @return
     */
//    public boolean isDisplayedBoolean() {
//        try {
//            checkMatches(ViewMatchers.isDisplayed());
//            return true;
//        } catch (Exception e) {
//            return false;
//        } catch (AssertionFailedError e) {
////            if (e.getMessage().contains("Expected: is displayed on the screen to the user")) {
////                return false;
////            }
////            throw e;
//            return false;
//        }
//    }


    public T isNotDisplayed() {
        return checkMatches(not(ViewMatchers.isDisplayed()));
    }

    public <E extends PageObject> E isNotDisplayed(Class<E> type) {
        return checkMatches(type, not(ViewMatchers.isDisplayed()));
    }

    public T isCompletelyDisplayed() {
        return checkMatches(ViewMatchers.isCompletelyDisplayed());
    }

    public <E extends PageObject> E isCompletelyDisplayed(Class<E> type) {
        return checkMatches(type, ViewMatchers.isCompletelyDisplayed());
    }

    public T withText(int stringId) {
        return checkMatches(ViewMatchers.withText(stringId));
    }

    public <E extends PageObject> E withText(Class<E> type, int stringId) {
        return checkMatches(type, ViewMatchers.withText(stringId));
    }


    public T withText(String string) {
        return checkMatches(ViewMatchers.withText(string));
    }

    public <E extends PageObject> E withText(Class<E> type, String string) {
        return checkMatches(type, ViewMatchers.withText(string));
    }

    public T withNotText(int stringId) {
        return checkMatches(not(ViewMatchers.withText(stringId)));
    }

    public <E extends PageObject> E withNotText(Class<E> type, int stringId) {
        return checkMatches(type, not(ViewMatchers.withText(stringId)));
    }

    public T withNotText(String string) {
        return checkMatches(not(ViewMatchers.withText(string)));
    }

    public <E extends PageObject> E withNotText(Class<E> type, String string) {
        return checkMatches(type, not(ViewMatchers.withText(string)));
    }

    public T withText(Matcher<String> stringMatcher) {
        return checkMatches(ViewMatchers.withText(stringMatcher));
    }

    public <E extends PageObject> E withText(Class<E> type, Matcher<String> stringMatcher) {
        return checkMatches(type, ViewMatchers.withText(stringMatcher));
    }

    public T isAssignableFrom(Class<? extends View> fromClass) {
        return checkMatches(ViewMatchers.isAssignableFrom(fromClass));
    }

    public <E extends PageObject> E isAssignableFrom(Class<E> type, Class<? extends View> fromClass) {
        return checkMatches(type, ViewMatchers.isAssignableFrom(fromClass));
    }

    public T withClassName(Matcher<String> classNameMatcher) {
        return checkMatches(ViewMatchers.withClassName(classNameMatcher));
    }

    public <E extends PageObject> E withClassName(Class<E> type, Matcher<String> classNameMatcher) {
        return checkMatches(type, ViewMatchers.withClassName(classNameMatcher));
    }

    public T isDisplayingAtLeast(int areaPercentage) {
        return checkMatches(ViewMatchers.isDisplayingAtLeast(areaPercentage));
    }

    public <E extends PageObject> E withClassName(Class<E> type, int areaPercentage) {
        return checkMatches(type, ViewMatchers.isDisplayingAtLeast(areaPercentage));
    }

    public T isEnabled() {
        return checkMatches(ViewMatchers.isEnabled());
    }

    public <E extends PageObject> E withClassName(Class<E> type) {
        return checkMatches(type, ViewMatchers.isEnabled());
    }

    public T isNotEnabled() {
        return checkMatches(not(ViewMatchers.isEnabled()));
    }

    public <E extends PageObject> E isNotEnabled(Class<E> type) {
        return checkMatches(type, not(ViewMatchers.isEnabled()));
    }

    public T isFocusable() {
        return checkMatches(ViewMatchers.isFocusable());
    }

    public <E extends PageObject> E isFocusable(Class<E> type) {
        return checkMatches(type, ViewMatchers.isFocusable());
    }

    public T hasFocus() {
        return checkMatches(ViewMatchers.hasFocus());
    }

    public <E extends PageObject> E hasFocus(Class<E> type) {
        return checkMatches(type, ViewMatchers.hasFocus());
    }

    public T hasSibling(BaseView<T> sibling) {
        return checkMatches(ViewMatchers.hasSibling(sibling.getSelector()));
    }

    public <E extends PageObject> E hasSibling(Class<E> type, BaseView<T> sibling) {
        return checkMatches(type, ViewMatchers.hasSibling(sibling.getSelector()));
    }

    public T withContentDescription(String text) {
        return checkMatches(ViewMatchers.withContentDescription(text));
    }

    public <E extends PageObject> E withContentDescription(Class<E> type, String text) {
        return checkMatches(type, ViewMatchers.withContentDescription(text));
    }

    public T withContentDescription(Matcher<? extends CharSequence> charSequenceMatcher) {
        return checkMatches(ViewMatchers.withContentDescription(charSequenceMatcher));
    }

    public <E extends PageObject> E withContentDescription(Class<E> type, Matcher<? extends CharSequence> charSequenceMatcher) {
        return checkMatches(type, ViewMatchers.withContentDescription(charSequenceMatcher));
    }

    public T withId(int id) {
        return checkMatches(ViewMatchers.withId(id));
    }

    public <E extends PageObject> E withId(Class<E> type, int id) {
        return checkMatches(type, ViewMatchers.withId(id));
    }

    public T withId(Matcher<Integer> integerMatcher) {
        return checkMatches(ViewMatchers.withId(integerMatcher));
    }

    public <E extends PageObject> E withId(Class<E> type, Matcher<Integer> integerMatcher) {
        return checkMatches(type, ViewMatchers.withId(integerMatcher));
    }

    public T withTagKey(int key) {
        return checkMatches(ViewMatchers.withTagKey(key));
    }

    public <E extends PageObject> E withTagKey(Class<E> type, int key) {
        return checkMatches(type, ViewMatchers.withTagKey(key));
    }

    public T withTagKey(int key, Matcher<Object> objectMatcher) {
        return checkMatches(ViewMatchers.withTagKey(key, objectMatcher));
    }

    public <E extends PageObject> E withTagKey(Class<E> type, int key,  Matcher<Object> objectMatcher) {
        return checkMatches(type, ViewMatchers.withTagKey(key, objectMatcher));
    }

    public T withTagValue(Matcher<Object> tagValueMatcher) {
        return checkMatches(ViewMatchers.withTagValue(tagValueMatcher));
    }

    public <E extends PageObject> E withTagValue(Class<E> type, Matcher<Object> tagValueMatcher) {
        return checkMatches(type, ViewMatchers.withTagValue(tagValueMatcher));
    }

    public T isNotChecked() {
        return checkMatches(ViewMatchers.isNotChecked());
    }

    public <E extends PageObject> E isNotChecked(Class<E> type) {
        return checkMatches(type, ViewMatchers.isNotChecked());
    }

    public T isChecked() {
        return checkMatches(ViewMatchers.isChecked());
    }

    public <E extends PageObject> E isChecked(Class<E> type) {
        return checkMatches(type, ViewMatchers.isChecked());
    }

    public T hasContentDescription() {
        return checkMatches(ViewMatchers.hasContentDescription());
    }

    public <E extends PageObject> E hasContentDescription(Class<E> type) {
        return checkMatches(type, ViewMatchers.hasContentDescription());
    }

    public T hasDescendant(BaseView<T> descendant) {
        return checkMatches(ViewMatchers.hasDescendant(descendant.getSelector()));
    }

    public <E extends PageObject> E hasDescendant(Class<E> type, BaseView<T> descendant) {
        return checkMatches(type, ViewMatchers.hasDescendant(descendant.getSelector()));
    }

    public T isNotClickable() {
        return checkMatches(not(ViewMatchers.isClickable()));
    }

    public <E extends PageObject> E isNotClickable(Class<E> type) {
        return checkMatches(type, not(ViewMatchers.isClickable()));
    }

    public T isClickable() {
        return checkMatches(ViewMatchers.isClickable());
    }

    public <E extends PageObject> E isClickable(Class<E> type) {
        return checkMatches(type, ViewMatchers.isClickable());
    }

    public T isDescendantOfA(BaseView<T> parent) {
        return checkMatches(ViewMatchers.isDescendantOfA(parent.getSelector()));
    }

    public <E extends PageObject> E isDescendantOfA(Class<E> type, BaseView<T> parent) {
        return checkMatches(type, ViewMatchers.isDescendantOfA(parent.getSelector()));
    }

    public T withEffectiveVisibility(ViewMatchers.Visibility visibility) {
        return checkMatches(ViewMatchers.withEffectiveVisibility(visibility));
    }

    public <E extends PageObject> E withEffectiveVisibility(Class<E> type, ViewMatchers.Visibility visibility) {
        return checkMatches(type, ViewMatchers.withEffectiveVisibility(visibility));
    }

    public T withParent(BaseView<T> parent) {
        return checkMatches(ViewMatchers.withParent(parent.getSelector()));
    }

    public <E extends PageObject> E withParent(Class<E> type, BaseView<T> parent) {
        return checkMatches(type, ViewMatchers.withParent(parent.getSelector()));
    }

    public T withChild(BaseView<T> child) {
        return checkMatches(ViewMatchers.withChild(child.getSelector()));
    }

    public <E extends PageObject> E withChile(Class<E> type, BaseView<T> child) {
        return checkMatches(type, ViewMatchers.withChild(child.getSelector()));
    }

    public T isRoot() {
        return checkMatches(ViewMatchers.isRoot());
    }

    public <E extends PageObject> E isRoot(Class<E> type) {
        return checkMatches(type, ViewMatchers.isRoot());
    }

    public T supportsInputMethods() {
        return checkMatches(ViewMatchers.supportsInputMethods());
    }

    public <E extends PageObject> E supportsInputMethods(Class<E> type) {
        return checkMatches(type, ViewMatchers.supportsInputMethods());
    }

    public T hasImeAction(int imeAction) {
        return checkMatches(ViewMatchers.hasImeAction(imeAction));
    }

    public <E extends PageObject> E hasImeAction(Class<E> type, int imeAction) {
        return checkMatches(type, ViewMatchers.hasImeAction(imeAction));
    }

    public T hasImeAction(Matcher<Integer> imeActionMatcher) {
        return checkMatches(ViewMatchers.hasImeAction(imeActionMatcher));
    }
    public <E extends PageObject> E hasImeAction(Class<E> type, Matcher<Integer> imeActionMatcher) {
        return checkMatches(type, ViewMatchers.hasImeAction(imeActionMatcher));
    }

    /**
     * Unable to use RootMatchers as of yet.
     */
//    public T isDialog(){
//        return checkMatches(RootMatchers.isDialog());
//    }

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

    /**
     * Potential removal for Open Source if can't find an implementation that depends on isDisplayedBoolean.
     */
//    public T waitForElement() {
//
//        int pauseTime = 0;
//
//        pause();
//
//        while (pauseTime < 10 && !this.isDisplayedBoolean()) {
//            pause();
//            pauseTime++;
//        }
//
//        return returnGeneric();
//    }
//
//    public <E extends PageObject> E waitForElement(Class<E> type) {
//        waitForElement();
//        return returnGeneric(type);
//    }
}
