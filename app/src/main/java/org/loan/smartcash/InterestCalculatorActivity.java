package org.loan.smartcash;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class InterestCalculatorActivity extends AppCompatActivity {
    EditText principal, rate, time;
    TextView result;
    Button calcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_calculator);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greenTheme));
//            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.greenTheme));
        }


        principal = findViewById(R.id.inputPrincipal);
        rate = findViewById(R.id.inputRate);
        time = findViewById(R.id.inputTime);
        result = findViewById(R.id.resultView);
        calcBtn = findViewById(R.id.btnCalculate);

        calcBtn.setOnClickListener(v -> {
            double p = Double.parseDouble(principal.getText().toString());
            double r = Double.parseDouble(rate.getText().toString());
            double t = Double.parseDouble(time.getText().toString());
            double interest = (p * r * t) / 100;
            result.setText("Interest: UGX " + interest);
        });
    }
}
