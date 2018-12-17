package utils;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract$Contacts;
import android.provider.ContactsContract$Data;
import android.provider.ContactsContract$PhoneLookup;
import android.provider.ContactsContract$RawContacts;
import android.provider.Settings$Secure;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.crashlytics.android.a.m;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.telegram.customization.util.c;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC$Chat;
import utils.view.PixelUtil;

public class d {
    class a {
        public String a;
        public String b;
        int c;
        int d;
        int e;

        public a(d arg1, Date arg2) {
            this.f = arg1;
            super();
            this.a = "";
            this.b = "";
            this.a(arg2);
        }

        private void a(Date arg14) {
            int v0 = arg14.getYear() + 1900;
            int v1 = arg14.getMonth() + 1;
            int v3 = arg14.getDate();
            int v14 = arg14.getDay();
            int[] v5 = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
            int[] v4 = new int[]{0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
            int v6 = v0 % 4;
            int v7 = 1996;
            int v8 = 79;
            int v9 = 10;
            int v10 = 186;
            int v11 = 31;
            int v12 = 30;
            if(v6 != 0) {
                this.c = v5[v1 - 1] + v3;
                if(this.c > v8) {
                    this.c -= v8;
                    if(this.c <= v10) {
                        if(this.c % v11 == 0) {
                            goto label_40;
                        }

                        goto label_32;
                    }
                    else {
                        this.c -= v10;
                        if(this.c % v12 == 0) {
                            goto label_60;
                        }

                        goto label_53;
                    }
                }
                else {
                    v1 = v0 <= v7 || v6 != 1 ? 10 : 11;
                    this.c += v1;
                    if(this.c % v12 == 0) {
                        goto label_85;
                    }

                    goto label_77;
                }
            }
            else {
                this.c = v4[v1 - 1] + v3;
                if(v0 >= v7) {
                }
                else {
                    v8 = 80;
                }

                if(this.c > v8) {
                    this.c -= v8;
                    if(this.c <= v10) {
                        if(this.c % v11 != 0) {
                        label_32:
                            this.d = this.c / v11 + 1;
                            v1 = this.c % v11;
                            goto label_38;
                        }

                    label_40:
                        this.d = this.c / v11;
                        this.c = v11;
                        goto label_44;
                    }

                    this.c -= v10;
                    if(this.c % v12 != 0) {
                    label_53:
                        this.d = this.c / v12 + 7;
                        v1 = this.c % v12;
                    label_38:
                        this.c = v1;
                    }
                    else {
                    label_60:
                        this.d = this.c / v12 + 6;
                        this.c = v12;
                    }

                label_44:
                    v0 += -621;
                    goto label_45;
                }

                this.c += v9;
                if(this.c % v12 != 0) {
                label_77:
                    this.d = this.c / v12 + v9;
                    this.c %= v12;
                }
                else {
                label_85:
                    this.d = this.c / v12 + 9;
                    this.c = v12;
                }

                v0 += -622;
            }

        label_45:
            this.e = v0;
            switch(this.d) {
                case 1: {
                    goto label_149;
                }
                case 2: {
                    goto label_147;
                }
                case 3: {
                    goto label_145;
                }
                case 4: {
                    goto label_143;
                }
                case 5: {
                    goto label_141;
                }
                case 6: {
                    goto label_139;
                }
                case 7: {
                    goto label_137;
                }
                case 8: {
                    goto label_135;
                }
                case 9: {
                    goto label_133;
                }
                case 10: {
                    goto label_131;
                }
                case 11: {
                    goto label_129;
                }
                case 12: {
                    goto label_127;
                }
            }

            goto label_151;
        label_129:
            String v0_1 = "بهمن";
            goto label_150;
        label_131:
            v0_1 = "دي";
            goto label_150;
        label_133:
            v0_1 = "آذر";
            goto label_150;
        label_135:
            v0_1 = "آبان";
            goto label_150;
        label_137:
            v0_1 = "مهر";
            goto label_150;
        label_139:
            v0_1 = "شهريور";
            goto label_150;
        label_141:
            v0_1 = "مرداد";
            goto label_150;
        label_143:
            v0_1 = "تير";
            goto label_150;
        label_145:
            v0_1 = "خرداد";
            goto label_150;
        label_147:
            v0_1 = "ارديبهشت";
            goto label_150;
        label_149:
            v0_1 = "فروردين";
            goto label_150;
        label_127:
            v0_1 = "اسفند";
        label_150:
            this.b = v0_1;
        label_151:
            switch(v14) {
                case 0: {
                    goto label_165;
                }
                case 1: {
                    goto label_163;
                }
                case 2: {
                    goto label_161;
                }
                case 3: {
                    goto label_159;
                }
                case 4: {
                    goto label_157;
                }
                case 5: {
                    goto label_155;
                }
                case 6: {
                    goto label_153;
                }
            }

            return;
        label_161:
            String v14_1 = "سه شنبه";
            goto label_166;
        label_163:
            v14_1 = "دوشنبه";
            goto label_166;
        label_165:
            v14_1 = "يکشنبه";
            goto label_166;
        label_153:
            v14_1 = "شنبه";
            goto label_166;
        label_155:
            v14_1 = "جمعه";
            goto label_166;
        label_157:
            v14_1 = "پنج شنبه";
            goto label_166;
        label_159:
            v14_1 = "چهارشنبه";
        label_166:
            this.a = v14_1;
        }
    }

