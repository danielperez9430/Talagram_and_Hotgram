package org.telegram.customization.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.EdgeEffect;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.ui.LaunchActivity;
import utils.d;

public class c {
    public static boolean a;
    public static final int b;
    private static final Hashtable c;
    private static Typeface d;
    private static Typeface e;

    static {
        c.c = new Hashtable();
        c.a = false;
        c.b = c.a("themeColor");
    }

    @SuppressLint(value={"NewApi"}) public static Bitmap a(Context arg4, Bitmap arg5, int arg6) {
        try {
            arg5 = c.a(arg5);
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }

        Bitmap v0_1 = null;
        if(arg5 == null) {
            return v0_1;
        }

        try {
            Bitmap v1 = Bitmap.createBitmap(arg5.getWidth(), arg5.getHeight(), Bitmap$Config.ARGB_8888);
            RenderScript v4_1 = RenderScript.create(arg4);
            Allocation v5 = Allocation.createFromBitmap(v4_1, arg5);
            Allocation v2 = Allocation.createFromBitmap(v4_1, v1);
            ScriptIntrinsicBlur v3 = ScriptIntrinsicBlur.create(v4_1, Element.U8_4(v4_1));
            v3.setInput(v5);
            v3.setRadius(((float)arg6));
            v3.forEach(v2);
            v2.copyTo(v1);
            v4_1.destroy();
            v3.destroy();
            return v1;
        }
        catch(Exception v4) {
            v4.printStackTrace();
            return v0_1;
        }
    }

    public static int a(String arg3) {
        return ApplicationLoader.applicationContext.getSharedPreferences("theme", 0).getInt(arg3, c.b);
    }

    public static int a(int arg4, int arg5) {
        int v0 = Color.alpha(arg4);
        int v1 = Color.red(arg4) - arg5;
        int v2 = Color.green(arg4) - arg5;
        arg4 = Color.blue(arg4) - arg5;
        if(arg5 < 0) {
            int v3 = 255;
            if(v1 > v3) {
                v1 = 255;
            }

            if(v2 > v3) {
                v2 = 255;
            }

            if(arg4 > v3) {
                arg4 = 255;
            }

            if(v1 != v3) {
                goto label_21;
            }

            if(v2 != v3) {
                goto label_21;
            }

            if(arg4 != v3) {
                goto label_21;
            }

            arg4 = arg5;
            v1 = arg4;
            v2 = v1;
        }

    label_21:
        if(arg5 > 0) {
            if(v1 < 0) {
                v1 = 0;
            }

            if(v2 < 0) {
                v2 = 0;
            }

            if(arg4 < 0) {
                arg4 = 0;
            }

            if(v1 != 0) {
                goto label_35;
            }

            if(v2 != 0) {
                goto label_35;
            }

            if(arg4 != 0) {
                goto label_35;
            }

            arg4 = arg5;
            v1 = arg4;
            v2 = v1;
        }

    label_35:
        return Color.argb(v0, v1, v2, arg4);
    }

    public static int a(Context arg5, String arg6, String arg7) {
        String v1 = c.a(arg5);
        StringBuilder v2 = new StringBuilder();
        v2.append(arg7);
        v2.append(".xml");
        File v0 = new File(v1, v2.toString());
        File v7 = new File(arg6);
        String v0_1 = c.a(c.a(v7, v0, false));
        if(!v0_1.contains("4")) {
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("ERROR: ");
            v1_1.append(v0_1);
            v1_1.append("\n");
            v1_1.append(arg5.getString(2131627016, new Object[]{v7.getAbsolutePath()}));
            Toast.makeText(arg5, v1_1.toString(), 1).show();
        }

        return Integer.parseInt(v0_1);
    }

    static String a(Context arg5) {
        String v0 = arg5.getFilesDir().getAbsolutePath();
        StringBuilder v2 = new StringBuilder();
        v2.append(v0.substring(0, v0.lastIndexOf(47) + 1));
        v2.append("shared_prefs/");
        File v1 = new File(v2.toString());
        if(!v1.exists()) {
            String v5 = arg5.getPackageName();
            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("/dbdata/databases/");
            v0_1.append(v5);
            v0_1.append("/shared_prefs/");
            v1 = new File(v0_1.toString());
        }

        return v1.getAbsolutePath();
    }

    static String a(int arg2) {
        String v0 = "-1";
        if(arg2 == 0) {
            v0 = "0: SOURCE FILE DOESN\'T EXIST";
        }

        if(arg2 == 1) {
            v0 = "1: DESTINATION FILE DOESN\'T EXIST";
        }

        if(arg2 == 2) {
            v0 = "2: NULL SOURCE & DESTINATION FILES";
        }

        if(arg2 == 3) {
            v0 = "3: NULL SOURCE FILE";
        }

        if(arg2 == 4) {
            v0 = "4";
        }

        return v0;
    }

    static int a(File arg8, File arg9, boolean arg10) {
        int v0 = -1;
        try {
            if(!arg8.exists()) {
                return 0;
            }

            if(!arg9.exists()) {
                if(arg10) {
                    v0 = 1;
                }

                arg9.createNewFile();
            }

            FileInputStream v10 = new FileInputStream(arg8);
            FileChannel v8_1 = v10.getChannel();
            FileOutputStream v7 = new FileOutputStream(arg9);
            FileChannel v9 = v7.getChannel();
            if(v9 != null && v8_1 != null) {
                v9.transferFrom(v8_1, 0, v8_1.size());
                v0 = 2;
            }

            if(v8_1 != null) {
                v8_1.close();
                v0 = 3;
            }

            if(v9 != null) {
                v9.close();
                v0 = 4;
            }

            v10.close();
            v7.close();
        }
        catch(Exception v8) {
            PrintStream v9_1 = System.err;
            v9_1.println("Error saving preferences: " + v8.getMessage());
        }

        return v0;
    }

