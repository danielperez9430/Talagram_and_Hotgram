package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;

public class kb {
    private String a;
    private String b;
    private String c;
    private String[] d;
    private static final String[] e;
    private jx f;

    static {
        kb.e = new String[]{"/aclk", "/pcs/click"};
    }

    public kb(jx arg4) {
        super();
        this.a = "googleads.g.doubleclick.net";
        this.b = "/pagead/ads";
        this.c = "ad.doubleclick.net";
        this.d = new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
        this.f = arg4;
    }

    private Uri a(Uri arg4, Context arg5, String arg6, boolean arg7) {
        try {
            boolean v0 = this.a(arg4);
            if(!v0) {
                if(arg4.getQueryParameter("ms") == null) {
                    goto label_14;
                }

                goto label_27;
            }
            else if(!arg4.toString().contains("dc_ms=")) {
            }
            else {
                throw new kc("Parameter already exists: dc_ms");
            }

        label_14:
            String v5 = arg7 ? this.f.a(arg5, arg6) : this.f.a(arg5);
            if(v0) {
                return this.b(arg4, "dc_ms", v5);
            }

            return this.a(arg4, "ms", v5);
        label_27:
            throw new kc("Query parameter already exists: ms");
        }
        catch(UnsupportedOperationException ) {
            throw new kc("Provided Uri is not in a valid state");
        }
    }

    public boolean a(Uri arg2) {
        if(arg2 != null) {
            try {
                return arg2.getHost().equals(this.c);
            }
            catch(NullPointerException ) {
                return 0;
            }
        }

        throw new NullPointerException();
    }

    private Uri a(Uri arg4, String arg5, String arg6) {
        String v0 = arg4.toString();
        int v1 = v0.indexOf("&adurl");
        int v2 = -1;
        if(v1 == v2) {
            v1 = v0.indexOf("?adurl");
        }

        if(v1 != v2) {
            ++v1;
            StringBuilder v4 = new StringBuilder(v0.substring(0, v1));
            v4.append(arg5);
            v4.append("=");
            v4.append(arg6);
            v4.append("&");
            v4.append(v0.substring(v1));
            return Uri.parse(v4.toString());
        }

        return arg4.buildUpon().appendQueryParameter(arg5, arg6).build();
    }

    public Uri a(Uri arg3, Context arg4) {
        return this.a(arg3, arg4, null, false);
    }

    public jx a() {
        return this.f;
    }

    private Uri b(Uri arg6, String arg7, String arg8) {
        String v6_1;
        String v0 = arg6.toString();
        int v1 = v0.indexOf(";adurl");
        if(v1 != -1) {
            ++v1;
            StringBuilder v6 = new StringBuilder(v0.substring(0, v1));
            v6.append(arg7);
            v6.append("=");
            v6.append(arg8);
            v6.append(";");
            v6.append(v0.substring(v1));
            v6_1 = v6.toString();
        }
        else {
            v6_1 = arg6.getEncodedPath();
            v1 = v0.indexOf(v6_1);
            StringBuilder v3 = new StringBuilder(v0.substring(0, v6_1.length() + v1));
            v3.append(";");
            v3.append(arg7);
            v3.append("=");
            v3.append(arg8);
            v3.append(";");
            v3.append(v0.substring(v1 + v6_1.length()));
            v6_1 = v3.toString();
        }

        return Uri.parse(v6_1);
    }

    public boolean b(Uri arg6) {
        int v3;
        if(arg6 == null) {
            goto label_15;
        }

        try {
            String v6 = arg6.getHost();
            String[] v1 = this.d;
            int v2 = v1.length;
            v3 = 0;
            while(true) {
            label_6:
                if(v3 >= v2) {
                    return 0;
                }

                if(!v6.endsWith(v1[v3])) {
                    goto label_12;
                }

                return 1;
            }
        }
        catch(NullPointerException ) {
            return 0;
        }

        return 1;
    label_12:
        ++v3;
        goto label_6;
        return 0;
    label_15:
        throw new NullPointerException();
    }
}

