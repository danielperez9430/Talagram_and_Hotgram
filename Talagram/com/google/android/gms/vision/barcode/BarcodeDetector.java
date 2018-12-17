package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zze;
import com.google.android.gms.internal.vision.zzg;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

public final class BarcodeDetector extends Detector {
    public class Builder {
        private zze zzbm;
        private Context zze;

        public Builder(Context arg1) {
            super();
            this.zze = arg1;
            this.zzbm = new zze();
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new zzg(this.zze, this.zzbm), null);
        }

        public Builder setBarcodeFormats(int arg2) {
            this.zzbm.zzbn = arg2;
            return this;
        }
    }

    private final zzg zzbl;

    private BarcodeDetector() {
        super();
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(zzg arg1) {
        super();
        this.zzbl = arg1;
    }

    BarcodeDetector(zzg arg1, zzc arg2) {
        this(arg1);
    }

    public final SparseArray detect(Frame arg6) {
        Barcode[] v6;
        if(arg6 != null) {
            zzm v0 = zzm.zzc(arg6);
            if(arg6.getBitmap() != null) {
                v6 = this.zzbl.zza(arg6.getBitmap(), v0);
                if(v6 != null) {
                }
                else {
                    throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
                }
            }
            else {
                v6 = this.zzbl.zza(arg6.getGrayscaleImageData(), v0);
            }

            SparseArray v0_1 = new SparseArray(v6.length);
            int v1 = v6.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                v0_1.append(v6[v2].rawValue.hashCode(), v6[v2]);
            }

            return v0_1;
        }

        throw new IllegalArgumentException("No frame supplied.");
    }

    public final boolean isOperational() {
        return this.zzbl.isOperational();
    }

    public final void release() {
        super.release();
        this.zzbl.zzo();
    }
}