    public static int a(String arg3, int arg4, float arg5) {
        int v3 = ApplicationLoader.applicationContext.getSharedPreferences("theme", 0).getInt(arg3, arg4);
        return Color.argb(Math.round((((float)Color.alpha(v3))) * arg5), Color.red(v3), Color.green(v3), Color.blue(v3));
    }

    private static Bitmap a(Bitmap arg9) {
        int[] v0 = new int[arg9.getWidth() * arg9.getHeight()];
        arg9.getPixels(v0, 0, arg9.getWidth(), 0, 0, arg9.getWidth(), arg9.getHeight());
        arg9 = Bitmap.createBitmap(arg9.getWidth(), arg9.getHeight(), Bitmap$Config.ARGB_8888);
        arg9.setPixels(v0, 0, arg9.getWidth(), 0, 0, arg9.getWidth(), arg9.getHeight());
        return arg9;
    }

    public static String a(long arg5) {
        String v5_1;
        String v0 = "";
        try {
            v5_1 = new org.ocpsoft.prettytime.c(new Locale("FA")).b(new Date(((long)(Double.valueOf(((double)arg5)).doubleValue() * 1000))));
        }
        catch(Exception v5) {
            v5.printStackTrace();
            v5_1 = v0;
        }

        return v5_1;
    }

    public static void a() {
        ApplicationLoader.applicationContext.getSystemService("alarm").set(1, System.currentTimeMillis() + 1000, PendingIntent.getActivity(ApplicationLoader.applicationContext, 123456, new Intent(ApplicationLoader.applicationContext, LaunchActivity.class), 268435456));
        System.exit(0);
    }

    public static void a(Context arg3, String arg4, String arg5, String arg6, boolean arg7) {
        Toast v3;
        File v0 = new File(c.a(arg3), arg5);
        if(c.b() > 1) {
            File v5 = new File(Environment.getExternalStorageDirectory(), arg4);
            v5.mkdirs();
            File v2 = new File(v5, arg6);
            arg5 = c.a(c.a(v0, v2, true));
            if(!arg5.equalsIgnoreCase("4")) {
                if(arg5.contains("0")) {
                    arg4 = arg3.getString(2131625939);
                    arg4 = "ERROR: " + arg4;
                }
                else {
                    StringBuilder v4 = new StringBuilder();
                    v4.append("ERROR: ");
                    v4.append(arg5);
                    Toast.makeText(arg3, v4.toString(), 1).show();
                    arg4 = v0.getAbsolutePath();
                }

                goto label_61;
            }
            else if((arg7) && v2.getName() != "") {
                v3 = Toast.makeText(arg3, arg3.getString(2131625952, new Object[]{v2.getName(), arg4}), 0);
                goto label_62;
            }
        }
        else {
            arg4 = "ERROR: " + arg3.getString(2131625270);
        label_61:
            v3 = Toast.makeText(arg3, ((CharSequence)arg4), 1);
        label_62:
            v3.show();
        }
    }

    public static void a(AbsListView arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            try {
                Field v0 = AbsListView.class.getDeclaredField("mEdgeGlowTop");
                v0.setAccessible(true);
                Object v0_1 = v0.get(arg3);
                if(v0_1 != null) {
                    ((EdgeEffect)v0_1).setColor(arg4);
                }

                v0 = AbsListView.class.getDeclaredField("mEdgeGlowBottom");
                v0.setAccessible(true);
                Object v3_1 = v0.get(arg3);
                if(v3_1 == null) {
                    return;
                }

                ((EdgeEffect)v3_1).setColor(arg4);
            }
            catch(Exception v3) {
                FileLog.e("tmessages", ((Throwable)v3));
            }
        }
    }

    static int b() {
        int v0_1;
        String v0 = Environment.getExternalStorageState();
        if(v0.equals("mounted")) {
            v0_1 = 2;
        }
        else if(v0.equals("mounted_ro")) {
            v0_1 = 1;
        }
        else {
            v0_1 = 0;
        }

        return v0_1;
    }

    public static Typeface b(Context arg1) {
        if(ApplicationLoader.USE_DEVICE_FONT) {
            return null;
        }

        if(c.d == null) {
            c.d = Typeface.createFromAsset(arg1.getAssets(), "fonts/IRANSansMobile_Light.ttf");
        }

        return c.d;
    }

    public static String b(long arg4) {
        Date v1 = new Date(((long)(Double.valueOf(((double)arg4)).doubleValue() * 1000)));
        String v4 = d.b(v1);
        String v5 = d.c(v1);
        String v1_1 = d.d(v1);
        String v2 = d.d(new Date());
        v4 = "" + v4 + " " + v5;
        if(!v2.contentEquals(((CharSequence)v1_1))) {
            v4 = v4 + " " + v1_1;
        }

        return v4;
    }

    public static String b(String arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg2)) && (arg2.startsWith("@"))) {
            arg2 = arg2.replace("@", "");
        }

        return arg2;
    }

    public static Typeface c(Context arg1) {
        if(ApplicationLoader.USE_DEVICE_FONT) {
            return null;
        }

        if(c.e == null) {
            c.e = Typeface.createFromAsset(arg1.getAssets(), "fonts/IRANSansMobile_UltraLight.ttf");
        }

        return c.e;
    }
}

