package com.mindbodyonline.ironhidetestapp.tests;

import android.content.Intent;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.DisplayActivity;
import com.mindbodyonline.ironhidetestapp.models.DisplayModel;

import org.junit.Test;

/**
 * Demonstrates the usage of
 * {@link android.test.ActivityInstrumentationTestCase2#setActivityIntent(android.content.Intent)}
 */
@LargeTest
public class InjectActivityTest extends BaseInstrumentTestCase<DisplayActivity> {
    private static final String INJECTED_TEXT = "this is my cool injection text string";

    public InjectActivityTest() {
        super(DisplayActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        Intent toStart = new Intent(Intent.ACTION_MAIN);

        toStart.putExtra("com.mindbodyonline.ironhidetestapp.DATA" /* aka. SendActivity.EXTRA_DATA*/, INJECTED_TEXT);
        setActivityIntent(toStart);

        // call this after so that we can start the activity with the intent we want
        super.setUp();
    }

    @Test
    public void testInjectedText() {
        new DisplayModel().displayText.withText(INJECTED_TEXT);
    }
}
