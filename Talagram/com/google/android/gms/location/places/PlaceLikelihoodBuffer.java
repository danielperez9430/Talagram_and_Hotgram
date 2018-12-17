package com.google.android.gms.location.places;

import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.places.internal.zzal;
import java.util.Comparator;

public class PlaceLikelihoodBuffer extends AbstractDataBuffer implements Result {
    private final Status zzdz;
    private final String zzea;
    private static final Comparator zzem;
    private final int zzen;
    private final boolean zzeo;

    static {
        PlaceLikelihoodBuffer.zzem = new zzj();
    }

    public PlaceLikelihoodBuffer(DataHolder arg2, int arg3) {
        this(arg2, false, arg3);
    }

    private PlaceLikelihoodBuffer(DataHolder arg2, boolean arg3, int arg4) {
        super(arg2);
        this.zzdz = PlacesStatusCodes.zze(arg2.getStatusCode());
        switch(arg4) {
            case 100: 
            case 101: 
            case 102: 
            case 103: 
            case 104: 
            case 105: 
            case 106: 
            case 107: 
            case 108: {
                goto label_15;
            }
        }

        StringBuilder v0 = new StringBuilder(27);
        v0.append("invalid source: ");
        v0.append(arg4);
        throw new IllegalArgumentException(v0.toString());
    label_15:
        this.zzen = arg4;
        this.zzeo = false;
        String v2 = arg2 == null || arg2.getMetadata() == null ? null : arg2.getMetadata().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
        this.zzea = v2;
    }

    public PlaceLikelihood get(int arg3) {
        return new zzal(this.mDataHolder, arg3);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    public CharSequence getAttributions() {
        return this.zzea;
    }

    public Status getStatus() {
        return this.zzdz;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.getStatus()).add("attributions", this.zzea).toString();
    }

    public static int zzb(Bundle arg1) {
        return arg1.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
    }
}

