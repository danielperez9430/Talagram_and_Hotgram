package com.googlecode.mp4parser.h264;

public class CharCache {
    private char[] cache;
    private int pos;

    public CharCache(int arg1) {
        super();
        this.cache = new char[arg1];
    }

    public void append(char arg3) {
        if(this.pos < this.cache.length - 1) {
            this.cache[this.pos] = arg3;
            ++this.pos;
        }
    }

    public void append(String arg5) {
        char[] v5 = arg5.toCharArray();
        int v0 = this.cache.length - this.pos;
        if(v5.length < v0) {
            v0 = v5.length;
        }

        System.arraycopy(v5, 0, this.cache, this.pos, v0);
        this.pos += v0;
    }

    public void clear() {
        this.pos = 0;
    }

    public int length() {
        return this.pos;
    }

    public String toString() {
        return new String(this.cache, 0, this.pos);
    }
}

