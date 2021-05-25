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
import android.widget.TextView;
import android.widget.Toast;

public class CreatePaymentPin extends AppCompatActivity {

    Button btn_verify;
    SharedPreferences sharedPreferences;
    TextView view, textView;
    private EditText input1, input2, input3, input4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_payment_pin);

        btn_verify = findViewById(R.id.btn_verify);
        input1 = findViewById(R.id.inputCode1);
        input2 = findViewById(R.id.inputCode2);
        input3 = findViewById(R.id.inputCode3);
        input4 = findViewById(R.id.inputCode4);

        setupOTPInputs();
        sharedPreferences = getSharedPreferences("myPref", 0);

        //readpref();

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int one = Integer.parseInt(input1.getText().toString());
                int two = Integer.parseInt(input2.getText().toString());
                int three = Integer.parseInt(input3.getText().toString());
                int four = Integer.parseInt(input4.getText().toString());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("1", one);
                editor.putInt("2", two);
                editor.putInt("3", three);
                editor.putInt("4", four);
                editor.commit();

                Intent intent = new Intent(CreatePaymentPin.this, PinVerification.class);
                startActivity(intent);


                Toast.makeText(CreatePaymentPin.this, "Succesfull", Toast.LENGTH_SHORT).show();
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
        input1.setText(String.valueOf(sharedPreferences.getInt("1",-1)));
        input2.setText(String.valueOf(sharedPreferences.getInt("2",-1)));
        input3.setText(String.valueOf(sharedPreferences.getInt("3",-1)));
        input4.setText(String.valueOf( sharedPreferences.getInt("4",-1)));

    }

}