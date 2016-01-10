package com.wattscorp.foodhoodui.connections;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.wattscorp.foodhoodui.dto.OrderItem;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePaymentStatusAsyncTask extends AsyncTask<String, Void, String> {

	private Context context;
	private String orderId;
	private String paymentStatusTxt;
	CheckBox markPaidChkBx;
	TextView paymentStatus;

	public UpdatePaymentStatusAsyncTask(Context context, String orderId, String paymentStatusTxt,
			CheckBox markPaidChkBx, TextView paymentStatus) {
		this.context = context;
		this.orderId = orderId;
		this.paymentStatusTxt = paymentStatusTxt;
		this.markPaidChkBx = markPaidChkBx;
		this.paymentStatus = paymentStatus;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("orderId", this.orderId));
		postParameters.add(new BasicNameValuePair("paymentStatus", this.paymentStatusTxt));
		String url = ConnectionHelper.IP_PORT_CONTEXT + "/itemsOrdered/updatePaymentByOrder";
		String result = ConnectionHelper.sendDataToServer(postParameters, url);
		return result;
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		CharSequence text;
		if (result == null || result.length() < 1) {
			paymentStatus.setText(paymentStatusTxt);
			if (OrderItem.PAY_AT_CHEF.equalsIgnoreCase(this.paymentStatusTxt)) {
				markPaidChkBx.setText("Mark as payment received");
			} else {
				markPaidChkBx.setText("Uncheck to revert payment status");
			}
			return;
		} else {
			if (OrderItem.PAY_AT_CHEF.equalsIgnoreCase(this.paymentStatusTxt)) {
				markPaidChkBx.setChecked(false);
			} else {
				markPaidChkBx.setChecked(true);
			}
			text = "Issue with order details updating payment status. Please contact administrator.";
		}

		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

	}
}
