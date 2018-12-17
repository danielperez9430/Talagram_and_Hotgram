package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzsl;
import com.google.android.gms.internal.measurement.zzsv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@VisibleForTesting public final class zzaf {
    @VisibleForTesting public final class zza {
        private final Object zzaan;
        private zzsl zzall;
        private final Object zzalm;
        private volatile Object zzaln;
        private final String zzoj;

        private zza(String arg1, Object arg2, Object arg3) {
            super();
            this.zzoj = arg1;
            this.zzaan = arg2;
            this.zzalm = arg3;
        }

        public final Object get() {
            if(zzaf.zzaiq == null) {
                return this.zzaan;
            }

            if(zzk.isMainThread()) {
                if(this.zzaln == null) {
                    return this.zzaan;
                }

                return this.zzaln;
            }

            zza.zzix();
            try {
                return this.zzall.get();
            }
            catch(SecurityException v0) {
                zzaf.zza(((Exception)v0));
                return this.zzall.getDefaultValue();
            }
        }

        public final Object get(Object arg1) {
            if(arg1 != null) {
                return arg1;
            }

            if(zzaf.zzaiq == null) {
                return this.zzaan;
            }

            if(zzk.isMainThread()) {
                if(this.zzaln == null) {
                    return this.zzaan;
                }

                return this.zzaln;
            }

            zza.zzix();
            try {
                return this.zzall.get();
            }
            catch(SecurityException v1) {
                zzaf.zza(((Exception)v1));
                return this.zzall.getDefaultValue();
            }
        }

        public final String getKey() {
            return this.zzoj;
        }

        static zza zza(String arg0, double arg1, double arg3) {
            zza v1 = new zza(arg0, Double.valueOf(-3), Double.valueOf(-3));
            zzaf.zzaiv.add(v1);
            return v1;
        }

        static zza zzb(String arg1, boolean arg2, boolean arg3) {
            zza v0 = new zza(arg1, Boolean.valueOf(arg2), Boolean.valueOf(arg3));
            zzaf.zzait.add(v0);
            return v0;
        }

        static zza zzb(String arg1, long arg2, long arg4) {
            zza v0 = new zza(arg1, Long.valueOf(arg2), Long.valueOf(arg4));
            zzaf.zzais.add(v0);
            return v0;
        }

        static zza zzc(String arg1, int arg2, int arg3) {
            zza v0 = new zza(arg1, Integer.valueOf(arg2), Integer.valueOf(arg3));
            zzaf.zzair.add(v0);
            return v0;
        }

        static zza zzd(String arg1, String arg2, String arg3) {
            zza v0 = new zza(arg1, arg2, arg3);
            zzaf.zzaiu.add(v0);
            return v0;
        }

        private static void zzix() {
            Object v2;
            Class v0 = zza.class;
            __monitor_enter(v0);
            try {
                if(zzk.isMainThread()) {
                    goto label_54;
                }

                try {
                    Iterator v1_2 = zzaf.zzait.iterator();
                    while(v1_2.hasNext()) {
                        v2 = v1_2.next();
                        ((zza)v2).zzaln = ((zza)v2).zzall.get();
                    }

                    v1_2 = zzaf.zzaiu.iterator();
                    while(v1_2.hasNext()) {
                        v2 = v1_2.next();
                        ((zza)v2).zzaln = ((zza)v2).zzall.get();
                    }

                    v1_2 = zzaf.zzais.iterator();
                    while(v1_2.hasNext()) {
                        v2 = v1_2.next();
                        ((zza)v2).zzaln = ((zza)v2).zzall.get();
                    }

                    v1_2 = zzaf.zzair.iterator();
                    while(v1_2.hasNext()) {
                        v2 = v1_2.next();
                        ((zza)v2).zzaln = ((zza)v2).zzall.get();
                    }

                    v1_2 = zzaf.zzaiv.iterator();
                    while(true) {
                        if(!v1_2.hasNext()) {
                            goto label_52;
                        }

                        v2 = v1_2.next();
                        ((zza)v2).zzaln = ((zza)v2).zzall.get();
                    }
                }
                catch(SecurityException v1_1) {
                    try {
                        zzaf.zza(((Exception)v1_1));
                    label_52:
                        __monitor_exit(v0);
                        return;
                    label_54:
                        throw new IllegalStateException("Tried to refresh flag cache on main thread or on package side.");
                    label_59:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_59;
                    }
                }
            }
            catch(Throwable v1) {
                goto label_59;
            }

            throw v1;
        }

        static void zziy() {
            zza.zzq();
        }

        private static void zzq() {
            Object v2;
            Class v0 = zza.class;
            __monitor_enter(v0);
            try {
                Iterator v1_1 = zzaf.zzait.iterator();
                while(v1_1.hasNext()) {
                    v2 = v1_1.next();
                    ((zza)v2).zzall = zzaf.zziw().zzf(((zza)v2).zzoj, ((zza)v2).zzaan.booleanValue());
                }

                v1_1 = zzaf.zzaiu.iterator();
                while(v1_1.hasNext()) {
                    v2 = v1_1.next();
                    ((zza)v2).zzall = zzaf.zziw().zzx(((zza)v2).zzoj, ((zza)v2).zzaan);
                }

                v1_1 = zzaf.zzais.iterator();
                while(v1_1.hasNext()) {
                    v2 = v1_1.next();
                    ((zza)v2).zzall = zzaf.zziw().zze(((zza)v2).zzoj, ((zza)v2).zzaan.longValue());
                }

                v1_1 = zzaf.zzair.iterator();
                while(v1_1.hasNext()) {
                    v2 = v1_1.next();
                    ((zza)v2).zzall = zzaf.zziw().zzd(((zza)v2).zzoj, ((zza)v2).zzaan.intValue());
                }

                v1_1 = zzaf.zzaiv.iterator();
                while(v1_1.hasNext()) {
                    v2 = v1_1.next();
                    ((zza)v2).zzall = zzaf.zziw().zzb(((zza)v2).zzoj, ((zza)v2).zzaan.doubleValue());
                }

                __monitor_exit(v0);
                return;
            label_69:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_69;
            }

            throw v1;
        }
    }

    private static volatile zzbt zzadj;
    static zzk zzaiq;
    static List zzair;
    static List zzais;
    static List zzait;
    static List zzaiu;
    static List zzaiv;
    private static final zzsv zzaiw;
    @VisibleForTesting private static Boolean zzaix;
    private static zza zzaiy;
    private static zza zzaiz;
    private static zza zzaja;
    public static zza zzajb;
    public static zza zzajc;
    public static zza zzajd;
    public static zza zzaje;
    public static zza zzajf;
    public static zza zzajg;
    public static zza zzajh;
    public static zza zzaji;
    public static zza zzajj;
    public static zza zzajk;
    public static zza zzajl;
    public static zza zzajm;
    public static zza zzajn;
    public static zza zzajo;
    public static zza zzajp;
    public static zza zzajq;
    public static zza zzajr;
    public static zza zzajs;
    public static zza zzajt;
    public static zza zzaju;
    public static zza zzajv;
    public static zza zzajw;
    public static zza zzajx;
    public static zza zzajy;
    public static zza zzajz;
    public static zza zzaka;
    public static zza zzakb;
    public static zza zzakc;
    public static zza zzakd;
    public static zza zzake;
    public static zza zzakf;
    public static zza zzakg;
    public static zza zzakh;
    public static zza zzaki;
    public static zza zzakj;
    public static zza zzakk;
    public static zza zzakl;
    public static zza zzakm;
    public static zza zzakn;
    public static zza zzako;
    public static zza zzakp;
    public static zza zzakq;
    public static zza zzakr;
    public static zza zzaks;
    public static zza zzakt;
    public static zza zzaku;
    public static zza zzakv;
    public static zza zzakw;
    public static zza zzakx;
    public static zza zzaky;
    public static zza zzakz;
    public static zza zzala;
    public static zza zzalb;
    public static zza zzalc;
    public static zza zzald;
    public static zza zzale;
    public static zza zzalf;
    private static zza zzalg;
    public static zza zzalh;
    private static zza zzali;
    public static zza zzalj;
    public static zza zzalk;

    static {
        zzaf.zzair = new ArrayList();
        zzaf.zzais = new ArrayList();
        zzaf.zzait = new ArrayList();
        zzaf.zzaiu = new ArrayList();
        zzaf.zzaiv = new ArrayList();
        String v2 = "content://com.google.android.gms.phenotype/";
        String v1 = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
        v1 = v1.length() != 0 ? v2.concat(v1) : new String(v2);
        zzaf.zzaiw = new zzsv(Uri.parse(v1));
        zzaf.zzaiy = zza.zzb("measurement.log_third_party_store_events_enabled", false, false);
        zzaf.zzaiz = zza.zzb("measurement.log_installs_enabled", false, false);
        zzaf.zzaja = zza.zzb("measurement.log_upgrades_enabled", false, false);
        zzaf.zzajb = zza.zzb("measurement.log_androidId_enabled", false, false);
        zzaf.zzajc = zza.zzb("measurement.upload_dsid_enabled", false, false);
        zzaf.zzajd = zza.zzd("measurement.log_tag", "FA", "FA-SVC");
        zzaf.zzaje = zza.zzb("measurement.ad_id_cache_time", 10000, 10000);
        zzaf.zzajf = zza.zzb("measurement.monitoring.sample_period_millis", 86400000, 86400000);
        zzaf.zzajg = zza.zzb("measurement.config.cache_time", 86400000, 3600000);
        zzaf.zzajh = zza.zzd("measurement.config.url_scheme", "https", "https");
        zzaf.zzaji = zza.zzd("measurement.config.url_authority", "app-measurement.com", "app-measurement.com");
        zzaf.zzajj = zza.zzc("measurement.upload.max_bundles", 100, 100);
        zzaf.zzajk = zza.zzc("measurement.upload.max_batch_size", 65536, 65536);
        zzaf.zzajl = zza.zzc("measurement.upload.max_bundle_size", 65536, 65536);
        zzaf.zzajm = zza.zzc("measurement.upload.max_events_per_bundle", 1000, 1000);
        zzaf.zzajn = zza.zzc("measurement.upload.max_events_per_day", 100000, 100000);
        zzaf.zzajo = zza.zzc("measurement.upload.max_error_events_per_day", 1000, 1000);
        zzaf.zzajp = zza.zzc("measurement.upload.max_public_events_per_day", 50000, 50000);
        zzaf.zzajq = zza.zzc("measurement.upload.max_conversions_per_day", 500, 500);
        zzaf.zzajr = zza.zzc("measurement.upload.max_realtime_events_per_day", 10, 10);
        zzaf.zzajs = zza.zzc("measurement.store.max_stored_events_per_app", 100000, 100000);
        zzaf.zzajt = zza.zzd("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a");
        zzaf.zzaju = zza.zzb("measurement.upload.backoff_period", 43200000, 43200000);
        zzaf.zzajv = zza.zzb("measurement.upload.window_interval", 3600000, 3600000);
        zzaf.zzajw = zza.zzb("measurement.upload.interval", 3600000, 3600000);
        zzaf.zzajx = zza.zzb("measurement.upload.realtime_upload_interval", 10000, 10000);
        zzaf.zzajy = zza.zzb("measurement.upload.debug_upload_interval", 1000, 1000);
        zzaf.zzajz = zza.zzb("measurement.upload.minimum_delay", 500, 500);
        zzaf.zzaka = zza.zzb("measurement.alarm_manager.minimum_interval", 60000, 60000);
        zzaf.zzakb = zza.zzb("measurement.upload.stale_data_deletion_interval", 86400000, 86400000);
        zzaf.zzakc = zza.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
        zzaf.zzakd = zza.zzb("measurement.upload.initial_upload_delay_time", 15000, 15000);
        zzaf.zzake = zza.zzb("measurement.upload.retry_time", 1800000, 1800000);
        zzaf.zzakf = zza.zzc("measurement.upload.retry_count", 6, 6);
        zzaf.zzakg = zza.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
        zzaf.zzakh = zza.zzc("measurement.lifetimevalue.max_currency_tracked", 4, 4);
        zzaf.zzaki = zza.zzc("measurement.audience.filter_result_max_count", 200, 200);
        zzaf.zzakj = zza.zzb("measurement.service_client.idle_disconnect_millis", 5000, 5000);
        zzaf.zzakk = zza.zzb("measurement.test.boolean_flag", false, false);
        zzaf.zzakl = zza.zzd("measurement.test.string_flag", "---", "---");
        zzaf.zzakm = zza.zzb("measurement.test.long_flag", -1, -1);
        zzaf.zzakn = zza.zzc("measurement.test.int_flag", -2, -2);
        zzaf.zzako = zza.zza("measurement.test.double_flag", -3, -3);
        zzaf.zzakp = zza.zzb("measurement.lifetimevalue.user_engagement_tracking_enabled", false, false);
        zzaf.zzakq = zza.zzb("measurement.audience.complex_param_evaluation", false, false);
        zzaf.zzakr = zza.zzb("measurement.validation.internal_limits_internal_event_params", false, false);
        zzaf.zzaks = zza.zzb("measurement.quality.unsuccessful_update_retry_counter", false, false);
        zzaf.zzakt = zza.zzb("measurement.iid.disable_on_collection_disabled", true, true);
        zzaf.zzaku = zza.zzb("measurement.app_launch.call_only_when_enabled", true, true);
        zzaf.zzakv = zza.zzb("measurement.run_on_worker_inline", true, false);
        zzaf.zzakw = zza.zzb("measurement.audience.dynamic_filters", false, false);
        zzaf.zzakx = zza.zzb("measurement.reset_analytics.persist_time", false, false);
        zzaf.zzaky = zza.zzb("measurement.validation.value_and_currency_params", false, false);
        zzaf.zzakz = zza.zzb("measurement.sampling.time_zone_offset_enabled", false, false);
        zzaf.zzala = zza.zzb("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", false, false);
        zzaf.zzalb = zza.zzb("measurement.disconnect_from_remote_service", false, false);
        zzaf.zzalc = zza.zzb("measurement.clear_local_database", false, false);
        zzaf.zzald = zza.zzb("measurement.fetch_config_with_admob_app_id", false, false);
        zzaf.zzale = zza.zzb("measurement.sessions.session_id_enabled", false, false);
        zzaf.zzalf = zza.zzb("measurement.sessions.immediate_start_enabled", false, false);
        zzaf.zzalg = zza.zzb("measurement.sessions.background_sessions_enabled", false, false);
        zzaf.zzalh = zza.zzb("measurement.collection.firebase_global_collection_flag_enabled", true, true);
        zzaf.zzali = zza.zzb("measurement.collection.efficient_engagement_reporting_enabled", false, false);
        zzaf.zzalj = zza.zzb("measurement.personalized_ads_feature_enabled", false, false);
        zzaf.zzalk = zza.zzb("measurement.remove_app_instance_id_cache_enabled", true, true);
    }

    static void zza(zzbt arg0) {
        zzaf.zzadj = arg0;
    }

    static void zza(zzk arg0) {
        zzaf.zzaiq = arg0;
        zza.zziy();
    }

    @VisibleForTesting static void zza(Exception arg3) {
        if(zzaf.zzadj == null) {
            return;
        }

        Context v0 = zzaf.zzadj.getContext();
        if(zzaf.zzaix == null) {
            boolean v0_1 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(v0, 12451000) == 0 ? true : false;
            zzaf.zzaix = Boolean.valueOf(v0_1);
        }

        if(zzaf.zzaix.booleanValue()) {
            zzaf.zzadj.zzgo().zzjd().zzg("Got Exception on PhenotypeFlag.get on Play device", arg3);
        }
    }

    static zzsv zziw() {
        return zzaf.zzaiw;
    }
}

