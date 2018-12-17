package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzad;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.internal.vision.zzo;
import com.google.android.gms.internal.vision.zzx;
import com.google.android.gms.internal.vision.zzz;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame$Metadata;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class TextRecognizer extends Detector {
    public class Builder {
        private zzae zzdg;
        private Context zze;

        public Builder(Context arg1) {
            super();
            this.zze = arg1;
            this.zzdg = new zzae();
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzad(this.zze, this.zzdg), null);
        }
    }

    private final zzad zzdf;

    private TextRecognizer() {
        super();
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzad arg1) {
        super();
        this.zzdf = arg1;
    }

    TextRecognizer(zzad arg1, zzb arg2) {
        this(arg1);
    }

    public final SparseArray detect(Frame arg14) {
        SparseArray v5_3;
        Rect v14_1;
        byte[] v6;
        int v2_2;
        Bitmap v2;
        zzz v0 = new zzz(new Rect());
        if(arg14 != null) {
            zzm v1 = zzm.zzc(arg14);
            int v3 = 0;
            if(arg14.getBitmap() != null) {
                v2 = arg14.getBitmap();
            }
            else {
                Metadata v2_1 = arg14.getMetadata();
                ByteBuffer v4 = arg14.getGrayscaleImageData();
                int v7 = v2_1.getFormat();
                v2_2 = v1.width;
                int v11 = v1.height;
                if(!v4.hasArray() || v4.arrayOffset() != 0) {
                    byte[] v5 = new byte[v4.capacity()];
                    v4.get(v5);
                    v6 = v5;
                }
                else {
                    v6 = v4.array();
                }

                ByteArrayOutputStream v4_1 = new ByteArrayOutputStream();
                new YuvImage(v6, v7, v2_2, v11, null).compressToJpeg(new Rect(0, 0, v2_2, v11), 100, ((OutputStream)v4_1));
                byte[] v2_3 = v4_1.toByteArray();
                v2 = BitmapFactory.decodeByteArray(v2_3, 0, v2_3.length);
            }

            v2 = zzo.zzb(v2, v1);
            if(!v0.zzdr.isEmpty()) {
                Rect v4_2 = v0.zzdr;
                int v5_1 = arg14.getMetadata().getWidth();
                int v14 = arg14.getMetadata().getHeight();
                switch(v1.rotation) {
                    case 1: {
                        v14_1 = new Rect(v14 - v4_2.bottom, v4_2.left, v14 - v4_2.top, v4_2.right);
                        break;
                    }
                    case 2: {
                        v14_1 = new Rect(v5_1 - v4_2.right, v14 - v4_2.bottom, v5_1 - v4_2.left, v14 - v4_2.top);
                        break;
                    }
                    case 3: {
                        v14_1 = new Rect(v4_2.top, v5_1 - v4_2.right, v4_2.bottom, v5_1 - v4_2.left);
                        break;
                    }
                    default: {
                        v14_1 = v4_2;
                        break;
                    }
                }

                v0.zzdr.set(v14_1);
            }

            v1.rotation = 0;
            zzx[] v14_2 = this.zzdf.zza(v2, v1, v0);
            SparseArray v0_1 = new SparseArray();
            int v1_1 = v14_2.length;
            for(v2_2 = 0; v2_2 < v1_1; ++v2_2) {
                zzx v4_3 = v14_2[v2_2];
                Object v5_2 = v0_1.get(v4_3.zzdp);
                if(v5_2 == null) {
                    v5_3 = new SparseArray();
                    v0_1.append(v4_3.zzdp, v5_3);
                }

                v5_3.append(v4_3.zzdq, v4_3);
            }

            SparseArray v14_3 = new SparseArray(v0_1.size());
            while(v3 < v0_1.size()) {
                v14_3.append(v0_1.keyAt(v3), new TextBlock(v0_1.valueAt(v3)));
                ++v3;
            }

            return v14_3;
        }

        throw new IllegalArgumentException("No frame supplied.");
    }

    public final boolean isOperational() {
        return this.zzdf.isOperational();
    }

    public final void release() {
        super.release();
        this.zzdf.zzo();
    }
}

