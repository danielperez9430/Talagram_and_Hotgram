package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class SimpleCacheSpan extends CacheSpan {
    private static final Pattern CACHE_FILE_PATTERN_V1 = null;
    private static final Pattern CACHE_FILE_PATTERN_V2 = null;
    private static final Pattern CACHE_FILE_PATTERN_V3 = null;
    private static final String SUFFIX = ".v3.exo";

    static {
        SimpleCacheSpan.CACHE_FILE_PATTERN_V1 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v1\\.exo$", 32);
        SimpleCacheSpan.CACHE_FILE_PATTERN_V2 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v2\\.exo$", 32);
        SimpleCacheSpan.CACHE_FILE_PATTERN_V3 = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.v3\\.exo$", 32);
    }

    private SimpleCacheSpan(String arg1, long arg2, long arg4, long arg6, File arg8) {
        super(arg1, arg2, arg4, arg6, arg8);
    }

    public SimpleCacheSpan copyWithUpdatedLastAccessTime(int arg11) {
        Assertions.checkState(this.isCached);
        long v7 = System.currentTimeMillis();
        return new SimpleCacheSpan(this.key, this.position, this.length, v7, SimpleCacheSpan.getCacheFile(this.file.getParentFile(), arg11, this.position, v7));
    }

    public static SimpleCacheSpan createCacheEntry(File arg12, CachedContentIndex arg13) {
        String v0 = arg12.getName();
        SimpleCacheSpan v2 = null;
        if(!v0.endsWith(".v3.exo")) {
            arg12 = SimpleCacheSpan.upgradeFile(arg12, arg13);
            if(arg12 == null) {
                return v2;
            }
            else {
                v0 = arg12.getName();
            }
        }

        File v11 = arg12;
        Matcher v12 = SimpleCacheSpan.CACHE_FILE_PATTERN_V3.matcher(((CharSequence)v0));
        if(!v12.matches()) {
            return v2;
        }

        long v7 = v11.length();
        String v4 = arg13.getKeyForId(Integer.parseInt(v12.group(1)));
        SimpleCacheSpan v13 = v4 == null ? v2 : new SimpleCacheSpan(v4, Long.parseLong(v12.group(2)), v7, Long.parseLong(v12.group(3)), v11);
        return v13;
    }

    public static SimpleCacheSpan createClosedHole(String arg10, long arg11, long arg13) {
        return new SimpleCacheSpan(arg10, arg11, arg13, -9223372036854775807L, null);
    }

    public static SimpleCacheSpan createLookup(String arg10, long arg11) {
        return new SimpleCacheSpan(arg10, arg11, -1, -9223372036854775807L, null);
    }

    public static SimpleCacheSpan createOpenHole(String arg10, long arg11) {
        return new SimpleCacheSpan(arg10, arg11, -1, -9223372036854775807L, null);
    }

    public static File getCacheFile(File arg2, int arg3, long arg4, long arg6) {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3);
        v1.append(".");
        v1.append(arg4);
        v1.append(".");
        v1.append(arg6);
        v1.append(".v3.exo");
        return new File(arg2, v1.toString());
    }

    private static File upgradeFile(File arg11, CachedContentIndex arg12) {
        String v0 = arg11.getName();
        Matcher v1 = SimpleCacheSpan.CACHE_FILE_PATTERN_V2.matcher(((CharSequence)v0));
        File v4 = null;
        if(v1.matches()) {
            v0 = Util.unescapeFileName(v1.group(1));
            if(v0 == null) {
                return v4;
            }
        }
        else {
            v1 = SimpleCacheSpan.CACHE_FILE_PATTERN_V1.matcher(((CharSequence)v0));
            if(!v1.matches()) {
                return v4;
            }
            else {
                v0 = v1.group(1);
            }
        }

        File v12 = SimpleCacheSpan.getCacheFile(arg11.getParentFile(), arg12.assignIdForKey(v0), Long.parseLong(v1.group(2)), Long.parseLong(v1.group(3)));
        if(!arg11.renameTo(v12)) {
            return v4;
        }

        return v12;
    }
}

