package com.google.android.gms.vision.text;

import java.util.Comparator;
import java.util.Map$Entry;

final class zza implements Comparator {
    zza(TextBlock arg1) {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return ((Map$Entry)arg1).getValue().compareTo(((Map$Entry)arg2).getValue());
    }
}

