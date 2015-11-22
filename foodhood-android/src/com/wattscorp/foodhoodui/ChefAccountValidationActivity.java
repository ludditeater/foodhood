package com.wattscorp.foodhoodui;

import com.wattscorp.foodhoodui.helper.AcitivityConstants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChefAccountValidationActivity extends AppCompatActivity {
    Button activateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_account_validation);
        activateBtn = (Button) findViewById(R.id.id_button_Activate);
        activateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcitivityConstants.ACCOUNT_CREATION_CONFIRM);
                startActivity(intent);
                finish();
            }
        });
    }
}
