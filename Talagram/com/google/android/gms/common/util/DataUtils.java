package com.google.android.gms.common.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.CharArrayBuffer;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class DataUtils {
    public DataUtils() {
        super();
    }

    public static void copyStringToBuffer(String arg3, CharArrayBuffer arg4) {
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            arg4.sizeCopied = 0;
        }
        else {
            if(arg4.data != null) {
                if(arg4.data.length < arg3.length()) {
                }
                else {
                    arg3.getChars(0, arg3.length(), arg4.data, 0);
                    goto label_18;
                }
            }

            arg4.data = arg3.toCharArray();
        }

    label_18:
        arg4.sizeCopied = arg3.length();
    }

    public static byte[] loadImageBytes(AssetManager arg0, String arg1) {
        try {
            return IOUtils.readInputStreamFully(arg0.open(arg1));
        }
        catch(IOException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }

    @VisibleForTesting public static byte[] loadImageBytes(Resources arg0, int arg1) {
        try {
            return IOUtils.readInputStreamFully(arg0.openRawResource(arg1));
        }
        catch(IOException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }

    public static byte[] loadImageBytes(Bitmap arg1) {
        return DataUtils.loadImageBytes(arg1, 100);
    }

    public static byte[] loadImageBytes(Bitmap arg2, int arg3) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        arg2.compress(Bitmap$CompressFormat.JPEG, arg3, ((OutputStream)v0));
        return v0.toByteArray();
    }

    public static byte[] loadImageBytes(BitmapDrawable arg0) {
        return DataUtils.loadImageBytes(arg0.getBitmap());
    }
}

