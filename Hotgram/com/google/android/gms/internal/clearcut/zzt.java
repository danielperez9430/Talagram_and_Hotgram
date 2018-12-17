package com.google.android.gms.internal.clearcut;

public final class zzt {
    public final class zza extends zzcg implements zzdq {
        public final class com.google.android.gms.internal.clearcut.zzt$zza$zza extends com.google.android.gms.internal.clearcut.zzcg$zza implements zzdq {
            com.google.android.gms.internal.clearcut.zzt$zza$zza(zzu arg1) {
                this();
            }

            private com.google.android.gms.internal.clearcut.zzt$zza$zza() {
                super(zza.zzb());
            }
        }

        public enum zzb implements zzcj {
            private final int value;
            private static final enum zzb zzbh;
            private static final enum zzb zzbi;
            private static final enum zzb zzbj;
            private static final enum zzb zzbk;
            private static final enum zzb zzbl;
            private static final enum zzb zzbm;
            private static final enum zzb zzbn;
            private static final enum zzb zzbo;
            private static final enum zzb zzbp;
            private static final zzck zzbq;

            static {
                zzb.zzbh = new zzb("ARCH_UNKNOWN", 0, 0);
                zzb.zzbi = new zzb("ARCH_NON_NATIVE", 1, 1);
                zzb.zzbj = new zzb("ARCH_ARMV5", 2, 2);
                zzb.zzbk = new zzb("ARCH_ARMV7", 3, 4);
                zzb.zzbl = new zzb("ARCH_ARM64", 4, 5);
                zzb.zzbm = new zzb("ARCH_MIPS", 5, 6);
                zzb.zzbn = new zzb("ARCH_MIPS_64", 6, 7);
                zzb.zzbo = new zzb("ARCH_X86", 7, 8);
                zzb.zzbp = new zzb("ARCH_X86_64", 8, 9);
                zzb.zzbr = new zzb[]{zzb.zzbh, zzb.zzbi, zzb.zzbj, zzb.zzbk, zzb.zzbl, zzb.zzbm, zzb.zzbn, zzb.zzbo, zzb.zzbp};
                zzb.zzbq = new zzv();
            }

            private zzb(String arg1, int arg2, int arg3) {
                super(arg1, arg2);
                this.value = arg3;
            }

            public static zzb[] values() {
                // Method was not decompiled
            }

            public static zzb zza(int arg0) {
                switch(arg0) {
                    case 0: {
                        goto label_19;
                    }
                    case 1: {
                        goto label_17;
                    }
                    case 2: {
                        goto label_15;
                    }
                    case 4: {
                        goto label_13;
                    }
                    case 5: {
                        goto label_11;
                    }
                    case 6: {
                        goto label_9;
                    }
                    case 7: {
                        goto label_7;
                    }
                    case 8: {
                        goto label_5;
                    }
                    case 9: {
                        goto label_3;
                    }
                }

                return null;
            label_17:
                return zzb.zzbi;
            label_19:
                return zzb.zzbh;
            label_3:
                return zzb.zzbp;
            label_5:
                return zzb.zzbo;
            label_7:
                return zzb.zzbn;
            label_9:
                return zzb.zzbm;
            label_11:
                return zzb.zzbl;
            label_13:
                return zzb.zzbk;
            label_15:
                return zzb.zzbj;
            }

            public final int zzc() {
                return this.value;
            }

            public static zzck zzd() {
                return zzb.zzbq;
            }
        }

        public enum zzc implements zzcj {
            private final int value;
            private static final zzck zzbq;
            private static final enum zzc zzbs;
            private static final enum zzc zzbt;
            private static final enum zzc zzbu;
            private static final enum zzc zzbv;
            private static final enum zzc zzbw;
            private static final enum zzc zzbx;
            private static final enum zzc zzby;
            private static final enum zzc zzbz;
            private static final enum zzc zzca;
            private static final enum zzc zzcb;
            private static final enum zzc zzcc;
            private static final enum zzc zzcd;

