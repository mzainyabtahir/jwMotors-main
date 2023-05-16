package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener {

    private Button btnRequests, btnFeedback, btnUpdatePassword, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnRequests = findViewById(R.id.button_allRequests);
        btnFeedback = findViewById(R.id.button_feedBack);
        btnUpdatePassword = findViewById(R.id.button_update_password);
        btnLogout = findViewById(R.id.button_logout);

        btnRequests.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
        btnUpdatePassword.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_allRequests:
                startActivity(new Intent(this, CustomerRequests.class));
                break;
            case R.id.button_feedBack:
                startActivity(new Intent(this, CustomerFeedback.class));
                break;
            case R.id.button_update_password:
                startActivity(new Intent(this, UpdatePassword.class));
                break;
            case R.id.button_logout:
                SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }
}