package com.googlecode.mp4parser.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RangeStartMap implements Map {
    class com.googlecode.mp4parser.util.RangeStartMap$1 implements Comparator {
        com.googlecode.mp4parser.util.RangeStartMap$1(RangeStartMap arg1) {
            RangeStartMap.this = arg1;
            super();
        }

        public int compare(Comparable arg1, Comparable arg2) {
            return -arg1.compareTo(arg2);
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Comparable)arg1), ((Comparable)arg2));
        }
    }

    TreeMap base;

    public RangeStartMap() {
        super();
        this.base = new TreeMap(new com.googlecode.mp4parser.util.RangeStartMap$1(this));
    }

    public RangeStartMap(Comparable arg3, Object arg4) {
        super();
        this.base = new TreeMap(new com.googlecode.mp4parser.util.RangeStartMap$1(this));
        this.put(arg3, arg4);
    }

    public void clear() {
        this.base.clear();
    }

    public boolean containsKey(Object arg2) {
        if(this.base.get(arg2) != null) {
            return 1;
        }

        return 0;
    }

    public boolean containsValue(Object arg1) {
        return 0;
    }

    public Set entrySet() {
        return this.base.entrySet();
    }

    public Object get(Object arg4) {
        Object v1 = null;
        if(!(arg4 instanceof Comparable)) {
            return v1;
        }

        if(this.isEmpty()) {
            return v1;
        }

        Iterator v0 = this.base.keySet().iterator();
        while(true) {
            v1 = v0.next();
            if((v0.hasNext()) && ((Comparable)arg4).compareTo(v1) < 0) {
                continue;
            }

            break;
        }

        return this.base.get(v1);
    }

    public boolean isEmpty() {
        return this.base.isEmpty();
    }

    public Set keySet() {
        return this.base.keySet();
    }

    public Object put(Comparable arg2, Object arg3) {
        return this.base.put(arg2, arg3);
    }

    public Object put(Object arg1, Object arg2) {
        return this.put(((Comparable)arg1), arg2);
    }

    public void putAll(Map arg2) {
        this.base.putAll(arg2);
    }

    public Object remove(Object arg4) {
        Object v1 = null;
        if(!(arg4 instanceof Comparable)) {
            return v1;
        }

        if(this.isEmpty()) {
            return v1;
        }

        Iterator v0 = this.base.keySet().iterator();
        while(true) {
            v1 = v0.next();
            if((v0.hasNext()) && ((Comparable)arg4).compareTo(v1) < 0) {
                continue;
            }

            break;
        }

        return this.base.remove(v1);
    }

    public int size() {
        return this.base.size();
    }

    public Collection values() {
        return this.base.values();
    }
}

