package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.SyncActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;

import org.junit.Test;

/**
 * Example for {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView#registerAsIdle(android.app.Activity)}
 */
@LargeTest
public class AdvancedSynchronizationTest extends TestFixture<SyncActivity> {
    
    public AdvancedSynchronizationTest() {
        super(SyncActivity.class);
    }

    @Test
    public void testCountingIdlingResource() {
        SyncPage
                .helloWorld.click()
                .statusText.registerAsIdle(initActivity)
                .statusText.withText(R.string.hello_world);
    }
}
