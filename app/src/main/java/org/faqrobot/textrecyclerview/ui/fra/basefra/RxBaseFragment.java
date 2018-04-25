package org.faqrobot.textrecyclerview.ui.fra.basefra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

public abstract class RxBaseFragment extends RxFragment {

    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("测试fra生命周期", "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("测试fra生命周期", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("测试fra生命周期", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("测试fra生命周期", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("测试fra生命周期", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("测试fra生命周期", "onDestroy: ");
    }

    public abstract int getLayoutId();

    public abstract void initViews(View view);
}
