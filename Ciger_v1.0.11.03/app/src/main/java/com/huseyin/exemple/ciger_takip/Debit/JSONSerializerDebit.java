package com.huseyin.exemple.ciger_takip.Debit;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huseyin on 23.10.2016.
 */

public class JSONSerializerDebit {

    private String mFilename;
    private Context mContext;

    public JSONSerializerDebit(String fn, Context con){
        mFilename=fn;
        mContext=con;
    }
    public void save(List<DebitInfo> debitInfoList) throws IOException, JSONException{
        JSONArray jsonArray=new JSONArray();

        for (DebitInfo k:debitInfoList){
            jsonArray.put(k.convertToJSON());
        }
        Writer writer=null;
        try {
            OutputStream out=mContext.openFileOutput(mFilename,mContext.MODE_PRIVATE);
            writer=new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        }finally {
            if(writer !=null)
                writer.close();
        }
    }


    public ArrayList<DebitInfo> load() throws IOException,JSONException{
        ArrayList<DebitInfo> debitInfoList = new ArrayList<DebitInfo>();

        BufferedReader reader =null;

        try {
            InputStream in=mContext.openFileInput(mFilename);
            reader = new BufferedReader((new InputStreamReader(in)));
            StringBuilder jsonString=new StringBuilder();
            String line=null;


            while((line=reader.readLine())!=null){
                jsonString.append(line);
            }
            JSONArray jsonArray= (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for (int i=0;i<jsonArray.length();i++){
                debitInfoList.add(new DebitInfo(jsonArray.getJSONObject(i)));
            }

        }finally {
            if (reader != null)
                reader.close();

        }
        return  debitInfoList;
    }

}
