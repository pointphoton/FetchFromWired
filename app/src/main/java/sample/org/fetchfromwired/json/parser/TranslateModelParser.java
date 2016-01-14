package sample.org.fetchfromwired.json.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import sample.org.fetchfromwired.json.model.ArticleModel;
import sample.org.fetchfromwired.json.model.TranslateModel;

/**
 * TODO: Add a class header comment!
 */
public class TranslateModelParser {
    public TranslateModel getWord(String json) {

       TranslateModel model = null;
        try {
            Type type = new TypeToken<TranslateModel>() {
            }.getType();

            model = new Gson().fromJson(json, type);

        } catch (Exception ex) {
            Log.d("getWord JSON", ex.getMessage());
        }

        return model;
    }
}
