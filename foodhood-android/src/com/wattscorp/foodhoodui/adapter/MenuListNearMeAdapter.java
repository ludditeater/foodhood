package com.wattscorp.foodhoodui.adapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.helper.ActivityConstants;
import com.wattscorp.foodhoodui.helper.Base64;
import com.wattscorp.foodhoodui.security.SessionManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MenuListNearMeAdapter extends ArrayAdapter<ChefMenuItem> {

	private final Activity context;
	private List<ChefMenuItem> menuItemList;
	private Map<Integer, ChefMenuItem> selectedMenuItemMap = new HashMap<Integer, ChefMenuItem>();
	private static final String SEPERATOR = ", ";
	private static final String DAY_FORMAT = new String("MM / dd");
	private static SimpleDateFormat day_sdf = new SimpleDateFormat(DAY_FORMAT, Locale.US);
	private static final String HOUR_FORMAT = new String("HH:mm");
	private static SimpleDateFormat hour_sdf = new SimpleDateFormat(HOUR_FORMAT, Locale.US);

	public MenuListNearMeAdapter(Activity context, List<ChefMenuItem> menuItemList) {
		super(context, R.layout.activity_menu_listitem_nearme_view, menuItemList);
		this.context = context;
		this.menuItemList = menuItemList;
	}

	public List<ChefMenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<ChefMenuItem> emplist) {
		this.menuItemList.addAll(emplist);
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView;
		if (view == null) {
			rowView = inflater.inflate(R.layout.activity_menu_listitem_nearme_view, null, true);
		} else {
			rowView = view;
		}

		TextView chefFullName = (TextView) rowView.findViewById(R.id.near_me_view_FullNameId);
		TextView itemName = (TextView) rowView.findViewById(R.id.near_me_view_item_name);
		TextView chefHomeNum = (TextView) rowView.findViewById(R.id.near_me_view_HomeNumId);
		TextView chefStreetName = (TextView) rowView.findViewById(R.id.near_me_view_StreetNameId);
		TextView chefCityStateZip = (TextView) rowView.findViewById(R.id.near_me_view_CityStateZipId);
		TextView chefItemAvailableFrom = (TextView) rowView.findViewById(R.id.near_me_view_ItemAvailableFromId);
		TextView chefItemAvailableTill = (TextView) rowView.findViewById(R.id.near_me_view_ItemAvailableTillId);
		TextView chefItemAvailableFromTime = (TextView) rowView.findViewById(R.id.near_me_view_ItemAvailableFromTimeId);
		TextView chefItemAvailableTillTime = (TextView) rowView.findViewById(R.id.near_me_view_ItemAvailableTillTimeId);
		ImageView chefItemimage = (ImageView) rowView.findViewById(R.id.near_me_view_item_image);
		TextView itemPrice = (TextView) rowView.findViewById(R.id.near_me_item_price);

		// ImageView chefItemImage = (ImageView)
		// rowView.findViewById(R.id.near_me_view_item_image);
		ChefMenuItem menuItem = menuItemList.get(position);

		chefFullName.setText(menuItem.getChefFirstName() + SEPERATOR + menuItem.getChefLastName());
		chefHomeNum.setText(menuItem.getChefHomeNum());
		itemName.setText(menuItem.getItemName());
		chefStreetName.setText(menuItem.getChefStreetName());
		itemPrice.setText("Price: $" + menuItem.getPrice());
		chefCityStateZip.setText(
				menuItem.getChefCity() + SEPERATOR + menuItem.getChefState() + SEPERATOR + menuItem.getChefZipcode());
		if (menuItem.getChefItemAvailableFrom() != null) {
			chefItemAvailableFrom.setText(day_sdf.format(menuItem.getChefItemAvailableFrom()));
			chefItemAvailableFromTime.setText(hour_sdf.format(menuItem.getChefItemAvailableFrom()));
		}
		if (menuItem.getChefItemAvailableTill() != null) {
			chefItemAvailableTill.setText(day_sdf.format(menuItem.getChefItemAvailableTill()));
			chefItemAvailableTillTime.setText(hour_sdf.format(menuItem.getChefItemAvailableTill()));
		}

		if (menuItem.getImageSrc() != null && menuItem.getImageSrc().length() > 0) {
			try {
				Bitmap decodedByte = Base64.decodeToBitMap(menuItem.getImageSrc());
				chefItemimage.setImageBitmap(decodedByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		final ToggleButton button = (ToggleButton) rowView.findViewById(R.id.near_me_view_toggleButton1);
		button.setTag(position);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View toggleView) {
				if(!new SessionManager(context).isLoggedIn()){
					Intent loginIntent = new Intent(ActivityConstants.LOGIN_SIGNUP_MAIN);
					context.startActivity(loginIntent);
					return;
				}
				Integer index = Integer.valueOf(toggleView.getTag().toString());
				if (button.isChecked()) {
					selectedMenuItemMap.put(index, menuItemList.get(index));
				} else {
					selectedMenuItemMap.remove(index);
				}
			}
		});

		return rowView;

	};

	public List<ChefMenuItem> getSelectedMenuItemList() {
		return new ArrayList<ChefMenuItem>(selectedMenuItemMap.values());
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}
}