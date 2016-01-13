package sample.org.fetchfromwired.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sample.org.fetchfromwired.R;
import sample.org.fetchfromwired.activity.ArticleViewActivity;
import sample.org.fetchfromwired.activity.MainActivity;
import sample.org.fetchfromwired.json.model.ArticleModel;

/**
 * TODO: Add a class header comment!
 */
public class ArticleListAdapter extends BaseAdapter {

    private MainActivity activity;
    private Context context;
    private List<ArticleModel> models;



    public ArticleListAdapter(MainActivity activity, List<ArticleModel> models) {
        context = activity;
       this.activity = activity;

        this.models = models;

    }

    @Override public int getCount() {
        if (models.size()>5) {
            return 5;
        }
        else {
          return   models.size();
        }
    }

    @Override public Object getItem(int position) {
        return models.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_main_activity_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }
        final ArticleModel model = (ArticleModel)getItem(position);
        if (model.getSlug() ==null || model.getSlug().isEmpty())
        {
            holder.txtName.setText( holder.txtName.getText().toString());
        }
        else
        {

            String slug = model.getSlug();
            Log.d("APP slug",slug);
            slug = slug.replace("-"," ");
            holder.txtName.setText(WordUtils.capitalize(slug));
        }
        holder.clikableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("APP position", position + "");
                if (model==null)
                {
                    Log.d("model","null");
                }
               activity.setArticleModel(model);

                activity.openView();

            }
        });


        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.row_main_list_header_textView) TextView txtName;
@Bind(R.id.row_main_list_clickable_layout) RelativeLayout clikableLayout;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}