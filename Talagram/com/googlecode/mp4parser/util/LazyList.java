package com.googlecode.mp4parser.util;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LazyList extends AbstractList {
    private static final Logger LOG;
    Iterator elementSource;
    List underlying;

    static {
        LazyList.LOG = Logger.getLogger(LazyList.class);
    }

    public LazyList(List arg1, Iterator arg2) {
        super();
        this.underlying = arg1;
        this.elementSource = arg2;
    }

    private void blowup() {
        LazyList.LOG.logDebug("blowup running");
        while(this.elementSource.hasNext()) {
            this.underlying.add(this.elementSource.next());
        }
    }

    public Object get(int arg3) {
        if(this.underlying.size() > arg3) {
            return this.underlying.get(arg3);
        }

        if(this.elementSource.hasNext()) {
            this.underlying.add(this.elementSource.next());
            return this.get(arg3);
        }

        throw new NoSuchElementException();
    }

    public List getUnderlying() {
        return this.underlying;
    }

    public Iterator iterator() {
        return new Iterator() {
            int pos;

            public boolean hasNext() {
                if(this.pos >= LazyList.this.underlying.size() && !LazyList.this.elementSource.hasNext()) {
                    return 0;
                }

                return 1;
            }

            public Object next() {
                if(this.pos < LazyList.this.underlying.size()) {
                    List v0 = LazyList.this.underlying;
                    int v1 = this.pos;
                    this.pos = v1 + 1;
                    return v0.get(v1);
                }

                LazyList.this.underlying.add(LazyList.this.elementSource.next());
                return this.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public int size() {
        LazyList.LOG.logDebug("potentially expensive size() call");
        this.blowup();
        return this.underlying.size();
    }
}

