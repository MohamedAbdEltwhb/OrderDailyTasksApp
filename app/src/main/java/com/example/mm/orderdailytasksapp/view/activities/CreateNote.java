package com.example.mm.orderdailytasksapp.view.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mm.orderdailytasksapp.AlarmReceiver;
import com.example.mm.orderdailytasksapp.R;
import com.example.mm.orderdailytasksapp.models.SQLiteHelper.DbHelper;
import com.example.mm.orderdailytasksapp.view.activities.MainActivity;
import com.example.mm.orderdailytasksapp.view.adaptor.MainAdaptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateNote extends AppCompatActivity {

    private DbHelper mDbHelper;
    private EditText mTitleText;
    private EditText mDescriptionText;
    private DatePicker pickerDate;
    private TimePicker pickerTime;
    private TextView time;
    private TextView date;
    private CheckBox checkBoxAlarm;

    private MainAdaptor mainAdaptor;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        mainAdaptor = new MainAdaptor(getApplicationContext(), cursor);
        mDbHelper = new DbHelper(this);

        mTitleText = findViewById(R.id.txttitle);
        mDescriptionText = findViewById(R.id.description);
        pickerDate = findViewById(R.id.datePicker);
        pickerTime = findViewById(R.id.timePicker);
        time = findViewById(R.id.txtTime);
        date = findViewById(R.id.txtDate);
        checkBoxAlarm = findViewById(R.id.checkBox);

        pickerDate.setVisibility(View.INVISIBLE);
        pickerTime.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);

        checkBoxAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    pickerDate.setVisibility(View.VISIBLE);
                    pickerTime.setVisibility(View.VISIBLE);
                    time.setVisibility(View.VISIBLE);
                    date.setVisibility(View.VISIBLE);
                }
                else{
                    pickerDate.setVisibility(View.INVISIBLE);
                    pickerTime.setVisibility(View.INVISIBLE);
                    time.setVisibility(View.INVISIBLE);
                    date.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MainActivity.class);
        startActivity(setIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                addTaskInfo();
            break;
        }
        return true;
    }

    private void addTaskInfo(){
        String title = mTitleText.getText().toString();
        String detail = mDescriptionText.getText().toString();
        String timeString = null;
        String dateString = null;

        if (checkBoxAlarm.isChecked()){
            Calendar calender = Calendar.getInstance();
            calender.clear();
            calender.set(Calendar.MONTH, pickerDate.getMonth());
            calender.set(Calendar.DAY_OF_MONTH, pickerDate.getDayOfMonth());
            calender.set(Calendar.YEAR, pickerDate.getYear());
            calender.set(Calendar.HOUR, pickerTime.getCurrentHour());
            calender.set(Calendar.MINUTE, pickerTime.getCurrentMinute());
            calender.set(Calendar.SECOND, 00);

            SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.hour_minutes));
            timeString = formatter.format(new Date(calender.getTimeInMillis()));

            SimpleDateFormat dateFormatter = new SimpleDateFormat(getString(R.string.dateformate));
            dateString = dateFormatter.format(new Date(calender.getTimeInMillis()));

            AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);

            String alertTitle = mTitleText.getText().toString();
            intent.putExtra(getString(R.string.alert_title), alertTitle);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            alarmMgr.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pendingIntent);
        }
        Long id = mDbHelper.addNewTask(title, detail, timeString, dateString);
        showToast(String.valueOf(id));

        mainAdaptor.swapCursor(cursor);

        Intent openMainScreen = new Intent(this, MainActivity.class);
        openMainScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openMainScreen);
    }

    void showToast(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
