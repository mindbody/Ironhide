package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;
import com.mindbodyonline.ironhidetestapp.tests.LongListMatchers;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.LongListActivity}
 */
public class LongListModel extends PageObject {
    
    public ListAdapter<LongListModel> LongListAdapter = new ListAdapter<>(LongListMatchers.withItemContent("item: 50")).goesTo(LongListModel.class);
    
    public ListAdapter<LongListModel> LongListAdapter_ItemSize8 = new ListAdapter<>(LongListMatchers.withItemSize(8)).goesTo(LongListModel.class);
    
    public ListAdapter<LongListModel> LongListAdapter_Footers = new ListAdapter<>(LongListMatchers.isFooter()).goesTo(LongListModel.class);
    
    public Clickable<LongListModel> List = new Clickable<>(LongListModel.class, R.id.list);
    
    public Clickable<LongListModel> RowText = new Clickable<>(LongListModel.class, R.id.selection_row_value);
    
    public Clickable<LongListModel> ColumnText = new Clickable<>(LongListModel.class, R.id.selection_column_value);
    
    public Clickable<LongListModel> ItemSize = new Clickable<>(LongListModel.class, R.id.item_size);
}
