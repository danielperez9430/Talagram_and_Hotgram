package com.google.android.gms.common.net;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SocketFactoryCreator extends RemoteCreator {
    private static SocketFactoryCreator zzvr;

    protected SocketFactoryCreator() {
        super("com.google.android.gms.common.net.SocketFactoryCreatorImpl");
    }

    public static SocketFactoryCreator getInstance() {
        if(SocketFactoryCreator.zzvr == null) {
            SocketFactoryCreator.zzvr = new SocketFactoryCreator();
        }

        return SocketFactoryCreator.zzvr;
    }

    protected ISocketFactoryCreator getRemoteCreator(IBinder arg1) {
        return Stub.asInterface(arg1);
    }

    protected Object getRemoteCreator(IBinder arg1) {
        return this.getRemoteCreator(arg1);
    }

    public SSLSocketFactory makeSocketFactory(Context arg2, KeyManager[] arg3, TrustManager[] arg4, boolean arg5) {
        try {
            return ObjectWrapper.unwrap(((RemoteCreator)this).getRemoteCreatorInstance(arg2).newSocketFactory(ObjectWrapper.wrap(arg2), ObjectWrapper.wrap(arg3), ObjectWrapper.wrap(arg4), arg5));
        }
        catch(RemoteCreatorException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }

    public SSLSocketFactory makeSocketFactoryWithCacheDir(Context arg2, KeyManager[] arg3, TrustManager[] arg4, String arg5) {
        try {
            return ObjectWrapper.unwrap(((RemoteCreator)this).getRemoteCreatorInstance(arg2).newSocketFactoryWithCacheDir(ObjectWrapper.wrap(arg2), ObjectWrapper.wrap(arg3), ObjectWrapper.wrap(arg4), arg5));
        }
        catch(RemoteCreatorException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }
}

