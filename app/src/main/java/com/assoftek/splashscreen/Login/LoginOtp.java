package com.assoftek.splashscreen.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.SignUp.Otp_verify;
import com.assoftek.splashscreen.SignUp.PhoneActivity;
import com.assoftek.splashscreen.SignUp.User_Detail;
import com.assoftek.splashscreen.databinding.ActivityLoginOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class LoginOtp extends AppCompatActivity {

    ActivityLoginOtpBinding binding;
    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String number=getIntent().getStringExtra("number");
        final String countryCode= getIntent().getStringExtra("countryCode");

        binding.number.setText(String.format("+91-%s", getIntent()   // showing number on textview
                .getStringExtra("number")));

        inputCode1=findViewById(R.id.inputCode1);
        inputCode2=findViewById(R.id.inputCode2);
        inputCode3=findViewById(R.id.inputCode3);
        inputCode4=findViewById(R.id.inputCode4);
        inputCode5=findViewById(R.id.inputCode5);
        inputCode6=findViewById(R.id.inputCode6);

        setupOTPInputs();

        final ProgressBar progressBar=findViewById(R.id.progressBar);
        final Button verify=findViewById(R.id.btn_verify);

        verificationId =getIntent().getStringExtra("verificationId");  // taking code from intent
        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputCode1.getText().toString().trim().isEmpty()
                        || inputCode2.getText().toString().trim().isEmpty()
                        || inputCode3.getText().toString().trim().isEmpty()
                        || inputCode4.getText().toString().trim().isEmpty()
                        || inputCode5.getText().toString().trim().isEmpty()
                        || inputCode6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(LoginOtp.this, "Please enter valid code",Toast.LENGTH_SHORT).show();
                    return;
                }

                String code=inputCode1.getText().toString()+inputCode2.getText().toString()+inputCode3.getText().toString()
                        +inputCode4.getText().toString()+inputCode5.getText().toString()+inputCode6.getText().toString();
                if(verificationId!=null)
                {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.btnVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                            verificationId,code);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(LoginOtp.this, "OTP Verified Successfully",Toast.LENGTH_SHORT).show();
                                        finishAffinity();

                                        Intent intent= new Intent(getApplicationContext(), DashboardActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       // intent.putExtra("mobile",number);
                                       // intent.putExtra("countryCode",countryCode);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(LoginOtp.this, "Invalid verification code entered",Toast.LENGTH_SHORT).show();
                                        finishAffinity();
                                        Intent i=new Intent(LoginOtp.this, LoginNumber.class);
                                        startActivity(i);
                                    }
                                }
                            });
                }
            }

        });

        binding.ResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                Intent i=new Intent(LoginOtp.this, LoginNumber.class);
                startActivity(i);
            }
        });
    }

    private void setupOTPInputs()
    {
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputCode2.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputCode3.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputCode4.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputCode5.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                {
                    inputCode6.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}