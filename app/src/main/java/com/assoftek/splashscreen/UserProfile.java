package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.assoftek.splashscreen.databinding.ActivityUserProfileBinding;

public class UserProfile extends AppCompatActivity {

    ActivityUserProfileBinding binding;
    boolean graduation=false, postGraduation=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.graduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(graduation==true)
                {
                    graduation=false;
                    binding.graduationlayout.setVisibility(View.INVISIBLE);
                }
                else
                {
                    graduation=true;
                    binding.graduationlayout.setVisibility(View.VISIBLE);
                    binding.postGraduationLayout.setVisibility(View.INVISIBLE);
                    postGraduation=false;
                }
            }
        });

        binding.postGraduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(postGraduation==true)
                {
                    postGraduation=false;
                    binding.postGraduationLayout.setVisibility(View.INVISIBLE);
                }
                else
                {
                    postGraduation=true;
                    binding.postGraduationLayout.setVisibility(View.VISIBLE);
                    binding.graduationlayout.setVisibility(View.INVISIBLE);
                    graduation=false;
                }
            }
        });
    }
}