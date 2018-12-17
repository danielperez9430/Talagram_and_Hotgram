package android.arch.lifecycle;

import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map$Entry;

public class h extends d {
    class android.arch.lifecycle.h$1 {
        static {
            android.arch.lifecycle.h$1.b = new int[b.values().length];
            try {
                android.arch.lifecycle.h$1.b[b.b.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
            label_9:
                int v1 = 2;
                try {
                    android.arch.lifecycle.h$1.b[b.c.ordinal()] = v1;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                label_14:
                    int v2 = 3;
                    try {
                        android.arch.lifecycle.h$1.b[b.d.ordinal()] = v2;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                    label_19:
                        int v3 = 4;
                        try {
                            android.arch.lifecycle.h$1.b[b.e.ordinal()] = v3;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                        label_24:
                            int v4 = 5;
                            try {
                                android.arch.lifecycle.h$1.b[b.a.ordinal()] = v4;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                            label_29:
                                android.arch.lifecycle.h$1.a = new int[a.values().length];
                                try {
                                    android.arch.lifecycle.h$1.a[a.ON_CREATE.ordinal()] = 1;
                                    goto label_37;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_37:
                                        android.arch.lifecycle.h$1.a[a.ON_STOP.ordinal()] = v1;
                                        goto label_41;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_41:
                                            android.arch.lifecycle.h$1.a[a.ON_START.ordinal()] = v2;
                                            goto label_45;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_45:
                                                android.arch.lifecycle.h$1.a[a.ON_PAUSE.ordinal()] = v3;
                                                goto label_49;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_49:
                                                    android.arch.lifecycle.h$1.a[a.ON_RESUME.ordinal()] = v4;
                                                    goto label_53;
                                                }
                                                catch(NoSuchFieldError ) {
                                                    try {
                                                    label_53:
                                                        android.arch.lifecycle.h$1.a[a.ON_DESTROY.ordinal()] = 6;
                                                        goto label_58;
                                                    }
                                                    catch(NoSuchFieldError ) {
                                                        try {
                                                        label_58:
                                                            android.arch.lifecycle.h$1.a[a.ON_ANY.ordinal()] = 7;
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
                    }
                }
            }
        }
    }

    class android.arch.lifecycle.h$a {
        b a;
        GenericLifecycleObserver b;

        android.arch.lifecycle.h$a(f arg1, b arg2) {
            super();
            this.b = k.a(arg1);
            this.a = arg2;
        }

        void a(g arg3, a arg4) {
            b v0 = h.b(arg4);
            this.a = h.a(this.a, v0);
            this.b.a(arg3, arg4);
            this.a = v0;
        }
    }

    private android.arch.a.b.a a;
    private b b;
    private final WeakReference c;
    private int d;
    private boolean e;
    private boolean f;
    private ArrayList g;

    public h(g arg2) {
        super();
        this.a = new android.arch.a.b.a();
        this.d = 0;
        this.e = false;
        this.f = false;
        this.g = new ArrayList();
        this.c = new WeakReference(arg2);
        this.b = b.b;
    }

    public void a(b arg1) {
        this.b(arg1);
    }

    public void a(a arg1) {
        this.b(h.b(arg1));
    }

    static b a(b arg1, b arg2) {
        if(arg2 != null && arg2.compareTo(((Enum)arg1)) < 0) {
            arg1 = arg2;
        }

        return arg1;
    }

    private void a(g arg6) {
        Object v2;
        android.arch.a.b.b$d v0 = this.a.c();
        do {
        label_2:
            if((((Iterator)v0).hasNext()) && !this.f) {
                Object v1 = ((Iterator)v0).next();
                v2 = ((Map$Entry)v1).getValue();
                while(true) {
                label_8:
                    if(((android.arch.lifecycle.h$a)v2).a.compareTo(this.b) >= 0) {
                        goto label_2;
                    }

                    if(this.f) {
                        goto label_2;
                    }

                    if(!this.a.c(((Map$Entry)v1).getKey())) {
                        goto label_2;
                    }

                    goto label_18;
                }
            }

            return;
        }
        while(true);

    label_18:
        this.c(((android.arch.lifecycle.h$a)v2).a);
        ((android.arch.lifecycle.h$a)v2).a(arg6, h.e(((android.arch.lifecycle.h$a)v2).a));
        this.c();
        goto label_8;
    }

    public b a() {
        return this.b;
    }

    public void a(f arg7) {
        b v0 = this.b == b.a ? b.a : b.b;
        android.arch.lifecycle.h$a v1 = new android.arch.lifecycle.h$a(arg7, v0);
        if(this.a.a(arg7, v1) != null) {
            return;
        }

        Object v0_1 = this.c.get();
        if(v0_1 == null) {
            return;
        }

        int v2 = this.d != 0 || (this.e) ? 1 : 0;
        b v4 = this.c(arg7);
        ++this.d;
        while(v1.a.compareTo(((Enum)v4)) < 0) {
            if(!this.a.c(arg7)) {
                break;
            }

            this.c(v1.a);
            v1.a(((g)v0_1), h.e(v1.a));
            this.c();
            v4 = this.c(arg7);
        }

        if(v2 == 0) {
            this.d();
        }

        --this.d;
    }

    static b b(a arg3) {
        switch(android.arch.lifecycle.h$1.a[arg3.ordinal()]) {
            case 1: 
            case 2: {
                goto label_19;
            }
            case 3: 
            case 4: {
                goto label_17;
            }
            case 5: {
                goto label_15;
            }
            case 6: {
                goto label_13;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected event value ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    label_17:
        return b.d;
    label_19:
        return b.c;
    label_13:
        return b.a;
    label_15:
        return b.e;
    }

    private void b(b arg2) {
        if(this.b == arg2) {
            return;
        }

        this.b = arg2;
        if(!this.e) {
            if(this.d != 0) {
            }
            else {
                this.e = true;
                this.d();
                this.e = false;
                return;
            }
        }

        this.f = true;
    }

    private void b(g arg6) {
        Object v2;
        Iterator v0 = this.a.b();
        do {
        label_2:
            if((v0.hasNext()) && !this.f) {
                Object v1 = v0.next();
                v2 = ((Map$Entry)v1).getValue();
                while(true) {
                label_8:
                    if(((android.arch.lifecycle.h$a)v2).a.compareTo(this.b) <= 0) {
                        goto label_2;
                    }

                    if(this.f) {
                        goto label_2;
                    }

                    if(!this.a.c(((Map$Entry)v1).getKey())) {
                        goto label_2;
                    }

                    goto label_18;
                }
            }

            return;
        }
        while(true);

    label_18:
        a v3 = h.d(((android.arch.lifecycle.h$a)v2).a);
        this.c(h.b(v3));
        ((android.arch.lifecycle.h$a)v2).a(arg6, v3);
        this.c();
        goto label_8;
    }

    private boolean b() {
        boolean v1 = true;
        if(this.a.a() == 0) {
            return 1;
        }

        b v0 = this.a.d().getValue().a;
        b v2 = this.a.e().getValue().a;
        if(v0 != v2 || this.b != v2) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    public void b(f arg2) {
        this.a.b(arg2);
    }

    private void c(b arg2) {
        this.g.add(arg2);
    }

    private void c() {
        this.g.remove(this.g.size() - 1);
    }

    private b c(f arg3) {
        Object v0_1;
        Map$Entry v3 = this.a.d(arg3);
        b v0 = null;
        b v3_1 = v3 != null ? v3.getValue().a : v0;
        if(!this.g.isEmpty()) {
            v0_1 = this.g.get(this.g.size() - 1);
        }

        return h.a(h.a(this.b, v3_1), ((b)v0_1));
    }

    private void d() {
        Object v0 = this.c.get();
        if(v0 == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn\'t try dispatch new events from it.");
            return;
        }

        while(!this.b()) {
            this.f = false;
            if(this.b.compareTo(this.a.d().getValue().a) < 0) {
                this.b(((g)v0));
            }

            Map$Entry v1 = this.a.e();
            if(this.f) {
                continue;
            }

            if(v1 == null) {
                continue;
            }

            if(this.b.compareTo(v1.getValue().a) <= 0) {
                continue;
            }

            this.a(((g)v0));
        }

        this.f = false;
    }

    private static a d(b arg3) {
        switch(android.arch.lifecycle.h$1.b[arg3.ordinal()]) {
            case 1: {
                goto label_22;
            }
            case 2: {
                goto label_20;
            }
            case 3: {
                goto label_18;
            }
            case 4: {
                goto label_16;
            }
            case 5: {
                goto label_13;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected state value ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    label_18:
        return a.ON_STOP;
    label_20:
        return a.ON_DESTROY;
    label_22:
        throw new IllegalArgumentException();
    label_13:
        throw new IllegalArgumentException();
    label_16:
        return a.ON_PAUSE;
    }

    private static a e(b arg3) {
        switch(android.arch.lifecycle.h$1.b[arg3.ordinal()]) {
            case 2: {
                goto label_18;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_13;
            }
            case 1: 
            case 5: {
                goto label_20;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected state value ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    label_18:
        return a.ON_START;
    label_20:
        return a.ON_CREATE;
    label_13:
        throw new IllegalArgumentException();
    label_16:
        return a.ON_RESUME;
    }
}

