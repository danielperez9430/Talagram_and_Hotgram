package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.internal.location.zze;

public class ActivityRecognition {
    public abstract class zza extends ApiMethodImpl {
        public zza(GoogleApiClient arg2) {
            super(ActivityRecognition.API, arg2);
        }
    }

    public static final Api API = null;
    @Deprecated public static final ActivityRecognitionApi ActivityRecognitionApi = null;
    private static final AbstractClientBuilder CLIENT_BUILDER = null;
    private static final ClientKey CLIENT_KEY = null;
    public static final String CLIENT_NAME = "activity_recognition";

    static {
        ActivityRecognition.CLIENT_KEY = new ClientKey();
        ActivityRecognition.CLIENT_BUILDER = new com.google.android.gms.location.zza();
        ActivityRecognition.API = new Api("ActivityRecognition.API", ActivityRecognition.CLIENT_BUILDER, ActivityRecognition.CLIENT_KEY);
        ActivityRecognition.ActivityRecognitionApi = new zze();
    }

    private ActivityRecognition() {
        super();
    }

    public static ActivityRecognitionClient getClient(Activity arg1) {
        return new ActivityRecognitionClient(arg1);
    }

    public static ActivityRecognitionClient getClient(Context arg1) {
        return new ActivityRecognitionClient(arg1);
    }
}

