package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting public class UnicodeUtils {
    private static final Pattern zzaal;

    static {
        UnicodeUtils.zzaal = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
    }

    public UnicodeUtils() {
        super();
    }

    public static String unescape(String arg4) {
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            Matcher v0 = UnicodeUtils.zzaal.matcher(((CharSequence)arg4));
            StringBuffer v1 = null;
            while(v0.find()) {
                if(v1 == null) {
                    v1 = new StringBuffer();
                }

                v0.appendReplacement(v1, new String(Character.toChars(Integer.parseInt(v0.group().substring(2), 16))));
            }

            if(v1 == null) {
                return arg4;
            }

            v0.appendTail(v1);
            arg4 = v1.toString();
        }

        return arg4;
    }
}

