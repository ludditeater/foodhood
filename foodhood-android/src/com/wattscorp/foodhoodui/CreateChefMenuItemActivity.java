package com.wattscorp.foodhoodui;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

import com.wattscorp.foodhoodui.connections.CreateMenuItemAsyncTask;
import com.wattscorp.foodhoodui.dto.ChefMenuItem;
import com.wattscorp.foodhoodui.dto.User;
import com.wattscorp.foodhoodui.security.SessionManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

public class CreateChefMenuItemActivity extends AppCompatActivity {
	private Button addMenuItemBtn;
	private EditText itemName, itemPrice, houseNo, streetAdd, city, state, zip, fromDateText, fromTimeText,
			tillDateText, tillTimeText;
	private ImageView selectedImage;
	private static int RESULT_LOAD_IMAGE = 1;
	private String selectedImageStr;

	// Variable for storing current date and time
	private int fromYear, fromMonth, fromDay, fromHour, fromMinute;
	private int tillYear, tillMonth, tillDay, tillHour, tillMinute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_chef_menu_item);

		itemName = (EditText) findViewById(R.id.id_menu_item_name);
		itemPrice = (EditText) findViewById(R.id.id_menu_item_price);
		houseNo = (EditText) findViewById(R.id.id_menu_item_home_num);
		streetAdd = (EditText) findViewById(R.id.id_menu_item_available_address);
		city = (EditText) findViewById(R.id.id_menu_item_city);
		state = (EditText) findViewById(R.id.id_menu_item_state);
		zip = (EditText) findViewById(R.id.id_menu_item_zip);

		fromDateText = (EditText) findViewById(R.id.id_menu_item_available_from_date);
		fromTimeText = (EditText) findViewById(R.id.id_menu_item_available_from_time);
		tillDateText = (EditText) findViewById(R.id.id_menu_item_available_till_date);
		tillTimeText = (EditText) findViewById(R.id.id_menu_item_available_till_time);

		fromDateText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Calendar c = Calendar.getInstance();
				fromYear = c.get(Calendar.YEAR);
				fromMonth = c.get(Calendar.MONTH);
				fromDay = c.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog fromdpd = new DatePickerDialog(CreateChefMenuItemActivity.this,
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						fromDateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

					}
				}, fromYear, fromMonth, fromDay);
				fromdpd.show();
			}
		});

		fromTimeText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				TimePickerDialog fromtpd = new TimePickerDialog(CreateChefMenuItemActivity.this,
						new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						fromTimeText.setText((hourOfDay > 9 ? hourOfDay : ("0" + hourOfDay)) + ":"
								+ (minute > 9 ? minute : ("0" + minute)));
					}
				}, fromHour, fromMinute, false);
				fromtpd.show();
			}
		});

		tillDateText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				final Calendar c = Calendar.getInstance();
				tillYear = c.get(Calendar.YEAR);
				tillMonth = c.get(Calendar.MONTH);
				tillDay = c.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog tilldpd = new DatePickerDialog(CreateChefMenuItemActivity.this,
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						tillDateText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

					}
				}, tillYear, tillMonth, tillDay);
				tilldpd.show();
			}
		});
		tillTimeText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TimePickerDialog tilltpd = new TimePickerDialog(CreateChefMenuItemActivity.this,
						new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						tillTimeText.setText((hourOfDay > 9 ? hourOfDay : ("0" + hourOfDay)) + ":"
								+ (minute > 9 ? minute : ("0" + minute)));
					}
				}, tillHour, tillMinute, false);
				tilltpd.show();
			}
		});

		addMenuItemBtn = (Button) findViewById(R.id.id_button_add_to_menu);
		addMenuItemBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ChefMenuItem menuItem = new ChefMenuItem();
				menuItem.setItemName(itemName.getText().toString());
				menuItem.setChefHomeNum(houseNo.getText().toString());
				menuItem.setChefStreetName(streetAdd.getText().toString());
				menuItem.setChefCity(city.getText().toString());
				menuItem.setChefState(state.getText().toString());
				menuItem.setChefZipcode(zip.getText().toString());
				User currUser = new SessionManager(CreateChefMenuItemActivity.this.getApplicationContext())
						.getUserDetails();
				menuItem.setChefId(currUser.getUsername());
				menuItem.setChefFirstName(currUser.getFirstname());
				menuItem.setChefLastName(currUser.getLastname());
				menuItem.setChefItemAvailableFrom(new Date(fromYear, fromMonth, fromDay, fromHour, fromMinute));
				menuItem.setChefItemAvailableTill(new Date(tillYear, tillMonth, tillDay, tillHour, tillMinute));
				menuItem.setImageSrc(selectedImageStr);
				menuItem.setPrice(itemPrice.getText().toString());
				new CreateMenuItemAsyncTask(CreateChefMenuItemActivity.this, menuItem).execute();
			}
		});

		selectedImage = (ImageView) findViewById(R.id.item_menu_image);
		selectedImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
			Uri selectedImg = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = this.getContentResolver().query(selectedImg, filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			// retrieve picture path
			String picturePath = cursor.getString(columnIndex);
			// read image at that path
			Bitmap bitMap = BitmapFactory.decodeFile(picturePath);
			// convert to string to pass on json object
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitMap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] b = baos.toByteArray();
			selectedImageStr = Base64.encodeToString(b, Base64.DEFAULT);
			// retrieve the image name from path to display on UI
			String attachmentName;
			int pos = picturePath.lastIndexOf("/");
			if (pos == -1 || pos == (picturePath.length() - 1)) {
				attachmentName = "";
			} else {
				attachmentName = picturePath.substring(pos + 1);
			}
			selectedImage.setImageBitmap(bitMap);
			cursor.close();
		}
	}
}
