package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Context;
import android.content.Intent;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huseyin.exemple.ciger_takip.Confirm.Confirm;
import com.huseyin.exemple.ciger_takip.R;
import com.huseyin.exemple.ciger_takip.ShowContact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huseyin on 22.11.2016.
 */

public class DebitMain extends AppCompatActivity implements Serializable {

    ListView debit_listview;
    private DebitAdapter mdebitAdapter;
    public boolean mdelete=false;
    private int id;


    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        id=getIntent().getIntExtra("id",-1);

        mdebitAdapter=new DebitAdapter();
        debit_listview=(ListView) findViewById(R.id.listView_ciger);

        final Intent in_new_debit=new Intent(this,NewDebit.class);
        Intent in_showcontact=new Intent(this, ShowContact.class);

        //new debit Create
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_debit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in_new_debit.putExtra("user",id);
                startActivity(in_new_debit);
            }
        });

        //if(debit_listview!=null){
            debit_listview.setAdapter(mdebitAdapter);
        //}
        //new debit
        DebitInfo mNewDebitSave= (DebitInfo) getIntent().getSerializableExtra("new_debit");
        if (mNewDebitSave!=null){
            createNewDebit(mNewDebitSave);
        }

        //int deleteDebitId


    }

    public class DebitAdapter extends BaseAdapter{

        private JSONSerializerDebit mSerializer;

        List<DebitInfo> mdebitlists=new ArrayList<DebitInfo>();



        public DebitAdapter(){
            Log.e("Exection, Hata: "," "+id);
            mSerializer=new JSONSerializerDebit("debit.json",DebitMain.this.getApplicationContext());
            try {
                mdebitlists = mSerializer.load(id);
            }catch (Exception e){
                mdebitlists =new ArrayList<DebitInfo>();
            }
        }

        public void saveNewKisi(){
            try {
                mSerializer.save(mdebitlists);
            }catch (Exception e){
                Log.e("Error Saving... :","",e);
            }
        }



        @Override
        public int getCount() {
            return mdebitlists.size();
        }

        @Override
        public Object getItem(int position) {
            return mdebitlists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fra_debit,parent,false);
            }

            TextView txtDebitName,txtDebitDate,txtDebitCollection;

            final DebitInfo debitInfo= mdebitlists.get(position);

            txtDebitName=(TextView)convertView.findViewById(R.id.debit_name);
            txtDebitDate=(TextView)convertView.findViewById(R.id.debit_date);
            txtDebitCollection=(TextView)convertView.findViewById(R.id.debit_collection);

            ImageView imv_debit_type=(ImageView)convertView.findViewById(R.id.imv_debit_type);
            final ImageView imv_dots=(ImageView)convertView.findViewById(R.id.imv_dots);
            imv_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                showPopupMenu(imv_dots,position, debitInfo);

                }
            });



            txtDebitName.setText(debitInfo.getmName());
            txtDebitDate.setText(debitInfo.getmDate());
            txtDebitCollection.setText(debitInfo.getmCollectionDate());

            return convertView;
        }

        private void showPopupMenu(View view, final int position, DebitInfo debitInfo){
            PopupMenu popup=new PopupMenu(view.getContext(),view);

            final DebitInfo deleteDebit=debitInfo;

            MenuInflater inflater=popup.getMenuInflater();
            inflater.inflate(R.menu.popup_menu,popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //menu içeriğinde neler yapılacağı seçilecek
                    switch (item.getItemId()){
                        case R.id.debit_delete:
                            delete(deleteDebit,position);
                    }

                    return false;
                }
            });
            popup.show();
        }

        public void addKisi(DebitInfo n){
            mdebitlists.add(n);
            notifyDataSetChanged();
        }
        public void mdelete(DebitInfo n, int i){
            mdebitlists.remove(i);
            notifyDataSetChanged();
        }

    }
    public void createNewDebit(DebitInfo k){
        mdebitAdapter.addKisi(k);
        mdebitAdapter.saveNewKisi();
    }

    public void delete(Object k, int i){
        DebitInfo n=(DebitInfo)k;
        Confirm confirm=new Confirm();
        if (mdelete==false) {
            confirm.sendContactSelected(n, i,"DebitMain");
            confirm.show(getFragmentManager(), "");
        }
        else if (mdelete==true){

            mdebitAdapter.mdelete(n,i);
            mdebitAdapter.saveNewKisi();
            mdelete=false;
        }

    }


}
