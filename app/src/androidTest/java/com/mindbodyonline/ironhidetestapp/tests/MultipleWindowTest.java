package com.mindbodyonline.ironhidetestapp.tests;

import android.os.Build;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.SendActivity;
import com.mindbodyonline.ironhidetestapp.models.SendModel;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Demonstrates dealing with multiple windows.
 *
 * Espresso provides the ability to switch the default window matcher used in both onView and onData
 * interactions.
 *
 * @see android.support.test.espresso.Espresso#onView
 * @see android.support.test.espresso.Espresso#onData
 */
@LargeTest
public class MultipleWindowTest extends BaseInstrumentTestCase<SendActivity> {

    private SendModel SendPage = new SendModel();
    
    public MultipleWindowTest() {
        super(SendActivity.class);
    }

    @Test
    public void testInteractionsWithAutoCompletePopup() {
        if (Build.VERSION.SDK_INT < 10) {
            // Froyo's AutoCompleteTextBox is broken - do not bother testing with it.
            return;
        }
        
        SendPage
                .AutoCompleteTextField.scrollTo()
                .AutoCompleteTextField.typeText("So")
                .AutoCompleteTextField.typeTextIntoFocusedView("uth")
                .PopupCompleteList.getItemFromText("South China Sea").changeRoot().click()
                .AutoCompleteTextField.clearText()
                .AutoCompleteTextField.typeText("S")
                ;
        // NOTE: getFirst() is applied to ListAdapter before changeRoot(), a bug
        Espresso.onData(allOf(instanceOf(String.class), is("Baltic Sea")))
                .inRoot(Matchers.not(RootMatchers.DEFAULT))
                .perform(ViewActions.click());
                //.AutoCompleteList.getFirst().changeRoot().click()

        SendPage
                .AutoCompleteTextField.withText("Baltic Sea")
                ;
    }

}

