package org.telegram.customization.g;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.crashlytics.android.a.m;
import com.google.a.f;
import com.google.a.g;
import com.google.a.l;
import com.google.a.o;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.telegram.customization.Model.Ads.Statistics;
import org.telegram.customization.Model.Payment.HostRequestData;
import org.telegram.customization.Model.Payment.HostResponseData;
import org.telegram.customization.Model.Payment.User;
import org.telegram.customization.Model.SearchRequest;
import org.telegram.customization.Model.SettingAndUpdate;
import org.telegram.customization.compression.lz4.LZ4Factory;
import org.telegram.customization.dynamicadapter.GsonAdapterFactory;
import org.telegram.customization.dynamicadapter.viewholder.ResponseReportHelper;
import org.telegram.customization.dynamicadapter.viewholder.ResponseSettleHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import utils.view.Constants;
import utils.volley.DefaultRetryPolicy;
import utils.volley.Request;
import utils.volley.RequestQueue;
import utils.volley.Response$ErrorListener;
import utils.volley.Response$Listener;
import utils.volley.RetryPolicy;
import utils.volley.TimeoutError;
import utils.volley.VolleyError;
import utils.volley.toolbox.StringRequest;
import utils.volley.toolbox.Volley;

public class c implements d, ErrorListener, Listener {
    interface a {
        RetryPolicy a();

        void a(VolleyError arg1);

        void a(org.telegram.customization.g.a arg1);

        Request b();

        boolean c();

        Type d();

        String e();
    }

    interface b extends a {
        String f();
    }

    Handler a;
    public static long b = 0;
    public static long c = 0;
    public static long d = 0;
    private String e;
    private static RequestQueue f = null;
    private final Context g;
    private a h;
    private d i;
    private d j;
    private int k;
    private static boolean l = false;
    private static long m;

    static {
    }

    private c(Context arg2, d arg3) {
        super();
        this.k = 1;
        this.g = arg2;
        this.i = ((d)this);
        this.j = arg3;
        try {
            this.a = new Handler(Looper.getMainLooper());
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public static RequestQueue a(Context arg1) {
        if(c.f == null) {
            c.f = Volley.newRequestQueue(arg1);
        }

        return c.f;
    }

    public static c a(Context arg1, d arg2) {
        return new c(arg1, arg2);
    }

    static d a(c arg0) {
        return arg0.j;
    }

    public static RequestQueue a() {
        return c.f;
    }

    private void a(a arg2) {
        if(this.a != null) {
            new Thread(arg2) {
                public void run() {
                    super.run();
                    c.a(this.b, this.a);
                }
            }.start();
        }
        else {
            this.b(arg2);
        }
    }

    static void a(c arg0, a arg1) {
        arg0.b(arg1);
    }

    static byte[] a(c arg0, String arg1) {
        return arg0.j(arg1);
    }

    public void a(int arg10, int arg11, long arg12, long arg14) {
        this.a(new a(arg10, arg11, arg12, arg14) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                Object v3 = arg3.a();
                if(v3 != null) {
                    c.c(this.e).onResult(((ResponseReportHelper)v3).getResponse(), 33);
                }
            }

            public void a(VolleyError arg3) {
                c.c(this.e).onResult(null, -33);
            }

            public Request b() {
                return new org.telegram.customization.g.b(0, String.format("v12/payment/gpr?pageIndex=%s&pageCount=%s&startDate=%s&endDate=%s", Integer.valueOf(this.a), Integer.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.d)), this.e, this.e, c.d(this.e), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "Application/json";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return ResponseReportHelper.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_REPORT_PAYMENT";
            }
        });
    }

    public void a(long arg12, long arg14, boolean arg16, long arg17, int arg19) {
        this.a(new a(arg14, arg12, arg16, arg19, arg17) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                o v3 = new f().a(arg3.a(), l.class).k();
                String v3_1 = v3.b("data") != null ? v3.b("data").toString() : null;
                c.c(this.f).onResult(new g().a(new GsonAdapterFactory()).b().a(v3_1, new com.google.a.c.a() {
                }.b()), 1);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.f).onResult(v3, -1);
            }

