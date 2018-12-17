package org.telegram.messenger.support;

public class SparseLongArray implements Cloneable {
    private int[] mKeys;
    private int mSize;
    private long[] mValues;

    public SparseLongArray() {
        this(10);
    }

    public SparseLongArray(int arg2) {
        super();
        arg2 = ArrayUtils.idealLongArraySize(arg2);
        this.mKeys = new int[arg2];
        this.mValues = new long[arg2];
        this.mSize = 0;
    }

    public void append(int arg3, long arg4) {
        if(this.mSize != 0 && arg3 <= this.mKeys[this.mSize - 1]) {
            this.put(arg3, arg4);
            return;
        }

        int v0 = this.mSize;
        if(v0 >= this.mKeys.length) {
            this.growKeyAndValueArrays(v0 + 1);
        }

        this.mKeys[v0] = arg3;
        this.mValues[v0] = arg4;
        this.mSize = v0 + 1;
    }

    private static int binarySearch(int[] arg6, int arg7, int arg8, long arg9) {
        arg8 += arg7;
        int v1 = arg7 - 1;
        for(arg7 = arg8; arg7 - v1 > 1; arg7 = v2) {
            int v2 = (arg7 + v1) / 2;
            if((((long)arg6[v2])) < arg9) {
                v1 = v2;
                continue;
            }
        }

        if(arg7 == arg8) {
            return arg8 ^ -1;
        }

        if((((long)arg6[arg7])) == arg9) {
            return arg7;
        }

        return arg7 ^ -1;
    }

    public void clear() {
        this.mSize = 0;
    }

    public Object clone() {
        return this.clone();
    }

    public SparseLongArray clone() {
        // Method was not decompiled
    }

    public void delete(int arg5) {
        arg5 = SparseLongArray.binarySearch(this.mKeys, 0, this.mSize, ((long)arg5));
        if(arg5 >= 0) {
            this.removeAt(arg5);
        }
    }

    public long get(int arg3) {
        return this.get(arg3, 0);
    }

    public long get(int arg5, long arg6) {
        arg5 = SparseLongArray.binarySearch(this.mKeys, 0, this.mSize, ((long)arg5));
        if(arg5 < 0) {
            return arg6;
        }

        return this.mValues[arg5];
    }

    private void growKeyAndValueArrays(int arg5) {
        arg5 = ArrayUtils.idealLongArraySize(arg5);
        int[] v0 = new int[arg5];
        long[] v5 = new long[arg5];
        System.arraycopy(this.mKeys, 0, v0, 0, this.mKeys.length);
        System.arraycopy(this.mValues, 0, v5, 0, this.mValues.length);
        this.mKeys = v0;
        this.mValues = v5;
    }

    public int indexOfKey(int arg5) {
        return SparseLongArray.binarySearch(this.mKeys, 0, this.mSize, ((long)arg5));
    }

    public int indexOfValue(long arg5) {
        int v0;
        for(v0 = 0; v0 < this.mSize; ++v0) {
            if(this.mValues[v0] == arg5) {
                return v0;
            }
        }

        return -1;
    }

    public int keyAt(int arg2) {
        return this.mKeys[arg2];
    }

    public void put(int arg6, long arg7) {
        int v0 = SparseLongArray.binarySearch(this.mKeys, 0, this.mSize, ((long)arg6));
        if(v0 >= 0) {
            this.mValues[v0] = arg7;
        }
        else {
            v0 ^= -1;
            if(this.mSize >= this.mKeys.length) {
                this.growKeyAndValueArrays(this.mSize + 1);
            }

            if(this.mSize - v0 != 0) {
                int v3 = v0 + 1;
                System.arraycopy(this.mKeys, v0, this.mKeys, v3, this.mSize - v0);
                System.arraycopy(this.mValues, v0, this.mValues, v3, this.mSize - v0);
            }

            this.mKeys[v0] = arg6;
            this.mValues[v0] = arg7;
            ++this.mSize;
        }
    }

    public void removeAt(int arg5) {
        int v1 = arg5 + 1;
        System.arraycopy(this.mKeys, v1, this.mKeys, arg5, this.mSize - v1);
        System.arraycopy(this.mValues, v1, this.mValues, arg5, this.mSize - v1);
        --this.mSize;
    }

    public int size() {
        return this.mSize;
    }

    public long valueAt(int arg4) {
        return this.mValues[arg4];
    }
}

