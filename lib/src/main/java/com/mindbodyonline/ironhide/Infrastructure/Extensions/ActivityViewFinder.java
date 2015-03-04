package com.mindbodyonline.ironhide.Infrastructure.Extensions;

import android.app.Activity;
import android.os.Looper;
import android.support.test.espresso.AmbiguousViewMatcherException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewFinder;
import android.view.View;

import com.android.support.test.deps.guava.base.Predicate;
import com.android.support.test.deps.guava.collect.Iterables;
import com.android.support.test.deps.guava.collect.Iterators;

import org.hamcrest.Matcher;

import java.util.Iterator;

import static android.support.test.espresso.util.TreeIterables.breadthFirstViewTraversal;
import static com.android.support.test.deps.guava.base.Preconditions.checkNotNull;
import static com.android.support.test.deps.guava.base.Preconditions.checkState;

/**
 * Adapted from {@link android.support.test.espresso.base.ViewFinderImpl}.
 * Searches the given activity for the {@link View} matching the supplied {@link Matcher}
 */
public class ActivityViewFinder implements ViewFinder {
    private View root;
    private Matcher<View> viewMatcher;
    
    public ActivityViewFinder(Activity from, Matcher<View> viewMatcher) {
        root = from.getWindow().findViewById(android.R.id.content);
        this.viewMatcher = viewMatcher;
    }

    /** {@inheritDoc} */
    @Override
    public View getView() throws AmbiguousViewMatcherException, NoMatchingViewException {
        checkMainThread();
        final Predicate<View> matcherPredicate = new MatcherPredicateAdapter<>(
                checkNotNull(viewMatcher));
        Iterator<View> matchedViewIterator = Iterables.filter(
                breadthFirstViewTraversal(root),
                matcherPredicate).iterator();
        View matchedView = null;

        while (matchedViewIterator.hasNext()) {
            if (matchedView != null) {
                // Ambiguous!
                throw new AmbiguousViewMatcherException.Builder()
                        .includeViewHierarchy(false)
                        .withViewMatcher(viewMatcher)
                        .withRootView(root)
                        .withView1(matchedView)
                        .withView2(matchedViewIterator.next())
                        .withOtherAmbiguousViews(Iterators.toArray(matchedViewIterator, View.class))
                        .build();
            }
            else
                matchedView = matchedViewIterator.next();
        }
        
        // no need to throw NoMatchingViewExceptions. We want the null value if view does not exist
        return matchedView;
    }

    /** @see android.support.test.espresso.base.ViewFinderImpl#checkMainThread() */
    private void checkMainThread() {
        checkState(Thread.currentThread().equals(Looper.getMainLooper().getThread()),
                "Executing a query on the view hierarchy outside of the main thread (on: %s)",
                Thread.currentThread().getName());
    }

    private static class MatcherPredicateAdapter<T> implements Predicate<T> {
        private final Matcher<? super T> matcher;

        private MatcherPredicateAdapter(Matcher<? super T> matcher) {
            this.matcher = checkNotNull(matcher);
        }

        @Override
        public boolean apply(T input) {
            return matcher.matches(input);
        }
    }
}
