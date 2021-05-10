package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.assoftek.splashscreen.SignUp.User_Detail;
import com.assoftek.splashscreen.databinding.ActivityUserProfileBinding;

import java.util.Calendar;

public class UserProfile extends AppCompatActivity {

    ActivityUserProfileBinding binding;
    int day1, month1, year1;
    int day2, month2, year2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

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

    }
}