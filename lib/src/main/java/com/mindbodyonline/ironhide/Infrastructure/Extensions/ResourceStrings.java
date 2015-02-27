package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.content.Context;

/**
 * Created by kyle.lozier on 2/20/2015.
 */
public class ResourceStrings {
    private static Context context;
    
    public static void setContext(Context context) {
        ResourceStrings.context = context;
    }
    
    public static String fromId(final int id) {
        return context.getString(id);
    }
}