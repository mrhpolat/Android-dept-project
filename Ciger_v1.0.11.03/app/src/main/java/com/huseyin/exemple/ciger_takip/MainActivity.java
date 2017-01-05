package com.huseyin.exemple.ciger_takip;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Confirm.Confirm;
import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

public class MainActivity extends AppCompatActivity {

    public boolean delete_confirm=false;
    ListView listView_kisiler;
    ContactAdapter contactAdapter;
    public static ContactInfo tempKisi;
    DataManger db;
    Cursor c;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handxle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if(id==R.id.refresh){
           final Intent mainActivity=new Intent(this,MainActivity.class);
           startActivity(mainActivity);
       }
       return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent_show_contact=new Intent(this,ShowContact.class);
        final Intent intent_new_contact=new Intent(this,NewContact.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(intent_new_contact);
            }
        });

        db = new DataManger(this);
        //db.privateSql("ALTER TABLE DEBIT ADD status text");



        listView_kisiler=(ListView)findViewById(R.id.listView_Contact);
        //db.deleteContact("2","CONTACT");
        c = db.selectAll("CONTACT");
        contactAdapter = new ContactAdapter(this, c);

        if (listView_kisiler != null) {
            listView_kisiler.setAdapter(contactAdapter);
            listView_kisiler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent_show_contact.putExtra("idd",Integer.parseInt(c.getString(0)));
                    startActivity(intent_show_contact);
                }
            });
        }
    }


    public void delete(int id){
        db.deleteContact(id,"CONTACT");
        Intent mainActivity=new Intent(this,MainActivity.class);
        startActivity(mainActivity);

    }

    public class ContactAdapter extends CursorAdapter {

        public ContactAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }


        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.fragment_contact, parent, false);
        }

        @Override
        public void bindView(View view, Context context, final Cursor cursor) {

            TextView txtName = (TextView) view.findViewById(R.id.txtName);
            TextView txtPhoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumber);
            ImageView imvDelete = (ImageView) view.findViewById(R.id.imv_delete_icon);


            final int id = cursor.getInt(0);
            final String Name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);


            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Confirm confirm =new Confirm();
                    confirm.sendContactSelected(id,"MainActivity",Name);
                    confirm.show(getFragmentManager(),"");

                }
            });
            txtName.setText(Name);
            txtPhoneNumber.setText(phone);

        }
    }
}



