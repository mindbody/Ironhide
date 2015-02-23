package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.net.Uri;
import android.os.SystemClock;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
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

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.RootMatchers.DEFAULT;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Base Class for all page elements represented in the models for application testing.
 * Cannot be instantiated, only serves as a base for other elements.
 * Includes all Espresso view matchers, and all view actions that are common to every type of element (e.g.: click(), scrollTo(), etc.).
 *
 * @param <T> The model the current element will return when interacted with
 */
public class BaseView<T extends PageObject> {

    protected final Class<T> type;
    protected final Matcher<View> selector;
    protected ViewInteraction viewInteraction;

    /**
     * Instantiates a {@link android.support.test.espresso.ViewInteraction} and retains type and selector for later access.
     * @param type the class of the generic type
     * @param selector  the {@link org.hamcrest.Matcher} to select the {@link android.view.View}
     */
    protected BaseView(Class<T> type, Matcher<View> selector) {
        this.type = type;
        this.selector = selector;
        this.viewInteraction = onView(selector);
    }

    /**
     * A generically typed BaseView with selector: {@link android.support.test.espresso.matcher.ViewMatchers#withId(int)}
     * @param resourceId    the resource id of the view to interact with
     */
    protected BaseView(Class<T> type, int resourceId) {
        this(type, ViewMatchers.withId(resourceId));
    }

    /**
     * A generically typed BaseView with selector: {@link android.support.test.espresso.matcher.ViewMatchers#withText(int)}
     * @param IGNORED   an ignored integer to distinguish this constructor from {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#BaseView(int)}
     * @param stringResourceId    the resource id of the string for the view to interact with
     */
    protected BaseView(Class<T> type, int IGNORED, int stringResourceId) {
        this(type, ViewMatchers.withText(stringResourceId));
    }

    /**
     * A generically typed BaseView with selector: {@link android.support.test.espresso.matcher.ViewMatchers#withText(String)}
     * @param displayText   the text inside the view to interact with
     */
    protected BaseView(Class<T> type, String displayText) {
        this(type, ViewMatchers.withText(displayText));
    }


    /**
     * Changes the destination class by returning an object of the given type
     */
    protected <E extends PageObject> BaseView<E> goesTo(Class<E> type) {
        return new BaseView<>(type, selector);
    }

    /**
     * Gets the appropriate selector for the current view element
     * AllOf() ViewMatchers have priority over any other selector, then the order goes as such: String Resource ID, String (Text), XML Android ID
     *
     * @return The highest order selector available for an element
     */
    protected Matcher<View> getSelector() {
        return selector;
    }

    /**
     * Performs an Espresso ViewAction on an element
     *
     * @param viewAction The ViewAction to execute
     * @return The model reached by interacting with this element.
     */
    protected T performAction(ViewAction viewAction) {
        viewInteraction.perform(viewAction);
        return returnGeneric();
    }

    /**
     * Checks if an element matches a certain value using an Espresso ViewMatcher
     *
     * @param viewMatcher The ViewMatcher used to check the element
     * @return The model reached by interacting with this element
     */
    protected T checkMatches(Matcher<? super View> viewMatcher) {
        return checkAssertion(ViewAssertions.matches(viewMatcher));
    }

    /**
     * Checks that the root of the current view matches the given rootMatcher
     *
     * @param rootMatcher The RootMatcher used to check the root of the element
     * @return The model reached by interacting with this element
     */
    protected T checkRootMatches(Matcher<Root> rootMatcher) {
        onView(getSelector())
                .inRoot(rootMatcher)
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        return returnGeneric();
    }

    /**
     * Checks if an element matches a certain value using an Espresso ViewAssertion
     *
     * @param viewAssertion The ViewAssertion used to check the element
     * @return The model reached by interacting with this element
     */
    protected T checkAssertion(ViewAssertion viewAssertion) {
        viewInteraction.check(viewAssertion);
        return returnGeneric();
    }

