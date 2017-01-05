package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Debit.DebitMain;
import com.huseyin.exemple.ciger_takip.SqLite.DataManger;


/**
 * Created by Huseyin on 2.11.2016.
 */

public class ShowContact extends AppCompatActivity {

    TextView mName, mPhoneNumber, mEmail, mCredit;
    int id = -1;
    Intent intent_update_contact, intent_mainactivity;
    Boolean update = false;
    String email,name;
    DataManger db;
    Cursor c,count;


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_contact, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handxle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idd = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (idd == R.id.updateSave) {
            startActivity(intent_update_contact);

        } else if (idd == R.id.borc_list) {
            Intent intent_debit = new Intent(this, DebitMain.class);
            intent_debit.putExtra("contactid",id);
            startActivity(intent_debit);
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        MainActivity mnactivity = new MainActivity();
        intent_mainactivity = new Intent(this, MainActivity.class);
        db = new DataManger(this);


        mName = (TextView) findViewById(R.id.txtName);
        mPhoneNumber = (TextView) findViewById(R.id.txtPhoneNumber);
        mEmail = (TextView) findViewById(R.id.txtEmail);
        mCredit = (TextView) findViewById(R.id.txtCredit);


        //getter mainActivity.java
        //
        id = getIntent().getIntExtra("idd",-1);
        Log.i("Show ID : "," "+id);
        if (id != -1) {
            c = db.selectItem("CONTACT", id);
            update = true;
        }

       if( c.moveToFirst()) {
           mName.setText(c.getString(1).toString());
           mPhoneNumber.setText(c.getString(3).toString());
           mEmail.setText(c.getString(2).toString());
           email = c.getString(2).toString();



           c.close();
       }
        count = db.cout("DEBIT",String.valueOf(id));
        if(count.moveToFirst()){
            mCredit.setText(count.getString(0).toString());
            Log.i("Contact Count: ", count.getString(0).toString());
        }


        if (update == true) {

            mEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, email);
                    intent.setData(Uri.parse("mailto:"+email));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "emailSubject");
                    intent.putExtra(Intent.EXTRA_TEXT, "emailContent");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
                    try {

                        startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Log.d("Email error:",e.toString());
                    }

                }
            });


            intent_update_contact = new Intent(this, UpdateContact.class);
            intent_update_contact.putExtra("idd",id);
        }

    }
}

