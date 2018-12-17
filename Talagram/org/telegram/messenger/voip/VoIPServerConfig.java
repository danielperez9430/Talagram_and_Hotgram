package org.telegram.messenger.voip;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class VoIPServerConfig {
    private static JSONObject config;

    public VoIPServerConfig() {
        super();
    }

    public static boolean getBoolean(String arg1, boolean arg2) {
        return VoIPServerConfig.config.optBoolean(arg1, arg2);
    }

    public static double getDouble(String arg1, double arg2) {
        return VoIPServerConfig.config.optDouble(arg1, arg2);
    }

    public static int getInt(String arg1, int arg2) {
        return VoIPServerConfig.config.optInt(arg1, arg2);
    }

    public static String getString(String arg1, String arg2) {
        return VoIPServerConfig.config.optString(arg1, arg2);
    }

    private static native void nativeSetConfig(String[] arg0, String[] arg1) {
    }

    public static void setConfig(String arg5) {
        try {
            JSONObject v0 = new JSONObject(arg5);
            VoIPServerConfig.config = v0;
            String[] v5_1 = new String[v0.length()];
            String[] v1 = new String[v0.length()];
            Iterator v2 = v0.keys();
            int v3;
            for(v3 = 0; v2.hasNext(); ++v3) {
                v5_1[v3] = v2.next();
                v1[v3] = v0.getString(v5_1[v3]);
            }

            VoIPServerConfig.nativeSetConfig(v5_1, v1);
        }
        catch(JSONException v5) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("Error parsing VoIP config", ((Throwable)v5));
        }
    }
}

