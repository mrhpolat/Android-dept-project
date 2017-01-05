package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

/**
 * Created by Huseyin on 2.11.2016.
 */

public class UpdateContact extends AppCompatActivity {

    EditText editName,editPhoneNumber,editEmail;
    Intent showContact;
    int idd =-1;
    Cursor c;
    DataManger db;

    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_new_contact);

        final Intent intent_show_contact=new Intent(this,ShowContact.class);

        editName=(EditText)findViewById(R.id.etxtName);
        editPhoneNumber=(EditText)findViewById(R.id.etxtPhoneNumber);
        editEmail=(EditText)findViewById(R.id.etxtEmail);
        idd = getIntent().getIntExtra("idd",-1);
        db = new DataManger(this);
        Log.i("ID update : ", " " + idd);

        c = db.selectItem("CONTACT", idd);

        if (c.moveToFirst()) {
            editName.setText(c.getString(1).toString());
            editPhoneNumber.setText(c.getString(3).toString());
            editEmail.setText(c.getString(2).toString());
            c.close();
        }
    }

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
            showContact =new Intent(this,ShowContact.class);

            db.updateContact(idd,editName.getText().toString(),editEmail.getText().toString(),editPhoneNumber.getText().toString());
            showContact.putExtra("idd",idd);

           startActivity(showContact);
        }
        else if (id==R.id.newContactCancel){
            showContact =new Intent(this,ShowContact.class);

            showContact.putExtra("idd",idd);
            startActivity(showContact);
        }

        return super.onOptionsItemSelected(item);
    }

}
