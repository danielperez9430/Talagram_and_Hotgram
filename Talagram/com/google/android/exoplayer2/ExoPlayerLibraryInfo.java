package com.google.android.exoplayer2;

import java.util.HashSet;

public final class ExoPlayerLibraryInfo {
    public static final boolean ASSERTIONS_ENABLED = true;
    public static final boolean GL_ASSERTIONS_ENABLED = false;
    public static final String TAG = "ExoPlayer";
    public static final boolean TRACE_ENABLED = true;
    public static final String VERSION = "2.8.3";
    public static final int VERSION_INT = 2008003;
    public static final String VERSION_SLASHY = "ExoPlayerLib/2.8.3";
    private static final HashSet registeredModules;
    private static String registeredModulesString;

    static {
        ExoPlayerLibraryInfo.registeredModules = new HashSet();
        ExoPlayerLibraryInfo.registeredModulesString = "goog.exo.core";
    }

    private ExoPlayerLibraryInfo() {
        super();
    }

    public static void registerModule(String arg3) {
        Class v0 = ExoPlayerLibraryInfo.class;
        __monitor_enter(v0);
        try {
            if(ExoPlayerLibraryInfo.registeredModules.add(arg3)) {
                ExoPlayerLibraryInfo.registeredModulesString = ExoPlayerLibraryInfo.registeredModulesString + ", " + arg3;
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    public static String registeredModules() {
        String v1_1;
        Class v0 = ExoPlayerLibraryInfo.class;
        __monitor_enter(v0);
        try {
            v1_1 = ExoPlayerLibraryInfo.registeredModulesString;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }
}

