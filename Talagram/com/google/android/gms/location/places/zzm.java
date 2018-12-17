package com.google.android.gms.location.places;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.places.zzdo;
import com.google.android.gms.location.places.internal.zzz;

public class zzm extends zzz {
    public abstract class zzb extends zzc {
        public zzb(Api arg1, GoogleApiClient arg2) {
            super(arg1, arg2);
        }

        protected Result createFailedResult(Status arg2) {
            return new AutocompletePredictionBuffer(DataHolder.empty(arg2.getStatusCode()));
        }
    }

    public abstract class zzc extends ApiMethodImpl {
        public zzc(Api arg1, GoogleApiClient arg2) {
            super(arg1, arg2);
        }
    }

    public abstract class zzd extends zzc {
        public zzd(Api arg1, GoogleApiClient arg2) {
            super(arg1, arg2);
        }

        protected Result createFailedResult(Status arg2) {
            return new PlaceBuffer(DataHolder.empty(arg2.getStatusCode()));
        }
    }

    public abstract class zze extends zzc {
        public zze(Api arg1, GoogleApiClient arg2) {
            super(arg1, arg2);
        }

        protected Result createFailedResult(Status arg3) {
            return new PlaceLikelihoodBuffer(DataHolder.empty(arg3.getStatusCode()), 100);
        }
    }

    @Deprecated public abstract class zzf extends zzc {
    }

    public abstract class zzg extends zzc {
        public zzg(Api arg1, GoogleApiClient arg2) {
            super(arg1, arg2);
        }

        protected Result createFailedResult(Status arg1) {
            return arg1;
        }
    }

    private static final String TAG = "zzm";
    private final zze zzex;
    private final zzb zzey;
    private final zzf zzez;
    private final zzg zzfa;
    private final zzd zzfb;

    static {
    }

    public zzm(zzb arg2) {
        super();
        this.zzex = null;
        this.zzey = arg2;
        this.zzez = null;
        this.zzfa = null;
        this.zzfb = null;
    }

    public zzm(zzd arg2) {
        super();
        this.zzex = null;
        this.zzey = null;
        this.zzez = null;
        this.zzfa = null;
        this.zzfb = arg2;
    }

    public zzm(zze arg1) {
        super();
        this.zzex = arg1;
        this.zzey = null;
        this.zzez = null;
        this.zzfa = null;
        this.zzfb = null;
    }

    public zzm(zzg arg2) {
        super();
        this.zzex = null;
        this.zzey = null;
        this.zzez = null;
        this.zzfa = arg2;
        this.zzfb = null;
    }

    public final void zzb(DataHolder arg3) {
        boolean v0 = this.zzex != null ? true : false;
        Preconditions.checkState(v0, "placeEstimator cannot be null");
        if(arg3 == null) {
            if(Log.isLoggable(zzm.TAG, 6)) {
                Log.e(zzm.TAG, "onPlaceEstimated received null DataHolder", new Throwable());
            }

            this.zzex.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }

        Bundle v0_1 = arg3.getMetadata();
        int v0_2 = v0_1 == null ? 100 : PlaceLikelihoodBuffer.zzb(v0_1);
        this.zzex.setResult(new PlaceLikelihoodBuffer(arg3, v0_2));
    }

    public final void zzc(DataHolder arg3) {
        if(arg3 == null) {
            if(Log.isLoggable(zzm.TAG, 6)) {
                Log.e(zzm.TAG, "onAutocompletePrediction received null DataHolder", new Throwable());
            }

            this.zzey.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }

        this.zzey.setResult(new AutocompletePredictionBuffer(arg3));
    }

    public final void zzd(DataHolder arg4) {
        zzf v0 = null;
        if(arg4 == null) {
            if(Log.isLoggable(zzm.TAG, 6)) {
                Log.e(zzm.TAG, "onPlaceUserDataFetched received null DataHolder", new Throwable());
            }

            v0.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            return;
        }

        v0.setResult(new zzdo(arg4));
    }

    public final void zze(Status arg2) {
        this.zzfa.setResult(((Result)arg2));
    }

    public final void zze(DataHolder arg2) {
        this.zzfb.setResult(new PlaceBuffer(arg2));
    }
}

