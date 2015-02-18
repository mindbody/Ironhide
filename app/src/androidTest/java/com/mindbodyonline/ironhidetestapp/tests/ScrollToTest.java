package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.ScrollActivity;
import com.mindbodyonline.ironhidetestapp.models.ScrollModel;

/**
 * Demonstrates the usage of
 * {@link android.support.test.espresso.action.ViewActions#scrollTo()}.
 */
@LargeTest
public class ScrollToTest extends BaseInstrumentTestCase<ScrollActivity> {

    private ScrollModel ScrollPage = new ScrollModel();
    
    public ScrollToTest() {
        super(ScrollActivity.class);
    }

    public void testScrollToInScrollView() {
        ScrollPage
                .BottomLeft.scrollTo()
                .BottomLeft.click();
    }
}
