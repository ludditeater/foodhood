package com.wattscorp.foodhoodui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.dto.OrderItem;
import com.wattscorp.foodhoodui.dto.User;
import com.wattscorp.foodhoodui.security.SessionManager;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CartListItemsAdapter extends ArrayAdapter<ChefMenuItem> {

	private final Activity context;
	private List<ChefMenuItem> menuItemList;
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	private static final String SEPERATOR = ", ";
	private static final String DOLLAR = "$";
	TextView totalValText;
	Map<Integer, Integer> listItemsTotal = new HashMap<Integer, Integer>();
	Integer totalCount = 0;

	public CartListItemsAdapter(Activity context, List<ChefMenuItem> menuItemList, TextView totalVal) {
		super(context, R.layout.activity_cart_item, menuItemList);
		this.context = context;
		this.menuItemList = menuItemList;
		this.totalValText = totalVal;
	}

	public List<ChefMenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<ChefMenuItem> emplist) {
		this.menuItemList.addAll(emplist);
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		OrderItem order = new OrderItem();
		
		View rowView;
		if (view == null) {
			rowView = inflater.inflate(R.layout.activity_cart_item, null, true);
		} else {
			return view;
		}

		TextView chefFullName = (TextView) rowView.findViewById(R.id.cart_item_chef_name);
		TextView itemName = (TextView) rowView.findViewById(R.id.cart_item_name);
		TextView chefHomeNum = (TextView) rowView.findViewById(R.id.cart_addr_street);
		TextView chefStateZip = (TextView) rowView.findViewById(R.id.cart_addr_state_zip);
		// NumberPicker cartCount = (NumberPicker)
		// rowView.findViewById(R.id.quantityPicker);
		final TextView itemPrice = (TextView) rowView.findViewById(R.id.cart_item_price);
		final TextView subTotal = (TextView) rowView.findViewById(R.id.cart_item_subtotal);
		final EditText cartCount = (EditText) rowView.findViewById(R.id.quantity_id);
		final Button increment = (Button) rowView.findViewById(R.id.increment_id);
		final Button decrement = (Button) rowView.findViewById(R.id.decrement_id);

		ChefMenuItem menuItem = menuItemList.get(position);
		String chefName = menuItem.getChefFirstName() + SEPERATOR + menuItem.getChefLastName();
		String chefAddress1 = menuItem.getChefHomeNum() + SEPERATOR + menuItem.getChefStreetName();
		String chefAddress2 = menuItem.getChefCity() + SEPERATOR + menuItem.getChefState() + SEPERATOR + menuItem.getChefZipcode();
		final Integer itemPricePerItem = Integer.valueOf(menuItem.getPrice());
		chefFullName.setText(chefName);
		chefFullName.setTag(menuItem.getChefId());
		itemName.setText(menuItem.getItemName());
		chefHomeNum.setText(chefAddress1);
		chefStateZip.setText(chefAddress2);
		cartCount.setText("1");
		cartCount.setTag(position);
		cartCount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer count = Integer.valueOf(cartCount.getText().toString());
				subTotal.setText(DOLLAR + itemPricePerItem * count);

			};
		});

		increment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Integer count = Integer.valueOf(cartCount.getText().toString());
				count = count + 1;
				cartCount.setText(count + "");
				Integer newSubTotal = itemPricePerItem * count;
				Integer oldSubTotal = listItemsTotal.get(cartCount.getTag());
				totalCount = totalCount - oldSubTotal + newSubTotal;
				int index = Integer.valueOf(cartCount.getTag().toString());
				listItemsTotal.put(index, newSubTotal);
				subTotal.setText(DOLLAR + itemPricePerItem * count);
				orderItems.get(index).setQuantity(count.toString());
				orderItems.get(index).setTotalPrice(String.valueOf(itemPricePerItem * count));
				totalValText.setText("Total is   $"+ totalCount);
			};
		});

		decrement.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Integer count = Integer.valueOf(cartCount.getText().toString());
				count = count - 1;
				if (count < 0) {
					return;
				}
				cartCount.setText(count + "");
				Integer newSubTotal = itemPricePerItem * count;
				Integer oldSubTotal = listItemsTotal.get(cartCount.getTag());
				totalCount = totalCount - oldSubTotal + newSubTotal;
				int index = Integer.valueOf(cartCount.getTag().toString());

				listItemsTotal.put(Integer.valueOf(index), newSubTotal);
				subTotal.setText(DOLLAR + newSubTotal);
				orderItems.get(index).setQuantity(count.toString());
				orderItems.get(index).setTotalPrice(String.valueOf(itemPricePerItem * count));
				totalValText.setText("Total is   $"+ totalCount);
			};
		});
		User currUser = new SessionManager(this.getContext()).getUserDetails();
		Integer subtotal = Integer.valueOf(menuItem.getPrice());
		totalCount = totalCount + subtotal;
		listItemsTotal.put(position, subtotal);
		itemPrice.setText(DOLLAR + menuItem.getPrice());
		subTotal.setText(DOLLAR + subtotal);
		totalValText.setText("Total is   $"+ totalCount);
//		order.setMenuItemId(menuItem.get);
		order.setItemName(menuItem.getItemName());
		order.setChefName(chefName);
		order.setChefId(menuItem.getChefId());
		order.setChefAddress1(menuItem.getChefHomeNum());
		order.setChefAddress2(menuItem.getChefStreetName());
		order.setChefCityStateZipcode(chefAddress2);
		order.setChefId(menuItem.getChefId());
		order.setChefItemAvailableTill(menuItem.getChefItemAvailableTill());
		order.setTotalPrice(menuItem.getPrice());
		order.setQuantity("1");
		order.setCustomerId(currUser.getUsername());
		order.setCustomerName(currUser.getFirstname()+ SEPERATOR+ currUser.getLastname());
		order.setPaymentStatus(OrderItem.PAY_AT_CHEF);
		orderItems.add(order);
		return rowView;

	};

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	public Map<Integer, Integer> getListItemsTotal() {
		return listItemsTotal;
	}

	public void setListItemsTotal(Map<Integer, Integer> listItemsTotal) {
		this.listItemsTotal = listItemsTotal;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}