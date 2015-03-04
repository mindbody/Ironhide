package com.mindbodyonline.ironhidetestapp.tests;

import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;

import com.mindbodyonline.ironhide.Fixture.MindbodyInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * A sample of how to set a non-default {@link FailureHandler}.
 */
@LargeTest
public class CustomFailureHandlerTest extends MindbodyInstrumentTestCase<MainActivity> {

    private static final String TAG = "CustomFailureHandlerTes";

    public CustomFailureHandlerTest() {
        super(MainActivity.class);
    }

    @Test
    public void testWithCustomFailureHandler() {
        try {
            onView(withText("does not exist")).perform(click());
        } catch (MySpecialException expected) {
            Log.e(TAG, "Special exception is special and expected: ", expected);
        }
    }

    /** 
     * Replaces NoMatchingViewException with MySpecialException (to be caught in the test)
     */
    @Override
    public void onFailure(FailureHandler delegate, Throwable error, Matcher<View> viewMatcher) {
        try {
            delegate.handle(error, viewMatcher);
        } catch (NoMatchingViewException e) {
            throw new MySpecialException(e);
        }
    }

    private static class MySpecialException extends RuntimeException {
        MySpecialException(Throwable cause) {
            super(cause);
        }
    }
}
