package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzi {
    private static final Uri CONTENT_URI;
    private static HashMap zzagq;
    private static final Uri zzagv;
    private static final Pattern zzagw;
    private static final Pattern zzagx;
    private static final AtomicBoolean zzagy;
    private static final HashMap zzagz;
    private static final HashMap zzaha;
    private static final HashMap zzahb;
    private static final HashMap zzahc;
    private static Object zzahd;
    private static boolean zzahe;
    private static String[] zzahf;

    static {
        zzi.CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        zzi.zzagv = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzi.zzagw = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzi.zzagx = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzi.zzagy = new AtomicBoolean();
        zzi.zzagz = new HashMap();
        zzi.zzaha = new HashMap();
        zzi.zzahb = new HashMap();
        zzi.zzahc = new HashMap();
        zzi.zzahf = new String[0];
    }

    public zzi() {
        super();
    }

    public static int getInt(ContentResolver arg3, String arg4, int arg5) {
        Integer v1_1;
        int v3_1;
        Object v0 = zzi.zzb(arg3);
        Object v1 = zzi.zza(zzi.zzaha, arg4, Integer.valueOf(arg5));
        if(v1 != null) {
            return ((Integer)v1).intValue();
        }

        String v3 = zzi.zza(arg3, arg4, null);
        if(v3 == null) {
            goto label_15;
        }
        else {
            try {
                v3_1 = Integer.parseInt(v3);
                v1_1 = Integer.valueOf(v3_1);
            }
            catch(NumberFormatException ) {
            label_15:
                v3_1 = arg5;
            }
        }

        zzi.zza(v0, zzi.zzaha, arg4, v1_1);
        return v3_1;
    }

    public static long getLong(ContentResolver arg4, String arg5, long arg6) {
        Object v4_2;
        Object v0 = zzi.zzb(arg4);
        Object v1 = zzi.zza(zzi.zzahb, arg5, Long.valueOf(arg6));
        if(v1 != null) {
            return ((Long)v1).longValue();
        }

        String v4 = zzi.zza(arg4, arg5, null);
        if(v4 == null) {
            goto label_15;
        }
        else {
            try {
                long v2 = Long.parseLong(v4);
                Long v4_1 = Long.valueOf(v2);
                arg6 = v2;
            }
            catch(NumberFormatException ) {
            label_15:
                v4_2 = v1;
            }
        }

        zzi.zza(v0, zzi.zzahb, arg5, v4_2);
        return arg6;
    }

    public static String zza(ContentResolver arg13, String arg14, String arg15) {
        String v0_1;
        String v13_2;
        Object v13_1;
        Object v1;
        Class v0 = zzi.class;
        __monitor_enter(v0);
        try {
            zzi.zza(arg13);
            v1 = zzi.zzahd;
            if(zzi.zzagq.containsKey(arg14)) {
                v13_1 = zzi.zzagq.get(arg14);
                if(v13_1 != null) {
                }
                else {
                    v13_2 = arg15;
                }

                __monitor_exit(v0);
                return v13_2;
            }

            String[] v2 = zzi.zzahf;
            int v3 = v2.length;
            int v5;
            for(v5 = 0; v5 < v3; ++v5) {
                if(arg14.startsWith(v2[v5])) {
                    if(!zzi.zzahe || (zzi.zzagq.isEmpty())) {
                        zzi.zzagq.putAll(zzi.zza(arg13, zzi.zzahf));
                        zzi.zzahe = true;
                        if(zzi.zzagq.containsKey(arg14)) {
                            v13_1 = zzi.zzagq.get(arg14);
                            if(v13_1 != null) {
                            }
                            else {
                                v13_2 = arg15;
                            }

                            __monitor_exit(v0);
                            return v13_2;
                        }
                    }

                    __monitor_exit(v0);
                    return arg15;
                }
            }

            __monitor_exit(v0);
        }
        catch(Throwable v13) {
            goto label_82;
        }

        Cursor v13_3 = arg13.query(zzi.CONTENT_URI, null, null, new String[]{arg14}, null);
        if(v13_3 != null) {
            try {
                if(!v13_3.moveToFirst()) {
                }
                else {
                    v0_1 = v13_3.getString(1);
                    if(v0_1 != null && (v0_1.equals(arg15))) {
                        v0_1 = arg15;
                    }

                    zzi.zza(v1, arg14, v0_1);
                    if(v0_1 != null) {
                        goto label_67;
                    }

                    goto label_68;
                }

                goto label_73;
            }
            catch(Throwable v14) {
                goto label_78;
            }

        label_67:
            arg15 = v0_1;
        label_68:
            if(v13_3 != null) {
                v13_3.close();
            }

            return arg15;
        }

    label_73:
        v0_1 = null;
        try {
            zzi.zza(v1, arg14, v0_1);
            if(v13_3 == null) {
                return arg15;
            }
        }
        catch(Throwable v14) {
        label_78:
            if(v13_3 != null) {
                v13_3.close();
            }

            throw v14;
        }

        v13_3.close();
        return arg15;
        try {
        label_82:
            __monitor_exit(v0);
        }
        catch(Throwable v13) {
            goto label_82;
        }

        throw v13;
    }

    public static boolean zza(ContentResolver arg5, String arg6, boolean arg7) {
        Boolean v1_1;
        Object v0 = zzi.zzb(arg5);
        Object v1 = zzi.zza(zzi.zzagz, arg6, Boolean.valueOf(arg7));
        if(v1 != null) {
            return ((Boolean)v1).booleanValue();
        }

        String v5 = zzi.zza(arg5, arg6, null);
        if(v5 != null) {
            if(v5.equals("")) {
            }
            else if(zzi.zzagw.matcher(((CharSequence)v5)).matches()) {
                v1_1 = Boolean.valueOf(true);
                arg7 = true;
            }
            else if(zzi.zzagx.matcher(((CharSequence)v5)).matches()) {
                v1_1 = Boolean.valueOf(false);
                arg7 = false;
            }
            else {
                StringBuilder v3 = new StringBuilder("attempt to read gservices key ");
                v3.append(arg6);
                v3.append(" (value \"");
                v3.append(v5);
                v3.append("\") as boolean");
                Log.w("Gservices", v3.toString());
            }
        }

        zzi.zza(v0, zzi.zzagz, arg6, v1_1);
        return arg7;
    }

    private static Object zza(HashMap arg2, String arg3, Object arg4) {
        Class v0 = zzi.class;
        __monitor_enter(v0);
        try {
            if(arg2.containsKey(arg3)) {
                Object v2_1 = arg2.get(arg3);
                if(v2_1 != null) {
                }
                else {
                    v2_1 = arg4;
                }

                __monitor_exit(v0);
                return v2_1;
            }

            __monitor_exit(v0);
            return null;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_14;
        }

        throw v2;
    }

    private static void zza(Object arg2, HashMap arg3, String arg4, Object arg5) {
        Class v0 = zzi.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzi.zzahd) {
                arg3.put(arg4, arg5);
                zzi.zzagq.remove(arg4);
            }

            __monitor_exit(v0);
            return;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_10;
        }

        throw v2;
    }

    private static void zza(ContentResolver arg4) {
        if(zzi.zzagq == null) {
            zzi.zzagy.set(false);
            zzi.zzagq = new HashMap();
            zzi.zzahd = new Object();
            zzi.zzahe = false;
            arg4.registerContentObserver(zzi.CONTENT_URI, true, new zzj(null));
            return;
        }

        if(zzi.zzagy.getAndSet(false)) {
            zzi.zzagq.clear();
            zzi.zzagz.clear();
            zzi.zzaha.clear();
            zzi.zzahb.clear();
            zzi.zzahc.clear();
            zzi.zzahd = new Object();
            zzi.zzahe = false;
        }
    }

    private static Map zza(ContentResolver arg6, String[] arg7) {
        Cursor v6 = arg6.query(zzi.zzagv, null, null, arg7, null);
        TreeMap v7 = new TreeMap();
        if(v6 == null) {
            return ((Map)v7);
        }

        try {
            while(v6.moveToNext()) {
                v7.put(v6.getString(0), v6.getString(1));
            }
        }
        catch(Throwable v7_1) {
            v6.close();
            throw v7_1;
        }

        v6.close();
        return ((Map)v7);
    }

    private static void zza(Object arg2, String arg3, String arg4) {
        Class v0 = zzi.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzi.zzahd) {
                zzi.zzagq.put(arg3, arg4);
            }

            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_9;
        }

        throw v2;
    }

    private static Object zzb(ContentResolver arg1) {
        Class v0 = zzi.class;
        __monitor_enter(v0);
        try {
            zzi.zza(arg1);
            __monitor_exit(v0);
            return zzi.zzahd;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    static AtomicBoolean zzdv() {
        return zzi.zzagy;
    }
}

