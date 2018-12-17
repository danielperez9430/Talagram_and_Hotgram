package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting public final class JsonUtils {
    private static final Pattern zzaae;
    private static final Pattern zzaaf;

    static {
        JsonUtils.zzaae = Pattern.compile("\\\\.");
        JsonUtils.zzaaf = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    private JsonUtils() {
        super();
    }

    public static boolean areJsonStringsEquivalent(String arg3, String arg4) {
        if(arg3 == null && arg4 == null) {
            return 1;
        }

        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                try {
                    return JsonUtils.areJsonValuesEquivalent(new JSONObject(arg3), new JSONObject(arg4));
                }
                catch(JSONException ) {
                    try {
                        return JsonUtils.areJsonValuesEquivalent(new JSONArray(arg3), new JSONArray(arg4));
                    }
                    catch(JSONException ) {
                        return 0;
                    }
                }
            }
        }

        return 0;
    }

    public static boolean areJsonValuesEquivalent(Object arg5, Object arg6) {
        if(arg5 == null && arg6 == null) {
            return 1;
        }

        if(arg5 != null) {
            if(arg6 == null) {
            }
            else {
                if(((arg5 instanceof JSONObject)) && ((arg6 instanceof JSONObject))) {
                    if(((JSONObject)arg5).length() != ((JSONObject)arg6).length()) {
                        return 0;
                    }
                    else {
                        Iterator v2 = ((JSONObject)arg5).keys();
                        do {
                            if(v2.hasNext()) {
                                Object v3 = v2.next();
                                if(!((JSONObject)arg6).has(((String)v3))) {
                                    return 0;
                                }
                                else {
                                    try {
                                        if(JsonUtils.areJsonValuesEquivalent(((JSONObject)arg5).get(((String)v3)), ((JSONObject)arg6).get(((String)v3)))) {
                                            continue;
                                        }

                                        return 0;
                                    }
                                    catch(JSONException ) {
                                        return 0;
                                    }
                                }
                            }
                            else {
                                return 1;
                            }

                            goto label_29;
                        }
                        while(true);

                        return 1;
                    }
                }

            label_29:
                if(((arg5 instanceof JSONArray)) && ((arg6 instanceof JSONArray))) {
                    if(((JSONArray)arg5).length() != ((JSONArray)arg6).length()) {
                        return 0;
                    }
                    else {
                        int v2_1;
                        for(v2_1 = 0; v2_1 < ((JSONArray)arg5).length(); ++v2_1) {
                            try {
                                if(JsonUtils.areJsonValuesEquivalent(((JSONArray)arg5).get(v2_1), ((JSONArray)arg6).get(v2_1))) {
                                    goto label_45;
                                }
                            }
                            catch(JSONException ) {
                                return 0;
                            }

                            return 0;
                        label_45:
                        }

                        return 1;
                    }
                }

                return arg5.equals(arg6);
            }
        }

        return 0;
    }

    public static String escapeString(String arg4) {
        String v2_1;
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            Matcher v0 = JsonUtils.zzaaf.matcher(((CharSequence)arg4));
            StringBuffer v1 = null;
            while(v0.find()) {
                if(v1 == null) {
                    v1 = new StringBuffer();
                }

                int v2 = v0.group().charAt(0);
                if(v2 == 34) {
                    v2_1 = "\\\\\\\"";
                }
                else if(v2 == 47) {
                    v2_1 = "\\\\/";
                }
                else if(v2 != 92) {
                    switch(v2) {
                        case 8: {
                            goto label_31;
                        }
                        case 9: {
                            goto label_28;
                        }
                        case 10: {
                            goto label_26;
                        }
                    }

                    switch(v2) {
                        case 12: {
                            goto label_24;
                        }
                        case 13: {
                            goto label_22;
                        }
                    }

                    continue;
                label_22:
                    v2_1 = "\\\\r";
                    goto label_29;
                label_24:
                    v2_1 = "\\\\f";
                    goto label_29;
                label_26:
                    v2_1 = "\\\\n";
                    goto label_29;
                label_28:
                    v2_1 = "\\\\t";
                    goto label_29;
                label_31:
                    v2_1 = "\\\\b";
                }
                else {
                    v2_1 = "\\\\\\\\";
                }

            label_29:
                v0.appendReplacement(v1, v2_1);
            }

            if(v1 == null) {
                return arg4;
            }

            v0.appendTail(v1);
            arg4 = v1.toString();
        }

        return arg4;
    }

    public static String unescapeString(String arg4) {
        String v2_1;
        if(!TextUtils.isEmpty(((CharSequence)arg4))) {
            arg4 = UnicodeUtils.unescape(arg4);
            Matcher v0 = JsonUtils.zzaae.matcher(((CharSequence)arg4));
            StringBuffer v1 = null;
            while(v0.find()) {
                if(v1 == null) {
                    v1 = new StringBuffer();
                }

                int v2 = v0.group().charAt(1);
                if(v2 == 34) {
                    v2_1 = "\"";
                }
                else if(v2 == 47) {
                    v2_1 = "/";
                }
                else if(v2 == 92) {
                    v2_1 = "\\\\";
                }
                else if(v2 == 98) {
                    v2_1 = "\b";
                }
                else if(v2 == 102) {
                    v2_1 = "\f";
                }
                else if(v2 == 110) {
                    v2_1 = "\n";
                }
                else if(v2 == 114) {
                    v2_1 = "\r";
                }
                else if(v2 == 116) {
                    v2_1 = "\t";
                }
                else {
                    throw new IllegalStateException("Found an escaped character that should never be.");
                }

                v0.appendReplacement(v1, v2_1);
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

