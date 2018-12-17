package android.support.v4.content;

import android.support.v4.f.d;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class b {
    public interface a {
    }

    int a;
    a b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;

    public String a(Object arg3) {
        StringBuilder v0 = new StringBuilder(64);
        d.a(arg3, v0);
        v0.append("}");
        return v0.toString();
    }

    public final void a() {
        this.c = true;
        this.e = false;
        this.d = false;
        this.b();
    }

    public void a(a arg2) {
        if(this.b != null) {
            if(this.b == arg2) {
                this.b = null;
                return;
            }

            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }

        throw new IllegalStateException("No listener register");
    }

    @Deprecated public void a(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
        arg3.print(arg1);
        arg3.print("mId=");
        arg3.print(this.a);
        arg3.print(" mListener=");
        arg3.println(this.b);
        if((this.c) || (this.f) || (this.g)) {
            arg3.print(arg1);
            arg3.print("mStarted=");
            arg3.print(this.c);
            arg3.print(" mContentChanged=");
            arg3.print(this.f);
            arg3.print(" mProcessingChange=");
            arg3.println(this.g);
        }

        if((this.d) || (this.e)) {
            arg3.print(arg1);
            arg3.print("mAbandoned=");
            arg3.print(this.d);
            arg3.print(" mReset=");
            arg3.println(this.e);
        }
    }

    protected void b() {
    }

    public boolean c() {
        return this.d();
    }

    protected boolean d() {
        return 0;
    }

    public void e() {
        this.c = false;
        this.f();
    }

    protected void f() {
    }

    public void g() {
        this.d = true;
        this.h();
    }

    protected void h() {
    }

    public void i() {
        this.j();
        this.e = true;
        this.c = false;
        this.d = false;
        this.f = false;
        this.g = false;
    }

    protected void j() {
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(64);
        d.a(this, v0);
        v0.append(" id=");
        v0.append(this.a);
        v0.append("}");
        return v0.toString();
    }
}

