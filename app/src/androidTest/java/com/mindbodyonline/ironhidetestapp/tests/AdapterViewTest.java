package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.LongListActivity;
import com.mindbodyonline.ironhidetestapp.models.LongListModel;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.Matchers.not;

/**
 * Demonstrates the usage of
 * {@link android.support.test.espresso.Espresso#onData(org.hamcrest.Matcher)}
 * to match data within list views.
 */
@LargeTest
public class AdapterViewTest extends TestFixture<LongListActivity> {
    
    public AdapterViewTest() {
        super(LongListActivity.class);
    }

    @Test
    public void testClickOnItem50() {
        LongListPage
                .LongListAdapter.getItemAtPosition(50).click()
                .RowText.withText("50")
                ;
    }

    @Test
    public void testClickOnSpecificChildOfRow60() {
        LongListPage
                .LongListAdapter.getItemAtPosition(60).clickChildView(LongListPage.ItemSize)
                .RowText.withText("60")
                .ColumnText.withText("2")
                ;
    }

    @Test
    public void testClickOnFirstAndFifthItemOfLength8() {
        LongListPage
                .LongListAdapter_ItemSize8.getFirst().click()
                .RowText.withText("10")
                .LongListAdapter.getItemAtPosition(4).click()
                .RowText.withText("14")
                ;
    }

    @Test
    public void testClickFooter() {
        LongListPage
                .LongListAdapter_Footers.getFirst().click()
                .RowText.withText("100")
                ;
    }

    @Test
    public void testDataItemNotInAdapter(){
        LongListPage
                .List.matches(not(withAdaptedData(LongListMatchers.withItemContent("item: 168"))))
                ;
    }

    private static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
