package com.huseyin.exemple.ciger_takip.Confirm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Huseyin on 4.01.2017.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView txtDate;

    public DateDialog(View view){
        txtDate = (TextView) view;
    }

    public Dialog   onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_WEEK);
        return new DatePickerDialog(getActivity(), this, day, month, year);
    }

    public void onDateSet(DatePicker view,int day, int month, int year){
        String date = year + "." + (month + 1) + "." + day;
        txtDate.setText(date);

    }

}
