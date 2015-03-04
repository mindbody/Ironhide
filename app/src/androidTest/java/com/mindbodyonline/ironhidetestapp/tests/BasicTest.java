package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.SimpleActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;
import com.mindbodyonline.ironhidetestapp.models.SimpleModel;

import org.junit.Test;

/**
 * Highlights basic
 * {@link android.support.test.espresso.Espresso#onView(org.hamcrest.Matcher)}
 * functionality.
 */
@LargeTest
public class BasicTest extends TestFixture<SimpleActivity> {
    
    public BasicTest() {
        super(SimpleActivity.class);
    }

    @Test
    public void testSimpleClickAndCheckText() {
        SimplePage
                .SimpleButton.click()
                .SimpleText.withText("Hello Espresso!")
                ;
    }

    @Test
    public void testTypingAndPressBack() {
        SimplePage
                .SimpleSendText.typeText(R.string.hello_world)
                .SimpleSendText.closeKeyboard()
                .SimpleSend.click()
                .displayText.withText(R.string.hello_world)
                .displayText.goesTo(SimpleModel.class).pressBack()
                .SimpleSendText.withText(R.string.hello_world)
                ;
    }

    @Test
    public void testClickOnSpinnerItemAmericano() {
        SimplePage
                .SimpleSpinner.click()
                .SpinnerList.getItemAtPosition(4).click()
                .SimpleSpinner.withText("Americano")
                ;
    }
}

