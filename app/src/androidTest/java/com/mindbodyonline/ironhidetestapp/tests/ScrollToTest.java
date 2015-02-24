package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.ScrollActivity;
import com.mindbodyonline.ironhidetestapp.models.ScrollModel;

import org.junit.Test;

/**
 * Demonstrates the usage of
 * {@link android.support.test.espresso.action.ViewActions#scrollTo()}.
 */
@LargeTest
public class ScrollToTest extends TestFixture<ScrollActivity> {

    public ScrollToTest() {
        super(ScrollActivity.class);
    }

    @Test
    public void testScrollToInScrollView() {
        ScrollPage
                .BottomLeft.scrollTo()
                .BottomLeft.click();
    }
}
