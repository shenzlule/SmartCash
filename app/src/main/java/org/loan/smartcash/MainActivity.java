package org.loan.smartcash;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.loan.smartcash.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    TextView welcomeText;
    Button logoutBtn;

    CardView cardInterestCalc, cardLiveChat, cardPayments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        welcomeText = findViewById(R.id.welcomeText);
        logoutBtn = findViewById(R.id.logoutButton);
        welcomeText.setText("Welcome, " + sessionManager.getUserEmail());

        logoutBtn.setOnClickListener(v -> {
            sessionManager.logout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        // Initialize cards
        cardInterestCalc = findViewById(R.id.cardInterest);
        cardLiveChat = findViewById(R.id.cardChat);
        cardPayments = findViewById(R.id.cardPayments);

        // Set click listeners
        cardInterestCalc.setOnClickListener(v -> startActivity(new Intent(this, InterestCalculatorActivity.class)));
        cardLiveChat.setOnClickListener(v -> startActivity(new Intent(this, LiveChatActivity.class)));
        cardPayments.setOnClickListener(v -> startActivity(new Intent(this, PaymentActivity.class)));
    }

}
