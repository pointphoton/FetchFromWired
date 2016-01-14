package sample.org.fetchfromwired.connection.listener;

import java.util.List;

import sample.org.fetchfromwired.connection.BaseServiceListener;
import sample.org.fetchfromwired.json.model.TranslateModel;

/**
 * TODO: Add a class header comment!
 */
public interface GetYandexServiceListener <T> extends BaseServiceListener<T> {

    void getTranslatedWord(TranslateModel translateModels);
}
