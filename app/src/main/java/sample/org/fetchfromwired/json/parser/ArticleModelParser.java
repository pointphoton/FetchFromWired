package sample.org.fetchfromwired.json.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import sample.org.fetchfromwired.json.model.ArticleModel;

/**
 * TODO: Add a class header comment!
 */
public class ArticleModelParser {

    public List<ArticleModel> getArticleList(String json) {

        List<ArticleModel> model = null;
        try {
            Type type = new TypeToken<List<ArticleModel>>() {
            }.getType();

            model = new Gson().fromJson(json, type);

        } catch (Exception ex) {
            Log.d("getArticleList JSON", ex.getMessage());
        }

        return model;
    }
}
