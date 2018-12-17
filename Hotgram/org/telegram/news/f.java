package org.telegram.news;

import android.content.Intent;
import android.util.Log;

public class f {
    public static Intent a(String arg2, String arg3, String arg4, boolean arg5) {
        Intent v0 = new Intent("android.intent.action.SEND");
        v0.setType("text/plain");
        v0.addFlags(524288);
        v0.putExtra("android.intent.extra.SUBJECT", arg2);
        if(arg5) {
            arg3 = arg2 + "\n" + arg3 + "\n" + arg4 + "\n\n\nآگهی:\nتگ\nhttp://app.tag.ir/tag.apk";
        }
        else {
            Log.d("sadegh", "is not manuallll");
        }

        v0.putExtra("android.intent.extra.TEXT", arg3);
        return Intent.createChooser(v0, "نرم افزار تگ-انتشار خبر");
    }
}

