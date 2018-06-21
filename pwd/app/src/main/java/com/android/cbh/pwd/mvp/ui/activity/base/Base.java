package com.android.cbh.pwd.mvp.ui.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.cbh.pwd.R;
import com.umeng.message.proguard.A;
import com.umeng.message.proguard.B;


//通过getIntent()获得的Intent用于开启Activity(Activity开启本身)时,观察发现Activity不会走onDestroy()方法.
//        那么以下两种获得Intent的方法在开启Activity时,有什么本质区别吗?
//        Intent intent1 = getIntent(); //此方法在MainActivity中调用
//        Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
//        如果利用intent2去startActivity() 那么会开启一个全新的Activity
//        如果用intent1 则不会.
public abstract class Base extends AppCompatActivity {

    private boolean isAnim;

    /**
     * overridePendingTransition mode
     */
    //过度模式，使用枚举，能让我们的代码可读性更强，可以把相关的常量分组到一个枚举类型里，
    public enum TransitionMode {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        SCALE,
        FADE
        //褪色
//        Scale是Android的尺寸缩放动画，继承自基类Animation
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (null != intent) {
            isAnim = intent.getBooleanExtra("anim", false);
        }
        if (isAnim) {
            startAnime();
        }
        super.onCreate(savedInstanceState);
    }
//    finish()方法用于结束销毁掉当前activity。
//    在接受数据的页面，使用getSerializableExtra（）方法获取传递的值，要注意类型的强制转换
    @Override
    public void finish() {
        super.finish();
    }
//    overridePendingTransition Android中不同Activity之间的切换是不可避免的事情，那么怎么才能让Acitivity的切换更优雅呢，
//    Android中提供了一个方法来解决这个问题，即overridePendingTransition(A，B)函数。
//    这个函数有两个参数，一个参数是第一个activity进入时的动画，另外一个参数则是第二个activity退出时的动画。
//    这里需要特别说明的是，关于overridePendingTransition这个函数，有两点需要主意
//1. 它必需紧挨着startActivity()或者finish()函数之后调用”
    private void startAnime() {
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in,R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in,R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in,R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    break;
            }
        }
    }


    protected abstract TransitionMode getOverridePendingTransitionMode();

    protected abstract boolean toggleOverridePendingTransition();

}

//我们知道Activity间数据传递是通过Intent实现的。启动一个Activity:
//private void init() {
//        Intent intent = new Intent(this, ActivityB.class);
//        intent.putExtra("key_1", "value_1");
//        intent.putExtra("key_2", false);
//        startActivity(intent);
//        }
//        上面是我们平时开发中常用的手段，以下是获取数据的代码段：
//private void init() {
//        Intent intent = getIntent();
//        String strValue = intent.getStringExtra("key_1");
//        Boolean blValue = intent.getBooleanExtra("key_2", false);
//        }
