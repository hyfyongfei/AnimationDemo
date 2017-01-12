package com.anima.yongfei.animationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by yongfei on 16/12/19.
 */

public class TestActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        mTextView = (TextView) findViewById(R.id.input_text_test);

        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.v("-----","beforeTextChanged"+ s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.v("-----","onTextChanged" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("-----","afterTextChanged"+s);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextView.setText("哈哈哈哈");
    }
}
