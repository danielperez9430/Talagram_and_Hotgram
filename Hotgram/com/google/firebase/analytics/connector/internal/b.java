package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class b {
    private static final Set a;
    private static final List b;
    private static final List c;
    private static final List d;
    private static final List e;
    private static final List f;

    static {
        b.a = new HashSet(Arrays.asList(new String[]{"_in", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "campaign_details", "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire"}));
        b.b = Arrays.asList(new String[]{"_e", "_f", "_iap", "_s", "_au", "_ui", "_cd", "app_open"});
        b.c = Arrays.asList(new String[]{"auto", "app", "am"});
        b.d = Arrays.asList(new String[]{"_r", "_dbg"});
        b.e = Arrays.asList(ArrayUtils.concat(new String[][]{UserProperty.zzado, UserProperty.zzadp}));
        b.f = Arrays.asList(new String[]{"^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$"});
    }

    public static boolean a(String arg1) {
        if(!b.c.contains(arg1)) {
            return 1;
        }

        return 0;
    }

    public static boolean a(String arg2, Bundle arg3) {
        if(b.b.contains(arg2)) {
            return 0;
        }

        if(arg3 != null) {
            Iterator v2 = b.d.iterator();
            do {
                if(v2.hasNext()) {
                    if(!arg3.containsKey(v2.next())) {
                        continue;
                    }

                    return 0;
                }

                return 1;
            }
            while(true);

            return 0;
        }

        return 1;
    }

    public static boolean a(String arg3, String arg4) {
        if(!"_ce1".equals(arg4)) {
            if("_ce2".equals(arg4)) {
            }
            else if("_ln".equals(arg4)) {
                if(!arg3.equals("fcm")) {
                    if(arg3.equals("fiam")) {
                    }
                    else {
                        return 0;
                    }
                }

                return 1;
            }
            else {
                if(b.e.contains(arg4)) {
                    return 0;
                }

                Iterator v3 = b.f.iterator();
                do {
                    if(!v3.hasNext()) {
                        return 1;
                    }
                }
                while(!arg4.matches(v3.next()));

                return 0;
            }
        }

        if(!arg3.equals("fcm")) {
            if(arg3.equals("frc")) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public static boolean a(String arg4, String arg5, Bundle arg6) {
        int v4;
        if(!"_cmp".equals(arg5)) {
            return 1;
        }

        if(!b.a(arg4)) {
            return 0;
        }

        if(arg6 == null) {
            return 0;
        }

        Iterator v5 = b.d.iterator();
        do {
            if(!v5.hasNext()) {
                goto label_19;
            }
        }
        while(!arg6.containsKey(v5.next()));

        return 0;
    label_19:
        int v2 = arg4.hashCode();
        if(v2 != 101200) {
            if(v2 != 101230) {
                if(v2 != 3142703) {
                    goto label_43;
                }
                else if(arg4.equals("fiam")) {
                    v4 = 2;
                }
                else {
                    goto label_43;
                }
            }
            else if(arg4.equals("fdl")) {
                v4 = 1;
            }
            else {
                goto label_43;
            }
        }
        else if(arg4.equals("fcm")) {
            v4 = 0;
        }
        else {
        label_43:
            v4 = -1;
        }

        switch(v4) {
            case 0: {
                goto label_53;
            }
            case 1: {
                goto label_50;
            }
            case 2: {
                goto label_46;
            }
        }

        return 0;
    label_50:
        arg4 = "_cis";
        arg5 = "fdl_integration";
        goto label_48;
    label_53:
        arg4 = "_cis";
        arg5 = "fcm_integration";
        goto label_48;
    label_46:
        arg4 = "_cis";
        arg5 = "fiam_integration";
    label_48:
        arg6.putString(arg4, arg5);
        return 1;
    }
}

