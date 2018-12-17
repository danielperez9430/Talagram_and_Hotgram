package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;

public abstract class jy implements jx {
    protected MotionEvent a;
    protected DisplayMetrics b;
    protected kd c;
    private ke d;

    protected jy(Context arg1, kd arg2, ke arg3) {
        super();
        this.c = arg2;
        this.d = arg3;
        try {
            this.b = arg1.getResources().getDisplayMetrics();
        }
        catch(UnsupportedOperationException ) {
            this.b = new DisplayMetrics();
            this.b.density = 1f;
        }
    }

    private String a(Context arg2, String arg3, boolean arg4) {
        String v2_2;
        byte[] v2_1;
        int v0 = 7;
        try {
            __monitor_enter(this);
        }
        catch(IOException ) {
            goto label_19;
        }
        catch(UnsupportedEncodingException ) {
            goto label_22;
        }

        try {
            this.a();
            if(arg4) {
                this.c(arg2);
            }
            else {
                this.b(arg2);
            }

            v2_1 = this.b();
            __monitor_exit(this);
            goto label_9;
        }
        catch(Throwable v2) {
            try {
            label_17:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_17;
            }

            try {
                throw v2;
            label_9:
                if(v2_1.length == 0) {
                    v2_2 = Integer.toString(5);
                    return v2_2;
                }

                v2_2 = this.a(v2_1, arg3);
            }
            catch(IOException ) {
            label_19:
                v2_2 = Integer.toString(3);
            }
            catch(UnsupportedEncodingException ) {
            label_22:
                v2_2 = Integer.toString(v0);
            }
        }

        return v2_2;
    }

    private void a() {
        this.d.a();
    }

    String a(byte[] arg5, String arg6) {
        ByteBuffer v5;
        byte[] v0;
        int v1 = 239;
        if(arg5.length > v1) {
            this.a();
            this.a(20, 1);
            arg5 = this.b();
        }

        int v2 = 240;
        if(arg5.length < v1) {
            v0 = new byte[v1 - arg5.length];
            new SecureRandom().nextBytes(v0);
            v5 = ByteBuffer.allocate(v2).put(((byte)arg5.length)).put(arg5).put(v0);
        }
        else {
            v5 = ByteBuffer.allocate(v2).put(((byte)arg5.length)).put(arg5);
        }

        arg5 = v5.array();
        MessageDigest v0_1 = MessageDigest.getInstance("MD5");
        v0_1.update(arg5);
        arg5 = ByteBuffer.allocate(256).put(v0_1.digest()).put(arg5).array();
        v0 = new byte[256];
        new jw().a(arg5, v0);
        if(arg6 != null && arg6.length() > 0) {
            this.a(arg6, v0);
        }

        return this.c.a(v0, true);
    }

    public String a(Context arg3) {
        return this.a(arg3, null, false);
    }

    public String a(Context arg2, String arg3) {
        return this.a(arg2, arg3, true);
    }

    protected String a(String arg5) {
        if(arg5 != null && (arg5.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$"))) {
            UUID v5 = UUID.fromString(arg5);
            byte[] v0 = new byte[16];
            ByteBuffer v1 = ByteBuffer.wrap(v0);
            v1.putLong(v5.getMostSignificantBits());
            v1.putLong(v5.getLeastSignificantBits());
            arg5 = this.c.a(v0, true);
        }

        return arg5;
    }

    protected void a(int arg2, long arg3) {
        this.d.a(arg2, arg3);
    }

    void a(String arg3, byte[] arg4) {
        int v1 = 32;
        if(arg3.length() > v1) {
            arg3 = arg3.substring(0, v1);
        }

        new lq(arg3.getBytes("UTF-8")).a(arg4);
    }

    protected void a(int arg2, String arg3) {
        this.d.a(arg2, arg3);
    }

    protected abstract void b(Context arg1);

    private byte[] b() {
        return this.d.b();
    }

    protected abstract void c(Context arg1);
}

