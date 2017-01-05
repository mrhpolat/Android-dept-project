package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Confirm.Confirm;
import com.huseyin.exemple.ciger_takip.R;
import com.huseyin.exemple.ciger_takip.ShowContact;
import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

import java.io.Serializable;

/**
 * Created by Huseyin on 22.11.2016.
 */

public class DebitMain extends AppCompatActivity implements Serializable {

    ListView debit_listview;
    public boolean mdelete=false;
    DebitAdapter debitAdapter;
    private int contactid;
    DataManger db;
    Cursor c;
    ImageView imv_dots;
    Intent debitShow;


    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        contactid =getIntent().getIntExtra("contactid",-1);


        db = new DataManger(this);
        debit_listview=(ListView) findViewById(R.id.listView_ciger);
        c =  db.selectDebitAll(String.valueOf(contactid));
        Log.i("Contat ID : ", "" + contactid);

        debitAdapter = new DebitAdapter(this, c);

        if(debit_listview !=null){
            debit_listview.setAdapter(debitAdapter);
            debit_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }

        final Intent in_new_debit=new Intent(this,NewDebit.class);
        Intent in_showcontact=new Intent(this, ShowContact.class);

        //new debit Create
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_debit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in_new_debit.putExtra("user", contactid);
                startActivity(in_new_debit);
            }
        });



    }
    public class DebitAdapter extends CursorAdapter {

        public DebitAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.fra_debit, parent, false);
        }

        @Override
        public void bindView(final View view, Context context, final Cursor cursor) {

            TextView txtName = (TextView) view.findViewById(R.id.debit_name);
            TextView txtdebitDate = (TextView) view.findViewById(R.id.debit_date);
            TextView txtcreditDate = (TextView) view.findViewById(R.id.credit_date);
             imv_dots=(ImageView) view.findViewById(R.id.imv_dots);


            //Log.i("Debit List", cursor.getString(0) +"  --  " +cursor.getString(1) +"  --  " + cursor.getString(2) + "  --  " +cursor.getString(3)+ "  --  " +cursor.getString(4)+ "  --  " +cursor.getString(5));
            final int id = cursor.getInt(0);
            final int user = cursor.getInt(1);
            final String Name = cursor.getString(2);
            String debitDate = cursor.getString(3);
            String creditDate = cursor.getString(4);
            String desc = cursor.getString(5);

           imv_dots.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   showPopupMenu(imv_dots, id,Name);
               }
           });

            txtName.setText(Name);
            txtdebitDate.setText(debitDate);
            txtcreditDate.setText(creditDate);

        }

        private void showPopupMenu(View view, final int id,final String Name){
            PopupMenu popup=new PopupMenu(view.getContext(),view);

            MenuInflater inflater=popup.getMenuInflater();
            inflater.inflate(R.menu.popup_menu,popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //menu içeriğinde neler yapılacağı seçilecek
                    switch (item.getItemId()){
                        case R.id.debit_delete:
                            Confirm confirm =new Confirm();
                            confirm.sendContactSelected(id,"DebitMain",Name);
                            confirm.show(getFragmentManager(),"");
                            break;
                        case R.id.debit_paid:
                            String query = "update DEBIT set status = 'F' where _id='" + id+"'";
                            db.privateSql(query);
                            break;
                        case R.id.debit_update:
                            intentupdate(id);
                            break;
                        case R.id.debit_info:
                            intentshow(id);
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });
            popup.show();
        }
    }

    public void delete(int idd){
        db.deleteContact(idd,"DEBIT");
        Intent debitMain=new Intent(this,DebitMain.class);
        debitMain.putExtra("contactid", contactid);
        Log.i("Debit Send contactid : ", "" + contactid);
        startActivity(debitMain);

    }

    public void intentshow(int id){
        debitShow = new Intent(this, DebitShow.class);
        debitShow.putExtra("id", id);
        Log.i("IDDD---- ", "" + id);
        startActivity(debitShow);
    }

    public void intentupdate(int id){
        Intent updatedebit=new Intent(this,DebitUpdate.class);
        updatedebit.putExtra("id", id);
        startActivity(updatedebit);
    }



}
