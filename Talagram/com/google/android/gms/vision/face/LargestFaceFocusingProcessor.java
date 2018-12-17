package com.google.android.gms.vision.face;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector$Detections;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.FocusingProcessor;
import com.google.android.gms.vision.Tracker;

public class LargestFaceFocusingProcessor extends FocusingProcessor {
    public class Builder {
        private LargestFaceFocusingProcessor zzcd;

        public Builder(Detector arg2, Tracker arg3) {
            super();
            this.zzcd = new LargestFaceFocusingProcessor(arg2, arg3);
        }

        public LargestFaceFocusingProcessor build() {
            return this.zzcd;
        }

        public Builder setMaxGapFrames(int arg2) {
            LargestFaceFocusingProcessor.zza(this.zzcd, arg2);
            return this;
        }
    }

    public LargestFaceFocusingProcessor(Detector arg1, Tracker arg2) {
        super(arg1, arg2);
    }

    public int selectFocus(Detections arg7) {
        SparseArray v7 = arg7.getDetectedItems();
        if(v7.size() != 0) {
            int v1 = v7.keyAt(0);
            float v0 = v7.valueAt(0).getWidth();
            int v2;
            for(v2 = 1; v2 < v7.size(); ++v2) {
                int v3 = v7.keyAt(v2);
                float v4 = v7.valueAt(v2).getWidth();
                if(v4 > v0) {
                    v1 = v3;
                    v0 = v4;
                }
            }

            return v1;
        }

        throw new IllegalArgumentException("No faces for selectFocus.");
    }

    static void zza(LargestFaceFocusingProcessor arg0, int arg1) {
        ((FocusingProcessor)arg0).zza(arg1);
    }
}

