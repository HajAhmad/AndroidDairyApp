package ir.plusrobot.dairyapp.rest;


import org.json.JSONException;
import org.json.JSONObject;

public class JsonOps {

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{
        JSONObject object = jsonObject.getJSONObject(tagName);
        return object;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
}
