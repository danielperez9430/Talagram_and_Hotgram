package com.google.android.gms.flags;

public final class Singletons {
    private static Singletons zzach;
    private final FlagRegistry zzaci;
    private final FlagValueProvider zzacj;

    static {
        Singletons.setInstance(new Singletons());
    }

    private Singletons() {
        super();
        this.zzaci = new FlagRegistry();
        this.zzacj = new FlagValueProvider();
    }

    public static FlagRegistry flagRegistry() {
        return Singletons.zzdm().zzaci;
    }

    public static FlagValueProvider flagValueProvider() {
        return Singletons.zzdm().zzacj;
    }

    protected static void setInstance(Singletons arg1) {
        Class v0 = Singletons.class;
        __monitor_enter(v0);
        try {
            Singletons.zzach = arg1;
            __monitor_exit(v0);
            return;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    private static Singletons zzdm() {
        Class v0 = Singletons.class;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return Singletons.zzach;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }
}

