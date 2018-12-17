package com.google.android.gms.internal.phenotype;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzf {
    private static final Uri CONTENT_URI;
    private static final Uri zzbe;
    private static final Pattern zzbf;
    private static final Pattern zzbg;
    private static final AtomicBoolean zzbh;
    private static HashMap zzbi;
    private static final HashMap zzbj;
    private static final HashMap zzbk;
    private static final HashMap zzbl;
    private static final HashMap zzbm;
    private static Object zzbn;
    private static boolean zzbo;
    private static String[] zzbp;

    static {
        zzf.CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        zzf.zzbe = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzf.zzbf = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzf.zzbg = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzf.zzbh = new AtomicBoolean();
        zzf.zzbj = new HashMap();
        zzf.zzbk = new HashMap();
        zzf.zzbl = new HashMap();
        zzf.zzbm = new HashMap();
        zzf.zzbp = new String[0];
    }

    public zzf() {
        super();
    }

    private static Object zza(HashMap arg2, String arg3, Object arg4) {
        Class v0 = zzf.class;
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
        Class v15 = zzf.class;
        __monitor_enter(v15);
        try {
            zzf.zza(arg13);
            v0 = zzf.zzbn;
            v2 = null;
            if(zzf.zzbi.containsKey(arg14)) {
                v13_1 = zzf.zzbi.get(arg14);
                if(v13_1 != null) {
                }
                else {
                    v13_2 = v2;
                }

                __monitor_exit(v15);
                return ((String)v13_1);
            }

            String[] v1 = zzf.zzbp;
            int v3 = v1.length;
            int v5;
            for(v5 = 0; v5 < v3; ++v5) {
                if(arg14.startsWith(v1[v5])) {
                    if(!zzf.zzbo || (zzf.zzbi.isEmpty())) {
                        zzf.zzbi.putAll(zzf.zza(arg13, zzf.zzbp));
                        zzf.zzbo = true;
                        if(zzf.zzbi.containsKey(arg14)) {
                            v13_1 = zzf.zzbi.get(arg14);
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
            goto label_83;
        }

        Cursor v13_3 = arg13.query(zzf.CONTENT_URI, null, null, new String[]{arg14}, null);
        if(v13_3 != null) {
            try {
                if(!v13_3.moveToFirst()) {
                }
                else {
                    arg15 = v13_3.getString(1);
                    if(arg15 != null && (arg15.equals(v2))) {
                        arg15 = v2;
                    }

                    zzf.zza(v0, arg14, arg15);
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
            zzf.zza(v0, arg14, v2);
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

    private static void zza(ContentResolver arg4) {
        if(zzf.zzbi == null) {
            zzf.zzbh.set(false);
            zzf.zzbi = new HashMap();
            zzf.zzbn = new Object();
            zzf.zzbo = false;
            arg4.registerContentObserver(zzf.CONTENT_URI, true, new zzg(null));
            return;
        }

        if(zzf.zzbh.getAndSet(false)) {
            zzf.zzbi.clear();
            zzf.zzbj.clear();
            zzf.zzbk.clear();
            zzf.zzbl.clear();
            zzf.zzbm.clear();
            zzf.zzbn = new Object();
            zzf.zzbo = false;
        }
    }

    private static Map zza(ContentResolver arg6, String[] arg7) {
        Cursor v6 = arg6.query(zzf.zzbe, null, null, arg7, null);
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
        Class v0 = zzf.class;
        __monitor_enter(v0);
        try {
            if(arg2 == zzf.zzbn) {
                zzf.zzbi.put(arg3, arg4);
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

    public static boolean zza(ContentResolver arg5, String arg6, boolean arg7) {
        Boolean v1_1;
        Object v0 = zzf.zzb(arg5);
        Object v1 = zzf.zza(zzf.zzbj, arg6, Boolean.valueOf(arg7));
        if(v1 != null) {
            return ((Boolean)v1).booleanValue();
        }

        String v5 = zzf.zza(arg5, arg6, null);
        if(v5 != null) {
            if(v5.equals("")) {
            }
            else if(zzf.zzbf.matcher(((CharSequence)v5)).matches()) {
                v1_1 = Boolean.valueOf(true);
                arg7 = true;
            }
            else if(zzf.zzbg.matcher(((CharSequence)v5)).matches()) {
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

        HashMap v5_1 = zzf.zzbj;
        Class v2 = zzf.class;
        __monitor_enter(v2);
        try {
            if(v0 == zzf.zzbn) {
                v5_1.put(arg6, v1);
                zzf.zzbi.remove(arg6);
            }

            __monitor_exit(v2);
            return arg7;
        label_53:
            __monitor_exit(v2);
        }
        catch(Throwable v5_2) {
            goto label_53;
        }

        throw v5_2;
    }

    private static Object zzb(ContentResolver arg1) {
        Class v0 = zzf.class;
        __monitor_enter(v0);
        try {
            zzf.zza(arg1);
            __monitor_exit(v0);
            return zzf.zzbn;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_7;
        }

        throw v1;
    }

    static AtomicBoolean zzi() {
        return zzf.zzbh;
    }
}

