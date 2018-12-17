package org.telegram.messenger.support;

import java.lang.reflect.Array;

public class ArrayUtils {
    private static final int CACHE_SIZE = 73;
    private static Object[] EMPTY;
    private static Object[] sCache;

    static {
        ArrayUtils.EMPTY = new Object[0];
        ArrayUtils.sCache = new Object[73];
    }

    private ArrayUtils() {
        super();
    }

    public static Object[] appendElement(Class arg3, Object[] arg4, Object arg5) {
        Object v3;
        int v0 = 0;
        if(arg4 != null) {
            int v1 = arg4.length;
            v3 = Array.newInstance(arg3, v1 + 1);
            System.arraycopy(arg4, 0, v3, 0, v1);
            v0 = v1;
        }
        else {
            v3 = Array.newInstance(arg3, 1);
        }

        v3[v0] = arg5;
        return ((Object[])v3);
    }

    public static int[] appendInt(int[] arg4, int arg5) {
        if(arg4 == null) {
            return new int[]{arg5};
        }

        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4[v2] == arg5) {
                return arg4;
            }
        }

        int[] v2_1 = new int[v1 + 1];
        System.arraycopy(arg4, 0, v2_1, 0, v1);
        v2_1[v1] = arg5;
        return v2_1;
    }

    public static boolean contains(int[] arg4, int arg5) {
        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean contains(Object[] arg5, Object arg6) {
        int v0 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg5[v2];
            if(v3 == null) {
                if(arg6 == null) {
                    return 1;
                }
            }
            else if(arg6 != null && (v3.equals(arg6))) {
                return 1;
            }
        }

        return 0;
    }

    public static Object[] emptyArray(Class arg3) {
        if(arg3 == Object.class) {
            return ArrayUtils.EMPTY;
        }

        int v0 = (System.identityHashCode(arg3) / 8 & 2147483647) % 73;
        Object v1 = ArrayUtils.sCache[v0];
        if(v1 == null || v1.getClass().getComponentType() != arg3) {
            v1 = Array.newInstance(arg3, 0);
            ArrayUtils.sCache[v0] = v1;
        }

        return ((Object[])v1);
    }

    public static boolean equals(byte[] arg5, byte[] arg6, int arg7) {
        if(arg5 == arg6) {
            return 1;
        }

        if(arg5 != null && arg6 != null && arg5.length >= arg7) {
            if(arg6.length < arg7) {
            }
            else {
                int v2 = 0;
                while(true) {
                    if(v2 >= arg7) {
                        return 1;
                    }
                    else if(arg5[v2] != arg6[v2]) {
                        return 0;
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public static int idealBooleanArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0);
    }

    public static int idealByteArraySize(int arg2) {
        int v0;
        for(v0 = 4; v0 < 32; ++v0) {
            int v1 = (1 << v0) - 12;
            if(arg2 <= v1) {
                return v1;
            }
        }

        return arg2;
    }

    public static int idealCharArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0 * 2) / 2;
    }

    public static int idealFloatArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0 * 4) / 4;
    }

    public static int idealIntArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0 * 4) / 4;
    }

    public static int idealLongArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0 * 8) / 8;
    }

    public static int idealObjectArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0 * 4) / 4;
    }

    public static int idealShortArraySize(int arg0) {
        return ArrayUtils.idealByteArraySize(arg0 * 2) / 2;
    }

    public static Object[] removeElement(Class arg4, Object[] arg5, Object arg6) {
        if(arg5 != null) {
            int v0 = arg5.length;
            int v2 = 0;
            while(v2 < v0) {
                if(arg5[v2] != arg6) {
                    ++v2;
                    continue;
                }
                else if(v0 == 1) {
                    return null;
                }
                else {
                    Object v4 = Array.newInstance(arg4, v0 - 1);
                    System.arraycopy(arg5, 0, v4, 0, v2);
                    System.arraycopy(arg5, v2 + 1, v4, v2, v0 - v2 - 1);
                    return ((Object[])v4);
                }

                return arg5;
            }
        }

        return arg5;
    }

    public static int[] removeInt(int[] arg4, int arg5) {
        if(arg4 == null) {
            return null;
        }

        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg4[v2] == arg5) {
                arg5 = v0 - 1;
                int[] v3 = new int[arg5];
                if(v2 > 0) {
                    System.arraycopy(arg4, 0, v3, 0, v2);
                }

                if(v2 < arg5) {
                    System.arraycopy(arg4, v2 + 1, v3, v2, v0 - v2 - 1);
                }

                return v3;
            }
        }

        return arg4;
    }

    public static long total(long[] arg6) {
        int v0 = arg6.length;
        long v1 = 0;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v1 += arg6[v3];
        }

        return v1;
    }
}

