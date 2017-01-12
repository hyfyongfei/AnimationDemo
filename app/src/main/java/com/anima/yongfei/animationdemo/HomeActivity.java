package com.anima.yongfei.animationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by yongfei on 17/1/11.
 */

public class HomeActivity extends Activity {

    private ListView mListView;
    private ArrayList<String> mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initData();

        mListView = (ListView) findViewById(R.id.lv_content);

        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStrings));

        setListViewClick();
    }

    private void setListViewClick() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setItemClick(position);
            }
        });
    }

    private void initData() {
        mStrings = new ArrayList<>();

        mStrings.add("PropertyValuesHolder 属性动画");

        mStrings.add("item 2");

        mStrings.add("item 3");
    }


    public void setItemClick(int itemClick) {
        switch(itemClick){
            case 0:
                startActivity1();
                break;
        }

    }

    private void startActivity1() {
        Intent intent = new Intent(this, TranslateActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
    }


}
