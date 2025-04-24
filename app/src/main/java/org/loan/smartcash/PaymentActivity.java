package org.loan.smartcash;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.loan.smartcash.models.PaymentRequest;

public class PaymentActivity extends AppCompatActivity {

    private EditText inputPhone, inputAmount;
    private Spinner providerSpinner;
    private Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greenTheme));
//            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.greenTheme));
        }

        initializeViews();
        setListeners();
    }

    private void initializeViews() {
        inputPhone = findViewById(R.id.inputPhone);
        inputAmount = findViewById(R.id.inputAmount);
        providerSpinner = findViewById(R.id.providerSpinner);
        btnPay = findViewById(R.id.btnPay);
    }

    private void setListeners() {
        btnPay.setOnClickListener(v -> {
            if (validateInputs()) {
                simulatePayment();
            }
        });
    }

    private boolean validateInputs() {
        String phone = inputPhone.getText().toString().trim();
        String amount = inputAmount.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            inputPhone.setError("Phone number is required");
            return false;
        }
        if (!phone.matches("^(07|06)\\d{8}$")) {
            inputPhone.setError("Invalid phone format");
            return false;
        }
        if (TextUtils.isEmpty(amount)) {
            inputAmount.setError("Amount is required");
            return false;
        }
        try {
            double value = Double.parseDouble(amount);
            if (value <= 0) {
                inputAmount.setError("Enter a valid amount");
                return false;
            }
        } catch (NumberFormatException e) {
            inputAmount.setError("Invalid number");
            return false;
        }

        return true;
    }

    private void simulatePayment() {
        String phone = inputPhone.getText().toString().trim();
        String provider = providerSpinner.getSelectedItem().toString();
        double amount = Double.parseDouble(inputAmount.getText().toString().trim());

        PaymentRequest request = new PaymentRequest("STU001", phone, amount, provider, "Campus Loan");

        // Simulate network delay or response
        Toast.makeText(this, "Processing payment for " + provider + "...", Toast.LENGTH_SHORT).show();

        inputPhone.postDelayed(() -> {
            Toast.makeText(this, "Payment of UGX " + amount + " via " + provider + " was successful!", Toast.LENGTH_LONG).show();
        }, 2000);
    }
}
