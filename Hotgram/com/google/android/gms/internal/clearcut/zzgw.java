package com.google.android.gms.internal.clearcut;

import java.util.List;

public final class zzgw {
    public final class zza extends zzcg implements zzdq {
        public final class com.google.android.gms.internal.clearcut.zzgw$zza$zza extends com.google.android.gms.internal.clearcut.zzcg$zza implements zzdq {
            com.google.android.gms.internal.clearcut.zzgw$zza$zza(zzgx arg1) {
                this();
            }

            private com.google.android.gms.internal.clearcut.zzgw$zza$zza() {
                super(zza.zzfu());
            }
        }

        public final class zzb extends zzcg implements zzdq {
            public final class com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza extends com.google.android.gms.internal.clearcut.zzcg$zza implements zzdq {
                com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza(zzgx arg1) {
                    this();
                }

                private com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza() {
                    super(zzb.zzga());
                }

                public final com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza zzn(String arg2) {
                    ((com.google.android.gms.internal.clearcut.zzcg$zza)this).zzbf();
                    zzb.zza(this.zzjt, arg2);
                    return this;
                }

                public final com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza zzr(long arg2) {
                    ((com.google.android.gms.internal.clearcut.zzcg$zza)this).zzbf();
                    zzb.zza(this.zzjt, arg2);
                    return this;
                }

                public final com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza zzs(long arg2) {
                    ((com.google.android.gms.internal.clearcut.zzcg$zza)this).zzbf();
                    zzb.zzb(this.zzjt, arg2);
                    return this;
                }
            }

            private int zzbb;
            private static volatile zzdz zzbg;
            private String zzbis;
            private long zzbit;
            private long zzbiu;
            private static final zzb zzbiv;
            private int zzya;

            static {
                zzb.zzbiv = new zzb();
                zzcg.zza(zzb.class, zzb.zzbiv);
            }

            private zzb() {
                super();
                this.zzbis = "";
            }

            public final int getEventCode() {
                return this.zzya;
            }

            static void zza(zzb arg0, long arg1) {
                arg0.zzp(arg1);
            }

            static void zza(zzb arg0, String arg1) {
                arg0.zzm(arg1);
            }

            protected final Object zza(int arg2, Object arg3, Object arg4) {
                com.google.android.gms.internal.clearcut.zzcg$zzb v2_2;
                arg3 = null;
                switch(zzgx.zzba[arg2 - 1]) {
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
                return new com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza(((zzgx)arg3));
            label_53:
                return new zzb();
            label_10:
                return Byte.valueOf(1);
            label_28:
                return zzb.zzbiv;
            label_12:
                zzdz v2 = zzb.zzbg;
                if(v2 == null) {
                    Class v3 = zzb.class;
                    __monitor_enter(v3);
                    try {
                        v2 = zzb.zzbg;
                        if(v2 == null) {
                            v2_2 = new com.google.android.gms.internal.clearcut.zzcg$zzb(zzb.zzbiv);
                            zzb.zzbg = ((zzdz)v2_2);
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
                return zzb.zza(zzb.zzbiv, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0004\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003", new Object[]{"zzbb", "zzya", "zzbis", "zzbit", "zzbiu"});
            }

            static void zzb(zzb arg0, long arg1) {
                arg0.zzq(arg1);
            }

            public final boolean zzfv() {
                if((this.zzbb & 1) == 1) {
                    return 1;
                }

                return 0;
            }

            public final String zzfw() {
                return this.zzbis;
            }

            public final long zzfx() {
                return this.zzbit;
            }

            public final long zzfy() {
                return this.zzbiu;
            }

            public static com.google.android.gms.internal.clearcut.zzgw$zza$zzb$zza zzfz() {
                return zzb.zzbiv.zza(zzg.zzkh, null, null);
            }

            static zzb zzga() {
                return zzb.zzbiv;
            }

            private final void zzm(String arg2) {
                if(arg2 != null) {
                    this.zzbb |= 2;
                    this.zzbis = arg2;
                    return;
                }

                throw new NullPointerException();
            }

            private final void zzp(long arg2) {
                this.zzbb |= 4;
                this.zzbit = arg2;
            }

            private final void zzq(long arg2) {
                this.zzbb |= 8;
                this.zzbiu = arg2;
            }
        }

        private static volatile zzdz zzbg;
        private zzcn zzbiq;
        private static final zza zzbir;

        static {
            zza.zzbir = new zza();
            zzcg.zza(zza.class, zza.zzbir);
        }

        private zza() {
            super();
            this.zzbiq = zza.zzbb();
        }

        protected final Object zza(int arg2, Object arg3, Object arg4) {
            arg3 = null;
            switch(zzgx.zzba[arg2 - 1]) {
                case 1: {
                    goto label_44;
                }
                case 2: {
                    goto label_41;
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
        label_41:
            return new com.google.android.gms.internal.clearcut.zzgw$zza$zza(((zzgx)arg3));
        label_10:
            return Byte.valueOf(1);
        label_44:
            return new zza();
        label_28:
            return zza.zzbir;
        label_12:
            zzdz v2 = zza.zzbg;
            if(v2 == null) {
                Class v3 = zza.class;
                __monitor_enter(v3);
                try {
                    v2 = zza.zzbg;
                    if(v2 == null) {
                        com.google.android.gms.internal.clearcut.zzcg$zzb v2_2 = new com.google.android.gms.internal.clearcut.zzcg$zzb(zza.zzbir);
                        zza.zzbg = ((zzdz)v2_2);
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
            return zza.zza(zza.zzbir, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0001\u0000\u0001\u001B", new Object[]{"zzbiq", zzb.class});
        }

        public final List zzfs() {
            return this.zzbiq;
        }

        public static zza zzft() {
            return zza.zzbir;
        }

        static zza zzfu() {
            return zza.zzbir;
        }

        public static zza zzi(byte[] arg1) {
            return zzcg.zzb(zza.zzbir, arg1);
        }
    }

}

