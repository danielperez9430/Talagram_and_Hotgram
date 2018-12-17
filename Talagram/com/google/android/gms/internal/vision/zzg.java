package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class zzg extends zzl {
    private final zze zzbm;

    public zzg(Context arg3, zze arg4) {
        super(arg3, "BarcodeNativeHandle", "barcode");
        this.zzbm = arg4;
        ((zzl)this).zzp();
    }

    protected final Object zza(DynamiteModule arg4, Context arg5) {
        zzk v4_2;
        IInterface v4_1;
        IBinder v4 = arg4.instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        Object v0 = null;
        if(v4 == null) {
            v4_1 = ((IInterface)v0);
        }
        else {
            IInterface v1 = v4.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            if((v1 instanceof zzj)) {
                v4_1 = v1;
            }
            else {
                v4_2 = new zzk(v4);
            }
        }

        if((((IInterface)v4_2)) == null) {
            return v0;
        }

        return ((zzj)v4_2).zza(ObjectWrapper.wrap(arg5), this.zzbm);
    }

    public final Barcode[] zza(Bitmap arg3, zzm arg4) {
        if(!((zzl)this).isOperational()) {
            return new Barcode[0];
        }

        try {
            return ((zzl)this).zzp().zzb(ObjectWrapper.wrap(arg3), arg4);
        }
        catch(RemoteException v3) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", ((Throwable)v3));
            return new Barcode[0];
        }
    }

    public final Barcode[] zza(ByteBuffer arg3, zzm arg4) {
        if(!((zzl)this).isOperational()) {
            return new Barcode[0];
        }

        try {
            return ((zzl)this).zzp().zza(ObjectWrapper.wrap(arg3), arg4);
        }
        catch(RemoteException v3) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", ((Throwable)v3));
            return new Barcode[0];
        }
    }

    protected final void zzm() {
        if(((zzl)this).isOperational()) {
            ((zzl)this).zzp().zzn();
        }
    }
}

