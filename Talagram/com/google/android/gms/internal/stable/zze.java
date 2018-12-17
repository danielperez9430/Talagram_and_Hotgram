package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.HashMap;

public final class zze {
    public class zza implements BaseColumns {
        private static HashMap zzagq;

        static {
            zza.zzagq = new HashMap();
        }

        public zza() {
            super();
        }

        private static zzh zza(ContentResolver arg4, Uri arg5) {
            zzh v0_1;
            Object v0 = zza.zzagq.get(arg5);
            if(v0 == null) {
                v0_1 = new zzh();
                zza.zzagq.put(arg5, v0_1);
                arg4.registerContentObserver(arg5, true, new zzf(null, v0_1));
            }
            else if(((zzh)v0).zzagu.getAndSet(false)) {
                __monitor_enter(v0);
                try {
                    ((zzh)v0).zzags.clear();
                    ((zzh)v0).zzagt = new Object();
                    __monitor_exit(v0);
                    return v0_1;
                label_26:
                    __monitor_exit(v0);
                }
                catch(Throwable v4) {
                    goto label_26;
                }

                throw v4;
            }

            return v0_1;
        }

        protected static String zza(ContentResolver arg11, Uri arg12, String arg13) {
            Cursor v3_1;
            Cursor v11_1;
            String v3;
            Object v0_1;
            zzh v1;
            Class v0 = zza.class;
            __monitor_enter(v0);
            try {
                v1 = zza.zza(arg11, arg12);
                __monitor_exit(v0);
            }
            catch(Throwable v11) {
                try {
                label_73:
                    __monitor_exit(v0);
                }
                catch(Throwable v11) {
                    goto label_73;
                }

                throw v11;
            }

            __monitor_enter(v1);
            try {
                v0_1 = v1.zzagt;
                if(v1.zzags.containsKey(arg13)) {
                    __monitor_exit(v1);
                    return v1.zzags.get(arg13);
                }

                __monitor_exit(v1);
                v3 = null;
            }
            catch(Throwable v11) {
                try {
                label_70:
                    __monitor_exit(v1);
                }
                catch(Throwable v11) {
                    goto label_70;
                }

                throw v11;
            }

            try {
                v11_1 = arg11.query(arg12, new String[]{"value"}, "name=?", new String[]{arg13}, null);
                if(v11_1 == null) {
                    goto label_45;
                }

                goto label_28;
            }
            catch(Throwable v12) {
            }
            catch(SQLException v0_2) {
                String v2 = v3;
                goto label_53;
                try {
                label_28:
                    if(!v11_1.moveToFirst()) {
                    }
                    else {
                        v2 = v11_1.getString(0);
                        goto label_32;
                    }

                    goto label_45;
                }
                catch(SQLException v0_2) {
                    goto label_42;
                }

                try {
                label_32:
                    zza.zza(v1, v0_1, arg13, v2);
                    if(v11_1 == null) {
                        return v2;
                    }
                }
                catch(SQLException v0_2) {
                    goto label_43;
                }

                v11_1.close();
                return v2;
                try {
                label_45:
                    zza.zza(v1, v0_1, arg13, v3);
                    if(v11_1 == null) {
                        return v3;
                    }

                    goto label_47;
                }
                catch(Throwable v12) {
                    v3_1 = v11_1;
                }
                catch(SQLException v0_2) {
                label_42:
                    v2 = v3;
                label_43:
                    v3_1 = v11_1;
                    try {
                    label_53:
                        StringBuilder v1_1 = new StringBuilder("Can\'t get key ");
                        v1_1.append(arg13);
                        v1_1.append(" from ");
                        v1_1.append(arg12);
                        Log.e("GoogleSettings", v1_1.toString(), ((Throwable)v0_2));
                        if((((Cursor)v3)) == null) {
                            return v2;
                        }
                    }
                    catch(Throwable v12) {
                        goto label_66;
                    }

                    ((Cursor)v3).close();
                    return v2;
                }
            }

        label_66:
            if((((Cursor)v3)) != null) {
                ((Cursor)v3).close();
            }

            throw v12;
        label_47:
            v11_1.close();
            return v3;
        }

        private static void zza(zzh arg1, Object arg2, String arg3, String arg4) {
            __monitor_enter(arg1);
            try {
                if(arg2 == arg1.zzagt) {
                    arg1.zzags.put(arg3, arg4);
                }

                __monitor_exit(arg1);
                return;
            label_8:
                __monitor_exit(arg1);
            }
            catch(Throwable v2) {
                goto label_8;
            }

            throw v2;
        }
    }

}

