package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.R;
import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

/**
 * Created by Huseyin on 5.01.2017.
 */

public class DebitShow extends AppCompatActivity {

    TextView txtdebit, txtcredit,txtdesc,txtname;
    Intent debitUpdate;
    Boolean update = false;
    DataManger db;
    Cursor c;
    int id; // kayıt id si :)

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_debit, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handxle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idd = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (idd == R.id.updateSave) {
            startActivity(debitUpdate);

        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_show);

        db = new DataManger(this);

        txtname = (TextView) findViewById(R.id.debitName);
        txtdebit = (TextView) findViewById(R.id.debitDate);
        txtcredit= (TextView) findViewById(R.id.creditDate);
        txtdesc = (TextView) findViewById(R.id.Desc);

        id = getIntent().getIntExtra("id", -1);
        Log.i("show debit id ", "+" + id);

        if (id != -1) {
            c = db.selectItem("DEBIT", id);
            update = true;
        }
        if (c.moveToFirst()){
            txtname.setText(c.getString(2));
            txtdebit.setText(c.getString(3));
            txtcredit.setText(c.getString(4));
            txtdesc.setText(c.getString(5));
        }
        if (update == true) {
            debitUpdate = new Intent(this, DebitUpdate.class);
            debitUpdate.putExtra("id",id);
        }

    }
}
