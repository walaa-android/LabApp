package com.example.labmedixapplication.model;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labmedixapplication.R;
import com.example.labmedixapplication.activities.AddReminderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MonthDayPickerDialog extends DialogFragment {
  //  TextView text_view_cancle , text_view_ok;

    TextView  time_reminder;
        private DatePickerDialog.OnDateSetListener listener;
        private int daysOfMonth = 31;
    String timeDate;


    private NumberPicker monthPicker;
        private NumberPicker yearPicker;
        private NumberPicker dayPicker;
        DatabaseReference informationRefReminder;


    private Calendar cal = Calendar.getInstance();

        public static final String MONTH_KEY = "monthValue";
        public static final String DAY_KEY = "dayValue";
        public static final String YEAR_KEY = "yearValue";

        int monthVal = -1 , dayVal = -1 , yearVal =-1 ;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
          //  text_view_cancle = getActivity().findViewById(R.id.text_view_cancle);
          //  text_view_ok =getActivity().findViewById(R.id.text_view_ok);

            time_reminder= getActivity().findViewById(R.id.time_reminder);


            Bundle extras = getArguments();
            if(extras != null) {
                monthVal = extras.getInt(MONTH_KEY, -1);
                dayVal = extras.getInt(DAY_KEY, -1);
                yearVal = extras.getInt(YEAR_KEY, -1);

            }

        }

        public static MonthDayPickerDialog newInstance(int monthIndex , int daysIndex , int yearIndex) {
            MonthDayPickerDialog f = new MonthDayPickerDialog();
            Bundle args = new Bundle();
            args.putInt(MONTH_KEY, monthIndex);
            args.putInt(DAY_KEY, daysIndex);
            args.putInt(YEAR_KEY, yearIndex);
            f.setArguments(args);

            return f;
        }


        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            //getDialog().setTitle("Add Birthday");

            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View dialog = inflater.inflate(R.layout.add_time_dialog_reminder, null);
            monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
          yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);
            dayPicker = (NumberPicker) dialog.findViewById(R.id.picker_day);

            monthPicker.setMinValue(1);
            monthPicker.setMaxValue(12);


            if(monthVal != -1)// && (monthVal > 0 && monthVal < 13))
                monthPicker.setValue(monthVal);
            else
                monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

            monthPicker.setDisplayedValues(new String[]{"1","2","3","4","5","6","7",
                    "8","9","10","11","12"});

             dayPicker.setDisplayedValues(new String[]{"AM" , "PM"});
         dayPicker.setMinValue(1);
              dayPicker.setMaxValue(2);

         if(dayVal != -1)
              dayPicker.setValue(dayVal);
              monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {


                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    switch (newVal){

                        case 1:case 2:case 3:
                        case 4:case 5:case 6:
                        case 7:case 8:case 9:
                        case 10:case 11:case 12:
                            daysOfMonth = 60;
                            dayPicker.setDisplayedValues(new String[]{"AM" , "PM"});
                            break;
                    }

                }

                String periodsTime =  monthPicker.getValue()+ " ";
                     //   Toast.makeText(this, "" + hour + minute + periods, Toast.LENGTH_LONG).show();





            });

            int maxYear = 60;
            final int minYear =-1;
            int arraySize = maxYear - minYear;

            String[] tempArray = new String[arraySize];
            tempArray[0] = "0";
            int tempYear = minYear+1;

            for(int i=0 ; i < arraySize; i++){
                if(i != 0){
                    tempArray[i] = " " + tempYear + "";
                }
                tempYear++;
            }
            Log.i("", "onCreateDialog: " + tempArray.length);
            yearPicker.setMinValue(minYear+1);
            yearPicker.setMaxValue(maxYear);
            yearPicker.setDisplayedValues(tempArray);

            if(yearVal != -1)
                yearPicker.setValue(yearVal);
            else
                yearPicker.setValue(tempYear -1);

            yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    try {
                        if(isLeapYear(picker.getValue())){
                         daysOfMonth = 60;
              //              dayPicker.setMaxValue(daysOfMonth);
                            dayPicker.setDisplayedValues(new String[]{"AM" , "PM"});

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


            builder.setView(dialog);
            return builder.create();
        }




    public static boolean isLeapYear(int year) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
        }


 /*  private void updateData( String timePeriod) {


        informationRefReminder = FirebaseDatabase.getInstance().getReference("userInformationReminder")
                .child(FirebaseAuth.getInstance().getUid());
        informationRefReminder.child("Reminder").setValue(timeDate);

    }*/

}
