package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.content.res.Resources;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * A collection of ViewMatchers specifically for {@link Spinner}s that allow the comparison of
 *  strings inside {@link Spinner}s' selected items to resource strings.
 */
public class SpinnerTextMatchers {

    /**
     * Checks to see if a TextView's text contains a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> containsString(final int resourceId) {
        return new StringIdSpinnerMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.containsString(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's text ends with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> endsWith(final int resourceId) {
        return new StringIdSpinnerMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.endsWith(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring case) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> equalToIgnoringCase(final int resourceId) {
        return new StringIdSpinnerMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.equalToIgnoringCase(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's text is equal to (ignoring white space around words) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> equalToIgnoringWhiteSpace(final int resourceId) {
        return new StringIdSpinnerMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.equalToIgnoringWhiteSpace(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's text starts with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> startsWith(final int resourceId) {
        return new StringIdSpinnerMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.startsWith(endPoint);
            }
        };
    }

    /**
     * A generic matcher class to test the text of {@link android.widget.TextView}s.
     * (Adapted from {@link android.support.test.espresso.matcher.ViewMatchers#withSpinnerText(int)})
     */
    public static abstract class StringIdSpinnerMatcher extends BoundedMatcher<View, Spinner> {
        protected Matcher<String> stringMatcher = null;
        protected String expectedText = null;
        protected String resourceName = null;
        protected int resourceId;

        public StringIdSpinnerMatcher(int resourceId) {
            super(Spinner.class);
            this.resourceId = resourceId;
        }

        @Override
        public void describeTo(Description description) {
            if (null != stringMatcher) {
                stringMatcher.describeTo(description);
            }

            description.appendText(" (from resource id: ");
            description.appendValue(resourceId);
            if (null != resourceName) {
                description.appendText("[");
                description.appendText(resourceName);
                description.appendText("]");
            }
            if (null != expectedText) {
                description.appendText(" value: \"");
                description.appendText(expectedText);
                description.appendText("\"");
            }
            description.appendText(")");
        }

        @Override
        protected boolean matchesSafely(Spinner spinner) {
            if (null == stringMatcher) {
                try {
                    expectedText = spinner.getResources().getString(resourceId);
                    resourceName = spinner.getResources().getResourceEntryName(resourceId);
                    stringMatcher = getStringMatcher(expectedText);
                } catch (Resources.NotFoundException ignored) {
                    /* view could be from a context unaware of the resource id. */
                }
            }

            if (spinner != null)
                Log.d("Spinner", spinner.getSelectedItem().toString() + " vs. " + expectedText);

            return spinner != null && stringMatcher != null && stringMatcher.matches(spinner.getSelectedItem().toString());
        }

        protected abstract Matcher<String> getStringMatcher(String endPoint);
    }
}
