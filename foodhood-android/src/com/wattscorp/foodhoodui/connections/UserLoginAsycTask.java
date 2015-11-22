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

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class UserLoginAsycTask extends AsyncTask<String, Void, String> {

	private Context context;
	private String userName;
	private String password;
	private boolean userExists = true;

	public UserLoginAsycTask(Context context, String userName, String password) {
		this.context = context;
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
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
				new SessionManager(context).createLoginSession(loggedInUser);
				Intent homeIntent = new Intent(context, FoodHoodMainActivity.class);
				homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				context.getApplicationContext().startActivity(homeIntent);
				return;
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			text = "Issue logging you in. Please contact administrator if already registered.";

		}else{
			text = "We can not log you into FoodWood with this user name and password. ";
		}

		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

	}
}
