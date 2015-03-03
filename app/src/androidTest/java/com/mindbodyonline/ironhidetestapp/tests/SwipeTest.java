package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.TestFixture;
import com.mindbodyonline.ironhidetestapp.ViewPagerActivity;

import org.junit.Test;

import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection.LEFT;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection.RIGHT;

/**
 * Demonstrates use of 
 * {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Swipeable
 * #swipe(com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection, int)}
 */
@LargeTest
// TODO: "java.lang.IllegalAccessError: Class ref in pre-verified class resolved to unexpected implementation" during 'super(ViewPagerActivity.class)'
public class SwipeTest extends TestFixture<ViewPagerActivity> {
    
    public SwipeTest() {
        super(ViewPagerActivity.class);
    }

    @Test
    public void testSwipingThroughViews() {
        PagerPage
                .Position0.isDisplayed()
                .Pager.swipe(LEFT)
                .Position1.isDisplayed()
                .Pager.swipe(LEFT)
                .Position2.isDisplayed()
                .Pager.swipe(LEFT)
                .Position2.isDisplayed()
                ;
    }

    @Test
    public void testSwipingBackAndForward() {
        PagerPage
                .Position0.isDisplayed()
                .Pager.swipe(LEFT)
                .Position1.isDisplayed()
                .Pager.swipe(RIGHT)
                .Position0.isDisplayed()
                .Pager.swipe(RIGHT)
                .Position0.isDisplayed()
                ;
    }

}
