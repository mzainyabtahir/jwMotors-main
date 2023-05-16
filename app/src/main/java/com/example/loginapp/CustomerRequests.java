package com.example.loginapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.loginapp.adapter.RequestAdapter;
import com.example.loginapp.models.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequests extends AppCompatActivity implements OnClickItemListener {

    private RecyclerView recyclerView;
    private List<Request> requests;

    private LinearLayoutManager linearLayoutManager;
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);

        recyclerView = findViewById(R.id.rv_userRequest);
        requests = new ArrayList<>();

        db.collection("requests").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot:snapshotList){
                            try {
                                Request r = (snapshot.toObject(Request.class));
                                requests.add(r);
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                });



        /*requests.add(new Request(1, "Test", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Request(1, "Test1", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Request(1, "Test2", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Request(1, "Test3", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));
        requests.add(new Request(1, "Test4", "72365412", "Audi A6", "AA-111","Engine Work","abc@123","1","2"));*/

        RequestAdapter requestAdapter = new RequestAdapter(requests, this);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(requestAdapter);

    }

    @Override
    public void onClickItem() {

    }
}