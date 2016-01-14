package sample.org.fetchfromwired.json.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TranslateModel {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private List<String> text = new ArrayList<String>();

    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return The text
     */
    public List<String> getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(List<String> text) {
        this.text = text;
    }
}