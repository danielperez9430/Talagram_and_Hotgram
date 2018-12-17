package com.google.android.gms.internal.config;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;
import java.util.Map;

public interface zzk extends Result {
    Status getStatus();

    long getThrottleEndTimeMillis();

    byte[] zza(String arg1, byte[] arg2, String arg3);

    List zzg();

    Map zzh();
}

