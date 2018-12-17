package org.telegram.customization.b;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode$VmPolicy$Builder;
import android.os.StrictMode;
import android.util.Log;
import c.a.a.a.i;
import com.d.a.b.a.g;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.telegram.customization.fetch.c;
import org.telegram.customization.g.d;
import org.telegram.messenger.ApplicationLoader;
import utils.a.b;

public class a extends Application implements d {
    public static boolean KEEP_ORIGINAL_FILENAME;
    public static boolean SHOW_ANDROID_EMOJI;
    public static boolean USE_DEVICE_FONT;
    public static org.telegram.b.a databaseHandler;
    public static c fetch;
    private static com.d.a.b.c options;

    static {
    }

    public a() {
        super();
    }

    private void CopyAssets() {
        String[] v1_1;
        AssetManager v0 = this.getAssets();
        try {
            v1_1 = v0.list("data");
        }
        catch(IOException v1) {
            Log.e("tag", v1.getMessage());
            v1_1 = null;
        }

        int v2 = v1_1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            String v4 = v1_1[v3];
            if(v4.contentEquals("white_bg.jpg")) {
                PrintStream v5 = System.out;
                v5.println("File name => " + v4);
                try {
                    StringBuilder v5_1 = new StringBuilder();
                    v5_1.append("data/");
                    v5_1.append(v4);
                    InputStream v5_2 = v0.open(v5_1.toString());
                    StringBuilder v7 = new StringBuilder();
                    v7.append(Environment.getExternalStorageDirectory().toString());
                    v7.append("/");
                    v7.append(v4);
                    FileOutputStream v6_1 = new FileOutputStream(v7.toString());
                    this.copyFile(v5_2, ((OutputStream)v6_1));
                    v5_2.close();
                    ((OutputStream)v6_1).flush();
                    ((OutputStream)v6_1).close();
                }
                catch(Exception v4_1) {
                    Log.e("tag", v4_1.getMessage());
                }
            }
        }
    }

    protected void attachBaseContext(Context arg1) {
        super.attachBaseContext(arg1);
        android.support.e.a.a(((Context)this));
    }

    private void copyFile(InputStream arg4, OutputStream arg5) {
        byte[] v0 = new byte[1024];
        while(true) {
            int v1 = arg4.read(v0);
            if(v1 == -1) {
                return;
            }

            arg5.write(v0, 0, v1);
        }
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode$VmPolicy$Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
    }

    public static org.telegram.b.a getDatabaseHandler() {
        if(a.databaseHandler == null) {
            try {
                a.databaseHandler = new org.telegram.b.a(ApplicationLoader.applicationContext);
                goto label_6;
            }
            catch(Exception ) {
            label_6:
                return a.databaseHandler;
            }
        }

        goto label_6;
    }

    public static com.d.a.b.c getImageOptions() {
        if(a.options == null) {
            a.options = new com.d.a.b.c$a().a(true).c(true).b(true).c(17170445).a();
        }

        return a.options;
    }

    public static c getInstanceLocal() {
        if(a.fetch == null) {
            new org.telegram.customization.fetch.c$a(ApplicationLoader.applicationContext).a(200).a(false).b(2).a();
            a.fetch = c.b(ApplicationLoader.applicationContext);
            a.fetch.a();
            return a.fetch;
        }

        return a.fetch;
    }

    public static void initUniversaImageLoader(Context arg1) {
        com.d.a.b.e$a v0 = new com.d.a.b.e$a(arg1);
        v0.a(3);
        v0.a();
        v0.a(new com.d.a.a.a.b.c());
        v0.b(52428800);
        v0.a(g.b);
        v0.b();
        com.d.a.c.c.a();
        com.d.a.b.d.a().a(v0.c());
    }

    public void onCreate() {
        super.onCreate();
        c.a.a.a.c.a(((Context)this), new i[]{new com.crashlytics.android.a()});
        try {
            new org.telegram.customization.fetch.c$a(this.getApplicationContext()).a(200).a(false).b(2).a();
            a.fetch = c.b(this.getApplicationContext());
            a.fetch.a();
            goto label_22;
        }
        catch(Exception ) {
        label_22:
            a.databaseHandler = new org.telegram.b.a(this.getApplicationContext());
            org.telegram.customization.g.c.a(this.getApplicationContext());
            a.initUniversaImageLoader(((Context)this));
            this.getString(2131625934);
            this.getString(2131623937);
            if(b.L(this.getApplicationContext()) == 0) {
                b.q(this.getApplicationContext(), System.currentTimeMillis());
            }

            org.telegram.customization.work.a.e();
            int v0 = b.i(((Context)this));
            if(v0 == 0 || v0 != 166) {
                b.f(this.getApplicationContext(), "");
                b.a(this.getApplicationContext(), "");
                b.a(this.getApplicationContext(), false);
                b.t(this.getApplicationContext(), System.currentTimeMillis());
                b.b(this.getApplicationContext(), new ArrayList());
                try {
                    if(b.T(ApplicationLoader.applicationContext) != null) {
                        if("hotgram".contentEquals("hotgram")) {
                        }
                        else {
                            "hotgram".contentEquals("talagram");
                        }
                    }

                    goto label_71;
                }
                catch(Exception ) {
                label_71:
                    SharedPreferences v0_1 = this.getApplicationContext().getSharedPreferences("plusconfig", 0);
                    a.SHOW_ANDROID_EMOJI = v0_1.getBoolean("showAndroidEmoji", false);
                    a.USE_DEVICE_FONT = v0_1.getBoolean("useDeviceFont", false);
                    a.KEEP_ORIGINAL_FILENAME = v0_1.getBoolean("keepOriginalFilename", false);
                    Log.i("alireza", "token FCM " + b.aw(ApplicationLoader.applicationContext));
                    return;
                }
            }

            goto label_71;
        }
    }

    public void onResult(Object arg1, int arg2) {
    }
}

