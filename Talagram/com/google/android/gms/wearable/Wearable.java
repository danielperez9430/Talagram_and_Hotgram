package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.v4.f.l;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.wearable.internal.zzaa;
import com.google.android.gms.wearable.internal.zzaj;
import com.google.android.gms.wearable.internal.zzao;
import com.google.android.gms.wearable.internal.zzbv;
import com.google.android.gms.wearable.internal.zzbw;
import com.google.android.gms.wearable.internal.zzcj;
import com.google.android.gms.wearable.internal.zzeu;
import com.google.android.gms.wearable.internal.zzez;
import com.google.android.gms.wearable.internal.zzfg;
import com.google.android.gms.wearable.internal.zzfl;
import com.google.android.gms.wearable.internal.zzgi;
import com.google.android.gms.wearable.internal.zzh;
import com.google.android.gms.wearable.internal.zzhq;
import com.google.android.gms.wearable.internal.zzk;
import com.google.android.gms.wearable.internal.zzo;

public class Wearable {
    public final class WearableOptions implements Optional {
        public class Builder {
            private Looper zzac;

            public Builder() {
                super();
            }

            public WearableOptions build() {
                return new WearableOptions(this, null);
            }

            public Builder setLooper(Looper arg1) {
                this.zzac = arg1;
                return this;
            }

            static Looper zza(Builder arg0) {
                return arg0.zzac;
            }
        }

        private final Looper zzac;

        private WearableOptions(Builder arg1) {
            super();
            this.zzac = Builder.zza(arg1);
        }

        WearableOptions(Builder arg1, zzj arg2) {
            this(arg1);
        }

        static Settings zza(WearableOptions arg0) {
            return arg0.zza();
        }

        private final Settings zza() {
            if(this.zzac != null) {
                return new com.google.android.gms.common.api.GoogleApi$Settings$Builder().setLooper(this.zzac).build();
            }

            return Settings.DEFAULT_SETTINGS;
        }
    }

    @Deprecated public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;
    @Deprecated public static final CapabilityApi CapabilityApi;
    @Deprecated public static final ChannelApi ChannelApi;
    @Deprecated public static final DataApi DataApi;
    @Deprecated public static final MessageApi MessageApi;
    @Deprecated public static final NodeApi NodeApi;
    @Deprecated private static final zzi zzaa;
    @Deprecated private static final zzu zzab;
    @Deprecated private static final zzc zzx;
    @Deprecated private static final zza zzy;
    @Deprecated private static final zzf zzz;

    static {
        Wearable.DataApi = new zzbw();
        Wearable.CapabilityApi = new zzo();
        Wearable.MessageApi = new zzeu();
        Wearable.NodeApi = new zzfg();
        Wearable.ChannelApi = new zzaj();
        Wearable.zzx = new zzk();
        Wearable.zzy = new zzh();
        Wearable.zzz = new zzbv();
        Wearable.zzaa = new zzgi();
        Wearable.zzab = new zzhq();
        Wearable.CLIENT_KEY = new ClientKey();
        Wearable.CLIENT_BUILDER = new zzj();
        Wearable.API = new Api("Wearable.API", Wearable.CLIENT_BUILDER, Wearable.CLIENT_KEY);
    }

    private Wearable() {
        super();
    }

    public static CapabilityClient getCapabilityClient(Activity arg2) {
        return new zzaa(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static CapabilityClient getCapabilityClient(Activity arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzaa(arg1, WearableOptions.zza(arg2));
    }

    public static CapabilityClient getCapabilityClient(Context arg2) {
        return new zzaa(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static CapabilityClient getCapabilityClient(Context arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzaa(arg1, WearableOptions.zza(arg2));
    }

    public static ChannelClient getChannelClient(Activity arg2) {
        return new zzao(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static ChannelClient getChannelClient(Activity arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzao(arg1, WearableOptions.zza(arg2));
    }

    public static ChannelClient getChannelClient(Context arg2) {
        return new zzao(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static ChannelClient getChannelClient(Context arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzao(arg1, WearableOptions.zza(arg2));
    }

    public static DataClient getDataClient(Activity arg2) {
        return new zzcj(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static DataClient getDataClient(Activity arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzcj(arg1, WearableOptions.zza(arg2));
    }

    public static DataClient getDataClient(Context arg2) {
        return new zzcj(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static DataClient getDataClient(Context arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzcj(arg1, WearableOptions.zza(arg2));
    }

    public static MessageClient getMessageClient(Activity arg2) {
        return new zzez(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static MessageClient getMessageClient(Activity arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzez(arg1, WearableOptions.zza(arg2));
    }

    public static MessageClient getMessageClient(Context arg2) {
        return new zzez(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static MessageClient getMessageClient(Context arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzez(arg1, WearableOptions.zza(arg2));
    }

    public static NodeClient getNodeClient(Activity arg2) {
        return new zzfl(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static NodeClient getNodeClient(Activity arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzfl(arg1, WearableOptions.zza(arg2));
    }

    public static NodeClient getNodeClient(Context arg2) {
        return new zzfl(arg2, Settings.DEFAULT_SETTINGS);
    }

    public static NodeClient getNodeClient(Context arg1, WearableOptions arg2) {
        l.a(arg2, "options must not be null");
        return new zzfl(arg1, WearableOptions.zza(arg2));
    }
}

