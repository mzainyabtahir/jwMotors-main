package com.example.loginapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText loginEmail;
    private EditText loginPassword;
    private TextView empLogin;
    private Button Loginbtn;

    public static final String MyPREFERENCES = "MyPrefs" ;

    SharedPreferences sharedpreferences ;


    public LoginFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        loginEmail=view.findViewById(R.id.et_email);
        empLogin=view.findViewById(R.id.emplogin);
        loginPassword=view.findViewById(R.id.et_password1);
        empLogin.setOnClickListener(v -> EmployeeLogin());
        Loginbtn=view.findViewById(R.id.btn_login);
        Loginbtn.setOnClickListener(v -> loginUser());
        return view;
    }

    public void loginUser()
    {
        String email=loginEmail.getText().toString();
        String password=loginPassword.getText().toString();

        firebaseLogin(email, password);
    }

    private void firebaseLogin(String email, String password){
        // Get Firebase instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Check if user exists, create new user if there's no existing user.
        db.collection("users").document(email)
                .get()
                .addOnCompleteListener(documentSnapshot -> {
                    if(!documentSnapshot.getResult().exists()){
                        Toast.makeText(getActivity(), "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if (documentSnapshot.getResult().exists() && !password.equals(documentSnapshot.getResult().getString("password"))) {
                        Toast.makeText(getActivity(), "Email or password is incorrect!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sharedpreferences = this.getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("loginEmailKey",email);
                    editor.apply();
                    Intent intent=new Intent(getActivity(),AfterLogin.class);
                    startActivity(intent);
                    requireActivity().finish();
                });
    }
    public void EmployeeLogin()
    {
        Intent intent=new Intent(getActivity().getApplicationContext(),EmployeeLogin.class);
        startActivity(intent);
    }
}