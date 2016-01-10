package com.wattscorp.foodhoodui.connections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wattscorp.foodhoodui.FoodHoodMainActivity;
import com.wattscorp.foodhoodui.dto.User;
import com.wattscorp.foodhoodui.security.SessionManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class UserLoginAsycTask extends AsyncTask<String, Void, String> {

	private Activity activity;
	private String userName;
	private String password;
	private boolean userExists = true;

	public UserLoginAsycTask(Activity activity, String userName, String password) {
		this.activity = activity;
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("username", this.userName));
		postParameters.add(new BasicNameValuePair("password", this.password));
		String url = ConnectionHelper.IP_PORT_CONTEXT+ "/user/login";
		String result = ConnectionHelper.sendDataToServer(postParameters, url);
		return result;
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		CharSequence text;
		if (result != null && !result.isEmpty()) {
			ObjectMapper objmap = new ObjectMapper();
			try {
				User loggedInUser = objmap.readValue(result, User.class);
				new SessionManager(activity.getApplicationContext()).createLoginSession(loggedInUser);
				Intent homeIntent = new Intent(activity.getApplicationContext(), FoodHoodMainActivity.class);
				homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				activity.getApplicationContext().getApplicationContext().startActivity(homeIntent);
				activity.finish();
				return;
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			text = "Issue logging you in. Please contact administrator if already registered.";

		}else{
			text = "We can not log you into FoodWood with this user name and password. ";
		}

		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(activity.getApplicationContext(), text, duration);
		toast.show();

	}
}
