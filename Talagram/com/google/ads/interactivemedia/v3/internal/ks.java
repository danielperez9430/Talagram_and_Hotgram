package com.google.ads.interactivemedia.v3.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ks {
    public static String a(String arg6, Object[] arg7) {
        arg6 = String.valueOf(arg6);
        int v0 = 0;
        if(arg7 == null) {
            arg7 = new Object[]{"(Object[])null"};
        }
        else {
            int v1;
            for(v1 = 0; v1 < arg7.length; ++v1) {
                arg7[v1] = ks.a(arg7[v1]);
            }
        }

        StringBuilder v1_1 = new StringBuilder(arg6.length() + arg7.length * 16);
        int v2 = 0;
        while(v0 < arg7.length) {
            int v3 = arg6.indexOf("%s", v2);
            if(v3 == -1) {
            }
            else {
                v1_1.append(((CharSequence)arg6), v2, v3);
                v1_1.append(arg7[v0]);
                v2 = v3 + 2;
                ++v0;
                continue;
            }

            break;
        }

        v1_1.append(((CharSequence)arg6), v2, arg6.length());
        if(v0 < arg7.length) {
            v1_1.append(" [");
            int v6 = v0 + 1;
            v1_1.append(arg7[v0]);
            while(v6 < arg7.length) {
                v1_1.append(", ");
                v1_1.append(arg7[v6]);
                ++v6;
            }

            v1_1.append(']');
        }

        return v1_1.toString();
    }

    private static String a(Object arg8) {
        try {
            return String.valueOf(arg8);
        }
        catch(Exception v0) {
            String v1 = arg8.getClass().getName();
            String v8 = Integer.toHexString(System.identityHashCode(arg8));
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 1 + String.valueOf(v8).length());
            v3.append(v1);
            v3.append('@');
            v3.append(v8);
            v8 = v3.toString();
            Logger v1_1 = Logger.getLogger("com.google.common.base.Strings");
            Level v2 = Level.WARNING;
            String v3_1 = "com.google.common.base.Strings";
            String v4 = "lenientToString";
            String v5 = "Exception during lenientFormat for ";
            String v6 = String.valueOf(v8);
            v5 = v6.length() != 0 ? v5.concat(v6) : new String(v5);
            v1_1.logp(v2, v3_1, v4, v5, v0);
            String v0_1 = v0.getClass().getName();
            StringBuilder v2_1 = new StringBuilder(String.valueOf(v8).length() + 9 + String.valueOf(v0_1).length());
            v2_1.append("<");
            v2_1.append(v8);
            v2_1.append(" threw ");
            v2_1.append(v0_1);
            v2_1.append(">");
            return v2_1.toString();
        }
    }

    public static boolean a(String arg0) {
        return kq.a(arg0);
    }
}

