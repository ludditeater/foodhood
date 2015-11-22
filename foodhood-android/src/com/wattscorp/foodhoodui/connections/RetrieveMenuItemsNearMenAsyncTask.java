package com.wattscorp.foodhoodui.connections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wattscorp.foodhoodui.adapter.MenuListNearMeAdapter;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.dto.User;
import com.wattscorp.foodhoodui.security.SessionManager;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class RetrieveMenuItemsNearMenAsyncTask extends AsyncTask<String, Void, String> {
	MenuListNearMeAdapter currAdapter;
	Context context;

	public RetrieveMenuItemsNearMenAsyncTask(MenuListNearMeAdapter adapter, Context context) {
		this.currAdapter = adapter;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		User currUser = new SessionManager(context).getUserDetails();
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("username", currUser.getUsername()));
		String url = ConnectionHelper.IP_PORT_CONTEXT + "/chefboard/getAllMenuItems";
		String result = ConnectionHelper.sendDataToServer(postParameters, url);
		return result;
		
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		CharSequence text;
		if (result != null && !result.isEmpty()) {
			ObjectMapper objmap = new ObjectMapper();
			try {
				List<ChefMenuItem> menuitemList = objmap.readValue(result, objmap.getTypeFactory().constructCollectionType(
	                    List.class, ChefMenuItem.class));
				currAdapter.setMenuItemList(menuitemList);
				currAdapter.notifyDataSetChanged();
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
			text = "Issue retrieving menu items. Please contact administrator if already registered.";

		} else {
			text = "Issue retrieving menu items. Please contact administrator if already registered.";
		}

		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
