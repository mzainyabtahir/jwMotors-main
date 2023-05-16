package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loginapp.models.feedback;
import com.google.firebase.firestore.FirebaseFirestore;

public class Feedback extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText feedback;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        name=findViewById(R.id.feedback_name);
        email=findViewById(R.id.feedback_email);
        feedback=findViewById(R.id.feedback);
        submit=findViewById(R.id.btn_feedback);
        submit.setOnClickListener(v -> submitFeedback());
    }

    public void submitFeedback()
    {
        String Name=name.getText().toString();
        String Email=email.getText().toString();
        String Feedback=feedback.getText().toString();
        if(Name.isEmpty()||Email.isEmpty()||Feedback.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            feedback feedback1=new feedback(Name,Email,Feedback);
            // Save This Data to Database
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("feedbacks").document(String.valueOf(feedback1.getEmail()))
                    .get()
                    .addOnCompleteListener(documentSnapshot -> {

                        db.collection("feedbacks")
                                .document(String.valueOf(feedback1.getEmail()))
                                .set(feedback1)
                                .addOnSuccessListener(unused -> {
                                    Toast.makeText(getApplicationContext(), "Submit Successful", Toast.LENGTH_SHORT).show();
                                    MainActivity main = (MainActivity) getApplicationContext();
                                    main.viewPager.setCurrentItem(0);
                                })
                                .addOnFailureListener(e1 -> Toast.makeText(getApplicationContext(), "Could not make request.", Toast.LENGTH_SHORT).show());
                    });


            Intent intent=new Intent(this, AfterLogin.class);
           startActivity(intent);
        }
    }
}