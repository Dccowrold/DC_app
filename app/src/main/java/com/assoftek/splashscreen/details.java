package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class details extends AppCompatActivity {

    EditText name,email,FixedIncome,OtherIncome,MedianIncome,TotalExpenses,SavingIncome,Age,RetirementAge, AssetClass,Return,Risk,Time,FinancialRisk,Standard,RiskWillingness,Liquidity;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        FixedIncome=findViewById(R.id.fixedIncome);
        OtherIncome=findViewById(R.id.otherIncome);
        MedianIncome=findViewById(R.id.medianIncome);
        TotalExpenses=findViewById(R.id.totalExpences);
        SavingIncome=findViewById(R.id.savingIncome);
        Age=findViewById(R.id.age);
        RetirementAge=findViewById(R.id.retirementage);
        AssetClass=findViewById(R.id.assetclass);
        Return=findViewById(R.id.Return);
        Risk=findViewById(R.id.risk);
        Time=findViewById(R.id.Time);
        FinancialRisk=findViewById(R.id.Financial_Risk);
        Standard=findViewById(R.id.standard);
        RiskWillingness=findViewById(R.id.RiskWillingness);
        Liquidity=findViewById(R.id.Liquidity);
        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    public void saveData(){
        Call<dataResponse> dataResponseCall= RetrofitClient.getService().
                uploadData(name.getText().toString(),
                email.getText().toString(),
                FixedIncome.getText().toString(),
                OtherIncome.getText().toString(),
                MedianIncome.getText().toString(),
                TotalExpenses.getText().toString(),
                SavingIncome.getText().toString(),
                Age.getText().toString(),
                RetirementAge.getText().toString(),
                AssetClass.getText().toString(),
                Return.getText().toString(),
                Risk.getText().toString(),
                Time.getText().toString(),
                FinancialRisk.getText().toString(),
                Standard.getText().toString(),
                RiskWillingness.getText().toString(),
                Liquidity.getText().toString());
        dataResponseCall.enqueue(new Callback<dataResponse>() {
            @Override
            public void onResponse(Call<dataResponse> call, Response<dataResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(details.this,"data saved Successful", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(details.this,"data uploading Failed", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<dataResponse> call, Throwable t) {
                Toast.makeText(details.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}