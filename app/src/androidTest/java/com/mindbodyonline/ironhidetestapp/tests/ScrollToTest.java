package com.mindbodyonline.ironhidetestapp.tests;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.ScrollActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates the usage of
 * {@link android.support.test.espresso.action.ViewActions#scrollTo()}.
 */
@LargeTest
public class ScrollToTest extends ActivityInstrumentationTestCase2<ScrollActivity> {

  @SuppressWarnings("deprecation")
  public ScrollToTest() {
    // This constructor was deprecated - but we want to support lower API levels.
    super("com.mindbodyonline.ironhidetestapp", ScrollActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    // Espresso will not launch our activity for us, we must launch it via getActivity().
    getActivity();
  }

  // You can pass more than one action to perform. This is useful if you are performing two actions
  // back-to-back on the same view.
  // Note - scrollTo is a no-op if the view is already displayed on the screen.
  public void testScrollToInScrollView() {
    onView(withId(is(R.id.bottom_left)))
      .perform(scrollTo(), click());
  }
}
