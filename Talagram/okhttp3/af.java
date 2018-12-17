package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum af {
    public static final enum af a;
    public static final enum af b;
    public static final enum af c;
    public static final enum af d;
    public static final enum af e;
    final String f;

    static {
        af.a = new af("TLS_1_3", 0, "TLSv1.3");
        af.b = new af("TLS_1_2", 1, "TLSv1.2");
        af.c = new af("TLS_1_1", 2, "TLSv1.1");
        af.d = new af("TLS_1_0", 3, "TLSv1");
        af.e = new af("SSL_3_0", 4, "SSLv3");
        af.g = new af[]{af.a, af.b, af.c, af.d, af.e};
    }

    private af(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.f = arg3;
    }

    static List a(String[] arg4) {
        ArrayList v0 = new ArrayList(arg4.length);
        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ((List)v0).add(af.a(arg4[v2]));
        }

        return Collections.unmodifiableList(((List)v0));
    }

    public static af a(String arg3) {
        int v0 = arg3.hashCode();
        if(v0 != 79201641) {
            if(v0 != 79923350) {
                switch(v0) {
                    case -503070503: {
                        goto label_17;
                    }
                    case -503070502: {
                        goto label_12;
                    }
                    case -503070501: {
                        goto label_7;
                    }
                }

                goto label_32;
            label_17:
                if(arg3.equals("TLSv1.1")) {
                    v0 = 2;
                    goto label_33;
                label_7:
                    if(arg3.equals("TLSv1.3")) {
                        v0 = 0;
                        goto label_33;
                    label_12:
                        if(arg3.equals("TLSv1.2")) {
                            v0 = 1;
                        }
                        else {
                            goto label_32;
                        }
                    }
                    else {
                        goto label_32;
                    }
                }
                else {
                    goto label_32;
                }
            }
            else if(arg3.equals("TLSv1")) {
                v0 = 3;
            }
            else {
                goto label_32;
            }
        }
        else if(arg3.equals("SSLv3")) {
            v0 = 4;
        }
        else {
        label_32:
            v0 = -1;
        }

    label_33:
        switch(v0) {
            case 0: {
                goto label_51;
            }
            case 1: {
                goto label_49;
            }
            case 2: {
                goto label_47;
            }
            case 3: {
                goto label_45;
            }
            case 4: {
                goto label_43;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected TLS version: ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    label_49:
        return af.b;
    label_51:
        return af.a;
    label_43:
        return af.e;
    label_45:
        return af.d;
    label_47:
        return af.c;
    }

    public static af valueOf(String arg1) {
        return Enum.valueOf(af.class, arg1);
    }

    public static af[] values() {
        // Method was not decompiled
    }
}

