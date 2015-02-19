package com.mindbodyonline.ironhidetestapp.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.DrawerActivity;
import com.mindbodyonline.ironhidetestapp.models.DrawerModel;

import org.junit.Test;

/**
 * Demonstrates use of {@link android.support.test.espresso.contrib.DrawerActions}.
 */
@LargeTest
// TODO: Test framework does not identify DrawerActivity as an existing class
public class DrawerActionsTest  extends BaseInstrumentTestCase<DrawerActivity> {
    private DrawerModel DrawerPage = new DrawerModel();
    
    public DrawerActionsTest() {
        super(DrawerActivity.class);
    }

    @Test
    public void testOpenAndCloseDrawer() {
        DrawerPage
                .Drawer.isClosed()
                .Drawer.openDrawer()
                .Drawer.isOpen()
                .Drawer.closeDrawer()
                .Drawer.isClosed()
                ;
    }

    @Test
    public void testDrawerOpenAndClick() {
        DrawerPage
                .Drawer.openDrawer()
                .Drawer.isOpen()
                .DrawerList.getItemAtPosition(2).click()
                .Drawer.isClosed()
                .DrawerText.withText("You picked: Pickle")
                ;
    }
}
