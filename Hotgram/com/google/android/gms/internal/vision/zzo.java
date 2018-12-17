package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public final class zzo {
    public static Bitmap zzb(Bitmap arg9, zzm arg10) {
        int v7 = arg9.getWidth();
        int v8 = arg9.getHeight();
        if(arg10.rotation != 0) {
            Matrix v5 = new Matrix();
            switch(arg10.rotation) {
                case 0: {
                    goto label_18;
                }
                case 1: {
                    goto label_16;
                }
                case 2: {
                    goto label_14;
                }
                case 3: {
                    goto label_12;
                }
            }

            throw new IllegalArgumentException("Unsupported rotation degree.");
        label_18:
            int v0 = 0;
            goto label_19;
        label_12:
            v0 = 270;
            goto label_19;
        label_14:
            v0 = 180;
            goto label_19;
        label_16:
            v0 = 90;
        label_19:
            v5.postRotate(((float)v0));
            arg9 = Bitmap.createBitmap(arg9, 0, 0, v7, v8, v5, false);
        }

        if(arg10.rotation == 1 || arg10.rotation == 3) {
            arg10.width = v8;
            arg10.height = v7;
        }

        return arg9;
    }
}

