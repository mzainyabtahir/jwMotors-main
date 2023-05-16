package com.example.loginapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText Name;
    private EditText Email;
    private EditText Password;
    private EditText RePassword;
    private Button Registerbtn;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        Name=view.findViewById(R.id.et_name);
        Email=view.findViewById(R.id.et_email);
        Password=view.findViewById(R.id.et_password);
        RePassword=view.findViewById(R.id.et_repassword);
        Registerbtn=view.findViewById(R.id.btn_register);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        return view;
    }

    public void registerUser() {
        String name = Name.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String repassword = RePassword.getText().toString();
        Toast.makeText(getActivity().getApplicationContext(), name + " + " + password, Toast.LENGTH_SHORT).show();

        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);


        User user = new User();
        user.name = name;
        user.email = email;
        user.phoneNumber = "+923111234567";
        user.password = password;

        if (!password.equals(repassword)) {
            Toast.makeText(getActivity(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get Firebase instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Check if user exists, create new user if there's no existing user.
        db.collection("users").document(user.email)
                .get()
                .addOnCompleteListener(documentSnapshot -> {
                    if (documentSnapshot.getResult().exists()) {
                        Toast.makeText(getActivity(), "Cannot register, user already exists.", Toast.LENGTH_SHORT).show();
                    } else {
                        db.collection("users")
                                .document(user.email)
                                .set(user)
                                .addOnSuccessListener(unused -> {
                                    Toast.makeText(getActivity(), "Registration Successful.", Toast.LENGTH_SHORT).show();
                                    MainActivity main = (MainActivity) this.getActivity();
                                    main.viewPager.setCurrentItem(0);
                                })
                                .addOnFailureListener(e1 -> Toast.makeText(getActivity(), "Could not register the user.", Toast.LENGTH_SHORT).show());
                    }
                });
    }

}
