package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by Huseyin on 2.11.2016.
 */

public class UpdateContact extends AppCompatActivity implements Serializable {

    EditText editName,editPhoneNumber,editEmail;
    ContactInfo mContactInfo;
    Intent mainactivity;
    int  position=-1;

    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_new_contact);

        final Intent intent_show_contact=new Intent(this,ShowContact.class);

        editName=(EditText)findViewById(R.id.etxtName);
        editPhoneNumber=(EditText)findViewById(R.id.etxtPhoneNumber);
        editEmail=(EditText)findViewById(R.id.etxtEmail);



         mContactInfo=(ContactInfo)getIntent().getSerializableExtra("selectedContact");

         position = (Integer) getIntent().getSerializableExtra("position");


        editName.setText(mContactInfo.getmName());
        editPhoneNumber.setText(mContactInfo.getmPhoneNumber());
        editEmail.setText(mContactInfo.getmEmail());

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

            ContactInfo updateContact = new ContactInfo();

            updateContact.setmName(editName.getText().toString());
            updateContact.setmPhoneNumber(editPhoneNumber.getText().toString());
            updateContact.setmEmail(editEmail.getText().toString());

            mainactivity.putExtra("updateContact",updateContact);
            mainactivity.putExtra("updatePosition", position);
            startActivity(mainactivity);
        }
        else if (id==R.id.newContactCancel){
            mainactivity=new Intent(this,MainActivity.class);
            startActivity(mainactivity);
        }

        return super.onOptionsItemSelected(item);
    }

}