            static {
                zzc.zzbs = new zzc("BUILD_TYPE_UNKNOWN", 0, 0);
                zzc.zzbt = new zzc("BUILD_TYPE_PROD", 1, 1);
                zzc.zzbu = new zzc("BUILD_TYPE_INTERNAL", 2, 2);
                zzc.zzbv = new zzc("BUILD_TYPE_PRODLMP", 3, 3);
                zzc.zzbw = new zzc("BUILD_TYPE_THINGS", 4, 4);
                zzc.zzbx = new zzc("BUILD_TYPE_PRODMNC", 5, 5);
                zzc.zzby = new zzc("BUILD_TYPE_WEARABLE", 6, 6);
                zzc.zzbz = new zzc("BUILD_TYPE_AUTO", 7, 7);
                zzc.zzca = new zzc("BUILD_TYPE_SIDEWINDERMNC", 8, 8);
                zzc.zzcb = new zzc("BUILD_TYPE_ATV", 9, 9);
                zzc.zzcc = new zzc("BUILD_TYPE_PRODPIX", 10, 10);
                zzc.zzcd = new zzc("BUILD_TYPE_PRODPI", 11, 11);
                zzc.zzce = new zzc[]{zzc.zzbs, zzc.zzbt, zzc.zzbu, zzc.zzbv, zzc.zzbw, zzc.zzbx, zzc.zzby, zzc.zzbz, zzc.zzca, zzc.zzcb, zzc.zzcc, zzc.zzcd};
                zzc.zzbq = new zzw();
            }

            private zzc(String arg1, int arg2, int arg3) {
                super(arg1, arg2);
                this.value = arg3;
            }

            public static zzc[] values() {
                // Method was not decompiled
            }

            public static zzc zzc(int arg0) {
                switch(arg0) {
                    case 0: {
                        goto label_25;
                    }
                    case 1: {
                        goto label_23;
                    }
                    case 2: {
                        goto label_21;
                    }
                    case 3: {
                        goto label_19;
                    }
                    case 4: {
                        goto label_17;
                    }
                    case 5: {
                        goto label_15;
                    }
                    case 6: {
                        goto label_13;
                    }
                    case 7: {
                        goto label_11;
                    }
                    case 8: {
                        goto label_9;
                    }
                    case 9: {
                        goto label_7;
                    }
                    case 10: {
                        goto label_5;
                    }
                    case 11: {
                        goto label_3;
                    }
                }

                return null;
            label_3:
                return zzc.zzcd;
            label_5:
                return zzc.zzcc;
            label_7:
                return zzc.zzcb;
            label_9:
                return zzc.zzca;
            label_11:
                return zzc.zzbz;
            label_13:
                return zzc.zzby;
            label_15:
                return zzc.zzbx;
            label_17:
                return zzc.zzbw;
            label_19:
                return zzc.zzbv;
            label_21:
                return zzc.zzbu;
            label_23:
                return zzc.zzbt;
            label_25:
                return zzc.zzbs;
            }

            public final int zzc() {
                return this.value;
            }

            public static zzck zzd() {
                return zzc.zzbq;
            }
        }

        public enum zzd implements zzcj {
            private final int value;
            private static final zzck zzbq;
            private static final enum zzd zzcf;
            private static final enum zzd zzcg;
            private static final enum zzd zzch;
            private static final enum zzd zzci;
            private static final enum zzd zzcj;
            private static final enum zzd zzck;
            private static final enum zzd zzcl;
            private static final enum zzd zzcm;
            private static final enum zzd zzcn;
            private static final enum zzd zzco;

