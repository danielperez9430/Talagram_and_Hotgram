package android.support.g.b.a;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build$VERSION;
import android.view.inputmethod.InputContentInfo;

public final class c {
    final class a implements android.support.g.b.a.c$c {
        final InputContentInfo a;

        a(Uri arg2, ClipDescription arg3, Uri arg4) {
            super();
            this.a = new InputContentInfo(arg2, arg3, arg4);
        }

        a(Object arg1) {
            super();
            this.a = ((InputContentInfo)arg1);
        }

        public Uri a() {
            return this.a.getContentUri();
        }

        public ClipDescription b() {
            return this.a.getDescription();
        }

        public void c() {
            this.a.requestPermission();
        }

        public void d() {
            this.a.releasePermission();
        }
    }

    final class b implements android.support.g.b.a.c$c {
        private final Uri a;
        private final ClipDescription b;
        private final Uri c;

        b(Uri arg1, ClipDescription arg2, Uri arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public Uri a() {
            return this.a;
        }

        public ClipDescription b() {
            return this.b;
        }

        public void c() {
        }

        public void d() {
        }
    }

    interface android.support.g.b.a.c$c {
        Uri a();

        ClipDescription b();

        void c();

        void d();
    }

    private final android.support.g.b.a.c$c a;

    public c(Uri arg3, ClipDescription arg4, Uri arg5) {
        b v0_1;
        super();
        if(Build$VERSION.SDK_INT >= 25) {
            a v0 = new a(arg3, arg4, arg5);
        }
        else {
            v0_1 = new b(arg3, arg4, arg5);
        }

        this.a = ((android.support.g.b.a.c$c)v0_1);
    }

    private c(android.support.g.b.a.c$c arg1) {
        super();
        this.a = arg1;
    }

    public static c a(Object arg3) {
        c v0 = null;
        if(arg3 == null) {
            return v0;
        }

        if(Build$VERSION.SDK_INT < 25) {
            return v0;
        }

        return new c(new a(arg3));
    }

    public Uri a() {
        return this.a.a();
    }

    public ClipDescription b() {
        return this.a.b();
    }

    public void c() {
        this.a.c();
    }

    public void d() {
        this.a.d();
    }
}

