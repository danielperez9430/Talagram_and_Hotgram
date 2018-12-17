package android.support.v4.view;

import android.os.Build$VERSION;

public class ab {
    private final Object a;

    private ab(Object arg1) {
        super();
        this.a = arg1;
    }

    public int a() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.a.getSystemWindowInsetLeft();
        }

        return 0;
    }

    public ab a(int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 20) {
            return new ab(this.a.replaceSystemWindowInsets(arg3, arg4, arg5, arg6));
        }

        return null;
    }

    static ab a(Object arg1) {
        ab v1 = arg1 == null ? null : new ab(arg1);
        return v1;
    }

    static Object a(ab arg0) {
        Object v0 = arg0 == null ? null : arg0.a;
        return v0;
    }

    public int b() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.a.getSystemWindowInsetTop();
        }

        return 0;
    }

    public int c() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.a.getSystemWindowInsetRight();
        }

        return 0;
    }

    public int d() {
        if(Build$VERSION.SDK_INT >= 20) {
            return this.a.getSystemWindowInsetBottom();
        }

        return 0;
    }

    public boolean e() {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.a.isConsumed();
        }

        return 0;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((ab)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.a != null) {
                    v0 = this.a.equals(((ab)arg5).a);
                }
                else if(((ab)arg5).a == null) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v0 = this.a == null ? 0 : this.a.hashCode();
        return v0;
    }
}

