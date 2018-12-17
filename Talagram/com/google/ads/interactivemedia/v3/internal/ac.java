package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build$VERSION;
import android.view.WindowManager;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ac {
    class a {
        final float a;
        final float b;

        a(float arg1, float arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    static float a;
    private static WindowManager b;
    private static String[] c;

    static {
        ac.c = new String[]{"x", "y", "width", "height"};
        ac.a = Resources.getSystem().getDisplayMetrics().density;
    }

    public static void a(JSONObject arg1, String arg2, Object arg3) {
        try {
            arg1.put(arg2, arg3);
        }
        catch(JSONException v1) {
            StringBuilder v0 = new StringBuilder(String.valueOf(arg2).length() + 47);
            v0.append("JSONException during JSONObject.put for name [");
            v0.append(arg2);
            v0.append("]");
            ad.a(v0.toString(), ((Exception)v1));
        }
    }

    static float a(int arg1) {
        return (((float)arg1)) / ac.a;
    }

    public static JSONObject a(int arg4, int arg5, int arg6, int arg7) {
        JSONObject v0 = new JSONObject();
        try {
            v0.put("x", ((double)ac.a(arg4)));
            v0.put("y", ((double)ac.a(arg5)));
            v0.put("width", ((double)ac.a(arg6)));
            v0.put("height", ((double)ac.a(arg7)));
        }
        catch(JSONException v4) {
            ad.a("Error with creating viewStateObject", ((Exception)v4));
        }

        return v0;
    }

    public static void a(Context arg1) {
        if(arg1 != null) {
            ac.a = arg1.getResources().getDisplayMetrics().density;
            ac.b = arg1.getSystemService("window");
        }
    }

    public static void a(JSONObject arg4) {
        a v0 = ac.b(arg4);
        try {
            arg4.put("width", ((double)v0.a));
            arg4.put("height", ((double)v0.b));
        }
        catch(JSONException v4) {
            lp.a(((Throwable)v4));
        }
    }

    public static void a(JSONObject arg1, String arg2) {
        try {
            arg1.put("adSessionId", arg2);
        }
        catch(JSONException v1) {
            ad.a("Error with setting ad session id", ((Exception)v1));
        }
    }

    public static void a(JSONObject arg2, List arg3) {
        JSONArray v0 = new JSONArray();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            v0.put(v3.next());
        }

        try {
            arg2.put("isFriendlyObstructionFor", v0);
        }
        catch(JSONException v2) {
            ad.a("Error with setting friendly obstruction", ((Exception)v2));
        }
    }

    public static void a(JSONObject arg2, JSONObject arg3) {
        try {
            JSONArray v0 = arg2.optJSONArray("childViews");
            if(v0 == null) {
                v0 = new JSONArray();
                arg2.put("childViews", v0);
            }

            v0.put(arg3);
        }
        catch(JSONException v2) {
            lp.a(((Throwable)v2));
        }
    }

    private static boolean a(JSONArray arg2, JSONArray arg3) {
        boolean v0 = true;
        if(arg2 == null && arg3 == null) {
            return 1;
        }

        if(arg2 != null) {
            if(arg3 == null) {
            }
            else {
                if(arg2.length() == arg3.length()) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
        }

        return 0;
    }

    private static a b(JSONObject arg13) {
        float v13_1;
        float v1 = 0f;
        int v2 = 0;
        if(Build$VERSION.SDK_INT < 17) {
            JSONArray v13_2 = arg13.optJSONArray("childViews");
            if(v13_2 != null) {
                int v0 = v13_2.length();
                float v3 = 0f;
                while(v2 < v0) {
                    JSONObject v4 = v13_2.optJSONObject(v2);
                    if(v4 != null) {
                        double v5 = v4.optDouble("x");
                        double v7 = v4.optDouble("y");
                        double v9 = v4.optDouble("width");
                        double v11 = v4.optDouble("height");
                        v1 = Math.max(v1, ((float)(v5 + v9)));
                        v3 = Math.max(v3, ((float)(v7 + v11)));
                    }

                    ++v2;
                }

                v13_1 = v3;
            }
            else {
            label_43:
                v13_1 = 0f;
            }
        }
        else if(ac.b != null) {
            Point v13 = new Point(0, 0);
            ac.b.getDefaultDisplay().getRealSize(v13);
            v1 = ac.a(v13.x);
            v13_1 = ac.a(v13.y);
        }
        else {
            goto label_43;
        }

        return new a(v1, v13_1);
    }

    public static boolean b(JSONObject arg3, JSONObject arg4) {
        boolean v0 = true;
        if(arg3 == null && arg4 == null) {
            return 1;
        }

        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                if(!ac.c(arg3, arg4) || !ac.d(arg3, arg4) || !ac.e(arg3, arg4) || !ac.f(arg3, arg4)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    private static boolean c(JSONObject arg9, JSONObject arg10) {
        String[] v0 = ac.c;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            if(arg9.optDouble(v0[v3]) != arg10.optDouble(v0[v3])) {
                return 0;
            }
        }

        return 1;
    }

    private static boolean d(JSONObject arg2, JSONObject arg3) {
        return arg2.optString("adSessionId", "").equals(arg3.optString("adSessionId", ""));
    }

    private static boolean e(JSONObject arg5, JSONObject arg6) {
        JSONArray v5 = arg5.optJSONArray("isFriendlyObstructionFor");
        JSONArray v6 = arg6.optJSONArray("isFriendlyObstructionFor");
        if(v5 == null && v6 == null) {
            return 1;
        }

        if(!ac.a(v5, v6)) {
            return 0;
        }

        int v1;
        for(v1 = 0; v1 < v5.length(); ++v1) {
            if(!v5.optString(v1, "").equals(v6.optString(v1, ""))) {
                return 0;
            }
        }

        return 1;
    }

    private static boolean f(JSONObject arg5, JSONObject arg6) {
        JSONArray v5 = arg5.optJSONArray("childViews");
        JSONArray v6 = arg6.optJSONArray("childViews");
        if(v5 == null && v6 == null) {
            return 1;
        }

        if(!ac.a(v5, v6)) {
            return 0;
        }

        int v1;
        for(v1 = 0; v1 < v5.length(); ++v1) {
            if(!ac.b(v5.optJSONObject(v1), v6.optJSONObject(v1))) {
                return 0;
            }
        }

        return 1;
    }
}

