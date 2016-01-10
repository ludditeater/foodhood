package com.wattscorp.foodhoodui;

import com.wattscorp.foodhoodui.connections.ForgotPasswordAsyncTask;
import com.wattscorp.foodhoodui.helper.GenericTextWatcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends AppCompatActivity{
    EditText enteredEmail;
    Button submitbtn;
    
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_forgot_password);
    	android.support.v7.app.ActionBar bar = getSupportActionBar();
    	bar.setDisplayUseLogoEnabled(true);
    	bar.setDisplayShowHomeEnabled(true);
    	submitbtn = (Button) findViewById(R.id.btnSubmit);
    	enteredEmail = (EditText) findViewById(R.id.emailid);
    	enteredEmail.addTextChangedListener(new GenericTextWatcher(enteredEmail));
    	submitbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!(GenericTextWatcher.isEmailValid(enteredEmail, true))) {
					return;
				}
				String email = enteredEmail.getText().toString();
				new ForgotPasswordAsyncTask(ForgotPasswordActivity.this.getApplicationContext(),ForgotPasswordActivity.this,email).execute();
			}
		});
    }
	
}
