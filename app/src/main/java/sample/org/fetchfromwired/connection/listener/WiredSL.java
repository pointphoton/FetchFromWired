package sample.org.fetchfromwired.connection.listener;

import android.content.Context;
import android.util.Log;

import java.util.List;

import sample.org.fetchfromwired.connection.BaseGetServiceSL;

import sample.org.fetchfromwired.connection.model.ResponseEventModel;
import sample.org.fetchfromwired.connection.model.ServiceResponseModel;
import sample.org.fetchfromwired.connection.service.BaseGetAsyncTask;
import sample.org.fetchfromwired.json.model.ArticleModel;
import sample.org.fetchfromwired.json.parser.ArticleModelParser;
import sample.org.fetchfromwired.link.HttpLink;
import sample.org.fetchfromwired.util.WiredUtil;


public class WiredSL extends BaseGetServiceSL<String> {


    private static final String NAME_OF_THE_CLASS = WiredSL.class.getSimpleName();

    private GetWiredServiceListener<String> getWiredServiceListener;

    public WiredSL(Context appContext, GetWiredServiceListener<String> listener, String serviceResUri) {
        super(appContext, listener, serviceResUri);
        this.getWiredServiceListener = listener;
    }


    // **********************************//
    // ---SEND DATA WEBSERVICE METHODS---
    // **********************************//

    public void sendWiredData(String postData) {

        BaseGetAsyncTask<String> getAsyncTask = new BaseGetAsyncTask<>(context, NAME_OF_THE_CLASS, postData);

        getAsyncTask.addServiceClientListener(this);

        String uri = getServiceUri();
        getAsyncTask.execute(uri, HttpLink.getLastArticlesLink());
    }

    @Override
    public void onGetCommit(ResponseEventModel<String> responseEventModel) {

        String result = responseEventModel.getResponseData();

        if (responseEventModel.getMethodName().equalsIgnoreCase(HttpLink.getLastArticlesLink())) {
            Log.d("WiredSL ", " method name is correct");
            ServiceResponseModel model = WiredUtil.getServiceResponse(result);
            List<ArticleModel> articleModelListResponse = null;
            if (model != null) {
                articleModelListResponse = new ArticleModelParser().getArticleList(model.getModel());
            }
            if (articleModelListResponse == null) {
                //
                Log.d("branch model ", "ERROR");
            } else {
                getWiredServiceListener.getLastFiveArticles(articleModelListResponse);
            }

        }
    }

}