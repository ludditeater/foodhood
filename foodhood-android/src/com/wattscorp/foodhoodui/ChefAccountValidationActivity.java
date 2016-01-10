package com.wattscorp.foodhoodui;

import com.wattscorp.foodhoodui.connections.ChefAccountValidationAsyncTask;
import com.wattscorp.foodhoodui.helper.ActivityConstants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChefAccountValidationActivity extends AppCompatActivity {
	Button activateBtn;
	EditText chefCode;
	String chefId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chef_account_validation);
		activateBtn = (Button) findViewById(R.id.id_button_Activate);
		chefCode = (EditText) findViewById(R.id.id_chef_code);
		Intent intent = getIntent();
		chefId = intent.getExtras().getString("chefIdVal");

		activateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new ChefAccountValidationAsyncTask(ChefAccountValidationActivity.this.getApplicationContext(), chefId,
						chefCode.getText().toString()).execute("");
			}
		});
	}
}
