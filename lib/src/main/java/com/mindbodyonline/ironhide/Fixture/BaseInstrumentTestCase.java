package com.mindbodyonline.ironhide.Fixture;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.ResourceStrings;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * This class provides functional testing of a single activity.
 */
@RunWith(AndroidJUnit4.class)
public class BaseInstrumentTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    protected T mActivity;

    /**
     * @see ActivityInstrumentationTestCase2#ActivityInstrumentationTestCase2(Class)
     */
    public BaseInstrumentTestCase(Class<T> activityClass) {
        super(activityClass);
    }

    /**
     * {@inheritDoc}
     * The default setup for AndroidJUnit tests.
     * If overrode, include the @Before tag.
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
        ResourceStrings.setContext(mActivity);
    }

    /**
     *  {@inheritDoc}
     * The default tear down for AndroidJUnit tests.
     * If overrode, include the @After tag.
     */
    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
