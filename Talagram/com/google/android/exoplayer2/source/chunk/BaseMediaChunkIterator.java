package com.google.android.exoplayer2.source.chunk;

import java.util.NoSuchElementException;

public abstract class BaseMediaChunkIterator implements MediaChunkIterator {
    private long currentIndex;
    private final long fromIndex;
    private final long toIndex;

    public BaseMediaChunkIterator(long arg1, long arg3) {
        super();
        this.fromIndex = arg1;
        this.toIndex = arg3;
        this.currentIndex = arg1 - 1;
    }

    protected void checkInBounds() {
        if(this.currentIndex >= this.fromIndex && this.currentIndex <= this.toIndex) {
            return;
        }

        throw new NoSuchElementException();
    }

    protected long getCurrentIndex() {
        return this.currentIndex;
    }

    public boolean isEnded() {
        boolean v0 = this.currentIndex > this.toIndex ? true : false;
        return v0;
    }

    public boolean next() {
        ++this.currentIndex;
        return this.isEnded() ^ 1;
    }
}

