package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

public class l {
    private ViewParent a;
    private ViewParent b;
    private final View c;
    private boolean d;
    private int[] e;

    public l(View arg1) {
        super();
        this.c = arg1;
    }

    private void a(int arg1, ViewParent arg2) {
        switch(arg1) {
            case 0: {
                this.a = arg2;
                break;
            }
            case 1: {
                this.b = arg2;
                break;
            }
            default: {
                break;
            }
        }
    }

    public void a(boolean arg2) {
        if(this.d) {
            t.z(this.c);
        }

        this.d = arg2;
    }

    public boolean a() {
        return this.d;
    }

    public boolean a(float arg3, float arg4) {
        if(this.a()) {
            ViewParent v0 = this.d(0);
            if(v0 != null) {
                return w.a(v0, this.c, arg3, arg4);
            }
        }

        return 0;
    }

    public boolean a(float arg3, float arg4, boolean arg5) {
        if(this.a()) {
            ViewParent v0 = this.d(0);
            if(v0 != null) {
                return w.a(v0, this.c, arg3, arg4, arg5);
            }
        }

        return 0;
    }

    public boolean a(int arg1) {
        boolean v1 = this.d(arg1) != null ? true : false;
        return v1;
    }

    public boolean a(int arg5, int arg6) {
        if(this.a(arg6)) {
            return 1;
        }

        if(this.a()) {
            ViewParent v0 = this.c.getParent();
            View v2 = this.c;
            while(v0 != null) {
                if(w.a(v0, v2, this.c, arg5, arg6)) {
                    this.a(arg6, v0);
                    w.b(v0, v2, this.c, arg5, arg6);
                    return 1;
                }
                else {
                    if((v0 instanceof View)) {
                        ViewParent v2_1 = v0;
                    }

                    v0 = v0.getParent();
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    public boolean a(int arg8, int arg9, int arg10, int arg11, int[] arg12) {
        return this.a(arg8, arg9, arg10, arg11, arg12, 0);
    }

    public boolean a(int arg15, int arg16, int arg17, int arg18, int[] arg19, int arg20) {
        int v13;
        int v12;
        l v0 = this;
        int[] v1 = arg19;
        if(this.a()) {
            ViewParent v4 = this.d(arg20);
            if(v4 == null) {
                return 0;
            }
            else {
                if(arg15 == 0 && arg16 == 0 && arg17 == 0) {
                    if(arg18 != 0) {
                    }
                    else {
                        if(v1 != null) {
                            v1[0] = 0;
                            v1[1] = 0;
                        }
                        else {
                        }

                        return 0;
                    }
                }

                if(v1 != null) {
                    v0.c.getLocationInWindow(v1);
                    v12 = v1[0];
                    v13 = v1[1];
                }
                else {
                    v12 = 0;
                    v13 = 0;
                }

                w.a(v4, v0.c, arg15, arg16, arg17, arg18, arg20);
                if(v1 != null) {
                    v0.c.getLocationInWindow(v1);
                    v1[0] -= v12;
                    v1[1] -= v13;
                }

                return 1;
            }
        }

        return 0;
    }

    public boolean a(int arg7, int arg8, int[] arg9, int[] arg10) {
        return this.a(arg7, arg8, arg9, arg10, 0);
    }

    public boolean a(int arg11, int arg12, int[] arg13, int[] arg14, int arg15) {
        int v9;
        int v8;
        if(this.a()) {
            ViewParent v2 = this.d(arg15);
            if(v2 == null) {
                return 0;
            }
            else {
                boolean v0 = true;
                if(arg11 == 0) {
                    if(arg12 != 0) {
                    }
                    else {
                        if(arg14 != null) {
                            arg14[0] = 0;
                            arg14[1] = 0;
                        }
                        else {
                        }

                        return 0;
                    }
                }

                if(arg14 != null) {
                    this.c.getLocationInWindow(arg14);
                    v8 = arg14[0];
                    v9 = arg14[1];
                }
                else {
                    v8 = 0;
                    v9 = 0;
                }

                if(arg13 == null) {
                    if(this.e == null) {
                        this.e = new int[2];
                    }

                    arg13 = this.e;
                }

                arg13[0] = 0;
                arg13[1] = 0;
                w.a(v2, this.c, arg11, arg12, arg13, arg15);
                if(arg14 != null) {
                    this.c.getLocationInWindow(arg14);
                    arg14[0] -= v8;
                    arg14[1] -= v9;
                }

                if(arg13[0] == 0) {
                    if(arg13[1] != 0) {
                    }
                    else {
                        v0 = false;
                    }
                }

                return v0;
            }
        }

        return 0;
    }

    public boolean b() {
        return this.a(0);
    }

    public boolean b(int arg2) {
        return this.a(arg2, 0);
    }

    public void c() {
        this.c(0);
    }

    public void c(int arg3) {
        ViewParent v0 = this.d(arg3);
        if(v0 != null) {
            w.a(v0, this.c, arg3);
            this.a(arg3, null);
        }
    }

    private ViewParent d(int arg1) {
        switch(arg1) {
            case 0: {
                goto label_5;
            }
            case 1: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return this.b;
    label_5:
        return this.a;
    }
}

