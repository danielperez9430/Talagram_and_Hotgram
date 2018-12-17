package com.d.a.b.a.a;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class d extends AbstractQueue implements a, Serializable {
    class com.d.a.b.a.a.d$1 {
    }

    abstract class com.d.a.b.a.a.d$a implements Iterator {
        c a;
        Object b;
        private c d;

        com.d.a.b.a.a.d$a(d arg2) {
            this.c = arg2;
            super();
            ReentrantLock v2 = arg2.c;
            v2.lock();
            try {
                this.a = this.a();
                Object v0_1 = this.a == null ? null : this.a.a;
                this.b = v0_1;
            }
            catch(Throwable v0) {
                v2.unlock();
                throw v0;
            }

            v2.unlock();
        }

        abstract c a();

        abstract c a(c arg1);

        private c b(c arg3) {
            while(true) {
                c v0 = this.a(arg3);
                if(v0 == null) {
                    return null;
                }

                if(v0.a != null) {
                    return v0;
                }

                if(v0 == arg3) {
                    return this.a();
                }

                arg3 = v0;
            }

            return null;
        }

        void b() {
            ReentrantLock v0 = this.c.c;
            v0.lock();
            try {
                this.a = this.b(this.a);
                Object v1_1 = this.a == null ? null : this.a.a;
                this.b = v1_1;
            }
            catch(Throwable v1) {
                v0.unlock();
                throw v1;
            }

            v0.unlock();
        }

        public boolean hasNext() {
            boolean v0 = this.a != null ? true : false;
            return v0;
        }

        public Object next() {
            if(this.a != null) {
                this.d = this.a;
                Object v0 = this.b;
                this.b();
                return v0;
            }

            throw new NoSuchElementException();
        }

        public void remove() {
            c v0 = this.d;
            if(v0 != null) {
                this.d = null;
                ReentrantLock v1 = this.c.c;
                v1.lock();
                try {
                    if(v0.a != null) {
                        this.c.a(v0);
                    }
                }
                catch(Throwable v0_1) {
                    v1.unlock();
                    throw v0_1;
                }

                v1.unlock();
                return;
            }

            throw new IllegalStateException();
        }
    }

    class b extends com.d.a.b.a.a.d$a {
        b(d arg1, com.d.a.b.a.a.d$1 arg2) {
            this(arg1);
        }

        private b(d arg1) {
            this.d = arg1;
            super(arg1);
        }

        c a() {
            return this.d.a;
        }

        c a(c arg1) {
            return arg1.c;
        }
    }

    final class c {
        Object a;
        c b;
        c c;

        c(Object arg1) {
            super();
            this.a = arg1;
        }
    }

    transient c a;
    transient c b;
    final ReentrantLock c;
    private transient int d;
    private final int e;
    private final Condition f;
    private final Condition g;

    public d() {
        this(2147483647);
    }

    public d(int arg2) {
        super();
        this.c = new ReentrantLock();
        this.f = this.c.newCondition();
        this.g = this.c.newCondition();
        if(arg2 > 0) {
            this.e = arg2;
            return;
        }

        throw new IllegalArgumentException();
    }

    public Object a() {
        Object v0 = this.b();
        if(v0 != null) {
            return v0;
        }

        throw new NoSuchElementException();
    }

    public Object a(long arg4, TimeUnit arg6) {
        Object v0;
        arg4 = arg6.toNanos(arg4);
        ReentrantLock v6 = this.c;
        v6.lockInterruptibly();
        try {
            while(true) {
            label_3:
                v0 = this.f();
                if(v0 != null) {
                    goto label_13;
                }

                break;
            }
        }
        catch(Throwable v4) {
            goto label_16;
        }

        if(arg4 <= 0) {
            v6.unlock();
            return null;
        }

        try {
            arg4 = this.f.awaitNanos(arg4);
            goto label_3;
        }
        catch(Throwable v4) {
        label_16:
            v6.unlock();
            throw v4;
        }

    label_13:
        v6.unlock();
        return v0;
    }

    void a(c arg3) {
        c v0 = arg3.b;
        c v1 = arg3.c;
        if(v0 == null) {
            this.f();
        }
        else if(v1 == null) {
            this.g();
        }
        else {
            v0.c = v1;
            v1.b = v0;
            arg3.a = null;
            --this.d;
            this.g.signal();
        }
    }

    public void a(Object arg2) {
        if(this.c(arg2)) {
            return;
        }

        throw new IllegalStateException("Deque full");
    }

    public boolean a(Object arg4, long arg5, TimeUnit arg7) {
        boolean v4_2;
        if(arg4 != null) {
            c v0 = new c(arg4);
            long v4 = arg7.toNanos(arg5);
            ReentrantLock v6 = this.c;
            v6.lockInterruptibly();
            try {
                while(true) {
                label_6:
                    if(!this.c(v0)) {
                        break;
                    }
                    else {
                        goto label_16;
                    }
                }
            }
            catch(Throwable v4_1) {
                goto label_19;
            }

            if(v4 <= 0) {
                v4_2 = false;
            }
            else {
                try {
                    v4 = this.g.awaitNanos(v4);
                    goto label_6;
                }
                catch(Throwable v4_1) {
                label_19:
                    v6.unlock();
                    throw v4_1;
                }

            label_16:
                v4_2 = true;
            }

            v6.unlock();
            return v4_2;
        }

        throw new NullPointerException();
    }

    public boolean add(Object arg1) {
        this.a(arg1);
        return 1;
    }

    private boolean b(c arg3) {
        if(this.d >= this.e) {
            return 0;
        }

        c v0 = this.a;
        arg3.c = v0;
        this.a = arg3;
        if(this.b == null) {
            this.b = arg3;
        }
        else {
            v0.b = arg3;
        }

        ++this.d;
        this.f.signal();
        return 1;
    }

    public Object b() {
        Object v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            v1_1 = this.f();
        }
        catch(Throwable v1) {
            v0.unlock();
            throw v1;
        }

        v0.unlock();
        return v1_1;
    }

    public boolean b(Object arg2) {
        boolean v0_2;
        if(arg2 != null) {
            c v0 = new c(arg2);
            ReentrantLock v2 = this.c;
            v2.lock();
            try {
                v0_2 = this.b(v0);
            }
            catch(Throwable v0_1) {
                v2.unlock();
                throw v0_1;
            }

            v2.unlock();
            return v0_2;
        }

        throw new NullPointerException();
    }

    private boolean c(c arg3) {
        if(this.d >= this.e) {
            return 0;
        }

        c v0 = this.b;
        arg3.b = v0;
        this.b = arg3;
        if(this.a == null) {
            this.a = arg3;
        }
        else {
            v0.c = arg3;
        }

        ++this.d;
        this.f.signal();
        return 1;
    }

    public boolean c(Object arg2) {
        boolean v0_2;
        if(arg2 != null) {
            c v0 = new c(arg2);
            ReentrantLock v2 = this.c;
            v2.lock();
            try {
                v0_2 = this.c(v0);
            }
            catch(Throwable v0_1) {
                v2.unlock();
                throw v0_1;
            }

            v2.unlock();
            return v0_2;
        }

        throw new NullPointerException();
    }

    public Object c() {
        Object v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            while(true) {
                v1_1 = this.f();
                if(v1_1 != null) {
                    break;
                }

                this.f.await();
            }
        }
        catch(Throwable v1) {
            v0.unlock();
            throw v1;
        }

        v0.unlock();
        return v1_1;
    }

    public void clear() {
        Object v2;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            c v1_1;
            for(v1_1 = this.a; true; v1_1 = v3) {
                v2 = null;
                if(v1_1 == null) {
                    break;
                }

                v1_1.a = v2;
                c v3 = v1_1.c;
                v1_1.b = ((c)v2);
                v1_1.c = ((c)v2);
            }

            this.b = ((c)v2);
            this.a = ((c)v2);
            this.d = 0;
            this.g.signalAll();
        }
        catch(Throwable v1) {
            goto label_20;
        }

        v0.unlock();
        return;
    label_20:
        v0.unlock();
        throw v1;
    }

    public boolean contains(Object arg5) {
        c v2;
        if(arg5 == null) {
            return 0;
        }

        ReentrantLock v1 = this.c;
        v1.lock();
        try {
            v2 = this.a;
            while(true) {
            label_6:
                if(v2 == null) {
                    goto label_15;
                }

                if(!arg5.equals(v2.a)) {
                    goto label_13;
                }

                break;
            }
        }
        catch(Throwable v5) {
            goto label_18;
        }

        v1.unlock();
        return 1;
        try {
        label_13:
            v2 = v2.c;
            goto label_6;
        }
        catch(Throwable v5) {
            goto label_18;
        }

    label_15:
        v1.unlock();
        return 0;
    label_18:
        v1.unlock();
        throw v5;
    }

    public Object d() {
        Object v0 = this.e();
        if(v0 != null) {
            return v0;
        }

        throw new NoSuchElementException();
    }

    public void d(Object arg3) {
        if(arg3 != null) {
            c v0 = new c(arg3);
            ReentrantLock v3 = this.c;
            v3.lock();
            try {
                while(!this.c(v0)) {
                    this.g.await();
                }
            }
            catch(Throwable v0_1) {
                v3.unlock();
                throw v0_1;
            }

            v3.unlock();
            return;
        }

        throw new NullPointerException();
    }

    public int drainTo(Collection arg2) {
        return this.drainTo(arg2, 2147483647);
    }

    public int drainTo(Collection arg4, int arg5) {
        if(arg4 == null) {
            goto label_22;
        }

        if((((d)arg4)) == this) {
            goto label_19;
        }

        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            arg5 = Math.min(arg5, this.d);
            int v1;
            for(v1 = 0; v1 < arg5; ++v1) {
                arg4.add(this.a.a);
                this.f();
            }
        }
        catch(Throwable v4) {
            goto label_17;
        }

        v0.unlock();
        return arg5;
    label_17:
        v0.unlock();
        throw v4;
    label_19:
        throw new IllegalArgumentException();
    label_22:
        throw new NullPointerException();
    }

    public Object e() {
        Object v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            v1_1 = this.a == null ? null : this.a.a;
        }
        catch(Throwable v1) {
            v0.unlock();
            throw v1;
        }

        v0.unlock();
        return v1_1;
    }

    public boolean e(Object arg5) {
        c v2;
        if(arg5 == null) {
            return 0;
        }

        ReentrantLock v1 = this.c;
        v1.lock();
        try {
            v2 = this.a;
            while(true) {
            label_6:
                if(v2 == null) {
                    goto label_16;
                }

                if(!arg5.equals(v2.a)) {
                    goto label_14;
                }

                this.a(v2);
                break;
            }
        }
        catch(Throwable v5) {
            goto label_19;
        }

        v1.unlock();
        return 1;
        try {
        label_14:
            v2 = v2.c;
            goto label_6;
        }
        catch(Throwable v5) {
            goto label_19;
        }

    label_16:
        v1.unlock();
        return 0;
    label_19:
        v1.unlock();
        throw v5;
    }

    public Object element() {
        return this.d();
    }

    private Object f() {
        c v0 = this.a;
        Object v1 = null;
        if(v0 == null) {
            return v1;
        }

        c v2 = v0.c;
        Object v3 = v0.a;
        v0.a = v1;
        v0.c = v0;
        this.a = v2;
        if(v2 == null) {
            this.b = ((c)v1);
        }
        else {
            v2.b = ((c)v1);
        }

        --this.d;
        this.g.signal();
        return v3;
    }

    private Object g() {
        c v0 = this.b;
        Object v1 = null;
        if(v0 == null) {
            return v1;
        }

        c v2 = v0.b;
        Object v3 = v0.a;
        v0.a = v1;
        v0.b = v0;
        this.b = v2;
        if(v2 == null) {
            this.a = ((c)v1);
        }
        else {
            v2.c = ((c)v1);
        }

        --this.d;
        this.g.signal();
        return v3;
    }

    public Iterator iterator() {
        return new b(this, null);
    }

    public boolean offer(Object arg1) {
        return this.c(arg1);
    }

    public boolean offer(Object arg1, long arg2, TimeUnit arg4) {
        return this.a(arg1, arg2, arg4);
    }

    public Object peek() {
        return this.e();
    }

    public Object poll() {
        return this.b();
    }

    public Object poll(long arg1, TimeUnit arg3) {
        return this.a(arg1, arg3);
    }

    public void put(Object arg1) {
        this.d(arg1);
    }

    public int remainingCapacity() {
        int v2;
        int v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            v1_1 = this.e;
            v2 = this.d;
        }
        catch(Throwable v1) {
            v0.unlock();
            throw v1;
        }

        v0.unlock();
        return v1_1 - v2;
    }

    public Object remove() {
        return this.a();
    }

    public boolean remove(Object arg1) {
        return this.e(arg1);
    }

    public int size() {
        int v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            v1_1 = this.d;
        }
        catch(Throwable v1) {
            v0.unlock();
            throw v1;
        }

        v0.unlock();
        return v1_1;
    }

    public Object take() {
        return this.c();
    }

    public Object[] toArray() {
        Object[] v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            v1_1 = new Object[this.d];
            int v2 = 0;
            c v3 = this.a;
            while(v3 != null) {
                v1_1[v2] = v3.a;
                v3 = v3.c;
                ++v2;
            }
        }
        catch(Throwable v1) {
            goto label_16;
        }

        v0.unlock();
        return v1_1;
    label_16:
        v0.unlock();
        throw v1;
    }

    public Object[] toArray(Object[] arg6) {
        Object v6_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            if(arg6.length < this.d) {
                v6_1 = Array.newInstance(arg6.getClass().getComponentType(), this.d);
            }

            int v1 = 0;
            c v2 = this.a;
            while(v2 != null) {
                ((Object[])v6_1)[v1] = v2.a;
                v2 = v2.c;
                ++v1;
            }

            if(((Object[])v6_1).length > v1) {
                ((Object[])v6_1)[v1] = null;
            }
        }
        catch(Throwable v6) {
            goto label_25;
        }

        v0.unlock();
        return ((Object[])v6_1);
    label_25:
        v0.unlock();
        throw v6;
    }

    public String toString() {
        String v3_1;
        StringBuilder v2;
        String v1_2;
        c v1_1;
        ReentrantLock v0 = this.c;
        v0.lock();
        try {
            v1_1 = this.a;
            if(v1_1 != null) {
                goto label_7;
            }

            v1_2 = "[]";
        }
        catch(Throwable v1) {
            goto label_28;
        }

        v0.unlock();
        return v1_2;
        try {
        label_7:
            v2 = new StringBuilder();
            v2.append('[');
            while(true) {
            label_11:
                Object v3 = v1_1.a;
                if((((d)v3)) == this) {
                    v3_1 = "(this Collection)";
                }

                v2.append(v3_1);
                v1_1 = v1_1.c;
                if(v1_1 != null) {
                    goto label_22;
                }

                v2.append(']');
                v1_2 = v2.toString();
                break;
            }
        }
        catch(Throwable v1) {
            goto label_28;
        }

        v0.unlock();
        return v1_2;
    label_22:
        char v3_2 = ',';
        try {
            v2.append(v3_2);
            v2.append(' ');
            goto label_11;
        }
        catch(Throwable v1) {
        label_28:
            v0.unlock();
            throw v1;
        }
    }
}

