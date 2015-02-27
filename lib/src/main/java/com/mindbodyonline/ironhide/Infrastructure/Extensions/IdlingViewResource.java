package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.view.View;
import android.widget.TextView;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;

/**
 * An implementation of {@link IdlingResource} for {@link View}s.
 * Takes in an activity whose root contains the view to look for.
 * Can be used in conjunction with {@link android.support.test.espresso.Espresso#registerIdlingResources(android.support.test.espresso.IdlingResource...)}
 *  to wait for the {@link BaseView} provided 
 */
public class IdlingViewResource implements IdlingResource {
    private View view;
    private boolean idle, checkForText;
    private ResourceCallback callback;
    private Activity activity;
    private ActivityViewFinder viewFinder;
    private String matcherDescription;

    public IdlingViewResource(Activity activity, BaseView<?> idling) {
        this(activity, idling, true);
    }
    
    public IdlingViewResource(Activity activity, BaseView<?> idling, boolean checkForText) {
        this.view = null;
        this.idle = true;
        this.checkForText = checkForText;
        this.callback = null;
        this.activity = activity;
        this.viewFinder = new ActivityViewFinder(activity, idling.getSelector());
        this.matcherDescription = idling.getSelector().toString();
    }

    @Override
    public String getName() {
        return "Monitor for: " + (view == null ? "View matching: " + matcherDescription : view);
    }

    @Override
    public boolean isIdleNow() {
        // try to get the view if we don't have a reference to it yet
        if (view == null)
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view = viewFinder.getView();
                }
            });
        
        if (view != null) {
            // we are still processing if the view is not displayed (or if the TextView has no text or hint text)
            boolean curState = isDisplayingAtLeast(90).matches(view);

            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                curState = curState && (!checkForText
                        || (textView.getText() != null && textView.getText().length() > 0)
                        || (textView.getHint() != null && textView.getHint().length() > 0));
            }

            if (!idle && curState)
                callback.onTransitionToIdle();
            
            idle = curState;
        }

        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }
}