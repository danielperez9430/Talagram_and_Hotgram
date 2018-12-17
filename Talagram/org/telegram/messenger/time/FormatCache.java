package org.telegram.messenger.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

abstract class FormatCache {
    class MultipartKey {
        private int hashCode;
        private final Object[] keys;

        public MultipartKey(Object[] arg1) {
            super();
            this.keys = arg1;
        }

        public boolean equals(Object arg2) {
            return Arrays.equals(this.keys, ((MultipartKey)arg2).keys);
        }

        public int hashCode() {
            if(this.hashCode == 0) {
                Object[] v0 = this.keys;
                int v1 = v0.length;
                int v2 = 0;
                int v3 = 0;
                while(v2 < v1) {
                    Object v4 = v0[v2];
                    if(v4 != null) {
                        v3 = v3 * 7 + v4.hashCode();
                    }

                    ++v2;
                }

                this.hashCode = v3;
            }

            return this.hashCode;
        }
    }

    static final int NONE = -1;
    private static final ConcurrentMap cDateTimeInstanceCache;
    private final ConcurrentMap cInstanceCache;

    static {
        FormatCache.cDateTimeInstanceCache = new ConcurrentHashMap(7);
    }

    FormatCache() {
        super();
        this.cInstanceCache = new ConcurrentHashMap(7);
    }

    protected abstract Format createInstance(String arg1, TimeZone arg2, Locale arg3);

    Format getDateInstance(int arg2, TimeZone arg3, Locale arg4) {
        return this.getDateTimeInstance(Integer.valueOf(arg2), null, arg3, arg4);
    }

    private Format getDateTimeInstance(Integer arg1, Integer arg2, TimeZone arg3, Locale arg4) {
        if(arg4 == null) {
            arg4 = Locale.getDefault();
        }

        return this.getInstance(FormatCache.getPatternForStyle(arg1, arg2, arg4), arg3, arg4);
    }

    Format getDateTimeInstance(int arg1, int arg2, TimeZone arg3, Locale arg4) {
        return this.getDateTimeInstance(Integer.valueOf(arg1), Integer.valueOf(arg2), arg3, arg4);
    }

    public Format getInstance(String arg4, TimeZone arg5, Locale arg6) {
        Format v1_1;
        if(arg4 != null) {
            if(arg5 == null) {
                arg5 = TimeZone.getDefault();
            }

            if(arg6 == null) {
                arg6 = Locale.getDefault();
            }

            MultipartKey v0 = new MultipartKey(new Object[]{arg4, arg5, arg6});
            Object v1 = this.cInstanceCache.get(v0);
            if(v1 == null) {
                v1_1 = this.createInstance(arg4, arg5, arg6);
                Object v4 = this.cInstanceCache.putIfAbsent(v0, v1_1);
                if(v4 != null) {
                    v1 = v4;
                }
            }

            return v1_1;
        }

        throw new NullPointerException("pattern must not be null");
    }

    public Format getInstance() {
        return this.getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    static String getPatternForStyle(Integer arg3, Integer arg4, Locale arg5) {
        String v1_1;
        Object v4;
        String v3_1;
        MultipartKey v0 = new MultipartKey(new Object[]{arg3, arg4, arg5});
        Object v1 = FormatCache.cDateTimeInstanceCache.get(v0);
        if(v1 == null) {
            if(arg3 == null) {
                try {
                    DateFormat v3 = DateFormat.getTimeInstance(arg4.intValue(), arg5);
                    goto label_24;
                label_17:
                    v3 = arg4 == null ? DateFormat.getDateInstance(arg3.intValue(), arg5) : DateFormat.getDateTimeInstance(arg3.intValue(), arg4.intValue(), arg5);
                label_24:
                    v3_1 = ((SimpleDateFormat)v3).toPattern();
                    v4 = FormatCache.cDateTimeInstanceCache.putIfAbsent(v0, v3_1);
                    if(v4 == null) {
                        goto label_30;
                    }

                    goto label_28;
                }
                catch(ClassCastException ) {
                    StringBuilder v4_1 = new StringBuilder();
                    v4_1.append("No date time pattern for locale: ");
                    v4_1.append(arg5);
                    throw new IllegalArgumentException(v4_1.toString());
                }
            }
            else {
                goto label_17;
            }

            goto label_24;
        label_28:
            v1 = v4;
            return v1_1;
        label_30:
            v1_1 = v3_1;
        }

        return v1_1;
    }

    Format getTimeInstance(int arg2, TimeZone arg3, Locale arg4) {
        return this.getDateTimeInstance(null, Integer.valueOf(arg2), arg3, arg4);
    }
}

