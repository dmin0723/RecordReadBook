package com.dmin.android.recordreadbook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dmin.android.recordreadbook.R;

/**
 * Created by dmin on 2016/4/21.
 */
public class SearchActivity extends Activity {
    private ListView listView;
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView) findViewById(R.id.search_list_view);
        searchEditText = (EditText) findViewById(R.id.search_content_edit_text);
        searchButton = (Button) findViewById(R.id.search_button);
    }
}
