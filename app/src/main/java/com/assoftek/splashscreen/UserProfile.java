package com.assoftek.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.assoftek.splashscreen.SignUp.User_Detail;
import com.assoftek.splashscreen.databinding.ActivityUserProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;

public class UserProfile extends AppCompatActivity {

    ActivityUserProfileBinding binding;
    int day1, month1, year1;
    int day2, month2, year2;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        final Calendar calendar1=Calendar.getInstance();
        year1=calendar1.get(Calendar.YEAR);
        month1=calendar1.get(Calendar.MONTH);
        day1=calendar1.get(Calendar.DAY_OF_MONTH);

        final Calendar calendar2=Calendar.getInstance();
        year2=calendar2.get(Calendar.YEAR);
        month2=calendar2.get(Calendar.MONTH);
        day2=calendar2.get(Calendar.DAY_OF_MONTH);

        binding.completion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog=new DatePickerDialog(UserProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year1, int month1, int day1) {
                        month1=month1+1;
                        String date=day1+" / "+month1+" / "+year1;
                        binding.completion1.setText(date);
                    }
                }, year1, month1,day1);
                datePickerDialog.show();
            }
        });


        binding.completion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog=new DatePickerDialog(UserProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year2, int month2, int day2) {
                        month2=month2+1;
                        String date=day2+" / "+month2+" / "+year2;
                        binding.completion2.setText(date);
                    }
                }, year2, month2,day2);
                datePickerDialog.show();
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String , Object> obj = new HashMap<>();
                obj.put("graduationCourse",binding.courseName1);
                obj.put("graduationUniversity",binding.universityName1);
                obj.put("graduationMajors",binding.majors1);
                obj.put("graduationDate",binding.completion1);

                obj.put("postGraduationCourse",binding.courseName2);
                obj.put("postGraduationUniversity",binding.universityName2);
                obj.put("postGraduationMajors",binding.majors2);
                obj.put("postGraduationDate",binding.completion2);

                obj.put("aadharCard",binding.aadharCard);
                obj.put("panCard",binding.panCard);

                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(obj);

                Toast.makeText(UserProfile.this, "Profile Details Submitted",
                        Toast.LENGTH_SHORT).show();
            }
        });

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UsersModel users = snapshot.getValue(UsersModel.class);

                        binding.courseName1.setText(users.getGraduationCourse());
                        binding.universityName1.setText(users.getGraduationUniversity());
                        binding.majors1.setText(users.getGraduationMajors());
                        binding.completion1.setText(users.getGraduationDate());

                        binding.courseName2.setText(users.getPostGraduationCourse());
                        binding.universityName2.setText(users.getPostGraduationUniversity());
                        binding.majors2.setText(users.getPostGraduationMajors());
                        binding.completion2.setText(users.getPostGraduationDate());

                        binding.panCard.setText(users.getPanCard());
                        binding.aadharCard.setText(users.getAadharCard());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}