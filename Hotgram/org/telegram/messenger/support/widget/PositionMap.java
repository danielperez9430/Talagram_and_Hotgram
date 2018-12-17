package org.telegram.messenger.support.widget;

import java.util.ArrayList;

class PositionMap implements Cloneable {
    class ContainerHelpers {
        static final boolean[] EMPTY_BOOLEANS;
        static final int[] EMPTY_INTS;
        static final long[] EMPTY_LONGS;
        static final Object[] EMPTY_OBJECTS;

        static {
            ContainerHelpers.EMPTY_BOOLEANS = new boolean[0];
            ContainerHelpers.EMPTY_INTS = new int[0];
            ContainerHelpers.EMPTY_LONGS = new long[0];
            ContainerHelpers.EMPTY_OBJECTS = new Object[0];
        }

        ContainerHelpers() {
            super();
        }

        static int binarySearch(int[] arg3, int arg4, int arg5) {
            int v1;
            --arg4;
            int v0 = 0;
            while(true) {
                if(v0 > arg4) {
                    goto label_15;
                }

                v1 = v0 + arg4 >>> 1;
                int v2 = arg3[v1];
                if(v2 < arg5) {
                    v0 = v1 + 1;
                    continue;
                }

                if(v2 <= arg5) {
                    return v1;
                }

                arg4 = v1 - 1;
            }

            return v1;
        label_15:
            return v0 ^ -1;
        }
    }

    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;

    static {
        PositionMap.DELETED = new Object();
    }

    PositionMap() {
        this(10);
    }

    PositionMap(int arg3) {
        Object[] v3;
        super();
        this.mGarbage = false;
        if(arg3 == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            v3 = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            arg3 = PositionMap.idealIntArraySize(arg3);
            this.mKeys = new int[arg3];
            v3 = new Object[arg3];
        }

        this.mValues = v3;
        this.mSize = 0;
    }

    public void append(int arg7, Object arg8) {
        if(this.mSize != 0 && arg7 <= this.mKeys[this.mSize - 1]) {
            this.put(arg7, arg8);
            return;
        }

        if((this.mGarbage) && this.mSize >= this.mKeys.length) {
            this.gc();
        }

        int v0 = this.mSize;
        if(v0 >= this.mKeys.length) {
            int v1 = PositionMap.idealIntArraySize(v0 + 1);
            int[] v2 = new int[v1];
            Object[] v1_1 = new Object[v1];
            System.arraycopy(this.mKeys, 0, v2, 0, this.mKeys.length);
            System.arraycopy(this.mValues, 0, v1_1, 0, this.mValues.length);
            this.mKeys = v2;
            this.mValues = v1_1;
        }

        this.mKeys[v0] = arg7;
        this.mValues[v0] = arg8;
        this.mSize = v0 + 1;
    }

    public void clear() {
        int v0 = this.mSize;
        Object[] v1 = this.mValues;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            v1[v3] = null;
        }

