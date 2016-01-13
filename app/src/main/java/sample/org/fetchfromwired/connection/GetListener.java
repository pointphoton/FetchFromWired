package sample.org.fetchfromwired.connection;

import java.util.EventListener;

import sample.org.fetchfromwired.connection.model.ResponseEventModel;


public interface GetListener<T> extends EventListener {

	void onGetCommit(ResponseEventModel<T> responseEventModel);
}
