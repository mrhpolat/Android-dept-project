package com.huseyin.exemple.ciger_takip.Confirm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.huseyin.exemple.ciger_takip.ContactInfo;
import com.huseyin.exemple.ciger_takip.Debit.DebitMain;
import com.huseyin.exemple.ciger_takip.MainActivity;

/**
 * Created by Huseyin on 15.11.2016.
 */

public class Confirm extends DialogFragment {

    Object mContactInfo=new ContactInfo();
    int n;
    public boolean mdelete;
    String sendClass;
    public void sendContactSelected(Object contactSelected, int i,String sendClass){
        mContactInfo = contactSelected;
        n=i;
        mdelete =false;
        this.sendClass=sendClass;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState){

    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
     builder.setMessage("Silmek istediğinizden emin misiniz?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {

             switch (sendClass) {
                 case "MainActivity":
                 MainActivity mainActivity = (MainActivity) getActivity();
                 mainActivity.mdelete = true;
                 mainActivity.delete(mContactInfo, n);
                break;
                 case "DebitMain":
                     DebitMain debitmain = (DebitMain) getActivity();
                     debitmain.mdelete = true;
                     debitmain.delete(mContactInfo, n);
                     break;
                default:
                    break;
             }
             dismiss();
         }
     })
     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dismiss();
         }
     })
     ;
    return builder.create();
    }
}

