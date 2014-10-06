package com.mindbodyonline.ironhide.Infrastructure.MindbodyViews;


import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.DataInteraction;
import com.google.android.apps.common.testing.ui.espresso.Root;
import com.google.android.apps.common.testing.ui.espresso.ViewAssertion;
import com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions;
import com.google.android.apps.common.testing.ui.espresso.contrib.DrawerMatchers;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.MindbodyViewMatchers;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import org.hamcrest.Matcher;

import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.common.testing.ui.espresso.action.EspressoKey;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.squareup.spoon.Spoon;

import junit.framework.AssertionFailedError;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.closeSoftKeyboard;
import static com.google.android.apps.common.testing.ui.espresso.matcher.RootMatchers.withDecorView;
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
    protected Matcher<Root> rootMatcher;

    /**
     * Changes the root in which to find the view.
     *
     * Example use:
     *  public Clickable<MyActivity> button = new Clickable<MyActivity>(MyActivity.class, R.id.button)
     *      .setRootMatcher(getNondefaultRootMatcher(getActivity());
     *
     * @param rootActivity The default root activity (if using MindbodyActivityInstrumentTestCase, pass the result of getActivity())
     * @return The model for the view in another root
     */
    public MindbodyView<T> setRootMatcher(Activity rootActivity) {
        this.rootMatcher = withDecorView(not(is(rootActivity.getWindow().getDecorView())));
        return this;
    }

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
        if(adapter != null)
            adapter.perform(viewAction);
        else if (rootMatcher != null)
            onView(getSelector()).inRoot(rootMatcher).perform(viewAction);
        else
            onView(getSelector()).perform(viewAction);
        return returnGeneric();
    }

    /**
     * Performs an Espresso ViewAction on an element
     *
     * @param type The type of the PageObject to return
     * @param viewAction The ViewAction to execute
     * @return The model returned by interacting with the element
     */
    protected <E extends PageObject> E  performAction(Class<E> type, ViewAction viewAction) {
        if(adapter != null)
            adapter.perform(viewAction);
        else if (rootMatcher != null)
            onView(getSelector()).inRoot(rootMatcher).perform(viewAction);
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
     * @return  The model returned by interacting with the element
     */
    protected T checkAssertion(ViewAssertion viewAssertion) {
        if(adapter != null)
            adapter.check(viewAssertion);
        else if (rootMatcher != null)
            onView(getSelector()).inRoot(rootMatcher).check(viewAssertion);
        else
            onView(getSelector()).check(viewAssertion);
        return returnGeneric();
    }

    /**
     * Checks if an element matches a certain value using an Espresso ViewMatcher
     *
     * @param type The type of the PageObject to return
     * @param viewMatcher The ViewMatcher used to check the element
     * @return The model returned by interacting with the element
     */
    protected <E extends PageObject> E checkMatches(Class<E> type, Matcher<? super View> viewMatcher) {
        if(adapter != null)
            adapter.check(ViewAssertions.matches(viewMatcher));
        else if (rootMatcher != null)
            onView(getSelector()).inRoot(rootMatcher).check(ViewAssertions.matches(viewMatcher));
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

    //public T closeDrawer(int id) { return performAction(DrawerActions.closeDrawer(id)); }

    public T scrollTo() {
        return performAction(ViewActions.scrollTo());
    }

    public <E extends PageObject> E scrollTo(Class<E> type) {
        return performAction(type, ViewActions.click());
    }

    public T click() {
        return performAction(ViewActions.click());
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
     * End ViewActions
     */

    /*============================================================================================*/

    /**
     * ViewMatchers
     */


    public T isOpen() { return checkMatches(DrawerMatchers.isOpen()); }

    public T isClosed() { return checkMatches(DrawerMatchers.isClosed()); }

    public T isDisplayed() {
        return checkMatches(ViewMatchers.isDisplayed());
    }

    public <E extends PageObject> E isDisplayed(Class<E> type) {
        return checkMatches(type, ViewMatchers.isDisplayed());
    }

    public boolean isDisplayedBoolean() {

        try{
            checkMatches(ViewMatchers.isDisplayed());
            return true;
        }catch(Exception e){
            return false;
        }catch(AssertionFailedError e){
            if (e.getMessage().contains("Expected: is displayed on the screen to the user")) {
                return false;
            }
            throw e;
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

    public T withNotText(int stringId) { return checkMatches(not(ViewMatchers.withText(stringId))); }

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
     * End ViewMatchers
     */

    /*============================================================================================*/

    /**
     * ViewAssertions
     */

    public T doesNotExist() {
        return checkAssertion(ViewAssertions.doesNotExist());
    }

    /**
     * End View Assertions
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

    public T waitForElement(){

        int pauseTime = 0;

        pause();

        while(pauseTime < 10 && !this.isDisplayedBoolean()) {
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
