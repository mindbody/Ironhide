package com.mindbodyonline.ironhide.Fixture;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

public class MindbodyActivityInstrumentTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    public MindbodyActivityInstrumentTestCase(String pkg, Class<T> activityClass) {
        super(pkg, activityClass);
    }

}
