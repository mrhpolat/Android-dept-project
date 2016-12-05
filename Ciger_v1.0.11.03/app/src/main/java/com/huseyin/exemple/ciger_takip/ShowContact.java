package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Debit.DebitMain;

import java.io.Serializable;

/**
 * Created by Huseyin on 2.11.2016.
 */

public class ShowContact extends AppCompatActivity implements Serializable {

    TextView mName,mPhoneNumber,mEmail;
    ContactInfo mContactInfo;
    Intent intent_update_contact,intent_mainactivity;
    Boolean update=false;
    int position=-1;


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_contact, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handxle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      if(id==R.id.updateSave){
          intent_update_contact =new Intent(this,UpdateContact.class);
          intent_update_contact.putExtra("selectedContact",mContactInfo);
          intent_update_contact.putExtra("position",position);
          startActivity(intent_update_contact);
        }
        else if (id==R.id.borc_list){
          Intent intent_debit = new Intent(this, DebitMain.class);
          Log.i("Position","________"+position);
          intent_debit.putExtra("id",position);
          startActivity(intent_debit);
      }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(final Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);


        MainActivity mnactivity = new MainActivity();
        intent_mainactivity = new Intent(this,MainActivity.class);

        mName=(TextView)findViewById(R.id.txtName);
        mPhoneNumber=(TextView)findViewById(R.id.txtPhoneNumber);
        mEmail=(TextView)findViewById(R.id.txtEmail);



        //getter mainActivity.java
        //
        mContactInfo =(ContactInfo)getIntent().getSerializableExtra("selectedContact");
        if (((Integer) getIntent().getSerializableExtra("position"))!=null)
        position= (Integer) getIntent().getSerializableExtra("position");

        //getter updateContact.java
        //
        if (((Integer)getIntent().getSerializableExtra("updatePosition"))!=null) {
            update=true;
            mContactInfo = (ContactInfo) getIntent().getSerializableExtra("updateContact");
            position = (Integer) getIntent().getSerializableExtra("updatePosition");
        }

        if (update==true) {
           // mnactivity.update(mContactInfo,position);
              intent_mainactivity.putExtra("updateContact", mContactInfo);
             intent_mainactivity.putExtra("updatePosition",position);

        }

        mName.setText(mContactInfo.getmName());
        mPhoneNumber.setText(mContactInfo.getmPhoneNumber());
        mEmail.setText(mContactInfo.getmEmail());

    }
}
