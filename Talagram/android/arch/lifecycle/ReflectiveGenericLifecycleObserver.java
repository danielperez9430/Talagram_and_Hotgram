package android.arch.lifecycle;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {
    private final Object a;
    private final a b;

    ReflectiveGenericLifecycleObserver(Object arg2) {
        super();
        this.a = arg2;
        this.b = android.arch.lifecycle.a.a.b(this.a.getClass());
    }

    public void a(g arg3, android.arch.lifecycle.d$a arg4) {
        this.b.a(arg3, arg4, this.a);
    }
}

