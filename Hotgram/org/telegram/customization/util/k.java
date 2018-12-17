package org.telegram.customization.util;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public class k {
    public static String a(Context arg2, String arg3) {
        try {
            AssetManager v2_1 = arg2.getAssets();
            StringBuilder v0 = new StringBuilder();
            v0.append("data/");
            v0.append(arg3);
            InputStream v2_2 = v2_1.open(v0.toString());
            byte[] v3 = new byte[v2_2.available()];
            v2_2.read(v3);
            v2_2.close();
            return new String(v3);
        }
        catch(IOException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }
}

