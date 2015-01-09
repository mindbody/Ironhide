package com.mindbodyonline.ironhide.PageObjects;

/**
 * Created by eliot.mestre on 4/9/14.
 */
public class PageObject {

    public static final int DEFAULT_PAUSE_TIME = 2000;

    /**
     * Syntax sugar for pause(type, 2000).
     *
     * @param type  the class for constructing a generic
     * @param <T>   the type to return
     * @return      a generic of passed in type
     */
    public <T extends PageObject> T pause(Class<T> type) {
        return pause(type, DEFAULT_PAUSE_TIME);
    }

    /**
     * Pauses the executing thread to wait for UI changes.
     *
     * @param type          the class for constructing a generic
     * @param timeInMillis  the amount of time to pause for
     * @param <T>           the type to return
     * @return              a generic of passed in type
     */
    public <T extends PageObject> T pause(Class<T> type, int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
            return type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
