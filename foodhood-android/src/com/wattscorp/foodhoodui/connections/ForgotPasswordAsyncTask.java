package com.wattscorp.foodhoodui.connections;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ForgotPasswordAsyncTask extends AsyncTask<String, Void, String> {
		private Context context;
		Activity currActivity;
		private String enteredEmail;

		public ForgotPasswordAsyncTask(Context context, Activity activity,String email) {
			this.context = context;
			this.currActivity = activity;
			this.enteredEmail = email;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("email", this.enteredEmail));
			String url = ConnectionHelper.IP_PORT_CONTEXT+ "/user/forgotPassword";
			String result = ConnectionHelper.sendDataToServer(postParameters, url);
			return result;
		}

		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			CharSequence text;
			if (result != null && "true".equals(result.trim())) {
				text="New Password id sent to your email id.Please login with new password.";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				currActivity.finish();
				return;
			} else {
				text="This email does not exist in our database.Please verify the email and try again.";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();		
				currActivity.finish();
				return;
			}
	}
}
