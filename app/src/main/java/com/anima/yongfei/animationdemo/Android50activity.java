package com.anima.yongfei.animationdemo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.anima.yongfei.animationdemo.fragment.CardFragment;
import com.anima.yongfei.animationdemo.utils.QRCode;

/**
 * Created by yongfei on 16/12/8.
 *
 * activity启动模式 ：
 *   singleTop：如果当前activity在栈顶 则不会创建一个新的activity而是调用 onNewIntent();
 *
 *  singleTop 适合接受通知
 *  singleTask 适合浏览器主页
 *  singleInstance 适合IPC打开的页面
 */

public class Android50activity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_view_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mImageView = (ImageView) findViewById(R.id.qr_image);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        startFragment();

        toolbar.showOverflowMenu();

        setQRImage();
    }


    private void startFragment(){
        CardFragment cardFragment = new CardFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,cardFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
//        View actionView = getLayoutInflater().inflate(R.layout.android_text,null);
//        menu.add("hahaha")
//                .setActionView(actionView)
//                .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setQRImage(){
        mImageView.setImageBitmap(QRCode.createQRCodeWithLogo2("http://www.tmtpost.com/2536837.html",500,drawableToBitmap(getResources().getDrawable(R.mipmap.ic_launcher))));
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}

