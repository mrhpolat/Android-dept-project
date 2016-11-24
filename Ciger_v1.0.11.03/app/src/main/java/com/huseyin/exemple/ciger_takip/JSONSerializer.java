package com.huseyin.exemple.ciger_takip;

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

public class JSONSerializer {

    private String mFilename;
    private Context mContext;

    public JSONSerializer(String fn,Context con){
        mFilename=fn;
        mContext=con;
    }
    public void save(List<ContactInfo> contactInfoLists) throws IOException, JSONException{
        JSONArray jsonArray=new JSONArray();

        for (ContactInfo k:contactInfoLists){
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


    public ArrayList<ContactInfo> load() throws IOException,JSONException{
        ArrayList<ContactInfo> contactInfoLists = new ArrayList<ContactInfo>();

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
                contactInfoLists.add(new ContactInfo(jsonArray.getJSONObject(i)));
            }

        }finally {
            if (reader != null)
                reader.close();

        }
        return  contactInfoLists;
    }

}
