package com.example.demointernship;

import org.json.JSONException;
import org.json.JSONObject;

public class ExampleItem {
    private JSONObject obj = new JSONObject();

    public ExampleItem(String title, String message, String url){
        try {
            obj.put("title", title);
            obj.put("message", message);
            obj.put("url", url);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JSONObject getObj(){
        return obj;
    }

}
