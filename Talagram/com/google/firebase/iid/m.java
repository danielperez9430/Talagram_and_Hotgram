package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class m {
    final int a;
    final TaskCompletionSource b;
    final int c;
    final Bundle d;

    m(int arg2, int arg3, Bundle arg4) {
        super();
        this.b = new TaskCompletionSource();
        this.a = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    final void a(n arg6) {
        if(Log.isLoggable("MessengerIpcClient", 3)) {
            String v1 = String.valueOf(this);
            String v2 = String.valueOf(arg6);
            StringBuilder v4 = new StringBuilder(String.valueOf(v1).length() + 14 + String.valueOf(v2).length());
            v4.append("Failing ");
            v4.append(v1);
            v4.append(" with ");
            v4.append(v2);
            Log.d("MessengerIpcClient", v4.toString());
        }

        this.b.setException(((Exception)arg6));
    }

    abstract void a(Bundle arg1);

    abstract boolean a();

    final void a(Object arg6) {
        if(Log.isLoggable("MessengerIpcClient", 3)) {
            String v1 = String.valueOf(this);
            String v2 = String.valueOf(arg6);
            StringBuilder v4 = new StringBuilder(String.valueOf(v1).length() + 16 + String.valueOf(v2).length());
            v4.append("Finishing ");
            v4.append(v1);
            v4.append(" with ");
            v4.append(v2);
            Log.d("MessengerIpcClient", v4.toString());
        }

        this.b.setResult(arg6);
    }

    public String toString() {
        int v0 = this.c;
        int v1 = this.a;
        boolean v2 = this.a();
        StringBuilder v3 = new StringBuilder(55);
        v3.append("Request { what=");
        v3.append(v0);
        v3.append(" id=");
        v3.append(v1);
        v3.append(" oneWay=");
        v3.append(v2);
        v3.append("}");
        return v3.toString();
    }
}

