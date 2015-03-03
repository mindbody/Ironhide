package com.mindbodyonline.ironhidetestapp.tests;

import android.support.test.espresso.contrib.CountingIdlingResource;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.SyncActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;
import com.mindbodyonline.ironhidetestapp.models.SyncModel;

import org.junit.Test;

/**
 * Example for {@link CountingIdlingResource}. Demonstrates how to wait on a delayed response from
 * request before continuing with a test.
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
                .statusText.registerAsIdle(mActivity)
                .statusText.withText(R.string.hello_world);
    }
}
