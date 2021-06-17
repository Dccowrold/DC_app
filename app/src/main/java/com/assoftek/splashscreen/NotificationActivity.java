package com.assoftek.splashscreen;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        getSupportActionBar().hide();


        findViewById(R.id.setbtn).setOnClickListener(this);
        findViewById(R.id.cancelbtn).setOnClickListener(this);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this , DashboardActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {

        EditText editText = findViewById(R.id.message);
        TimePicker timePicker = findViewById(R.id.timePicker);

        Intent intent = new Intent(NotificationActivity.this , AlarmReciever.class);
        intent.putExtra("notificationId" , notificationId);
        intent.putExtra("message" , editText.getText().toString());
        PendingIntent alarmIntent = PendingIntent.getBroadcast(
                NotificationActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
        );
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setbtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //Create Time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime = startTime.getTimeInMillis();
                //Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmStartTime,AlarmManager.INTERVAL_DAY,alarmIntent);
                Toast.makeText(this, "Notification Set!", Toast.LENGTH_SHORT).show();
                break;


            case R.id.cancelbtn:
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}