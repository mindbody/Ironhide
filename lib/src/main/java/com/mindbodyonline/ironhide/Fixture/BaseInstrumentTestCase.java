package com.mindbodyonline.ironhide.Fixture;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * This class provides functional testing of a single activity.
 * New with Espresso 2.0:
 *  - runs with android.support.test.AndroidJUnitRunner (instead of com.google...GoogleInstrumentationTestRunner)
 *  - instrumentation is a singleton instance held within InstrumentationRegistry (use this to get target context, etc)
 *  - use mActivity rather than getActivity()
 *  - all tests MUST be commented with @Test
 *  - all test classes MUST be commented with @LargeTest
 *  - espresso is in the android support repository, use the following compiles in stead of downloading espresso:
 *      compile 'com.android.support.test.espresso:espresso-core:2.0'
 *      compile 'com.android.support.test:testing-support-lib:0.1'
 *      compile 'com.android.support.test.espresso:espresso-contrib:2.0'
 *  - ensure all support repositories are updated to ensure successful builds
 */
@RunWith(AndroidJUnit4.class)
public class BaseInstrumentTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    protected Activity mActivity;

    public BaseInstrumentTestCase(Class<T> activityClass) {
        super(activityClass);
    }

    /**
     * The default setup for AndroidJUnit tests.
     * If overrode, include the @Before tag.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    /**
     * The default tear down for AndroidJUnit tests.
     * If overrode, include the @After tag.
     */
    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}