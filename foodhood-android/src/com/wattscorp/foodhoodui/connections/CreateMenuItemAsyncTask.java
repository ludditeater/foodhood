package com.wattscorp.foodhoodui.connections;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wattscorp.foodhoodui.dto.ChefMenuItem;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

public class CreateMenuItemAsyncTask extends AsyncTask<String, Void, String> {
	Activity currActivity;
	ChefMenuItem newMenuItem;

	public CreateMenuItemAsyncTask(Activity activity, ChefMenuItem menuItem) {
		this.currActivity = activity;
		this.newMenuItem = menuItem;
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
			String newMenuItemJsonObj = objmap.writeValueAsString(newMenuItem);
			String url = ConnectionHelper.IP_PORT_CONTEXT + "/chefboard/createMenu";
			result = ConnectionHelper.sendDataToServer(newMenuItemJsonObj, url);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (!"false".equals(result)) {
			 currActivity.finish();
		} else {
			AlertDialog.Builder builder1 = new AlertDialog.Builder(currActivity.getApplicationContext());
			builder1.setMessage("There is an issue while creating menu item. Please try later.");
			builder1.setCancelable(true);
			builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					 currActivity.finish();
				}
			});
			AlertDialog alert11 = builder1.create();
			alert11.show();
		}

	}
}
