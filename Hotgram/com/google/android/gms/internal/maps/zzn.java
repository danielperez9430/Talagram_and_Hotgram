package com.google.android.gms.internal.maps;

import android.os.IInterface;
import java.util.List;

public interface zzn extends IInterface {
    int getActiveLevelIndex();

    int getDefaultLevelIndex();

    List getLevels();

    boolean isUnderground();

    boolean zzb(zzn arg1);

    int zzi();
}

