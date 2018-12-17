package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import java.io.IOException;
import java.util.Map;

public interface ez extends fc {
    class com.google.ads.interactivemedia.v3.internal.ez$1 implements fq {
        com.google.ads.interactivemedia.v3.internal.ez$1() {
            super();
        }

        public boolean a(Object arg1) {
            return this.a(((String)arg1));
        }

        public boolean a(String arg2) {
            boolean v2;
            arg2 = ft.b(arg2);
            if(!TextUtils.isEmpty(((CharSequence)arg2))) {
                if((arg2.contains("text")) && !arg2.contains("text/vtt")) {
                    goto label_17;
                }

                if(arg2.contains("html")) {
                    goto label_17;
                }

                if(arg2.contains("xml")) {
                    goto label_17;
                }

                v2 = true;
            }
            else {
            label_17:
                v2 = false;
            }

            return v2;
        }
    }

    public class a extends IOException {
        public final int a;
        public final eu b;

        public a(IOException arg1, eu arg2, int arg3) {
            super(((Throwable)arg1));
            this.b = arg2;
            this.a = arg3;
        }

        public a(String arg1, eu arg2, int arg3) {
            super(arg1);
            this.b = arg2;
            this.a = arg3;
        }

        public a(String arg1, IOException arg2, eu arg3, int arg4) {
            super(arg1, ((Throwable)arg2));
            this.b = arg3;
            this.a = arg4;
        }
    }

    public final class b extends a {
        public final String c;

        public b(String arg4, eu arg5) {
            String v0 = "Invalid content type: ";
            String v1 = String.valueOf(arg4);
            v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
            super(v0, arg5, 1);
            this.c = arg4;
        }
    }

    public final class c extends a {
        public final int c;
        public final Map d;

        public c(int arg3, Map arg4, eu arg5) {
            StringBuilder v0 = new StringBuilder(26);
            v0.append("Response code: ");
            v0.append(arg3);
            super(v0.toString(), arg5, 1);
            this.c = arg3;
            this.d = arg4;
        }
    }

    public static final fq a;

    static {
        ez.a = new com.google.ads.interactivemedia.v3.internal.ez$1();
    }
}

