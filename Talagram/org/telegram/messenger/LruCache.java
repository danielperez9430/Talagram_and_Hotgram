package org.telegram.messenger;

import android.graphics.drawable.BitmapDrawable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;

public class LruCache {
    private final LinkedHashMap map;
    private final LinkedHashMap mapFilters;
    private int maxSize;
    private int size;

    public LruCache(int arg4) {
        super();
        if(arg4 > 0) {
            this.maxSize = arg4;
            this.map = new LinkedHashMap(0, 0.75f, true);
            this.mapFilters = new LinkedHashMap();
            return;
        }

        throw new IllegalArgumentException("maxSize <= 0");
    }

    public boolean contains(String arg2) {
        return this.map.containsKey(arg2);
    }

    protected void entryRemoved(boolean arg1, String arg2, BitmapDrawable arg3, BitmapDrawable arg4) {
    }

    public final void evictAll() {
        this.trimToSize(-1, null);
    }

    public final BitmapDrawable get(String arg2) {
        if(arg2 != null) {
            __monitor_enter(this);
            try {
                Object v2_1 = this.map.get(arg2);
                if(v2_1 != null) {
                    __monitor_exit(this);
                    return ((BitmapDrawable)v2_1);
                }

                __monitor_exit(this);
                return null;
            label_11:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_11;
            }

            throw v2;
        }

        throw new NullPointerException("key == null");
    }

    public ArrayList getFilterKeys(String arg2) {
        Object v2 = this.mapFilters.get(arg2);
        if(v2 != null) {
            return new ArrayList(((Collection)v2));
        }

        return null;
    }

    public final int maxSize() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.maxSize;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public BitmapDrawable put(String arg8, BitmapDrawable arg9) {
        Object v0;
        if(arg8 != null && arg9 != null) {
            __monitor_enter(this);
            try {
                this.size += this.safeSizeOf(arg8, arg9);
                v0 = this.map.put(arg8, arg9);
                if(v0 != null) {
                    this.size -= this.safeSizeOf(arg8, ((BitmapDrawable)v0));
                }

                __monitor_exit(this);
            }
            catch(Throwable v8) {
                try {
                label_41:
                    __monitor_exit(this);
                }
                catch(Throwable v8) {
                    goto label_41;
                }

                throw v8;
            }

            String[] v1 = arg8.split("@");
            if(v1.length > 1) {
                Object v2 = this.mapFilters.get(v1[0]);
                if(v2 == null) {
                    ArrayList v2_1 = new ArrayList();
                    this.mapFilters.put(v1[0], v2_1);
                }

                if(((ArrayList)v2).contains(v1[1])) {
                    goto label_35;
                }

                ((ArrayList)v2).add(v1[1]);
            }

        label_35:
            if(v0 != null) {
                this.entryRemoved(false, arg8, ((BitmapDrawable)v0), arg9);
            }

            this.trimToSize(this.maxSize, arg8);
            return ((BitmapDrawable)v0);
        }

        throw new NullPointerException("key == null || value == null");
    }

    public final BitmapDrawable remove(String arg7) {
        Object v0;
        if(arg7 != null) {
            __monitor_enter(this);
            try {
                v0 = this.map.remove(arg7);
                if(v0 != null) {
                    this.size -= this.safeSizeOf(arg7, ((BitmapDrawable)v0));
                }

                __monitor_exit(this);
                if(v0 == null) {
                    goto label_30;
                }
            }
            catch(Throwable v7) {
                try {
                label_32:
                    __monitor_exit(this);
                }
                catch(Throwable v7) {
                    goto label_32;
                }

                throw v7;
            }

            String[] v1 = arg7.split("@");
            if(v1.length > 1) {
                Object v2 = this.mapFilters.get(v1[0]);
                if(v2 != null) {
                    ((ArrayList)v2).remove(v1[1]);
                    if(((ArrayList)v2).isEmpty()) {
                        this.mapFilters.remove(v1[0]);
                    }
                }
            }

            this.entryRemoved(false, arg7, ((BitmapDrawable)v0), null);
        label_30:
            return ((BitmapDrawable)v0);
        }

        throw new NullPointerException("key == null");
    }

    private int safeSizeOf(String arg4, BitmapDrawable arg5) {
        int v0 = this.sizeOf(arg4, arg5);
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Negative size: ");
        v1.append(arg4);
        v1.append("=");
        v1.append(arg5);
        throw new IllegalStateException(v1.toString());
    }

    public final int size() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.size;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    protected int sizeOf(String arg1, BitmapDrawable arg2) {
        return 1;
    }

    private void trimToSize(int arg9, String arg10) {
        __monitor_enter(this);
        try {
            Iterator v0 = this.map.entrySet().iterator();
            while(v0.hasNext()) {
                if(this.size <= arg9) {
                    break;
                }

                if(this.map.isEmpty()) {
                }
                else {
                    Object v1 = v0.next();
                    Object v2 = ((Map$Entry)v1).getKey();
                    if(arg10 != null && (arg10.equals(v2))) {
                        continue;
                    }

                    v1 = ((Map$Entry)v1).getValue();
                    this.size -= this.safeSizeOf(((String)v2), ((BitmapDrawable)v1));
                    v0.remove();
                    String[] v3 = ((String)v2).split("@");
                    if(v3.length > 1) {
                        Object v4 = this.mapFilters.get(v3[0]);
                        if(v4 != null) {
                            ((ArrayList)v4).remove(v3[1]);
                            if(((ArrayList)v4).isEmpty()) {
                                this.mapFilters.remove(v3[0]);
                            }
                        }
                    }

                    this.entryRemoved(true, ((String)v2), ((BitmapDrawable)v1), null);
                    continue;
                }

                break;
            }

            __monitor_exit(this);
            return;
        label_47:
            __monitor_exit(this);
        }
        catch(Throwable v9) {
            goto label_47;
        }

        throw v9;
    }
}

