package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;

public final class SignInButtonCreator extends RemoteCreator {
    private static final SignInButtonCreator zzuz;

    static {
        SignInButtonCreator.zzuz = new SignInButtonCreator();
    }

    private SignInButtonCreator() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View createView(Context arg1, int arg2, int arg3) {
        return SignInButtonCreator.zzuz.zza(arg1, arg2, arg3);
    }

    public final ISignInButtonCreator getRemoteCreator(IBinder arg1) {
        return Stub.asInterface(arg1);
    }

    public final Object getRemoteCreator(IBinder arg1) {
        return this.getRemoteCreator(arg1);
    }

    private final View zza(Context arg4, int arg5, int arg6) {
        try {
            return ObjectWrapper.unwrap(((RemoteCreator)this).getRemoteCreatorInstance(arg4).newSignInButtonFromConfig(ObjectWrapper.wrap(arg4), new SignInButtonConfig(arg5, arg6, null)));
        }
        catch(Exception v4) {
            StringBuilder v2 = new StringBuilder(64);
            v2.append("Could not get button with size ");
            v2.append(arg5);
            v2.append(" and color ");
            v2.append(arg6);
            throw new RemoteCreatorException(v2.toString(), ((Throwable)v4));
        }
    }
}

