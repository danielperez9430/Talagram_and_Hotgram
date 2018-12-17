package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@VisibleForTesting public final class ArrayUtils {
    final class zza {
        HashMap zzzb;

        zza(int arg2) {
            super();
            this.zzzb = new HashMap(arg2);
        }

        final void zzb(Object arg2) {
            com.google.android.gms.common.util.zza v2 = this.zzd(arg2);
            ++v2.count;
        }

        final void zzc(Object arg2) {
            com.google.android.gms.common.util.zza v2 = this.zzd(arg2);
            --v2.count;
        }

        private final com.google.android.gms.common.util.zza zzd(Object arg3) {
            Object v0 = this.zzzb.get(arg3);
            if(v0 == null) {
                com.google.android.gms.common.util.zza v0_1 = new com.google.android.gms.common.util.zza();
                this.zzzb.put(arg3, v0_1);
            }

            return ((com.google.android.gms.common.util.zza)v0);
        }
    }

    private ArrayUtils() {
        super();
    }

    public static int[] appendToArray(int[] arg2, int arg3) {
        arg2 = arg2 == null || arg2.length == 0 ? new int[1] : Arrays.copyOf(arg2, arg2.length + 1);
        arg2[arg2.length - 1] = arg3;
        return arg2;
    }

    public static Object[] appendToArray(Object[] arg2, Object arg3) {
        Object v2;
        if(arg2 == null) {
            if(arg3 != null) {
            }
            else {
                throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
            }
        }

        if(arg2 == null) {
            v2 = Array.newInstance(arg3.getClass(), 1);
        }
        else {
            arg2 = Arrays.copyOf(arg2, arg2.length + 1);
        }

        v2[v2.length - 1] = arg3;
        return ((Object[])v2);
    }

    public static Object[] concat(Object[][] arg6) {
        if(arg6.length == 0) {
            return Array.newInstance(arg6.getClass(), 0);
        }

        int v0 = 0;
        int v2 = 0;
        while(v0 < arg6.length) {
            v2 += arg6[v0].length;
            ++v0;
        }

        Object[] v0_1 = Arrays.copyOf(arg6[0], v2);
        v2 = arg6[0].length;
        int v3;
        for(v3 = 1; v3 < arg6.length; ++v3) {
            Object[] v4 = arg6[v3];
            System.arraycopy(v4, 0, v0_1, v2, v4.length);
            v2 += v4.length;
        }

        return v0_1;
    }

    public static byte[] concatByteArrays(byte[][] arg6) {
        if(arg6.length == 0) {
            return new byte[0];
        }

        int v0 = 0;
        int v2 = 0;
        while(v0 < arg6.length) {
            v2 += arg6[v0].length;
            ++v0;
        }

        byte[] v0_1 = Arrays.copyOf(arg6[0], v2);
        v2 = arg6[0].length;
        int v3;
        for(v3 = 1; v3 < arg6.length; ++v3) {
            byte[] v4 = arg6[v3];
            System.arraycopy(v4, 0, v0_1, v2, v4.length);
            v2 += v4.length;
        }

        return v0_1;
    }

    public static boolean contains(Object[] arg0, Object arg1) {
        if(ArrayUtils.indexOf(arg0, arg1) >= 0) {
            return 1;
        }

        return 0;
    }

    public static boolean contains(byte[] arg4, byte arg5) {
        if(arg4 == null) {
            return 0;
        }

        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(char[] arg4, char arg5) {
        if(arg4 == null) {
            return 0;
        }

        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(double[] arg6, double arg7) {
        if(arg6 == null) {
            return 0;
        }

        int v1 = arg6.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg6[v2] == arg7) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(float[] arg5, float arg6, float arg7) {
        if(arg5 == null) {
            return 0;
        }

        float v1 = arg6 - arg7;
        arg6 += arg7;
        int v7 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v7; ++v2) {
            float v3 = arg5[v2];
            if(v1 <= v3 && v3 <= arg6) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(int[] arg4, int arg5) {
        if(arg4 == null) {
            return 0;
        }

        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(short[] arg4, short arg5) {
        if(arg4 == null) {
            return 0;
        }

        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(boolean[] arg4, boolean arg5) {
        if(arg4 == null) {
            return 0;
        }

        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean containsIgnoreCase(String[] arg5, String arg6) {
        if(arg5 == null) {
            return 0;
        }

        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v3 = arg5[v2];
            if(v3 == arg6) {
                return 1;
            }

            if(v3 != null && (v3.equalsIgnoreCase(arg6))) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean equalsAnyOrder(Object[] arg6, Object[] arg7) {
        if(arg6 == arg7) {
            return 1;
        }

        int v2 = arg6 == null ? 0 : arg6.length;
        int v3 = arg7 == null ? 0 : arg7.length;
        if(v2 == 0 && v3 == 0) {
            return 1;
        }

        if(v2 != v3) {
            return 0;
        }

        zza v3_1 = new zza(v2);
        v2 = arg6.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            v3_1.zzb(arg6[v4]);
        }

        int v6 = arg7.length;
        for(v2 = 0; v2 < v6; ++v2) {
            v3_1.zzc(arg7[v2]);
        }

        Iterator v6_1 = v3_1.zzzb.values().iterator();
        do {
            if(!v6_1.hasNext()) {
                return 1;
            }
        }
        while(v6_1.next().count == 0);

        return 0;
    }

    public static int indexOf(Object[] arg3, Object arg4) {
        int v0 = 0;
        int v1 = arg3 != null ? arg3.length : 0;
        while(v0 < v1) {
            if(Objects.equal(arg3[v0], arg4)) {
                return v0;
            }

            ++v0;
        }

        return -1;
    }

    public static ArrayList newArrayList() {
        return new ArrayList();
    }

    public static int rearrange(Object[] arg5, Predicate arg6) {
        int v0 = 0;
        if(arg5 != null) {
            if(arg5.length == 0) {
            }
            else {
                int v1 = arg5.length;
                int v2 = 0;
                while(v0 < v1) {
                    if(arg6.apply(arg5[v0])) {
                        if(v2 != v0) {
                            Object v3 = arg5[v2];
                            arg5[v2] = arg5[v0];
                            arg5[v0] = v3;
                        }

                        ++v2;
                    }

                    ++v0;
                }

                return v2;
            }
        }

        return 0;
    }

    public static int[] removeAll(int[] arg7, int[] arg8) {
        int v4;
        int v3;
        int v1;
        if(arg7 == null) {
            return null;
        }

        if(arg8 != null) {
            if(arg8.length == 0) {
            }
            else {
                int[] v0 = new int[arg7.length];
                int v2 = 0;
                if(arg8.length == 1) {
                    v1 = arg7.length;
                    v3 = 0;
                    v4 = 0;
                    while(v3 < v1) {
                        int v5 = arg7[v3];
                        if(arg8[0] != v5) {
                            v0[v4] = v5;
                            ++v4;
                        }

                        ++v3;
                    }
                }
                else {
                    v1 = arg7.length;
                    v4 = 0;
                    while(v2 < v1) {
                        v3 = arg7[v2];
                        if(!ArrayUtils.contains(arg8, v3)) {
                            v0[v4] = v3;
                            ++v4;
                        }

                        ++v2;
                    }
                }

                return ArrayUtils.resize(v0, v4);
            }
        }

        return Arrays.copyOf(arg7, arg7.length);
    }

    public static Object[] removeAll(Object[] arg7, Object[] arg8) {
        int v4;
        int v1;
        if(arg7 == null) {
            return null;
        }

        if(arg8 != null) {
            if(arg8.length == 0) {
            }
            else {
                Object v0 = Array.newInstance(arg8.getClass().getComponentType(), arg7.length);
                int v2 = 0;
                if(arg8.length == 1) {
                    v1 = arg7.length;
                    int v3 = 0;
                    v4 = 0;
                    while(v3 < v1) {
                        Object v5 = arg7[v3];
                        if(!Objects.equal(arg8[0], v5)) {
                            v0[v4] = v5;
                            ++v4;
                        }

                        ++v3;
                    }
                }
                else {
                    v1 = arg7.length;
                    v4 = 0;
                    while(v2 < v1) {
                        Object v3_1 = arg7[v2];
                        if(!ArrayUtils.contains(arg8, v3_1)) {
                            v0[v4] = v3_1;
                            ++v4;
                        }

                        ++v2;
                    }
                }

                return ArrayUtils.resize(((Object[])v0), v4);
            }
        }

        return Arrays.copyOf(arg7, arg7.length);
    }

    public static int[] resize(int[] arg1, int arg2) {
        if(arg1 == null) {
            return null;
        }

        if(arg2 != arg1.length) {
            arg1 = Arrays.copyOf(arg1, arg2);
        }

        return arg1;
    }

    public static Object[] resize(Object[] arg1, int arg2) {
        if(arg1 == null) {
            return null;
        }

        if(arg2 != arg1.length) {
            arg1 = Arrays.copyOf(arg1, arg2);
        }

        return arg1;
    }

    public static ArrayList toArrayList(Collection arg1) {
        if(arg1 == null) {
            return null;
        }

        return new ArrayList(arg1);
    }

    public static ArrayList toArrayList(Object[] arg4) {
        int v0 = arg4.length;
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(arg4[v2]);
        }

        return v1;
    }

    public static long[] toLongArray(Collection arg6) {
        int v0 = 0;
        if(arg6 != null) {
            if(arg6.size() == 0) {
            }
            else {
                long[] v1 = new long[arg6.size()];
                Iterator v6 = arg6.iterator();
                while(v6.hasNext()) {
                    v1[v0] = v6.next().longValue();
                    ++v0;
                }

                return v1;
            }
        }

        return new long[0];
    }

    public static long[] toLongArray(Long[] arg4) {
        int v0 = 0;
        if(arg4 == null) {
            return new long[0];
        }

        long[] v1 = new long[arg4.length];
        while(v0 < arg4.length) {
            v1[v0] = arg4[v0].longValue();
            ++v0;
        }

        return v1;
    }

    public static int[] toPrimitiveArray(Collection arg4) {
        int v0 = 0;
        if(arg4 != null) {
            if(arg4.size() == 0) {
            }
            else {
                int[] v1 = new int[arg4.size()];
                Iterator v4 = arg4.iterator();
                while(v4.hasNext()) {
                    v1[v0] = v4.next().intValue();
                    ++v0;
                }

                return v1;
            }
        }

        return new int[0];
    }

    public static int[] toPrimitiveArray(Integer[] arg3) {
        int v0 = 0;
        if(arg3 == null) {
            return new int[0];
        }

        int[] v1 = new int[arg3.length];
        while(v0 < arg3.length) {
            v1[v0] = arg3[v0].intValue();
            ++v0;
        }

        return v1;
    }

    public static String[] toStringArray(Collection arg1) {
        if(arg1 != null) {
            if(arg1.size() == 0) {
            }
            else {
                return arg1.toArray(new String[arg1.size()]);
            }
        }

        return new String[0];
    }

    public static Boolean[] toWrapperArray(boolean[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        Boolean[] v1 = new Boolean[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Boolean.valueOf(arg4[v2]);
        }

        return v1;
    }

    public static Byte[] toWrapperArray(byte[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        Byte[] v1 = new Byte[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Byte.valueOf(arg4[v2]);
        }

        return v1;
    }

    public static Character[] toWrapperArray(char[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        Character[] v1 = new Character[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Character.valueOf(arg4[v2]);
        }

        return v1;
    }

    public static Double[] toWrapperArray(double[] arg5) {
        if(arg5 == null) {
            return null;
        }

        int v0 = arg5.length;
        Double[] v1 = new Double[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Double.valueOf(arg5[v2]);
        }

        return v1;
    }

    public static Float[] toWrapperArray(float[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        Float[] v1 = new Float[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Float.valueOf(arg4[v2]);
        }

        return v1;
    }

    public static Integer[] toWrapperArray(int[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        Integer[] v1 = new Integer[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Integer.valueOf(arg4[v2]);
        }

        return v1;
    }

    public static Long[] toWrapperArray(long[] arg5) {
        if(arg5 == null) {
            return null;
        }

        int v0 = arg5.length;
        Long[] v1 = new Long[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Long.valueOf(arg5[v2]);
        }

        return v1;
    }

    public static Short[] toWrapperArray(short[] arg4) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        Short[] v1 = new Short[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = Short.valueOf(arg4[v2]);
        }

        return v1;
    }

    public static void writeArray(StringBuilder arg4, double[] arg5) {
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg4.append(",");
            }

            arg4.append(Double.toString(arg5[v1]));
        }
    }

    public static void writeArray(StringBuilder arg3, float[] arg4) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg3.append(",");
            }

            arg3.append(Float.toString(arg4[v1]));
        }
    }

    public static void writeArray(StringBuilder arg3, int[] arg4) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg3.append(",");
            }

            arg3.append(Integer.toString(arg4[v1]));
        }
    }

    public static void writeArray(StringBuilder arg4, long[] arg5) {
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg4.append(",");
            }

            arg4.append(Long.toString(arg5[v1]));
        }
    }

    public static void writeArray(StringBuilder arg3, Object[] arg4) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg3.append(",");
            }

            arg3.append(arg4[v1].toString());
        }
    }

    public static void writeArray(StringBuilder arg3, boolean[] arg4) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg3.append(",");
            }

            arg3.append(Boolean.toString(arg4[v1]));
        }
    }

    public static void writeStringArray(StringBuilder arg3, String[] arg4) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(v1 != 0) {
                arg3.append(",");
            }

            arg3.append("\"");
            arg3.append(arg4[v1]);
            arg3.append("\"");
        }
    }
}

