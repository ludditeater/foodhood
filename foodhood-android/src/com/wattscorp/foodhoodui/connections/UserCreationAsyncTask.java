package com.wattscorp.foodhoodui.connections;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wattscorp.foodhoodui.dto.User;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

public class UserCreationAsyncTask extends AsyncTask<String, Void, String> {
	String forwardingActivity;
	Context context;
	User newUser;

	public UserCreationAsyncTask(String forwardingActivity, Context context, User newUser) {
		this.forwardingActivity = forwardingActivity;
		this.context = context;
		this.newUser = newUser;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		ObjectMapper objmap = new ObjectMapper();
		String result = "false";
		try {
			String newUserJsonObj = objmap.writeValueAsString(newUser);
			String url = ConnectionHelper.IP_PORT_CONTEXT+ "/user/signup";
			result = ConnectionHelper.sendDataToServer(newUserJsonObj, url);
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
		if ("true".equals(result)) {
			Intent forwardToActivity = new Intent(forwardingActivity);
			forwardToActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.getApplicationContext().finish();
			context.getApplicationContext().startActivity(forwardToActivity);
		} else {
			AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
			builder1.setMessage("There is an issue while creating user. Please try later.");
			builder1.setCancelable(true);
			builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					Intent forwardToActivity = new Intent(forwardingActivity);
					forwardToActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// context.getApplicationContext().finish();
					context.getApplicationContext().startActivity(forwardToActivity);
				}
			});
			AlertDialog alert11 = builder1.create();
			alert11.show();
		}

	}
}
