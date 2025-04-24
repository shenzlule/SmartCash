package org.loan.smartcash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import org.loan.smartcash.session.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            SessionManager sessionManager = new SessionManager(this);
            Intent intent = new Intent(this,
                    sessionManager.isLoggedIn() ? MainActivity.class : LoginActivity.class);
            startActivity(intent);
            finish();
        }, 2500); // 2.5 seconds
    }
}
