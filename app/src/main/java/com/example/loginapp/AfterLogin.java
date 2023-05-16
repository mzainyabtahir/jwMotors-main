package com.example.loginapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AfterLogin extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private Button requestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        requestBtn=findViewById(R.id.button);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RequestMechanic.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.app_menu, menu);

        return true;

    }
    public void RequestMechanicView(View v) {
        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.about:
                startActivity(new Intent(this, AboutUs.class));
                return true;
            case R.id.reqMech:
                startActivity(new Intent(this, RequestMechanic.class));
                return true;

            case R.id.feedback:
                startActivity(new Intent(this, Feedback.class));
                return true;

            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();
                startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.mylocation:
                startActivity(new Intent(this, MapsActivity.class));
                return true;

            case R.id.services:
                startActivity(new Intent(this, Services.class));
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }
}
