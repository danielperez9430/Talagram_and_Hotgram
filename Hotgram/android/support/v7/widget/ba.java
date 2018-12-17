package android.support.v7.widget;

class ba {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;

    ba() {
        super();
        this.a = 0;
        this.b = 0;
        this.c = -2147483648;
        this.d = -2147483648;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.h = false;
    }

    public void a(int arg3, int arg4) {
        this.c = arg3;
        this.d = arg4;
        this.h = true;
        int v1 = -2147483648;
        if(this.g) {
            if(arg4 != v1) {
                this.a = arg4;
            }

            if(arg3 == v1) {
                return;
            }

            this.b = arg3;
        }
        else {
            if(arg3 != v1) {
                this.a = arg3;
            }

            if(arg4 == v1) {
                return;
            }

            this.b = arg4;
        }
    }

    public int a() {
        return this.a;
    }

    public void a(boolean arg2) {
        int v2;
        if(arg2 == this.g) {
            return;
        }

        this.g = arg2;
        if(this.h) {
            int v0 = -2147483648;
            if(arg2) {
                v2 = this.d != v0 ? this.d : this.e;
                this.a = v2;
                if(this.c == v0) {
                    goto label_30;
                }

                v2 = this.c;
            }
            else {
                v2 = this.c != v0 ? this.c : this.e;
                this.a = v2;
                if(this.d == v0) {
                    goto label_30;
                }

                v2 = this.d;
            }
        }
        else {
            this.a = this.e;
        label_30:
            v2 = this.f;
        }

        this.b = v2;
    }

    public void b(int arg2, int arg3) {
        this.h = false;
        int v0 = -2147483648;
        if(arg2 != v0) {
            this.e = arg2;
            this.a = arg2;
        }

        if(arg3 != v0) {
            this.f = arg3;
            this.b = arg3;
        }
    }

    public int b() {
        return this.b;
    }

    public int c() {
        int v0 = this.g ? this.b : this.a;
        return v0;
    }

    public int d() {
        int v0 = this.g ? this.a : this.b;
        return v0;
    }
}

