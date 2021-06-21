package com.assoftek.splashscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class notification_screen extends AppCompatActivity {

//    SwitchCompat switchCompat;
//    TextView settime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);

//        switchCompat = findViewById(R.id.switchCompat);
//        settime = findViewById(R.id.settime);
//
//
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(switchCompat.isChecked()){
//                    Calendar mcurrentTime = Calendar.getInstance();
//                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                    int minute = mcurrentTime.get(Calendar.MINUTE);
//                    TimePickerDialog mTimePicker;
//                    mTimePicker = new TimePickerDialog(notification_screen.this, new TimePickerDialog.OnTimeSetListener() {
//                        @Override
//                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                            settime.setText( selectedHour + ":" + selectedMinute);
//                        }
//                    }, hour, minute, true);//Yes 24 hour time
//                    mTimePicker.setTitle("Select Time");
//                    mTimePicker.show();
//                }else{
//                    Toast.makeText(notification_screen.this, "Switch on to set reminder", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}