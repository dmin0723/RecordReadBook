package com.dmin.android.recordreadbook.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by dmin on 2016/4/21.
 */
public class KeyBoardUtils {
    //打开键盘
    public static void showKeyBoard(Activity activity){
        InputMethodManager imm = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null){
           imm.toggleSoftInput(0,InputMethodManager.SHOW_IMPLICIT);
        }
    }


    //关闭键盘
    public static void closeKeyBoard(Context context){
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if(imm.isActive()){
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
        }
    }
}
