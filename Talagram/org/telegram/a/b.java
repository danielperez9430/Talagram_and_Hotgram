package org.telegram.a;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

public class b {
    public byte[] a;
    public ByteBuffer b;
    public String c;
    public String d;
    public HashMap e;
    public HashMap f;
    public HashMap g;
    public HashMap h;
    private boolean i;
    private static volatile b j;

    static {
    }

    public b() {
        super();
        this.i = false;
        this.c(null);
    }

    public static b a() {
        b v0 = b.j;
        if(v0 == null) {
            Class v1 = b.class;
            __monitor_enter(v1);
            try {
                v0 = b.j;
                if(v0 == null) {
                    v0 = new b();
                    b.j = v0;
                }

                __monitor_exit(v1);
                return v0;
            label_12:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_12;
            }

            throw v0_1;
        }

        return v0;
    }

    public static String a(String arg2, boolean arg3) {
        if(arg2 == null) {
            return null;
        }

        StringBuilder v0 = new StringBuilder(arg2);
        arg2 = "0123456789";
        if(arg3) {
            arg2 = arg2 + "+";
        }

        int v3_1;
        for(v3_1 = v0.length() - 1; v3_1 >= 0; --v3_1) {
            if(!arg2.contains(v0.substring(v3_1, v3_1 + 1))) {
                v0.deleteCharAt(v3_1);
            }
        }

        return v0.toString();
    }

    public static String a(String arg3) {
        StringBuilder v0 = new StringBuilder(arg3);
        arg3 = "0123456789+*#";
        int v1;
        for(v1 = v0.length() - 1; v1 >= 0; --v1) {
            if(!arg3.contains(v0.substring(v1, v1 + 1))) {
                v0.deleteCharAt(v1);
            }
        }

        return v0.toString();
    }

    int a(int arg3) {
        if(arg3 + 4 <= this.a.length) {
            this.b.position(arg3);
            return this.b.getInt();
        }

        return 0;
    }

    public static String b(String arg1) {
        return b.a(arg1, false);
    }

    short b(int arg3) {
        if(arg3 + 2 <= this.a.length) {
            this.b.position(arg3);
            return this.b.getShort();
        }

        return 0;
    }

    public void b() {
        int v0 = 0;
        int v1 = this.a(0);
        int v3 = 4;
        int v2 = v1 * 12 + v3;
        int v4 = 4;
        while(v0 < v1) {
            String v5 = this.c(v4);
            v4 += v3;
            String v6 = this.c(v4);
            v4 += v3;
            int v7 = this.a(v4) + v2;
            v4 += v3;
            if(v6.equals(this.c)) {
                this.d = v5;
            }

            this.h.put(v6, v5);
            this.e.put(v5, Integer.valueOf(v7));
            Object v7_1 = this.f.get(v5);
            if(v7_1 == null) {
                ArrayList v7_2 = new ArrayList();
                this.f.put(v5, v7_2);
            }

            ((ArrayList)v7_1).add(v6);
            ++v0;
        }

        if(this.d != null) {
            this.f(this.d);
        }
    }

    public void c(String arg8) {
        int v0_1;
        ByteArrayOutputStream v2;
        InputStream v1;
        ByteArrayOutputStream v0 = null;
        try {
            v1 = ApplicationLoader.applicationContext.getAssets().open("PhoneFormats.dat");
        }
        catch(Throwable v8) {
            v1 = ((InputStream)v0);
            v2 = ((ByteArrayOutputStream)v1);
            goto label_85;
        }
        catch(Exception v8_1) {
            v1 = ((InputStream)v0);
            goto label_71;
        }

        try {
            v2 = new ByteArrayOutputStream();
            v0_1 = 1024;
            goto label_8;
        }
        catch(Throwable v8) {
        }
        catch(Exception v8_1) {
            goto label_71;
            try {
            label_8:
                byte[] v3 = new byte[v0_1];
                while(true) {
                    int v5 = v1.read(v3, 0, v0_1);
                    if(v5 == -1) {
                        break;
                    }

                    v2.write(v3, 0, v5);
                }

                this.a = v2.toByteArray();
                this.b = ByteBuffer.wrap(this.a);
                this.b.order(ByteOrder.LITTLE_ENDIAN);
            }
            catch(Throwable v8) {
                goto label_85;
            }
            catch(Exception v8_1) {
                goto label_61;
            }

            try {
                v2.close();
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }

            if(v1 != null) {
                try {
                    v1.close();
                }
                catch(Exception v0_2) {
                    FileLog.e(((Throwable)v0_2));
                }
            }

            if(arg8 == null || arg8.length() == 0) {
                arg8 = Locale.getDefault().getCountry().toLowerCase();
            }
            else {
            }

            this.c = arg8;
            this.e = new HashMap(255);
            this.f = new HashMap(255);
            this.g = new HashMap(10);
            this.h = new HashMap(255);
            this.b();
            this.i = true;
            return;
        label_61:
            v0 = v2;
            try {
            label_71:
                v8_1.printStackTrace();
                if((((ByteArrayOutputStream)v0_1)) == null) {
                    goto label_77;
                }
            }
            catch(Throwable v8) {
                v2 = ((ByteArrayOutputStream)v0_1);
                goto label_85;
            }
        }

        try {
            ((ByteArrayOutputStream)v0_1).close();
        }
        catch(Exception v8_1) {
            FileLog.e(((Throwable)v8_1));
        }

    label_77:
        if(v1 != null) {
            try {
                v1.close();
            }
            catch(Exception v8_1) {
                FileLog.e(((Throwable)v8_1));
            }
        }

        return;
    label_85:
        if(v2 != null) {
            try {
                v2.close();
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }
        }

        if(v1 != null) {
            try {
                v1.close();
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }
        }

        throw v8;
    }

