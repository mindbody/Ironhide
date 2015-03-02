package com.mindbodyonline.ironhidetestapp;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.tests.ActionBarTest;
import com.mindbodyonline.ironhidetestapp.tests.AdapterViewTest;
import com.mindbodyonline.ironhidetestapp.tests.AdvancedSynchronizationTest;
import com.mindbodyonline.ironhidetestapp.tests.BasicTest;
import com.mindbodyonline.ironhidetestapp.tests.CustomFailureHandlerTest;
import com.mindbodyonline.ironhidetestapp.tests.DrawerActionsTest;
import com.mindbodyonline.ironhidetestapp.tests.MenuTest;
import com.mindbodyonline.ironhidetestapp.tests.MultipleWindowTest;
import com.mindbodyonline.ironhidetestapp.tests.ScrollToTest;
import com.mindbodyonline.ironhidetestapp.tests.SwipeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all tests that have been converted to Ironhide style
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ActionBarTest.class,
        AdapterViewTest.class,
        AdvancedSynchronizationTest.class,
        BasicTest.class,
        CustomFailureHandlerTest.class,
        DrawerActionsTest.class,
        MenuTest.class,
        MultipleWindowTest.class,
        ScrollToTest.class,
        SwipeTest.class,
})
public class IronhideTestSuite extends BaseInstrumentTestCase<ActionBarTestActivity> {
    public IronhideTestSuite() {
        super(ActionBarTestActivity.class);
    }
}
