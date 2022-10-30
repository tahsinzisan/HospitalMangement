package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectRegistrationActivity extends AppCompatActivity {


    private TextView backToLogin;
    private Button docReg;
    private Button patientReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_registration);

        backToLogin = (TextView) findViewById(R.id.back);

        backToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SelectRegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        docReg = (Button) findViewById(R.id.docReg);

        docReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SelectRegistrationActivity.this, DoctorRegistrationActivity.class);
                startActivity(intent);
            }

        });



        patientReg = (Button) findViewById(R.id.patientReg);

        patientReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SelectRegistrationActivity.this, ActivityPatientRegistration.class);
                startActivity(intent);
            }

        });


    }
}