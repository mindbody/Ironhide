package com.mindbodyonline.ironhide.Infrastructure;

import android.preference.Preference;
import android.support.test.espresso.matcher.PreferenceMatchers;

import org.hamcrest.Matcher;

/**
 * Preferences provides preference assertion chaining.
 */
public class Preferences {

    private Preference check;

    // TODO: actual making this get its own preferences
    public Preferences(Preference toCheck) {
        this.check = toCheck;
    }

    /**
     * Checks to see if this preference matches the given {@link org.hamcrest.Matcher}<{@link android.preference.Preference}>
     * @param preferenceMatcher The matcher to check this preference against
     * @return  this
     */
    public Preferences checkMatches(Matcher<Preference> preferenceMatcher) {
        preferenceMatcher.matches(check);
        return this;
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#isEnabled()} */
    public Preferences isEnabled() {
        return checkMatches(PreferenceMatchers.isEnabled());
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withKey(String)} */
    // TODO: make this more abstract similar to TextFieldMatchers
    public Preferences withKey(String key) {
        return checkMatches(PreferenceMatchers.withKey(key));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withKey(org.hamcrest.Matcher)} */
    // TODO: make this more abstract similar to TextFieldMatchers
    public Preferences withKey(Matcher<String> keyMatcher) {
        return checkMatches(PreferenceMatchers.withKey(keyMatcher));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withSummary(int)} */
    public Preferences withSummary(int resourceId) {
        return checkMatches(PreferenceMatchers.withSummary(resourceId));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withSummaryText(org.hamcrest.Matcher)} */
    public Preferences withSummaryText(Matcher<String> summaryMatcher) {
        return checkMatches(PreferenceMatchers.withSummaryText(summaryMatcher));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withSummaryText(String)} */
    public Preferences withSummaryText(String summary) {
        return checkMatches(PreferenceMatchers.withSummaryText(summary));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withTitle(int)} */
    public Preferences withTitle(int resourceId) {
        return checkMatches(PreferenceMatchers.withTitle(resourceId));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withTitleText(String)} */
    public Preferences withTitleText(String title) {
        return checkMatches(PreferenceMatchers.withTitleText(title));
    }

    /** {@link android.support.test.espresso.matcher.PreferenceMatchers#withTitleText(org.hamcrest.Matcher)} */
    public Preferences withTitleText(Matcher<String> titleMatcher) {
        return checkMatches(PreferenceMatchers.withTitleText(titleMatcher));
    }
}
