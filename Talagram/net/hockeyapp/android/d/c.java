package net.hockeyapp.android.d;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import net.hockeyapp.android.e.k;

public abstract class c extends AsyncTask {
    public c() {
        super();
    }

    protected static String a(HttpURLConnection arg1) {
        BufferedInputStream v0 = new BufferedInputStream(arg1.getInputStream());
        String v1 = k.a(((InputStream)v0));
        ((InputStream)v0).close();
        return v1;
    }
}

