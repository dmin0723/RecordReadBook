package com.dmin.android.recordreadbook.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dmin.android.recordreadbook.R;

/**
 * Created by dmin on 2016/4/21.
 */
public class BookListAdapter extends SimpleCursorAdapter {
    private LayoutInflater mInflater;
    private TextView titleTextView;
    private TextView pageTextView;
    private TextView progressTextView;
    private ProgressBar mProgressBar;

    public BookListAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        View convertView = null;
        if(view == null){
            convertView = mInflater.inflate(R.layout.book_list_item,null);
        }else{
            convertView = view;
        }

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String percent = cursor.getString(cursor.getColumnIndex("percent"));
        String page = cursor.getString(cursor.getColumnIndex("page"));

        titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);
        pageTextView = (TextView) convertView.findViewById(R.id.page_text_view);
        progressTextView = (TextView) convertView.findViewById(R.id.percent_text_view);
        mProgressBar = (ProgressBar) convertView.findViewById(R.id.progress);

        titleTextView.setText(title);
        pageTextView.setText(page + "页");
        progressTextView.setText(percent + "%");
        mProgressBar.setMax(100);
        mProgressBar.setProgress(Integer.parseInt(percent));
    }
}
