package sample.org.fetchfromwired.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.org.fetchfromwired.R;
import sample.org.fetchfromwired.adapter.ArticleListAdapter;
import sample.org.fetchfromwired.connection.listener.GetWiredServiceListener;
import sample.org.fetchfromwired.connection.listener.WiredSL;
import sample.org.fetchfromwired.connection.model.ResponseEventModel;
import sample.org.fetchfromwired.json.model.ArticleModel;
import sample.org.fetchfromwired.link.HttpLink;
import sample.org.fetchfromwired.link.IntentKey;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_activity_listView)ListView listView;
    private ArticleModel articleModel =new ArticleModel();
    private  String article ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       // articleModel=new ArticleModel();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        WiredSL wiredSL = new WiredSL(MainActivity.this,getWiredServiceListener, HttpLink.getWiredLink());
        wiredSL.sendWiredData("");
        showProgress(getResources().getString(R.string.connecting));
    }

    public void  openView(){
        Intent intent = new Intent(this, ArticleViewActivity.class);
        intent.putExtra(IntentKey.LINK.name(),articleModel.getLink());
        intent.putExtra(IntentKey.CONTENT.name(),articleModel.getContent().getRendered());
        startActivity(intent);
    }


    GetWiredServiceListener<String> getWiredServiceListener = new GetWiredServiceListener<String>() {

        @Override
        public void getLastFiveArticles(List<ArticleModel> articleModels) {
            dismissProgress();
            if (articleModels!=null) {
                Log.d("article models size", articleModels.size() + " ");
                listView.setAdapter(new ArticleListAdapter(MainActivity.this, articleModels));
            }
        }

        @Override
        public void onComplete(ResponseEventModel<String> onComplete) {

        }
    };

    public ArticleModel getArticleModel() {
        return articleModel;
    }

    public MainActivity setArticleModel(ArticleModel articleModel) {
        this.articleModel = articleModel;
        return this;
    }

    public String getArticle() {
        return article;
    }

    public MainActivity setArticle(String article) {
        this.article = article;
        return this;
    }
}
