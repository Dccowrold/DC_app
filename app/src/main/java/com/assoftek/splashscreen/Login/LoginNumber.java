package com.assoftek.splashscreen.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.SignUp.Otp_verify;
import com.assoftek.splashscreen.SignUp.PhoneActivity;
import com.assoftek.splashscreen.databinding.ActivityLoginNumberBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginNumber extends AppCompatActivity {

    ActivityLoginNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.phoneNumber.getText().toString().isEmpty()) {
                    binding.phoneNumber.setError("Enter mobile number!!");
                    return;
                }
                if (binding.countryCode.getText().toString().isEmpty()) {
                    binding.countryCode.setError("Enter your country code!!");
                    return;
                }

                final EditText inputMobile = findViewById(R.id.phoneNumber);
                final EditText code = findViewById(R.id.countryCode);

                binding.progressBar.setVisibility(View.VISIBLE);
                binding.buttonGetOTP.setVisibility(View.INVISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(    // number verification with country code
                        "+"+code.getText().toString() + inputMobile.getText().toString(), 60,
                        TimeUnit.SECONDS, LoginNumber.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.buttonGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.buttonGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.buttonGetOTP.setVisibility(View.VISIBLE);

                                Intent i = new Intent(LoginNumber.this, LoginOtp.class);
                                i.putExtra("uuid",getIntent().getStringExtra("uuid"));
                                i.putExtra("verificationId", verificationId);
                                i.putExtra("number", binding.phoneNumber.getText().toString());
                                i.putExtra("countryCode", binding.countryCode.getText().toString());
                                startActivity(i);

                            }
                        });
            }
        });
    }
}