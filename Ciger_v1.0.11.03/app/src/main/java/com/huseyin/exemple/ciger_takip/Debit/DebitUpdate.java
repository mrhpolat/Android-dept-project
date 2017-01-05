package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.R;
import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

/**
 * Created by Huseyin on 5.01.2017.
 */

public class DebitUpdate extends AppCompatActivity {

    Intent debitmain;
    Spinner debitName;
    EditText DebitDesc;
    TextView txtdebit, txtcredit;
    private int id;
    DataManger db;
    Cursor c;

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_contact, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handxle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idd = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(idd==R.id.newContactSave){
            debitmain =new Intent(this,DebitShow.class);

            db.updateDebit(id, debitName.getSelectedItem().toString(), txtdebit.getText().toString(),txtcredit.getText().toString(), DebitDesc.getText().toString());
            debitmain.putExtra("id",id);
            Log.i("debt update id : ","+"+id);

            startActivity(debitmain);
        }
        else if (idd==R.id.newContactCancel){
            debitmain =new Intent(this,DebitShow.class);

            debitmain.putExtra("id",id);
            startActivity(debitmain);
        }

        return super.onOptionsItemSelected(item);
    }


    protected void onCreate(Bundle saveInstaceState) {
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_new_debit);

        debitName = (Spinner) findViewById(R.id.spinner_debit);
        DebitDesc = (EditText) findViewById(R.id.etxt_debit_desc);
        txtcredit = (TextView) findViewById(R.id.new_txt_credit);
        txtdebit = (TextView) findViewById(R.id.new_txt_debit);

        db = new DataManger(this);

        id = getIntent().getIntExtra("id", -1);
        Log.i("Transno : ", "" + id);

        c = db.selectItem("DEBIT", id);

        if (c.moveToFirst()){
            debitName.setSelection(getIndex(debitName, (c.getString(2))));
            DebitDesc.setText(c.getString(5));
            txtcredit.setText(c.getString(4));
            txtdebit.setText(c.getString(3));
        }


    }
    //private method of your class
    private int getIndex(Spinner spinner, String value)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)){
                index = i;
                break;
            }
        }
        return index;
    }
}
