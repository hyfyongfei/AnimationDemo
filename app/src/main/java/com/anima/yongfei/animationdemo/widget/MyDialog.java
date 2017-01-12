package com.anima.yongfei.animationdemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.anima.yongfei.animationdemo.R;

/**
 * Created by yongfei on 16/11/1.
 */

public class MyDialog extends Dialog{

    public MyDialog(Context context) {
        super(context, R.style.custom_dialog);
    }


    public static class Builder{

        private Context mContext;
        private Context context;
        private String left;
        private String right;
        private View.OnClickListener leftOCL;
        private View.OnClickListener rightOCL;
        private Button leftButton;
        private Button rightButton;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setLeftText(String left) {
            this.left=left;
            return this;
        }
        public Builder setRightText(String right) {
            this.right=right;
            return this;
        }
        public Builder setLeftOnClick(View.OnClickListener left) {
            this.leftOCL=left;
            return this;
        }
        public Builder setRightOnClick(View.OnClickListener right) {
            this.rightOCL=right;
            return this;
        }

        public MyDialog create() {
            final MyDialog dialog = new MyDialog(mContext);
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog,null);

            Window window = dialog.getWindow();
            window.getDecorView().setPadding(0,0,0,0);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
            window.setGravity(Gravity.CENTER);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(true);
            return dialog;
        }

        public MyDialog show(){
            MyDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }

}
