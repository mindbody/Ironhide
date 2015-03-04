package com.mindbodyonline.ironhidetestapp.tests;

import android.os.Build;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.SendActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;

import org.junit.Test;

/**
 * Demonstrates dealing with multiple windows.
 *
 * Espresso provides the ability to switch the default window matcher used in both onView and onData
 * interactions.
 *
 * @see com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.BaseView#inRoot(org.hamcrest.Matcher)
 */
@LargeTest
public class MultipleWindowTest extends TestFixture<SendActivity> {

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
                .AutoCompleteList.changeRoot().getFirst().click()
                .AutoCompleteTextField.withText("Baltic Sea")
                ;
    }

}


