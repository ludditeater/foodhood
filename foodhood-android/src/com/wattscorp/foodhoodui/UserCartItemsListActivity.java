package com.wattscorp.foodhoodui;

import java.util.ArrayList;
import java.util.List;

import com.wattscorp.foodhoodui.adapter.CartListItemsAdapter;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.helper.AcitivityConstants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class UserCartItemsListActivity extends AppCompatActivity {

	ListView list;
	Button checkout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_list_view);

		List<ChefMenuItem> cartItems = new ArrayList<ChefMenuItem>();
		cartItems = getIntent().getParcelableArrayListExtra("cartList");

		CartListItemsAdapter adapter = new CartListItemsAdapter(this, cartItems);
		list = (ListView) findViewById(R.id.cart_items_list_id);
		list.setAdapter(adapter);
		list.setFocusable(false);
		checkout = (Button) findViewById(R.id.cart_items_checkout_id);
		checkout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent addMenuItemIntent = new Intent(AcitivityConstants.CHEF_MENU_ITEM_CREATION);
				startActivity(addMenuItemIntent);
			};
		});
	}
}
