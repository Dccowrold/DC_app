package com.assoftek.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.databinding.ActivityPaymentsBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentsActivity extends AppCompatActivity implements PaymentResultListener {
    private String TAG="razorPay";
    ActivityPaymentsBinding binding;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        getSupportActionBar().hide();
        Checkout.preload(getApplicationContext());

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentsActivity.this , DashboardActivity.class);
                startActivity(intent);
            }
        });


        String sAmount="100";

        int amount=Math.round(Float.parseFloat(sAmount)*100);

//        binding.PayButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setUpPayment();
//            }
//        });

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
        Toast.makeText(this,"Payment Successful",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Payment unSuccessful",Toast.LENGTH_SHORT).show();

    }
}