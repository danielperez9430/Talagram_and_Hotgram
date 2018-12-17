package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;

public final class bm {
    public final ax a;
    public ByteBuffer b;
    public int c;
    public int d;
    public long e;
    private final int f;

    public bm(int arg2) {
        super();
        this.a = new ax();
        this.f = arg2;
    }

    public void a(int arg4) {
        if(this.b == null) {
            this.b = this.b(arg4);
            return;
        }

        int v0 = this.b.capacity();
        int v1 = this.b.position();
        arg4 += v1;
        if(v0 >= arg4) {
            return;
        }

        ByteBuffer v4 = this.b(arg4);
        if(v1 > 0) {
            this.b.position(0);
            this.b.limit(v1);
            v4.put(this.b);
        }

        this.b = v4;
    }

    public boolean a() {
        boolean v0 = (this.d & 2) != 0 ? true : false;
        return v0;
    }

    private ByteBuffer b(int arg5) {
        if(this.f == 1) {
            return ByteBuffer.allocate(arg5);
        }

        if(this.f == 2) {
            return ByteBuffer.allocateDirect(arg5);
        }

        int v0 = this.b == null ? 0 : this.b.capacity();
        StringBuilder v3 = new StringBuilder(44);
        v3.append("Buffer too small (");
        v3.append(v0);
        v3.append(" < ");
        v3.append(arg5);
        v3.append(")");
        throw new IllegalStateException(v3.toString());
    }

    public boolean b() {
        boolean v0 = (this.d & 134217728) != 0 ? true : false;
        return v0;
    }

    public boolean c() {
        boolean v1 = true;
        if((this.d & 1) != 0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void d() {
        if(this.b != null) {
            this.b.clear();
        }
    }
}

