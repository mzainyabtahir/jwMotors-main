package com.example.loginapp;

import static com.example.loginapp.MapsActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.models.Requests;
import com.google.firebase.firestore.FirebaseFirestore;

public class RequestMechanic extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private EditText vehicle;
    private EditText regNo;
    private EditText requiredWork;
    private Button submit;
    private CheckBox attackLocation;
    public String temp = "";



    public SharedPreferences sharedpreferences;
    public int index=1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_mechanic);
        name=findViewById(R.id.request_name);
        phone=findViewById(R.id.request_phone);
        vehicle=findViewById(R.id.request_vehicle);
        regNo=findViewById(R.id.request_registrationno);
        requiredWork=findViewById(R.id.request_requiredwork);
        submit=findViewById(R.id.btn_request);
        attackLocation=findViewById(R.id.checkBox);
        attackLocation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name1=name.getText().toString();
                String phone1=phone.getText().toString();
                String vehicle1=vehicle.getText().toString();
                String regNo1=regNo.getText().toString();
                String reqWork=requiredWork.getText().toString();
                if(name1.isEmpty()|| phone1.isEmpty()||vehicle1.isEmpty()||regNo1.isEmpty()||reqWork.isEmpty()||!attackLocation.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    String longitude= sharedpreferences.getString("longKey", null);
                    String latitude= sharedpreferences.getString("latKey", null);
                    String email= sharedpreferences.getString("loginEmailKey", null);
                    Toast.makeText(getApplicationContext(), "We have received your Requests! \n Our representative will contact you shortly.", Toast.LENGTH_SHORT).show();


                    FirebaseFirestore db= FirebaseFirestore.getInstance();

                   /* db.collection("requests").get()
                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                            List<DocumentSnapshot> snapshotList=queryDocumentSnapshots.getDocuments();
                                            for(DocumentSnapshot snapshot:snapshotList){
                                                Log.d(TAG,"onSuccess: "+snapshot.getData().toString());
                                                Log.d(TAG,"onSuccess: "+snapshot.getData().toString().charAt(((snapshot.getData().toString()).indexOf("id="))+3));
                                                int start=((snapshot.getData().toString()).indexOf("id="))+3;
                                                if((snapshot.getData().toString()).charAt(start+1) == ',')
                                                {
                                                    index=Integer.valueOf( snapshot.getData().toString().charAt(start));
                                                }
                                            }
                                        }
                                    });
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/

                    //Toast.makeText(getApplicationContext(), index, Toast.LENGTH_SHORT).show();
                    int b = (int)(Math.random()*(999999-1+1)+1);
                    Requests request=new Requests(b,name1,phone1,vehicle1,regNo1,reqWork,email,latitude,longitude);

                    db.collection("requests").document(String.valueOf(request.getId()))
                            .get()
                            .addOnCompleteListener(documentSnapshot -> {

                                db.collection("requests")
                                        .document(String.valueOf(request.getId()))
                                        .set(request)
                                        .addOnSuccessListener(unused -> {
                                            Toast.makeText(getApplicationContext(), "Registration Successful.", Toast.LENGTH_SHORT).show();
                                            MainActivity main = (MainActivity) getApplicationContext();
                                            main.viewPager.setCurrentItem(0);
                                        })
                                        .addOnFailureListener(e1 -> Toast.makeText(getApplicationContext(), "Could not make request.", Toast.LENGTH_SHORT).show());
                            });


                    Intent intent=new Intent(getApplicationContext(),AfterLogin.class);
                    startActivity(intent);
                }
            }
        });

    }

}


                    // Check if user exists, create new user if there's no existing user.

