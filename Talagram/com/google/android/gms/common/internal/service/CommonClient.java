package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public class CommonClient extends GmsClient {
    public static final String START_SERVICE_ACTION = "com.google.android.gms.common.service.START";

    public CommonClient(Context arg8, Looper arg9, ClientSettings arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12) {
        super(arg8, arg9, 39, arg10, arg11, arg12);
    }

    protected IInterface createServiceInterface(IBinder arg1) {
        return this.createServiceInterface(arg1);
    }

    protected ICommonService createServiceInterface(IBinder arg1) {
        return Stub.asInterface(arg1);
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    protected String getServiceDescriptor() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }

    public String getStartServiceAction() {
        return "com.google.android.gms.common.service.START";
    }
}

