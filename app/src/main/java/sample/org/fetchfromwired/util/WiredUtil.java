package sample.org.fetchfromwired.util;

import android.util.Log;
import android.util.Pair;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.List;

import sample.org.fetchfromwired.connection.model.ServiceResponseModel;

/**
 * TODO: Add a class header comment!
 */
public class WiredUtil {

    public static ServiceResponseModel getServiceResponse(String jsonResult) {

        ServiceResponseModel model = new ServiceResponseModel();

        try {
			/*
			JsonElement jelement = new JsonParser().parse(jsonResult);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonElement element = jobject.get("productList");
			if (element != null) {

				model.setModel(element.toString());
				*/
            JsonElement jelement = new JsonParser().parse(jsonResult);
            model.setModel(jelement.toString());


        }

        catch (Exception e) {
            Log.d(" getServiceResponse", e.getMessage());
        }

        return model;

    }

    public static String formattedData(List<Pair<String, String>> params) {

        if (params == null) {
            return null;
        }
        String retunValue = "{ ";

        if (params != null && params.size() > 0) {

            for (int i = 0; i < params.size(); i++) {

                if (params.size() > 1) {

                    if (params.size() - 1 == i) {
                        retunValue += "\"" + params.get(i).first + "\" : ";
                        retunValue += "\"" + params.get(i).second + "\"";
                    } else {
                        retunValue += "\"" + params.get(i).first + "\" : ";
                        retunValue += "\"" + params.get(i).second + "\"";
                        retunValue += " , ";
                    }

                } else {
                    retunValue += "\"" + params.get(i).first + "\" : ";
                    retunValue += "\"" + params.get(i).second + "\"";
                }

            }
        }
        retunValue += " }";
        return retunValue;
    }
}
