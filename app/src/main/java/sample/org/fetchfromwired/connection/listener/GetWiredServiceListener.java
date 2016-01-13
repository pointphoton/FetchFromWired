package sample.org.fetchfromwired.connection.listener;


import java.util.List;

import sample.org.fetchfromwired.connection.BaseServiceListener;
import sample.org.fetchfromwired.json.model.ArticleModel;

/**
 * TODO: Add a class header comment!
 */
public interface GetWiredServiceListener<T> extends BaseServiceListener<T> {

	void getLastFiveArticles(List<ArticleModel> articleModels);
}
