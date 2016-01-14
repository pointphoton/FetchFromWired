package sample.org.fetchfromwired.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.test.mock.MockApplication;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.org.fetchfromwired.R;
import sample.org.fetchfromwired.connection.listener.GetYandexServiceListener;
import sample.org.fetchfromwired.connection.listener.WiredSL;
import sample.org.fetchfromwired.connection.listener.YandexSL;
import sample.org.fetchfromwired.connection.model.ResponseEventModel;
import sample.org.fetchfromwired.json.model.TranslateModel;
import sample.org.fetchfromwired.link.HttpLink;
import sample.org.fetchfromwired.link.IntentKey;
import sample.org.fetchfromwired.util.MapUtil;

/**
 * TODO: Add a class header comment!
 */
public class ArticleViewActivity extends BaseActivity {

    @Bind(R.id.myWebView)WebView webView;
    @Bind(R.id.btnListFive)Button btnListFive;
   List<String> wList =new ArrayList<>();
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
private static int flag=0;
    private List<String> trWordList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        ButterKnife.bind(this);
        String url = getIntent().getExtras().getString(IntentKey.LINK.name());
        String data = getIntent().getExtras().getString(IntentKey.CONTENT.name());
        Document nonHtml = Jsoup.parse(data);
        String articleHtml = nonHtml.outerHtml();
        String article = nonHtml.text();
        Log.d("*** APP ", article);
        //   webView.loadData(data, "text/html; charset=utf-8", "");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //FIND FIVE MOST FREQUENT WORDS

        String plainArticle = article.replaceAll("[^a-zA-Z\\s]", "");
        plainArticle = plainArticle.toLowerCase();
        String[] words = plainArticle.split(" ");
        List<String> atLeastThreeCharWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 3) {
                atLeastThreeCharWords.add(words[i]);
            }
        }
       Map<String, Integer> wordMap = new HashMap<>();
        for (String word : atLeastThreeCharWords) {
            if (!wordMap.containsKey(word)) {
                wordMap.put(word, 1);
            } else {
                int count =wordMap.get(word);
               count++;
              wordMap.put(word, count);
            }
        }
        wordMap = MapUtil.sortByValue(wordMap);
        List<String> keyList =new ArrayList<>();
        for (Map.Entry<String ,Integer> key: wordMap.entrySet()) {
            Log.d("list ","key :  "+key.getKey()  +" value : "+key.getValue());
            keyList.add(key.getKey());
        }
        final List<String> mostFive  = keyList.subList(0, 5);
        wList =mostFive;
        for (String most :mostFive)
        {
            Log.d("most five", most);

        }

        btnListFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final  YandexSL yandexSL = new YandexSL(ArticleViewActivity.this,getYandexServiceListener, HttpLink.getYandexLink());

                Runnable task = new Runnable() {
                    public void run() {
                        yandexSL.sendYandexData("", mostFive.get(flag));
                    }
                };
                worker.scheduleAtFixedRate(task, 1, 5, TimeUnit.SECONDS);

            }
        });

        //EXTRACT ADVERTISEMENTS IF NECESSARY
/*
       String first = data.substring(0, data.indexOf("<h4"));
      String last = data.substring(data.indexOf("</h5>")+"</h5>".length(), data.length());
       String article = first+last;
        Log.d("*** APP ", index+"");
        Log.d("*** APP ", last);
        Log.d("*** APP ",article);

        */


    }


    GetYandexServiceListener<String> getYandexServiceListener  = new GetYandexServiceListener<String>() {

        @Override
        public void getTranslatedWord(TranslateModel translateModels) {

            String trWord = translateModels.getText().get(0);
            String enWord=wList.get(flag);
            String showWord = enWord+" = "+trWord;

            Toast.makeText(ArticleViewActivity.this,showWord,Toast.LENGTH_SHORT).show();
            Log.d("word ", trWord);
            trWordList.add(trWord);

            for (String w :trWordList) {
                Log.d("word",w);

            }

            flag++;


        }

        @Override
        public void onComplete(ResponseEventModel<String> onComplete) {

        }
    };
}
