package com.anima.yongfei.animationdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anima.yongfei.animationdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongfei on 16/12/8.
 */

public class CardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.card_view_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
//        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        manager.setReverseLayout(false);// 不重用view
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DefaultItemDecoration());
        recyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter {

        private final List<String> mStrings;

        public MyAdapter() {
            mStrings = new ArrayList<>();
            for (int i = 0; i < 300; i++) {
                if (i % 5 == 0){
                    mStrings.add("----------------------- :"+i*2);
                }else if (i % 7 == 0){
                    mStrings.add("coun" +
                            "t :"+i*10000);
                } else if (i % 3 == 0){
                    mStrings.add("+/// :"+i*10 + " ?asdadsasdasdaddddddddddddddddddddd");
                }else {
                    mStrings.add("count :"+i*22);
                }

            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_recycle,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder)holder;
            myViewHolder.mTextView.setText(mStrings.get(position));
        }

        @Override
        public int getItemCount() {
            return mStrings.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycle_tv);
        }
    }

    private class DefaultItemDecoration extends RecyclerView.ItemDecoration {

    }
}
