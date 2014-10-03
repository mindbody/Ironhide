package com.mindbodyonline.ironhide.Infrastructure.Extensions;


import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.apps.common.testing.ui.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.google.android.apps.common.testing.testrunner.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.not;

/**
 * Custom ViewMatchers used for ConnectView elements
 * These are already wrapped inside of elements, so there is little need to use them directly
 */
public class MindbodyViewMatchers {

    /**
     * Checks an ImageView to see if the displayed image correspondes to the image pointed to by a Drawable resource
     *
     * @param drawableId The Drawable Resource ID to compare to the ImageView's content
     * @return A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<Object> withDrawableRes(int drawableId) {
        checkNotNull(drawableId);
        return withDrawable(drawableId);
    }

    private static Matcher<Object> withDrawable(final int drawableId) {
        return new BoundedMatcher<Object, ImageView>(ImageView.class) {
            @Override
            public boolean matchesSafely(ImageView image) {
                return image.getResources().getDrawable(drawableId).getConstantState().equals(image.getDrawable().getConstantState());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with drawable ");
                description.appendText(drawableId + "");
            }
        };
    }

    /**
     * Checks an EditText to see if the hint text correspondes to the text pointed to by a String resource
     *
     * @param stringId The String Resource ID to compare to the EditText's hint text
     * @return A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<Object> withHintText(int stringId) {
        checkNotNull(stringId);
        return withHint(stringId);
    }

    private static Matcher<Object> withHint(final int stringId) {
        return new BoundedMatcher<Object, EditText>(EditText.class) {
            @Override
            public boolean matchesSafely(EditText hint) {
                assert hint.getResources() != null && hint.getHint() != null;
                String expectedText = hint.getResources().getString(stringId);
                return expectedText.equals(hint.getHint().toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with hint ");
                description.appendText(stringId + "");
            }
        };
    }

    public static Matcher<Object> withHintText(String string) {
        checkNotNull(string);
        return withHint(string);
    }

    private static Matcher<Object> withHint(final String string) {
        return new BoundedMatcher<Object, EditText>(EditText.class) {
            @Override
            public boolean matchesSafely(EditText hint) {
                assert hint.getResources() != null && hint.getHint() != null;
                return string.equals(hint.getHint().toString());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with hint ");
                description.appendText(string + "");
            }
        };
    }

    /**
     * Checks to see if a View is selected on the screen
     *
     * @return A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<Object> isSelected() {
        return checkSelected();
    }

    private static Matcher<Object> checkSelected() {
        return new BoundedMatcher<Object, View>(View.class) {
            @Override
            public boolean matchesSafely(View view) {
                return view.isSelected();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is selected ");
            }
        };
    }


    /**
     * Checks to see if a View has a certain index
     *
     * @param index The index to compare to
     * @return A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hasIndex(final int index) {
        return checkHasIndex(index);
    }

    public static Matcher<View> doesNotHaveIndex(final int index) { return not(checkHasIndex(index));}

    private static Matcher<View> checkHasIndex(final int index) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public boolean matchesSafely(View view) {
                assert (view.getParent()) != null;
                return ((ViewGroup) view.getParent()).indexOfChild(view) == index;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has index " + index);
            }
        };
    }

    /**
     * Checks to see if a View is of a certain type
     * @param type The Class Type to compare the type of the View to
     * @return A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> instanceOf(final Class<?> type) {
        return checkInstanceOf(type);
    }

    private static Matcher<View> checkInstanceOf(final Class<?> type) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public boolean matchesSafely(View view) {
                return type.isInstance(view);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("instance of " + type.toString());
            }
        };
    }

    /**
     * Checks to see if a View has a certain number of children
     * @param count The number of children to check the View has
     * @return      A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> hasChildCount(final int count) {
        return checkChildCount(count);
    }

    private static Matcher<View> checkChildCount(final int count) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public boolean matchesSafely(View view) {
                return view instanceof ViewGroup && ((ViewGroup) view).getChildCount() == count;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Has " + count + " children");
            }
        };
    }

    /**
     * Checks to see if a TextView's text contains a certain string given the string's resource id.
     * (Adapted from Espresso.ViewMatchers.withText)
     * @param resourceId    The string's resource id
     * @return              A Matcher to check using Espresso ViewAssertions.matches method
     */
    public static Matcher<View> containsString(final int resourceId) {

        return new BoundedMatcher<View, TextView>(TextView.class) {
            private String resourceName = null;
            private String expectedText = null;

            @Override
            public void describeTo(Description description) {
                description.appendText("contains string from resource id: ");
                description.appendValue(resourceId);
                if (null != resourceName) {
                    description.appendText("[");
                    description.appendText(resourceName);
                    description.appendText("]");
                }
                if (null != expectedText) {
                    description.appendText(" value: ");
                    description.appendText(expectedText);
                }
            }

            @Override
            public boolean matchesSafely(TextView textView) {
                if (null == expectedText) {
                    try {
                        expectedText = textView.getResources().getString(resourceId);
                        resourceName = textView.getResources().getResourceEntryName(resourceId);
                    } catch (Resources.NotFoundException ignored) {
                        /* view could be from a context unaware of the resource id. */
                    }
                }
                return (null != expectedText) && textView.getText().toString().contains(expectedText);
            }
        };
    }

    /**
     * Checks to see if a CompoundButton is checked.
     * @return  A Matcher to check using Espresso ViewAssertions.matchers method
     */
    public static Matcher<View> isChecked() {

        return new BoundedMatcher<View, CompoundButton>(CompoundButton.class) {
            @Override
            protected boolean matchesSafely(CompoundButton compoundButton) {
                return compoundButton.isChecked();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("instance of CompoundButton and is checked");
            }
        };
    }
}
