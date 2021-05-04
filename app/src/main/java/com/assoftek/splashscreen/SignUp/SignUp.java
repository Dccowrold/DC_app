package com.assoftek.splashscreen.SignUp;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivitySignupBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    ActivitySignupBinding binding;

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.phoneNumber.getText().toString().isEmpty())
                {
                    binding.phoneNumber.setError("Enter mobile number!!");
                    return;
                }
                if(binding.countryCode.getText().toString().isEmpty())
                {
                    binding.countryCode.setError("Enter your country code!!");
                    return;
                }
                if(binding.email.getText().toString().isEmpty())
                {
                    binding.email.setError("Enter your email!!");
                    return;
                }

                if(binding.password.getText().toString().isEmpty() )
                {
                    binding.password.setError("Enter your password!!");
                    return;
                }


                if(binding.password.getText().toString().length()<8)
                {
                    binding.password.setError("Enter password of minimum 8 characters.");
                    return;
                }


                final EditText inputMobile= findViewById(R.id.phoneNumber);
                final EditText code= findViewById(R.id.countryCode);

                binding.progressBar.setVisibility(View.VISIBLE);
                binding.signUpButton.setVisibility(View.INVISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(    // number verification with country code
                        code.getText().toString()+inputMobile.getText().toString(),60,
                        TimeUnit.SECONDS, SignUp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.signUpButton.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.signUpButton.setVisibility(View.VISIBLE);
                                Toast.makeText(SignUp.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.signUpButton.setVisibility(View.VISIBLE);
                                Intent i=new Intent(SignUp.this,Otp_verify.class);
                                i.putExtra("verificationId", verificationId);
                                i.putExtra("number", binding.phoneNumber.getText().toString());
                                i.putExtra("email", binding.email.getText().toString());
                                i.putExtra("countryCode", binding.countryCode.getText().toString());
                                i.putExtra("password", binding.password.getText().toString());
                                startActivity(i);
                            }
                        });


            }
        });
    }

}
