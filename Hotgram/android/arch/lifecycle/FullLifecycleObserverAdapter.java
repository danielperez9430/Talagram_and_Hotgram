package android.arch.lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    class android.arch.lifecycle.FullLifecycleObserverAdapter$1 {
        static {
            android.arch.lifecycle.FullLifecycleObserverAdapter$1.a = new int[a.values().length];
            try {
                android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_CREATE.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_START.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_RESUME.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_PAUSE.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_STOP.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_DESTROY.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[a.ON_ANY.ordinal()] = 7;
                                        return;
                                    }
                                    catch(NoSuchFieldError ) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private final FullLifecycleObserver a;

    FullLifecycleObserverAdapter(FullLifecycleObserver arg1) {
        super();
        this.a = arg1;
    }

    public void a(g arg2, a arg3) {
        switch(android.arch.lifecycle.FullLifecycleObserverAdapter$1.a[arg3.ordinal()]) {
            case 1: {
                goto label_24;
            }
            case 2: {
                goto label_21;
            }
            case 3: {
                goto label_18;
            }
            case 4: {
                goto label_15;
            }
            case 5: {
                goto label_12;
            }
            case 6: {
                goto label_9;
            }
            case 7: {
                goto label_5;
            }
        }

        return;
    label_18:
        this.a.c(arg2);
        return;
    label_21:
        this.a.b(arg2);
        return;
    label_5:
        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
    label_24:
        this.a.a(arg2);
        return;
    label_9:
        this.a.f(arg2);
        return;
    label_12:
        this.a.e(arg2);
        return;
    label_15:
        this.a.d(arg2);
    }
}

