package com.dmin.android.recordreadbook.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.dmin.android.recordreadbook.R;
import com.dmin.android.recordreadbook.adapter.SearchAdapter;
import com.dmin.android.recordreadbook.book.Book;
import com.dmin.android.recordreadbook.db.DB;
import com.dmin.android.recordreadbook.dialog.AddDialog;
import com.dmin.android.recordreadbook.util.KeyBoardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmin on 2016/4/21.
 */
public class SearchActivity extends Activity {
    private ListView listView;
    private EditText searchEditText;
    private Button searchButton;

    private List<Book> mBooks = new ArrayList<Book>();
    private SearchAdapter mAdapter;
    private DB db;
    private SQLiteDatabase dbWrite;
    private AddDialog addDialog;
    private int mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView) findViewById(R.id.search_list_view);
        searchEditText = (EditText) findViewById(R.id.search_content_edit_text);
        searchButton = (Button) findViewById(R.id.search_button);
        db = new DB(SearchActivity.this);
        dbWrite = db.getWritableDatabase();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mItem = position;
                //和ViewActivity有点区别
                addDialog = new AddDialog(SearchActivity.this,R.style.dialog_style);
                ImageButton addImageButton = (ImageButton) addDialog.getPosBtn();
                addImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues cv = new ContentValues();
                        cv.clear();
                        cv.put("title",mBooks.get(mItem).getTitle());
                        cv.put("page",mBooks.get(mItem).getPage());

                        dbWrite.insert("book",null,cv);
                        addDialog.dismiss();
                        Toast.makeText(SearchActivity.this, "已添加", Toast.LENGTH_SHORT).show();
                    }
                });
                addDialog.show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtils.closeKeyBoard(SearchActivity.this);
                getRequestedDate(searchButton.getText().toString());
            }
        });
    }


}
