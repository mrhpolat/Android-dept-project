package com.huseyin.exemple.ciger_takip;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huseyin.exemple.ciger_takip.SqLite.DataManger;


/**
 * Created by Huseyin on 5.01.2017.
 */

public class Login extends AppCompatActivity {

    EditText user,pass;
    Button btnLogin;
    DataManger db;
    Cursor c;
    String name;
    int bool=0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DataManger(this);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        //db.privateSql("INSERT INTO EMPLOYEE (usercode, password, email, name) values ('HPOLAT','1','HPOLAT@AMONRA.COM.TR','Hüseyin POLAT');");

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String ucode = user.getText().toString();
               final String password = pass.getText().toString();
              //  if ((ucode!=null && password!=null)||(ucode==''))
                c=db.selectEMPLOYEE("EMPLOYEE",ucode,password);
                if (c.moveToFirst()){
                    bool = Integer.parseInt(c.getString(0));
                }else
                    Log.e("cursordan değer alınamadı...", "");
                Log.i("Login durumu",""+bool);
                if (bool==1){
                    mainactivity();
                }
                else
                    notmainactivity();
            }
        });

    }
    private void mainactivity(){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
    private void notmainactivity(){
        Toast.makeText(this, "Kullanıcı adınız veya şifreniz hatalı lütfen tekrar deneyiniz..", Toast.LENGTH_LONG);
    }
}
