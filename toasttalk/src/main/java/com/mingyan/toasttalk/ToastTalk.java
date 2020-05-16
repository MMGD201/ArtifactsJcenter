package com.mingyan.toasttalk;

import android.content.Context;
import android.widget.Toast;

public class ToastTalk {

    public static void vShort(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void vLong(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
