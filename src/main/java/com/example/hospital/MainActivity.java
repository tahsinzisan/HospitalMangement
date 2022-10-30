package com.example.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private TextView loginPageQuestion;
    private TextInputEditText email, password;
    private Button logIn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email= findViewById(R.id.emailField);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.passField);
        logIn = findViewById(R.id.signIn);
        loginPageQuestion = (TextView) findViewById(R.id.signUpQuestion);

        loginPageQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, SelectRegistrationActivity.class);
                startActivity(intent);
            }

        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin(){
        String userEmail = email.getText().toString().trim();
        String userpass = password.getText().toString().trim();

        if(userEmail.isEmpty()){
            email.setError("Enter email");
            return;
        }

        if(userpass.isEmpty()){
            password.setError("Enter password");
            return;
        }

        mAuth.signInWithEmailAndPassword(userEmail, userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Intent intent= new Intent(MainActivity.this, PatientActivity.class);
                startActivity(intent);
            }
        });
    }
}