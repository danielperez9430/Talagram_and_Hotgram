package net.hockeyapp.android.e;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class f {
    private final String a;
    private String b;
    private String c;
    private j d;
    private int e;
    private final Map f;

    public f(String arg3) {
        super();
        this.e = 120000;
        this.a = arg3;
        this.f = new HashMap();
        this.f.put("User-Agent", "HockeySDK/Android 5.1.0");
    }

    private static String a(Map arg5, String arg6) {
        ArrayList v0 = new ArrayList();
        Iterator v1 = arg5.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            Object v3 = arg5.get(v2);
            String v2_1 = URLEncoder.encode(((String)v2), arg6);
            String v3_1 = URLEncoder.encode(((String)v3), arg6);
            ((List)v0).add(v2_1 + "=" + v3_1);
        }

        return TextUtils.join("&", ((Iterable)v0));
    }

    public HttpURLConnection a() {
        URLConnection v0 = new URL(this.a).openConnection();
        ((HttpURLConnection)v0).setConnectTimeout(this.e);
        ((HttpURLConnection)v0).setReadTimeout(this.e);
        if(!TextUtils.isEmpty(this.b)) {
            ((HttpURLConnection)v0).setRequestMethod(this.b);
            if((TextUtils.isEmpty(this.c)) && !this.b.equalsIgnoreCase("POST") && !this.b.equalsIgnoreCase("PUT")) {
                goto label_26;
            }

            ((HttpURLConnection)v0).setDoOutput(true);
        }

    label_26:
        Iterator v1 = this.f.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            ((HttpURLConnection)v0).setRequestProperty(((String)v2), this.f.get(v2));
        }

        if(!TextUtils.isEmpty(this.c)) {
            BufferedWriter v2_1 = new BufferedWriter(new OutputStreamWriter(((HttpURLConnection)v0).getOutputStream(), "UTF-8"));
            v2_1.write(this.c);
            v2_1.flush();
            v2_1.close();
        }

        if(this.d != null) {
            ((HttpURLConnection)v0).setRequestProperty("Content-Length", String.valueOf(this.d.d()));
            this.d.a(((HttpURLConnection)v0).getOutputStream());
        }

        return ((HttpURLConnection)v0);
    }

    public f a(String arg1) {
        this.b = arg1;
        return this;
    }

    public f a(String arg2, String arg3) {
        this.f.put(arg2, arg3);
        return this;
    }

    public f a(Map arg9) {
        long v5;
        Object v2;
        Object v1_1;
        int v1 = 25;
        if(arg9.size() > v1) {
            goto label_42;
        }

        Iterator v0 = arg9.keySet().iterator();
        while(true) {
            if(!v0.hasNext()) {
                goto label_31;
            }

            v1_1 = v0.next();
            v2 = arg9.get(v1_1);
            if(v2 == null) {
                continue;
            }

            v5 = 4194304;
            if((((long)((String)v2).length())) > v5) {
                break;
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("Form field ");
        v0_1.append(((String)v1_1));
        v0_1.append(" size too large: ");
        v0_1.append(((String)v2).length());
        v0_1.append(" - max allowed: ");
        v0_1.append(v5);
        throw new IllegalArgumentException(v0_1.toString());
        try {
        label_31:
            String v9_1 = f.a(arg9, "UTF-8");
            this.a("Content-Type", "application/x-www-form-urlencoded");
            this.b(v9_1);
            return this;
        }
        catch(UnsupportedEncodingException v9) {
            throw new RuntimeException(((Throwable)v9));
        }

    label_42:
        StringBuilder v2_1 = new StringBuilder();
        v2_1.append("Fields size too large: ");
        v2_1.append(arg9.size());
        v2_1.append(" - max allowed: ");
        v2_1.append(v1);
        throw new IllegalArgumentException(v2_1.toString());
    }

    public f a(Map arg8, Context arg9, List arg10) {
        Object v1;
        try {
            this.d = new j(File.createTempFile("multipart", null, arg9.getCacheDir()));
            this.d.b();
            Iterator v0 = arg8.keySet().iterator();
            while(v0.hasNext()) {
                v1 = v0.next();
                this.d.a(((String)v1), arg8.get(v1));
            }

            int v0_1;
            for(v0_1 = 0; v0_1 < arg10.size(); ++v0_1) {
                v1 = arg10.get(v0_1);
                boolean v3 = true;
                if(v0_1 == arg10.size() - 1) {
                }
                else {
                    v3 = false;
                }

                InputStream v2 = arg9.getContentResolver().openInputStream(((Uri)v1));
                String v1_1 = ((Uri)v1).getLastPathSegment();
                j v4 = this.d;
                v4.a("attachment" + v0_1, v1_1, v2, v3);
            }

            this.d.c();
            this.a("Content-Type", "multipart/form-data; boundary=" + this.d.a());
            return this;
        }
        catch(IOException v8) {
            throw new RuntimeException(((Throwable)v8));
        }
    }

    public f b(String arg1) {
        this.c = arg1;
        return this;
    }

    public f b(String arg3, String arg4) {
        StringBuilder v0 = new StringBuilder();
        v0.append("Basic ");
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3);
        v1.append(":");
        v1.append(arg4);
        v0.append(b.a(v1.toString().getBytes(), 2));
        this.a("Authorization", v0.toString());
        return this;
    }
}

