package com.huseyin.exemple.ciger_takip;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Huseyin on 23.10.2016.
 */

public class ContactInfo implements Serializable {

    private String mName, mPhoneNumber, mEmail;

    private int mCiger;

    private static final String JSON_ADI="Hüseyin";
    private static final String JSON_TELEFON_NUMARASI="444 23 48";
    private static final String JSON_EMAIL="hpolat@hotech.com.tr";


    public ContactInfo(JSONObject jsonObject) throws JSONException{

        mName =jsonObject.getString(JSON_ADI);
        mPhoneNumber =jsonObject.getString(JSON_TELEFON_NUMARASI);
        mEmail=jsonObject.getString(JSON_EMAIL);

    }
    public ContactInfo(){

    }

    public JSONObject convertToJSON () throws JSONException{
        JSONObject jsonObject= new JSONObject();

        jsonObject.put(JSON_ADI, mName);
        jsonObject.put(JSON_TELEFON_NUMARASI, mPhoneNumber);
        jsonObject.put(JSON_EMAIL,mEmail);

        return jsonObject;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String nEmail) {
        this.mEmail = nEmail;
    }

    public int getmCiger() {
        return mCiger;
    }

    public void setmCiger(int mCiger) {
        this.mCiger = mCiger;
    }

}
