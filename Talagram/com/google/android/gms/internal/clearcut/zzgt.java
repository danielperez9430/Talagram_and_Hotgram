package com.google.android.gms.internal.clearcut;

public final class zzgt {
    public final class zza extends zzcg implements zzdq {
        public final class com.google.android.gms.internal.clearcut.zzgt$zza$zza extends com.google.android.gms.internal.clearcut.zzcg$zza implements zzdq {
            com.google.android.gms.internal.clearcut.zzgt$zza$zza(zzgu arg1) {
                this();
            }

            private com.google.android.gms.internal.clearcut.zzgt$zza$zza() {
                super(zza.zzfr());
            }
        }

        public enum zzb implements zzcj {
            private final int value;
            private static final enum zzb zzbim;
            private static final enum zzb zzbin;
            private static final enum zzb zzbio;
            private static final zzck zzbq;

            static {
                zzb.zzbim = new zzb("NO_RESTRICTION", 0, 0);
                zzb.zzbin = new zzb("SIDEWINDER_DEVICE", 1, 1);
                zzb.zzbio = new zzb("LATCHSKY_DEVICE", 2, 2);
                zzb.zzbip = new zzb[]{zzb.zzbim, zzb.zzbin, zzb.zzbio};
                zzb.zzbq = new zzgv();
            }

            private zzb(String arg1, int arg2, int arg3) {
                super(arg1, arg2);
                this.value = arg3;
            }

            public static zzb[] values() {
                // Method was not decompiled
            }

            public static zzb zzbe(int arg0) {
                switch(arg0) {
                    case 0: {
                        goto label_7;
                    }
                    case 1: {
                        goto label_5;
                    }
                    case 2: {
                        goto label_3;
                    }
                }

                return null;
            label_3:
                return zzb.zzbio;
            label_5:
                return zzb.zzbin;
            label_7:
                return zzb.zzbim;
            }

            public final int zzc() {
                return this.value;
            }

            public static zzck zzd() {
                return zzb.zzbq;
            }
        }

        private static volatile zzdz zzbg;
        private static final zza zzbil;

        static {
            zza.zzbil = new zza();
            zzcg.zza(zza.class, zza.zzbil);
        }

        private zza() {
            super();
        }

        protected final Object zza(int arg1, Object arg2, Object arg3) {
            arg2 = null;
            switch(zzgu.zzba[arg1 - 1]) {
                case 1: {
                    goto label_37;
                }
                case 2: {
                    goto label_34;
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
                    return arg2;
                }
            }

            throw new UnsupportedOperationException();
        label_34:
            return new com.google.android.gms.internal.clearcut.zzgt$zza$zza(((zzgu)arg2));
        label_37:
            return new zza();
        label_10:
            return Byte.valueOf(1);
        label_28:
            return zza.zzbil;
        label_12:
            zzdz v1 = zza.zzbg;
            if(v1 == null) {
                Class v2 = zza.class;
                __monitor_enter(v2);
                try {
                    v1 = zza.zzbg;
                    if(v1 == null) {
                        com.google.android.gms.internal.clearcut.zzcg$zzb v1_2 = new com.google.android.gms.internal.clearcut.zzcg$zzb(zza.zzbil);
                        zza.zzbg = ((zzdz)v1_2);
                    }

                    __monitor_exit(v2);
                    return v1;
                label_25:
                    __monitor_exit(v2);
                }
                catch(Throwable v1_1) {
                    goto label_25;
                }

                throw v1_1;
            }

            return v1;
        label_30:
            return zza.zza(zza.zzbil, "\u0001\u0000", ((Object[])arg2));
        }

        static zza zzfr() {
            return zza.zzbil;
        }
    }

}

