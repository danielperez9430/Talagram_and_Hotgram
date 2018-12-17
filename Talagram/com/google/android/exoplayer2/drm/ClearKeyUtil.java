package com.google.android.exoplayer2.drm;

import android.util.Log;
import com.google.android.exoplayer2.util.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ClearKeyUtil {
    private static final String TAG = "ClearKeyUtil";

    private ClearKeyUtil() {
        super();
    }

    public static byte[] adjustRequestData(byte[] arg2) {
        if(Util.SDK_INT >= 27) {
            return arg2;
        }

        return Util.getUtf8Bytes(ClearKeyUtil.base64ToBase64Url(Util.fromUtf8Bytes(arg2)));
    }

    public static byte[] adjustResponseData(byte[] arg5) {
        if(Util.SDK_INT >= 27) {
            return arg5;
        }

        try {
            JSONObject v0_1 = new JSONObject(Util.fromUtf8Bytes(arg5));
            StringBuilder v1 = new StringBuilder("{\"keys\":[");
            JSONArray v0_2 = v0_1.getJSONArray("keys");
            int v2;
            for(v2 = 0; v2 < v0_2.length(); ++v2) {
                if(v2 != 0) {
                    v1.append(",");
                }

                JSONObject v3 = v0_2.getJSONObject(v2);
                v1.append("{\"k\":\"");
                v1.append(ClearKeyUtil.base64UrlToBase64(v3.getString("k")));
                v1.append("\",\"kid\":\"");
                v1.append(ClearKeyUtil.base64UrlToBase64(v3.getString("kid")));
                v1.append("\",\"kty\":\"");
                v1.append(v3.getString("kty"));
                v1.append("\"}");
            }

            v1.append("]}");
            return Util.getUtf8Bytes(v1.toString());
        }
        catch(JSONException v0) {
            Log.e("ClearKeyUtil", "Failed to adjust response data: " + Util.fromUtf8Bytes(arg5), ((Throwable)v0));
            return arg5;
        }
    }

    private static String base64ToBase64Url(String arg2) {
        return arg2.replace('+', '-').replace('/', '_');
    }

    private static String base64UrlToBase64(String arg2) {
        return arg2.replace('-', '+').replace('_', '/');
    }
}

