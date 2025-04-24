package org.loan.smartcash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.loan.smartcash.database.AppDatabase;
import org.loan.smartcash.models.User;

import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    EditText email, password;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.btnRegister);

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
}
