package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.assoftek.splashscreen.databinding.ActivityDashboardBinding;
import com.assoftek.splashscreen.databinding.ActivityPaymentsBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentsActivity extends AppCompatActivity implements PaymentResultListener {
    private String TAG="razorPay";
    ActivityPaymentsBinding binding;
    Button PayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        getSupportActionBar().hide();
        Checkout.preload(getApplicationContext());
        PayButton=(Button)findViewById(R.id.PayButton);
        PayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment();
            }
        });

    }

    private void setUpPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_EntPO95sG7KvMb");
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Derive Capital");
            options.put("description", "12345");
            options.put("image", "@dr");
           // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "100");//pass amount in currency subunits
            options.put("prefill.email", "useremail@gmail.com");
            options.put("prefill.contact","1234567890");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);

        } catch(Exception e) {
            Toast.makeText(activity, "Error in payments: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"payment Successful",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"payment unSuccessful",Toast.LENGTH_SHORT).show();

    }
}