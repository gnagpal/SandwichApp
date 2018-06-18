package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String SANDWICH_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonObject;

        String mainName = null;
        String origin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();
        List<String> alsoKnownAs = new ArrayList<>();

        try{
            jsonObject = new JSONObject(json);
            JSONObject jsonName = jsonObject.getJSONObject("name");
            mainName = jsonName.getString("mainName");
            origin = jsonObject.getString("placeOfOrigin");
            description = jsonObject.getString("description");
            image = jsonObject.getString("image");

            alsoKnownAs = jsonList(jsonName.getJSONArray("alsoKnownAs"));
            ingredients = jsonList(jsonObject.getJSONArray("ingredients"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName, alsoKnownAs, origin, description, image, ingredients);
    }

    private static List<String> jsonList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        if(jsonArray!=null){
            for(int i=0; i<jsonArray.length(); i++){
                try {
                    list.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


}
