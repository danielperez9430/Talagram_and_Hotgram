package android.arch.b.a;

public final class a implements e {
    private final String a;
    private final Object[] b;

    public a(String arg1, Object[] arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public a(String arg2) {
        this(arg2, null);
    }

    private static void a(d arg2, int arg3, Object arg4) {
        int v4;
        long v0_1;
        double v0;
        if(arg4 == null) {
            arg2.a(arg3);
        }
        else if((arg4 instanceof byte[])) {
            arg2.a(arg3, ((byte[])arg4));
        }
        else {
            if((arg4 instanceof Float)) {
                v0 = ((double)((Float)arg4).floatValue());
            }
            else if((arg4 instanceof Double)) {
                v0 = ((Double)arg4).doubleValue();
            }
            else {
                goto label_17;
            }

            arg2.a(arg3, v0);
            return;
        label_17:
            if((arg4 instanceof Long)) {
                v0_1 = ((Long)arg4).longValue();
            }
            else {
                if((arg4 instanceof Integer)) {
                    v4 = ((Integer)arg4).intValue();
                }
                else if((arg4 instanceof Short)) {
                    v4 = ((Short)arg4).shortValue();
                }
                else if((arg4 instanceof Byte)) {
                    v4 = ((Byte)arg4).byteValue();
                }
                else {
                    goto label_35;
                }

                v0_1 = ((long)v4);
                goto label_20;
            label_35:
                if((arg4 instanceof String)) {
                    arg2.a(arg3, ((String)arg4));
                    return;
                }

                if(!(arg4 instanceof Boolean)) {
                    goto label_48;
                }

                if(((Boolean)arg4).booleanValue()) {
                    v0_1 = 1;
                    goto label_20;
                }

                v0_1 = 0;
            }

        label_20:
            arg2.a(arg3, v0_1);
        }

        return;
    label_48:
        StringBuilder v0_2 = new StringBuilder();
        v0_2.append("Cannot bind ");
        v0_2.append(arg4);
        v0_2.append(" at index ");
        v0_2.append(arg3);
        v0_2.append(" Supported types: null, byte[], float, double, long, int, short, byte,");
        v0_2.append(" string");
        throw new IllegalArgumentException(v0_2.toString());
    }

    public static void a(d arg3, Object[] arg4) {
        if(arg4 == null) {
            return;
        }

        int v0 = arg4.length;
        int v1 = 0;
        while(v1 < v0) {
            Object v2 = arg4[v1];
            ++v1;
            a.a(arg3, v1, v2);
        }
    }

    public String a() {
        return this.a;
    }

    public void a(d arg2) {
        a.a(arg2, this.b);
    }
}

