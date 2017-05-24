package com.example.gabi2.criogenia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gabi2 on 23/05/2017.
 */
public class Users {
    public static String[] ids;
    public static String[] names;
    public static String[] emails;


    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id_usuario";
    public static final String KEY_NAME = "nome";
    public static final String KEY_EMAIL = "email";


    private JSONArray users = null;

    private String json;

    public Users (String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            names = new String[users.length()];
            emails = new String[users.length()];


            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                names[i] = jo.getString(KEY_NAME);
                emails[i]=jo.getString(KEY_EMAIL);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
