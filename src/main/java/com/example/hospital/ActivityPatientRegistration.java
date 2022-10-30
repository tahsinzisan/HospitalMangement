package com.example.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ActivityPatientRegistration extends AppCompatActivity {

    private TextView reLogInPage;
    private TextInputEditText name, email, number, password;
    private Button reg;
    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        mAuth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(ActivityPatientRegistration.this);
        reLogInPage= findViewById(R.id.reLoginPagePat);
        reLogInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPatientRegistration.this, MainActivity.class);
                startActivity(intent);
            }
        });
        name=findViewById(R.id.patientName);
        email=findViewById(R.id.patientEmail);
        number=findViewById(R.id.patientNumber);
        password=findViewById(R.id.patientPassword);
        reg=findViewById(R.id.patientRegisterButton);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullName=name.getText().toString().trim();
                final String patientNumber=number.getText().toString().trim();
                final String patientEmail=email.getText().toString().trim();
                final String patientPass=password.getText().toString().trim();

                if(TextUtils.isEmpty(fullName)){
                    name.setError("Name is Required");
                    return;
                }

                if(TextUtils.isEmpty(patientNumber)){
                    number.setError("Number is Required");
                    return;
                }

                if(TextUtils.isEmpty(patientEmail)){
                    email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(patientPass)){
                    password.setError("Password is Required");
                    return;
                }

                else{

                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(patientEmail, patientPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                String error = task.getException().toString();
                                Toast.makeText(ActivityPatientRegistration.this, "error", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("user").child(currentUserId);

                                HashMap userInfo = new HashMap();
                                userInfo.put("id", currentUserId);
                                userInfo.put("email", patientEmail);
                                userInfo.put("password", patientEmail);
                                userInfo.put("number", patientNumber);
                                userInfo.put("name", fullName);
                                userInfo.put("type", "patient");

                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(ActivityPatientRegistration.this, "Details set successfully", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(ActivityPatientRegistration.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                        finish();
                                        loader.dismiss();
                                    }
                                });

                                //userDatabaseRef.up
                            }
                        }
                    });
                }

            }
        });


    }



}
