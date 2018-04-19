package org.faqrobot.textrecyclerview.callback;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

/**
 * Created by 孟晨 on 2018/4/9.
 */

public abstract class BottomClickCallback implements BottomNavigationBar.OnTabSelectedListener {

    public abstract void clickFirst();
    public abstract void clickTwo();
    public abstract void clickThree();
    public abstract void clickFour();
    public abstract void clickFive();

    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                clickFirst();
                break;
            case 1:
                clickTwo();
                break;
            case 2:
                clickThree();
                break;
            case 3:
                clickFour();
                break;
            case 4:
                clickFive();
                break;
            default:
                break;
        }
    }
    @Override
    public void onTabUnselected(int position) {

    }
    @Override
    public void onTabReselected(int position) {

    }
}
