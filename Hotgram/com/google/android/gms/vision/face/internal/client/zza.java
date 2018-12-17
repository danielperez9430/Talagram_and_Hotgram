package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.vision.zzl;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.nio.ByteBuffer;

public final class zza extends zzl {
    private final zzc zzce;

    public zza(Context arg3, zzc arg4) {
        super(arg3, "FaceNativeHandle", "face");
        this.zzce = arg4;
        ((zzl)this).zzp();
    }

    protected final Object zza(DynamiteModule arg4, Context arg5) {
        IInterface v4_1;
        IBinder v4 = arg4.instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator");
        Object v0 = null;
        if(v4 == null) {
            v4_1 = ((IInterface)v0);
        }
        else {
            IInterface v1 = v4.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
            if((v1 instanceof zzg)) {
                v4_1 = v1;
            }
            else {
                zzh v4_2 = new zzh(v4);
            }
        }

        if(v4_1 == null) {
            return v0;
        }

        return ((zzg)v4_1).zza(ObjectWrapper.wrap(arg5), this.zzce);
    }

    public final Face[] zzb(ByteBuffer arg20, zzm arg21) {
        Landmark[] v12;
        FaceParcel[] v17;
        FaceParcel[] v0_1;
        int v1 = 0;
        if(!((zzl)this).isOperational()) {
            return new Face[0];
        }

        try {
            v0_1 = ((zzl)this).zzp().zzc(ObjectWrapper.wrap(arg20), arg21);
        }
        catch(RemoteException v0) {
            Log.e("FaceNativeHandle", "Could not call native face detector", ((Throwable)v0));
            return new Face[0];
        }

        Face[] v2 = new Face[v0_1.length];
        int v3 = 0;
        while(v3 < v0_1.length) {
            FaceParcel v4 = v0_1[v3];
            Face v16 = null;
            int v6 = v4.id;
            PointF v7 = new PointF(v4.centerX, v4.centerY);
            float v8 = v4.width;
            float v9 = v4.height;
            float v10 = v4.zzcf;
            float v11 = v4.zzcg;
            LandmarkParcel[] v5 = v4.zzch;
            if(v5 == null) {
                v17 = v0_1;
                v12 = new Landmark[v1];
            }
            else {
                v12 = new Landmark[v5.length];
                int v13 = 0;
                while(v13 < v5.length) {
                    v12[v13] = new Landmark(new PointF(v5[v13].x, v5[v13].y), v5[v13].type);
                    ++v13;
                    v0_1 = v0_1;
                    v5 = v5;
                }

                v17 = v0_1;
            }

            super(v6, v7, v8, v9, v10, v11, v12, v4.zzbs, v4.zzbt, v4.zzbu);
            v2[v3] = v16;
            ++v3;
            v0_1 = v17;
            v1 = 0;
        }

        return v2;
    }

    public final boolean zzd(int arg4) {
        if(!((zzl)this).isOperational()) {
            return 0;
        }

        try {
            return ((zzl)this).zzp().zzd(arg4);
        }
        catch(RemoteException v4) {
            Log.e("FaceNativeHandle", "Could not call native face detector", ((Throwable)v4));
            return 0;
        }
    }

    protected final void zzm() {
        ((zzl)this).zzp().zzn();
    }
}

