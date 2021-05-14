package com.assoftek.splashscreen.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.UsersModel;
import com.assoftek.splashscreen.databinding.ActivityUserDetailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_Detail extends AppCompatActivity {

    ActivityUserDetailBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    static boolean male = false, female = false;
    static String gender = "";
    int day, month, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        final Calendar calendar = Calendar.getInstance();

        final String number = getIntent().getStringExtra("number");
        final String countryCode = getIntent().getStringExtra("countryCode");

        final String email = getIntent().getStringExtra("email");
        final String password = getIntent().getStringExtra("password");

        final String name = binding.reference.getText().toString();
        final String state = binding.state.getText().toString();
        final String pincode = binding.pincode.getText().toString();


        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        binding.dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(User_Detail.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + " / " + month + " / " + year;
                        binding.dateOfBirth.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        final String dob = day+"/"+ month+"/"+year;

        binding.male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male) {
                    binding.male.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundblue));
                    male = false;
                } else {
                    binding.male.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundgbluebutton));
                    male = true;
                    if (female)
                        binding.female.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundblue));
                    female = false;
                }
            }
        });


        binding.female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (female) {
                    binding.female.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundblue));
                    female = false;
                } else {
                    binding.female.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundgbluebutton));
                    female = true;
                    if (male)
                        binding.female.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundblue));
                    male = false;
                }
            }
        });
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(User_Detail.this);
        progressDialog.setTitle("Creating Account");   // loading while creating account.
        progressDialog.setMessage("We're creating your account..");


        binding.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

                if (!male && !female) {
                    Toast.makeText(User_Detail.this, "Select Gender!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.reference.getText().toString().isEmpty()) {
                    binding.reference.setError("Enter your name!!");
                    return;
                }

                if (binding.state.getText().toString().isEmpty()) {
                    binding.state.setError("Enter your state!!");
                    return;
                }

                if (binding.pincode.getText().toString().isEmpty()) {
                    binding.pincode.setError("Enter your Pincode!!");
                    return;
                }

                if (binding.pincode.getText().toString().length()>6) {
                    binding.pincode.setError("Enter your Pincode in 6 characters only!!");
                    return;
                }

                if (binding.dateOfBirth.getText().toString().isEmpty()) {
                    binding.dateOfBirth.setError("Enter your Birth date!!");
                    return;
                }

                if (male) {
                    gender += "male";
                }
                if (female) {
                    gender += "female";
                }


                UsersModel user = new UsersModel(name, email, number, countryCode, password, state, pincode, gender, dob);         // taking username, email, password in database.
                String id = getIntent().getStringExtra("uuid");

                database.getReference().child("Users").child(id).setValue(user);
                // it will create a user node in firebase containing userId / mail , password, username
                Intent intent = new Intent(User_Detail.this, DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(User_Detail.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
