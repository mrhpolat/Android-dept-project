package com.huseyin.exemple.ciger_takip.SqLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Huseyin on 6.12.2016.
 */

public class DataManger {

    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_Contact_ID = "contact_id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_PHONE = "phone";

    private static final String DB_NAME = "ciger_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE__ROWS_DEBIT_NAME = "debit_name";
    private static final String TABLE__ROWS_INSDATE = "debit_name";
    private static final String TABLE__ROWS_COLLECTIONDATE = "debit_name";
    private static final String TABLE__ROWS_DESCRIPTION = "debit_name";
    private static final String TABLE_ROW_EMAIL = "email";

    private static final String TABLE_CONTACT = "CONTACT";
    private static final String TABLE_DEBIT = "DEBIT";


    public DataManger(Context context){

        CustomSQLiteOpenHelper helper =new CustomSQLiteOpenHelper(context);
        db=helper.getWritableDatabase();

    }

    public void insertContact(String name, String email, String phone){

        String query="INSERT INTO "+TABLE_CONTACT+" ("+TABLE_ROW_NAME+", "+TABLE_ROW_EMAIL+", "+TABLE_ROW_PHONE+") VALUES ("+"'" + name +"'"+", "
                +"'" +email +"'"+", "
                +"'" +phone +"'"+");";
        Log.i("insertContact() =  ", query);

        db.execSQL(query);
        db.close();
    }
    public void updateContact(int id, String name, String email, String phone){
        db.isOpen();
        String query = "UPDATE " + TABLE_CONTACT + " SET " + TABLE_ROW_NAME + " = '" + name + "', " + TABLE_ROW_EMAIL + " = '" + email + "', " + TABLE_ROW_PHONE + " = '" + phone + "' WHERE "+TABLE_ROW_ID +" = '" + id+"'";

        Log.i("updateContact() =  ", query);

        db.execSQL(query);
        db.close();
    }

    public Cursor cout(String table_name){
        Cursor c = db.rawQuery("SELECT count(1)" + " from " +
                table_name, null);
        return c;
    }

    public void insertDebit(String name, String contact_id,String insdate,String collectionDate, String description){
        db.isOpen();
        String query="INSERT INTO "+TABLE_DEBIT+
                " ("+TABLE__ROWS_DEBIT_NAME+", "
                +TABLE_ROW_Contact_ID+", "
                +TABLE__ROWS_INSDATE+", "
                +TABLE__ROWS_COLLECTIONDATE+", "
                +TABLE__ROWS_DESCRIPTION+") " +
                "VALUES ("+"'" + name +"'"+", "
                +"'" +contact_id +"'"+", "
                +"'" +insdate +"'"+", "
                +"'" +collectionDate +"'"+", "
                +"'" +description +"'"+");";
        Log.i("insertDebit() =  ",query);

        db.execSQL(query);
        db.close();
    }

    public void deleteContact(int id,String table_name){
        db.isOpen();
        String query="DELETE FROM "+table_name+" WHERE " + TABLE_ROW_ID + " = '"+id + "';";
        db.execSQL(query);
        db.close();

    }

    public Cursor selectAll(String table_name){
        Cursor c = db.rawQuery("SELECT *" + " from " +
                table_name, null);
        return c;

    }

    public Cursor selectItem(String table_name,int id){

        Cursor c = db.rawQuery("SELECT *" + " from " +
                table_name + " WHERE " + TABLE_ROW_ID + " = " + id, null);

        return c;

    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String newTableQueryString = "create table "
                    + TABLE_CONTACT + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_EMAIL
                    + " text not null,"
                    + TABLE_ROW_PHONE
                    + " text not null);";


            db.execSQL(newTableQueryString);

           /* newTableQueryString = "create table "
                    + TABLE_DEBIT + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_Contact_ID
                    + " text not null,"
                    + TABLE__ROWS_DEBIT_NAME
                    + " text not null,"
                    + TABLE__ROWS_INSDATE
                    + " text not null,"
                    + TABLE__ROWS_COLLECTIONDATE
                    + " text not null,"
                    + TABLE__ROWS_DESCRIPTION
                    + " text not null);";

            db.execSQL(newTableQueryString);*/

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }





}
