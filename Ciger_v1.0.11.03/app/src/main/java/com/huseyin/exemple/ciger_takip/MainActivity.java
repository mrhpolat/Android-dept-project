package com.huseyin.exemple.ciger_takip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Confirm.Confirm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable{

    private PhoneAdapter mPhoneAdapter;
    public static ContactInfo tempKisi;
    public boolean mdelete=false;
    ListView listView_kisiler;
    List<ContactInfo> contactslists =new ArrayList<ContactInfo>();
    ImageView imvDelete;
    int mValue=-1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent_show_contact=new Intent(this,ShowContact.class);
        final Intent intent_new_contact=new Intent(this,NewContact.class);
        final Intent intent_update_contact=new Intent(this,UpdateContact.class);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(intent_new_contact);
            }
        });

        mPhoneAdapter=new PhoneAdapter();

        listView_kisiler=(ListView)findViewById(R.id.listView_Contact);

        if (listView_kisiler!=null) {
            listView_kisiler.setAdapter(mPhoneAdapter);

            listView_kisiler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    tempKisi = (ContactInfo) mPhoneAdapter.getItem(position);
                    intent_show_contact.putExtra("selectedContact", tempKisi);
                    intent_show_contact.putExtra("position", position);
                    startActivity(intent_show_contact);
                }
            });
            //new record
            ContactInfo mNewContactSave = (ContactInfo) getIntent().getSerializableExtra("new_contact");
            if (mNewContactSave != null) {
                createNewContact(mNewContactSave);
            }
            //delete contact
            ContactInfo mDeleteContact =  (ContactInfo) getIntent().getSerializableExtra("deleteContact");

            if ((Integer) getIntent().getSerializableExtra("position_delete") != null)
                mValue =(Integer) getIntent().getSerializableExtra("position_delete");
            if (mValue!=-1)
            delete(mDeleteContact, mValue);

            ContactInfo mUpdateContact=  (ContactInfo) getIntent().getSerializableExtra("updateContact");

            if ((Integer) getIntent().getSerializableExtra("updatePosition") != null)
                mValue =(Integer) getIntent().getSerializableExtra("updatePosition");
            if (mValue!=-1)
                update(mUpdateContact, mValue);
        }
    }


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
      /*  if(id==R.id.action_add){

            DialogNewContact dialogNewKisi = new DialogNewContact();
            dialogNewKisi.show(getFragmentManager(),"");

        }*/

        return super.onOptionsItemSelected(item);
    }

    public void createNewContact(ContactInfo k){
        mPhoneAdapter.addKisi(k);
        mPhoneAdapter.saveNewKisi();
    }

    public void delete(Object k, int i){
        ContactInfo n=(ContactInfo)k;
        Confirm confirm=new Confirm();
        if (mdelete==false) {
            confirm.sendContactSelected(n, i,"MainActivity");
            confirm.show(getFragmentManager(), "");
        }
       else if (mdelete==true){

        mPhoneAdapter.mdelete(n,i);
        mPhoneAdapter.saveNewKisi();
            mdelete=false;
    }

    }

    public void update(ContactInfo k, int i){
        tempKisi=k;
        mValue=i;
        mPhoneAdapter.mUpdate(tempKisi,mValue);
        mPhoneAdapter.saveNewKisi();
    }

    public class PhoneAdapter extends BaseAdapter {

        private JSONSerializer  mSerializer;
        List<ContactInfo> contactlists =new ArrayList<ContactInfo>();

        public PhoneAdapter(){
            mSerializer=new JSONSerializer("PhoneBook.json",MainActivity.this.getApplicationContext());
            try {
                contactlists = mSerializer.load();
            }catch (Exception e){
                contactlists =new ArrayList<ContactInfo>();
            }
        }

        public void saveNewKisi(){
            try {
                mSerializer.save(contactlists);
            }catch (Exception e){
                Log.e("Error Saving... :","",e);
            }
        }

        @Override
        public int getCount() {
            return contactlists.size();
        }

        @Override
        public Object getItem(int position) {
            return contactlists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView==null){
                LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_contact,parent, false);
            }

            TextView txtName=(TextView)convertView.findViewById(R.id.txtName);
            TextView txtPhoneNumber=(TextView)convertView.findViewById(R.id.txtPhoneNumber);
           // Layout lyo_contact_name=(Layout)convertView.findViewById(R.id.lyo_contact_name);
            ImageView imvDelete=(ImageView)convertView.findViewById(R.id.imv_delete_icon);

            final ContactInfo tempKisi= contactlists.get(position);

            txtName.setText(tempKisi.getmName());
            txtPhoneNumber.setText(tempKisi.getmPhoneNumber());
            imvDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)  {
                    delete(tempKisi,position);

                }
            });

            return convertView;
        }

        public void addKisi(ContactInfo n){
            contactlists.add(n);
            notifyDataSetChanged();
        }

        public void mdelete(ContactInfo n, int i){
            contactlists.remove(i);
            notifyDataSetChanged();
        }
        public void mUpdate(ContactInfo n, int i){
            contactlists.set(i,n);
            notifyDataSetChanged();
        }
    }

}
