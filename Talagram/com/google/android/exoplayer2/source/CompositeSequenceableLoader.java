package com.google.android.exoplayer2.source;

public class CompositeSequenceableLoader implements SequenceableLoader {
    protected final SequenceableLoader[] loaders;

    public CompositeSequenceableLoader(SequenceableLoader[] arg1) {
        super();
        this.loaders = arg1;
    }

    public boolean continueLoading(long arg19) {
        long v0 = arg19;
        int v3 = 0;
        do {
            long v4 = this.getNextLoadPositionUs();
            long v6 = -9223372036854775808L;
            if(v4 != v6) {
                SequenceableLoader[] v9 = this.loaders;
                int v10 = v9.length;
                int v11 = 0;
                int v12 = 0;
                while(v11 < v10) {
                    SequenceableLoader v13 = v9[v11];
                    long v14 = v13.getNextLoadPositionUs();
                    int v16 = v14 == v6 || v14 > v0 ? 0 : 1;
                    if(v14 == v4 || v16 != 0) {
                        v12 |= v13.continueLoading(v0);
                    }

                    ++v11;
                }

                v3 |= v12;
                if(v12 != 0) {
                    continue;
                }
            }
            else {
            }

            break;
        }
        while(true);

        return ((boolean)v3);
    }

    public final long getBufferedPositionUs() {
        long v7;
        SequenceableLoader[] v0 = this.loaders;
        int v1 = v0.length;
        long v2 = 9223372036854775807L;
        int v4 = 0;
        long v5 = v2;
        while(true) {
            v7 = -9223372036854775808L;
            if(v4 >= v1) {
                break;
            }

            long v9 = v0[v4].getBufferedPositionUs();
            if(v9 != v7) {
                v5 = Math.min(v5, v9);
            }

            ++v4;
        }

        if(v5 == v2) {
            v5 = v7;
        }

        return v5;
    }

    public final long getNextLoadPositionUs() {
        long v7;
        SequenceableLoader[] v0 = this.loaders;
        int v1 = v0.length;
        long v2 = 9223372036854775807L;
        int v4 = 0;
        long v5 = v2;
        while(true) {
            v7 = -9223372036854775808L;
            if(v4 >= v1) {
                break;
            }

            long v9 = v0[v4].getNextLoadPositionUs();
            if(v9 != v7) {
                v5 = Math.min(v5, v9);
            }

            ++v4;
        }

        if(v5 == v2) {
            v5 = v7;
        }

        return v5;
    }

    public final void reevaluateBuffer(long arg5) {
        SequenceableLoader[] v0 = this.loaders;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].reevaluateBuffer(arg5);
        }
    }
}

