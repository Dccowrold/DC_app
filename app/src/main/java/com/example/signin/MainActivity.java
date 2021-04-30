package com.example.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "Main";
	static GoogleSignInClient mGoogleSignInClient;
	SignInButton GSignInBtn;
	Button signInBtn;
	Switch Switch;
	static FirebaseAuth mAuth;
	static String personName;
	static String personEmail;
	static String personPhoto;
	public Boolean isOn = false;
	String email;
	String password;
	EditText etEmail;
	EditText etPassword;


	@Override
	protected void onStart() {
		super.onStart();

		FirebaseUser currentuser = mAuth.getCurrentUser();
		if (currentuser!=null){


			Toast.makeText(this,"User already signed in !",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MainActivity.this, SecondActivity.class));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GSignInBtn = findViewById(R.id.sign_in_button);
		Switch = findViewById(R.id.switch1);
		signInBtn = findViewById(R.id.button);
		etEmail = findViewById(R.id.etEmail);
		etPassword = findViewById(R.id.etPass);
		mAuth = FirebaseAuth.getInstance();

		processrequest();


		//Changing button text and isOn value on switch clicked

		Switch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isOn){
					signInBtn.setText("Sign in");
					isOn = false;

				}else {
					signInBtn.setText("Sign up");
					isOn = true;

				}
			}
		});


		signInBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				email = etEmail.getText().toString();
				password = etPassword.getText().toString();
				if (isOn){
					//signUp
					signUpProcess();
				}else {
					signInProcess();
				}
			}
		});
		
		

		GSignInBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				processlogin();
			}
		});



	}


	//sign in process for email and password
	private void signInProcess() {
		Toast.makeText(this,"Sign in process",Toast.LENGTH_SHORT).show();
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "signInWithEmail:success");

							FirebaseUser user = mAuth.getCurrentUser();
							boolean emailVerified = user.isEmailVerified();
							if (emailVerified){

								Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
								startActivity(new Intent(MainActivity.this, SecondActivity.class));
							}else{
								Toast.makeText(MainActivity.this, "Email is't verified !", Toast.LENGTH_SHORT).show();
							}

							//updateUI(user);
						} else {
							// If sign in fails, display a message to the user.
							Log.w(TAG, "signInWithEmail:failure", task.getException());
							Toast.makeText(MainActivity.this, "Authentication failed.",
									Toast.LENGTH_SHORT).show();
							//updateUI(null);
						}
					}
				});


	}



   //sign up process for email n password
	private void signUpProcess() {

		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "createUserWithEmail:success");
							FirebaseUser user = mAuth.getCurrentUser();
							Toast.makeText(MainActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
							FirebaseAuth auth = FirebaseAuth.getInstance();
							user.sendEmailVerification()
									.addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
										@Override
										public void onComplete(@NonNull Task task) {
											// Re-enable button

											if (task.isSuccessful()) {
												Toast.makeText(MainActivity.this,
														"Verification email sent to " + user.getEmail(),
														Toast.LENGTH_SHORT).show();
											} else {
												Log.e(TAG, "sendEmailVerification", task.getException());
												Toast.makeText(MainActivity.this,
														"Failed to send verification email.",
														Toast.LENGTH_SHORT).show();
											}
										}
									});
						} else {
							// If sign in fails, display a message to the user.
							Log.w(TAG, "createUserWithEmail:failure", task.getException());
							Toast.makeText(MainActivity.this, "Authentication failed.",
									Toast.LENGTH_SHORT).show();
						
						}
					}
				});
		
		
	}



	//instantiating gsignin
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



	//HAndling result of GsignIn
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



	//BackButtonLogic
	@Override
	public void onBackPressed()
	{

		finishAffinity();

		return;
	}


}