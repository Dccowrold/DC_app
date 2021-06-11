package com.assoftek.splashscreen.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.UsersModel;
import com.assoftek.splashscreen.databinding.ActivityUserProfileBinding;
import com.assoftek.splashscreen.db.AppDatabase;
import com.assoftek.splashscreen.db.Details;
import com.assoftek.splashscreen.db.User;
import com.assoftek.splashscreen.db.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class UserProfileActivity extends AppCompatActivity {

    ActivityUserProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        binding.goToDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfileActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        binding.goToEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserProfileActivity.this, UserEducationActivity.class);
                startActivity(intent);
            }
        });


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {     // taking username and status
            @Override
            public void onClick(View view) {
                String name=binding.name.getText().toString();
                String state=binding.state.getText().toString();
                String pincode=binding.pincode.getText().toString();
                String Dob=binding.dateOfBirth.getText().toString();
                String Phone_no =binding.number.getText().toString();

                AppDatabase db = AppDatabase.getDbInstance(UserProfileActivity.this);

                UserProfile userProfile= new UserProfile();
                userProfile.name=name;
                userProfile.state=state;
                userProfile.pincode=pincode;
                userProfile.Dob=Dob;
                userProfile.Phone_no=Phone_no;

                db.userProfileDao().insertProfile(userProfile);


                HashMap<String , Object> obj = new HashMap<>();
                obj.put("userName", name);
                obj.put("state",state );
                obj.put("pinCode",pincode );

                database.getReference().child("Users").child(auth.getUid())
                        .updateChildren(obj);

                Toast.makeText(UserProfileActivity.this, "Profile Updated",
                        Toast.LENGTH_SHORT).show();
            }
        });



        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UsersModel users=snapshot.getValue(UsersModel.class);

                        binding.name.setText(users.getUserName());
                        binding.dateOfBirth.setText(users.getDob());
                        binding.number.setText(users.getPhoneNumber());
                        binding.state.setText(users.getState());
                        binding.pincode.setText(users.getPinCode());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}