package com.wattscorp.foodhoodui;

import java.util.ArrayList;
import java.util.List;

import com.wattscorp.foodhoodui.adapter.CartListItemsAdapter;
import com.wattscorp.foodhoodui.connections.CreateOrderAsyncTask;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.dto.OrderItem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UserCartItemsListActivity extends AppCompatActivity {

	ListView list;
	Button checkout;
	TextView totalAmount;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart_list_view);

		List<ChefMenuItem> cartItems = new ArrayList<ChefMenuItem>();
		cartItems = getIntent().getParcelableArrayListExtra("cartList");
		totalAmount = (TextView) findViewById(R.id.cart_items_total);
		final CartListItemsAdapter adapter = new CartListItemsAdapter(this, cartItems, totalAmount);
		list = (ListView) findViewById(R.id.cart_items_list_id);
		list.setAdapter(adapter);
		list.setFocusable(false);
		checkout = (Button) findViewById(R.id.cart_items_checkout_id);

		checkout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<OrderItem> orderList = adapter.getOrderItems();
				if (orderList.size() > 0) {
					new CreateOrderAsyncTask(UserCartItemsListActivity.this, orderList).execute("");
				}
			};
		});
	}
}
