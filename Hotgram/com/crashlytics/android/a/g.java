package com.crashlytics.android.a;

import android.content.Context;
import android.os.Looper;
import c.a.a.a.a.b.t;
import c.a.a.a.a.f.a;

class g {
    final Context a;
    final a b;

    public g(Context arg1, a arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public z a() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            return new z(this.a, new af(), new t(), new c.a.a.a.a.d.g(this.a, this.b.a(), "session_analytics.tap", "session_analytics_to_send"));
        }

        throw new IllegalStateException("AnswersFilesManagerProvider cannot be called on the main thread");
    }
}

