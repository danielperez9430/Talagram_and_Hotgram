package android.arch.lifecycle;

public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
    private final c[] a;

    CompositeGeneratedAdaptersObserver(c[] arg1) {
        super();
        this.a = arg1;
    }

    public void a(g arg7, a arg8) {
        l v0 = new l();
        c[] v1 = this.a;
        int v2 = v1.length;
        int v3 = 0;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            v1[v4].a(arg7, arg8, false, v0);
        }

        v1 = this.a;
        v2 = v1.length;
        while(v3 < v2) {
            v1[v3].a(arg7, arg8, true, v0);
            ++v3;
        }
    }
}

