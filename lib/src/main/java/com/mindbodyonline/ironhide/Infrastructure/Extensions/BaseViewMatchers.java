package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.not;

/**
 * Custom ViewMatchers used for BaseView elements
 * These are already wrapped inside of elements, so there is little need to use them directly
 */
public class BaseViewMatchers {

    /**
     * Checks an ImageView to see if the displayed image corresponds to the image pointed to by a Drawable resource
     * NOTE: see open issue #94 for Espresso (https://code.google.com/p/android-test-kit/issues/detail?id=94)
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
     * NOTE: Espresso 2.0 provides ViewMatchers.isAssignableFrom(Class<? extends View> clazz),
     *  which may or may not be a substitute for this method.
     *
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
}
