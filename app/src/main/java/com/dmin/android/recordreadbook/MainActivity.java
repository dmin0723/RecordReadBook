package com.dmin.android.recordreadbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dmin.android.recordreadbook.activity.SearchActivity;
import com.dmin.android.recordreadbook.activity.ViewActivity;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout viewRelatvieLayout;
    private RelativeLayout searchRelativeLayout;
    private ImageView mainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageView = (ImageView) findViewById(R.id.main_image_view);
        viewRelatvieLayout = (RelativeLayout) findViewById(R.id.view_relative_layout);
        searchRelativeLayout = (RelativeLayout) findViewById(R.id.main_search_relative_layout);

        viewRelatvieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });

        searchRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
