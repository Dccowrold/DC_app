package com.assoftek.splashscreen.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivityPhoneBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    ActivityPhoneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final String email= getIntent().getStringExtra("email");
        final String password= getIntent().getStringExtra("password");

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
                        code.getText().toString() + inputMobile.getText().toString(), 60,
                        TimeUnit.SECONDS, PhoneActivity.this,
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
                                Toast.makeText(PhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.buttonGetOTP.setVisibility(View.VISIBLE);

                                    Intent i = new Intent(PhoneActivity.this, Otp_verify.class);
                                    i.putExtra("verificationId", verificationId);
                                    i.putExtra("number", binding.phoneNumber.getText().toString());
                                    i.putExtra("countryCode", binding.countryCode.getText().toString());
                                    i.putExtra("email", email);
                                    i.putExtra("password", password);
                                    startActivity(i);

                            }
                        });
            }
        });
    }
}