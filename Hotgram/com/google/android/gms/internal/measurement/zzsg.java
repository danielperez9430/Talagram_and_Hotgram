package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzsg {
    private static final Uri CONTENT_URI;
    private static final Uri zzbqd;
    public static final Pattern zzbqe;
    public static final Pattern zzbqf;
    private static final AtomicBoolean zzbqg;
    private static HashMap zzbqh;
    private static final HashMap zzbqi;
    private static final HashMap zzbqj;
    private static final HashMap zzbqk;
    private static final HashMap zzbql;
    private static Object zzbqm;
    private static boolean zzbqn;
    private static String[] zzbqo;

    static {
        zzsg.CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        zzsg.zzbqd = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzsg.zzbqe = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzsg.zzbqf = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzsg.zzbqg = new AtomicBoolean();
        zzsg.zzbqi = new HashMap();
        zzsg.zzbqj = new HashMap();
        zzsg.zzbqk = new HashMap();
        zzsg.zzbql = new HashMap();
        zzsg.zzbqo = new String[0];
    }

    public zzsg() {
        super();
    }

    private static Object zza(HashMap arg2, String arg3, Object arg4) {
        Class v0 = zzsg.class;
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

    public static String zza(ContentResolver arg13, String arg14, String arg15) {
        String v13_2;
        Object v13_1;
        String v2;
        Object v0;
        Class v15 = zzsg.class;
        __monitor_enter(v15);
        try {
            zzsg.zza(arg13);
            v0 = zzsg.zzbqm;
            v2 = null;
            if(zzsg.zzbqh.containsKey(arg14)) {
                v13_1 = zzsg.zzbqh.get(arg14);
                if(v13_1 != null) {
                }
                else {
                    v13_2 = v2;
                }

                __monitor_exit(v15);
                return ((String)v13_1);
            }

            String[] v1 = zzsg.zzbqo;
            int v3 = v1.length;
            int v5;
            for(v5 = 0; v5 < v3; ++v5) {
                if(arg14.startsWith(v1[v5])) {
                    if(!zzsg.zzbqn || (zzsg.zzbqh.isEmpty())) {
                        zzsg.zzbqh.putAll(zzsg.zza(arg13, zzsg.zzbqo));
                        zzsg.zzbqn = true;
                        if(zzsg.zzbqh.containsKey(arg14)) {
                            v13_1 = zzsg.zzbqh.get(arg14);
                            if(v13_1 != null) {
                            }
                            else {
                                v13_2 = v2;
                            }

                            __monitor_exit(v15);
                            return ((String)v13_1);
                        }
                    }

                    __monitor_exit(v15);
                    return v2;
                }
            }

            __monitor_exit(v15);
        }
        catch(Throwable v13) {
            goto label_84;
        }

        Cursor v13_3 = arg13.query(zzsg.CONTENT_URI, null, null, new String[]{arg14}, null);
        if(v13_3 == null) {
            if(v13_3 != null) {
                v13_3.close();
            }

            return v2;
        }

        try {
            if(v13_3.moveToFirst()) {
                goto label_67;
            }

            zzsg.zza(v0, arg14, v2);
            if(v13_3 != null) {
                goto label_65;
            }

            return v2;
        }
        catch(Throwable v14) {
            goto label_80;
        }

    label_65:
        v13_3.close();
        return v2;
        try {
        label_67:
            arg15 = v13_3.getString(1);
            if(arg15 != null && (arg15.equals(v2))) {
                arg15 = v2;
            }

            zzsg.zza(v0, arg14, arg15);
            if(arg15 == null) {
                goto label_75;
            }
        }
        catch(Throwable v14) {
        label_80:
            if(v13_3 != null) {
                v13_3.close();
            }

            throw v14;
        }

        goto label_76;
    label_75:
        arg15 = v2;
    label_76:
        if(v13_3 != null) {
            v13_3.close();
        }

        return arg15;
        try {
        label_84:
            __monitor_exit(v15);
        }
        catch(Throwable v13) {
            goto label_84;
        }

        throw v13;
    }

    private static void zza(ContentResolver arg4) {
        if(zzsg.zzbqh == null) {
            zzsg.zzbqg.set(false);
            zzsg.zzbqh = new HashMap();
            zzsg.zzbqm = new Object();
            zzsg.zzbqn = false;
            arg4.registerContentObserver(zzsg.CONTENT_URI, true, new zzsh(null));
            return;
        }

        if(zzsg.zzbqg.getAndSet(false)) {
            zzsg.zzbqh.clear();
            zzsg.zzbqi.clear();
            zzsg.zzbqj.clear();
            zzsg.zzbqk.clear();
            zzsg.zzbql.clear();
            zzsg.zzbqm = new Object();
            zzsg.zzbqn = false;
        }
    }

    private static Map zza(ContentResolver arg6, String[] arg7) {
        Cursor v6 = arg6.query(zzsg.zzbqd, null, null, arg7, null);
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
        Class v0 = zzsg.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzsg.zzbqm) {
                zzsg.zzbqh.put(arg3, arg4);
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

    private static void zza(Object arg2, HashMap arg3, String arg4, Object arg5) {
        Class v0 = zzsg.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzsg.zzbqm) {
                arg3.put(arg4, arg5);
                zzsg.zzbqh.remove(arg4);
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

    public static boolean zza(ContentResolver arg5, String arg6, boolean arg7) {
        Boolean v1_1;
        Object v0 = zzsg.zzb(arg5);
        Object v1 = zzsg.zza(zzsg.zzbqi, arg6, Boolean.valueOf(arg7));
        if(v1 != null) {
            return ((Boolean)v1).booleanValue();
        }

        String v5 = zzsg.zza(arg5, arg6, null);
        if(v5 != null) {
            if(v5.equals("")) {
            }
            else if(zzsg.zzbqe.matcher(((CharSequence)v5)).matches()) {
                v1_1 = Boolean.valueOf(true);
                arg7 = true;
            }
            else if(zzsg.zzbqf.matcher(((CharSequence)v5)).matches()) {
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

        zzsg.zza(v0, zzsg.zzbqi, arg6, v1);
        return arg7;
    }

    private static Object zzb(ContentResolver arg1) {
        Class v0 = zzsg.class;
        __monitor_enter(v0);
        try {
            zzsg.zza(arg1);
            __monitor_exit(v0);
            return zzsg.zzbqm;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    static AtomicBoolean zzsy() {
        return zzsg.zzbqg;
    }
}

