package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * Created by kyle.lozier on 1/14/2015.
 */
public class TextViewMatchers {

    /**
     * Checks to see if a TextView's text contains a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> containsString(final int resourceId) {
        return new StringIdViewMatcher(resourceId) {
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
        return new StringIdViewMatcher(resourceId) {
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
        return new StringIdViewMatcher(resourceId) {
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
        return new StringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.equalToIgnoringWhiteSpace(endPoint);
            }
        };
    }

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

    /**
     * Checks to see if a TextView's text starts with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> startsWith(final int resourceId) {
        return new StringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.startsWith(endPoint);
            }
        };
    }

    // Hint matchers

    /**
     * Checks to see if a TextView's hint text contains a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> containsHintString(final int resourceId) {
        return new HintStringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.containsString(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's hint text ends with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hintEndsWith(final int resourceId) {
        return new HintStringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.endsWith(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's hint text is equal to (ignoring case) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hintEqualToIgnoringCase(final int resourceId) {
        return new HintStringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.equalToIgnoringCase(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's hint text is equal to (ignoring white space around words) a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hintEqualToIgnoringWhiteSpace(final int resourceId) {
        return new HintStringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.equalToIgnoringWhiteSpace(endPoint);
            }
        };
    }

    /**
     * Checks to see if a TextView's hint text is empty or null.
     * NOTE: see issue 72 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=72)
     * @return  A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hintIsEmptyOrNullString() {
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
    public static Matcher<View> hintIsEmptyString() {
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

    /**
     * Checks to see if a TextView's hint text starts with a certain string given the string's resource id.
     *
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hintStartsWith(final int resourceId) {
        return new HintStringIdViewMatcher(resourceId) {
            @Override
            protected Matcher<String> getStringMatcher(String endPoint) {
                return Matchers.startsWith(endPoint);
            }
        };
    }

    /**
     * Matcher classes
     */

    /**
     * A generic matcher to test the hint text of {@code TextView}s
     */
    public static abstract class HintStringIdViewMatcher extends StringIdViewMatcher {
        public HintStringIdViewMatcher(int resourceId) {
            super(resourceId);
        }

        @Override
        protected boolean matchesSafely(TextView textView) {
            if (null == stringMatcher) {
                expectedText = textView.getResources().getString(resourceId);
                resourceName = textView.getResources().getResourceEntryName(resourceId);
                stringMatcher = getStringMatcher(expectedText);
            }

            return textView != null && stringMatcher != null && stringMatcher.matches(textView.getHint().toString());
        }
    }

    /**
     * A generic matcher class to test the text of {@code TextView}s.
     * (Adapted from Espresso.ViewMatchers.withText)
     */
    public static abstract class StringIdViewMatcher extends BoundedMatcher<View, TextView> {
        protected Matcher<String> stringMatcher = null;
        protected String expectedText = null;
        protected String resourceName = null;
        protected int resourceId;

        public StringIdViewMatcher(int resourceId) {
            super(TextView.class);
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
        protected boolean matchesSafely(TextView textView) {
            if (null == stringMatcher) {
                expectedText = textView.getResources().getString(resourceId);
                resourceName = textView.getResources().getResourceEntryName(resourceId);
                stringMatcher = getStringMatcher(expectedText);
            }

            return textView != null && stringMatcher != null && stringMatcher.matches(textView.getText().toString());
        }

        protected abstract Matcher<String> getStringMatcher(String endPoint);
    }
}
