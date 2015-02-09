package com.mindbodyonline.ironhide.Infrastructure;

import android.preference.Preference;
import android.support.test.espresso.matcher.PreferenceMatchers;

import org.hamcrest.Matcher;

/**
 * Preferences provides preference assertion chaining.
 */
public class Preferences {

    private Preference check;

    public Preferences(Preference toCheck) {
        this.check = toCheck;
    }

    /**
     * Checks to see if this preference matches the given {@link org.hamcrest.Matcher}<{@link Preference}>
     * @param preferenceMatcher The matcher to check this preference against
     * @return  this
     */
    public Preferences checkMatches(Matcher<Preference> preferenceMatcher) {
        preferenceMatcher.matches(check);
        return this;
    }

    /** {@link PreferenceMatchers#isEnabled()} */
    public Preferences isEnabled() {
        return checkMatches(PreferenceMatchers.isEnabled());
    }

    /** {@link PreferenceMatchers#withKey(String)} */
    public Preferences withKey(String key) {
        return checkMatches(PreferenceMatchers.withKey(key));
    }

    /** {@link PreferenceMatchers#withKey(Matcher)} */
    public Preferences withKey(Matcher<String> keyMatcher) {
        return checkMatches(PreferenceMatchers.withKey(keyMatcher));
    }

    /** {@link PreferenceMatchers#withSummary(int)} */
    public Preferences withSummary(int resourceId) {
        return checkMatches(PreferenceMatchers.withSummary(resourceId));
    }

    /** {@link PreferenceMatchers#withSummaryText(Matcher)} */
    public Preferences withSummaryText(Matcher<String> summaryMatcher) {
        return checkMatches(PreferenceMatchers.withSummaryText(summaryMatcher));
    }

    /** {@link PreferenceMatchers#withSummaryText(String)} */
    public Preferences withSummaryText(String summary) {
        return checkMatches(PreferenceMatchers.withSummaryText(summary));
    }

    /** {@link PreferenceMatchers#withTitle(int)} */
    public Preferences withTitle(int resourceId) {
        return checkMatches(PreferenceMatchers.withTitle(resourceId));
    }

    /** {@link PreferenceMatchers#withTitleText(String)} */
    public Preferences withTitleText(String title) {
        return checkMatches(PreferenceMatchers.withTitleText(title));
    }

    /** {@link PreferenceMatchers#withTitleText(Matcher)} */
    public Preferences withTitleText(Matcher<String> titleMatcher) {
        return checkMatches(PreferenceMatchers.withTitleText(titleMatcher));
    }
}
