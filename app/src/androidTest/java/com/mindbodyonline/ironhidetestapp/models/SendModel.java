package com.mindbodyonline.ironhidetestapp.models;

import android.widget.TextView;

import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.DynamicListAdapter;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.ListAdapter;
import com.mindbodyonline.ironhide.Infrastructure.IronhideViews.TextField;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.SendActivity}
 */
public class SendModel extends PageObject {
    
    public TextField<SendModel> AutoCompleteTextField = new TextField<>(SendModel.class, R.id.auto_complete_text_view);

    public DynamicListAdapter<SendModel> PopupCompleteList = new DynamicListAdapter<>(SendModel.class, TextView.class);

    public ListAdapter<SendModel> AutoCompleteList = new ListAdapter<>(SendModel.class, allOf(instanceOf(String.class), is("Baltic Sea")));
}
