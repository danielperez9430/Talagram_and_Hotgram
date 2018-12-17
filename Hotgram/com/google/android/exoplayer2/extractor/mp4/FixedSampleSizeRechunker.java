package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Util;

final class FixedSampleSizeRechunker {
    class com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker$1 {
    }

    public final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;

        Results(long[] arg1, int[] arg2, int arg3, long[] arg4, int[] arg5, long arg6, com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker$1 arg8) {
            this(arg1, arg2, arg3, arg4, arg5, arg6);
        }

        private Results(long[] arg1, int[] arg2, int arg3, long[] arg4, int[] arg5, long arg6) {
            super();
            this.offsets = arg1;
            this.sizes = arg2;
            this.maximumSize = arg3;
            this.timestamps = arg4;
            this.flags = arg5;
            this.duration = arg6;
        }
    }

    private static final int MAX_SAMPLE_SIZE = 8192;

    private FixedSampleSizeRechunker() {
        super();
    }

    public static Results rechunk(int arg17, long[] arg18, int[] arg19, long arg20) {
        int[] v1 = arg19;
        int v2 = 8192 / arg17;
        int v3 = v1.length;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        while(v5 < v3) {
            v6 += Util.ceilDivide(v1[v5], v2);
            ++v5;
        }

        long[] v8 = new long[v6];
        int[] v9 = new int[v6];
        long[] v11 = new long[v6];
        int[] v12 = new int[v6];
        v3 = 0;
        v5 = 0;
        int v10 = 0;
        while(v4 < v1.length) {
            v6 = v1[v4];
            long v13 = arg18[v4];
            while(v6 > 0) {
                int v15 = Math.min(v2, v6);
                v8[v5] = v13;
                v9[v5] = arg17 * v15;
                v10 = Math.max(v10, v9[v5]);
                v11[v5] = (((long)v3)) * arg20;
                v12[v5] = 1;
                v13 += ((long)v9[v5]);
                v3 += v15;
                v6 -= v15;
                ++v5;
            }

            ++v4;
            v1 = arg19;
        }

        return new Results(v8, v9, v10, v11, v12, arg20 * (((long)v3)), null);
    }
}

