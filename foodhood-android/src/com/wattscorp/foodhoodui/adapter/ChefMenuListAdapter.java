package com.wattscorp.foodhoodui.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.wattscorp.foodhoodui.R;
import com.wattscorp.foodhoodui.connections.DownloadImageAsyncTask;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChefMenuListAdapter extends ArrayAdapter<ChefMenuItem> {

	private final Activity context;
	private List<ChefMenuItem> menuItemList;
	private static final String SEPERATOR = ", ";
	private static final String DAY_FORMAT = new String("MM / dd");
	private static SimpleDateFormat day_sdf = new SimpleDateFormat(DAY_FORMAT, Locale.US);
	private static final String HOUR_FORMAT = new String("HH:mm");
	private static SimpleDateFormat hour_sdf = new SimpleDateFormat(HOUR_FORMAT, Locale.US);

	public ChefMenuListAdapter(Activity context, List<ChefMenuItem> menuItemList) {
		super(context, R.layout.activity_chef_menu_listitem_view, menuItemList);
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
			rowView = inflater.inflate(R.layout.activity_chef_menu_listitem_view, null, true);
		} else {
			rowView = view;
		}

		TextView chefFullName = (TextView) rowView.findViewById(R.id.chefFullNameId);
		TextView itemName = (TextView) rowView.findViewById(R.id.item_name);
		TextView chefHomeNum = (TextView) rowView.findViewById(R.id.chefHomeNumId);
		TextView chefStreetName = (TextView) rowView.findViewById(R.id.chefStreetNameId);
		TextView chefCityStateZip = (TextView) rowView.findViewById(R.id.chefCityStateZipId);
		TextView chefItemAvailableFrom = (TextView) rowView.findViewById(R.id.chefItemAvailableFromId);
		TextView chefItemAvailableTill = (TextView) rowView.findViewById(R.id.chefItemAvailableTillId);
		ImageView chefItemImage = (ImageView) rowView.findViewById(R.id.item_image);
		TextView itemPrice = (TextView) rowView.findViewById(R.id.chef_item_price);
		TextView chefItemAvailableFromTime = (TextView) rowView.findViewById(R.id.chefItemAvailableFromTimeId);
		TextView chefItemAvailableTillTime = (TextView) rowView.findViewById(R.id.chefItemAvailableTillTimeId);

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
			chefItemAvailableTillTime.setText(hour_sdf.format(menuItem.getChefItemAvailableFrom()));
		}
		// new DownloadImageAsyncTask(chefItemImage).execute("");
		// editb.setTag(position);
		// editb.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Integer pos = (Integer) v.getTag();
		// Intent editemppage = new
		// Intent("com.singhdatagroup.cx3.admin.manageEmployees.EDITEMPPROFILE");
		// Employee emp = emplist.get(pos);
		// editemppage.putExtra("selected_emp_obj", emp);
		// v.getContext().startActivity(editemppage);
		// };
		// });

		return rowView;

	};

	@Override
	public boolean isEnabled(int position) {
		return true;
	}
}