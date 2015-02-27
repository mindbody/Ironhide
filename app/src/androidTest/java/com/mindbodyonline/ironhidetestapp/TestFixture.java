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
import com.mindbodyonline.ironhidetestapp.models.ViewPagerModel;

/**
 * Created by gregory.sawers on 2/24/2015.
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

    public TestFixture(Class<T> activityClass) {
        super(activityClass);
    }


}