        this.mSize = 0;
        this.mGarbage = false;
    }

    public Object clone() {
        return this.clone();
    }

    public PositionMap clone() {
        // Method was not decompiled
    }

    public void delete(int arg3) {
        arg3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg3);
        if(arg3 >= 0 && this.mValues[arg3] != PositionMap.DELETED) {
            this.mValues[arg3] = PositionMap.DELETED;
            this.mGarbage = true;
        }
    }

    private void gc() {
        int v0 = this.mSize;
        int[] v1 = this.mKeys;
        Object[] v2 = this.mValues;
        int v4 = 0;
        int v5 = 0;
        while(v4 < v0) {
            Object v6 = v2[v4];
            if(v6 != PositionMap.DELETED) {
                if(v4 != v5) {
                    v1[v5] = v1[v4];
                    v2[v5] = v6;
                    v2[v4] = null;
                }

                ++v5;
            }

            ++v4;
        }

        this.mGarbage = false;
        this.mSize = v5;
    }

    public Object get(int arg2) {
        return this.get(arg2, null);
    }

    public Object get(int arg3, Object arg4) {
        arg3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg3);
        if(arg3 >= 0) {
            if(this.mValues[arg3] == PositionMap.DELETED) {
            }
            else {
                return this.mValues[arg3];
            }
        }

        return arg4;
    }

    static int idealBooleanArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0);
    }

    static int idealByteArraySize(int arg2) {
        int v0;
        for(v0 = 4; v0 < 32; ++v0) {
            int v1 = (1 << v0) - 12;
            if(arg2 <= v1) {
                return v1;
            }
        }

        return arg2;
    }

    static int idealCharArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0 * 2) / 2;
    }

    static int idealFloatArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0 * 4) / 4;
    }

    static int idealIntArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0 * 4) / 4;
    }

    static int idealLongArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0 * 8) / 8;
    }

    static int idealObjectArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0 * 4) / 4;
    }

    static int idealShortArraySize(int arg0) {
        return PositionMap.idealByteArraySize(arg0 * 2) / 2;
    }

    public int indexOfKey(int arg3) {
        if(this.mGarbage) {
            this.gc();
        }

        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg3);
    }

    public int indexOfValue(Object arg3) {
        if(this.mGarbage) {
            this.gc();
        }

        int v0;
        for(v0 = 0; v0 < this.mSize; ++v0) {
            if(this.mValues[v0] == arg3) {
                return v0;
            }
        }

        return -1;
    }

    public void insertKeyRange(int arg1, int arg2) {
    }

    public int keyAt(int arg2) {
        if(this.mGarbage) {
            this.gc();
        }

        return this.mKeys[arg2];
    }

    public void put(int arg7, Object arg8) {
        int v0 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg7);
        if(v0 >= 0) {
            this.mValues[v0] = arg8;
        }
        else {
            v0 ^= -1;
            if(v0 < this.mSize && this.mValues[v0] == PositionMap.DELETED) {
                this.mKeys[v0] = arg7;
                this.mValues[v0] = arg8;
                return;
            }

            if((this.mGarbage) && this.mSize >= this.mKeys.length) {
                this.gc();
                v0 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, arg7) ^ -1;
            }

            if(this.mSize >= this.mKeys.length) {
                int v1 = PositionMap.idealIntArraySize(this.mSize + 1);
                int[] v2 = new int[v1];
                Object[] v1_1 = new Object[v1];
                System.arraycopy(this.mKeys, 0, v2, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, v1_1, 0, this.mValues.length);
                this.mKeys = v2;
                this.mValues = v1_1;
            }

            if(this.mSize - v0 != 0) {
                int v3 = v0 + 1;
                System.arraycopy(this.mKeys, v0, this.mKeys, v3, this.mSize - v0);
                System.arraycopy(this.mValues, v0, this.mValues, v3, this.mSize - v0);
            }

            this.mKeys[v0] = arg7;
            this.mValues[v0] = arg8;
            ++this.mSize;
        }
    }

    public void remove(int arg1) {
        this.delete(arg1);
    }

    public void removeAt(int arg3) {
        if(this.mValues[arg3] != PositionMap.DELETED) {
            this.mValues[arg3] = PositionMap.DELETED;
            this.mGarbage = true;
        }
    }

    public void removeAtRange(int arg2, int arg3) {
        arg3 = Math.min(this.mSize, arg3 + arg2);
        while(arg2 < arg3) {
            this.removeAt(arg2);
            ++arg2;
        }
    }

    public void removeKeyRange(ArrayList arg1, int arg2, int arg3) {
    }

    public void setValueAt(int arg2, Object arg3) {
        if(this.mGarbage) {
            this.gc();
        }

        this.mValues[arg2] = arg3;
    }

    public int size() {
        if(this.mGarbage) {
            this.gc();
        }

        return this.mSize;
    }

    public String toString() {
        if(this.size() <= 0) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.mSize * 28);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.mSize; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            v0.append(this.keyAt(v1));
            v0.append('=');
            Object v2 = this.valueAt(v1);
            if((((PositionMap)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Map)");
            }
        }

        v0.append('}');
        return v0.toString();
    }

    public Object valueAt(int arg2) {
        if(this.mGarbage) {
            this.gc();
        }

        return this.mValues[arg2];
    }
}

