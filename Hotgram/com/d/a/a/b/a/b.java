package com.d.a.a.b.a;

import android.graphics.Bitmap;
import com.d.a.a.b.a;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map$Entry;

public class b implements a {
    private final LinkedHashMap a;
    private final int b;
    private int c;

    public b(int arg4) {
        super();
        if(arg4 > 0) {
            this.b = arg4;
            this.a = new LinkedHashMap(0, 0.75f, true);
            return;
        }

        throw new IllegalArgumentException("maxSize <= 0");
    }

    private void a(int arg4) {
        while(true) {
            __monitor_enter(this);
            try {
                if(this.c >= 0 && (!this.a.isEmpty() || this.c == 0)) {
                    if(this.c > arg4) {
                        if(this.a.isEmpty()) {
                        }
                        else {
                            Object v0 = this.a.entrySet().iterator().next();
                            if(v0 != null) {
                                Object v1 = ((Map$Entry)v0).getKey();
                                v0 = ((Map$Entry)v0).getValue();
                                this.a.remove(v1);
                                this.c -= this.b(((String)v1), ((Bitmap)v0));
                                __monitor_exit(this);
                                continue;
                            }
                        }
                    }

                    __monitor_exit(this);
                    return;
                }

                StringBuilder v0_1 = new StringBuilder();
                v0_1.append(this.getClass().getName());
                v0_1.append(".sizeOf() is reporting inconsistent results!");
                throw new IllegalStateException(v0_1.toString());
            label_44:
                __monitor_exit(this);
                break;
            }
            catch(Throwable v4) {
                goto label_44;
            }
        }

        throw v4;
    }

    public final Bitmap a(String arg2) {
        if(arg2 != null) {
            __monitor_enter(this);
            try {
                __monitor_exit(this);
                return this.a.get(arg2);
            label_7:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_7;
            }

            throw v2;
        }

        throw new NullPointerException("key == null");
    }

    public Collection a() {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return new HashSet(this.a.keySet());
        label_8:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_8;
        }

        throw v0;
    }

    public final boolean a(String arg3, Bitmap arg4) {
        if(arg3 != null && arg4 != null) {
            __monitor_enter(this);
            try {
                this.c += this.b(arg3, arg4);
                Object v4 = this.a.put(arg3, arg4);
                if(v4 != null) {
                    this.c -= this.b(arg3, ((Bitmap)v4));
                }

                __monitor_exit(this);
            }
            catch(Throwable v3) {
                try {
                label_20:
                    __monitor_exit(this);
                }
                catch(Throwable v3) {
                    goto label_20;
                }

                throw v3;
            }

            this.a(this.b);
            return 1;
        }

        throw new NullPointerException("key == null || value == null");
    }

    private int b(String arg1, Bitmap arg2) {
        return arg2.getRowBytes() * arg2.getHeight();
    }

    public final Bitmap b(String arg3) {
        if(arg3 != null) {
            __monitor_enter(this);
            try {
                Object v0 = this.a.remove(arg3);
                if(v0 != null) {
                    this.c -= this.b(arg3, ((Bitmap)v0));
                }

                __monitor_exit(this);
                return ((Bitmap)v0);
            label_12:
                __monitor_exit(this);
            }
            catch(Throwable v3) {
                goto label_12;
            }

            throw v3;
        }

        throw new NullPointerException("key == null");
    }

    public final String toString() {
        String v0_1;
        __monitor_enter(this);
        try {
            v0_1 = String.format("LruCache[maxSize=%d]", Integer.valueOf(this.b));
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }
}

