package com.assoftek.splashscreen.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.SignUp.SignUp;
import com.assoftek.splashscreen.databinding.ActivityLoginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.Arrays;

public class login extends Activity {


    ActivityLoginBinding binding;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;
    CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //twitter code
        TwitterAuthConfig config = new TwitterAuthConfig(getString(R.string.Api_key),getString(R.string.Api_Secret));
        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(config)
                .build();
        Twitter.initialize(twitterConfig);



        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);


        // going to sign up
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, SignUp.class);
                startActivity(intent);
            }
        });



        binding.twTwitterLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.twitter)
                    binding.twTwitterLogo.performClick();
                Log.i("Button","Twitter pressed");

            }
        });

        binding.twitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.i("callback","Came into callback");
                Toast.makeText(login.this, "Signed in using twitter", Toast.LENGTH_SHORT).show();
                MainProcess(result.data);
            }

            @Override
            public void failure(TwitterException exception) {

                Toast.makeText(login.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progress.setVisibility(View.VISIBLE);
                binding.loginButton.setVisibility(View.GONE);
                signupwithgoogle();
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
                binding.progress.setVisibility(View.VISIBLE);
                binding.loginButton.setVisibility(View.GONE);
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
    }

    private void MainProcess(TwitterSession data) {

        AuthCredential credential= TwitterAuthProvider.getCredential(data.getAuthToken().token,data.getAuthToken().secret);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(login.this, "Signed in twitter successfull", Toast.LENGTH_SHORT).show();

                if (!task.isSuccessful()){
                    Toast.makeText(login.this, "Firebase Auth Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateuser(user);
                    binding.progress.setVisibility(View.GONE);
                    binding.loginButton.setVisibility(View.VISIBLE);
                    Intent intent= new Intent(getApplicationContext(), LoginNumber.class);
                    startActivity(intent);
                } else {
                    binding.progress.setVisibility(View.GONE);
                    binding.loginButton.setVisibility(View.VISIBLE);
                    Toast.makeText(login.this, "Failed", Toast.LENGTH_SHORT).show();
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

        binding.twitter.onActivityResult(requestCode,resultCode,data);

    }

    private void handlesignInResult(Task<GoogleSignInAccount> completedtask) {

        try {
            GoogleSignInAccount acc = completedtask.getResult(ApiException.class);
            Toast.makeText(this, "Signned In Successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            binding.progress.setVisibility(View.GONE);
            binding.loginButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Signned In Failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateuser(user);
                    binding.progress.setVisibility(View.GONE);
                    binding.loginButton.setVisibility(View.VISIBLE);
                } else {
                    binding.progress.setVisibility(View.GONE);
                    binding.loginButton.setVisibility(View.VISIBLE);
                    Toast.makeText(login.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateuser(FirebaseUser fuser) {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {

            String person_email = account.getEmail();

            Toast.makeText(this, "Logged in as "+person_email, Toast.LENGTH_SHORT).show();
            Intent intentmovetouser = new Intent(login.this, LoginNumber.class);
            intentmovetouser.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentmovetouser);
        }

    }

}
