package org.loan.smartcash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.loan.smartcash.database.AppDatabase;
import org.loan.smartcash.models.User;

import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    EditText email, password;
    Button btnRegister;

    TextView alreadyHaveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.btnRegister);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greenTheme));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.greenTheme));
        }


        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        // Set OnClickListener for Login link
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Login screen
                goToLoginScreen();
            }
        });
        btnRegister.setOnClickListener(v -> {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Executors.newSingleThreadExecutor().execute(() -> {
                if (db.userDao().findByEmail(userEmail) == null) {
                    db.userDao().insert(new User() {{
                        email = userEmail;
                        password = userPassword;
                    }});
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show());
                }
            });
        });
    }


    // Method to navigate to Login screen
    private void goToLoginScreen() {
        // Start the Login Activity
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); // Replace with actual LoginActivity class
        startActivity(intent);
        finish();
    }
}
