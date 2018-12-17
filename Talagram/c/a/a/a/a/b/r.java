package c.a.a.a.a.b;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class r implements Closeable {
    class a {
        static final a a;
        final int b;
        final int c;

        static {
            a.a = new a(0, 0);
        }

        a(int arg1, int arg2) {
            super();
            this.b = arg1;
            this.c = arg2;
        }

        public String toString() {
            return this.getClass().getSimpleName() + "[position = " + this.b + ", length = " + this.c + "]";
        }
    }

    final class b extends InputStream {
        private int b;
        private int c;

        b(r arg1, a arg2, c.a.a.a.a.b.r$1 arg3) {
            this(arg1, arg2);
        }

        private b(r arg2, a arg3) {
            this.a = arg2;
            super();
            this.b = r.a(arg2, arg3.b + 4);
            this.c = arg3.c;
        }

        public int read() {
            if(this.c == 0) {
                return -1;
            }

            r.a(this.a).seek(((long)this.b));
            int v0 = r.a(this.a).read();
            this.b = r.a(this.a, this.b + 1);
            --this.c;
            return v0;
        }

        public int read(byte[] arg3, int arg4, int arg5) {
            r.a(arg3, "buffer");
            if((arg4 | arg5) >= 0 && arg5 <= arg3.length - arg4) {
                if(this.c > 0) {
                    if(arg5 > this.c) {
                        arg5 = this.c;
                    }

                    r.a(this.a, this.b, arg3, arg4, arg5);
                    this.b = r.a(this.a, this.b + arg5);
                    this.c -= arg5;
                    return arg5;
                }
                else {
                    return -1;
                }
            }

            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public interface c {
        void a(InputStream arg1, int arg2);
    }

    int a;
    private static final Logger b;
    private final RandomAccessFile c;
    private int d;
    private a e;
    private a f;
    private final byte[] g;

    static {
        r.b = Logger.getLogger(r.class.getName());
    }

    public r(File arg2) {
        super();
        this.g = new byte[16];
        if(!arg2.exists()) {
            r.a(arg2);
        }

        this.c = r.b(arg2);
        this.c();
    }

    private static void a(File arg4) {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg4.getPath());
        v1.append(".tmp");
        File v0 = new File(v1.toString());
        RandomAccessFile v1_1 = r.b(v0);
        long v2 = 4096;
        try {
            v1_1.setLength(v2);
            v1_1.seek(0);
            byte[] v2_1 = new byte[16];
            r.a(v2_1, new int[]{4096, 0, 0, 0});
            v1_1.write(v2_1);
        }
        catch(Throwable v4) {
            v1_1.close();
            throw v4;
        }

        v1_1.close();
        if(v0.renameTo(arg4)) {
            return;
        }

        throw new IOException("Rename failed!");
    }

    static int a(r arg0, int arg1) {
        return arg0.b(arg1);
    }

    private static int a(byte[] arg2, int arg3) {
        return ((arg2[arg3] & 255) << 24) + ((arg2[arg3 + 1] & 255) << 16) + ((arg2[arg3 + 2] & 255) << 8) + (arg2[arg3 + 3] & 255);
    }

    private a a(int arg4) {
        if(arg4 == 0) {
            return a.a;
        }

        this.c.seek(((long)arg4));
        return new a(arg4, this.c.readInt());
    }

    static RandomAccessFile a(r arg0) {
        return arg0.c;
    }

    static Object a(Object arg0, String arg1) {
        return r.b(arg0, arg1);
    }

    private void a(int arg4, int arg5, int arg6, int arg7) {
        r.a(this.g, new int[]{arg4, arg5, arg6, arg7});
        this.c.seek(0);
        this.c.write(this.g);
    }

    private static void a(byte[] arg4, int[] arg5) {
        int v0 = arg5.length;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            r.b(arg4, v2, arg5[v1]);
            v2 += 4;
            ++v1;
        }
    }

    private void a(int arg5, byte[] arg6, int arg7, int arg8) {
        RandomAccessFile v5;
        arg5 = this.b(arg5);
        if(arg5 + arg8 <= this.a) {
            this.c.seek(((long)arg5));
            v5 = this.c;
        }
        else {
            int v0 = this.a - arg5;
            this.c.seek(((long)arg5));
            this.c.write(arg6, arg7, v0);
            this.c.seek(16);
            v5 = this.c;
            arg7 += v0;
            arg8 -= v0;
        }

        v5.write(arg6, arg7, arg8);
    }

    static void a(r arg0, int arg1, byte[] arg2, int arg3, int arg4) {
        arg0.b(arg1, arg2, arg3, arg4);
    }

    public int a() {
        int v1 = 16;
        if(this.d == 0) {
            return v1;
        }

        if(this.f.b >= this.e.b) {
            return this.f.b - this.e.b + 4 + this.f.c + v1;
        }

        return this.f.b + 4 + this.f.c + this.a - this.e.b;
    }

    public void a(c arg5) {
        __monitor_enter(this);
        try {
            int v0 = this.e.b;
            int v1;
            for(v1 = 0; v1 < this.d; ++v1) {
                a v0_1 = this.a(((int)v0_1));
                arg5.a(new b(this, v0_1, null), v0_1.c);
                v0 = this.b(v0_1.b + 4 + v0_1.c);
            }
        }
        catch(Throwable v5) {
            goto label_22;
        }

        __monitor_exit(this);
        return;
    label_22:
        __monitor_exit(this);
        throw v5;
    }

    public void a(byte[] arg3) {
        this.a(arg3, 0, arg3.length);
    }

    public void a(byte[] arg7, int arg8, int arg9) {
        __monitor_enter(this);
        try {
            r.b(arg7, "buffer");
            if((arg8 | arg9) >= 0 && arg9 <= arg7.length - arg8) {
                this.c(arg9);
                boolean v0 = this.b();
                int v1 = 4;
                int v2 = v0 ? 16 : this.b(this.f.b + v1 + this.f.c);
                a v3 = new a(v2, arg9);
                r.b(this.g, 0, arg9);
                this.a(v3.b, this.g, 0, v1);
                this.a(v3.b + v1, arg7, arg8, arg9);
                int v7_1 = v0 ? v3.b : this.e.b;
                this.a(this.a, this.d + 1, v7_1, v3.b);
                this.f = v3;
                ++this.d;
                if(v0) {
                    this.e = this.f;
                }

                goto label_49;
            }

            goto label_51;
        }
        catch(Throwable v7) {
            goto label_55;
        }

    label_49:
        __monitor_exit(this);
        return;
        try {
        label_51:
            throw new IndexOutOfBoundsException();
        }
        catch(Throwable v7) {
        label_55:
            __monitor_exit(this);
            throw v7;
        }
    }

    public boolean a(int arg2, int arg3) {
        boolean v2 = this.a() + 4 + arg2 <= arg3 ? true : false;
        return v2;
    }

    private static RandomAccessFile b(File arg2) {
        return new RandomAccessFile(arg2, "rwd");
    }

    private int b(int arg2) {
        if(arg2 < this.a) {
        }
        else {
            arg2 = arg2 + 16 - this.a;
        }

        return arg2;
    }

    private static Object b(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }

    private void b(int arg5, byte[] arg6, int arg7, int arg8) {
        RandomAccessFile v5;
        arg5 = this.b(arg5);
        if(arg5 + arg8 <= this.a) {
            this.c.seek(((long)arg5));
            v5 = this.c;
        }
        else {
            int v0 = this.a - arg5;
            this.c.seek(((long)arg5));
            this.c.readFully(arg6, arg7, v0);
            this.c.seek(16);
            v5 = this.c;
            arg7 += v0;
            arg8 -= v0;
        }

        v5.readFully(arg6, arg7, arg8);
    }

    private static void b(byte[] arg2, int arg3, int arg4) {
        arg2[arg3] = ((byte)(arg4 >> 24));
        arg2[arg3 + 1] = ((byte)(arg4 >> 16));
        arg2[arg3 + 2] = ((byte)(arg4 >> 8));
        arg2[arg3 + 3] = ((byte)arg4);
    }

    public boolean b() {
        __monitor_enter(this);
        try {
            if(this.d != 0) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        boolean v0_1 = true;
        goto label_6;
    label_5:
        v0_1 = false;
    label_6:
        __monitor_exit(this);
        return v0_1;
    }

    private void c() {
        this.c.seek(0);
        this.c.readFully(this.g);
        this.a = r.a(this.g, 0);
        if((((long)this.a)) <= this.c.length()) {
            this.d = r.a(this.g, 4);
            int v0 = r.a(this.g, 8);
            int v1 = r.a(this.g, 12);
            this.e = this.a(v0);
            this.f = this.a(v1);
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("File is truncated. Expected length: ");
        v1_1.append(this.a);
        v1_1.append(", Actual length: ");
        v1_1.append(this.c.length());
        throw new IOException(v1_1.toString());
    }

    private void c(int arg11) {
        arg11 += 4;
        int v0 = this.d();
        if(v0 >= arg11) {
            return;
        }

        int v1 = this.a;
        do {
            v0 += v1;
            v1 <<= 1;
        }
        while(v0 < arg11);

        this.d(v1);
        arg11 = this.b(this.f.b + 4 + this.f.c);
        if(arg11 < this.e.b) {
            FileChannel v7 = this.c.getChannel();
            v7.position(((long)this.a));
            long v8 = ((long)(arg11 - 4));
            if(v7.transferTo(16, v8, ((WritableByteChannel)v7)) == v8) {
            }
            else {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }

        if(this.f.b < this.e.b) {
            arg11 = this.a + this.f.b - 16;
            this.a(v1, this.d, this.e.b, arg11);
            this.f = new a(arg11, this.f.c);
        }
        else {
            this.a(v1, this.d, this.e.b, this.f.b);
        }

        this.a = v1;
    }

    public void close() {
        __monitor_enter(this);
        try {
            this.c.close();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private int d() {
        return this.a - this.a();
    }

    private void d(int arg4) {
        this.c.setLength(((long)arg4));
        this.c.getChannel().force(true);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.getClass().getSimpleName());
        v0.append('[');
        v0.append("fileLength=");
        v0.append(this.a);
        v0.append(", size=");
        v0.append(this.d);
        v0.append(", first=");
        v0.append(this.e);
        v0.append(", last=");
        v0.append(this.f);
        v0.append(", element lengths=[");
        try {
            this.a(new c(v0) {
                boolean a;

                public void a(InputStream arg2, int arg3) {
                    if(this.a) {
                        this.a = false;
                    }
                    else {
                        this.b.append(", ");
                    }

                    this.b.append(arg3);
                }
            });
        }
        catch(IOException v1) {
            r.b.log(Level.WARNING, "read error", ((Throwable)v1));
        }

        v0.append("]]");
        return v0.toString();
    }
}

