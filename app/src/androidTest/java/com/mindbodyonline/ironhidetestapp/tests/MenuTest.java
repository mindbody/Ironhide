package com.mindbodyonline.ironhidetestapp.tests;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.MenuActivity;
import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.models.MenuModel;

import org.junit.Test;

/**
 * Ensures view root ordering works properly.
 */
@LargeTest
public class MenuTest extends BaseInstrumentTestCase<MenuActivity> {
    private MenuModel MenuPage = new MenuModel();
    
    public MenuTest() {
        super(MenuActivity.class);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Test
    public void testPopupMenu() {
        MenuPage
                .Popup1.doesNotExist()
                .PopupButton.click()
                .Popup1.click()
                .ResultText.withText(R.string.popup_item_1_text)
                ;
    }

    @Test
    public void testContextMenu() {
        MenuPage
                .Context2.doesNotExist()
                .ContextButton.longClick()
                .Context2.isDisplayed()
                .Context2.click()
                .ResultText.withText(R.string.context_item_2_text)
                ;
    }

    @Test
    public void testOptionMenu() {
        MenuPage
                .Options3.doesNotExist()
                .Popup1.pressMenuKey()
                .Options3.isDisplayed()
                .Options3.click()
                .ResultText.withText(R.string.options_item_3_text)
                ;
    }
}
