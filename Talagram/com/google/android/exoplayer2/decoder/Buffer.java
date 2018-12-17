package com.google.android.exoplayer2.decoder;

public abstract class Buffer {
    private int flags;

    public Buffer() {
        super();
    }

    public final void addFlag(int arg2) {
        this.flags |= arg2;
    }

    public void clear() {
        this.flags = 0;
    }

    public final void clearFlag(int arg2) {
        this.flags &= arg2 ^ -1;
    }

    protected final boolean getFlag(int arg2) {
        boolean v2 = (this.flags & arg2) == arg2 ? true : false;
        return v2;
    }

    public final boolean isDecodeOnly() {
        return this.getFlag(-2147483648);
    }

    public final boolean isEndOfStream() {
        return this.getFlag(4);
    }

    public final boolean isKeyFrame() {
        return this.getFlag(1);
    }

    public final void setFlags(int arg1) {
        this.flags = arg1;
    }
}

