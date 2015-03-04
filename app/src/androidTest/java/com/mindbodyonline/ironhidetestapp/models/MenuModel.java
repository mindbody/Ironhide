package com.mindbodyonline.ironhidetestapp.models;

import com.mindbodyonline.ironhide.Infrastructure.MindbodyViews.Clickable;
import com.mindbodyonline.ironhide.PageObjects.PageObject;
import com.mindbodyonline.ironhidetestapp.R;

/**
 * Page Object for {@link com.mindbodyonline.ironhidetestapp.MenuActivity}
 */
public class MenuModel extends PageObject {

    public Clickable<MenuModel> PopupButton = new Clickable<>(MenuModel.class, R.id.popup_button);

    public Clickable<MenuModel> ResultText = new Clickable<>(MenuModel.class, R.id.text_menu_result);
    
    public Clickable<MenuModel> ContextButton = new Clickable<>(MenuModel.class, R.id.text_context_menu);
    
    public Clickable<MenuModel> Popup1 = new Clickable<>(MenuModel.class, 0, R.string.popup_item_1_text);

    public Clickable<MenuModel> Popup2 = new Clickable<>(MenuModel.class, 0, R.string.popup_item_2_text);

    public Clickable<MenuModel> Popup3 = new Clickable<>(MenuModel.class, 0, R.string.popup_item_3_text);

    public Clickable<MenuModel> Context1 = new Clickable<>(MenuModel.class, 0, R.string.context_item_1_text);

    public Clickable<MenuModel> Context2 = new Clickable<>(MenuModel.class, 0, R.string.context_item_2_text);

    public Clickable<MenuModel> Context3 = new Clickable<>(MenuModel.class, 0, R.string.context_item_3_text);

    public Clickable<MenuModel> Options1 = new Clickable<>(MenuModel.class, 0, R.string.options_item_1_text);

    public Clickable<MenuModel> Options2 = new Clickable<>(MenuModel.class, 0, R.string.options_item_2_text);

    public Clickable<MenuModel> Options3 = new Clickable<>(MenuModel.class, 0, R.string.options_item_3_text);
}
