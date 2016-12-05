package com.huseyin.exemple.ciger_takip.Debit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Huseyin on 22.11.2016.
 */

public class DebitInfo implements Serializable {

    private String mName,mDate,mCollectionDate,mDesc;
    private int id;


    private static final String JSON_NAME="GENEL";
    private static final String JSON_DATE="01.01.2016";
    private static final String JSON_COLLECTIONDATE="01.01.2016";
    private static final String JSON_DESCRIPTION="GENEL";
    private static final int JSON_USERID =-1;

    public DebitInfo(JSONObject jsonObject) throws JSONException{

        mName =jsonObject.getString(JSON_NAME);
        mDate=jsonObject.getString(JSON_DATE);
        mCollectionDate=jsonObject.getString(JSON_COLLECTIONDATE);
        mDesc=jsonObject.getString(JSON_DESCRIPTION);
        id=jsonObject.getInt(String.valueOf(JSON_USERID));

    }

    public DebitInfo(){

    }

    public JSONObject convertToJSON() throws JSONException{

        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_NAME,mName);
        jsonObject.put(JSON_DATE,mDate);
        jsonObject.put(JSON_COLLECTIONDATE,mCollectionDate);
        jsonObject.put(JSON_DESCRIPTION,mDesc);
        jsonObject.put(String.valueOf(JSON_USERID),id);

        return jsonObject;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmCollectionDate() {
        return mCollectionDate;
    }

    public void setmCollectionDate(String mCollectionDate) {
        this.mCollectionDate = mCollectionDate;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
