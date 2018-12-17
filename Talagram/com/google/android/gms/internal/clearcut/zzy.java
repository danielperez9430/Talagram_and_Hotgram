package com.google.android.gms.internal.clearcut;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzy {
    private static final Uri CONTENT_URI;
    private static final Uri zzcq;
    public static final Pattern zzcr;
    public static final Pattern zzcs;
    private static final AtomicBoolean zzct;
    private static HashMap zzcu;
    private static final HashMap zzcv;
    private static final HashMap zzcw;
    private static final HashMap zzcx;
    private static final HashMap zzcy;
    private static Object zzcz;
    private static boolean zzda;
    private static String[] zzdb;

    static {
        zzy.CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        zzy.zzcq = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzy.zzcr = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzy.zzcs = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzy.zzct = new AtomicBoolean();
        zzy.zzcv = new HashMap();
        zzy.zzcw = new HashMap();
        zzy.zzcx = new HashMap();
        zzy.zzcy = new HashMap();
        zzy.zzdb = new String[0];
    }

    public zzy() {
        super();
    }

    public static long getLong(ContentResolver arg4, String arg5, long arg6) {
        Long v7_1;
        Object v6 = zzy.zzb(arg4);
        long v0 = 0;
        Object v7 = zzy.zza(zzy.zzcx, arg5, Long.valueOf(v0));
        if(v7 != null) {
            return ((Long)v7).longValue();
        }

        String v4 = zzy.zza(arg4, arg5, null);
        if(v4 == null) {
        }
        else {
            try {
                long v2 = Long.parseLong(v4);
                v7_1 = Long.valueOf(v2);
                v0 = v2;
                goto label_16;
            }
            catch(NumberFormatException ) {
            label_16:
                zzy.zza(v6, zzy.zzcx, arg5, v7_1);
                return v0;
            }
        }

        goto label_16;
    }

    public static boolean zza(ContentResolver arg5, String arg6, boolean arg7) {
        Boolean v1_1;
        Object v0 = zzy.zzb(arg5);
        Object v1 = zzy.zza(zzy.zzcv, arg6, Boolean.valueOf(arg7));
        if(v1 != null) {
            return ((Boolean)v1).booleanValue();
        }

        String v5 = zzy.zza(arg5, arg6, null);
        if(v5 != null) {
            if(v5.equals("")) {
            }
            else if(zzy.zzcr.matcher(((CharSequence)v5)).matches()) {
                v1_1 = Boolean.valueOf(true);
                arg7 = true;
            }
            else if(zzy.zzcs.matcher(((CharSequence)v5)).matches()) {
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

        zzy.zza(v0, zzy.zzcv, arg6, v1_1);
        return arg7;
    }

    public static String zza(ContentResolver arg13, String arg14, String arg15) {
        String v13_2;
        Object v13_1;
        String v2;
        Object v0;
        Class v15 = zzy.class;
        __monitor_enter(v15);
        try {
            zzy.zza(arg13);
            v0 = zzy.zzcz;
            v2 = null;
            if(zzy.zzcu.containsKey(arg14)) {
                v13_1 = zzy.zzcu.get(arg14);
                if(v13_1 != null) {
                }
                else {
                    v13_2 = v2;
                }

                __monitor_exit(v15);
                return v13_2;
            }

            String[] v1 = zzy.zzdb;
            int v3 = v1.length;
            int v5;
            for(v5 = 0; v5 < v3; ++v5) {
                if(arg14.startsWith(v1[v5])) {
                    if(!zzy.zzda || (zzy.zzcu.isEmpty())) {
                        zzy.zzcu.putAll(zzy.zza(arg13, zzy.zzdb));
                        zzy.zzda = true;
                        if(zzy.zzcu.containsKey(arg14)) {
                            v13_1 = zzy.zzcu.get(arg14);
                            if(v13_1 != null) {
                            }
                            else {
                                v13_2 = v2;
                            }

                            __monitor_exit(v15);
                            return v13_2;
                        }
                    }

                    __monitor_exit(v15);
                    return v2;
                }
            }

            __monitor_exit(v15);
        }
        catch(Throwable v13) {
            goto label_83;
        }

        Cursor v13_3 = arg13.query(zzy.CONTENT_URI, null, null, new String[]{arg14}, null);
        if(v13_3 != null) {
            try {
                if(!v13_3.moveToFirst()) {
                }
                else {
                    arg15 = v13_3.getString(1);
                    if(arg15 != null && (arg15.equals(v2))) {
                        arg15 = v2;
                    }

                    zzy.zza(v0, arg14, arg15);
                    if(arg15 != null) {
                        goto label_68;
                    }
                    else {
                        goto label_69;
                    }
                }

                goto label_75;
            }
            catch(Throwable v14) {
                goto label_79;
            }

        label_68:
            goto label_70;
        label_69:
            arg15 = v2;
        label_70:
            if(v13_3 != null) {
                v13_3.close();
            }

            return arg15;
        }

        try {
        label_75:
            zzy.zza(v0, arg14, v2);
            if(v13_3 == null) {
                return v2;
            }
        }
        catch(Throwable v14) {
        label_79:
            if(v13_3 != null) {
                v13_3.close();
            }

            throw v14;
        }

        v13_3.close();
        return v2;
        try {
        label_83:
            __monitor_exit(v15);
        }
        catch(Throwable v13) {
            goto label_83;
        }

        throw v13;
    }

    private static Object zza(HashMap arg2, String arg3, Object arg4) {
        Class v0 = zzy.class;
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
        Class v0 = zzy.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzy.zzcz) {
                arg3.put(arg4, arg5);
                zzy.zzcu.remove(arg4);
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
        if(zzy.zzcu == null) {
            zzy.zzct.set(false);
            zzy.zzcu = new HashMap();
            zzy.zzcz = new Object();
            zzy.zzda = false;
            arg4.registerContentObserver(zzy.CONTENT_URI, true, new zzz(null));
            return;
        }

        if(zzy.zzct.getAndSet(false)) {
            zzy.zzcu.clear();
            zzy.zzcv.clear();
            zzy.zzcw.clear();
            zzy.zzcx.clear();
            zzy.zzcy.clear();
            zzy.zzcz = new Object();
            zzy.zzda = false;
        }
    }

    private static Map zza(ContentResolver arg6, String[] arg7) {
        Cursor v6 = arg6.query(zzy.zzcq, null, null, arg7, null);
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
        Class v0 = zzy.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzy.zzcz) {
                zzy.zzcu.put(arg3, arg4);
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
        Class v0 = zzy.class;
        __monitor_enter(v0);
        try {
            zzy.zza(arg1);
            __monitor_exit(v0);
            return zzy.zzcz;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    static AtomicBoolean zze() {
        return zzy.zzct;
    }
}

