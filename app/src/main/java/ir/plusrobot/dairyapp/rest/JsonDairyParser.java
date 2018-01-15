package ir.plusrobot.dairyapp.rest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDairyParser {

    public static List<Dairy> getDairy(String data){

        List<Dairy> dairyList = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(data);
           for (int i = 0; i < array.length(); i++){
               JSONObject obj = array.getJSONObject(i);
               Dairy dairy = new Dairy();
               dairy.set_Id(obj.getInt("Id"));
               dairy.set_Title(obj.getString("Title"));
               dairy.set_Content(obj.getString("Content"));
               dairy.set_Date(obj.getString("Date"));
               dairy.set_IsFav(obj.getBoolean("IsFav"));

           }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return dairyList;
    }

}
