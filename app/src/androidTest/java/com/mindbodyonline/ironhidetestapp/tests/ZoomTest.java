package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.SwipeAction;
import com.mindbodyonline.ironhide.Infrastructure.Extensions.ZoomAction;
import com.mindbodyonline.ironhidetestapp.TestFixture;
import com.mindbodyonline.ironhidetestapp.ZoomActivity;

import org.junit.Test;

/**
 * Demonstrating using {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Zoomable}
 */
@LargeTest
public class ZoomTest extends TestFixture<ZoomActivity> {
    
    public ZoomTest() {
        super(ZoomActivity.class);
    }
    
    @Test
    public void testSwipeVsZoom() {
        ZoomPage
                .asZoomable.zoom(ZoomAction.ZoomDirection.IN)
                .Name.withText("Zoom")
                .asSwipeable.swipe(SwipeAction.SwipeDirection.RIGHT)
                .Name.withText("Swipe/click")
                ;
    }
}
