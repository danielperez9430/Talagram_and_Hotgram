package com.googlecode.mp4parser.util;

public class IntHashMap {
    class Entry {
        int hash;
        int key;
        Entry next;
        Object value;

        protected Entry(int arg1, int arg2, Object arg3, Entry arg4) {
            super();
            this.hash = arg1;
            this.key = arg2;
            this.value = arg3;
            this.next = arg4;
        }
    }

    private transient int count;
    private float loadFactor;
    private transient Entry[] table;
    private int threshold;

    public IntHashMap() {
        this(20, 0.75f);
    }

    public IntHashMap(int arg3, float arg4) {
        StringBuilder v0;
        super();
        if(arg3 >= 0) {
            if(arg4 > 0f) {
                this.loadFactor = arg4;
                this.table = new Entry[1];
                this.threshold = ((int)((((float)1)) * arg4));
                return;
            }

            v0 = new StringBuilder("Illegal Load: ");
            v0.append(arg4);
            throw new IllegalArgumentException(v0.toString());
        }

        v0 = new StringBuilder("Illegal Capacity: ");
        v0.append(arg3);
        throw new IllegalArgumentException(v0.toString());
    }

    public IntHashMap(int arg2) {
        this(arg2, 0.75f);
    }

    public void clear() {
        int v1;
        Entry[] v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.table;
            v1 = v0_1.length;
            while(true) {
            label_3:
                --v1;
                if(v1 >= 0) {
                    break;
                }

                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_13;
        }

        Entry v2 = null;
        try {
            v0_1[v1] = v2;
            goto label_3;
        label_5:
            this.count = 0;
        }
        catch(Throwable v0) {
            goto label_13;
        }

        __monitor_exit(this);
        return;
    label_13:
        __monitor_exit(this);
        throw v0;
    }

    public boolean contains(Object arg5) {
        if(arg5 != null) {
            Entry[] v0 = this.table;
            int v1;
            for(v1 = v0.length; true; v1 = v2) {
                int v2 = v1 - 1;
                if(v1 <= 0) {
                    return 0;
                }

                Entry v1_1;
                for(v1_1 = v0[v2]; v1_1 != null; v1_1 = v1_1.next) {
                    if(v1_1.value.equals(arg5)) {
                        return 1;
                    }
                }
            }

            return 0;
        }

        throw new NullPointerException();
    }

    public boolean containsKey(int arg4) {
        Entry v0;
        for(v0 = this.table[(2147483647 & arg4) % this.table.length]; v0 != null; v0 = v0.next) {
            if(v0.hash == arg4) {
                return 1;
            }
        }

        return 0;
    }

    public boolean containsValue(Object arg1) {
        return this.contains(arg1);
    }

    public Object get(int arg4) {
        Entry v0;
        for(v0 = this.table[(2147483647 & arg4) % this.table.length]; v0 != null; v0 = v0.next) {
            if(v0.hash == arg4) {
                return v0.value;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        if(this.count == 0) {
            return 1;
        }

        return 0;
    }

    public Object put(int arg6, Object arg7) {
        Entry[] v0 = this.table;
        int v1 = 2147483647 & arg6;
        int v2 = v1 % v0.length;
        Entry v3;
        for(v3 = v0[v2]; v3 != null; v3 = v3.next) {
            if(v3.hash == arg6) {
                Object v6 = v3.value;
                v3.value = arg7;
                return v6;
            }
        }

        if(this.count >= this.threshold) {
            this.rehash();
            v0 = this.table;
            v2 = v1 % v0.length;
        }

        v0[v2] = new Entry(arg6, arg6, arg7, v0[v2]);
        ++this.count;
        return null;
    }

    protected void rehash() {
        int v0 = this.table.length;
        Entry[] v1 = this.table;
        int v2 = v0 * 2 + 1;
        Entry[] v3 = new Entry[v2];
        this.threshold = ((int)((((float)v2)) * this.loadFactor));
        this.table = v3;
        while(true) {
            int v4 = v0 - 1;
            if(v0 <= 0) {
                return;
            }

            Entry v0_1;
            for(v0_1 = v1[v4]; v0_1 != null; v0_1 = v5) {
                Entry v5 = v0_1.next;
                int v6 = (v0_1.hash & 2147483647) % v2;
                v0_1.next = v3[v6];
                v3[v6] = v0_1;
            }

            v0 = v4;
        }
    }

    public Object remove(int arg8) {
        Entry[] v0 = this.table;
        int v1 = (2147483647 & arg8) % v0.length;
        Entry v2 = v0[v1];
        Object v3 = null;
        Entry v4 = ((Entry)v3);
        while(v2 != null) {
            if(v2.hash == arg8) {
                if(v4 != null) {
                    v4.next = v2.next;
                }
                else {
                    v0[v1] = v2.next;
                }

                --this.count;
                Object v8 = v2.value;
                v2.value = v3;
                return v8;
            }

            v4 = v2;
            v2 = v2.next;
        }

        return v3;
    }

    public int size() {
        return this.count;
    }
}

