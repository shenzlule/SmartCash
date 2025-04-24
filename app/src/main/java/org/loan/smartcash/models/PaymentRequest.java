package org.loan.smartcash.models;



public class PaymentRequest {

    private String studentId;
    private String phoneNumber;
    private double amount;
    private String provider; // "MTN" or "AIRTEL"
    private String purpose;

    public PaymentRequest(String studentId, String phoneNumber, double amount, String provider, String purpose) {
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.provider = provider;
        this.purpose = purpose;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getProvider() {
        return provider;
    }

    public String getPurpose() {
        return purpose;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "studentId='" + studentId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", amount=" + amount +
                ", provider='" + provider + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
