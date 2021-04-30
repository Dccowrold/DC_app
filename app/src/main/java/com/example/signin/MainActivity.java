package com.example.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
	static GoogleSignInClient mGoogleSignInClient;
	SignInButton signinbtn;
	static FirebaseAuth mAuth;
	static String personName;
	static String personEmail;
	static String personPhoto;


	@Override
	protected void onStart() {
		super.onStart();

		FirebaseUser currentuser = mAuth.getCurrentUser();
		if (currentuser!=null){

			personName = currentuser.getDisplayName();
			personEmail = currentuser.getEmail();
			personPhoto = currentuser.getPhotoUrl().toString();
			Toast.makeText(this,"User already signed in !",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MainActivity.this, SecondActivity.class));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		signinbtn = findViewById(R.id.sign_in_button);
		mAuth = FirebaseAuth.getInstance();

		processrequest();

		signinbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				processlogin();
			}
		});
	}





	private void processlogin() {
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, 101);
	}

	private void processrequest() {
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(getString(R.string.web_client_id))
				.requestEmail()
				.build();

		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == 101) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			try {
				// Google Sign In was successful, authenticate with Firebase
				GoogleSignInAccount account = task.getResult(ApiException.class);
				Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
				firebaseAuthWithGoogle(account.getIdToken());
			} catch (ApiException e) {
				// Google Sign In failed, update UI appropriately
				Log.w("TAG", "Google sign in failed", e);
			}
		}
	}
	private void firebaseAuthWithGoogle(String idToken) {
		AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
							// Sign in success, update UI with the signed-in user's information
							Log.d("TAG", "signInWithCredential:success");
							FirebaseUser user = mAuth.getCurrentUser();
							personName = user.getDisplayName();
							personEmail = user.getEmail();
							personPhoto = user.getPhotoUrl().toString();

							startActivity(new Intent(MainActivity.this, SecondActivity.class));

							// updateUI(user);
						} else {
							Toast.makeText(MainActivity.this, "Login Error !", Toast.LENGTH_SHORT).show();;
							// If sign in fails, display a message to the user.
							Log.w("TAG", "signInWithCredential:failure", task.getException());
							// updateUI(null);
						}
					}
				});
	}

}