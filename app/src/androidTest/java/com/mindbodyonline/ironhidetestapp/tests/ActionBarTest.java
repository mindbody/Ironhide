package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.ActionBarTestActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;

import org.junit.Test;

/**
 * Demonstrates some basic functionality and matchers working on the {@link android.app.ActionBar}
 */
@LargeTest
public class ActionBarTest extends TestFixture<ActionBarTestActivity> {

    public ActionBarTest() {
        super(ActionBarTestActivity.class);
    }

    @Test
    public void clickActionBarItem() {
        ActionBarPage
                .HideContextualActionBar.click()
                .SaveAction.click()
                .TextResult.withText("Save")
                ;
    }

    @Test
    public void clickActionModeItem() {
        ActionBarPage
                .ShowContextualActionBar.click()
                .LockAction.click()
                .TextResult.withText("Lock")
                ;
    }

    @Test
    public void actionBarOverflow() {
        ActionBarPage
                .HideContextualActionBar.click()
                .OverflowAction.click()
                .WorldAction.click()
                .TextResult.withText("World")
                ;
    }

    @Test
    public void testActionModeOverflow() {
        ActionBarPage
                .ShowContextualActionBar.click()
                .OverflowAction.click()
                .KeyAction.click()
                .TextResult.withText("Key")
                ;
    }
}
