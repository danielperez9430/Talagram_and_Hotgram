package com.google.android.exoplayer2.util;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventDispatcher {
    public interface Event {
        void sendTo(Object arg1);
    }

    final class HandlerAndListener {
        public final Handler handler;
        public final Object listener;

        public HandlerAndListener(Handler arg1, Object arg2) {
            super();
            this.handler = arg1;
            this.listener = arg2;
        }
    }

    private final CopyOnWriteArrayList listeners;

    public EventDispatcher() {
        super();
        this.listeners = new CopyOnWriteArrayList();
    }

    public void addListener(Handler arg3, Object arg4) {
        boolean v0 = arg3 == null || arg4 == null ? false : true;
        Assertions.checkArgument(v0);
        this.removeListener(arg4);
        this.listeners.add(new HandlerAndListener(arg3, arg4));
    }

    public void dispatch(Event arg5) {
        Iterator v0 = this.listeners.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            ((HandlerAndListener)v1).handler.post(new -$$Lambda$EventDispatcher$N5z9NZy9yo_9QWPNCHLnel_5AjM(arg5, ((HandlerAndListener)v1).listener));
        }
    }

    static void lambda$dispatch$0(Event arg0, Object arg1) {
        arg0.sendTo(arg1);
    }

    public void removeListener(Object arg4) {
        Iterator v0 = this.listeners.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(((HandlerAndListener)v1).listener != arg4) {
                continue;
            }

            this.listeners.remove(v1);
        }
    }
}

