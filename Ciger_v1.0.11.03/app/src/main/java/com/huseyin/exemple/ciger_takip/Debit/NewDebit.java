package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.huseyin.exemple.ciger_takip.R;

import java.io.Serializable;

/**
 * Created by Huseyin on 23.11.2016.
 */

public class NewDebit extends AppCompatActivity implements Serializable{

    Intent debitmain;
    Spinner debitName;
    EditText eDebitDate,eDebitCollection,eDebitDesc;
    private int user;

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
            DebitInfo newDebitInfo= new DebitInfo();

            newDebitInfo.setmName(debitName.getSelectedItem().toString());
            Toast.makeText(this," "+debitName.getSelectedItem().toString()+" Borcu Eklendi",Toast.LENGTH_LONG).show();
            newDebitInfo.setmDate(eDebitDate.getText().toString());
            newDebitInfo.setmCollectionDate(eDebitCollection.getText().toString());
            newDebitInfo.setmDesc(eDebitDesc.getText().toString());
            newDebitInfo.setId(user);

            debitmain.putExtra("new_debit", newDebitInfo);
            startActivity(debitmain);
        }
        else if (id==R.id.newContactCancel){
            debitmain=new Intent(this,DebitMain.class);
            startActivity(debitmain);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(final Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_new_debit);
        debitmain=new Intent(this,DebitMain.class);
        user=getIntent().getIntExtra("user",-1);

        debitName=(Spinner)findViewById(R.id.spinner_debit);
        eDebitDate=(EditText)findViewById(R.id.etxt_debit_date);
        eDebitCollection=(EditText)findViewById(R.id.etxt_date_collection);
        eDebitDesc=(EditText)findViewById(R.id.etxt_debit_desc);
    }

}
