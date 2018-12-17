package com.google.android.exoplayer2.upstream.cache;

public interface ContentMetadata {
    public static final String INTERNAL_METADATA_NAME_PREFIX = "exo_";

    boolean contains(String arg1);

    long get(String arg1, long arg2);

    String get(String arg1, String arg2);

    byte[] get(String arg1, byte[] arg2);
}

