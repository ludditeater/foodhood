package com.wattscorp.foodhoodui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AccountCreationConfirmationActivity extends AppCompatActivity {
    Button activate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
        setContentView(R.layout.activity_account_creation_confirmation);
        activate = (Button) findViewById(R.id.id_acct_creat_continue);
        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(AccountCreationConfirmationActivity.this, FoodHoodMainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeIntent);
            }
        });
    }
}
