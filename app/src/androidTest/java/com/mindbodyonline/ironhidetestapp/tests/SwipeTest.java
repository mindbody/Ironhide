package com.mindbodyonline.ironhidetestapp.tests;

import android.support.test.espresso.action.ViewActions;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.ViewPagerActivity;
import com.mindbodyonline.ironhidetestapp.models.ViewPagerModel;

import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection.LEFT;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction.SwipeDirection.RIGHT;

/**
 * Demonstrates use of {@link ViewActions#swipeLeft()} and {@link ViewActions#swipeRight()}.
 */
@LargeTest
public class SwipeTest extends BaseInstrumentTestCase<ViewPagerActivity> {

    private ViewPagerModel PagerPage = new ViewPagerModel();
    
    public SwipeTest() {
        super(ViewPagerActivity.class);
    }

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
