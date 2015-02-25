package com.mindbodyonline.ironhidetestapp.tests;

import android.support.test.espresso.contrib.CountingIdlingResource;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.SyncActivity;
import com.mindbodyonline.ironhidetestapp.models.SyncModel;

import org.junit.Test;

/**
 * Example for {@link CountingIdlingResource}. Demonstrates how to wait on a delayed response from
 * request before continuing with a test.
 */
@LargeTest
public class AdvancedSynchronizationTest extends BaseInstrumentTestCase<SyncActivity> {

    private SyncModel SyncPage = new SyncModel();
    
    public AdvancedSynchronizationTest() {
        super(SyncActivity.class);
    }

    @Test
    public void testCountingIdlingResource() {
        SyncPage
                .RequestButton.click()
                .HiddenTextView.registerAsIdle(mActivity)
                .HiddenTextView.withText(R.string.hello_world);
    }
}
