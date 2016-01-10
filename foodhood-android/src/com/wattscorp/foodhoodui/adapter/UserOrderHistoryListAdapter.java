package com.wattscorp.foodhoodui.adapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.dto.OrderHistoryItem;
import com.wattscorp.foodhoodui.dto.OrderHistorySubItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserOrderHistoryListAdapter extends ArrayAdapter<OrderHistoryItem> {

	private final Activity context;
	private List<OrderHistoryItem> orderHistoryItemList;
	private static final String DOLLAR = "$";
	private static final String SEPERATOR = ", ";
	private static final String DAY_FORMAT = new String("MM / dd");
	private static SimpleDateFormat day_sdf = new SimpleDateFormat(DAY_FORMAT, Locale.US);
	private static final String HOUR_FORMAT = new String("HH:mm");
	private static SimpleDateFormat hour_sdf = new SimpleDateFormat(HOUR_FORMAT, Locale.US);

	public UserOrderHistoryListAdapter(Activity context, List<OrderHistoryItem> orderHistoryItemList) {
		super(context, R.layout.activity_chef_order_listitem_view, orderHistoryItemList);
		this.context = context;
		this.orderHistoryItemList = orderHistoryItemList;
	}

	public List<OrderHistoryItem> getOrderHistoryItemList() {
		return orderHistoryItemList;
	}

	public void setOrderHistoryItemList(List<OrderHistoryItem> orderHistoryItemList) {
		this.orderHistoryItemList.addAll(orderHistoryItemList);
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView;
		if (view == null) {
			rowView = inflater.inflate(R.layout.activity_order_item_detail, null, true);
		} else {
			return view;
		}

		OrderHistoryItem orderItem = orderHistoryItemList.get(position);

		TextView chefFullName = (TextView) rowView.findViewById(R.id.order_history_chef_name);
		TextView chefHomeNum = (TextView) rowView.findViewById(R.id.order_pickup_at_address1);
		TextView chefStateZip = (TextView) rowView.findViewById(R.id.order_pickup_at_address2);
		TextView paymentStatus = (TextView) rowView.findViewById(R.id.order_history_payment_status);
		TextView totalPrice = (TextView) rowView.findViewById(R.id.order_history_payment_total);
		chefFullName.setText(orderItem.getChefName());
		chefHomeNum.setText(orderItem.getChefAddress1() + SEPERATOR + orderItem.getChefAddress2());
		chefStateZip.setText(orderItem.getChefCityStateZipcode());
		paymentStatus.setText(orderItem.getPaymentStatus());
		totalPrice.setText(orderItem.getTotalAmount());

		for (OrderHistorySubItem eachItem : orderItem.getOrderEachHistoryItems()) {
			View holder = inflater.inflate(R.layout.activity_order_each_item_detail, parent, false);
			TextView itemName = (TextView) holder.findViewById(R.id.individual_item_name);
			TextView itemAvailableTill = (TextView) holder.findViewById(R.id.individual_item_available_till);
			TextView itemSubtotal = (TextView) holder.findViewById(R.id.individual_item_subtotal);

			itemName.setText(eachItem.getItemName() + " ("+ eachItem.getQuantity()+")");
			if(eachItem.getChefItemAvailableTill() != null ){
				itemAvailableTill.setText(day_sdf.format(eachItem.getChefItemAvailableTill()));
			}
			itemSubtotal.setText(DOLLAR + " " + eachItem.getTotalPrice());
			((LinearLayout) rowView).addView(holder);
		}
		return rowView;

	};

	@Override
	public boolean isEnabled(int position) {
		return true;
	}
}