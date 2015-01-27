package com.mindbodyonline.ironhide.Infrastructure;

import android.database.Cursor;
import android.support.test.espresso.matcher.CursorMatchers;


import org.hamcrest.Matcher;

/**
 * Created by gregory.sawers on 1/27/2015.
 */
public class Cursors {

    private Cursor check;

    public Cursors(Cursor toCheck){
        check = toCheck;
    }

    /**
     * Checks to see if this cursor matches the given {@link org.hamcrest.Matcher<android.preference.Preference>}
     * @param cursorMatcher The matcher to check this cursor against
     * @return  this
     */
    public Cursors checkMatches(Matcher<Object> cursorMatcher) {
        cursorMatcher.matches(check);
        return this;
    }

    /**
     * Checks to see if the byte[] value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowBlob(String columnName, byte[] value){
        return checkMatches(CursorMatchers.withRowBlob(columnName, value));
    }

    /**
     * Checks to see if the byte[] value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowBlob(int columnIndex, byte[] value){
        return checkMatches(CursorMatchers.withRowBlob(columnIndex, value));
    }

    /**
     * Checks to see if the byte[] value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to check against.
     * @return this
     */
    public Cursors withRowBlob(int columnIndex, Matcher<byte[]> valueMatcher){
        return checkMatches(CursorMatchers.withRowBlob(columnIndex, valueMatcher));
    }

    /**
     * Checks to see if the byte[] value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value to check for.
     * @return this
     */
    public Cursors withRowBlob(String columnName, Matcher<byte[]> valueMatcher){
        return checkMatches(CursorMatchers.withRowBlob(columnName, valueMatcher));
    }

    /**
     * Checks to see if the byte[] value at a given column index is in the cursors data row.
     * @param columnMatcher Matcher of column to check for value in.
     * @param valueMatcher value to check for.
     * @return this
     */
    public Cursors withRowBlob(Matcher<String> columnMatcher, Matcher<byte[]> valueMatcher){
        return checkMatches(CursorMatchers.withRowBlob(columnMatcher, valueMatcher));
    }


}
