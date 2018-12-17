package okhttp3.internal.publicsuffix;

import e.e;
import e.j;
import e.l;
import java.io.Closeable;
import java.io.InputStream;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.c;

public final class PublicSuffixDatabase {
    private static final byte[] a;
    private static final String[] b;
    private static final String[] c;
    private static final PublicSuffixDatabase d;
    private final AtomicBoolean e;
    private final CountDownLatch f;
    private byte[] g;
    private byte[] h;

    static {
        PublicSuffixDatabase.a = new byte[]{42};
        PublicSuffixDatabase.b = new String[0];
        PublicSuffixDatabase.c = new String[]{"*"};
        PublicSuffixDatabase.d = new PublicSuffixDatabase();
    }

    public PublicSuffixDatabase() {
        super();
        this.e = new AtomicBoolean(false);
        this.f = new CountDownLatch(1);
    }

    public String a(String arg7) {
        int v1_1;
        int v0_1;
        if(arg7 != null) {
            String[] v0 = IDN.toUnicode(arg7).split("\\.");
            String[] v1 = this.a(v0);
            int v4 = 33;
            if(v0.length == v1.length && v1[0].charAt(0) != v4) {
                return null;
            }

            if(v1[0].charAt(0) == v4) {
                v0_1 = v0.length;
                v1_1 = v1.length;
            }
            else {
                v0_1 = v0.length;
                v1_1 = v1.length + 1;
            }

            v0_1 -= v1_1;
            StringBuilder v1_2 = new StringBuilder();
            String[] v7 = arg7.split("\\.");
            while(v0_1 < v7.length) {
                v1_2.append(v7[v0_1]);
                v1_2.append('.');
                ++v0_1;
            }

            v1_2.deleteCharAt(v1_2.length() - 1);
            return v1_2.toString();
        }

        throw new NullPointerException("domain == null");
    }

    public static PublicSuffixDatabase a() {
        return PublicSuffixDatabase.d;
    }

    private static String a(byte[] arg16, byte[][] arg17, int arg18) {
        int v8;
        int v14;
        int v10;
        int v6;
        int v5;
        byte[] v0 = arg16;
        byte[][] v1 = arg17;
        int v4 = v0.length;
        int v2 = 0;
        while(true) {
        label_6:
            if(v2 < v4) {
                v5 = (v2 + v4) / 2;
                while(true) {
                    v6 = 10;
                    if(v5 > -1 && v0[v5] != v6) {
                        --v5;
                        continue;
                    }

                    break;
                }

                ++v5;
                int v9;
                for(v9 = 1; true; ++v9) {
                    v10 = v5 + v9;
                    if(v0[v10] == v6) {
                        break;
                    }
                }

                v6 = v10 - v5;
                int v11 = arg18;
                v9 = 0;
                int v12 = 0;
                int v13 = 0;
                while(true) {
                    if(v9 != 0) {
                        v9 = 0;
                        v14 = 46;
                    }
                    else {
                        v14 = v1[v11][v12] & 255;
                    }

                    v14 -= v0[v5 + v13] & 255;
                    if(v14 != 0) {
                    }
                    else {
                        ++v13;
                        ++v12;
                        if(v13 == v6) {
                        }
                        else {
                            if(v1[v11].length != v12) {
                                continue;
                            }

                            if(v11 == v1.length - 1) {
                                goto label_53;
                            }

                            goto label_80;
                        }
                    }

                label_53:
                    if(v14 >= 0) {
                        if(v14 <= 0) {
                            int v7 = v6 - v13;
                            v8 = v1[v11].length - v12;
                            break;
                        label_80:
                            ++v11;
                            v9 = 1;
                            v12 = -1;
                            continue;
                        }

                        goto label_58;
                    }

                    goto label_54;
                }

                while(true) {
                    ++v11;
                    if(v11 >= v1.length) {
                        break;
                    }

                    v8 += v1[v11].length;
                }

                if(v8 < v7) {
                    break;
                }
                else if(v8 > v7) {
                }
                else {
                    String v1_1 = new String(v0, v5, v6, c.e);
                    return v1_1;
                }

            label_58:
                v2 = v10 + 1;
                continue;
            }
            else {
                return null;
            }
        }

    label_54:
        v4 = v5 - 1;
        goto label_6;
        return null;
    }

    private String[] a(String[] arg8) {
        // Method was not decompiled
    }

    private void b() {
        // Method was not decompiled
    }

    private void c() {
        byte[] v2;
        byte[] v1_1;
        InputStream v0 = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
        if(v0 == null) {
            return;
        }

        e v0_1 = l.a(new j(l.a(v0)));
        try {
            v1_1 = new byte[v0_1.k()];
            v0_1.a(v1_1);
            v2 = new byte[v0_1.k()];
            v0_1.a(v2);
        }
        catch(Throwable v1) {
            c.a(((Closeable)v0_1));
            throw v1;
        }

        c.a(((Closeable)v0_1));
        __monitor_enter(this);
        try {
            this.g = v1_1;
            this.h = v2;
            __monitor_exit(this);
        }
        catch(Throwable v0_2) {
            try {
            label_24:
                __monitor_exit(this);
            }
            catch(Throwable v0_2) {
                goto label_24;
            }

            throw v0_2;
        }

        this.f.countDown();
    }
}

