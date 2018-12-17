package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

public class ViewUtils {
    private ViewUtils() {
        super();
    }

    public static String getXmlAttributeString(String arg3, String arg4, Context arg5, AttributeSet arg6, boolean arg7, boolean arg8, String arg9) {
        StringBuilder v6_1;
        arg3 = arg6 == null ? null : arg6.getAttributeValue(arg3, arg4);
        if(arg3 != null && (arg3.startsWith("@string/")) && (arg7)) {
            int v6 = 8;
            String v7 = arg3.substring(v6);
            String v0 = arg5.getPackageName();
            TypedValue v1 = new TypedValue();
            try {
                Resources v5 = arg5.getResources();
                v6_1 = new StringBuilder(String.valueOf(v0).length() + v6 + String.valueOf(v7).length());
                v6_1.append(v0);
                v6_1.append(":string/");
                v6_1.append(v7);
                v5.getValue(v6_1.toString(), v1, true);
            }
            catch(Resources$NotFoundException ) {
                v6_1 = new StringBuilder(String.valueOf(arg4).length() + 30 + String.valueOf(arg3).length());
                v6_1.append("Could not find resource for ");
                v6_1.append(arg4);
                v6_1.append(": ");
                v6_1.append(arg3);
                Log.w(arg9, v6_1.toString());
            }

            if(v1.string != null) {
                arg3 = v1.string.toString();
            }
            else {
                String v5_1 = String.valueOf(v1);
                StringBuilder v7_1 = new StringBuilder(String.valueOf(arg4).length() + 28 + String.valueOf(v5_1).length());
                v7_1.append("Resource ");
                v7_1.append(arg4);
                v7_1.append(" was not a string: ");
                v7_1.append(v5_1);
                Log.w(arg9, v7_1.toString());
            }
        }

        if((arg8) && arg3 == null) {
            v6_1 = new StringBuilder(String.valueOf(arg4).length() + 33);
            v6_1.append("Required XML attribute \"");
            v6_1.append(arg4);
            v6_1.append("\" missing");
            Log.w(arg9, v6_1.toString());
        }

        return arg3;
    }
}

