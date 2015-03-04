package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.support.test.InstrumentationRegistry;

/**
 * Created by kyle.lozier on 2/20/2015.
 */
public class ResourceStrings {
    public static String fromId(final int id) {
        return InstrumentationRegistry.getTargetContext().getString(id);
    }
}
