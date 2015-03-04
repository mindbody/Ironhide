package com.mindbodyonline.ironhidetestapp;

import android.app.Activity;

import com.mindbodyonline.ironhide.Fixture.BaseInstrumentTestCase;
import com.mindbodyonline.ironhidetestapp.models.ActionBarModel;
import com.mindbodyonline.ironhidetestapp.models.DrawerModel;
import com.mindbodyonline.ironhidetestapp.models.LongListModel;
import com.mindbodyonline.ironhidetestapp.models.MenuModel;
import com.mindbodyonline.ironhidetestapp.models.ScrollModel;
import com.mindbodyonline.ironhidetestapp.models.SendModel;
import com.mindbodyonline.ironhidetestapp.models.SimpleModel;
import com.mindbodyonline.ironhidetestapp.models.SyncModel;
import com.mindbodyonline.ironhidetestapp.models.ViewPagerModel;
import com.mindbodyonline.ironhidetestapp.models.ZoomModel;

/**
 * A specific instrumentation class for the test app.
 * Holds references for each of the models for any extending class.
 */
public class TestFixture <T extends Activity> extends BaseInstrumentTestCase<T>{

    protected ActionBarModel ActionBarPage = new ActionBarModel();
    protected LongListModel LongListPage = new LongListModel();
    protected SimpleModel SimplePage = new SimpleModel();
    protected DrawerModel DrawerPage = new DrawerModel();
    protected MenuModel MenuPage = new MenuModel();
    protected SendModel SendPage = new SendModel();
    protected ScrollModel ScrollPage = new ScrollModel();
    protected ViewPagerModel PagerPage = new ViewPagerModel();
    protected SyncModel SyncPage = new SyncModel();
    protected ZoomModel ZoomPage = new ZoomModel();

    public TestFixture(Class<T> activityClass) {
        super(activityClass);
    }
}