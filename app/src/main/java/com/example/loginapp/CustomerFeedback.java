package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.loginapp.models.Feedbacks;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CustomerFeedback extends AppCompatActivity {

    private TextView txvFeedBack, txvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedback);

        txvFeedBack = findViewById(R.id.txv_feedback);
        txvName = findViewById(R.id.txv_name);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("feedbacks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("CustomerFeedback", document.getId() + " => " + document.getData());
                                Feedbacks feedback = document.toObject(Feedbacks.class);
                                txvFeedBack.setText("Feedback: "+feedback.getFeedback());
                                txvName.setText("Customer Name: "+feedback.getName());
                                // add to adapter list
                            }
                        } else {
                            Log.d("CustomerFeedback", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}