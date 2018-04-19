package org.faqrobot.textrecyclerview.ui.act;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import org.faqrobot.textrecyclerview.R;
import org.faqrobot.textrecyclerview.callback.BottomClickCallback;
import org.faqrobot.textrecyclerview.ui.baseact.RxBaseActivity;
import org.faqrobot.textrecyclerview.ui.fra.client.ClientFragment;
import org.faqrobot.textrecyclerview.ui.fra.gettask.GetTaskFragment;
import org.faqrobot.textrecyclerview.ui.fra.home.HomeFragment;
import org.faqrobot.textrecyclerview.ui.fra.my.MyFragment;
import org.faqrobot.textrecyclerview.ui.fra.prividertask.ProviderTaskFragment;
import org.faqrobot.textrecyclerview.utils.ChangeUi;
import org.faqrobot.textrecyclerview.utils.ToastUtils;

public class HomeActivity extends RxBaseActivity {

    private BottomNavigationBar bottomNavigationBar;
    private Fragment[] fragments;
    private int currentFra = 0;
    private Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        initFragment();
        initBottomBar();
    }

    @Override
    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFragment() {
        HomeFragment homeFra = HomeFragment.getInstance();
        GetTaskFragment getTaskFra = GetTaskFragment.getInstance();
        ProviderTaskFragment providerTaskFra = ProviderTaskFragment.getInstance();
        ClientFragment clientFra = ClientFragment.getInstance();
        MyFragment myFra = MyFragment.getInstance();

        fragments = new Fragment[]{homeFra, getTaskFra, providerTaskFra, clientFra, myFra};

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, homeFra)
                .commit();
    }

    private void initBottomBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页")
                        .setInactiveIcon(ContextCompat.getDrawable(HomeActivity.this, R.drawable.homeact_bottom_img)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "接榜")
                        .setInactiveIcon(ContextCompat.getDrawable(HomeActivity.this, R.drawable.homeact_bottom_img)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "发榜")
                        .setInactiveIcon(ContextCompat.getDrawable(HomeActivity.this, R.drawable.homeact_bottom_img)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "附庸")
                        .setInactiveIcon(ContextCompat.getDrawable(HomeActivity.this, R.drawable.homeact_bottom_img)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "我的")
                        .setInactiveIcon(ContextCompat.getDrawable(HomeActivity.this, R.drawable.homeact_bottom_img)))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomClickCallback() {
            @Override
            public void clickFirst() {
                //如果0位置，点击0位置时，不会触发此回调
                replaceFra(0);
            }

            @Override
            public void clickTwo() {
                replaceFra(1);
            }

            @Override
            public void clickThree() {
                replaceFra(2);
            }

            @Override
            public void clickFour() {
                replaceFra(3);
            }

            @Override
            public void clickFive() {
                replaceFra(4);
            }
        });
    }

    private void replaceFra(int changeToNumber) {
        if (currentFra == changeToNumber) {
            return;
        }
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragments[currentFra]);

        if (!fragments[changeToNumber].isAdded()) {
            trx.add(R.id.content, fragments[changeToNumber]);
        }
        trx.show(fragments[changeToNumber]).commit();
        currentFra = changeToNumber;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                ChangeUi.changeUi(this, FaqrobotActvity.class);
                break;
            case R.id.action_item2:
                ToastUtils.show("开发中");
                break;
            case R.id.action_item3:
                ToastUtils.show("开发中");
                break;
            case R.id.action_item4:
                ToastUtils.show("开发中");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
