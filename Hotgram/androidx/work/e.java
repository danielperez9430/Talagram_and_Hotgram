package androidx.work;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public final class e {
    public final class a {
        private Map a;

        public a() {
            super();
            this.a = new HashMap();
        }

        public e a() {
            return new e(this.a);
        }

        public a a(e arg1) {
            this.a(arg1.b);
            return this;
        }

        public a a(Map arg3) {
            Iterator v3 = arg3.entrySet().iterator();
            while(v3.hasNext()) {
                Object v0 = v3.next();
                this.a(((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
            }

            return this;
        }

        public a a(String arg4, Object arg5) {
            Long[] v5_2;
            Map v0_1;
            if(arg5 == null) {
                this.a.put(arg4, null);
            }
            else {
                Class v0 = arg5.getClass();
                if(v0 == Boolean.class || v0 == Integer.class || v0 == Long.class || v0 == Float.class || v0 == Double.class || v0 == String.class || v0 == Boolean[].class || v0 == Integer[].class || v0 == Long[].class || v0 == Float[].class || v0 == Double[].class || v0 == String[].class) {
                    v0_1 = this.a;
                }
                else if(v0 == boolean[].class) {
                    v0_1 = this.a;
                    Boolean[] v5 = e.a(((boolean[])arg5));
                }
                else if(v0 == int[].class) {
                    v0_1 = this.a;
                    Integer[] v5_1 = e.a(((int[])arg5));
                }
                else if(v0 == long[].class) {
                    v0_1 = this.a;
                    v5_2 = e.a(((long[])arg5));
                }
                else if(v0 == float[].class) {
                    v0_1 = this.a;
                    Float[] v5_3 = e.a(((float[])arg5));
                }
                else if(v0 == double[].class) {
                    v0_1 = this.a;
                    Double[] v5_4 = e.a(((double[])arg5));
                }
                else {
                    throw new IllegalArgumentException(String.format("Key %s has invalid type %s", arg4, v0));
                }

                v0_1.put(arg4, v5_2);
            }

            return this;
        }

        public a a(String arg2, String arg3) {
            this.a.put(arg2, arg3);
            return this;
        }

        public a a(String arg2, boolean arg3) {
            this.a.put(arg2, Boolean.valueOf(arg3));
            return this;
        }
    }

    public static final e a;
    Map b;

    static {
        e.a = new a().a();
    }

    e() {
        super();
    }

    public e(e arg2) {
        super();
        this.b = new HashMap(arg2.b);
    }

    e(Map arg2) {
        super();
        this.b = new HashMap(arg2);
    }

    public static e a(byte[] arg6) {
        ClassNotFoundException v6_1;
        Throwable v6_2;
        ObjectInputStream v2_1;
        if(arg6.length > 10240) {
            goto label_57;
        }

        HashMap v0 = new HashMap();
        ByteArrayInputStream v1 = new ByteArrayInputStream(arg6);
        ObjectInputStream v6 = null;
        try {
            v2_1 = new ObjectInputStream(((InputStream)v1));
        }
        catch(Throwable v0_1) {
            v2_1 = v6;
            v6_2 = v0_1;
            goto label_47;
        }
        catch(ClassNotFoundException v2) {
            ClassNotFoundException v5 = v2;
            v2_1 = v6;
            v6_1 = v5;
            goto label_33;
        }

        try {
            int v6_3;
            for(v6_3 = v2_1.readInt(); v6_3 > 0; --v6_3) {
                ((Map)v0).put(v2_1.readUTF(), v2_1.readObject());
            }
        }
        catch(ClassNotFoundException v6_1) {
            goto label_24;
        }
        catch(Throwable v6_2) {
            goto label_47;
        }

        try {
            v2_1.close();
        }
        catch(IOException v6_4) {
            v6_4.printStackTrace();
        }

        try {
            v1.close();
            goto label_43;
        }
        catch(IOException v6_4) {
            goto label_42;
        }

    label_24:
        try {
        label_33:
            ((Exception)v6_1).printStackTrace();
            if(v2_1 == null) {
                goto label_39;
            }
        }
        catch(Throwable v6_2) {
            goto label_47;
        }

        try {
            v2_1.close();
        }
        catch(IOException v6_4) {
            v6_4.printStackTrace();
        }

        try {
        label_39:
            v1.close();
        }
        catch(IOException v6_4) {
        label_42:
            v6_4.printStackTrace();
        }

    label_43:
        return new e(((Map)v0));
    label_47:
        if(v2_1 == null) {
            goto label_52;
        }

        try {
            v2_1.close();
        }
        catch(IOException v0_2) {
            v0_2.printStackTrace();
        }

        try {
        label_52:
            v1.close();
        }
        catch(IOException v0_2) {
            v0_2.printStackTrace();
        }

        throw v6_2;
    label_57:
        throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
    }

    public static byte[] a(e arg4) {
        ObjectOutputStream v2;
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        ObjectOutputStream v1 = null;
        try {
            v2 = new ObjectOutputStream(((OutputStream)v0));
            goto label_5;
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            goto label_33;
            try {
            label_5:
                v2.writeInt(arg4.b());
                Iterator v4_2 = arg4.b.entrySet().iterator();
                while(v4_2.hasNext()) {
                    Object v1_1 = v4_2.next();
                    v2.writeUTF(((Map$Entry)v1_1).getKey());
                    v2.writeObject(((Map$Entry)v1_1).getValue());
                }
            }
            catch(Throwable v4) {
                goto label_52;
            }
            catch(IOException v4_1) {
                goto label_27;
            }

            try {
                v2.close();
            }
            catch(IOException v4_1) {
                v4_1.printStackTrace();
            }

            try {
                v0.close();
                goto label_43;
            }
            catch(IOException v4_1) {
                goto label_42;
            }

        label_27:
            v1 = v2;
            try {
            label_33:
                v4_1.printStackTrace();
                if(v1 == null) {
                    goto label_39;
                }
            }
            catch(Throwable v4) {
                v2 = v1;
                goto label_52;
            }
        }

        try {
            v1.close();
        }
        catch(IOException v4_1) {
            v4_1.printStackTrace();
        }

        try {
        label_39:
            v0.close();
        }
        catch(IOException v4_1) {
        label_42:
            v4_1.printStackTrace();
        }

    label_43:
        if(v0.size() <= 10240) {
            return v0.toByteArray();
        }

        throw new IllegalStateException("Data cannot occupy more than 10240 bytes when serialized");
    label_52:
        if(v2 == null) {
            goto label_57;
        }

        try {
            v2.close();
        }
        catch(IOException v1_2) {
            v1_2.printStackTrace();
        }

        try {
        label_57:
            v0.close();
        }
        catch(IOException v0_1) {
            v0_1.printStackTrace();
        }

        throw v4;
    }

    static Boolean[] a(boolean[] arg3) {
        Boolean[] v0 = new Boolean[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = Boolean.valueOf(arg3[v1]);
        }

        return v0;
    }

    static Double[] a(double[] arg4) {
        Double[] v0 = new Double[arg4.length];
        int v1;
        for(v1 = 0; v1 < arg4.length; ++v1) {
            v0[v1] = Double.valueOf(arg4[v1]);
        }

        return v0;
    }

    static Float[] a(float[] arg3) {
        Float[] v0 = new Float[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = Float.valueOf(arg3[v1]);
        }

        return v0;
    }

    static Integer[] a(int[] arg3) {
        Integer[] v0 = new Integer[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = Integer.valueOf(arg3[v1]);
        }

        return v0;
    }

    static Long[] a(long[] arg4) {
        Long[] v0 = new Long[arg4.length];
        int v1;
        for(v1 = 0; v1 < arg4.length; ++v1) {
            v0[v1] = Long.valueOf(arg4[v1]);
        }

        return v0;
    }

    public String a(String arg2) {
        Object v2 = this.b.get(arg2);
        if((v2 instanceof String)) {
            return ((String)v2);
        }

        return null;
    }

    public Map a() {
        return Collections.unmodifiableMap(this.b);
    }

    public boolean a(String arg2, boolean arg3) {
        Object v2 = this.b.get(arg2);
        if((v2 instanceof Boolean)) {
            return ((Boolean)v2).booleanValue();
        }

        return arg3;
    }

    public int b() {
        return this.b.size();
    }

    public boolean equals(Object arg3) {
        if(this == (((e)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return this.b.equals(((e)arg3).b);
            }
        }

        return 0;
    }

    public int hashCode() {
        return this.b.hashCode() * 31;
    }
}

