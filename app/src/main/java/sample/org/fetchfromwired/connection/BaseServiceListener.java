package sample.org.fetchfromwired.connection;

import java.util.EventListener;

import sample.org.fetchfromwired.connection.model.ResponseEventModel;


public interface BaseServiceListener<TModel> extends EventListener {

	public void onComplete(ResponseEventModel<TModel> onComplete);


}