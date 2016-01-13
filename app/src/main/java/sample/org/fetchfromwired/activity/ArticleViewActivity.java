package sample.org.fetchfromwired.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Xml;
import android.webkit.MimeTypeMap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.org.fetchfromwired.R;

/**
 * TODO: Add a class header comment!
 */
public class ArticleViewActivity extends BaseActivity {
    //@Bind(R.id.textView)TextView textView;
    @Bind(R.id.myWebView)WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_article_view);
    //    setContentView(R.layout.layout);
        ButterKnife.bind(this);
        String data = getIntent().getExtras().getString("data");
        int first  =data.indexOf("<h4");
        String a = data.substring(0, first);
int n =data.indexOf("/n");
        Log.d("data ",n+"");
        Log.d("/n ",data);
        //int first =data.indexOf("<p>");
        int last =data.lastIndexOf("</h5>");
        String b = data.substring(last, data.length());
        String c = a+b;
        c = c.replace("/n","");
        c = c.replace("/t","");


        Log.d("index", first + " " + last);
       webView.loadData(c, "text/html; charset=utf-8", "");
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
       webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

    }
}
