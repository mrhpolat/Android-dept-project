package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huseyin.exemple.ciger_takip.SqLite.DataManger;

import java.util.Random;


/**
 * Created by Huseyin on 5.01.2017.
 */

public class Login extends AppCompatActivity {

    EditText user,pass,txtcapt;
    Button btnLogin;
    DataManger db;
    Cursor c;
    int i1=10,i2=11, sonuc=0;
    String operetion;
    String Sonuc;

    int bool;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DataManger(this);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        txtcapt = (EditText) findViewById(R.id.txtcapt);
        //db.privateSql("INSERT INTO EMPLOYEE (usercode, password, email, name) values ('HPOLAT','1','HPOLAT@AMONRA.COM.TR','Hüseyin POLAT');");
        TextView capt = (TextView) findViewById(R.id.captcha);
        final String sonucc = captcha();
        Sonuc=sonucc;

        capt.setText(i1+" "+operetion+" "+i2);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String ucode = user.getText().toString().trim().toUpperCase();
               final String password = pass.getText().toString().trim().toUpperCase();
               final String capt = txtcapt.getText().toString().trim();
               c=db.selectEMPLOYEE("EMPLOYEE",ucode,password);
                if (c.moveToFirst()){
                   try{
                       bool = Integer.parseInt(c.getString(0));
                   }catch (Exception e){
                       notmainactivity();
                   }
                }else
                    Log.e("cursordan değer alınamadı...", "");


              //  Log.i("Login durumu",""+bool+" capt: "+capt+" sonuc: "+sonuc);
                if (bool==1 && capt.trim().equals(sonucc)){
                    mainactivity();
                }
                else if (bool==0)
                    notmainactivity();
            }
        });


    }
    private void mainactivity(){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        bool=0;
    }
    private void notmainactivity(){
        Toast.makeText(this, "Kullanıcı adınız veya şifreniz hatalı lütfen tekrar deneyiniz..", Toast.LENGTH_LONG).show();
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
    public String captcha(){
        Random r = new Random();
        i1 = (r.nextInt(99) + 10);
        i2 = (r.nextInt(99) + 10);
        int i3=(r.nextInt(4)+1);
        switch (i3) {
            case 1:{
                operetion = "+";
                return String.valueOf(i1+i2);
            }
            case 2:{
                operetion = "-";
                return String.valueOf(i1-i2);
            }
            case 3:{
                operetion = "*";
                return String.valueOf(i1*i2);
            }
            default:{
                operetion = "+";
                return String.valueOf(i1+i2);
            }
        }
    }
}


