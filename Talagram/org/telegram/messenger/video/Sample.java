package org.telegram.messenger.video;

public class Sample {
    private long offset;
    private long size;

    public Sample(long arg1, long arg3) {
        super();
        this.offset = arg1;
        this.size = arg3;
    }

    public long getOffset() {
        return this.offset;
    }

    public long getSize() {
        return this.size;
    }
}

