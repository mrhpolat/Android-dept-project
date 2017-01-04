package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Confirm.DateDialog;
import com.huseyin.exemple.ciger_takip.R;
import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

/**
 * Created by Huseyin on 23.11.2016.
 */

public class NewDebit extends AppCompatActivity {

    Intent debitmain;
    Spinner debitName;
    EditText eDebitDesc;
    TextView txtdebit, txtcredit;
    private int user;
    DataManger db;


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_contact, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handxle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.newContactSave){

            debitmain=new Intent(this,DebitMain.class);

            db = new DataManger(this);

                db.insertDebit(debitName.getSelectedItem().toString(), String.valueOf(user), txtdebit.getText().toString(), txtcredit.getText().toString(), eDebitDesc.getText().toString());


            debitmain.putExtra("id",user);
            startActivity(debitmain);
        }
        else if (id==R.id.newContactCancel){
            debitmain=new Intent(this,DebitMain.class);
            debitmain.putExtra("id",user);
            startActivity(debitmain);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(final Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_new_debit);
        debitmain=new Intent(this,DebitMain.class);
        user=getIntent().getIntExtra("user",-1);

        debitName = (Spinner) findViewById(R.id.spinner_debit);
        eDebitDesc = (EditText) findViewById(R.id.etxt_debit_desc);
        txtcredit = (TextView) findViewById(R.id.new_txt_credit);
        txtdebit = (TextView) findViewById(R.id.new_txt_debit);

        txtcredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dateDialog = new DateDialog(v);
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                dateDialog.show(ft, "DatePicker");
            }
        });
        txtdebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dateDialog = new DateDialog(v);
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                dateDialog.show(ft, "DatePicker");
            }
        });



    }

}
