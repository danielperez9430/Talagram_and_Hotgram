package com.google.android.exoplayer2.util;

public final class LibraryLoader {
    private boolean isAvailable;
    private boolean loadAttempted;
    private String[] nativeLibraries;

    public LibraryLoader(String[] arg1) {
        super();
        this.nativeLibraries = arg1;
    }

    public boolean isAvailable() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            if(!this.loadAttempted) {
                goto label_7;
            }

            v0_1 = this.isAvailable;
        }
        catch(Throwable v0) {
            goto label_21;
        }

        __monitor_exit(this);
        return v0_1;
        try {
        label_7:
            this.loadAttempted = true;
            try {
                String[] v1 = this.nativeLibraries;
                int v2 = v1.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    System.loadLibrary(v1[v3]);
                }

                this.isAvailable = true;
                goto label_17;
            }
            catch(UnsatisfiedLinkError ) {
            label_17:
                v0_1 = this.isAvailable;
                __monitor_exit(this);
                return v0_1;
            }
        }
        catch(Throwable v0) {
        }

    label_21:
        __monitor_exit(this);
        throw v0;
    }

    public void setLibraries(String[] arg3) {
        __monitor_enter(this);
        try {
            Assertions.checkState(this.loadAttempted ^ 1, "Cannot set libraries after loading");
            this.nativeLibraries = arg3;
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }
}

