package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";


    /**
     * @param sandwichJsonString FIELDS ARE
     *                           JSONObject called NAME with MAIN_NAME as string and KNOWN_AS as JSONArray
     *                           String PLACE_OF_ORIGIN , DESCRIPTION , IMAGE
     *                           JSONArray INGREDIENTS
     * @return Sandwich object
     */
    public static Sandwich parseSandwichJson(String sandwichJsonString) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichJsonObj = new JSONObject(sandwichJsonString);

            // set sandwich simple fields
            sandwich.setPlaceOfOrigin(sandwichJsonObj.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(sandwichJsonObj.getString(DESCRIPTION));
            sandwich.setImage(sandwichJsonObj.getString(IMAGE));

            // set sandwich name info
            JSONObject nameObject = sandwichJsonObj.getJSONObject(NAME);
            // set main name
            sandwich.setMainName(nameObject.getString(MAIN_NAME));
            // get know as list
            JSONArray knownAsJsonArray = nameObject.getJSONArray(KNOWN_AS);
            sandwich.setAlsoKnownAs(getKnownAsList(knownAsJsonArray));

            // set ingredients list
            JSONArray ingredientsJsonArr = sandwichJsonObj.getJSONArray(INGREDIENTS);
            sandwich.setIngredients(getIngredientsList(ingredientsJsonArr));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    private static List<String> getKnownAsList(JSONArray knownAsJsonArray) {
        List<String> knownAsList = new ArrayList<>();
        for (int i = 0; i < knownAsJsonArray.length(); i++) {
            try {
                knownAsList.add(knownAsJsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return knownAsList;
    }

    private static List<String> getIngredientsList(JSONArray ingredientsJsonArr) {
        List<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredientsJsonArr.length(); i++) {
            try {
                ingredientsList.add(ingredientsJsonArr.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ingredientsList;
    }
}
