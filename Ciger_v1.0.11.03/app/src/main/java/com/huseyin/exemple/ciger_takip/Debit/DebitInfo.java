package com.huseyin.exemple.ciger_takip.Debit;

import java.io.Serializable;

/**
 * Created by Huseyin on 22.11.2016.
 */

public class DebitInfo implements Serializable {

    private String mName,mDate,mCollectionDate,mDesc;
    private int id;



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
