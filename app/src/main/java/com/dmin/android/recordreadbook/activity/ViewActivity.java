package com.dmin.android.recordreadbook.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.dmin.android.recordreadbook.R;
import com.dmin.android.recordreadbook.adapter.BookListAdapter;
import com.dmin.android.recordreadbook.db.DB;
import com.dmin.android.recordreadbook.dialog.CustomDialog;

/**
 * Created by dmin on 2016/4/21.
 */
public class ViewActivity extends Activity {
    private ListView mListView;
    private DB db;
    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;
    private BookListAdapter adapter;
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //数据库
        db = new DB(ViewActivity.this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        Cursor cursor = dbRead.query("book", null, null, null, null, null, null);

        adapter = new BookListAdapter(this, R.layout.book_list_item, cursor,
                new String[]{"title", "page", "percent"},
                new int[]{R.id.title_text_view, R.id.page_text_view, R.id.percent_text_view}, 0);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(adapter);
        refreshView();

        //长按删除书本
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(ViewActivity.this)
                        .setTitle("提醒")
                        .setMessage("是否要删除")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            Cursor cursor = adapter.getCursor();

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbWrite.delete("book", "_id=?",
                                        new String[]{cursor.getInt(cursor.getColumnIndex("_id")) + " "});
                                refreshView();
                            }
                        }).show();

                return true;
            }
        });

        //点击弹出对话框
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new CustomDialog(ViewActivity.this, R.layout.dialog_custom);
                final EditText editText = (EditText) dialog.getEditText();

                Cursor c = adapter.getCursor();
                final int page = c.getInt(c.getColumnIndex("page"));
                final int ID = c.getInt(c.getColumnIndex("_id"));

                dialog.getPosBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editText.getText().toString().equals("")) {
                            int currentPage = Integer.parseInt(editText.getText().toString());
                            int percent = (int) (((double) currentPage / page) * 100);

                            ContentValues cv = new ContentValues();
                            cv.clear();
                            cv.put("percent", percent);
                            cv.put("page", page);
                            dbWrite.update("book", cv, "_id=?", new String[]{ID + ""});

                            refreshView();
                            dialog.dismiss();
                        }else{
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

    }

    public void refreshView() {
        Cursor cursor = dbRead.query("book", null, null, null, null, null, null);
        adapter.changeCursor(cursor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(db != null){
            db.close();
        }

        Cursor c = adapter.getCursor();
        if(c != null && !c.isClosed() ){
            c.close();
        }
    }

    //好像没有向左的图标 <
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActionBar().setHomeButtonEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.home:
                finish();
                break;
        }
        return true;
    }
}
