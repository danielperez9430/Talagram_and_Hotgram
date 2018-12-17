package com.google.android.gms.flags;

import android.os.RemoteException;

public abstract class Flag {
    public class BooleanFlag extends Flag {
        public BooleanFlag(int arg2, String arg3, Boolean arg4) {
            super(arg2, arg3, arg4, null);
        }

        public Boolean get(IFlagProvider arg4) {
            try {
                return Boolean.valueOf(arg4.getBooleanFlagValue(((Flag)this).getKey(), ((Flag)this).getDefault().booleanValue(), ((Flag)this).getSource()));
            }
            catch(RemoteException ) {
                return ((Flag)this).getDefault();
            }
        }

        public Object get(IFlagProvider arg1) {
            return this.get(arg1);
        }
    }

    public class IntegerFlag extends Flag {
        public IntegerFlag(int arg2, String arg3, Integer arg4) {
            super(arg2, arg3, arg4, null);
        }

        public Integer get(IFlagProvider arg4) {
            try {
                return Integer.valueOf(arg4.getIntFlagValue(((Flag)this).getKey(), ((Flag)this).getDefault().intValue(), ((Flag)this).getSource()));
            }
            catch(RemoteException ) {
                return ((Flag)this).getDefault();
            }
        }

        public Object get(IFlagProvider arg1) {
            return this.get(arg1);
        }
    }

    public class LongFlag extends Flag {
        public LongFlag(int arg2, String arg3, Long arg4) {
            super(arg2, arg3, arg4, null);
        }

        public Long get(IFlagProvider arg5) {
            try {
                return Long.valueOf(arg5.getLongFlagValue(((Flag)this).getKey(), ((Flag)this).getDefault().longValue(), ((Flag)this).getSource()));
            }
            catch(RemoteException ) {
                return ((Flag)this).getDefault();
            }
        }

        public Object get(IFlagProvider arg1) {
            return this.get(arg1);
        }
    }

    public class StringFlag extends Flag {
        public StringFlag(int arg2, String arg3, String arg4) {
            super(arg2, arg3, arg4, null);
        }

        public Object get(IFlagProvider arg1) {
            return this.get(arg1);
        }

        public String get(IFlagProvider arg4) {
            try {
                return arg4.getStringFlagValue(((Flag)this).getKey(), ((Flag)this).getDefault(), ((Flag)this).getSource());
            }
            catch(RemoteException ) {
                return ((Flag)this).getDefault();
            }
        }
    }

    private final Object mDefaultValue;
    private final String mKey;
    private final int zzacb;

    private Flag(int arg1, String arg2, Object arg3) {
        super();
        this.zzacb = arg1;
        this.mKey = arg2;
        this.mDefaultValue = arg3;
        Singletons.flagRegistry().registerFlag(this);
    }

    Flag(int arg1, String arg2, Object arg3, zza arg4) {
        this(arg1, arg2, arg3);
    }

    public static BooleanFlag define(int arg1, String arg2, Boolean arg3) {
        return new BooleanFlag(arg1, arg2, arg3);
    }

    public static IntegerFlag define(int arg1, String arg2, int arg3) {
        return new IntegerFlag(arg1, arg2, Integer.valueOf(arg3));
    }

    public static LongFlag define(int arg1, String arg2, long arg3) {
        return new LongFlag(arg1, arg2, Long.valueOf(arg3));
    }

    public static StringFlag define(int arg1, String arg2, String arg3) {
        return new StringFlag(arg1, arg2, arg3);
    }

    public static StringFlag defineClientExperimentId(int arg1, String arg2) {
        StringFlag v1 = Flag.define(arg1, arg2, null);
        Singletons.flagRegistry().registerClientExperimentId(v1);
        return v1;
    }

    public static StringFlag defineServiceExperimentId(int arg1, String arg2) {
        StringFlag v1 = Flag.define(arg1, arg2, null);
        Singletons.flagRegistry().registerServiceExperimentId(v1);
        return v1;
    }

    public Object get() {
        return Singletons.flagValueProvider().getFlagValue(this);
    }

    protected abstract Object get(IFlagProvider arg1);

    public Object getDefault() {
        return this.mDefaultValue;
    }

    public String getKey() {
        return this.mKey;
    }

    public int getSource() {
        return this.zzacb;
    }
}

