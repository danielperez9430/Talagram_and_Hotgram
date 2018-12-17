package com.google.android.gms.common.util;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting public class Strings {
    private static final Pattern zzaak;

    static {
        Strings.zzaak = Pattern.compile("\\$\\{(.*?)\\}");
    }

    private Strings() {
        super();
    }

    public static String capitalize(String arg3) {
        if(arg3.length() == 0) {
            return arg3;
        }

        char v0 = arg3.charAt(0);
        char v1 = Character.toUpperCase(v0);
        if(v0 == v1) {
            return arg3;
        }

        arg3 = arg3.substring(1);
        StringBuilder v0_1 = new StringBuilder(String.valueOf(arg3).length() + 1);
        v0_1.append(v1);
        v0_1.append(arg3);
        return v0_1.toString();
    }

    public static String emptyToNull(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            arg1 = null;
        }

        return arg1;
    }

    public static String format(String arg3, Bundle arg4) {
        Matcher v0 = Strings.zzaak.matcher(((CharSequence)arg3));
        if(v0.find()) {
            StringBuffer v1 = new StringBuffer();
            do {
                arg3 = v0.group(1);
                Object v2 = arg4.get(arg3);
                if(v2 != null) {
                    arg3 = v2.toString();
                }
                else if(arg4.containsKey(arg3)) {
                    arg3 = "null";
                }
                else {
                    arg3 = "";
                }

                v0.appendReplacement(v1, arg3);
            }
            while(v0.find());

            v0.appendTail(v1);
            arg3 = v1.toString();
        }

        return arg3;
    }

    public static boolean isEmptyOrWhitespace(String arg0) {
        if(arg0 != null) {
            if(arg0.trim().isEmpty()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public static String nullToEmpty(String arg0) {
        if(arg0 == null) {
            arg0 = "";
        }

        return arg0;
    }

    public static String padEnd(String arg1, int arg2, char arg3) {
        Preconditions.checkNotNull(arg1);
        if(arg1.length() >= arg2) {
            return arg1;
        }

        StringBuilder v0 = new StringBuilder(arg2);
        v0.append(arg1);
        int v1;
        for(v1 = arg1.length(); v1 < arg2; ++v1) {
            v0.append(arg3);
        }

        return v0.toString();
    }
}

