package com.assoftek.splashscreen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.db.AppDatabase;
import com.assoftek.splashscreen.db.Details;

import java.lang.ref.WeakReference;

public class UserDetails extends AppCompatActivity {

    private TextView name, email, FixedIncome, OtherIncome, MedianIncome, TotalExpenses, SavingIncome, Age, RetirementAge, AssetClass, Time, Return, Risk, FinancialRisk, Standard, RiskWillingness, Liquidity;

    private String emailFromExtra;

    private AppDatabase db;

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

        db = AppDatabase.getDbInstance(this.getApplicationContext());

        new RetrieveTask(this).execute();

    }

    private class RetrieveTask extends AsyncTask<Void, Void, Details> {

        private WeakReference<UserDetails> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(UserDetails context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Details doInBackground(Void... voids) {
            if (activityReference.get() != null)
                return activityReference.get().db.detailsDao().findUserFromEmail(emailFromExtra);
            else
                return null;
        }

        @Override
        protected void onPostExecute(Details currentUserData) {
            name.setText(currentUserData.name);
            email.setText(emailFromExtra);
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

}