            static {
                zzd.zzcf = new zzd("DENSITY_UNKNOWN", 0, 0);
                zzd.zzcg = new zzd("DENSITY_ALLDPI", 1, 1);
                zzd.zzch = new zzd("DENSITY_LDPI", 2, 2);
                zzd.zzci = new zzd("DENSITY_MDPI", 3, 3);
                zzd.zzcj = new zzd("DENSITY_TVDPI", 4, 4);
                zzd.zzck = new zzd("DENSITY_HDPI", 5, 5);
                zzd.zzcl = new zzd("DENSITY_XHDPI", 6, 7);
                zzd.zzcm = new zzd("DENSITY_DPI400", 7, 8);
                zzd.zzcn = new zzd("DENSITY_XXHDPI", 8, 9);
                zzd.zzco = new zzd("DENSITY_XXXHDPI", 9, 10);
                zzd.zzcp = new zzd[]{zzd.zzcf, zzd.zzcg, zzd.zzch, zzd.zzci, zzd.zzcj, zzd.zzck, zzd.zzcl, zzd.zzcm, zzd.zzcn, zzd.zzco};
                zzd.zzbq = new zzx();
            }

            private zzd(String arg1, int arg2, int arg3) {
                super(arg1, arg2);
                this.value = arg3;
            }

            public static zzd[] values() {
                // Method was not decompiled
            }

            public final int zzc() {
                return this.value;
            }

            public static zzck zzd() {
                return zzd.zzbq;
            }

            public static zzd zzd(int arg0) {
                switch(arg0) {
                    case 0: {
                        goto label_21;
                    }
                    case 1: {
                        goto label_19;
                    }
                    case 2: {
                        goto label_17;
                    }
                    case 3: {
                        goto label_15;
                    }
                    case 4: {
                        goto label_13;
                    }
                    case 5: {
                        goto label_11;
                    }
                    case 7: {
                        goto label_9;
                    }
                    case 8: {
                        goto label_7;
                    }
                    case 9: {
                        goto label_5;
                    }
                    case 10: {
                        goto label_3;
                    }
                }

                return null;
            label_17:
                return zzd.zzch;
            label_19:
                return zzd.zzcg;
            label_3:
                return zzd.zzco;
            label_21:
                return zzd.zzcf;
            label_5:
                return zzd.zzcn;
            label_7:
                return zzd.zzcm;
            label_9:
                return zzd.zzcl;
            label_11:
                return zzd.zzck;
            label_13:
                return zzd.zzcj;
            label_15:
                return zzd.zzci;
            }
        }

        private int zzbb;
        private int zzbc;
        private int zzbd;
        private int zzbe;
        private static final zza zzbf;
        private static volatile zzdz zzbg;

        static {
            zza.zzbf = new zza();
            zzcg.zza(zza.class, zza.zzbf);
        }

        private zza() {
            super();
        }

        protected final Object zza(int arg2, Object arg3, Object arg4) {
            com.google.android.gms.internal.clearcut.zzcg$zzb v2_2;
            arg3 = null;
            switch(zzu.zzba[arg2 - 1]) {
                case 1: {
                    goto label_59;
                }
                case 2: {
                    goto label_56;
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
        label_56:
            return new com.google.android.gms.internal.clearcut.zzt$zza$zza(((zzu)arg3));
        label_10:
            return Byte.valueOf(1);
        label_59:
            return new zza();
        label_28:
            return zza.zzbf;
        label_12:
            zzdz v2 = zza.zzbg;
            if(v2 == null) {
                Class v3 = zza.class;
                __monitor_enter(v3);
                try {
                    v2 = zza.zzbg;
                    if(v2 == null) {
                        v2_2 = new com.google.android.gms.internal.clearcut.zzcg$zzb(zza.zzbf);
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
            return zza.zza(zza.zzbf, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\f\u0002", new Object[]{"zzbb", "zzbc", zzc.zzd(), "zzbd", zzb.zzd(), "zzbe", zzd.zzd()});
        }

        static zza zzb() {
            return zza.zzbf;
        }
    }

}

