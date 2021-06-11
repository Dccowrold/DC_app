package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class UserDetails extends AppCompatActivity {

    TextView name, email, FixedIncome, OtherIncome, MedianIncome, TotalExpenses, SavingIncome, Age, RetirementAge, AssetClass, Time, Return, Risk, FinancialRisk, Standard, RiskWillingness, Liquidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        FixedIncome = findViewById(R.id.fixedIncome);
        OtherIncome = findViewById(R.id.otherIncome);
        MedianIncome = findViewById(R.id.medianIncome);
        TotalExpenses = findViewById(R.id.totalExpences);
        SavingIncome = findViewById(R.id.savingIncome);
        Age = findViewById(R.id.age);
        RetirementAge = findViewById(R.id.retirementage);
        AssetClass = findViewById(R.id.assetclass);
        Return = findViewById(R.id.Return);
        Risk = findViewById(R.id.risk);
        Time = findViewById(R.id.Time);
        FinancialRisk = findViewById(R.id.Financial_Risk);
        Standard = findViewById(R.id.standard);
        RiskWillingness = findViewById(R.id.RiskWillingness);
        Liquidity = findViewById(R.id.Liquidity);
    }

}