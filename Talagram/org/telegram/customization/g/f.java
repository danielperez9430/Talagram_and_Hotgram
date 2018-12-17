package org.telegram.customization.g;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import utils.a.b;

public class f {
    class a {
        public String a;
        public String b;

        public a(String arg1, String arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public a() {
            super();
            this.a = "";
            this.b = "";
        }
    }

    public static final ArrayList a;

    static {
        f.a = new ArrayList();
        f.a.add(new a("creator", "publisher"));
        f.a.add(new a("downloadCount", "dl_count"));
        f.a.add(new a("imageLinkOnServer", "image_link"));
    }

    public static int a(Context arg0) {
        return b.k(arg0);
    }

    public static String a(int arg2, String arg3, Context arg4, String arg5, String arg6) {
        arg3 = arg3.replace("http://", "").replace("https://", "");
        arg3 = arg3.replace(arg3.substring(0, arg3.indexOf("/") + 1), "");
        String v0 = null;
        if(arg5.contentEquals("set-1067")) {
            if(arg2 > b.B(arg4, arg6).size()) {
                return v0;
            }
        }
        else if(arg2 > 5) {
            return v0;
        }

        return arg3;
    }

    public static String b(Context arg1) {
        String v1;
        try {
            v1 = arg1.getSystemService("phone").getNetworkOperatorName();
            if(TextUtils.isEmpty(((CharSequence)v1))) {
                return "";
            }
        }
        catch(Exception ) {
            return "";
        }

        return v1;
    }
}

