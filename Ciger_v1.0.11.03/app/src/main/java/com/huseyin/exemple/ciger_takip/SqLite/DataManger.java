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

    private static final String DB_NAME = "ciger_db.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE__ROWS_DEBIT_NAME = "debit_name";
    private static final String TABLE__ROWS_INSDATE = "debit_insdate";
    private static final String TABLE__ROWS_CREDITDATE = "debit_creditdate";
    private static final String TABLE__ROWS_DESCRIPTION = "debit_desc";
    private static final String TABLE_ROW_EMAIL = "email";
    private static final String TABLE_ROW_STATUS = "status";


    private static final String TABLE_ROW_USERCODE = "usercode";
    private static final String TABLE_ROW_PASSWORD = "password";


    private static final String TABLE_CONTACT = "CONTACT";
    private static final String TABLE_DEBIT = "DEBIT";
    private static final String TABLE_EMPLOYEE = "EMPLOYEE";

    private String CONTACT = "create table " + TABLE_CONTACT + " (" + TABLE_ROW_ID + " integer primary key autoincrement not null," + TABLE_ROW_NAME  + " text not null," + TABLE_ROW_EMAIL + " text not null," + TABLE_ROW_PHONE + " text not null);";

    private String DEBIT = "create table " + TABLE_DEBIT + " (" + TABLE_ROW_ID + " integer primary key autoincrement not null," + TABLE_ROW_Contact_ID + " text not null," + TABLE__ROWS_DEBIT_NAME + " text not null," + TABLE__ROWS_INSDATE + " text not null," + TABLE__ROWS_CREDITDATE  + " text not null," + TABLE__ROWS_DESCRIPTION + " text not null," + TABLE_ROW_STATUS +"text);";

    private String EMPLOYEE= "CREATE TABLE "+ TABLE_EMPLOYEE+" ("+TABLE_ROW_ID+ " INTEGER AUTO_INCREMENT , "+TABLE_ROW_USERCODE + " text primary key not null," + TABLE_ROW_PASSWORD+ " text not null, " + TABLE_ROW_EMAIL+ " text not null, " + TABLE_ROW_NAME + " text not null);";

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

    public void updateDebit(int id, String name, String debitDate, String creditDate, String desc) {
        db.isOpen();
        String query = "UPDATE " + TABLE_DEBIT + " SET " + TABLE__ROWS_DEBIT_NAME + " = '" + name + "', " + TABLE__ROWS_INSDATE + " = '" + debitDate + "', " + TABLE__ROWS_CREDITDATE + " = '" + creditDate + "', " + TABLE__ROWS_DESCRIPTION + " = '" + desc + "' WHERE "+TABLE_ROW_ID +" = '" + id+"'";

        Log.i("updateContact() =  ", query);

        db.execSQL(query);
        db.close();
    }

    public Cursor cout(String table_name,String contactid){
        Cursor c = db.rawQuery("SELECT count(1)" + " from " +
                table_name +" where " +TABLE_ROW_Contact_ID+ " = '"+contactid+"' and status='A'", null);
        return c;
    }

    public void insertDebit(String name, String contact_id,String insdate,String creditdate, String description){
        db.isOpen();
        String query="INSERT INTO "+TABLE_DEBIT + " ("+TABLE__ROWS_DEBIT_NAME+", " +TABLE_ROW_Contact_ID+", "+ TABLE__ROWS_INSDATE+", " + TABLE__ROWS_CREDITDATE +", "+TABLE__ROWS_DESCRIPTION+", "+TABLE_ROW_STATUS+") " +
                "VALUES ("+"'" + name +"'"+", "+"'" +contact_id +"'"+", " +"'" +insdate +"'"+", " +"'" +creditdate +"'"+", "  +"'" +description +"', 'A'"+");";

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

    public Cursor selectDebitAll(String id){
        Cursor c = db.rawQuery("SELECT *" + " from DEBIT WHERE status='A' and contact_id='" +
                id +"'", null);
        Log.i("SelectDebit All Sql: ","SELECT * from DEBIT WHERE status='A' and contact_id=" + id);
        return c;
    }



    public Cursor selectItem(String table_name,int id){

        Cursor c = db.rawQuery("SELECT *" + " from " +
                table_name + " WHERE " + TABLE_ROW_ID + " = " + id, null);

        return c;

    }
    public Cursor selectEMPLOYEE(String table_name,String code,String pass){

        Cursor c = db.rawQuery("SELECT 1" + " from " +
                table_name + " WHERE " + TABLE_ROW_USERCODE + " = '" + code+"' and "+ TABLE_ROW_PASSWORD + " = '" + pass+"'", null);
        return c;

    }

    public void privateSql(String query){
        Log.i("Create Sql : ", "" + query);
        db.execSQL(query);
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context){
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            /*String newTableQueryString = "create table "
                    + TABLE_CONTACT + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_EMAIL
                    + " text not null,"
                    + TABLE_ROW_PHONE
                    + " text not null);";

           String newTableQueryString2 = "create table "
                    + TABLE_DEBIT + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_Contact_ID
                    + " text not null,"
                    + TABLE__ROWS_DEBIT_NAME
                    + " text not null,"
                    + TABLE__ROWS_INSDATE
                    + " text not null,"
                    + TABLE__ROWS_CREDITDATE
                    + " text not null,"
                    + TABLE__ROWS_DESCRIPTION
                    + " text not null);";*/

            Log.i("Contat: ", "oluştu : " + CONTACT);
            Log.i("Debit: ", "oluştu : " + DEBIT);
            db.execSQL(CONTACT);
            db.execSQL(DEBIT);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }





}
