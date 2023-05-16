package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class EmployeeLogin extends AppCompatActivity {
    private EditText loginEmail;
    private EditText loginPassword;
    private Button Loginbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        loginPassword=findViewById(R.id.et_password_emp1);
        loginEmail=findViewById(R.id.et_email_emp);
        Loginbtn=findViewById(R.id.btn_login_emp);
        Loginbtn.setOnClickListener(v -> loginAdmin());
    }


    public void loginAdmin()
    {
        String email=loginEmail.getText().toString();
        String password=loginPassword.getText().toString();

        firebaseLogin(email, password);
    }

    private void firebaseLogin(String email, String password){
        // Get Firebase instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("admin").document(email)
                .get()
                .addOnCompleteListener(documentSnapshot -> {
                    if(!documentSnapshot.getResult().exists()){
                        Toast.makeText(this, "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if (documentSnapshot.getResult().exists() && !password.equals(documentSnapshot.getResult().getString("password"))) {
                        Toast.makeText(this, "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent=new Intent(this, AdminDashboard.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    startActivity(intent);
                    this.finish();
                });
    }


}