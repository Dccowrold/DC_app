package com.assoftek.splashscreen.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.Login.login;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.UsersModel;
import com.assoftek.splashscreen.databinding.ActivityOtpVerifyBinding;
import com.assoftek.splashscreen.databinding.ActivityUserDetailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class User_Detail extends AppCompatActivity {

    ActivityUserDetailBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    static boolean male=false, female=false;
    static String gender="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final String number=getIntent().getStringExtra("number");
        final String email= getIntent().getStringExtra("email");
        final String password= getIntent().getStringExtra("password");
        final String countryCode= getIntent().getStringExtra("countryCode");
        final String name= binding.reference.getText().toString();
        final String  state= binding.state.getText().toString();
        final String  hometown= binding.Hometown.getText().toString();
        final String  dob= binding.dateOfBirth.getText().toString();


        binding.male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(male)
                {
                    binding.male.setBackgroundDrawable( getResources().getDrawable(R.drawable.greysquare) );
                    male=false;
                }
                else
                {
                    binding.male.setBackgroundDrawable( getResources().getDrawable(R.drawable.greengrey) );
                    male=true;
                    if(female) binding.female.setBackgroundDrawable( getResources().getDrawable(R.drawable.greysquare) );
                    female=false;
                }
            }
        });

        binding.female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(female)
                {
                    binding.female.setBackgroundDrawable( getResources().getDrawable(R.drawable.greysquare) );
                    female=false;
                }
                else
                {
                    binding.female.setBackgroundDrawable( getResources().getDrawable(R.drawable.greengrey) );
                    female=true;
                    if(male) binding.female.setBackgroundDrawable( getResources().getDrawable(R.drawable.greysquare) );
                    male=false;
                }
            }
        });

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(User_Detail.this);
        progressDialog.setTitle("Creating Account");   // loading while creating account.
        progressDialog.setMessage("We're creating your account..");


        binding.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                if(male)
                {
                  gender+="male";
                }
                if(female)
                {
                  gender+="female";
                }

                if(male==false && female==false)
                {
                    Toast.makeText(User_Detail.this, "Select Gender!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(binding.state.getText().toString().isEmpty())
                {
                    binding.state.setError("Enter your state!!");
                    return;
                }

                if(binding.Hometown.getText().toString().isEmpty())
                {
                    binding.Hometown.setError("Enter your hometown!!");
                    return;
                }

                if(binding.dateOfBirth.getText().toString().isEmpty())
                {
                    binding.dateOfBirth.setError("Enter your date of birth!!");
                    return;
                }

                // taking email and password to create new user
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();    // after account created dialog will disappear
                        // main work

                        if(task.isSuccessful())
                        {
                            UsersModel user= new UsersModel(name,email, number,countryCode, password,state, hometown, gender,dob);         // taking username, email, password in database.
                            String id=task.getResult().getUser().getUid();

                            database.getReference().child("Users").child(id).setValue(user);   // it will create a user node in firebase containing userId / mail , password, username
                            Intent intent=new Intent(User_Detail.this, DashboardActivity.class);
                            startActivity(intent);
                            Toast.makeText(User_Detail.this,"User Created Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(User_Detail.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

       

    }
}