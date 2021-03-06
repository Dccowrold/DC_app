package com.assoftek.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.db.AppDatabase;
import com.assoftek.splashscreen.db.Details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailsActivity extends AppCompatActivity {

    EditText name, email, FixedIncome, OtherIncome, MedianIncome, TotalExpenses, SavingIncome, Age, RetirementAge, AssetClass, Time;

    String Return_s,Risk_s,FinancialRisk_s,Standard_s,RiskWillingness_s,Liquidity_s;
    AutoCompleteTextView Return, Risk, FinancialRisk, Standard, RiskWillingness, Liquidity;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        Return.setAdapter(adapter);
        FinancialRisk.setAdapter(adapter);
        Standard.setAdapter(adapter);
        RiskWillingness.setAdapter(adapter);
        Liquidity.setAdapter(adapter);
        Risk.setAdapter(adapter);
        Return_s="NULL";
        FinancialRisk_s="NULL";
        Risk_s="NULL";
        Standard_s="NULL";
        RiskWillingness_s="NULL";
        Liquidity_s="NULL";

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Return.showDropDown();
            }
        });
        Return.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Return_s=values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Return_s="NULL";
            }
        });
        FinancialRisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinancialRisk.showDropDown();
            }
        });
        FinancialRisk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FinancialRisk_s=values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                FinancialRisk_s="NULL";
            }
        });
        Risk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Risk.showDropDown();
            }
        });
        Risk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Risk_s=values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Risk_s="NULL";
            }
        });
        Standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Standard.showDropDown();
            }
        });
        Standard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Standard_s=values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Standard_s="NULL";
            }
        });
        RiskWillingness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RiskWillingness.showDropDown();
            }
        });
        RiskWillingness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RiskWillingness_s=values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                RiskWillingness_s="NULL";
            }
        });
        Liquidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Liquidity.showDropDown();
            }
        });
        Liquidity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Liquidity_s=values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Liquidity_s="NULL";
            }
        });
    }


    private static final String[] values = new String[]{"High", "Medium", "Low"};

    public void saveData() {
        Call<dataResponse> dataResponseCall = RetrofitClient.getService().
                uploadData(new dataRequest(name.getText().toString(),
                        email.getText().toString(),
                        FixedIncome.getText().toString(),
                        OtherIncome.getText().toString(),
                        MedianIncome.getText().toString(),
                        TotalExpenses.getText().toString(),
                        SavingIncome.getText().toString(),
                        Age.getText().toString(),
                        RetirementAge.getText().toString(),
                        AssetClass.getText().toString(),
                        Return_s,
                        Risk_s,
                        Time.getText().toString(),
                        FinancialRisk_s,
                        Standard_s,
                        RiskWillingness_s,
                        Liquidity_s));
        dataResponseCall.enqueue(new Callback<dataResponse>() {
            @Override
            public void onResponse(Call<dataResponse> call, Response<dataResponse> response) {
                if (response.isSuccessful()) {
                    //Storing to Room Database
                    saveDetails(email.getText().toString(),
                            name.getText().toString(),
                            FixedIncome.getText().toString(),
                            OtherIncome.getText().toString(),
                            MedianIncome.getText().toString(),
                            TotalExpenses.getText().toString(),
                            SavingIncome.getText().toString(),
                            Age.getText().toString(),
                            RetirementAge.getText().toString(),
                            AssetClass.getText().toString(),
                            Return_s,
                            Risk_s,
                            Time.getText().toString(),
                            FinancialRisk_s,
                            Standard_s,
                            RiskWillingness_s,
                            Liquidity_s);
                    Log.d("TAG",response.body().toString());

                    Toast.makeText(detailsActivity.this, "data saved Successful", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(detailsActivity.this,DashboardActivity.class);
                    intent.putExtra("username",getIntent().getStringExtra("username"));
                    intent.putExtra("emailID",getIntent().getStringExtra("emailID"));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(detailsActivity.this, "data uploading Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<dataResponse> call, Throwable t) {
                Toast.makeText(detailsActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveDetails(String email, String name, String FixedIncome, String OtherIncome, String MedianIncome,
                             String TotalExpenses, String SavingIncome, String Age, String RetirementAge,
                             String AssetClass, String Return, String RiskString, String Time, String FinancialRisk,
                             String Standard, String RiskWillingness, String Liquidity) {

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Details details = new Details();
        details.email = email;
        details.name = name;
        details.FixedIncome = FixedIncome;
        details.OtherIncome = OtherIncome;
        details.MedianIncome = MedianIncome;
        details.TotalExpenses = TotalExpenses;
        details.SavingIncome = SavingIncome;
        details.Age = Age;
        details.RetirementAge = RetirementAge;
        details.AssetClass = AssetClass;
        details.Return = Return;
        details.Risk = RiskString;
        details.Time = Time;
        details.FinancialRisk = FinancialRisk;
        details.Standard = Standard;
        details.RiskWillingness = RiskWillingness;
        details.Liquidity = Liquidity;

        db.detailsDao().insertDetails(details);
        Details currentUserData = db.detailsDao().findUserFromEmail(email);
        Log.d("Email",currentUserData.name);
    }

}