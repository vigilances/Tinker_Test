package com.miracle.demo_6_29_hotpatch;

import android.content.Context;
import android.widget.Toast;

public   class Util {

    private static Toast toast;

    public static void showToast(Context context,
        String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                         content, 
                         Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}