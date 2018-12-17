package com.google.ads.interactivemedia.v3.internal;

import java.util.NoSuchElementException;

abstract class kt extends lo {
    private final int a;
    private int b;

    protected kt(int arg1, int arg2) {
        super();
        kr.b(arg2, arg1);
        this.a = arg1;
        this.b = arg2;
    }

    protected abstract Object a(int arg1);

    public final boolean hasNext() {
        boolean v0 = this.b < this.a ? true : false;
        return v0;
    }

    public final boolean hasPrevious() {
        boolean v0 = this.b > 0 ? true : false;
        return v0;
    }

    public final Object next() {
        if(this.hasNext()) {
            int v0 = this.b;
            this.b = v0 + 1;
            return this.a(v0);
        }

        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.b;
    }

    public final Object previous() {
        if(this.hasPrevious()) {
            int v0 = this.b - 1;
            this.b = v0;
            return this.a(v0);
        }

        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.b - 1;
    }
}

