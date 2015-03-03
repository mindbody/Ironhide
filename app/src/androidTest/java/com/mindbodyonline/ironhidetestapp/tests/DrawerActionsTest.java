package com.mindbodyonline.ironhidetestapp.tests;

import android.test.suitebuilder.annotation.LargeTest;

import com.mindbodyonline.ironhidetestapp.DrawerActivity;
import com.mindbodyonline.ironhidetestapp.TestFixture;

import org.junit.Test;

/**
 * Demonstrates use of {@link com.mindbodyonline.ironhide.Infrastructure.IronhideViews.NavDrawer}.
 */
@LargeTest
public class DrawerActionsTest  extends TestFixture<DrawerActivity> {
    
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
