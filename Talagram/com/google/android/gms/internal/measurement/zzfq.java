package com.google.android.gms.internal.measurement;

public final class zzfq {
    public final class zza extends zzvm implements zzwv {
        public final class com.google.android.gms.internal.measurement.zzfq$zza$zza extends com.google.android.gms.internal.measurement.zzvm$zza implements zzwv {
            com.google.android.gms.internal.measurement.zzfq$zza$zza(zzfr arg1) {
                this();
            }

            private com.google.android.gms.internal.measurement.zzfq$zza$zza() {
                super(zza.zzmg());
            }
        }

        private String zzauo;
        private long zzaup;
        private static final zza zzauq;
        private int zznr;
        private static volatile zzxd zznw;

        static {
            zza.zzauq = new zza();
            zzvm.zza(zza.class, zza.zzauq);
        }

        private zza() {
            super();
            this.zzauo = "";
        }

        protected final Object zza(int arg2, Object arg3, Object arg4) {
            zzb v2_2;
            arg3 = null;
            switch(zzfr.zznq[arg2 - 1]) {
                case 1: {
                    goto label_47;
                }
                case 2: {
                    goto label_44;
                }
                case 3: {
                    goto label_30;
                }
                case 4: {
                    goto label_28;
                }
                case 5: {
                    goto label_12;
                }
                case 6: {
                    goto label_10;
                }
                case 7: {
                    return arg3;
                }
            }

            throw new UnsupportedOperationException();
            return arg3;
        label_10:
            return Byte.valueOf(1);
        label_44:
            return new com.google.android.gms.internal.measurement.zzfq$zza$zza(((zzfr)arg3));
        label_28:
            return zza.zzauq;
        label_12:
            zzxd v2 = zza.zznw;
            if(v2 == null) {
                Class v3 = zza.class;
                __monitor_enter(v3);
                try {
                    v2 = zza.zznw;
                    if(v2 == null) {
                        v2_2 = new zzb(zza.zzauq);
                        zza.zznw = ((zzxd)v2_2);
                    }

                    __monitor_exit(v3);
                    return v2_2;
                label_25:
                    __monitor_exit(v3);
                }
                catch(Throwable v2_1) {
                    goto label_25;
                }

                throw v2_1;
            }

            return v2_2;
        label_30:
            return zza.zza(zza.zzauq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001", new Object[]{"zznr", "zzauo", "zzaup"});
        label_47:
            return new zza();
        }

        static zza zzmg() {
            return zza.zzauq;
        }
    }

    public final class com.google.android.gms.internal.measurement.zzfq$zzb extends zzvm implements zzwv {
        public final class com.google.android.gms.internal.measurement.zzfq$zzb$zza extends com.google.android.gms.internal.measurement.zzvm$zza implements zzwv {
            com.google.android.gms.internal.measurement.zzfq$zzb$zza(zzfr arg1) {
                this();
            }

            private com.google.android.gms.internal.measurement.zzfq$zzb$zza() {
                super(com.google.android.gms.internal.measurement.zzfq$zzb.zzmh());
            }
        }

        public enum com.google.android.gms.internal.measurement.zzfq$zzb$zzb implements zzvp {
            private final int value;
            private static final enum com.google.android.gms.internal.measurement.zzfq$zzb$zzb zzauu;
            private static final enum com.google.android.gms.internal.measurement.zzfq$zzb$zzb zzauv;
            private static final zzvq zzoa;

            static {
                com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauu = new com.google.android.gms.internal.measurement.zzfq$zzb$zzb("RADS", 0, 1);
                com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauv = new com.google.android.gms.internal.measurement.zzfq$zzb$zzb("PROVISIONING", 1, 2);
                com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauw = new com.google.android.gms.internal.measurement.zzfq$zzb$zzb[]{com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauu, com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauv};
                com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzoa = new zzfs();
            }