            public Request b() {
                if(this.a == 0) {
                    utils.a.b.l(c.d(this.f));
                }

                String v0 = "v12/content/getHome?tagId=%s&direction=%s&pageSize=%s&mediaType=%s&lastRow=%s&itr=%s";
                Object[] v1 = new Object[6];
                v1[0] = Long.valueOf(this.b);
                String v3 = this.c ? "previous" : "next";
                v1[1] = v3;
                v1[2] = Integer.valueOf(this.d);
                v1[3] = Long.valueOf(this.e);
                v1[4] = Long.valueOf(this.a);
                v1[5] = Long.valueOf(utils.a.b.m(c.d(this.f)));
                return new org.telegram.customization.g.b(0, String.format(v0, v1), this.f, this.f, c.d(this.f), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1051";
            }

            public String toString() {
                return "WS_GET_HOME";
            }
        });
    }

    public void a(long arg14, long arg16, boolean arg18, String arg19, long arg20, int arg22, long arg23, boolean arg25) {
        this.a(new b(arg19, arg18, arg16, arg20, arg22, arg25, arg23) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                o v3 = new f().a(arg3.a(), l.class).k();
                String v3_1 = v3.b("data") != null ? v3.b("data").toString() : null;
                c.c(this.h).onResult(new g().a(new GsonAdapterFactory()).b().a(v3_1, new com.google.a.c.a() {
                }.b()), 1);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.h).onResult(v3, -1);
            }

            public Request b() {
                try {
                    URLEncoder.encode(this.a, "UTF-8");
                    goto label_4;
                }
                catch(Exception ) {
                label_4:
                    return new org.telegram.customization.g.b(1, "v12/content/getSearch", this.h, this.h, c.d(this.h), this.e()) {
                        public byte[] getBody() {
                            SearchRequest v0 = new SearchRequest();
                            v0.setDirection(this.b.b);
                            v0.setLastRow(this.b.c);
                            v0.setMediaType(this.b.d);
                            v0.setPageSize(this.b.e);
                            v0.setPhrasesearch(this.b.f);
                            v0.setSortOrder(this.b.g);
                            v0.setSearchTerm(this.b.a);
                            return c.h(new f().a(v0));
                        }

                        protected Map getParams() {
                            return super.getParams();
                        }
                    };
                }
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1051";
            }

            public String f() {
                return "v12/content/getSearch";
            }

            public String toString() {
                return "WS_GET_SEARCH";
            }
        });
    }

    public void a(long arg8, String arg10, View arg11) {
        this.a(new a(arg11, arg10, arg8) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg4) {
                c.c(this.d).onResult(new Object[]{this.a, new f().a(arg4.a(), org.telegram.news.b.b.class)}, 12);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.d).onResult(v3, -12);
            }

            public Request b() {
                StringBuilder v0 = new StringBuilder();
                v0.append(utils.a.b.u(c.d(this.d)));
                v0.append("generalcontent/v5/getitem?newsId=%s&ParentTagId=%s");
                return new StringRequest(0, String.format(v0.toString(), this.b, Long.valueOf(this.c)), this.d, this.d) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public Map getHeaders() {
                        HashMap v0 = new HashMap();
                        ((Map)v0).put("X-SLS-Version", String.valueOf(166));
                        String v1 = "550205994";
                        String v2 = "-1";
                        if(v1.length() > 0) {
                            ((Map)v0).put("X-SLS-DeviceId", String.valueOf(v1));
                        }
                        else {
                            ((Map)v0).put("X-SLS-DeviceId", String.valueOf(0));
                        }

                        if(v2.length() > 0) {
                            v1 = "X-SLS-UserId";
                            v2 = String.valueOf(v2);
                        }
                        else {
                            v1 = "X-SLS-UserId";
                            v2 = String.valueOf(0);
                        }

                        ((Map)v0).put(v1, v2);
                        ((Map)v0).put("X-SLS-AppId", String.valueOf(4));
                        try {
                            ((Map)v0).put("X-SLS-Token", "dVQGpLWTfXBOs7dH2FI37LX+MmI=");
                            goto label_33;
                        }
                        catch(Exception ) {
                        label_33:
                            return ((Map)v0);
                        }
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return null;
            }

            public String toString() {
                return "WS_GET_NEWS_LIST";
            }
        });
    }

    public void a(long arg9, String arg11, String arg12, int arg13) {
        this.a(new a(arg9, arg13, arg11, arg12) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg4) {
                Object v4 = arg4.a();
                f v0 = new f();
                o v4_1 = v0.a(((String)v4), l.class).k();
                ArrayList v1 = new ArrayList();
                if(v4_1.b("result") != null) {
                    Object v1_1 = v0.a(v4_1.b("result").toString(), new com.google.a.c.a() {
                    }.b());
                }

                c.c(this.e).onResult(v1, 11);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.e).onResult(v3, -11);
            }

            public Request b() {
                StringBuilder v0 = new StringBuilder();
                v0.append(utils.a.b.u(c.d(this.e)));
                v0.append("generalcontent/v5/GetListByTagId?tagId=%s&count=%s&nid=%s&dir=%s&itemId=%s&date=&legendId=%s");
                return new StringRequest(0, String.format(v0.toString(), Long.valueOf(this.a), Integer.valueOf(this.b), this.c, this.d, "", "", ""), this.e, this.e) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public Map getHeaders() {
                        HashMap v0 = new HashMap();
                        ((Map)v0).put("X-SLS-Version", String.valueOf(166));
                        String v1 = "550205994";
                        String v2 = "-1";
                        if(v1.length() > 0) {
                            ((Map)v0).put("X-SLS-DeviceId", String.valueOf(v1));
                        }
                        else {
                            ((Map)v0).put("X-SLS-DeviceId", String.valueOf(0));
                        }

                        if(v2.length() > 0) {
                            v1 = "X-SLS-UserId";
                            v2 = String.valueOf(v2);
                        }
                        else {
                            v1 = "X-SLS-UserId";
                            v2 = String.valueOf(0);
                        }

                        ((Map)v0).put(v1, v2);
                        ((Map)v0).put("X-SLS-AppId", String.valueOf(4));
                        try {
                            ((Map)v0).put("X-SLS-Token", "dVQGpLWTfXBOs7dH2FI37LX+MmI=");
                            goto label_33;
                        }
                        catch(Exception ) {
                        label_33:
                            return ((Map)v0);
                        }
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return null;
            }

            public String toString() {
                return "WS_GET_NEWS_LIST";
            }
        });
    }

    public void a(Runnable arg2) {
        if(this.j != null) {
            if(this.a != null) {
                this.a.post(arg2);
            }
            else {
                arg2.run();
            }
        }
    }

    public void a(String arg3) {
        Log.d("LEE", "HandleRequest onResponse");
        try {
            if(this.a != null) {
                new Thread(arg3) {
                    public void run() {
                        super.run();
                        this.b.b(this.a);
                    }
                }.start();
            }
            else {
                this.b(arg3);
            }

            return;
        }
        catch(Exception ) {
            return;
        }
    }

    public void a(String arg2, String arg3) {
        this.a(new a(arg2, arg3) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                Object v3 = arg3.a();
                if(v3 != null) {
                    c.c(this.c).onResult(v3, 36);
                }
            }

            public void a(VolleyError arg3) {
                c.c(this.c).onResult(null, -36);
            }

            public Request b() {
                return new org.telegram.customization.g.b(1, String.format("v12/payment/fp?paymentId=%s&description=%s", this.a, this.b), this.c, this.c, c.d(this.c), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "text/plain";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return HostRequestData.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_POST_T_M";
            }
        });
    }

    public void a(HostResponseData arg2) {
        this.a(new a(arg2) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                c.c(this.b).onResult(arg3, 30);
            }

            public void a(VolleyError arg3) {
                c.c(this.b).onResult(null, -30);
            }

            public Request b() {
                return new org.telegram.customization.g.b(1, "v12/payment/ic", this.b, this.b, c.d(this.b), this.e()) {
                    public byte[] getBody() {
                        return c.a(this.b.b, new f().a(this.b.a));
                    }

                    public String getBodyContentType() {
                        return "Application/json";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return User.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_POST_T_M";
            }
        });
    }

    public void a(User arg2) {
        this.a(new a(arg2) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                c.c(this.b).onResult(arg3, 28);
            }

            public void a(VolleyError arg3) {
                c.c(this.b).onResult(null, -28);
            }

            public Request b() {
                return new org.telegram.customization.g.b(1, "v12/payment/rsu", this.b, this.b, c.d(this.b), this.e()) {
                    public byte[] getBody() {
                        return c.a(this.b.b, new f().a(this.b.a));
                    }

                    public String getBodyContentType() {
                        return "Application/json";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_POST_T_M";
            }
        });
    }

    public void a(VolleyError arg7) {
        StringBuilder v2;
        String v1_1;
        try {
            if(this.h.e().equals("set-1056")) {
                c.l = false;
            }

            goto label_7;
        }
        catch(Exception ) {
        label_7:
            String v0 = "0";
            if(arg7 != null) {
                try {
                    if((arg7 instanceof TimeoutError)) {
                        v0 = "1000";
                    }
                    else {
                    label_15:
                        if(arg7 != null && arg7.networkResponse != null) {
                            v0 = String.valueOf(arg7.networkResponse.statusCode);
                        }
                    }
                }
                catch(Exception v0_1) {
                    goto label_14;
                }
            }
            else {
                goto label_15;
            }

            try {
                v1_1 = this.h.b().getUrl() + " ";
            }
            catch(Throwable ) {
                goto label_65;
            }
            catch(Exception v0_1) {
                goto label_14;
            }

            try {
                v2 = new StringBuilder();
                v2.append(v1_1);
                v2.append(arg7.networkResponse.headers.toString());
                v2.toString();
                goto label_45;
            }
            catch(Throwable ) {
            }
            catch(Exception ) {
                try {
                    v2 = new StringBuilder();
                    v2.append(v1_1);
                    v2.append("bad class");
                    v2.toString();
                label_45:
                    com.crashlytics.android.a.b v1_2 = com.crashlytics.android.a.b.c();
                    m v2_1 = new m("NETWORK_ERROR");
                    String v3 = this.h.toString();
                    StringBuilder v4 = new StringBuilder();
                    v4.append(v0);
                    v4.append(" - ");
                    v4.append(166);
                    v4.append(" ");
                    v4.append("v12/");
                    v1_2.a(v2_1.a(v3, v4.toString()));
                    goto label_65;
                }
                catch(Exception v0_1) {
                label_87:
                    v0_1.printStackTrace();
                    goto label_88;
                }
                catch(Throwable ) {
                    try {
                    label_65:
                        Log.d("LEE", "HandleRequest onErrorResponseInternal " + v0 + this.h.e());
                        goto label_76;
                    label_14:
                        goto label_87;
                    }
                    catch(Exception v0_1) {
                        goto label_14;
                    }
                }
            }

            try {
            label_76:
                if(this.h.e() == "set-1067") {
                    goto label_88;
                }

                if(this.h.e() == "KEY_CALL_URL") {
                    goto label_88;
                }

                if(!this.k(v0)) {
                    goto label_88;
                }
            }
            catch(Exception ) {
                goto label_88;
            }

            return;
        label_88:
            if(this.j == null) {
                return;
            }

            try {
                this.h.a(arg7);
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    private void b(a arg6) {
        DefaultRetryPolicy v1_1;
        this.h = arg6;
        Request v0 = this.h.b();
        this.e = v0.getUrl();
        if(v0 == null || !ConnectionsManager.isNetworkOnline()) {
            this.i.onResult("offline", -1000);
        }
        else {
            RetryPolicy v1 = arg6.a();
            if(v1 == null) {
                v1_1 = new DefaultRetryPolicy(org.telegram.customization.g.f.a(this.g), 0, 1f);
            }

            v0.setRetryPolicy(((RetryPolicy)v1_1));
            if((arg6 instanceof b)) {
                try {
                    v0.setTag(((b)arg6).f());
                    goto label_21;
                }
                catch(Exception ) {
                label_21:
                    c.a().add(v0);
                    return;
                }
            }

            goto label_21;
        }
    }

    static a b(c arg0) {
        return arg0.h;
    }

    public void b(String arg6) {
        int v6_2;
        org.telegram.customization.g.a v4;
        String v3;
        String v2_1;
        org.telegram.customization.g.a v2;
        try {
            if(this.h.e().equals("set-1056")) {
                c.l = false;
            }

            goto label_7;
        }
        catch(Exception ) {
        label_7:
            if(this.j == null) {
                return;
            }

            int v0 = -1;
            VolleyError v1 = null;
            try {
                if(!this.h.c()) {
                    goto label_23;
                }

                v2 = new org.telegram.customization.g.a();
            }
            catch(Exception v6) {
                goto label_70;
            }

            try {
                v2.a(arg6);
                this.h.a(v2);
                return;
            }
            catch(Exception v6) {
                try {
                label_71:
                    v6.printStackTrace();
                }
                catch(Exception ) {
                }

                goto label_72;
            }

            try {
            label_23:
                o v6_1 = new f().a(arg6, l.class).k();
                v2_1 = v6_1.b("code") != null ? v6_1.b("code").toString() : ((String)v1);
                v3 = v6_1.b("data") != null ? v6_1.b("data").toString() : ((String)v1);
                arg6 = v6_1.b("message") != null ? v6_1.b("message").toString() : ((String)v1);
                v4 = new org.telegram.customization.g.a();
                v4.a(arg6);
            }
            catch(Exception v6) {
                goto label_70;
            }

            try {
                v6_2 = Integer.parseInt(v2_1);
            }
            catch(Exception ) {
                v6_2 = -1;
            }

            try {
                v4.a(v6_2);
                v4.a(new f().a(v3, this.h.d()));
                this.h.a(v4);
                v2 = ((org.telegram.customization.g.a)v1);
            }
            catch(Exception v6) {
            label_70:
                v2 = ((org.telegram.customization.g.a)v1);
                goto label_71;
            }

        label_72:
            if(v2 == null) {
                v2 = new org.telegram.customization.g.a();
                v2.a(v0);
                v2.a("");
            }

            try {
                this.h.a(v2);
                return;
            }
            catch(Exception ) {
                try {
                    this.h.a(v1);
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }
    }

    public void b() {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                o v3 = new f().a(arg3.a(), l.class).k();
                String v3_1 = v3.b("data") != null ? v3.b("data").toString() : null;
                c.c(this.a).onResult(new g().a(new GsonAdapterFactory()).b().a(v3_1, new com.google.a.c.a() {
                }.b()), 9);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.a).onResult(v3, -9);
            }

            public Request b() {
                return new org.telegram.customization.g.b(0, String.format("v12/user/getTags?userId=%s", Integer.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id)), this.a, this.a, c.d(this.a), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1051";
            }

            public String toString() {
                return "WS_GET_USER_TAGS";
            }
        });
    }

    public void b(int arg10, int arg11, long arg12, long arg14) {
        this.a(new a(arg10, arg11, arg12, arg14) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                Object v3 = arg3.a();
                if(v3 != null) {
                    c.c(this.e).onResult(((ResponseSettleHelper)v3).getResponse(), 34);
                }
            }

            public void a(VolleyError arg3) {
                c.c(this.e).onResult(null, -34);
            }

            public Request b() {
                return new org.telegram.customization.g.b(0, String.format("v12/payment/gsr?pageIndex=%s&pageCount=%s&startDate=%s&endDate=%s", Integer.valueOf(this.a), Integer.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.d)), this.e, this.e, c.d(this.e), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "Application/json";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return ResponseSettleHelper.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_REPORT_SETTLEMENT";
            }
        });
    }

    public void b(long arg12, long arg14, boolean arg16, long arg17, int arg19) {
        this.a(new a(arg12, arg16, arg19, arg17, arg14) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                o v3 = new f().a(arg3.a(), l.class).k();
                String v3_1 = v3.b("data") != null ? v3.b("data").toString() : null;
                c.c(this.f).onResult(new g().a(new GsonAdapterFactory()).b().a(v3_1, new com.google.a.c.a() {
                }.b()), 1);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.f).onResult(v3, -1);
            }

            public Request b() {
                String v0 = "v12/content/getDashboard?userId=%s&direction=%s&pageSize=%s&mediaType=%s&lastRow=%s";
                Object[] v1 = new Object[5];
                v1[0] = Long.valueOf(this.a);
                String v2 = this.b ? "previous" : "next";
                v1[1] = v2;
                v1[2] = Integer.valueOf(this.c);
                v1[3] = Long.valueOf(this.d);
                v1[4] = Long.valueOf(this.e);
                return new org.telegram.customization.g.b(0, String.format(v0, v1), this.f, this.f, c.d(this.f), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1051";
            }

            public String toString() {
                return "WS_GET_DASHBOARD";
            }
        });
    }

    static d c(c arg0) {
        return arg0.i;
    }

    public static void c(String arg1) {
        c.a().cancelAll(arg1);
    }

    public void c() {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg4) {
                try {
                    o v4_1 = new f().a(arg4.a(), l.class).k();
                    String v0 = null;
                    if(v4_1.b("data") != null) {
                        v0 = v4_1.b("data").toString();
                    }

                    Object v4_2 = new f().a(v0, SettingAndUpdate.class);
                    utils.c.a(((SettingAndUpdate)v4_2).getSetting(), c.d(this.a));
                    Log.d("alireza ", "alireza setting " + new f().a(v4_2));
                    c.c(this.a).onResult(v4_2, 10);
                }
                catch(Exception v4) {
                    v4.printStackTrace();
                    arg4 = new org.telegram.customization.g.a();
                    arg4.a(-1);
                    arg4.a("");
                    c.c(this.a).onResult(arg4, -10);
                }
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.a).onResult(v3, -10);
            }

            public Request b() {
                org.telegram.customization.g.c$4$1 v0 = new org.telegram.customization.g.b(0, String.format("v5/config?appId=%s&appVer=%s&apiVer=%s", String.valueOf(c.d(this.a).getResources().getInteger(2131361792)), String.valueOf(166), String.valueOf(12)), this.a, this.a, c.d(this.a), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
                ((org.telegram.customization.g.b)v0).setShouldCache(false);
                return ((Request)v0);
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1066";
            }

            public String toString() {
                return "WS_LIGHT_HOST_CONFIG";
            }
        });
    }

    static Context d(c arg0) {
        return arg0.g;
    }

    public void d() {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                o v3 = new f().a(arg3.a(), l.class).k();
                String v3_1 = v3.b("data") != null ? v3.b("data").toString() : null;
                c.c(this.a).onResult(new g().a(new GsonAdapterFactory()).b().a(v3_1, new com.google.a.c.a() {
                }.b()), 21);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.a).onResult(v3, -21);
            }

            public Request b() {
                return new org.telegram.customization.g.b(0, "v12/ad/Categories", this.a, this.a, c.d(this.a), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1051";
            }

            public String toString() {
                return "WS_GET_HOME";
            }
        });
    }

    public void d(String arg1) {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                c.c(this.a).onResult(new f().a(arg3.a(), org.telegram.customization.util.view.Poll.c.class), 13);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.a).onResult(v3, -13);
            }

            public Request b() {
                StringBuilder v0 = new StringBuilder();
                v0.append(utils.a.b.u(c.d(this.a)));
                v0.append("election/v5/GetVoteResult");
                return new StringRequest(0, v0.toString(), this.a, this.a) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public Map getHeaders() {
                        HashMap v0 = new HashMap();
                        ((Map)v0).put("X-SLS-Version", String.valueOf(166));
                        String v1 = "550205994";
                        String v2 = "-1";
                        if(v1.length() > 0) {
                            ((Map)v0).put("X-SLS-DeviceId", String.valueOf(v1));
                        }
                        else {
                            ((Map)v0).put("X-SLS-DeviceId", String.valueOf(0));
                        }

                        if(v2.length() > 0) {
                            v1 = "X-SLS-UserId";
                            v2 = String.valueOf(v2);
                        }
                        else {
                            v1 = "X-SLS-UserId";
                            v2 = String.valueOf(0);
                        }

                        ((Map)v0).put(v1, v2);
                        ((Map)v0).put("X-SLS-AppId", String.valueOf(4));
                        try {
                            ((Map)v0).put("X-SLS-Token", "dVQGpLWTfXBOs7dH2FI37LX+MmI=");
                            goto label_33;
                        }
                        catch(Exception ) {
                        label_33:
                            return ((Map)v0);
                        }
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return null;
            }

            public String toString() {
                return "WS_GET_POLL";
            }
        });
    }

    public void e() {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                o v3 = new f().a(arg3.a(), l.class).k();
                String v3_1 = v3.b("data") != null ? v3.b("data").toString() : null;
                c.c(this.a).onResult(new g().a(new GsonAdapterFactory()).b().a(v3_1, Statistics.class), 22);
            }

            public void a(VolleyError arg3) {
                org.telegram.customization.g.a v3 = new org.telegram.customization.g.a();
                v3.a(-1);
                v3.a("");
                c.c(this.a).onResult(v3, -22);
            }

            public Request b() {
                return new org.telegram.customization.g.b(0, "v12/ad/Statistics", this.a, this.a, c.d(this.a), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 1;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "set-1051";
            }

            public String toString() {
                return "WS_GET_HOME";
            }
        });
    }

    public void e(String arg2) {
        this.a(new a(arg2) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                if(arg3.b() != 500) {
                    Object v3 = arg3.a();
                    if(v3 != null) {
                        c.c(this.b).onResult(v3, 35);
                    }
                }
                else {
                    c.c(this.b).onResult(null, -35);
                }
            }

            public void a(VolleyError arg3) {
                c.c(this.b).onResult(null, -35);
            }

            public Request b() {
                return new org.telegram.customization.g.b(1, String.format("v12/payment/gpd?paymentId=%s", this.a), this.b, this.b, c.d(this.b), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "text/plain";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return HostRequestData.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_POST_T_M";
            }
        });
    }

    public void f() {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                int v1;
                d v0;
                Object v3 = arg3.a();
                if(v3 != null) {
                    v0 = c.c(this.a);
                    v1 = 27;
                }
                else {
                    v0 = c.c(this.a);
                    v1 = -27;
                }

                v0.onResult(v3, v1);
            }

            public void a(VolleyError arg3) {
                c.c(this.a).onResult(null, -27);
            }

            public Request b() {
                if(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser() != null) {
                    UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
                }

                return new org.telegram.customization.g.b(1, "v12/payment/rsd", this.a, this.a, c.d(this.a), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "text/plain";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return HostRequestData.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_REGISTER_AP_SDK";
            }
        });
    }

    public void f(String arg2) {
        this.a(new a(arg2) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                Object v3 = arg3.a();
                if(v3 != null) {
                    c.c(this.b).onResult(v3, 31);
                }
            }

            public void a(VolleyError arg3) {
                c.c(this.b).onResult(null, -31);
            }

            public Request b() {
                return new org.telegram.customization.g.b(1, String.format("v12/payment/rp?paymentId=%s", this.a), this.b, this.b, c.d(this.b), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "text/plain";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return HostRequestData.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_POST_T_M";
            }
        });
    }

    public void g() {
        this.a(new a() {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg3) {
                int v1;
                d v0;
                Object v3 = arg3.a();
                if(v3 != null) {
                    v0 = c.c(this.a);
                    v1 = 29;
                }
                else {
                    v0 = c.c(this.a);
                    v1 = -29;
                }

                v0.onResult(v3, v1);
            }

            public void a(VolleyError arg3) {
                c.c(this.a).onResult(null, -29);
            }

            public Request b() {
                return new org.telegram.customization.g.b(1, "v12/payment/crs", this.a, this.a, c.d(this.a), this.e()) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public String getBodyContentType() {
                        return "text/plain";
                    }

                    public Map getHeaders() {
                        return org.telegram.customization.g.b.a(this.a);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return User.class;
            }

            public String e() {
                return "set-1062";
            }

            public String toString() {
                return "WS_POST_T_M";
            }
        });
    }

    public void g(String arg2) {
        this.a(new a(arg2) {
            public RetryPolicy a() {
                return null;
            }

            public void a(org.telegram.customization.g.a arg2) {
                Log.d("alireza", "alireza call yrl success");
            }

            public void a(VolleyError arg3) {
                utils.a.b.c(System.currentTimeMillis());
                Log.d("alireza", "alireza call yrl error");
            }

            public Request b() {
                return new StringRequest(0, this.a, this.b, this.b) {
                    public byte[] getBody() {
                        return super.getBody();
                    }

                    public Map getHeaders() {
                        HashMap v0 = new HashMap();
                        List v1 = Arrays.asList(Constants.a);
                        Collections.shuffle(v1);
                        ((Map)v0).put("User-agent", v1.get(0));
                        return ((Map)v0);
                    }

                    protected Map getParams() {
                        return super.getParams();
                    }
                };
            }

            public boolean c() {
                return 0;
            }

            public Type d() {
                return null;
            }

            public String e() {
                return "KEY_CALL_URL";
            }
        });
    }

    public static String h() {
        return new Object() {
            int a;

            public String toString() {
                byte[] v0 = new byte[16];
                this.a = -1146426114;
                v0[0] = ((byte)(this.a >>> 23));
                this.a = -1797346490;
                v0[1] = ((byte)(this.a >>> 14));
                this.a = 1620530314;
                v0[2] = ((byte)(this.a >>> 17));
                this.a = 280132393;
                v0[3] = ((byte)(this.a >>> 17));
                this.a = 911222007;
                v0[4] = ((byte)(this.a >>> 8));
                this.a = -718635942;
                v0[5] = ((byte)(this.a >>> 15));
                this.a = -2027781029;
                v0[6] = ((byte)(this.a >>> 11));
                this.a = 1075422348;
                v0[7] = ((byte)(this.a >>> 5));
                this.a = -1506462008;
                v0[8] = ((byte)(this.a >>> 15));
                this.a = 1231375103;
                v0[9] = ((byte)(this.a >>> 19));
                this.a = -644791617;
                v0[10] = ((byte)(this.a >>> 22));
                this.a = -1412508669;
                v0[11] = ((byte)(this.a >>> 4));
                this.a = -1213432602;
                v0[12] = ((byte)(this.a >>> 20));
                this.a = 1963683952;
                v0[13] = ((byte)(this.a >>> 1));
                this.a = -145400071;
                v0[14] = ((byte)(this.a >>> 10));
                this.a = 1875206718;
                v0[15] = ((byte)(this.a >>> 13));
                return new String(v0);
            }
        }.toString();
    }

    public static byte[] h(String arg4) {
        byte[] v4_1;
        try {
            v4_1 = new org.telegram.customization.util.a(c.h(), c.i(), c.j()).a(arg4);
        }
        catch(Exception v4) {
            v4.printStackTrace();
            v4_1 = null;
        }

        return Base64.encode(LZ4Factory.fastestInstance().fastCompressor().compress(v4_1), 2);
    }

    public static String i() {
        return new Object() {
            int a;

            public String toString() {
                byte[] v1 = new byte[50];
                this.a = 1748961344;
                v1[0] = ((byte)(this.a >>> 16));
                this.a = 733408092;
                v1[1] = ((byte)(this.a >>> 3));
                this.a = 1915698508;
                v1[2] = ((byte)(this.a >>> 2));
                this.a = -610650695;
                v1[3] = ((byte)(this.a >>> 8));
                this.a = -1833414587;
                v1[4] = ((byte)(this.a >>> 9));
                this.a = 710755243;
                v1[5] = ((byte)(this.a >>> 10));
                this.a = -1966242239;
                v1[6] = ((byte)(this.a >>> 19));
                this.a = 2013847863;
                v1[7] = ((byte)(this.a >>> 9));
                this.a = -1297991548;
                v1[8] = ((byte)(this.a >>> 2));
                this.a = -1629668290;
                v1[9] = ((byte)(this.a >>> 17));
                this.a = 1395312367;
                v1[10] = ((byte)(this.a >>> 4));
                this.a = 383630200;
                v1[11] = ((byte)(this.a >>> 22));
                this.a = 458468302;
                v1[12] = ((byte)(this.a >>> 11));
                this.a = 1769850473;
                v1[13] = ((byte)(this.a >>> 4));
                this.a = 1209178569;
                v1[14] = ((byte)(this.a >>> 7));
                this.a = 1701124779;
                v1[15] = ((byte)(this.a >>> 3));
                this.a = -1624828247;
                v1[16] = ((byte)(this.a >>> 7));
                this.a = 581284651;
                v1[17] = ((byte)(this.a >>> 15));
                this.a = -2140686275;
                v1[18] = ((byte)(this.a >>> 7));
                this.a = 1291097231;
                v1[19] = ((byte)(this.a >>> 21));
                this.a = 1504938734;
                v1[20] = ((byte)(this.a >>> 12));
                this.a = -1251227850;
                v1[21] = ((byte)(this.a >>> 18));
                this.a = 1733967305;
                v1[22] = ((byte)(this.a >>> 2));
                this.a = 366406377;
                v1[23] = ((byte)(this.a >>> 14));
                this.a = 483939836;
                v1[24] = ((byte)(this.a >>> 14));
                this.a = -389836815;
                v1[25] = ((byte)(this.a >>> 21));
                this.a = -1208243749;
                v1[26] = ((byte)(this.a >>> 7));
                this.a = 2098557969;
                v1[27] = ((byte)(this.a >>> 14));
                this.a = 1450222276;
                v1[28] = ((byte)(this.a >>> 7));
                this.a = 662963414;
                v1[29] = ((byte)(this.a >>> 5));
                this.a = -1275901816;
                v1[30] = ((byte)(this.a >>> 8));
                this.a = 1632367724;
                v1[31] = ((byte)(this.a >>> 19));
                this.a = 1715292235;
                v1[32] = ((byte)(this.a >>> 10));
                this.a = -1523848812;
                v1[33] = ((byte)(this.a >>> 13));
                this.a = -1471394602;
                v1[34] = ((byte)(this.a >>> 17));
                this.a = 1956469843;
                v1[35] = ((byte)(this.a >>> 18));
                this.a = 1669480763;
                v1[36] = ((byte)(this.a >>> 3));
                this.a = -321882675;
                v1[37] = ((byte)(this.a >>> 21));
                this.a = 509591513;
                v1[38] = ((byte)(this.a >>> 22));
                this.a = 404820427;
                v1[39] = ((byte)(this.a >>> 3));
                this.a = 1155351698;
                v1[40] = ((byte)(this.a >>> 8));
                this.a = 1397758681;
                v1[41] = ((byte)(this.a >>> 6));
                this.a = 1407876858;
                v1[42] = ((byte)(this.a >>> 9));
                this.a = -80507156;
                v1[43] = ((byte)(this.a >>> 16));
                this.a = 882858469;
                v1[44] = ((byte)(this.a >>> 2));
                this.a = 839159394;
                v1[45] = ((byte)(this.a >>> 12));
                this.a = 308957427;
                v1[46] = ((byte)(this.a >>> 17));
                this.a = -1657011054;
                v1[47] = ((byte)(this.a >>> 22));
                this.a = -1646008178;
                v1[48] = ((byte)(this.a >>> 12));
                this.a = -1511721590;
                v1[49] = ((byte)(this.a >>> 2));
                return new String(v1);
            }
        }.toString();
    }

    public static String i(String arg4) {
        try {
            arg4 = new org.telegram.customization.util.a(new Object() {
                int a;

                public String toString() {
                    byte[] v0 = new byte[16];
                    this.a = -921327696;
                    v0[0] = ((byte)(this.a >>> 21));
                    this.a = -1454805131;
                    v0[1] = ((byte)(this.a >>> 18));
                    this.a = -997177012;
                    v0[2] = ((byte)(this.a >>> 18));
                    this.a = 848718914;
                    v0[3] = ((byte)(this.a >>> 24));
                    this.a = -1312795763;
                    v0[4] = ((byte)(this.a >>> 18));
                    this.a = 1492750920;
                    v0[5] = ((byte)(this.a >>> 3));
                    this.a = 445447860;
                    v0[6] = ((byte)(this.a >>> 22));
                    this.a = -1022029244;
                    v0[7] = ((byte)(this.a >>> 3));
                    this.a = 1135421775;
                    v0[8] = ((byte)(this.a >>> 7));
                    this.a = -650566676;
                    v0[9] = ((byte)(this.a >>> 11));
                    this.a = -687928167;
                    v0[10] = ((byte)(this.a >>> 6));
                    this.a = -2106923253;
                    v0[11] = ((byte)(this.a >>> 4));
                    this.a = 136663648;
                    v0[12] = ((byte)(this.a >>> 6));
                    this.a = 1588933977;
                    v0[13] = ((byte)(this.a >>> 7));
                    this.a = 462980392;
                    v0[14] = ((byte)(this.a >>> 15));
                    this.a = 365316230;
                    v0[15] = ((byte)(this.a >>> 13));
                    return new String(v0);
                }
            }.toString(), new Object() {
                int a;

                public String toString() {
                    byte[] v0 = new byte[29];
                    this.a = -1323374672;
                    v0[0] = ((byte)(this.a >>> 9));
                    this.a = -1697530697;
                    v0[1] = ((byte)(this.a >>> 17));
                    this.a = -553214828;
                    v0[2] = ((byte)(this.a >>> 12));
                    this.a = -1183682831;
                    v0[3] = ((byte)(this.a >>> 16));
                    this.a = 622355557;
                    v0[4] = ((byte)(this.a >>> 14));
                    this.a = -1869800696;
                    v0[5] = ((byte)(this.a >>> 6));
                    this.a = 115773841;
                    v0[6] = ((byte)(this.a >>> 12));
                    this.a = 601159015;
                    v0[7] = ((byte)(this.a >>> 9));
                    this.a = -1502212024;
                    v0[8] = ((byte)(this.a >>> 12));
                    this.a = -1316186378;
                    v0[9] = ((byte)(this.a >>> 13));
                    this.a = -1523525308;
                    v0[10] = ((byte)(this.a >>> 6));
                    this.a = -852937073;
                    v0[11] = ((byte)(this.a >>> 4));
                    this.a = -790793511;
                    v0[12] = ((byte)(this.a >>> 4));
                    this.a = 1133015572;
                    v0[13] = ((byte)(this.a >>> 8));
                    this.a = 1637923373;
                    v0[14] = ((byte)(this.a >>> 24));
                    this.a = 1488246512;
                    v0[15] = ((byte)(this.a >>> 22));
                    this.a = 162058779;
                    v0[16] = ((byte)(this.a >>> 9));
                    this.a = -1701493558;
                    v0[17] = ((byte)(this.a >>> 1));
                    this.a = -950482349;
                    v0[18] = ((byte)(this.a >>> 9));
                    this.a = 615001492;
                    v0[19] = ((byte)(this.a >>> 8));
                    this.a = -1615639500;
                    v0[20] = ((byte)(this.a >>> 5));
                    this.a = 324922794;
                    v0[21] = ((byte)(this.a >>> 5));
                    this.a = -1497713243;
                    v0[22] = ((byte)(this.a >>> 2));
                    this.a = 132316049;
                    v0[23] = ((byte)(this.a >>> 3));
                    this.a = -349379853;
                    v0[24] = ((byte)(this.a >>> 1));
                    this.a = 1500385852;
                    v0[25] = ((byte)(this.a >>> 19));
                    this.a = 1255987002;
                    v0[26] = ((byte)(this.a >>> 14));
                    this.a = 727776337;
                    v0[27] = ((byte)(this.a >>> 19));
                    this.a = -1060285537;
                    v0[28] = ((byte)(this.a >>> 3));
                    return new String(v0);
                }
            }.toString(), new Object() {
                int a;

                public String toString() {
                    byte[] v0 = new byte[38];
                    this.a = 1846853717;
                    v0[0] = ((byte)(this.a >>> 24));
                    this.a = -457943302;
                    v0[1] = ((byte)(this.a >>> 4));
                    this.a = -1657788742;
                    v0[2] = ((byte)(this.a >>> 22));
                    this.a = -316881697;
                    v0[3] = ((byte)(this.a >>> 21));
                    this.a = -1389270379;
                    v0[4] = ((byte)(this.a >>> 21));
                    this.a = -1229048018;
                    v0[5] = ((byte)(this.a >>> 7));
                    this.a = -1393597039;
                    v0[6] = ((byte)(this.a >>> 21));
                    this.a = 25351900;
                    v0[7] = ((byte)(this.a >>> 12));
                    this.a = -670706977;
                    v0[8] = ((byte)(this.a >>> 10));
                    this.a = 534508768;
                    v0[9] = ((byte)(this.a >>> 14));
                    this.a = 1293329753;
                    v0[10] = ((byte)(this.a >>> 15));
                    this.a = -610414205;
                    v0[11] = ((byte)(this.a >>> 19));
                    this.a = -1377839407;
                    v0[12] = ((byte)(this.a >>> 1));
                    this.a = 935520358;
                    v0[13] = ((byte)(this.a >>> 23));
                    this.a = 1493569260;
                    v0[14] = ((byte)(this.a >>> 5));
                    this.a = 689348297;
                    v0[15] = ((byte)(this.a >>> 15));
                    this.a = 429671249;
                    v0[16] = ((byte)(this.a >>> 23));
                    this.a = -2073667319;
                    v0[17] = ((byte)(this.a >>> 13));
                    this.a = -529863353;
                    v0[18] = ((byte)(this.a >>> 16));
                    this.a = 1911096426;
                    v0[19] = ((byte)(this.a >>> 13));
                    this.a = -1332100585;
                    v0[20] = ((byte)(this.a >>> 11));
                    this.a = 1096227095;
                    v0[21] = ((byte)(this.a >>> 19));
                    this.a = 1757390365;
                    v0[22] = ((byte)(this.a >>> 4));
                    this.a = -9331610;
                    v0[23] = ((byte)(this.a >>> 6));
                    this.a = 74053918;
                    v0[24] = ((byte)(this.a >>> 21));
                    this.a = -190420129;
                    v0[25] = ((byte)(this.a >>> 13));
                    this.a = 1310801925;
                    v0[26] = ((byte)(this.a >>> 15));
                    this.a = -974174850;
                    v0[27] = ((byte)(this.a >>> 21));
                    this.a = -2082445906;
                    v0[28] = ((byte)(this.a >>> 9));
                    this.a = 1191342008;
                    v0[29] = ((byte)(this.a >>> 20));
                    this.a = -1187927464;
                    v0[30] = ((byte)(this.a >>> 16));
                    this.a = 737697723;
                    v0[31] = ((byte)(this.a >>> 21));
                    this.a = -188773899;
                    v0[32] = ((byte)(this.a >>> 20));
                    this.a = 870997882;
                    v0[33] = ((byte)(this.a >>> 24));
                    this.a = 686637404;
                    v0[34] = ((byte)(this.a >>> 2));
                    this.a = -1597208344;
                    v0[35] = ((byte)(this.a >>> 13));
                    this.a = -1818999423;
                    v0[36] = ((byte)(this.a >>> 19));
                    this.a = 1651707427;
                    v0[37] = ((byte)(this.a >>> 5));
                    return new String(v0);
                }
            }.toString()).b(arg4);
        }
        catch(Exception v4) {
            v4.printStackTrace();
            arg4 = null;
        }

        return arg4;
    }

    private byte[] j(String arg3) {
        byte[] v3_1;
        byte[] v0 = new byte[0];
        try {
            v3_1 = arg3.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException v3) {
            v3.printStackTrace();
            v3_1 = v0;
        }

        return v3_1;
    }

    public static String j() {
        return new Object() {
            int a;

            public String toString() {
                byte[] v1 = new byte[50];
                this.a = 145891398;
                v1[0] = ((byte)(this.a >>> 21));
                this.a = 319737629;
                v1[1] = ((byte)(this.a >>> 19));
                this.a = -851587979;
                v1[2] = ((byte)(this.a >>> 1));
                this.a = 1942148620;
                v1[3] = ((byte)(this.a >>> 24));
                this.a = -392868703;
                v1[4] = ((byte)(this.a >>> 18));
                this.a = 695341963;
                v1[5] = ((byte)(this.a >>> 11));
                this.a = -716409239;
                v1[6] = ((byte)(this.a >>> 22));
                this.a = -745655375;
                v1[7] = ((byte)(this.a >>> 3));
                this.a = 1572494708;
                v1[8] = ((byte)(this.a >>> 22));
                this.a = -856020485;
                v1[9] = ((byte)(this.a >>> 21));
                this.a = 1160389040;
                v1[10] = ((byte)(this.a >>> 13));
                this.a = -1138009353;
                v1[11] = ((byte)(this.a >>> 15));
                this.a = -1783476828;
                v1[12] = ((byte)(this.a >>> 15));
                this.a = 512218506;
                v1[13] = ((byte)(this.a >>> 2));
                this.a = 1124761521;
                v1[14] = ((byte)(this.a >>> 19));
                this.a = 1893819989;
                v1[15] = ((byte)(this.a >>> 1));
                this.a = 732259226;
                v1[16] = ((byte)(this.a >>> 23));
                this.a = 1832196843;
                v1[17] = ((byte)(this.a >>> 18));
                this.a = 313211973;
                v1[18] = ((byte)(this.a >>> 7));
                this.a = 457247168;
                v1[19] = ((byte)(this.a >>> 23));
                this.a = -1095852606;
                v1[20] = ((byte)(this.a >>> 17));
                this.a = -1805716531;
                v1[21] = ((byte)(this.a >>> 9));
                this.a = 1020315532;
                v1[22] = ((byte)(this.a >>> 24));
                this.a = -215381177;
                v1[23] = ((byte)(this.a >>> 19));
                this.a = 111191823;
                v1[24] = ((byte)(this.a >>> 10));
                this.a = -1820812176;
                v1[25] = ((byte)(this.a >>> 22));
                this.a = 746582634;
                v1[26] = ((byte)(this.a >>> 24));
                this.a = 513172472;
                v1[27] = ((byte)(this.a >>> 22));
                this.a = -176518283;
                v1[28] = ((byte)(this.a >>> 18));
                this.a = -42447201;
                v1[29] = ((byte)(this.a >>> 8));
                this.a = -1409422587;
                v1[30] = ((byte)(this.a >>> 21));
                this.a = -1199087777;
                v1[31] = ((byte)(this.a >>> 6));
                this.a = 512388206;
                v1[32] = ((byte)(this.a >>> 11));
                this.a = 9259597;
                v1[33] = ((byte)(this.a >>> 1));
                this.a = 1823149385;
                v1[34] = ((byte)(this.a >>> 21));
                this.a = -99305106;
                v1[35] = ((byte)(this.a >>> 10));
                this.a = 1592849782;
                v1[36] = ((byte)(this.a >>> 22));
                this.a = -1628304960;
                v1[37] = ((byte)(this.a >>> 5));
                this.a = -1507479708;
                v1[38] = ((byte)(this.a >>> 7));
                this.a = 686146935;
                v1[39] = ((byte)(this.a >>> 18));
                this.a = -850755381;
                v1[40] = ((byte)(this.a >>> 2));
                this.a = 1102112889;
                v1[41] = ((byte)(this.a >>> 9));
                this.a = 317194826;
                v1[42] = ((byte)(this.a >>> 22));
                this.a = 1852610100;
                v1[43] = ((byte)(this.a >>> 9));
                this.a = -1142678850;
                v1[44] = ((byte)(this.a >>> 7));
                this.a = -1589078898;
                v1[45] = ((byte)(this.a >>> 6));
                this.a = 1605332530;
                v1[46] = ((byte)(this.a >>> 22));
                this.a = 1794743168;
                v1[47] = ((byte)(this.a >>> 21));
                this.a = 1696409484;
                v1[48] = ((byte)(this.a >>> 4));
                this.a = -22606017;
                v1[49] = ((byte)(this.a >>> 12));
                return new String(v1);
            }
        }.toString();
    }

    private boolean k(String arg15) {
        DefaultRetryPolicy v2_1;
        Log.d("LEE", "retryByAnotherAddress");
        utils.a.b.f(this.h.e());
        ++this.k;
        String v8 = org.telegram.customization.g.f.a(this.k, this.e, this.g, this.h.e(), this.h.e());
        if(v8 == null) {
            return 0;
        }

        org.telegram.customization.g.c$11 v1 = new org.telegram.customization.g.b(this.h.b().getMethod(), v8, this, this, this.g, true, this.h.e()) {
            public byte[] getBody() {
                return c.b(this.b).b().getBody();
            }

            public String getBodyContentType() {
                return c.b(this.b).b().getBodyContentType();
            }

            public Map getHeaders() {
                try {
                    return c.b(this.b).b().getHeaders();
                }
                catch(Exception ) {
                    return super.getHeaders();
                }
            }

            protected Map getParams() {
                return super.getParams();
            }

            public String getPostBodyContentType() {
                return c.b(this.b).b().getPostBodyContentType();
            }
        };
        RetryPolicy v2 = this.h.a();
        if(v2 == null) {
            v2_1 = new DefaultRetryPolicy(org.telegram.customization.g.f.a(this.g), 0, 1f);
        }

        ((Request)v1).setRetryPolicy(((RetryPolicy)v2_1));
        ((Request)v1).setShouldCache(true);
        if((this.h.e().contentEquals("set-1062")) && !((Request)v1).getUrl().startsWith("https")) {
            return 1;
        }

        c.a().add(((Request)v1));
        return 1;
    }

    public void onErrorResponse(VolleyError arg4) {
        try {
            Log.d("LEE", "HandleRequest onErrorResponse" + arg4.networkResponse + "----" + arg4.networkResponse.statusCode);
            goto label_14;
        }
        catch(Exception ) {
            try {
            label_14:
                if(this.a != null) {
                    new Thread(arg4) {
                        public void run() {
                            super.run();
                            this.b.a(this.a);
                        }
                    }.start();
                }
                else {
                    this.a(arg4);
                }

                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    public void onResponse(Object arg1) {
        this.a(((String)arg1));
    }

    public void onResult(Object arg2, int arg3) {
        this.a(new Runnable(arg2, arg3) {
            public void run() {
                c.a(this.c).onResult(this.a, this.b);
            }
        });
    }
}

