package com.mindbodyonline.ironhide.Fixture;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

public class BaseActivityInstrumentTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    /**
     * {@inheritDoc}
     */
    public BaseActivityInstrumentTestCase(Class<T> activityClass) {
        super(activityClass);
    }
}
