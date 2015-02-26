package com.mindbodyonline.ironhidetestapp.tests;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import com.mindbodyonline.ironhidetestapp.LongListActivity;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static com.mindbodyonline.ironhidetestapp.LongListMatchers.withItemContent;
import static com.mindbodyonline.ironhidetestapp.LongListMatchers.withItemSize;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;

/**
 * UnitTests for LongListMatchers matcher factory.
 */
public final class LongListMatchersTest extends ActivityUnitTestCase<LongListActivity> {

  public LongListMatchersTest() {
    super(LongListActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    startActivity(new Intent(getInstrumentation().getTargetContext(), LongListActivity.class),
        null, null);
  }

  public void testWithContent() {
    assertThat(getActivity().makeItem(54), withItemContent("item: 54"));
    assertThat(getActivity().makeItem(54), withItemContent(endsWith("54")));
    assertFalse(withItemContent("hello world").matches(getActivity().makeItem(54)));
  }

  @SuppressWarnings("unchecked")
  public void testWithItemSize() {
    assertThat(getActivity().makeItem(54), withItemSize(8));
    assertThat(getActivity().makeItem(54), withItemSize(anyOf(equalTo(8), equalTo(7))));
    assertFalse(withItemSize(7).matches(getActivity().makeItem(54)));
  }
}
