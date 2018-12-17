package com.f.a.c;

import com.f.a.b.b;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.Security;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    final class a {
        public final String a;
        public final String b;

        public a(String arg1, String arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    private static final SSLSocketFactory a;

    static {
        d.a = new f();
    }

    public static b a(Map arg2, c arg3) {
        return d.a("POST", d.a(), arg2, arg3);
    }

    private static b a(String arg6, String arg7, Map arg8, c arg9) {
        b v6_4;
        String v3;
        b v0 = null;
        if(arg9 == null) {
            return v0;
        }

        Boolean v1 = Boolean.valueOf(true);
        try {
            v3 = Security.getProperty("networkaddress.cache.ttl");
        }
        catch(SecurityException ) {
            v3 = ((String)v0);
            goto label_13;
        }

        try {
            Security.setProperty("networkaddress.cache.ttl", "0");
        }
        catch(SecurityException ) {
        label_13:
            v1 = Boolean.valueOf(false);
        }

        if(!arg9.c().trim().isEmpty()) {
            try {
                e v6_1 = d.b(arg6, arg7, arg8, arg9);
                int v7 = v6_1.a();
                String v8 = v6_1.b();
                Map v6_2 = v6_1.c();
                Object v6_3 = v6_2 == null ? v0 : v6_2.get("Request-Id");
                if(v6_3 == null || ((List)v6_3).size() <= 0) {
                    arg6 = ((String)v0);
                }
                else {
                    v6_3 = ((List)v6_3).get(0);
                }

                if(v7 < 200 || v7 >= 300) {
                    d.a(v8, v7, arg6);
                }

                v6_4 = g.a(v8);
            }
            catch(Throwable v6) {
                if(v1.booleanValue()) {
                    if(v3 == null) {
                        Security.setProperty("networkaddress.cache.ttl", "-1");
                    }
                    else {
                        Security.setProperty("networkaddress.cache.ttl", v3);
                    }
                }

                throw v6;
            }
            catch(JSONException ) {
                if(v1.booleanValue()) {
                    if(v3 == null) {
                        Security.setProperty("networkaddress.cache.ttl", "-1");
                    }
                    else {
                        Security.setProperty("networkaddress.cache.ttl", v3);
                    }
                }

                return v0;
            }

            if(v1.booleanValue()) {
                if(v3 == null) {
                    Security.setProperty("networkaddress.cache.ttl", "-1");
                }
                else {
                    Security.setProperty("networkaddress.cache.ttl", v3);
                }
            }

            return v6_4;
        }

        throw new com.f.a.a.c("No API key provided. (HINT: set your API key using \'Stripe.apiKey = <API-KEY>\'. You can generate API keys from the Stripe web interface. See https://stripe.com/api for details or email support@stripe.com if you have questions.", ((String)v0), Integer.valueOf(0));
    }

    private static void a(String arg13, int arg14, String arg15) {
        com.f.a.c.b$a v13 = com.f.a.c.b.a(arg13);
        if(arg14 == 429) {
            goto label_53;
        }

        switch(arg14) {
            case 400: {
                goto label_43;
            }
            case 401: {
                goto label_38;
            }
            case 402: {
                goto label_25;
            }
            case 403: {
                goto label_20;
            }
            case 404: {
                goto label_10;
            }
        }

        throw new com.f.a.a.b(v13.b, arg15, Integer.valueOf(arg14), null);
    label_20:
        throw new com.f.a.a.f(v13.b, arg15, Integer.valueOf(arg14));
    label_38:
        throw new com.f.a.a.c(v13.b, arg15, Integer.valueOf(arg14));
    label_25:
        throw new com.f.a.a.d(v13.b, arg15, v13.c, v13.d, v13.e, v13.f, Integer.valueOf(arg14), null);
    label_10:
        throw new com.f.a.a.e(v13.b, v13.d, arg15, Integer.valueOf(arg14), null);
    label_43:
        throw new com.f.a.a.e(v13.b, v13.d, arg15, Integer.valueOf(arg14), null);
    label_53:
        throw new com.f.a.a.g(v13.b, v13.d, arg15, Integer.valueOf(arg14), null);
    }

    static String a() {
        return String.format("%s/v1/%s", "https://api.stripe.com", "tokens");
    }

    private static e a(String arg6, String arg7, String arg8, c arg9) {
        e v9;
        int v0 = -1;
        HttpURLConnection v3 = null;
        try {
            int v4 = arg6.hashCode();
            if(v4 != 70454) {
                if(v4 != 2461856) {
                }
                else if(arg6.equals("POST")) {
                    v0 = 1;
                }
            }
            else if(arg6.equals("GET")) {
                v0 = 0;
            }

            switch(v0) {
                case 0: {
                    goto label_24;
                }
                case 1: {
                    goto label_22;
                }
            }

            throw new com.f.a.a.a(String.format("Unrecognized HTTP method %s. This indicates a bug in the Stripe bindings. Please contact support@stripe.com for assistance.", arg6));
        label_22:
            HttpURLConnection v6_2 = d.b(arg7, arg8, arg9);
            goto label_25;
        label_24:
            v6_2 = d.a(arg7, arg8, arg9);
        label_25:
            v3 = v6_2;
            int v6_3 = v3.getResponseCode();
            InputStream v7 = v6_3 < 200 || v6_3 >= 300 ? v3.getErrorStream() : v3.getInputStream();
            arg7 = d.a(v7);
            v9 = new e(v6_3, arg7, v3.getHeaderFields());
            if(v3 == null) {
                return v9;
            }
        }
        catch(Throwable v6) {
        }
        catch(IOException v6_1) {
            try {
                throw new com.f.a.a.a(String.format("IOException during API request to Stripe (%s): %s Please check your internet connection and try again. If this problem persists, you should check Stripe\'s service status at https://twitter.com/stripestatus, or let us know at support@stripe.com.", d.a(), v6_1.getMessage()), ((Throwable)v6_1));
            }
            catch(Throwable v6) {
                if(v3 != null) {
                    v3.disconnect();
                }

                throw v6;
            }
        }

        v3.disconnect();
        return v9;
    }

    private static HttpURLConnection a(String arg0, String arg1, c arg2) {
        HttpURLConnection v0 = d.a(d.a(arg0, arg1), arg2);
        v0.setRequestMethod("GET");
        return v0;
    }

    private static String a(InputStream arg2) {
        String v0 = new Scanner(arg2, "UTF-8").useDelimiter("\\A").next();
        arg2.close();
        return v0;
    }

    private static String a(String arg1) {
        if(arg1 == null) {
            return null;
        }

        return URLEncoder.encode(arg1, "UTF-8");
    }

    private static String a(String arg4, String arg5) {
        if(arg5 != null) {
            if(arg5.isEmpty()) {
            }
            else {
                String v0 = arg4.contains("?") ? "&" : "?";
                arg4 = String.format("%s%s%s", arg4, v0, arg5);
            }
        }

        return arg4;
    }

    static String a(Map arg3) {
        StringBuilder v0 = new StringBuilder();
        Iterator v3 = d.b(arg3).iterator();
        while(v3.hasNext()) {
            if(v0.length() > 0) {
                v0.append("&");
            }

            Object v1 = v3.next();
            v0.append(d.b(((a)v1).a, ((a)v1).b));
        }

        return v0.toString();
    }

    private static HttpURLConnection a(String arg2, c arg3) {
        URLConnection v2 = new URL(arg2).openConnection();
        ((HttpURLConnection)v2).setConnectTimeout(30000);
        ((HttpURLConnection)v2).setReadTimeout(80000);
        ((HttpURLConnection)v2).setUseCaches(false);
        Iterator v3 = d.a(arg3).entrySet().iterator();
        while(v3.hasNext()) {
            Object v0 = v3.next();
            ((HttpURLConnection)v2).setRequestProperty(((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }

        if((v2 instanceof HttpsURLConnection)) {
            v2.setSSLSocketFactory(d.a);
        }

        return ((HttpURLConnection)v2);
    }

    static Map a(c arg8) {
        HashMap v0 = new HashMap();
        String v1 = arg8.a();
        ((Map)v0).put("Accept-Charset", "UTF-8");
        ((Map)v0).put("Accept", "application/json");
        Object[] v5 = new Object[1];
        int v7 = 0;
        v5[0] = "3.5.0";
        ((Map)v0).put("User-Agent", String.format("Stripe/v1 JavaBindings/%s", v5));
        ((Map)v0).put("Authorization", String.format("Bearer %s", arg8.c()));
        String[] v2 = new String[]{"os.name", "os.version", "os.arch", "java.version", "java.vendor", "java.vm.version", "java.vm.vendor"};
        HashMap v3 = new HashMap();
        int v4 = v2.length;
        while(v7 < v4) {
            ((Map)v3).put(v2[v7], System.getProperty(v2[v7]));
            ++v7;
        }

        ((Map)v3).put("bindings.version", "3.5.0");
        ((Map)v3).put("lang", "Java");
        ((Map)v3).put("publisher", "Stripe");
        ((Map)v0).put("X-Stripe-Client-User-Agent", new JSONObject(((Map)v3)).toString());
        if(v1 != null) {
            ((Map)v0).put("Stripe-Version", v1);
        }

        if(arg8.b() != null) {
            ((Map)v0).put("Idempotency-Key", arg8.b());
        }

        return ((Map)v0);
    }

    private static List a(Object arg6, String arg7) {
        LinkedList v6_1;
        List v6;
        if((arg6 instanceof Map)) {
            v6 = d.a(((Map)arg6), arg7);
        }
        else if((arg6 instanceof List)) {
            v6 = d.a(((List)arg6), arg7);
        }
        else if("".equals(arg6)) {
            goto label_27;
        }
        else if(arg6 == null) {
            v6_1 = new LinkedList();
            ((List)v6_1).add(new a(arg7, ""));
        }
        else {
            LinkedList v0 = new LinkedList();
            ((List)v0).add(new a(arg7, arg6.toString()));
            v6_1 = v0;
        }

        return v6;
    label_27:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("You cannot set \'");
        v0_1.append(arg7);
        v0_1.append("\' to an empty string. We interpret empty strings as null in requests. You may set \'");
        v0_1.append(arg7);
        v0_1.append("\' to null to delete the property.");
        throw new com.f.a.a.e(v0_1.toString(), arg7, null, Integer.valueOf(0), null);
    }

    private static List a(Map arg6, String arg7) {
        LinkedList v0 = new LinkedList();
        if(arg6 == null) {
            return ((List)v0);
        }

        Iterator v6 = arg6.entrySet().iterator();
        while(v6.hasNext()) {
            Object v1 = v6.next();
            Object v2 = ((Map$Entry)v1).getKey();
            v1 = ((Map$Entry)v1).getValue();
            if(arg7 != null) {
                String v2_1 = String.format("%s[%s]", arg7, v2);
            }

            ((List)v0).addAll(d.a(v1, ((String)v2)));
        }

        return ((List)v0);
    }

    private static List a(List arg5, String arg6) {
        LinkedList v0 = new LinkedList();
        Iterator v1 = arg5.iterator();
        String v2 = String.format("%s[]", arg6);
        if(arg5.isEmpty()) {
            ((List)v0).add(new a(arg6, ""));
        }
        else {
            while(v1.hasNext()) {
                ((List)v0).addAll(d.a(v1.next(), v2));
            }
        }

        return ((List)v0);
    }

    private static e b(String arg6, String arg7, Map arg8, c arg9) {
        String v8;
        try {
            v8 = d.a(arg8);
        }
        catch(UnsupportedEncodingException v5) {
            throw new com.f.a.a.e("Unable to encode parameters to UTF-8. Please contact support@stripe.com for assistance.", null, null, Integer.valueOf(0), ((Throwable)v5));
        }

        return d.a(arg6, arg7, v8, arg9);
    }

    private static HttpURLConnection b(String arg4, String arg5, c arg6) {
        OutputStream v6;
        HttpURLConnection v4 = d.a(arg4, arg6);
        v4.setDoOutput(true);
        v4.setRequestMethod("POST");
        v4.setRequestProperty("Content-Type", String.format("application/x-www-form-urlencoded;charset=%s", "UTF-8"));
        try {
            v6 = v4.getOutputStream();
        }
        catch(Throwable v4_1) {
            v6 = null;
            goto label_24;
        }

        try {
            v6.write(arg5.getBytes("UTF-8"));
            if(v6 == null) {
                return v4;
            }

            goto label_18;
        }
        catch(Throwable v4_1) {
        }

    label_24:
        if(v6 != null) {
            v6.close();
        }

        throw v4_1;
    label_18:
        v6.close();
        return v4;
    }

    private static List b(Map arg1) {
        return d.a(arg1, null);
    }

    private static String b(String arg3, String arg4) {
        return String.format("%s=%s", d.a(arg3), d.a(arg4));
    }
}

