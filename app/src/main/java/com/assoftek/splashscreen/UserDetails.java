package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.assoftek.splashscreen.db.AppDatabase;
import com.assoftek.splashscreen.db.Details;

public class UserDetails extends AppCompatActivity {

    TextView name, email, FixedIncome, OtherIncome, MedianIncome, TotalExpenses, SavingIncome, Age, RetirementAge, AssetClass, Time, Return, Risk, FinancialRisk, Standard, RiskWillingness, Liquidity;

    String emailFromExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //Email of the current user received from calling activity
        emailFromExtra = getIntent().getStringExtra("emailID");

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

        //Populating the textviews
        setFields(emailFromExtra);
    }

    private void setFields(String emailID) {

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Details currentUserData = db.detailsDao().findUserFromEmail(emailID);

        name.setText(currentUserData.name);
        email.setText(emailID);
        FixedIncome.setText(currentUserData.FixedIncome);
        OtherIncome.setText(currentUserData.OtherIncome);
        MedianIncome.setText(currentUserData.MedianIncome);
        TotalExpenses.setText(currentUserData.TotalExpenses);
        SavingIncome.setText(currentUserData.SavingIncome);
        Age.setText(currentUserData.Age);
        RetirementAge.setText(currentUserData.RetirementAge);
        AssetClass.setText(currentUserData.AssetClass);
        Return.setText(currentUserData.Return);
        Risk.setText(currentUserData.Risk);
        Time.setText(currentUserData.Time);
        FinancialRisk.setText(currentUserData.FinancialRisk);
        Standard.setText(currentUserData.Standard);
        RiskWillingness.setText(currentUserData.RiskWillingness);
        Liquidity.setText(currentUserData.Liquidity);

    }

}