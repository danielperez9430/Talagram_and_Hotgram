package net.hockeyapp.android.e;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class c implements Future {
    private final Object a;

    public c(Object arg1) {
        super();
        this.a = arg1;
    }

    public boolean cancel(boolean arg1) {
        return 0;
    }

    public Object get() {
        return this.a;
    }

    public Object get(long arg1, TimeUnit arg3) {
        return this.a;
    }

    public boolean isCancelled() {
        return 0;
    }

    public boolean isDone() {
        return 1;
    }
}

