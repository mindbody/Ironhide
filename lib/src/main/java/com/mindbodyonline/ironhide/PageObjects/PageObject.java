package com.mindbodyonline.ironhide.PageObjects;

/**
 * A container class for keeping view references, such as {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable}s
 *  and {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.TextField}s.
 * E.g.,
 * <p>
 * public class MyPageObject extends PageObject {
 * <p>
 *      Clickable &ltNextPage&gt MyButton = new Clickable &lt&gt(R.id.button);
 * <p>
 * }
 */
public class PageObject {

    public static final int DEFAULT_PAUSE_TIME = 2000;

    /**
     * Pause the test run for {@link PageObject#DEFAULT_PAUSE_TIME} milliseconds
     * @return A generic of the passed in type
     */
    public <T extends PageObject> T pause(Class<T> type) {
        return pause(type, DEFAULT_PAUSE_TIME);
    }

    /**
     * Pause the test run for a given amount of time(in milliseconds).
     * @param timeInMillis Time, in milliseconds, to pause for.
     * @return A generic of the passed in type.                     
     */
    public <T extends PageObject> T pause(Class<T> type, int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
