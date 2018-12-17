package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;

abstract class lm implements Iterator {
    final Iterator a;

    abstract Object a(Object arg1);

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final Object next() {
        return this.a(this.a.next());
    }

    public final void remove() {
        this.a.remove();
    }
}

