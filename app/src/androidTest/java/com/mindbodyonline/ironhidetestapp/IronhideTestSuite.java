package com.mindbodyonline.ironhidetestapp;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.tests.ActionBarTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by kyle.lozier on 2/16/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ActionBarTest.class
})
public class IronhideTestSuite extends BaseInstrumentTestCase<ActionBarTestActivity> {
    public IronhideTestSuite() {
        super(ActionBarTestActivity.class);
    }
}