    public String c(int arg4) {
        int v0 = arg4;
        try {
            while(v0 < this.a.length) {
                if(this.a[v0] == 0) {
                    v0 -= arg4;
                    if(arg4 == v0) {
                        return "";
                    }

                    return new String(this.a, arg4, v0);
                }

                ++v0;
            }

            return "";
        }
        catch(Exception v4) {
            v4.printStackTrace();
            return "";
        }
    }

    public a d(String arg5) {
        a v2 = null;
        int v1 = 0;
        do {
            if(v1 >= 3) {
                return v2;
            }

            if(v1 >= arg5.length()) {
                return v2;
            }

            ++v1;
            v2 = this.f(arg5.substring(0, v1));
        }
        while(v2 == null);

        return v2;
    }

    public String e(String arg7) {
        a v1;
        if(!this.i) {
            return arg7;
        }

        try {
            String v0_1 = b.a(arg7);
            if(v0_1.startsWith("+")) {
                v0_1 = v0_1.substring(1);
                v1 = this.d(v0_1);
                if(v1 != null) {
                    v0_1 = v1.c(v0_1);
                    return "+" + v0_1;
                }

                return arg7;
            }

            v1 = this.f(this.d);
            if(v1 == null) {
                return arg7;
            }

            String v3 = v1.a(v0_1);
            if(v3 != null) {
                v0_1 = v0_1.substring(v3.length());
                v1 = this.d(v0_1);
                if(v1 != null) {
                    v0_1 = v1.c(v0_1);
                }

                if(v0_1.length() == 0) {
                    return v3;
                }

                return String.format("%s %s", v3, v0_1);
            }

            return v1.c(v0_1);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
            return arg7;
        }
    }

    public a f(String arg21) {
        a v2_2;
        boolean v1_2;
        int v19;
        int v18;
        byte[] v17;
        b v0 = this;
        String v1 = arg21;
        Object v2 = v0.g.get(v1);
        if(v2 == null) {
            Object v3 = v0.e.get(v1);
            if(v3 != null) {
                byte[] v2_1 = v0.a;
                int v3_1 = ((Integer)v3).intValue();
                a v4 = new a();
                v4.b = v1;
                v4.a = v0.f.get(v1);
                v0.g.put(v1, v4);
                int v1_1 = v0.b(v3_1);
                int v6 = 2;
                int v5 = v3_1 + 2 + v6;
                int v7 = v0.b(v5);
                v5 = v5 + v6 + v6;
                int v8 = v0.b(v5);
                v5 = v5 + v6 + v6;
                int v10 = 5;
                ArrayList v9 = new ArrayList(v10);
                while(true) {
                    String v11 = v0.c(v5);
                    if(v11.length() == 0) {
                        break;
                    }

                    v9.add(v11);
                    v5 += v11.length() + 1;
                }

                v4.c = v9;
                ++v5;
                v9 = new ArrayList(v10);
                while(true) {
                    String v10_1 = v0.c(v5);
                    if(v10_1.length() == 0) {
                        break;
                    }

                    v9.add(v10_1);
                    v5 += v10_1.length() + 1;
                }

                v4.d = v9;
                ArrayList v5_1 = new ArrayList(v8);
                v3_1 += v1_1;
                v10 = v3_1;
                int v9_1 = 0;
                while(v9_1 < v8) {
                    d v11_1 = new d();
                    v11_1.a = v0.b(v10);
                    v10 += v6;
                    int v12 = v0.b(v10);
                    ArrayList v14 = new ArrayList(v12);
                    int v15 = v10 + v6;
                    v10 = 0;
                    while(v10 < v12) {
                        c v13 = new c();
                        v13.a = v0.a(v15);
                        v15 += 4;
                        v13.b = v0.a(v15);
                        v15 += 4;
                        v1_1 = v15 + 1;
                        v13.c = v2_1[v15];
                        v15 = v1_1 + 1;
                        v13.d = v2_1[v1_1];
                        v1_1 = v15 + 1;
                        v13.e = v2_1[v15];
                        v15 = v1_1 + 1;
                        v13.f = v2_1[v1_1];
                        v1_1 = v15 + 1;
                        v13.g = v2_1[v15];
                        v15 = v1_1 + 1;
                        v13.h = v2_1[v1_1];
                        v1_1 = v0.b(v15);
                        v15 += v6;
                        v13.i = v0.c(v3_1 + v7 + v1_1);
                        v1_1 = v13.i.indexOf("[[");
                        if(v1_1 != -1) {
                            int v0_1 = v13.i.indexOf("]]");
                            v17 = v2_1;
                            v18 = v3_1;
                            Object[] v3_2 = new Object[2];
                            v19 = v7;
                            v3_2[0] = v13.i.substring(0, v1_1);
                            v1_2 = true;
                            v3_2[1] = v13.i.substring(v0_1 + 2);
                            v13.i = String.format("%s%s", v3_2);
                        }
                        else {
                            v17 = v2_1;
                            v18 = v3_1;
                            v19 = v7;
                            v1_2 = true;
                        }

                        v14.add(v13);
                        if(v13.j) {
                            v11_1.c = v1_2;
                        }

                        if(v13.k) {
                            v11_1.d = v1_2;
                        }

                        ++v10;
                        v2_1 = v17;
                        v3_1 = v18;
                        v7 = v19;
                        v0 = this;
                        v6 = 2;
                    }

                    v11_1.b = v14;
                    v5_1.add(v11_1);
                    ++v9_1;
                    v10 = v15;
                    v2_1 = v2_1;
                    v7 = v7;
                    v0 = this;
                    v6 = 2;
                }

                v4.e = v5_1;
                v2_2 = v4;
            }
        }

        return v2_2;
    }
}

