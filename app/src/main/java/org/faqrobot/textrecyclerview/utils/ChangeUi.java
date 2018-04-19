package org.faqrobot.textrecyclerview.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by 孟晨 on 2018/4/16.
 */

public class ChangeUi {

    public static void changeUi(Activity thisAct,Class<?> toClass) {
        thisAct.startActivity(new Intent(thisAct,toClass));
    }
}
