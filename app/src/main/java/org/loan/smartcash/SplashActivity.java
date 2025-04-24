package org.loan.smartcash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.loan.smartcash.session.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greenTheme));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.greenTheme));
        }

        new Handler().postDelayed(() -> {
            SessionManager sessionManager = new SessionManager(this);
            Intent intent = new Intent(this,
                    sessionManager.isLoggedIn() ? MainActivity.class : LoginActivity.class);
            startActivity(intent);
            finish();
        }, 2500); // 2.5 seconds
    }
}