    public d() {
        super();
    }

    public static int a(float arg0, Context arg1) {
        return PixelUtil.a(arg1, ((int)arg0));
    }

    public static int a(int arg4, float arg5) {
        return Color.argb(Color.alpha(arg4), Math.max(((int)((((float)Color.red(arg4))) * arg5)), 0), Math.max(((int)((((float)Color.green(arg4))) * arg5)), 0), Math.max(((int)((((float)Color.blue(arg4))) * arg5)), 0));
    }

    public static int a(Context arg0) {
        return arg0.getResources().getDisplayMetrics().widthPixels;
    }

    public static String a() {
        String v0_1 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ApplicationLoader.applicationContext.getString(2131625826) + File.separator;
        File v1 = new File(v0_1);
        if(!v1.exists()) {
            v1.mkdirs();
        }

        return v0_1;
    }

    public static String a(long arg4) {
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

    public static String a(String arg2) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            arg2 = "#55000000";
        }

        if(!arg2.startsWith("#")) {
            arg2 = "#" + arg2;
        }

        return arg2;
    }

    public static String a(Date arg8) {
        Locale v0 = new Locale("en_US");
        d v1 = new d();
        v1.getClass();
        a v2 = new a(v1, arg8);
        return String.valueOf(v2.e) + "/" + String.format(v0, "%02d", Integer.valueOf(v2.d)) + "/" + String.format(v0, "%02d", Integer.valueOf(v2.c)) + " - " + arg8.getHours() + ":" + arg8.getMinutes();
    }

    public static String a(boolean arg5) {
        String v5_1;
        String v2_1;
        try {
            Iterator v0 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        label_3:
            if(!v0.hasNext()) {
                return "";
            }

            Iterator v1 = Collections.list(v0.next().getInetAddresses()).iterator();
            do {
            label_9:
                if(!v1.hasNext()) {
                    goto label_3;
                }

                Object v2 = v1.next();
                if(((InetAddress)v2).isLoopbackAddress()) {
                    goto label_9;
                }

                v2_1 = ((InetAddress)v2).getHostAddress();
                int v3 = v2_1.indexOf(58) < 0 ? 1 : 0;
                if(arg5) {
                    if(v3 == 0) {
                        goto label_9;
                    }

                    return v2_1;
                }
            }
            while(v3 != 0);

            int v5 = v2_1.indexOf(37);
            v5_1 = v5 < 0 ? v2_1.toUpperCase() : v2_1.substring(0, v5).toUpperCase();
        }
        catch(Exception ) {
            return "";
        }

        return v5_1;
    }

