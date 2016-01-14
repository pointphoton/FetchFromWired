package sample.org.fetchfromwired.connection.listener;

import android.content.Context;
import android.util.Log;

import java.util.List;

import sample.org.fetchfromwired.connection.BaseGetServiceSL;
import sample.org.fetchfromwired.connection.model.ResponseEventModel;
import sample.org.fetchfromwired.connection.model.ServiceResponseModel;
import sample.org.fetchfromwired.connection.service.BaseGetAsyncTask;
import sample.org.fetchfromwired.json.model.ArticleModel;
import sample.org.fetchfromwired.json.model.TranslateModel;
import sample.org.fetchfromwired.json.parser.ArticleModelParser;
import sample.org.fetchfromwired.json.parser.TranslateModelParser;
import sample.org.fetchfromwired.link.HttpLink;
import sample.org.fetchfromwired.util.WiredUtil;

/**
 * TODO: Add a class header comment!
 */
public class YandexSL  extends BaseGetServiceSL<String> {


    private static final String NAME_OF_THE_CLASS = YandexSL.class.getSimpleName();
    private String name;

    private GetYandexServiceListener<String> getYandexServiceListener;

    public YandexSL(Context appContext, GetYandexServiceListener<String> listener, String
            serviceResUri) {
        super(appContext, listener, serviceResUri);
        this.getYandexServiceListener = listener;
    }

    // **********************************//
    // ---SEND DATA WEBSERVICE METHODS---
    // **********************************//

    public void sendYandexData(String postData, String word) {

        BaseGetAsyncTask<String> getAsyncTask = new BaseGetAsyncTask<>(context, NAME_OF_THE_CLASS, postData);

        getAsyncTask.addServiceClientListener(this);

        String uri = getServiceUri();
        name = word.toLowerCase() + HttpLink.getYandexEnTr();
        getAsyncTask.execute(uri, name);
    }


    @Override
    public void onGetCommit(ResponseEventModel<String> responseEventModel) {


        String result = responseEventModel.getResponseData();

        if (responseEventModel.getMethodName().equalsIgnoreCase(name)) {
            Log.d("WiredSL ", " method name is correct");
            ServiceResponseModel model = WiredUtil.getServiceResponse(result);
            TranslateModel wordResponse = null;
            if (model != null) {
                wordResponse = new TranslateModelParser().getWord(model.getModel());
            }
            if (wordResponse == null) {
                //
                Log.d("branch model ", "ERROR");
            } else {
                getYandexServiceListener.getTranslatedWord(wordResponse);
            }

        }


    }

}
