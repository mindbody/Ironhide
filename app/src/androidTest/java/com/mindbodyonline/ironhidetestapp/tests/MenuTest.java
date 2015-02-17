package com.mindbodyonline.ironhidetestapp.tests;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressMenuKey;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.mindbodyonline.ironhidetestapp.MenuActivity;
import com.mindbodyonline.ironhidetestapp.R;

import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Ensures view root ordering works properly.
 */
@LargeTest
public class MenuTest extends ActivityInstrumentationTestCase2<MenuActivity> {
  @SuppressWarnings("deprecation")
  public MenuTest() {
    // This constructor was deprecated - but we want to support lower API levels.
    super("com.mindbodyonline.ironhidetestapp", MenuActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    getActivity();
  }

  public void testPopupMenu() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
      // popup menus are post honeycomb.
      return;
    }
    onView(withText(R.string.popup_item_1_text)).check(doesNotExist());
    onView(withId(R.id.popup_button)).perform(click());
    onView(withText(R.string.popup_item_1_text)).check(matches(isDisplayed())).perform(click());

    onView(withId(R.id.text_menu_result)).check(matches(withText(R.string.popup_item_1_text)));
  }

  public void testContextMenu() {
    onView(withText(R.string.context_item_2_text)).check(doesNotExist());
    onView(withId(R.id.text_context_menu)).perform(longClick());
    onView(withText(R.string.context_item_2_text)).check(matches(isDisplayed())).perform(click());

    onView(withId(R.id.text_menu_result)).check(matches(withText(R.string.context_item_2_text)));
  }

  public void testOptionMenu() {
    onView(withText(R.string.options_item_3_text)).check(doesNotExist());
    onView(isRoot()).perform(pressMenuKey());
    onView(withText(R.string.options_item_3_text)).check(matches(isDisplayed())).perform(click());

    onView(withId(R.id.text_menu_result)).check(matches(withText(R.string.options_item_3_text)));
  }
}
