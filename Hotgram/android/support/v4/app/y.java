package android.support.v4.app;

import android.app.Notification$Builder;
import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class y {
    private static final Object a;
    private static Field b;
    private static boolean c;
    private static final Object d;

    static {
        y.a = new Object();
        y.d = new Object();
    }

    public static Bundle a(Notification arg6) {
        Object v3_1;
        Field v3;
        Bundle v2;
        Object v0 = y.a;
        __monitor_enter(v0);
        try {
            v2 = null;
            if(!y.c) {
                goto label_8;
            }

            __monitor_exit(v0);
            return v2;
        }
        catch(Throwable v6) {
            goto label_47;
        }

        try {
        label_8:
            if(y.b == null) {
                v3 = Notification.class.getDeclaredField("extras");
                if(!Bundle.class.isAssignableFrom(v3.getType())) {
                    Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                    y.c = true;
                    goto label_21;
                }
                else {
                    goto label_23;
                }
            }

            goto label_25;
        }
        catch(IllegalAccessException v6_1) {
            goto label_40;
        }
        catch(NoSuchFieldException v6_2) {
            goto label_35;
        }
        catch(Throwable v6) {
            goto label_47;
        }

        try {
        label_21:
            __monitor_exit(v0);
            return v2;
        }
        catch(Throwable v6) {
            goto label_47;
        }

        try {
        label_23:
            v3.setAccessible(true);
            y.b = v3;
        label_25:
            v3_1 = y.b.get(arg6);
            if(v3_1 == null) {
                Bundle v3_2 = new Bundle();
                y.b.set(arg6, v3_2);
            }

            goto label_32;
        }
        catch(Throwable v6) {
        label_48:
            throw v6;
        }
        catch(NoSuchFieldException v6_2) {
        }
        catch(IllegalAccessException v6_1) {
            try {
            label_40:
                String v3_3 = "NotificationCompat";
                String v4 = "Unable to access notification extras";
                goto label_37;
            label_35:
                v3_3 = "NotificationCompat";
                v4 = "Unable to access notification extras";
            label_37:
                Log.e(v3_3, v4, ((Throwable)v6_1));
                y.c = true;
                __monitor_exit(v0);
                return v2;
            label_32:
                __monitor_exit(v0);
                return ((Bundle)v3_1);
            label_47:
                __monitor_exit(v0);
                goto label_48;
            }
            catch(Throwable v6) {
                goto label_47;
            }
        }
    }

    static Bundle a(a arg4) {
        Bundle v0 = new Bundle();
        v0.putInt("icon", arg4.a());
        v0.putCharSequence("title", arg4.b());
        v0.putParcelable("actionIntent", arg4.c());
        Bundle v1 = arg4.d() != null ? new Bundle(arg4.d()) : new Bundle();
        v1.putBoolean("android.support.allowGeneratedReplies", arg4.e());
        v0.putBundle("extras", v1);
        v0.putParcelableArray("remoteInputs", y.a(arg4.f()));
        v0.putBoolean("showsUserInterface", arg4.i());
        v0.putInt("semanticAction", arg4.g());
        return v0;
    }

    public static Bundle a(Notification$Builder arg3, a arg4) {
        arg3.addAction(arg4.a(), arg4.b(), arg4.c());
        Bundle v3 = new Bundle(arg4.d());
        if(arg4.f() != null) {
            v3.putParcelableArray("android.support.remoteInputs", y.a(arg4.f()));
        }

        if(arg4.h() != null) {
            v3.putParcelableArray("android.support.dataRemoteInputs", y.a(arg4.h()));
        }

        v3.putBoolean("android.support.allowGeneratedReplies", arg4.e());
        return v3;
    }

    private static Bundle[] a(ac[] arg3) {
        if(arg3 == null) {
            return null;
        }

        Bundle[] v0 = new Bundle[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = y.a(arg3[v1]);
        }

        return v0;
    }

    private static Bundle a(ac arg3) {
        Bundle v0 = new Bundle();
        v0.putString("resultKey", arg3.a());
        v0.putCharSequence("label", arg3.b());
        v0.putCharSequenceArray("choices", arg3.c());
        v0.putBoolean("allowFreeFormInput", arg3.f());
        v0.putBundle("extras", arg3.g());
        Set v3 = arg3.d();
        if(v3 != null && !v3.isEmpty()) {
            ArrayList v1 = new ArrayList(v3.size());
            Iterator v3_1 = v3.iterator();
            while(v3_1.hasNext()) {
                v1.add(v3_1.next());
            }

            v0.putStringArrayList("allowedDataTypes", v1);
        }

        return v0;
    }

    public static SparseArray a(List arg4) {
        int v0 = arg4.size();
        SparseArray v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg4.get(v2);
            if(v3 != null) {
                if(v1 == null) {
                    v1 = new SparseArray();
                }

                v1.put(v2, v3);
            }
        }

        return v1;
    }
}

