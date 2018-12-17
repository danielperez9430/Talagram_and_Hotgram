package com.google.android.exoplayer2.util;

import android.net.Uri$Builder;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Iterator;

public final class UriUtil {
    private static final int FRAGMENT = 3;
    private static final int INDEX_COUNT = 4;
    private static final int PATH = 1;
    private static final int QUERY = 2;
    private static final int SCHEME_COLON;

    private UriUtil() {
        super();
    }

    private static int[] getUriIndices(String arg10) {
        int v10;
        int[] v0 = new int[4];
        int v3 = -1;
        if(TextUtils.isEmpty(((CharSequence)arg10))) {
            v0[0] = v3;
            return v0;
        }

        int v1 = arg10.length();
        int v4 = arg10.indexOf(35);
        if(v4 == v3) {
        }
        else {
            v1 = v4;
        }

        v4 = arg10.indexOf(63);
        if(v4 == v3 || v4 > v1) {
            v4 = v1;
        }

        int v5 = 47;
        int v6 = arg10.indexOf(v5);
        if(v6 == v3 || v6 > v4) {
            v6 = v4;
        }

        int v7 = arg10.indexOf(58);
        if(v7 > v6) {
            v7 = -1;
        }

        v6 = v7 + 2;
        v6 = v6 >= v4 || arg10.charAt(v7 + 1) != v5 || arg10.charAt(v6) != v5 ? 0 : 1;
        if(v6 != 0) {
            v10 = arg10.indexOf(v5, v7 + 3);
            if(v10 != v3 && v10 <= v4) {
                goto label_47;
            }

            v10 = v4;
        }
        else {
            v10 = v7 + 1;
        }

    label_47:
        v0[0] = v7;
        v0[1] = v10;
        v0[2] = v4;
        v0[3] = v1;
        return v0;
    }

    private static String removeDotSegments(StringBuilder arg7, int arg8, int arg9) {
        int v3;
        if(arg8 >= arg9) {
            return arg7.toString();
        }

        int v1 = 47;
        if(arg7.charAt(arg8) == v1) {
            ++arg8;
        }

        int v2 = arg8;
        int v0 = arg9;
        while(true) {
        label_9:
            arg9 = v2;
            while(true) {
            label_10:
                if(arg9 > v0) {
                    goto label_49;
                }

                if(arg9 == v0) {
                    v3 = arg9;
                }
                else if(arg7.charAt(arg9) == v1) {
                    v3 = arg9 + 1;
                }
                else {
                    break;
                }

                int v4 = v2 + 1;
                int v5 = 46;
                if(arg9 == v4 && arg7.charAt(v2) == v5) {
                    arg7.delete(v2, v3);
                    v0 -= v3 - v2;
                    goto label_9;
                }

                if(arg9 != v2 + 2 || arg7.charAt(v2) != v5 || arg7.charAt(v4) != v5) {
                    ++arg9;
                }
                else {
                    arg9 = arg7.lastIndexOf("/", v2 - 2) + 1;
                    v2 = arg9 > arg8 ? arg9 : arg8;
                    arg7.delete(v2, v3);
                    v0 -= v3 - v2;
                }

                v2 = arg9;
                goto label_9;
            }
        }

        ++arg9;
        goto label_10;
    label_49:
        return arg7.toString();
    }

    public static Uri removeQueryParameter(Uri arg5, String arg6) {
        Object v2;
        Uri$Builder v0 = arg5.buildUpon();
        v0.clearQuery();
        Iterator v1 = arg5.getQueryParameterNames().iterator();
        do {
        label_4:
            if(!v1.hasNext()) {
                goto label_16;
            }

            v2 = v1.next();
        }
        while(((String)v2).equals(arg6));

        Iterator v3 = arg5.getQueryParameters(((String)v2)).iterator();
        goto label_11;
    label_16:
        return v0.build();
        while(true) {
        label_11:
            if(!v3.hasNext()) {
                goto label_4;
            }

            v0.appendQueryParameter(((String)v2), v3.next());
        }
    }

    public static String resolve(String arg10, String arg11) {
        StringBuilder v0 = new StringBuilder();
        if(arg10 == null) {
            arg10 = "";
        }

        if(arg11 == null) {
            arg11 = "";
        }

        int[] v1 = UriUtil.getUriIndices(arg11);
        int v4 = -1;
        int v5 = 2;
        if(v1[0] != v4) {
            v0.append(arg11);
            UriUtil.removeDotSegments(v0, v1[1], v1[v5]);
            return v0.toString();
        }

        int[] v3 = UriUtil.getUriIndices(arg10);
        int v7 = 3;
        if(v1[v7] == 0) {
            v0.append(((CharSequence)arg10), 0, v3[v7]);
            v0.append(arg11);
            return v0.toString();
        }

        if(v1[v5] == 0) {
            v0.append(((CharSequence)arg10), 0, v3[v5]);
            v0.append(arg11);
            return v0.toString();
        }

        if(v1[1] != 0) {
            int v3_1 = v3[0] + 1;
            v0.append(((CharSequence)arg10), 0, v3_1);
            v0.append(arg11);
            return UriUtil.removeDotSegments(v0, v1[1] + v3_1, v3_1 + v1[v5]);
        }

        char v8 = '/';
        if(arg11.charAt(v1[1]) == v8) {
            v0.append(((CharSequence)arg10), 0, v3[1]);
            v0.append(arg11);
            return UriUtil.removeDotSegments(v0, v3[1], v3[1] + v1[v5]);
        }

        if(v3[0] + v5 < v3[1] && v3[1] == v3[v5]) {
            v0.append(((CharSequence)arg10), 0, v3[1]);
            v0.append(v8);
            v0.append(arg11);
            return UriUtil.removeDotSegments(v0, v3[1], v3[1] + v1[v5] + 1);
        }

        v7 = arg10.lastIndexOf(v8, v3[v5] - 1);
        v4 = v7 == v4 ? v3[1] : v7 + 1;
        v0.append(((CharSequence)arg10), 0, v4);
        v0.append(arg11);
        return UriUtil.removeDotSegments(v0, v3[1], v4 + v1[v5]);
    }

    public static Uri resolveToUri(String arg0, String arg1) {
        return Uri.parse(UriUtil.resolve(arg0, arg1));
    }
}

