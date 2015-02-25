package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.view.View;
import android.widget.TextView;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.BaseView;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;

/**
 * Created by kyle.lozier on 2/25/2015.
 */
public class IdlingView implements IdlingResource {
    private View view;
    private boolean idle;
    private ResourceCallback callback;
    private final Activity activity;
    private ActivityViewFinderImpl viewFinder;
    private String matcherDescription;

    public IdlingView(final Activity activity, BaseView<?> idling) {
        this.view = null;
        this.idle = true;
        this.callback = null;
        this.activity = activity;
        this.viewFinder = new ActivityViewFinderImpl(activity, idling.getSelector());
        this.matcherDescription = idling.getSelector().toString();
    }

    @Override
    public String getName() {
        return "Monitor for: " + (view == null ? "View matching: " + matcherDescription : view);
    }

    @Override
    public boolean isIdleNow() {
        if (view == null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view = viewFinder.getView();
                }
            });
        }
        
        boolean curState = (view != null && isDisplayingAtLeast(90).matches(view) && ((TextView) view).getText().length() > 0);

        if (!idle && curState)
            callback.onTransitionToIdle();

        return idle = curState;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }
}