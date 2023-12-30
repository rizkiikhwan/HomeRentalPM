package com.example.homerentalpm;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.homerentalpm.screens.HomeActivity;
import com.example.homerentalpm.screens.SignInActivity;
import com.example.homerentalpm.screens.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // splash screen wait for 2 sec and then launch SignIn activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // if user is already login, then it will go to Home screen, otherwise need to login
                startActivities(new Intent[]{new Intent(SplashActivity.this, SignInActivity.class)});
                finish();
            }
        }, 4200);

    }
}