    /**
     * Used whenever interacting with an element to return the correct following model
     *
     * @return The model reached by interacting with this element
     */
    protected T returnGeneric() {
        if (type != null)
            try {
                return type.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        return null;
    }

    /**
     * Root changers
     */

    /**
     * Changes the root for this view to be anything that is not the default root selected by Espresso.
     * @return this
     */
    public BaseView<T> changeRoot() {
        return inRoot(not(is(DEFAULT)));
    }

    /**
     * Changes the root to search for this view to be a dialog
     */
    public BaseView<T> inDialogRoot() {
        return inRoot(RootMatchers.isDialog());
    }

    /**
     * Changes the root to search for this view to be a platform popup
     */
    public BaseView<T> inPlatformPopup() {
        return inRoot(RootMatchers.isPlatformPopup());
    }

    /**
     * Changes the root to search for this view to be touchable
     */
    public BaseView<T> inTouchableRoot() {
        return inRoot(RootMatchers.isTouchable());
    }

    /**
     * Changes the root to search for this view to be a decor view
     */
    public BaseView<T> inDecorView(Matcher<View> decorViewMatcher) {
        return inRoot(RootMatchers.withDecorView(decorViewMatcher));
    }

    /**
     * Changes the root to search for this view to be focusable
     */
    public BaseView<T> inFocusableRoot() {
        return inRoot(RootMatchers.isFocusable());
    }

    /**
     * Changes the root for this view to match the given rootMatcher
     * @param rootMatcher a rootMatcher using Espresso's RootMatchers
     * @return  this
     */
    protected BaseView<T> inRoot(Matcher<Root> rootMatcher) {
        this.viewInteraction = this.viewInteraction.inRoot(rootMatcher);

        return this;
    }

    /**
     * End Root changers
     */

    /*============================================================================================*/

    /**
     * ViewActions
     */

    /**
     * Close the Navigation Drawer.
     * @param drawerLayoutId The android id of the Drawer Layout
     * @return The model reached by interacting with this element.
     */
    public T closeDrawer(int drawerLayoutId) {
        DrawerActions.closeDrawer(drawerLayoutId);

        return returnGeneric();
    }

    /**
     * Open the Navigation drawer.
     * @param drawerLayoutId The android id of the Drawer Layout
     * @return The model reached by interacting with this element.
     */
    public T openDrawer(int drawerLayoutId) {
        DrawerActions.openDrawer(drawerLayoutId);

        return returnGeneric();
    }

    /**
     * Scroll to the element.
     * @return The model reached by interacting with this element.
     */
    public T scrollTo() {
        return performAction(ViewActions.scrollTo());
    }

    /**
     * Click on the element.
     * @return The model reached by interacting with this element.
     */
    public T click() {
        return performAction(ViewActions.click());
    }


    /**
     * Returns an action that performs a single click on the view. If the click takes longer than the 'long press' duration (which is possible) the provided rollback action is invoked on the view and a click is attempted again. This is only necessary if the view being clicked on has some different behaviour for long press versus a normal tap. For example - if a long press on a particular view element opens a popup menu - ViewActions.pressBack() may be an acceptable rollback action.
     * <p>View constraints:</p>
     *
     * <p>must be displayed on screen</p>
     * <p>any constraints of the rollbackAction</p>
     * @return The model reached by interacting with this element.
     */
    public T click(ViewAction rollbackAction) {
        return performAction(ViewActions.click(rollbackAction));
    }



    /**
     * Press the back button.
     * @return     The model reached by interacting with this element.
     */
    public T pressBack() {
        return performAction(ViewActions.pressBack());
    }

    /**
     * Press the Ime action button.
     * @return     The model reached by interacting with this element.
     */
    public T pressImeActionButton() {
        return performAction(ViewActions.pressImeActionButton());
    }

    /**
     * Press the menu button.
     * @return     The model reached by interacting with this element.
     */
    public T pressMenuKey() {
        return performAction(ViewActions.pressMenuKey());
    }

    /**
     * Press the specified key.
     * @param keyCode The integer value of the key to be pressed.
     * @return     The model reached by interacting with this element.
     */
    public T pressKey(int keyCode) {
        return performAction(ViewActions.pressKey(keyCode));
    }

    /**
     * Press the specified key.
     * @param key The EspressoKey object representation of the key to be pressed.
     * @return The model reached by interacting with this element.
     */
    public T pressKey(EspressoKey key) {
        return performAction(ViewActions.pressKey(key));
    }

    /**
     * Double click on the element.
     * @return     The model reached by interacting with this element.
     */
    public T doubleClick() {
        return performAction(ViewActions.doubleClick());
    }

    /**
     * Long click on the element.
     * @return     The model reached by interacting with this element.
     */
    public T longClick() {
        return performAction(ViewActions.longClick());
    }

    /**
     * Close the on-screen keyboard.
     * @return     The model reached by interacting with this element.
     */
    public T closeKeyboard() {
        viewInteraction.perform(closeSoftKeyboard());
        return pause();
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

    /**
     * Checks to see if the Navigation Drawer is open.
     * @return The model reached by interacting with this element.
     */
    public T isOpen() {
        return checkMatches(DrawerMatchers.isOpen());
    }

    /**
     * Checks to see if the Navigation Drawer is closed.
     * @return The model reached by interacting with this element.
     */
    public T isClosed() {
        return checkMatches(DrawerMatchers.isClosed());
    }

    /**
     * Checks to see if the given object is at least partially displayed on the screen.
     * @return The model reached by interacting with this element.
     */
    public T isDisplayed() {
        return checkMatches(ViewMatchers.isDisplayed());
    }

    /**
     * Check to see if the element is not displayed.
     * @return The model reached by interacting with this element.
     */
    public T isNotDisplayed() {
        return checkMatches(not(ViewMatchers.isDisplayed()));
    }

    /**
     * Check to see if all of the element is currently displayed.
     * @return The model reached by interacting with this element.
     */
    public T isCompletelyDisplayed() {
        return checkMatches(ViewMatchers.isCompletelyDisplayed());
    }

    /**
     * Check to see if the element contains the given text.
     * @param stringId Resource id of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T withText(int stringId) {
        return checkMatches(ViewMatchers.withText(stringId));
    }

    /**
     * Check to see if the element contains the given text.
     * @param string String to check for.
     * @return The model reached by interacting with this element.
     */
    public T withText(String string) {
        return checkMatches(ViewMatchers.withText(string));
    }

    /**
     * Check to see if the element contains the given text.
     * @param stringMatcher A hamcrest matcher that represents the string of text to check for.
     * @return The model reached by interacting with this element.
     */
    public T withText(Matcher<String> stringMatcher) {
        return checkMatches(ViewMatchers.withText(stringMatcher));
    }

    /**
     * Check to see if the element does not contain the given text.
     * @param stringId Resource id of the string to check for.
     * @return The model reached by interacting with this element.
     */
    public T withNotText(int stringId) {
        return checkMatches(not(ViewMatchers.withText(stringId)));
    }

    /**
     * Check to see if the element does not contain the given text.
     * @param string String to check for.
     * @return The model reached by interacting with this element.
     */
    public T withNotText(String string) {
        return checkMatches(not(ViewMatchers.withText(string)));
    }

    /**
     * Checks whether the element is an instance of or a subclass of the provided class.
     * @param fromClass Class to check against.
     * @return the model reached by interacting with this element.
     */
    public T isAssignableFrom(Class<? extends View> fromClass) {
        return checkMatches(ViewMatchers.isAssignableFrom(fromClass));
    }

    /**
     * Check to see if the element has the given class name.
     * @param classNameMatcher Hamcrest Matcher that represents the class name.
     * @return The model reached by interacting with this element.
     */
    public T withClassName(Matcher<String> classNameMatcher) {
        return checkMatches(ViewMatchers.withClassName(classNameMatcher));
    }

    /**
     * Check to see if the element is displaying at least areaPercentage of its area on the screen.
     * @param areaPercentage The percentage of the element to check for.
     * @return The model reached by interacting with this element.
     */
    public T isDisplayingAtLeast(int areaPercentage) {
        return checkMatches(ViewMatchers.isDisplayingAtLeast(areaPercentage));
    }

    /**
     * Check to see if the element is enabled.
     * @return The model reached by interacting with this element.
     */
    public T isEnabled() {
        return checkMatches(ViewMatchers.isEnabled());
    }

    /**
     * Check to see if the element is not enabled.
     * @return The model reached by interacting with this element.
     */
    public T isNotEnabled() {
        return checkMatches(not(ViewMatchers.isEnabled()));
    }

    /**
     * Check to see if the element is focusable
     * @return The model reached by interacting with this element.
     */
    public T isFocusable() {
        return checkMatches(ViewMatchers.isFocusable());
    }

    /**
     * Checks to see if a WebView is evaluating javascript
     * @return  The model reached by interacting with this element.
     */
    public T isJavascriptEnabled() {
        return checkMatches(ViewMatchers.isJavascriptEnabled());
    }

    /**
     * Check to see if the element has the current focus.
     * @return The model reached by interacting with this element.
     */
    public T hasFocus() {
        return checkMatches(ViewMatchers.hasFocus());
    }

    /**
     * Check to see if the element has a sibling (another BaseView object with the same parent) matching the given BaseView.
     * @param sibling The BaseView to check as a sibling.
     * @return The model reached by interacting with this element.
     */
    public T hasSibling(BaseView<T> sibling) {
        return checkMatches(ViewMatchers.hasSibling(sibling.getSelector()));
    }

    /**
     * Check to see if the element has the content description matching the given string.
     * @param text The string to check against the content description.
     * @return The model reached by interacting with this element.
     */
    public T withContentDescription(String text) {
        return checkMatches(ViewMatchers.withContentDescription(text));
    }

    /**
     * Check to see if the element has the content description matching the given Matcher
     * @param charSequenceMatcher Hamcrest Matcher representing the text to check the content description against.
     * @return The model reached by interacting with this element.
     */
    public T withContentDescription(Matcher<? extends CharSequence> charSequenceMatcher) {
        return checkMatches(ViewMatchers.withContentDescription(charSequenceMatcher));
    }

    /**
     * Check to see if the element has the given resource id.
     * @param id Resource id to check.
     * @return The model reached by interacting with this element.
     */
    public T withId(int id) {
        return checkMatches(ViewMatchers.withId(id));
    }

    /**
     * Check to see if the element has the given resource id, represented by a Hamcrest Matcher.
     * @param integerMatcher Hamcrest Matcher representing the resource id to check for.
     * @return The model reached by interacting with this element.
     */
    public T withId(Matcher<Integer> integerMatcher) {
        return checkMatches(ViewMatchers.withId(integerMatcher));
    }

    /**
     * Check to see if the element has the given tag key.
     * @param key Tag Key to check against.
     * @return The model reached by interacting with this element.
     */
    public T withTagKey(int key) {
        return checkMatches(ViewMatchers.withTagKey(key));
    }

    /**
     * Check to see if the element has the given tag key.
     * @param key Tag Key to check against.
     * @return The model reached by interacting with this element.
     */
    public T withTagKey(int key, Matcher<Object> objectMatcher) {
        return checkMatches(ViewMatchers.withTagKey(key, objectMatcher));
    }

    /**
     * Check to see if the element has the given tag value.
     * @param tagValueMatcher Hamcrest Matcher representing the tag value.
     * @return The model reached by interacting with this element.
     */
    public T withTagValue(Matcher<Object> tagValueMatcher) {
        return checkMatches(ViewMatchers.withTagValue(tagValueMatcher));
    }

    /**
     * Check to see if the element is a CompoundButton and is not in the checked state.
     * @return The model reached by interacting with this element.
     */
    public T isNotChecked() {
        return checkMatches(ViewMatchers.isNotChecked());
    }

    /**
     * Check to see if the element is a CompoundButton and is in the checked state.
     * @return The model reached by interacting with this element.
     */
    public T isChecked() {
        return checkMatches(ViewMatchers.isChecked());
    }

    /**
     * Check to see if the element has a content description.
     * @return The model reached by interacting with this element.
     */
    public T hasContentDescription() {
        return checkMatches(ViewMatchers.hasContentDescription());
    }

    /**
     * Check to see if the element has a descendant matching the given descendant.
     * @param descendant The descendant(A BaseView) to match on.
     * @return The model reached by interacting with this element.
     */
    public T hasDescendant(BaseView<T> descendant) {
        return checkMatches(ViewMatchers.hasDescendant(descendant.getSelector()));
    }

    /**
     * Checks to see if the element is not clickable
     * @return The model reached by interacting with this element.
     */
    public T isNotClickable() {
        return checkMatches(not(ViewMatchers.isClickable()));
    }

    /**
     * Checks to see if the element is clickable.
     * @return The model reached by interacting with this element.
     */
    public T isClickable() {
        return checkMatches(ViewMatchers.isClickable());
    }

    /**
     * Checks to see if the ancestor of the element matches the given ancestor.
     * @param parent The ancestor to check against.
     * @return The model reached by interacting with this element.
     */
    public T isDescendantOfA(BaseView<T> parent) {
        return checkMatches(ViewMatchers.isDescendantOfA(parent.getSelector()));
    }

    /**
     * Checks to see if the view has the given "effective" visibility.
     * Effective visibility takes into account not only the view's visibility value, but also that of its ancestors. In case of View.VISIBLE, this means that the view and all of its ancestors have visibility=VISIBLE. In case of GONE and INVISIBLE, it's the opposite - any GONE or INVISIBLE parent will make all of its children have their effective visibility.
     * Note: Contrary to what the name may imply, view visibility does not directly translate into whether the view is displayed on screen (use isDisplayed() for that). For example, the view and all of its ancestors can have visibility=VISIBLE, but the view may need to be scrolled to in order to be actually visible to the user. Unless you're specifically targeting the visibility value with your test, use isDisplayed.
     * @param visibility The visibility type to match against.
     * @return The model reached by interacting with this element.
     */
    public T withEffectiveVisibility(ViewMatchers.Visibility visibility) {
        return checkMatches(ViewMatchers.withEffectiveVisibility(visibility));
    }

    /**
     * Checks to see if the elements parent matches the given parent.
     * @param parent The parent to check against.
     * @return The model reached by interacting with this element.
     */
    public T withParent(BaseView<T> parent) {
        return checkMatches(ViewMatchers.withParent(parent.getSelector()));
    }

    /**
     * Checks to see if the element has a child matching the given child.
     * @param child The child to check against.
     * @return The model reached by interacting with this element.
     */
    public T withChild(BaseView<T> child) {
        return checkMatches(ViewMatchers.withChild(child.getSelector()));
    }

    /**
     * Checks to see if the element is the root view.
     * @return The model reached by interacting with this element.
     */
    public T isRoot() {
        return checkMatches(ViewMatchers.isRoot());
    }

    /**
     * Checks to see if the element is selected
     * @return  The model reached by interacting with this element.
     */
    public T isSelected() {
        return checkMatches(ViewMatchers.isSelected());
    }

    /**
     * Checks to see if the element supports input methods.
     * @return The model reached by interacting with this element.
     */
    public T supportsInputMethods() {
        return checkMatches(ViewMatchers.supportsInputMethods());
    }

    /**
     * Checks to see if the element supports input methods (e.g. EditText) and have the specified IME action set in its EditorInfo.
     * @param imeAction The IME action to match
     * @return The model reached by interacting with this element.
     */
    public T hasImeAction(int imeAction) {
        return checkMatches(ViewMatchers.hasImeAction(imeAction));
    }

    /**
     * Checks to see if the element supports input methods (e.g. EditText) and have the specified IME action set in its EditorInfo.
     * @param imeActionMatcher Hamcrest Matcher that represents the IME action to match.
     * @return The model reached by interacting with this element.
     */
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

    /**
     * Checks if the element does not exist
     * @return The model reached by interacting with this element.
     */
    public T doesNotExist() {
        return checkAssertion(ViewAssertions.doesNotExist());
    }

    /**
     * Checks to see if the element matches the given Matcher
     * @param viewMatcher Hamcrest Matcher to match the element against.
     * @return The model reached by interacting with this element.
     */
    public T matches(Matcher<View> viewMatcher) {
        return checkAssertion(ViewAssertions.matches(viewMatcher));
    }

    /**
     * Checks that the descendant views of the element selected by the selector match the specified matcher.
     * @param selector Selector to choose which descendants to check.
     * @param matcher Matcher to match the selected descendants against.
     * @return The model reached by interacting with this element.
     */
    public T selectedDescendantsMatch(Matcher<View> selector, Matcher<View> matcher) {
        return checkAssertion(ViewAssertions.selectedDescendantsMatch(selector, matcher));
    }


    /**
     * End View Assertions
     */

    /*============================================================================================*/

    /**
     * Position-based Assertions
     */

    /**
     * Check to see if the element is above the given view.
     * @param matcher Matcher representing a view to check if the element is above.
     * @return The model reached by interacting with this element.
     */
    public T isAbove(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isAbove(matcher));
    }

    /**
     * Check to see if the element is below the given view.
     * @param matcher Matcher representing a view to check if the element is below.
     * @return The model reached by interacting with this element.
     */
    public T isBelow(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isBelow(matcher));
    }

