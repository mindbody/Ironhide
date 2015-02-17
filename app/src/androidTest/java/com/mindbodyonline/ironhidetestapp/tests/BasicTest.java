package com.mindbodyonline.ironhidetestapp.tests;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.SimpleActivity;
import com.mindbodyonline.ironhidetestapp.models.SimpleModel;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;

/**
 * Highlights basic
 * {@link android.support.test.espresso.Espresso#onView(org.hamcrest.Matcher)}
 * functionality.
 */
@LargeTest
public class BasicTest extends BaseInstrumentTestCase<SimpleActivity> {

    private SimpleModel SimplePage = new SimpleModel();
    
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
        String sendString = "Have a cup of Espresso.";
        
        SimplePage
                .SimpleSendText.typeText(sendString)
                .SimpleSendText.closeKeyboard()
                .SimpleSend.click()
                .displayText.withText(sendString)
                .displayText.goesTo(SimpleModel.class).pressBack()
                .SimpleSendText.withText(sendString)
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

