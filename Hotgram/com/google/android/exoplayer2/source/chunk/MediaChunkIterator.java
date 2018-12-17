package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.upstream.DataSpec;
import java.util.NoSuchElementException;

public interface MediaChunkIterator {
    final class com.google.android.exoplayer2.source.chunk.MediaChunkIterator$1 implements MediaChunkIterator {
        com.google.android.exoplayer2.source.chunk.MediaChunkIterator$1() {
            super();
        }

        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }

        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        public DataSpec getDataSpec() {
            throw new NoSuchElementException();
        }

        public boolean isEnded() {
            return 1;
        }

        public boolean next() {
            return 0;
        }
    }

    public static final MediaChunkIterator EMPTY;

    static {
        MediaChunkIterator.EMPTY = new com.google.android.exoplayer2.source.chunk.MediaChunkIterator$1();
    }

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    DataSpec getDataSpec();

    boolean isEnded();

    boolean next();
}