    public static void a(Context arg5, String arg6, String arg7, String arg8) {
        Log.d("slspushreceiver msg:", "add contact 1");
        ArrayList v0 = new ArrayList();
        v0.add(ContentProviderOperation.newInsert(ContactsContract$RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        Log.d("slspushreceiver msg:", "add contact 2");
        v0.add(ContentProviderOperation.newInsert(ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", arg6).withValue("data3", arg7).build());
        Log.d("slspushreceiver msg:", "add contact 3");
        v0.add(ContentProviderOperation.newInsert(ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", arg8).withValue("data2", Integer.valueOf(1)).build());
        try {
            arg5.getContentResolver().applyBatch("com.android.contacts", v0);
        }
        catch(Exception v5) {
            v5.printStackTrace();
        }
    }

    public static void a(Throwable arg1) {
        Throwable v0 = arg1;
        goto label_3;
        v0 = null;
    label_3:
        if(v0 == null) {
            return;
        }

        throw arg1;
    }

    public static boolean a(MessageObject arg5, Chat arg6) {
        boolean v6 = false;
        try {
            if(arg5.getChannelId() > 0 || arg5.getGroupId() > 0) {
                String v0 = b.a(arg5.getId(), ((long)arg5.getChannelId()));
                if((TextUtils.isEmpty(((CharSequence)v0))) || !v0.contentEquals("IFX")) {
                    if(arg5.messageOwner == null) {
                    }
                    else if(arg5.messageOwner.fwd_from == null) {
                    }
                    else if(org.telegram.b.a.c(((long)arg5.messageOwner.fwd_from.channel_id))) {
                        return true;
                    }

                    return v6;
                }

                return true;
            }
        }
        catch(Throwable ) {
        }

        return v6;
    }

    public static String b(Date arg4) {
        Locale v0 = new Locale("en_US");
        d v1 = new d();
        v1.getClass();
        return String.format(v0, "%02d", Integer.valueOf(new a(v1, arg4).c));
    }

    public static float b(float arg0, Context arg1) {
        return ((float)PixelUtil.b(arg1, ((int)arg0)));
    }

    public static final int b() {
        return ApplicationLoader.applicationContext.getSharedPreferences("theme", 0).getInt("themeColor", c.b);
    }

    public static int b(Context arg0) {
        return arg0.getResources().getDisplayMetrics().heightPixels;
    }

    public static void b(String arg3) {
        try {
            com.crashlytics.android.a.b.c().a(new m("TRACKER_VIEW_ACTION").a("SelectedBottomTab", arg3));
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public static boolean b(Context arg7, String arg8, String arg9, String arg10) {
        Cursor v8 = arg7.getContentResolver().query(Uri.withAppendedPath(ContactsContract$PhoneLookup.CONTENT_FILTER_URI, Uri.encode(arg8)), null, null, null, null);
        try {
            if(!v8.moveToFirst()) {
                goto label_47;
            }

            do {
                Log.d("LEE", "Contact:" + v8.getString(v8.getColumnIndex("display_name")));
                String v0 = v8.getString(v8.getColumnIndex("display_name"));
                v1 = new StringBuilder();
                v1.append(arg9);
                v1.append(" ");
                v1.append(arg10);
                if(v0.equalsIgnoreCase(v1.toString())) {
                    break;
                }

                if(v8.moveToNext()) {
                    continue;
                }

                goto label_47;
            }
            while(true);

            arg7.getContentResolver().delete(Uri.withAppendedPath(ContactsContract$Contacts.CONTENT_LOOKUP_URI, v8.getString(v8.getColumnIndex("lookup"))), null, null);
        }
        catch(Throwable v7) {
            goto label_58;
        }
        catch(Exception v7_1) {
            goto label_52;
        }

        v8.close();
        return 1;
        try {
        label_52:
            System.out.println(v7_1.getStackTrace());
        }
        catch(Throwable v7) {
        label_58:
            v8.close();
            throw v7;
        }

    label_47:
        v8.close();
        return 0;
    }

    public static String c(Context arg6) {
        String v6;
        String v0 = "";
        String v1 = "";
        String v2 = "";
        String v3 = "";
        String v4 = "";
        try {
            v6 = Settings$Secure.getString(arg6.getContentResolver(), "android_id");
            if(v6 == null) {
                goto label_9;
            }

            goto label_11;
        }
        catch(Exception ) {
            goto label_10;
        }

        try {
        label_9:
            v0 = "";
        }
        catch(Exception ) {
            goto label_11;
        }

    label_10:
        v6 = v0;
        try {
        label_11:
            v0 = Build.MODEL;
            if(v0 == null) {
                goto label_13;
            }

            goto label_15;
        }
        catch(Exception ) {
            goto label_14;
        }

        try {
        label_13:
            v1 = "";
        }
        catch(Exception ) {
            goto label_15;
        }

    label_14:
        v0 = v1;
        try {
        label_15:
            v1 = Build.BRAND;
            if(v1 == null) {
                goto label_17;
            }

            goto label_19;
        }
        catch(Exception ) {
            goto label_18;
        }

        try {
        label_17:
            v2 = "";
        }
        catch(Exception ) {
            goto label_19;
        }

    label_18:
        v1 = v2;
        try {
        label_19:
            v2 = Build.SERIAL;
            if(v2 == null) {
                goto label_21;
            }

            goto label_23;
        }
        catch(Exception ) {
            goto label_22;
        }

        try {
        label_21:
            v4 = "";
        }
        catch(Exception ) {
            goto label_23;
        }

    label_22:
        v2 = v4;
    label_23:
        v6 = v3 + v1 + v0 + v6 + v2 + "ir.hotgram.mobile.android";
        v0 = null;
        new byte[0];
        try {
            v6 = Base64.encodeToString(v6.getBytes("UTF-8"), 0);
        }
        catch(UnsupportedEncodingException v6_1) {
            v6_1.printStackTrace();
            v6 = v0;
        }

        return v6;
    }

    public static String c(Date arg2) {
        d v0 = new d();
        v0.getClass();
        return new a(v0, arg2).b;
    }

    public static String c() {
        return ApplicationLoader.applicationContext.getSystemService("phone").getNetworkOperatorName();
    }

    public static String d(Date arg2) {
        d v0 = new d();
        v0.getClass();
        a v1 = new a(v0, arg2);
        return v1.e + "";
    }
}

