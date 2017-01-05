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
    String sendClass,Name=" ";

    public void sendContactSelected(int i,String sendClass,String Name){
        n=i;
        this.sendClass=sendClass;
        this.Name=Name;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState){

    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(Name + " Kaydını Silmek Üzeresiniz.");
        AlertDialog.Builder builder1 = builder.setMessage("Silmek istediğiniz'den emin misiniz?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (sendClass) {
                    case "MainActivity":
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.delete(n);
                        break;
                    case "DebitMain":
                        DebitMain debitMain = (DebitMain) getActivity();
                        debitMain.delete(n);
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
                });
        return builder.create();
    }
}

