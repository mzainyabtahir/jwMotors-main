package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.loginapp.adapter.RequestAdapter;
import com.example.loginapp.models.Feedbacks;
import com.example.loginapp.models.Requests;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequests extends AppCompatActivity implements OnClickItemListener {

    private RecyclerView recyclerView;
    private List<Requests> requests;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);

        recyclerView = findViewById(R.id.rv_userRequest);
        requests = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("requests")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("CustomerRequests", document.getId() + " => " + document.getData());
                                Requests request = document.toObject(Requests.class);
                                requests.add(request);
                                // add to adapter list
                            }
                            Log.d("request size", "onCreate: "+ requests.size());
                            RequestAdapter requestAdapter = new RequestAdapter(requests, new OnClickItemListener() {
                                @Override
                                public void onClickItem() {

                                }
                            });
                            recyclerView.setAdapter(requestAdapter);
                        } else {
                            Log.d("CustomerRequests", "Error getting documents: ", task.getException());
                        }
                    }
                });




        /*requests.add(new Requests(1, "Test", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Requests(1, "Test1", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Requests(1, "Test2", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Requests(1, "Test3", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Requests(1, "Test4", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));*/



    }

    @Override
    public void onClickItem() {

    }
}