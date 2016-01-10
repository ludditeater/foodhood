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
import android.widget.Toast;

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
		if (result != null && "true".equalsIgnoreCase(result.trim())) {
			Intent forwardToActivity = new Intent(forwardingActivity);
			forwardToActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			forwardToActivity.putExtra("chefIdVal", newUser.getUsername());
			context.getApplicationContext().startActivity(forwardToActivity);
		} else {
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, "There is an issue while creating user. Please try later.", duration);
			toast.show();
		}
	}
}
