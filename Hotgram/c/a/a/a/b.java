package c.a.a.a;

import android.util.Log;

public class b implements l {
    private int a;

    public b() {
        super();
        this.a = 4;
    }

    public b(int arg1) {
        super();
        this.a = arg1;
    }

    public void a(int arg2, String arg3, String arg4) {
        this.a(arg2, arg3, arg4, false);
    }

    public void a(int arg1, String arg2, String arg3, boolean arg4) {
        if((arg4) || (this.a(arg2, arg1))) {
            Log.println(arg1, arg2, arg3);
        }
    }

    public boolean a(String arg1, int arg2) {
        boolean v1 = this.a <= arg2 ? true : false;
        return v1;
    }

    public void a(String arg2, String arg3) {
        this.a(arg2, arg3, null);
    }

    public void a(String arg2, String arg3, Throwable arg4) {
        if(this.a(arg2, 3)) {
            Log.d(arg2, arg3, arg4);
        }
    }

    public void b(String arg2, String arg3) {
        this.b(arg2, arg3, null);
    }

    public void b(String arg2, String arg3, Throwable arg4) {
        if(this.a(arg2, 2)) {
            Log.v(arg2, arg3, arg4);
        }
    }

    public void c(String arg2, String arg3) {
        this.c(arg2, arg3, null);
    }

    public void c(String arg2, String arg3, Throwable arg4) {
        if(this.a(arg2, 4)) {
            Log.i(arg2, arg3, arg4);
        }
    }

    public void d(String arg2, String arg3) {
        this.d(arg2, arg3, null);
    }

    public void d(String arg2, String arg3, Throwable arg4) {
        if(this.a(arg2, 5)) {
            Log.w(arg2, arg3, arg4);
        }
    }

    public void e(String arg2, String arg3) {
        this.e(arg2, arg3, null);
    }

    public void e(String arg2, String arg3, Throwable arg4) {
        if(this.a(arg2, 6)) {
            Log.e(arg2, arg3, arg4);
        }
    }
}

