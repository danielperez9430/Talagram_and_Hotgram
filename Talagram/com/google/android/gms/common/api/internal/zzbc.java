package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;

public interface zzbc {
    void begin();

    void connect();

    boolean disconnect();

    ApiMethodImpl enqueue(ApiMethodImpl arg1);

    ApiMethodImpl execute(ApiMethodImpl arg1);

    void onConnected(Bundle arg1);

    void onConnectionSuspended(int arg1);

    void zza(ConnectionResult arg1, Api arg2, boolean arg3);
}

