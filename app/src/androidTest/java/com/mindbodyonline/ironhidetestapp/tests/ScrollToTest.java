package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.ScrollActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;

import org.junit.Test;

/**
 * Demonstrates the usage of
 * {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#scrollTo()}
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
