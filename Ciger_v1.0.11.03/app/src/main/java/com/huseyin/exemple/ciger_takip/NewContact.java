package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

import java.io.Serializable;

/**
 * Created by Huseyin on 2.11.2016.
 */

public class NewContact extends AppCompatActivity implements Serializable {

    EditText editName,editPhoneNumber,editEmail;
    Intent mainactivity;
    DataManger db;

    protected void onCreate(final Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_new_contact);

        editName=(EditText)findViewById(R.id.etxtName);
        editPhoneNumber=(EditText)findViewById(R.id.etxtPhoneNumber);
        editEmail=(EditText)findViewById(R.id.etxtEmail);
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
            mainactivity=new Intent(this,MainActivity.class);
            db = new DataManger(this);
            db.insertContact(editName.getText().toString(),editEmail.getText().toString(),editPhoneNumber.getText().toString());
           startActivity(mainactivity);

        }
        else if (id==R.id.newContactCancel){
            mainactivity=new Intent(this,MainActivity.class);
            startActivity(mainactivity);
        }

        return super.onOptionsItemSelected(item);
    }

}
