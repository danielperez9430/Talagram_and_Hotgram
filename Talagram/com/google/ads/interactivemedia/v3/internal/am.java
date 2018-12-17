package com.google.ads.interactivemedia.v3.internal;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

public abstract class am extends AsyncTask {
    public interface a {
        void a(am arg1);
    }

    public interface b {
        void a(JSONObject arg1);

        JSONObject b();
    }

    private a a;
    protected final b d;

    public am(b arg1) {
        super();
        this.d = arg1;
    }

    public void a(a arg1) {
        this.a = arg1;
    }

    protected void a(String arg1) {
        if(this.a != null) {
            this.a.a(this);
        }
    }

    public void a(ThreadPoolExecutor arg2) {
        this.executeOnExecutor(((Executor)arg2), new Object[0]);
    }

    protected void onPostExecute(Object arg1) {
        this.a(((String)arg1));
    }
}

