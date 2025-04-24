package org.loan.smartcash;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.loan.smartcash.session.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;
    TextView welcomeText;

    Button needhelp;
    CardView cardInterestCalc, cardLiveChat, cardPayments,logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greenTheme));
//            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.greenTheme));
        }


        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        welcomeText = findViewById(R.id.welcomeText);

        logoutBtn = findViewById(R.id.cardlogout);
        needhelp = findViewById(R.id.needhelp);

        welcomeText.setText("Welcome, " + sessionManager.getUserEmail());

        logoutBtn = findViewById(R.id.cardlogout);

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
        needhelp.setOnClickListener(v -> startActivity(new Intent(this, ChatSupportActivity.class)));

    }

}
