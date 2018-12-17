package com.persianswitch.a;

public enum ac {
    public static final enum ac a;
    public static final enum ac b;
    public static final enum ac c;
    public static final enum ac d;
    final String e;

    static {
        ac.a = new ac("TLS_1_2", 0, "TLSv1.2");
        ac.b = new ac("TLS_1_1", 1, "TLSv1.1");
        ac.c = new ac("TLS_1_0", 2, "TLSv1");
        ac.d = new ac("SSL_3_0", 3, "SSLv3");
        ac.f = new ac[]{ac.a, ac.b, ac.c, ac.d};
    }

    private ac(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.e = arg3;
    }

    public static ac a(String arg3) {
        int v0;
        switch(arg3.hashCode()) {
            case -503070503: {
                if(!arg3.equals("TLSv1.1")) {
                    goto label_23;
                }

                v0 = 1;
                break;
            }
            case -503070502: {
                if(arg3.equals("TLSv1.2")) {
                    v0 = 0;
                    goto label_24;
                }

            label_23:
                v0 = -1;
                break;
            }
            case 79201641: {
                if(!arg3.equals("SSLv3")) {
                    goto label_23;
                }

                v0 = 3;
                break;
            }
            case 79923350: {
                if(!arg3.equals("TLSv1")) {
                    goto label_23;
                }

                v0 = 2;
                break;
            }
            default: {
                goto label_23;
            }
        }

    label_24:
        switch(v0) {
            case 0: {
                goto label_40;
            }
            case 1: {
                goto label_38;
            }
            case 2: {
                goto label_36;
            }
            case 3: {
                goto label_34;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected TLS version: ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    label_34:
        return ac.d;
    label_36:
        return ac.c;
    label_38:
        return ac.b;
    label_40:
        return ac.a;
    }

    public static ac valueOf(String arg1) {
        return Enum.valueOf(ac.class, arg1);
    }

    public static ac[] values() {
        // Method was not decompiled
    }
}

