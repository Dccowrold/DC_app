package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PinVerification extends AppCompatActivity {

    Button btn_verify;
    SharedPreferences sharedPreferences;
    private EditText input1, input2, input3, input4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verification);

        btn_verify = findViewById(R.id.proceed);
        input1 = findViewById(R.id.iCode1);
        input2 = findViewById(R.id.iCode2);
        input3 = findViewById(R.id.iCode3);
        input4 = findViewById(R.id.iCode4);

        setupOTPInputs();

        sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int onev = Integer.parseInt(input1.getText().toString());
                int twov = Integer.parseInt(input2.getText().toString());
                int threev = Integer.parseInt(input3.getText().toString());
                int fourv = Integer.parseInt(input4.getText().toString());

                int pin1 = sharedPreferences.getInt("1", -2);
                int pin2 = sharedPreferences.getInt("2", 0);
                int pin3 = sharedPreferences.getInt("3", 1);
                int pin4 = sharedPreferences.getInt("4", -1);

                if (onev == pin1 && twov == pin2 && threev == pin3 && fourv == pin4) {
//                    Intent intent = new Intent(PinVerification.this , PaymentPage.class);
//                    startActivity(intent);
                    Toast.makeText(PinVerification.this, "verified successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PinVerification.this, "Incorrect Pin", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setupOTPInputs() {
        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void readpref() {

        input1.setText(String.valueOf(sharedPreferences.getInt("1", -1)));

        input2.setText(String.valueOf(sharedPreferences.getInt("2", -1)));

        input3.setText(String.valueOf(sharedPreferences.getInt("3", -1)));

        input4.setText(String.valueOf(sharedPreferences.getInt("4", -1)));

    }

}