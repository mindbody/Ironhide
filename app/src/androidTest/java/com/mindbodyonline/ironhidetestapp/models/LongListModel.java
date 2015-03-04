package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.ListAdapter;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.LongListMatchers;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.LongListActivity}
 */
public class LongListModel extends PageObject {

    public ListAdapter<LongListModel> Item50Adapter = new ListAdapter<>(LongListModel.class, LongListMatchers.withItemContent("item: 50"));

    public ListAdapter<LongListModel> Item60Adapter = new ListAdapter<>(LongListModel.class, LongListMatchers.withItemContent("item: 60"));

    public ListAdapter<LongListModel> ItemSize8Adapter = new ListAdapter<>(LongListModel.class, LongListMatchers.withItemSize(8));

    public ListAdapter<LongListModel> FooterAdapter = new ListAdapter<>(LongListModel.class, LongListMatchers.isFooter());

    public Clickable<LongListModel> List = new Clickable<>(LongListModel.class, R.id.list);

    public Clickable<LongListModel> RowText = new Clickable<>(LongListModel.class, R.id.selection_row_value);

    public Clickable<LongListModel> ColumnText = new Clickable<>(LongListModel.class, R.id.selection_column_value);

    public Clickable<LongListModel> ItemSize = new Clickable<>(LongListModel.class, R.id.item_size);
}
