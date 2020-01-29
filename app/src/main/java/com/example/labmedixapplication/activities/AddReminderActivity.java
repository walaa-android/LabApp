package com.example.labmedixapplication.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.fragment.MyDatePickerFragment;
import com.example.labmedixapplication.fragment.MyDatePickerReminderFragment;
import com.example.labmedixapplication.fragment.ProfileFragment;
import com.example.labmedixapplication.model.MonthDayPickerDialog;
import com.google.android.gms.common.internal.Constants;
import com.google.common.base.MoreObjects;

import java.util.Calendar;
import java.util.Date;

import static com.example.labmedixapplication.model.MonthDayPickerDialog.newInstance;

public class AddReminderActivity extends AppCompatActivity {

    TextView day_reminder ;
    String day ;
    TextView time_reminder ;
    EditText edit_text_reminder;
    DatePicker dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        day_reminder = findViewById(R.id.day_reminder);
        time_reminder= findViewById(R.id.time_reminder);
        edit_text_reminder=findViewById(R.id.edit_text_reminder);

      //  if (!TextUtils.isEmpty((CharSequence) edit_text_reminder) ){
            String time = edit_text_reminder.getText().toString();
            Bundle bundleTime = new Bundle();
            bundleTime.putString("Time",time);

      //  }




            dp = findViewById(R.id.datePicker);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Toast.makeText(AddReminderActivity.this, "picked date is " + view.getDayOfMonth() +
                            " / " + (view.getMonth()+1) +
                            " / " + view.getYear(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void back(View view) {

        getSupportFragmentManager().beginTransaction().replace(R.id. add_con , new ProfileFragment()).commit();

    }


    public void save_reminder(View view) {
        Intent intent = new Intent(AddReminderActivity.this , ReminderListActivity.class);
        startActivity(intent);
    }

    public void add_date(View view) {
        DialogFragment newFragment = new MyDatePickerReminderFragment();
       newFragment.show(getSupportFragmentManager(), "date picker");
    }

    public void cancled(View view) {

        Intent intent = new Intent(getApplicationContext(), AddReminderActivity .class);
        startActivity(intent);
    }



    public void add_reminder(View view) {

        NumberPicker picker_month = findViewById(R.id.picker_month);
        NumberPicker picker_year = findViewById(R.id.picker_year);
        NumberPicker picker_day = findViewById(R.id.picker_day);
     /*  int hour=  picker_month.getValue();
        int minute =  picker_year.getValue();
        int periods = picker_day.getValue();
        //time_reminder .setText("time " , hour, minute, periods);


        Toast.makeText(this, "" + hour + minute + periods, Toast.LENGTH_LONG).show();*/

      //  String periodsTime =  monthPicker.getValue()+ " ";

    }

    public void add_time_reminder(View view) {
        MonthDayPickerDialog pd = new MonthDayPickerDialog();
        pd.show(getSupportFragmentManager(), "MonthDayPickerDialog");
      //  time_reminder.setText(pd.getText().toString());
    }


    public class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        private int year;
        private int month;
        private int day;


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public Date getDateFromDatePicker(){
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            return calendar.getTime();
        }


    }



}
