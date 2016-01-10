package com.wattscorp.foodhoodui.connections;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.dto.OrderItem;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class CreateOrderAsyncTask extends AsyncTask<String, Void, String> {
	Activity currActivity;
	List<OrderItem> orderedItemList;

	public CreateOrderAsyncTask(Activity activity, List<OrderItem> orderedItemList) {
		this.currActivity = activity;
		this.orderedItemList = orderedItemList;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		ObjectMapper objmap = new ObjectMapper();
		String result = "false";
		try {
			String newOrderedItemsListJsonObj = objmap.writeValueAsString(orderedItemList);
			String url = ConnectionHelper.IP_PORT_CONTEXT + "/itemsOrdered/createOrder";
			result = ConnectionHelper.sendDataToServer(newOrderedItemsListJsonObj, url);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (!"false".equals(result)) {
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(currActivity.getApplicationContext(),
					"Your order has been created successfully.", duration);
			toast.show();
			currActivity.finish();
		} else {
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(currActivity.getApplicationContext(),
					"Encountered problem creating the order.", duration);
			toast.show();
		}

	}
}
