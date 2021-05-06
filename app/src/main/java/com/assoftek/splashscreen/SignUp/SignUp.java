package com.assoftek.splashscreen.SignUp;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.Login.login;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivitySignupBinding;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {

    ActivitySignupBinding binding;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    private int RC_SIGN_IN = 1;


    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        binding.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.signUpButton.setVisibility(View.GONE);
                signupwithgoogle();
            }
        });


        binding.movetosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movetoSignin = new Intent(getApplicationContext(), login.class);
                startActivity(movetoSignin);

            }
        });

        mCallbackManager = CallbackManager.Factory.create();
        binding.fb.setReadPermissions(Arrays.asList("user_friends","email","public_profile"));
        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.facebook)
                    binding.fb.performClick();

            }
        });
        binding.fb.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.signUpButton.setVisibility(View.GONE);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "SignUp was unsuccessful", Toast.LENGTH_SHORT).show();
                Log.d("error fb",error.toString());

            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
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
                if (binding.email.getText().toString().isEmpty()) {
                    binding.email.setError("Enter your email!!");
                    return;
                }

                if (binding.password.getText().toString().isEmpty()) {
                    binding.password.setError("Enter your password!!");
                    return;
                }


                if (binding.password.getText().toString().length() < 8) {
                    binding.password.setError("Enter password of minimum 8 characters.");
                    return;
                }


                final EditText inputMobile = findViewById(R.id.phoneNumber);
                final EditText code = findViewById(R.id.countryCode);

                binding.progressBar.setVisibility(View.VISIBLE);
                binding.signUpButton.setVisibility(View.INVISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(    // number verification with country code
                        code.getText().toString() + inputMobile.getText().toString(), 60,
                        TimeUnit.SECONDS, SignUp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.signUpButton.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.signUpButton.setVisibility(View.VISIBLE);
                                Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.signUpButton.setVisibility(View.VISIBLE);
                                Intent i = new Intent(SignUp.this, Otp_verify.class);
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

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateuser(user);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.signUpButton.setVisibility(View.VISIBLE);
                    Intent intent= new Intent(getApplicationContext(),User_Detail.class);
                    startActivity(intent);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.signUpButton.setVisibility(View.VISIBLE);
                    Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signupwithgoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handlesignInResult(task);
        }
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handlesignInResult(Task<GoogleSignInAccount> completedtask) {

        try {
            GoogleSignInAccount acc = completedtask.getResult(ApiException.class);
            Toast.makeText(this, "Signned In Successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(this, "Signned In Failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateuser(user);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.signUpButton.setVisibility(View.VISIBLE);
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.signUpButton.setVisibility(View.VISIBLE);
                    Toast.makeText(SignUp.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateuser(FirebaseUser fuser) {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {

            String person_email = account.getEmail();

            Toast.makeText(this, "Logged in as "+person_email, Toast.LENGTH_SHORT).show();
            Intent intentmovetouser = new Intent(SignUp.this,User_Detail.class);
            intentmovetouser.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentmovetouser);


        }

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

    }

}