package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PatientActivity extends AppCompatActivity {

    private FirebaseUser user;
    private TextView displayName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        user = FirebaseAuth.getInstance().getCurrentUser();
        String name=" ";
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getEmail();
            //String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            //boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            //String uid = user.getUid();
        }
        displayName=findViewById(R.id.displayName);
        displayName.setText("Welcome "+ name );

    }
}

