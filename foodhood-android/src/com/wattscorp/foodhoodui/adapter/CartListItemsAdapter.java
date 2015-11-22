package com.wattscorp.foodhoodui.adapter;

import java.util.List;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CartListItemsAdapter extends ArrayAdapter<ChefMenuItem> {

	private final Activity context;
	private List<ChefMenuItem> menuItemList;
	private static final String SEPERATOR = ", ";
	private static final String DOLLAR = "$";

	public CartListItemsAdapter(Activity context, List<ChefMenuItem> menuItemList) {
		super(context, R.layout.activity_cart_item, menuItemList);
		this.context = context;
		this.menuItemList = menuItemList;
	}

	public List<ChefMenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<ChefMenuItem> emplist) {
		this.menuItemList.addAll(emplist);
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView;
		if (view == null) {
			rowView = inflater.inflate(R.layout.activity_cart_item, null, true);
		} else {
			rowView = view;
		}

		TextView chefFullName = (TextView) rowView.findViewById(R.id.cart_item_chef_name);
		TextView itemName = (TextView) rowView.findViewById(R.id.cart_item_name);
		TextView chefHomeNum = (TextView) rowView.findViewById(R.id.cart_addr_street);
		TextView chefStateZip = (TextView) rowView.findViewById(R.id.cart_addr_state_zip);
		EditText cartCount = (EditText) rowView.findViewById(R.id.cart_qty);
		final TextView itemPrice = (TextView) rowView.findViewById(R.id.cart_item_price);
		final TextView subTotal = (TextView) rowView.findViewById(R.id.cart_item_subtotal);
		ChefMenuItem menuItem = menuItemList.get(position);
		final Integer itemPricePerItem = Integer.valueOf(menuItem.getPrice());
		chefFullName.setText(menuItem.getChefFirstName() + SEPERATOR + menuItem.getChefLastName());
		itemName.setText(menuItem.getItemName());
		chefHomeNum.setText(menuItem.getChefHomeNum() + SEPERATOR + menuItem.getChefStreetName());
		chefStateZip.setText(
				menuItem.getChefCity() + SEPERATOR + menuItem.getChefState() + SEPERATOR + menuItem.getChefZipcode());
		cartCount.setText("1");
		itemPrice.setText(DOLLAR + menuItem.getPrice());
		subTotal.setText(DOLLAR + menuItem.getPrice());
		cartCount.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View qty, boolean hasFocus) {
				/*
				 * When focus is lost check that the text field has valid
				 * values.
				 */
				if (!hasFocus) {
					String value = ((EditText) qty).getText().toString();
					if(value != null){
						Integer subtotalVal = itemPricePerItem * Integer.valueOf(value);
						subTotal.setText(subtotalVal.toString());
					}
				}
			}
		});
		return rowView;

	};

	@Override
	public boolean isEnabled(int position) {
		return true;
	}
}