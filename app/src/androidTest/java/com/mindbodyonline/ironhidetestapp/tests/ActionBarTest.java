package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.ActionBarTestActivity;
import com.mindbodyonline.ironhidetestapp.models.ActionBarModel;

import org.junit.Test;

/**
 * Demonstrates Espresso with action bar and contextual action mode. 
 * {@link android.support.test.espresso.Espresso#openActionBarOverflowOrOptionsMenu(android.content.Context)} opens the overflow menu from an action bar.
 * {@link android.support.test.espresso.Espresso#openContextualActionModeOverflowMenu()} opens the overflow menu from an contextual action
 * mode.
 */
@LargeTest
// TODO: Test framework does not identify ActionBarTestActivity as an existing class
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