            private com.google.android.gms.internal.measurement.zzfq$zzb$zzb(String arg1, int arg2, int arg3) {
                super(arg1, arg2);
                this.value = arg3;
            }

            public static com.google.android.gms.internal.measurement.zzfq$zzb$zzb[] values() {
                // Method was not decompiled
            }

            public final int zzc() {
                return this.value;
            }

            public static zzvr zzd() {
                return zzft.zzoc;
            }

            public static com.google.android.gms.internal.measurement.zzfq$zzb$zzb zzs(int arg0) {
                switch(arg0) {
                    case 1: {
                        goto label_5;
                    }
                    case 2: {
                        goto label_3;
                    }
                }

                return null;
            label_3:
                return com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauv;
            label_5:
                return com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzauu;
            }
        }

        private int zzaur;
        private zzvs zzaus;
        private static final com.google.android.gms.internal.measurement.zzfq$zzb zzaut;
        private int zznr;
        private static volatile zzxd zznw;

        static {
            com.google.android.gms.internal.measurement.zzfq$zzb.zzaut = new com.google.android.gms.internal.measurement.zzfq$zzb();
            zzvm.zza(com.google.android.gms.internal.measurement.zzfq$zzb.class, com.google.android.gms.internal.measurement.zzfq$zzb.zzaut);
        }

        private com.google.android.gms.internal.measurement.zzfq$zzb() {
            super();
            this.zzaur = 1;
            this.zzaus = com.google.android.gms.internal.measurement.zzfq$zzb.zzwc();
        }

        public static zzxd zza() {
            return com.google.android.gms.internal.measurement.zzfq$zzb.zzaut.zza(zze.zzbyz, null, null);
        }

        protected final Object zza(int arg2, Object arg3, Object arg4) {
            arg3 = null;
            switch(zzfr.zznq[arg2 - 1]) {
                case 1: {
                    goto label_53;
                }
                case 2: {
                    goto label_50;
                }
                case 3: {
                    goto label_30;
                }
                case 4: {
                    goto label_28;
                }
                case 5: {
                    goto label_12;
                }
                case 6: {
                    goto label_10;
                }
                case 7: {
                    return arg3;
                }
            }

            throw new UnsupportedOperationException();
        label_50:
            return new com.google.android.gms.internal.measurement.zzfq$zzb$zza(((zzfr)arg3));
        label_53:
            return new com.google.android.gms.internal.measurement.zzfq$zzb();
        label_10:
            return Byte.valueOf(1);
        label_28:
            return com.google.android.gms.internal.measurement.zzfq$zzb.zzaut;
        label_12:
            zzxd v2 = com.google.android.gms.internal.measurement.zzfq$zzb.zznw;
            if(v2 == null) {
                Class v3 = com.google.android.gms.internal.measurement.zzfq$zzb.class;
                __monitor_enter(v3);
                try {
                    v2 = com.google.android.gms.internal.measurement.zzfq$zzb.zznw;
                    if(v2 == null) {
                        zzb v2_2 = new zzb(com.google.android.gms.internal.measurement.zzfq$zzb.zzaut);
                        com.google.android.gms.internal.measurement.zzfq$zzb.zznw = ((zzxd)v2_2);
                    }

                    __monitor_exit(v3);
                    return v2;
                label_25:
                    __monitor_exit(v3);
                }
                catch(Throwable v2_1) {
                    goto label_25;
                }

                throw v2_1;
            }

            return v2;
        label_30:
            return com.google.android.gms.internal.measurement.zzfq$zzb.zza(com.google.android.gms.internal.measurement.zzfq$zzb.zzaut, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u001B", new Object[]{"zznr", "zzaur", com.google.android.gms.internal.measurement.zzfq$zzb$zzb.zzd(), "zzaus", zza.class});
        }

        static com.google.android.gms.internal.measurement.zzfq$zzb zzmh() {
            return com.google.android.gms.internal.measurement.zzfq$zzb.zzaut;
        }
    }

}

