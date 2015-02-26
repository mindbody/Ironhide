package com.mindbodyonline.ironhide.Fixture;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.mindbodyonline.ironhide.Infrastructure.Extensions.ResourceStrings;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.setFailureHandler;

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

    // compatibility constructor
    @Deprecated
    public BaseInstrumentTestCase(String IGNORED, Class<T> activityClass) {
        this(activityClass);
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
        setFailureHandler(new BaseFailureHandler(this));
        
        // TODO: this is bad. Gets NPE if activity changes during test
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
    
    /** @see android.support.test.espresso.Espresso#setFailureHandler(android.support.test.espresso.FailureHandler) */
    protected void onFailure(FailureHandler delegate, Throwable error, Matcher<View> viewMatcher) {
        delegate.handle(error, viewMatcher);
    }

    /**
     * A failure handler that calls {@link BaseInstrumentTestCase#onFailure(FailureHandler, Throwable, Matcher)}
     *  so that failures can be more easily handled. Does not prevent use of ones own FailureHandler
     *  to handle errors as seen fit.  
     */
    private static class BaseFailureHandler implements FailureHandler {
        private final FailureHandler delegate;
        private final BaseInstrumentTestCase fixture;

        public BaseFailureHandler(BaseInstrumentTestCase fixture) {
            this.fixture = fixture;
            this.delegate = new DefaultFailureHandler(fixture.getInstrumentation().getTargetContext());
        }

        @Override
        public void handle(Throwable error, Matcher<View> viewMatcher) {
            fixture.onFailure(delegate, error, viewMatcher);
        }
    }
}
