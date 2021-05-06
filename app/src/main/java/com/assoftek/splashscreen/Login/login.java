package com.assoftek.splashscreen.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.HomeActivity.HomeActivity;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.SignUp.Otp_verify;
import com.assoftek.splashscreen.SignUp.SignUp;
import com.assoftek.splashscreen.SignUp.User_Detail;
import com.assoftek.splashscreen.databinding.ActivityLoginBinding;
import com.assoftek.splashscreen.databinding.ActivitySignupBinding;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;
    private int RC_SIGN_IN = 1;
    CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);


        loginBinding.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBinding.progress.setVisibility(View.VISIBLE);
                loginBinding.login.setVisibility(View.GONE);
                signupwithgoogle();
            }
        });


        loginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
                    Toast.makeText(login.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateuser(user);
                    loginBinding.progress.setVisibility(View.GONE);
                    loginBinding.login.setVisibility(View.VISIBLE);
                } else {
                    loginBinding.progress.setVisibility(View.GONE);
                    loginBinding.progress.setVisibility(View.VISIBLE);
                    Toast.makeText(login.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateuser(FirebaseUser fuser) {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {

            String person_email = account.getEmail();

            Toast.makeText(this, "Logged in as " + person_email, Toast.LENGTH_SHORT).show();
            Intent intentmovetouser = new Intent(login.this, DashboardActivity.class);
            intentmovetouser.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentmovetouser);


        }
    }
}