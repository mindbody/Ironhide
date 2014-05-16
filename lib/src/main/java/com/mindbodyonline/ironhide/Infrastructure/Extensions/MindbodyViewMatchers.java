package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.android.apps.common.testing.ui.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.google.android.apps.common.testing.testrunner.util.Checks.checkNotNull;

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
}
