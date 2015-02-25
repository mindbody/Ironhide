package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.content.res.Resources;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * A collection of ViewMatchers specifically for {@link TextView}s that allow the comparison of
 *  strings inside {@link TextView}s to resource strings.
 */
public class TextViewMatchers {

    /**
     * Checks to see if a TextView's text is empty or null.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> isEmptyOrNullString() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                return textView != null
                        && (textView.getText() == null || textView.getText().length() == 0);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text empty or null string");
            }
        };
    }

    /**
     * Checks to see if a TextView's text is empty.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> isEmptyString() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                return textView != null
                        && (textView.getText() == null || textView.getText().length() == 0);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text empty string");
            }
        };
    }

    // Hint matchers


    /**
     * Checks to see if a TextView's hint text is empty or null.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> isEmptyOrNullHint() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                return textView != null
                        && (textView.getHint() == null || textView.getText().length() == 0);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text empty or null string");
            }
        };
    }

    /**
     * Checks to see if a TextView's hint text is empty.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> isEmptyHint() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            protected boolean matchesSafely(TextView textView) {
                return textView != null
                        && (textView.getHint() == null || textView.getText().length() == 0);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text empty string");
            }
        };
    }
}
