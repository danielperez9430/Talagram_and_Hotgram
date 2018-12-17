package com.google.android.gms.internal.clearcut;

public final class zzap {
    public final class zza extends zzcg implements zzdq {
        public final class com.google.android.gms.internal.clearcut.zzap$zza$zza extends com.google.android.gms.internal.clearcut.zzcg$zza implements zzdq {
            com.google.android.gms.internal.clearcut.zzap$zza$zza(zzaq arg1) {
                this();
            }

            private com.google.android.gms.internal.clearcut.zzap$zza$zza() {
                super(zza.zzq());
            }
        }

        public enum zzb implements zzcj {
            private final int value;
            private static final zzck zzbq;
            private static final enum zzb zzet;
            private static final enum zzb zzeu;
            private static final enum zzb zzev;

            static {
                zzb.zzet = new zzb("UNKNOWN", 0, 0);
                zzb.zzeu = new zzb("ON", 1, 1);
                zzb.zzev = new zzb("OFF", 2, 2);
                zzb.zzew = new zzb[]{zzb.zzet, zzb.zzeu, zzb.zzev};
                zzb.zzbq = new zzar();
            }

            private zzb(String arg1, int arg2, int arg3) {
                super(arg1, arg2);
                this.value = arg3;
            }

            public static zzb[] values() {
                // Method was not decompiled
            }

            public final int zzc() {
                return this.value;
            }

            public static zzck zzd() {
                return zzb.zzbq;
            }

            public static zzb zze(int arg0) {
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
                return zzb.zzev;
            label_5:
                return zzb.zzeu;
            label_7:
                return zzb.zzet;
            }
        }

        private int zzbb;
        private static volatile zzdz zzbg;
        private int zzel;
        private int zzem;
        private int zzen;
        private int zzeo;
        private int zzep;
        private int zzeq;
        private int zzer;
        private static final zza zzes;

        static {
            zza.zzes = new zza();
            zzcg.zza(zza.class, zza.zzes);
        }

        private zza() {
            super();
        }

        protected final Object zza(int arg2, Object arg3, Object arg4) {
            com.google.android.gms.internal.clearcut.zzcg$zzb v2_2;
            arg3 = null;
            switch(zzaq.zzba[arg2 - 1]) {
                case 1: {
                    goto label_83;
                }
                case 2: {
                    goto label_80;
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
        label_83:
            return new zza();
        label_10:
            return Byte.valueOf(1);
        label_28:
            return zza.zzes;
        label_12:
            zzdz v2 = zza.zzbg;
            if(v2 == null) {
                Class v3 = zza.class;
                __monitor_enter(v3);
                try {
                    v2 = zza.zzbg;
                    if(v2 == null) {
                        v2_2 = new com.google.android.gms.internal.clearcut.zzcg$zzb(zza.zzes);
                        zza.zzbg = ((zzdz)v2_2);
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
            return zza.zza(zza.zzes, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\b\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002\u0004\f\u0003\u0005\f\u0004\u0006\f\u0005\u0007\f\u0006", new Object[]{"zzbb", "zzel", zzb.zzd(), "zzem", zzb.zzd(), "zzen", zzb.zzd(), "zzeo", zzb.zzd(), "zzep", zzb.zzd(), "zzeq", zzb.zzd(), "zzer", zzb.zzd()});
        label_80:
            return new com.google.android.gms.internal.clearcut.zzap$zza$zza(((zzaq)arg3));
        }

        static zza zzq() {
            return zza.zzes;
        }
    }

}

