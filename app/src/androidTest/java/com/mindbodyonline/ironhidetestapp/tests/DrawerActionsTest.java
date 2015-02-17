package com.mindbodyonline.ironhidetestapp.tests;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.closeDrawer;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.contrib.DrawerActions;
import com.mindbodyonline.ironhidetestapp.DrawerActivity;
import com.mindbodyonline.ironhidetestapp.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates use of {@link DrawerActions}.
 */
@LargeTest
public class DrawerActionsTest  extends ActivityInstrumentationTestCase2<DrawerActivity> {

  public DrawerActionsTest() {
    super(DrawerActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    getActivity();
  }

  public void testOpenAndCloseDrawer() {
    // Drawer should not be open to start.
    onView(withId(R.id.drawer_layout)).check(matches(isClosed()));

    openDrawer(R.id.drawer_layout);

    // The drawer should now be open.
    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));

    closeDrawer(R.id.drawer_layout);

    // Drawer should be closed again.
    onView(withId(R.id.drawer_layout)).check(matches(isClosed()));
  }

  @SuppressWarnings("unchecked")
  public void testDrawerOpenAndClick() {
    openDrawer(R.id.drawer_layout);

    onView(withId(R.id.drawer_layout)).check(matches(isOpen()));

    // Click an item in the drawer. We use onData because the drawer is backed by a ListView, and
    // the item may not necessarily be visible in the view hierarchy.
    int rowIndex = 2;
    String rowContents = DrawerActivity.DRAWER_CONTENTS[rowIndex];
    onData(allOf(is(instanceOf(String.class)), is(rowContents))).perform(click());

    // clicking the item should close the drawer.
    onView(withId(R.id.drawer_layout)).check(matches(isClosed()));

    // The text view will now display "You picked: Pickle"
    onView(withId(R.id.drawer_text_view)).check(matches(withText("You picked: " + rowContents)));
  }
}
