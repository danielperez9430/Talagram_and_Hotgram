package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.internal.location.zzad;
import com.google.android.gms.internal.location.zzaj;
import com.google.android.gms.internal.location.zzak;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.internal.location.zzbm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class FusedLocationProviderClient extends GoogleApi {
    final class zza extends zzak {
        private final TaskCompletionSource zzac;

        public zza(TaskCompletionSource arg1) {
            super();
            this.zzac = arg1;
        }

        public final void zza(zzad arg2) {
            TaskUtil.setResultOrApiException(arg2.getStatus(), this.zzac);
        }
    }

    public static final String KEY_VERTICAL_ACCURACY = "verticalAccuracy";

    public FusedLocationProviderClient(Activity arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public FusedLocationProviderClient(Context arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public Task flushLocations() {
        return PendingResultUtil.toVoidTask(LocationServices.FusedLocationApi.flushLocations(this.asGoogleApiClient()));
    }

    public Task getLastLocation() {
        return this.doRead(new zzl(this));
    }

    public Task getLocationAvailability() {
        return this.doRead(new zzm(this));
    }

    public Task removeLocationUpdates(PendingIntent arg3) {
        return PendingResultUtil.toVoidTask(LocationServices.FusedLocationApi.removeLocationUpdates(this.asGoogleApiClient(), arg3));
    }

    public Task removeLocationUpdates(LocationCallback arg2) {
        return TaskUtil.toVoidTaskThatFailsOnFalse(this.doUnregisterEventListener(ListenerHolders.createListenerKey(arg2, LocationCallback.class.getSimpleName())));
    }

    public Task requestLocationUpdates(LocationRequest arg3, PendingIntent arg4) {
        return PendingResultUtil.toVoidTask(LocationServices.FusedLocationApi.requestLocationUpdates(this.asGoogleApiClient(), arg3, arg4));
    }

    public Task requestLocationUpdates(LocationRequest arg2, LocationCallback arg3, Looper arg4) {
        zzbd v2 = zzbd.zza(arg2);
        ListenerHolder v3 = ListenerHolders.createListenerHolder(arg3, zzbm.zza(arg4), LocationCallback.class.getSimpleName());
        return this.doRegisterEventListener(new zzn(this, v3, v2, v3), new zzo(this, v3.getListenerKey()));
    }

    public Task setMockLocation(Location arg3) {
        return PendingResultUtil.toVoidTask(LocationServices.FusedLocationApi.setMockLocation(this.asGoogleApiClient(), arg3));
    }

    public Task setMockMode(boolean arg3) {
        return PendingResultUtil.toVoidTask(LocationServices.FusedLocationApi.setMockMode(this.asGoogleApiClient(), arg3));
    }

    static zzaj zza(FusedLocationProviderClient arg0, TaskCompletionSource arg1) {
        return arg0.zza(arg1);
    }

    private final zzaj zza(TaskCompletionSource arg2) {
        return new zzp(this, arg2);
    }
}

