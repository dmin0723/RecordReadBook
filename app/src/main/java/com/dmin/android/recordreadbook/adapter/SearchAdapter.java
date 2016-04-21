package com.dmin.android.recordreadbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmin.android.recordreadbook.R;
import com.dmin.android.recordreadbook.book.Book;
import com.dmin.android.recordreadbook.util.ImageLoader;

import java.util.List;

/**
 * Created by dmin on 2016/4/21.
 */
public class SearchAdapter extends BaseAdapter {
    List<Book> mList;
    LayoutInflater mInflater;

    public SearchAdapter(List<Book> list, Context context) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        String mUrl = mList.get(position).getBitmap();

        if(view == null){
            view = mInflater.inflate(R.layout.search_item,null);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) view.findViewById(R.id.search_title_text_view);
            holder.authorTextView = (TextView) view.findViewById(R.id.author_text_view);
            holder.imageView = (ImageView) view.findViewById(R.id.icon_image_view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageResource(R.drawable.defaultbook);
        holder.imageView.setTag(mUrl);
        new ImageLoader().showImageByAsynctask(holder.imageView,mUrl);

        holder.titleTextView.setText(mList.get(position).getTitle());
        holder.authorTextView.setText("作者:"+mList.get(position).getAuthor());

        return view;
    }

    public void setDate(List<Book> list){
        mList = list;
    }

    class ViewHolder {
        public TextView titleTextView;
        public TextView authorTextView;
        public ImageView imageView;
    }
}
