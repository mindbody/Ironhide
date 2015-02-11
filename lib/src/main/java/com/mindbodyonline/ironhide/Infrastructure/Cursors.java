package com.mindbodyonline.ironhide.Infrastructure;

import android.database.Cursor;
import android.support.test.espresso.matcher.CursorMatchers;

import org.hamcrest.Matcher;

/**
 * Cursors provides cursor assertion chaining.
 */
public class Cursors {

    private final Cursor check;

    public Cursors(Cursor toCheck){
        check = toCheck;
    }

    /**
     * Checks to see if this cursor matches the given {@link org.hamcrest.Matcher}
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
     * @param valueMatcher value matcher to check against.
     * @return this
     */
    public Cursors withRowBlob(String columnName, Matcher<byte[]> valueMatcher){
        return checkMatches(CursorMatchers.withRowBlob(columnName, valueMatcher));
    }

    /**
     * Checks to see if the byte[] value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to check against.
     * @return this
     */
    public Cursors withRowBlob(Matcher<String> columnNameMatcher, Matcher<byte[]> valueMatcher){
        return checkMatches(CursorMatchers.withRowBlob(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the double value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowDouble(String columnName, double value){
        return checkMatches(CursorMatchers.withRowDouble(columnName, value));
    }

    /**
     * Checks to see if the double value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowDouble(String columnName, Matcher<Double> valueMatcher){
        return checkMatches(CursorMatchers.withRowDouble(columnName, valueMatcher));
    }

    /**
     * Checks to see if the double value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowDouble(int columnIndex, double value){
        return checkMatches(CursorMatchers.withRowDouble(columnIndex, value));
    }

    /**
     * Checks to see if the double value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowDouble(Matcher<String> columnNameMatcher, Matcher<Double> valueMatcher){
        return checkMatches(CursorMatchers.withRowDouble(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the double value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowDouble(int columnIndex, Matcher<Double> valueMatcher){
        return checkMatches(CursorMatchers.withRowDouble(columnIndex, valueMatcher));
    }

    /**
     * Checks to see if the float value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowFloat(String columnName, float value){
        return checkMatches(CursorMatchers.withRowFloat(columnName, value));
    }

    /**
     * Checks to see if the float value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowFloat(String columnName, Matcher<Float> valueMatcher){
        return checkMatches(CursorMatchers.withRowFloat(columnName, valueMatcher));
    }

    /**
     * Checks to see if the float value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowFloat(int columnIndex, float value){
        return checkMatches(CursorMatchers.withRowFloat(columnIndex, value));
    }

    /**
     * Checks to see if the float value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowFloat(Matcher<String> columnNameMatcher, Matcher<Float> valueMatcher){
        return checkMatches(CursorMatchers.withRowFloat(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the float value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowFloat(int columnIndex, Matcher<Float> valueMatcher){
        return checkMatches(CursorMatchers.withRowFloat(columnIndex, valueMatcher));
    }

    /**
     * Checks to see if the int value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowInt(String columnName, int value){
        return checkMatches(CursorMatchers.withRowInt(columnName, value));
    }

    /**
     * Checks to see if the int value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowInt(String columnName, Matcher<Integer> valueMatcher){
        return checkMatches(CursorMatchers.withRowInt(columnName, valueMatcher));
    }

    /**
     * Checks to see if the int value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowInt(int columnIndex, int value){
        return checkMatches(CursorMatchers.withRowInt(columnIndex, value));
    }

    /**
     * Checks to see if the int value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowInt(Matcher<String> columnNameMatcher, Matcher<Integer> valueMatcher){
        return checkMatches(CursorMatchers.withRowInt(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the int value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowInt(int columnIndex, Matcher<Integer> valueMatcher){
        return checkMatches(CursorMatchers.withRowInt(columnIndex, valueMatcher));
    }

    /**
     * Checks to see if the long value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowLong(String columnName, long value){
        return checkMatches(CursorMatchers.withRowLong(columnName, value));
    }

    /**
     * Checks to see if the long value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowLong(String columnName, Matcher<Long> valueMatcher){
        return checkMatches(CursorMatchers.withRowLong(columnName, valueMatcher));
    }

    /**
     * Checks to see if the long value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowLong(int columnIndex, long value){
        return checkMatches(CursorMatchers.withRowLong(columnIndex, value));
    }

    /**
     * Checks to see if the long value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowLong(Matcher<String> columnNameMatcher, Matcher<Long> valueMatcher){
        return checkMatches(CursorMatchers.withRowLong(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the long value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowLong(int columnIndex, Matcher<Long> valueMatcher){
        return checkMatches(CursorMatchers.withRowLong(columnIndex, valueMatcher));
    }

    /**
     * Checks to see if the short value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowShort(String columnName, short value){
        return checkMatches(CursorMatchers.withRowShort(columnName, value));
    }

    /**
     * Checks to see if the short value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowShort(String columnName, Matcher<Short> valueMatcher){
        return checkMatches(CursorMatchers.withRowShort(columnName, valueMatcher));
    }

    /**
     * Checks to see if the short value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowShort(int columnIndex, short value){
        return checkMatches(CursorMatchers.withRowShort(columnIndex, value));
    }

    /**
     * Checks to see if the short value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowShort(Matcher<String> columnNameMatcher, Matcher<Short> valueMatcher){
        return checkMatches(CursorMatchers.withRowShort(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the short value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowShort(int columnIndex, Matcher<Short> valueMatcher){
        return checkMatches(CursorMatchers.withRowShort(columnIndex, valueMatcher));
    }

    /**
     * Checks to see if the string value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowString(String columnName, String value){
        return checkMatches(CursorMatchers.withRowString(columnName, value));
    }

    /**
     * Checks to see if the String value at a given column index is in the cursors data row.
     * @param columnName Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowString(String columnName, Matcher<String> valueMatcher){
        return checkMatches(CursorMatchers.withRowString(columnName, valueMatcher));
    }

    /**
     * Checks to see if the String value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param value value to check for.
     * @return this
     */
    public Cursors withRowString(int columnIndex, String value){
        return checkMatches(CursorMatchers.withRowString(columnIndex, value));
    }

    /**
     * Checks to see if the String value at a given column index is in the cursors data row.
     * @param columnNameMatcher Matcher of column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowString(Matcher<String> columnNameMatcher, Matcher<String> valueMatcher){
        return checkMatches(CursorMatchers.withRowString(columnNameMatcher, valueMatcher));
    }

    /**
     * Checks to see if the String value at a given column index is in the cursors data row.
     * @param columnIndex Column to check for value in.
     * @param valueMatcher value matcher to match against.
     * @return this
     */
    public Cursors withRowString(int columnIndex, Matcher<String> valueMatcher){
        return checkMatches(CursorMatchers.withRowString(columnIndex, valueMatcher));
    }







}