    /**
     * Check to see if the element is bottom aligned with the given view.
     * @param matcher Matcher representing a view to check if the element is bottom aligned with.
     * @return The model reached by interacting with this element.
     */
    public T isBottomAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isBottomAlignedWith(matcher));
    }

    /**
     * Check to see if the element is right aligned with the given view.
     * @param matcher Matcher representing a view to check if the element is right aligned with.
     * @return The model reached by interacting with this element.
     */
    public T isLeftAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isLeftAlignedWith(matcher));
    }

    /**
     * Check to see if the element is left of the given view.
     * @param matcher Matcher representing a view to check if the element is left of.
     * @return The model reached by interacting with this element.
     */
    public T isLeftOf(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isLeftOf(matcher));
    }

    /**
     * Check to see if the element is right aligned with the given view.
     * @param matcher Matcher representing a view to check if the element is right aligned with.
     * @return The model reached by interacting with this element.
     */
    public T isRightAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isRightAlignedWith(matcher));
    }

    /**
     * Check to see if the element is right of the given view.
     * @param matcher Matcher representing a view to check if the element is right of.
     * @return The model reached by interacting with this element.
     */
    public T isRightOf(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isRightOf(matcher));
    }

    /**
     * Check to see if the element is top aligned with the given view.
     * @param matcher Matcher representing a view to check if the element is top aligned with.
     * @return The model reached by interacting with this element.
     */
    public T isTopAlignedWith(Matcher<View> matcher) {
        return checkAssertion(PositionAssertions.isTopAlignedWith(matcher));
    }

    /**
     * End Position-based Assertions
     */

    /*============================================================================================*/
    /**
     * Layout-based Assertions
     */

    /**
     * Checks whether the entire view hierarchy does not contain ellipsized or cut off text views.
     * @return The model reached by interacting with this element.
     */
    public T noEllipsizedText() {
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
     * @param matcher Matcher representing a view to check for no overlaps.
     * @return The model reached by interacting with this element.
     */
    public T noOverLaps(Matcher<View> matcher){
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

    /**
     * Pause the test run for DEFAULT_PAUSE_TIME seconds
     * @see com.mindbodyonline.ironhide.PageObjects.PageObject
     * @return The model reached by interacting with this element.
     */
    public T pause() {
        return pause(PageObject.DEFAULT_PAUSE_TIME);
    }

    /**
     * Pause the test run for a given amount of time(in milliseconds).
     * @param timeInMillis Time, in milliseconds, to pause for.
     * @return The model reached by interacting with this element.
     */
    public T pause(int timeInMillis) {
        SystemClock.sleep(timeInMillis);
        return returnGeneric();
    }
}
