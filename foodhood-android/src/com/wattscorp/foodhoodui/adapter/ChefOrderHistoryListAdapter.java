package com.wattscorp.foodhoodui.adapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.connections.UpdatePaymentStatusAsyncTask;
import com.wattscorp.foodhoodui.dto.OrderHistoryItem;
import com.wattscorp.foodhoodui.dto.OrderHistorySubItem;
import com.wattscorp.foodhoodui.dto.OrderItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChefOrderHistoryListAdapter extends ArrayAdapter<OrderHistoryItem> {

	private final Activity context;
	private List<OrderHistoryItem> orderHistoryItemList;
	private static final String DOLLAR = "$";
	private static final String DAY_FORMAT = new String("MM / dd");
	private static SimpleDateFormat day_sdf = new SimpleDateFormat(DAY_FORMAT, Locale.US);

	public ChefOrderHistoryListAdapter(Activity context, List<OrderHistoryItem> orderHistoryItemList) {
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
			rowView = inflater.inflate(R.layout.activity_chef_order_listitem_view, null, true);
		} else {
			return view;
		}
		OrderHistoryItem orderItem = orderHistoryItemList.get(position);

		TextView chefFullName = (TextView) rowView.findViewById(R.id.chef_order_user_name);
		TextView pickupOrder = (TextView) rowView.findViewById(R.id.chef_order_id);
		final TextView paymentStatus = (TextView) rowView.findViewById(R.id.chef_order_payment_status);
		TextView totalPrice = (TextView) rowView.findViewById(R.id.chef_order_payment_total);
		final CheckBox markPaidChkBx = (CheckBox) rowView.findViewById(R.id.chef_order_mark_paid);
		pickupOrder.setText("Pickup order # " + orderItem.getOrderId());
		chefFullName.setText(orderItem.getCustomerName());
		paymentStatus.setText(orderItem.getPaymentStatus());
		totalPrice.setText(orderItem.getTotalAmount());
		if (OrderItem.PAY_AT_CHEF.equals(paymentStatus.getText())) {
			markPaidChkBx.setChecked(false);
		} else {
			markPaidChkBx.setChecked(true);
			markPaidChkBx.setText("Uncheck to revert payment status");
		}
		markPaidChkBx.setTag(orderItem.getOrderId());
		markPaidChkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String paymentStatusVal;
				if (isChecked) {
					paymentStatusVal = OrderItem.PAYMENT_DONE;
				} else {
					paymentStatusVal = OrderItem.PAY_AT_CHEF;
				}
				new UpdatePaymentStatusAsyncTask(context, (String) markPaidChkBx.getTag(), paymentStatusVal,
						markPaidChkBx, paymentStatus).execute();

			}
		});

		for (OrderHistorySubItem eachItem : orderItem.getOrderEachHistoryItems()) {
			View holder = inflater.inflate(R.layout.activity_order_each_item_detail, parent, false);
			TextView itemName = (TextView) holder.findViewById(R.id.individual_item_name);
			TextView itemAvailableTill = (TextView) holder.findViewById(R.id.individual_item_available_till);
			TextView itemSubtotal = (TextView) holder.findViewById(R.id.individual_item_subtotal);

			itemName.setText(eachItem.getItemName() + " (" + eachItem.getQuantity() + ")");
			if (eachItem.getChefItemAvailableTill() != null) {
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