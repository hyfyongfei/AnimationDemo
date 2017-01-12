package com.anima.yongfei.animationdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anima.yongfei.animationdemo.R;


/**
 * Created by yongfei on 16/11/4.
 */

public class MyFragmentB extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my ,container,false);
        TextView tv = (TextView) view.findViewById(R.id.tv_fragment_content);
        tv.setText("22222222 000000000------");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

}
