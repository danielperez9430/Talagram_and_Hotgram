package com.persianswitch.sdk.base.utils;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore$Images$Media;
import android.view.View;
import com.persianswitch.sdk.base.log.SDKLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class ScreenshotUtils {
    public ScreenshotUtils() {
        super();
    }

    public static String a(Context arg3) {
        String v0 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US).format(new Date());
        File v1 = new File(arg3.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "AsanPardakht");
        if(!v1.exists()) {
            v1.mkdirs();
        }

        StringBuilder v2 = new StringBuilder();
        v2.append(v0);
        v2.append(".jpg");
        return new File(v1, v2.toString()).getAbsolutePath();
    }

    public static boolean a(View arg6, String arg7, boolean arg8) {
        try {
            Context v1 = arg6.getContext().getApplicationContext();
            arg6.setDrawingCacheEnabled(true);
            arg6.setDrawingCacheEnabled(true);
            arg6.buildDrawingCache();
            Bitmap v3 = Bitmap.createBitmap(arg6.getDrawingCache());
            arg6.setDrawingCacheEnabled(false);
            File v6_1 = new File(arg7);
            v6_1.createNewFile();
            FileOutputStream v4 = new FileOutputStream(v6_1);
            v3.compress(Bitmap$CompressFormat.JPEG, 80, ((OutputStream)v4));
            ((OutputStream)v4).flush();
            ((OutputStream)v4).close();
            v3.recycle();
            if(arg8) {
                ContentValues v6_2 = new ContentValues();
                v6_2.put("title", "AsanPardakht");
                v6_2.put("description", "Transaction Screenshot");
                v6_2.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                v6_2.put("mime_type", "image/jpeg");
                v6_2.put("_data", arg7);
                v1.getContentResolver().insert(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, v6_2);
            }
        }
        catch(Exception v6) {
            SDKLog.b("ScreenshotUtils", "error in take screenshot from view", ((Throwable)v6), new Object[0]);
            return 0;
        }

        return 1;
    }
}

