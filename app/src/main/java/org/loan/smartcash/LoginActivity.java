package org.loan.smartcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.loan.smartcash.database.AppDatabase;
import org.loan.smartcash.models.User;
import org.loan.smartcash.session.SessionManager;

import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnLogin;




    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());

        // If user is already logged in, skip login
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Executors.newSingleThreadExecutor().execute(() -> {
                User user = db.userDao().login(userEmail, userPassword);
                runOnUiThread(() -> {
                    if (user != null) {
                        sessionManager.login(userEmail); // Save session
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });
    }
}
