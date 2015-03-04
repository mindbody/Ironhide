package com.mindbodyonline.ironhide.Infrastructure.IronhideViews;

import android.view.View;

import com.mindbodyonline.ironhide.PageObjects.PageObject;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers.hasIndex;
import static com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;

/**
 * Simple element that allows to interact with ListViews that have children added dynamically.
 * Enables access to ListItems inside a dynamic ListView that does not use an adapter.
 * This element is rarely used, as these types of ListViews are considered bad practice
 *
 * @param <T> The model the current element will return when interacted with
 */
public class DynamicListAdapter<T extends PageObject> {

    private final Class<T> type;
    private final Matcher<View> childMatcher;

    /**
     * A generically typed DynamicListAdapter with selector:
     *  {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     * @param itemType the class type to use for {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     */
    public DynamicListAdapter(Class<T> type, Class itemType) {
        this(type, instanceOf(itemType));
    }

    /**
     * A generically typed DynamicListAdapter with selector:
     *  {@link org.hamcrest.Matchers#allOf(Iterable)}
     *  with sub-matchers:
     *   {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)},
     *   and {@link android.support.test.espresso.matcher.ViewMatchers#isDescendantOfA(org.hamcrest.Matcher)},
     *   the second of which also has sub-matcher:
     *    {@link android.support.test.espresso.matcher.ViewMatchers#withId(int)}.
     * @param itemType  the class type to use for {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     * @param parentId  the id to use for {@link android.support.test.espresso.matcher.ViewMatchers#withId(int)}
     */
    @SuppressWarnings("unchecked")
    public DynamicListAdapter(Class<T> type, Class itemType, int parentId) {
        this(type, allOf(instanceOf(itemType),
                isDescendantOfA(withId(parentId))));
    }

    /**
     * A generically typed DynamicListAdapter with selector:
     *  {@link org.hamcrest.Matchers#allOf(Iterable)}
     *  with sub-matchers:
     *   {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)},
     *   and {@link android.support.test.espresso.matcher.ViewMatchers#isDescendantOfA(org.hamcrest.Matcher)},
     *   the second of which also has sub-matcher:
     *    {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}.
     * @param itemType  the class type to use for the first usage of {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     * @param parentClass  the class to use for the second usage of {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     */
    @SuppressWarnings("unchecked")
    public DynamicListAdapter(Class<T> type, Class itemType, Class parentClass) {
        this(type, allOf(instanceOf(itemType),
                         isDescendantOfA( instanceOf(parentClass) )));
    }


    /**
     * A generically typed DynamicListAdapter with selector:
     *  {@link org.hamcrest.Matchers#allOf(Iterable)}
     *  with sub-matchers:
     *   {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)},
     *   and {@link android.support.test.espresso.matcher.ViewMatchers#isDescendantOfA(org.hamcrest.Matcher)},
     *   the second of which also has sub-matchers:
     *    {@link android.support.test.espresso.matcher.ViewMatchers#withId(int)} and
     *    {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}.
     * @param itemType  the class type to use for the first usage of {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     * @param parentId  the id to use for {@link android.support.test.espresso.matcher.ViewMatchers#withId(int)}
     * @param parentClass  the class to use for the second usage of {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#instanceOf(Class)}
     */
    @SuppressWarnings("unchecked")
    public DynamicListAdapter(Class<T> type, Class itemType, int parentId, Class parentClass) {
        this(type, allOf(instanceOf(itemType),
                         isDescendantOfA(allOf(
                                withId(parentId),
                                instanceOf(parentClass) ))));
    }

    /**
     * Instantiates a {@link android.support.test.espresso.ViewInteraction} and retains type and matcher for later access.
     * @param type  the class of the generic type
     * @param childMatcher  the {@link org.hamcrest.Matcher} to select the {@link android.widget.AdapterView}
     */
    public DynamicListAdapter(Class<T> type, Matcher<View> childMatcher) {
        this.type = type;
        this.childMatcher = childMatcher;
    }

    /** @see BaseView#goesTo(Class) */
    public <E extends PageObject> DynamicListAdapter<E> goesTo(Class<E> type) {
        return new DynamicListAdapter<>(type, childMatcher);
    }

    /**
     * Creates a new {@link Clickable} for the matcher of the child and {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#hasIndex(int)}
     * @param index the index for {@link com.mindbodyonline.ironhide.Infrastructure.Extensions.BaseViewMatchers#hasIndex(int)}
     * @return  the Clickable created
     */
    public Clickable<T> getItemAt(int index) {
        return getItemMatching(hasIndex(index));
    }

    /**
     * Creates a new {@link Clickable} for the matcher of the child and {@link android.support.test.espresso.matcher.ViewMatchers#withText(int)}
     * @param text  the String to use for {@link android.support.test.espresso.matcher.ViewMatchers#withText(int)}
     * @return  the Clickable created
     */
    public Clickable<T> getItemFromText(String text) {
        return getItemMatching(withText(text));
    }

    /**
     * Creates a new {@link Clickable} for the matcher of the child and itemMatcher
     * @param itemMatcher the {@link org.hamcrest.Matcher} to append to the child matcher
     * @return  the Clickable created
     */
    @SuppressWarnings("unchecked")
    public Clickable<T> getItemMatching(Matcher<View> itemMatcher) {
        return new Clickable<>(type, allOf(isDisplayed(), childMatcher, itemMatcher));
    }
    
    // Compatibility pausing

    public T pause() {
        return pause(PageObject.DEFAULT_PAUSE_TIME);
    }
    
    public T pause(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
            if (type != null)
                return type.newInstance();
        } catch (InstantiationException | IllegalAccessException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}