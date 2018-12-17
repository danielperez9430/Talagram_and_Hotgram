package com.persianswitch.sdk.base;

import android.content.Context;
import com.persianswitch.sdk.base.webservice.HttpEngine;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public interface Injection {
    public final class Network {
        public Network() {
            super();
        }

        public static HttpEngine a(Context arg0, Config arg1) {
            return HttpEngine.a(arg0, arg1);
        }
    }

    public final class ThreadPool {
        private static Executor a;

        public ThreadPool() {
            super();
        }

        public static Executor a() {
            if(ThreadPool.a == null) {
                ThreadPool.a = Executors.newFixedThreadPool(2);
            }

            return ThreadPool.a;
        }
    }

}

