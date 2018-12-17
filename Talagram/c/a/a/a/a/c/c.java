package c.a.a.a.a.c;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class c extends PriorityBlockingQueue {
    final Queue a;
    private final ReentrantLock b;

    public c() {
        super();
        this.a = new LinkedList();
        this.b = new ReentrantLock();
    }

    public b a() {
        return this.b(0, null, null);
    }

    b a(int arg1, Long arg2, TimeUnit arg3) {
        switch(arg1) {
            case 0: {
                goto label_10;
            }
            case 1: {
                goto label_8;
            }
            case 2: {
                goto label_6;
            }
            case 3: {
                goto label_3;
            }
        }

        return null;
    label_3:
        Object v1 = super.poll(arg2.longValue(), arg3);
        goto label_11;
    label_6:
        v1 = super.poll();
        goto label_11;
    label_8:
        v1 = super.peek();
        goto label_11;
    label_10:
        v1 = super.take();
    label_11:
        return ((b)v1);
    }

    public b a(long arg1, TimeUnit arg3) {
        return this.b(3, Long.valueOf(arg1), arg3);
    }

    boolean a(int arg2, b arg3) {
        boolean v2_1;
        try {
            this.b.lock();
            if(arg2 == 1) {
                super.remove(arg3);
            }

            v2_1 = this.a.offer(arg3);
        }
        catch(Throwable v2) {
            this.b.unlock();
            throw v2;
        }

        this.b.unlock();
        return v2_1;
    }

    boolean a(b arg1) {
        return arg1.d();
    }

    Object[] a(Object[] arg5, Object[] arg6) {
        int v0 = arg5.length;
        int v1 = arg6.length;
        Object v2 = Array.newInstance(arg5.getClass().getComponentType(), v0 + v1);
        System.arraycopy(arg5, 0, v2, 0, v0);
        System.arraycopy(arg6, 0, v2, v0, v1);
        return ((Object[])v2);
    }

    b b(int arg3, Long arg4, TimeUnit arg5) {
        b v0;
        while(true) {
            v0 = this.a(arg3, arg4, arg5);
            if(v0 != null) {
                if(this.a(v0)) {
                }
                else {
                    this.a(arg3, v0);
                    continue;
                }
            }

            return v0;
        }

        return v0;
    }

    public b b() {
        Long v1 = null;
        try {
            return this.b(1, v1, ((TimeUnit)v1));
        }
        catch(InterruptedException ) {
            return ((b)v1);
        }
    }

    public b c() {
        int v0 = 2;
        Long v1 = null;
        try {
            return this.b(v0, v1, ((TimeUnit)v1));
        }
        catch(InterruptedException ) {
            return ((b)v1);
        }
    }

    public void clear() {
        try {
            this.b.lock();
            this.a.clear();
            super.clear();
        }
        catch(Throwable v0) {
            this.b.unlock();
            throw v0;
        }

        this.b.unlock();
    }

    public boolean contains(Object arg2) {
        try {
            this.b.lock();
            if(super.contains(arg2)) {
                goto label_10;
            }
            else if(!this.a.contains(arg2)) {
                goto label_8;
            }
        }
        catch(Throwable v2) {
            this.b.unlock();
            throw v2;
        }

        goto label_10;
    label_8:
        boolean v2_1 = false;
        goto label_11;
    label_10:
        v2_1 = true;
    label_11:
        this.b.unlock();
        return v2_1;
    }

    public void d() {
        try {
            this.b.lock();
            Iterator v0_1 = this.a.iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                if(!this.a(((b)v1))) {
                    continue;
                }

                super.offer(v1);
                v0_1.remove();
            }
        }
        catch(Throwable v0) {
            goto label_17;
        }

        this.b.unlock();
        return;
    label_17:
        this.b.unlock();
        throw v0;
    }

    public int drainTo(Collection arg3) {
        int v0;
        try {
            this.b.lock();
            v0 = super.drainTo(arg3) + this.a.size();
            while(!this.a.isEmpty()) {
                arg3.add(this.a.poll());
            }
        }
        catch(Throwable v3) {
            goto label_18;
        }

        this.b.unlock();
        return v0;
    label_18:
        this.b.unlock();
        throw v3;
    }

    public int drainTo(Collection arg3, int arg4) {
        try {
            this.b.lock();
            int v0;
            for(v0 = super.drainTo(arg3, arg4); !this.a.isEmpty(); ++v0) {
                if(v0 > arg4) {
                    break;
                }

                arg3.add(this.a.poll());
            }
        }
        catch(Throwable v3) {
            goto label_17;
        }

        this.b.unlock();
        return v0;
    label_17:
        this.b.unlock();
        throw v3;
    }

    public Object peek() {
        return this.b();
    }

    public Object poll() {
        return this.c();
    }

    public Object poll(long arg1, TimeUnit arg3) {
        return this.a(arg1, arg3);
    }

    public boolean remove(Object arg2) {
        try {
            this.b.lock();
            if(super.remove(arg2)) {
                goto label_10;
            }
            else if(!this.a.remove(arg2)) {
                goto label_8;
            }
        }
        catch(Throwable v2) {
            this.b.unlock();
            throw v2;
        }

        goto label_10;
    label_8:
        boolean v2_1 = false;
        goto label_11;
    label_10:
        v2_1 = true;
    label_11:
        this.b.unlock();
        return v2_1;
    }

    public boolean removeAll(Collection arg3) {
        boolean v3_1;
        boolean v0;
        try {
            this.b.lock();
            v0 = super.removeAll(arg3);
            v3_1 = this.a.removeAll(arg3);
        }
        catch(Throwable v3) {
            this.b.unlock();
            throw v3;
        }

        this.b.unlock();
        return (((int)v3_1)) | (((int)v0));
    }

    public int size() {
        int v1;
        int v0_1;
        try {
            this.b.lock();
            v0_1 = this.a.size();
            v1 = super.size();
        }
        catch(Throwable v0) {
            this.b.unlock();
            throw v0;
        }

        this.b.unlock();
        return v0_1 + v1;
    }

    public Object take() {
        return this.a();
    }

    public Object[] toArray() {
        Object[] v0_1;
        try {
            this.b.lock();
            v0_1 = this.a(super.toArray(), this.a.toArray());
        }
        catch(Throwable v0) {
            this.b.unlock();
            throw v0;
        }

        this.b.unlock();
        return v0_1;
    }

    public Object[] toArray(Object[] arg3) {
        try {
            this.b.lock();
            arg3 = this.a(super.toArray(arg3), this.a.toArray(arg3));
        }
        catch(Throwable v3) {
            this.b.unlock();
            throw v3;
        }

        this.b.unlock();
        return arg3;
    }
}

