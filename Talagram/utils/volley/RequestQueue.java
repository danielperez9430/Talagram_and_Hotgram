package utils.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestQueue {
    public interface RequestFilter {
        boolean apply(Request arg1);
    }

    public interface RequestFinishedListener {
        void onRequestFinished(Request arg1);
    }

    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private final Cache mCache;
    private CacheDispatcher mCacheDispatcher;
    private final PriorityBlockingQueue mCacheQueue;
    private final Set mCurrentRequests;
    private final ResponseDelivery mDelivery;
    private final NetworkDispatcher[] mDispatchers;
    private final List mFinishedListeners;
    private final Network mNetwork;
    private final PriorityBlockingQueue mNetworkQueue;
    private final AtomicInteger mSequenceGenerator;

    public RequestQueue(Cache arg2, Network arg3) {
        this(arg2, arg3, 4);
    }

    public RequestQueue(Cache arg4, Network arg5, int arg6) {
        this(arg4, arg5, arg6, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Cache arg2, Network arg3, int arg4, ResponseDelivery arg5) {
        super();
        this.mSequenceGenerator = new AtomicInteger();
        this.mCurrentRequests = new HashSet();
        this.mCacheQueue = new PriorityBlockingQueue();
        this.mNetworkQueue = new PriorityBlockingQueue();
        this.mFinishedListeners = new ArrayList();
        this.mCache = arg2;
        this.mNetwork = arg3;
        this.mDispatchers = new NetworkDispatcher[arg4];
        this.mDelivery = arg5;
    }

    public Request add(Request arg3) {
        arg3.setRequestQueue(this);
        Set v0 = this.mCurrentRequests;
        __monitor_enter(v0);
        try {
            this.mCurrentRequests.add(arg3);
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_18:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_18;
            }

            throw v3;
        }

        arg3.setSequence(this.getSequenceNumber());
        arg3.addMarker("add-to-queue");
        PriorityBlockingQueue v0_1 = !arg3.shouldCache() ? this.mNetworkQueue : this.mCacheQueue;
        v0_1.add(arg3);
        return arg3;
    }

    public void addRequestFinishedListener(RequestFinishedListener arg3) {
        List v0 = this.mFinishedListeners;
        __monitor_enter(v0);
        try {
            this.mFinishedListeners.add(arg3);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public void cancelAll(Object arg2) {
        if(arg2 != null) {
            this.cancelAll(new RequestFilter(arg2) {
                public boolean apply(Request arg2) {
                    boolean v2 = arg2.getTag() == this.val$tag ? true : false;
                    return v2;
                }
            });
            return;
        }

        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    public void cancelAll(RequestFilter arg5) {
        Set v0 = this.mCurrentRequests;
        __monitor_enter(v0);
        try {
            Iterator v1 = this.mCurrentRequests.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                if(!arg5.apply(((Request)v2))) {
                    continue;
                }

                ((Request)v2).cancel();
            }

            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_14;
        }

        throw v5;
    }

    void finish(Request arg4) {
        Set v0 = this.mCurrentRequests;
        __monitor_enter(v0);
        try {
            this.mCurrentRequests.remove(arg4);
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            try {
            label_20:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_20;
            }

            throw v4;
        }

        List v1 = this.mFinishedListeners;
        __monitor_enter(v1);
        try {
            Iterator v0_1 = this.mFinishedListeners.iterator();
            while(v0_1.hasNext()) {
                v0_1.next().onRequestFinished(arg4);
            }

            __monitor_exit(v1);
            return;
        label_17:
            __monitor_exit(v1);
        }
        catch(Throwable v4) {
            goto label_17;
        }

        throw v4;
    }

    public Cache getCache() {
        return this.mCache;
    }

    public int getSequenceNumber() {
        return this.mSequenceGenerator.incrementAndGet();
    }

    public void removeRequestFinishedListener(RequestFinishedListener arg3) {
        List v0 = this.mFinishedListeners;
        __monitor_enter(v0);
        try {
            this.mFinishedListeners.remove(arg3);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    public void start() {
        this.stop();
        this.mCacheDispatcher = new CacheDispatcher(this.mCacheQueue, this.mNetworkQueue, this.mCache, this.mDelivery);
        this.mCacheDispatcher.start();
        int v0;
        for(v0 = 0; v0 < this.mDispatchers.length; ++v0) {
            NetworkDispatcher v1 = new NetworkDispatcher(this.mNetworkQueue, this.mNetwork, this.mCache, this.mDelivery);
            this.mDispatchers[v0] = v1;
            v1.start();
        }
    }

    public void stop() {
        if(this.mCacheDispatcher != null) {
            this.mCacheDispatcher.quit();
        }

        NetworkDispatcher[] v0 = this.mDispatchers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            NetworkDispatcher v3 = v0[v2];
            if(v3 != null) {
                v3.quit();
            }
        }
    }
}

