package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzad extends zzl {
    private final zzae zzdg;

    public zzad(Context arg3, zzae arg4) {
        super(arg3, "TextNativeHandle", "text");
        this.zzdg = arg4;
        ((zzl)this).zzp();
    }

    protected final Object zza(DynamiteModule arg4, Context arg5) {
        IInterface v4_1;
        IBinder v4 = arg4.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        Object v0 = null;
        if(v4 == null) {
            v4_1 = ((IInterface)v0);
        }
        else {
            IInterface v1 = v4.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            if((v1 instanceof zzv)) {
                v4_1 = v1;
            }
            else {
                zzw v4_2 = new zzw(v4);
            }
        }

        if(v4_1 == null) {
            return v0;
        }

        return ((zzv)v4_1).zza(ObjectWrapper.wrap(arg5), this.zzdg);
    }

    public final zzx[] zza(Bitmap arg3, zzm arg4, zzz arg5) {
        if(!((zzl)this).isOperational()) {
            return new zzx[0];
        }

        try {
            return ((zzl)this).zzp().zza(ObjectWrapper.wrap(arg3), arg4, arg5);
        }
        catch(RemoteException v3) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", ((Throwable)v3));
            return new zzx[0];
        }
    }

    protected final void zzm() {
        ((zzl)this).zzp().zzq();
    }
}

