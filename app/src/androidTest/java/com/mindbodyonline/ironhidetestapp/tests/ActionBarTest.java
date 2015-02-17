package com.mindbodyonline.ironhidetestapp.tests;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.ActionBarTestActivity;
import com.mindbodyonline.ironhidetestapp.models.ActionBarModel;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;

/**
 * Demonstrates Espresso with action bar and contextual action mode. 
 * {@link openActionBarOverflowOrOptionsMenu()} opens the overflow menu from an action bar.
 * {@link openContextualActionModeOverflowMenu()} opens the overflow menu from an contextual action
 * mode.
 */
@LargeTest
public class ActionBarTest extends BaseInstrumentTestCase<ActionBarTestActivity> {
    private ActionBarModel ActionBarPage = new ActionBarModel();
    
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
