package com.wattscorp.foodhoodui.connections;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.wattscorp.foodhoodui.helper.ActivityConstants;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class ChefAccountValidationAsyncTask extends AsyncTask<String, Void, String> {
	Context context;
	String chefCode;
	String chefId;

	public ChefAccountValidationAsyncTask(Context context,String chefId, String chefCode) {
		this.context =context;
		this.chefCode = chefCode;
		this.chefId = chefId;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("chefUserName", this.chefId));
		postParameters.add(new BasicNameValuePair("chefCode", this.chefCode));
		String url = ConnectionHelper.IP_PORT_CONTEXT+ "/user/validateChef";
		String result = ConnectionHelper.sendDataToServer(postParameters, url);
		return result;
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (result!= null && "true".equalsIgnoreCase(result.trim())) {
			Intent forwardToActivity = new Intent(ActivityConstants.ACCOUNT_CREATION_CONFIRM);
			forwardToActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.getApplicationContext().finish();
			context.getApplicationContext().startActivity(forwardToActivity);
		} else {
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, "Invalid code. Try again", duration);
			toast.show();
		}
	}
}
