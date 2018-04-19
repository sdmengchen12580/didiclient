package org.faqrobot.textrecyclerview.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.ui.baseact.RxBaseActivity;


//app图标为：逗比附庸开小车
public class LeaderActivity extends RxBaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaderActivity.this,HomeActivity.class));
                finish();
            }
        });
    }

    @Override
    public void initToolBar() {

    }


}
