package net.hockeyapp.android.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import net.hockeyapp.android.a;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.k;
import net.hockeyapp.android.e.l;
import net.hockeyapp.android.j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AsyncTask {
    protected String a;
    protected String b;
    protected String c;
    protected Boolean d;
    protected j e;
    private WeakReference f;
    private long g;

    private String a(Context arg4, String arg5) {
        CharSequence v1_3;
        StringBuilder v0 = new StringBuilder();
        v0.append(this.a);
        v0.append("api/2/apps/");
        String v1 = this.c != null ? this.c : arg4.getPackageName();
        v0.append(v1);
        v0.append("?format=");
        v0.append(arg5);
        arg5 = null;
        try {
            Object v1_2 = a.a().get();
        }
        catch(ExecutionException v1_1) {
            e.a("Error get device identifier", ((Throwable)v1_1));
            v1_3 = ((CharSequence)arg5);
        }

        if(!TextUtils.isEmpty(v1_3)) {
            v0.append("&udid=");
            v0.append(this.a(((String)v1_3)));
        }

        SharedPreferences v4 = arg4.getSharedPreferences("net.hockeyapp.android.login", 0);
        v1 = v4.getString("auid", arg5);
        if(!TextUtils.isEmpty(((CharSequence)v1))) {
            v0.append("&auid=");
            v0.append(this.a(v1));
        }

        String v4_1 = v4.getString("iuid", arg5);
        if(!TextUtils.isEmpty(((CharSequence)v4_1))) {
            v0.append("&iuid=");
            v0.append(this.a(v4_1));
        }

        v0.append("&os=Android");
        v0.append("&os_version=");
        v0.append(this.a(a.d));
        v0.append("&device=");
        v0.append(this.a(a.f));
        v0.append("&oem=");
        v0.append(this.a(a.g));
        v0.append("&app_version=");
        v0.append(this.a(a.a));
        v0.append("&sdk=");
        v0.append(this.a("HockeySDK"));
        v0.append("&sdk_version=");
        v0.append(this.a("5.1.0"));
        v0.append("&lang=");
        v0.append(this.a(Locale.getDefault().getLanguage()));
        v0.append("&usage_time=");
        v0.append(this.g);
        return v0.toString();
    }

    private String a(String arg2) {
        try {
            return URLEncoder.encode(arg2, "UTF-8");
        }
        catch(UnsupportedEncodingException ) {
            return "";
        }
    }

    private boolean a(Context arg10, JSONArray arg11, int arg12) {
        int v1 = 0;
        boolean v2 = false;
        try {
            while(true) {
            label_3:
                if(v1 >= arg11.length()) {
                    return v2;
                }

                JSONObject v3 = arg11.getJSONObject(v1);
                int v4 = v3.getInt("version") > arg12 ? 1 : 0;
                int v6 = v3.getInt("version") != arg12 || !l.a(arg10, v3.getLong("timestamp")) ? 0 : 1;
                int v7 = l.a(v3.getString("minimum_os_version"), l.a(Build$VERSION.RELEASE)) <= 0 ? 1 : 0;
                if((v4 != 0 || v6 != 0) && v7 != 0) {
                    if(v3.has("mandatory")) {
                        this.d = Boolean.valueOf(this.d.booleanValue() | v3.getBoolean("mandatory"));
                    }

                    break;
                }

                goto label_46;
            }
        }
        catch(JSONException ) {
            return 0;
        }

        v2 = true;
    label_46:
        ++v1;
        goto label_3;
        return v2;
    }

    protected URLConnection a(URL arg3) {
        URLConnection v3 = arg3.openConnection();
        v3.addRequestProperty("User-Agent", "HockeySDK/Android 5.1.0");
        return v3;
    }

    protected JSONArray a(Void[] arg5) {
        JSONArray v0 = null;
        Object v5 = this.f != null ? this.f.get() : v0;
        if(v5 == null) {
            return v0;
        }

        this.b = this.a(((Context)v5), "apk");
        try {
            int v1_1 = this.b();
            URLConnection v2 = this.a(new URL(this.a(((Context)v5), "json")));
            v2.connect();
            BufferedInputStream v3 = new BufferedInputStream(v2.getInputStream());
            String v2_1 = k.a(((InputStream)v3));
            ((InputStream)v3).close();
            JSONArray v3_1 = new JSONArray(v2_1);
            if(!this.a(((Context)v5), v3_1, v1_1)) {
                return v0;
            }

            return this.b(v3_1);
        }
        catch(JSONException v1) {
            if(!k.a(((Context)v5))) {
                return v0;
            }

            e.b("HockeyUpdate", "Could not fetch updates although connected to internet", ((Throwable)v1));
        }

        return v0;
    }

    public void a() {
        this.f = null;
    }

    protected void a(JSONArray arg3) {
        if(arg3 != null) {
            e.a("HockeyUpdate", "Received Update Info");
            if(this.e != null) {
                this.e.a(arg3, this.b);
            }
        }
        else {
            e.a("HockeyUpdate", "No Update Info available");
            if(this.e != null) {
                this.e.a();
            }
        }
    }

    private JSONArray b(JSONArray arg5) {
        JSONArray v0 = new JSONArray();
        int v1 = 0;
        while(v1 < Math.min(arg5.length(), 25)) {
            try {
                v0.put(arg5.get(v1));
                goto label_9;
            }
            catch(JSONException ) {
            label_9:
                ++v1;
                continue;
            }
        }

        return v0;
    }

    protected int b() {
        return Integer.parseInt(a.a);
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected void onPostExecute(Object arg1) {
        this.a(((JSONArray)arg1));
    }
}

