package com.anima.yongfei.animationdemo.widget;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anima.yongfei.animationdemo.R;

import java.util.List;

/**
 * Created by yongfei on 16/12/21.
 */

public class HintPopwindow {

    private Activity mActivity;
    private Context mContext;
    private View mRootView;
    private LinearLayout mPopupContainer;

    public HintPopwindow(Activity activity, List<String> contentDesc) {
        mActivity = activity;
        mContext = mActivity.getBaseContext();
        init(contentDesc);
    }

    private void init(List<String> contentDesc) {
        // 获取windowManager
        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);

        mRootView = View.inflate(mActivity, R.layout.pupwindow_layout, null);
        mPopupContainer = (LinearLayout) mRootView.findViewById(R.id.popup_window_contain);

        if (contentDesc == null || contentDesc.size() <= 0){
            Log.e("###########","error !!!");
            return;
        }


        for (int i = 0; i < contentDesc.size(); i++) {
            TextView itemText = new TextView(mActivity);
            mPopupContainer.addView(itemText);
        }
    }
}
