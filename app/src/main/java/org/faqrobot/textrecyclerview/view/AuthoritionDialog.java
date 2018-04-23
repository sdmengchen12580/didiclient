package org.faqrobot.commonrobot_v2.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

/**
 * Created by 孟晨 on 2018/4/20.
 */

public class AuthoritionDialog extends Dialog  {

    public AuthoritionDialog(@NonNull Context context) {
        super(context);
    }

    public AuthoritionDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
