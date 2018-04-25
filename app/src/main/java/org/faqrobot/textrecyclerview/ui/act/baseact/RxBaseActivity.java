package org.faqrobot.textrecyclerview.ui.act.baseact;

import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract class RxBaseActivity extends RxAppCompatActivity {
    public abstract int getLayoutId();
    public abstract void initViews(Bundle savedInstanceState);
    public abstract void initToolBar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        ButterKnife.bind(this);
        //初始化ToolBar
        initToolBar();
        //初始化控件
        initViews(savedInstanceState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
