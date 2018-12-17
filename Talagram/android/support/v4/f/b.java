package android.support.v4.f;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class b implements Collection, Set {
    Object[] a;
    int b;
    private static final int[] c;
    private static final Object[] d;
    private static Object[] e;
    private static int f;
    private static Object[] g;
    private static int h;
    private int[] i;
    private h j;

    static {
        b.c = new int[0];
        b.d = new Object[0];
    }

    public b(int arg1) {
        super();
        if(arg1 == 0) {
            this.i = b.c;
            this.a = b.d;
        }
        else {
            this.d(arg1);
        }

        this.b = 0;
    }

    public b() {
        this(0);
    }

    private int a() {
        int v0 = this.b;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = c.a(this.i, v0, 0);
        if(v2 < 0) {
            return v2;
        }

        if(this.a[v2] == null) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.i[v3] != 0) {
                break;
            }

            if(this.a[v3] == null) {
                return v3;
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        --v2;
        while(v2 >= 0) {
            if(this.i[v2] != 0) {
                break;
            }

            if(this.a[v2] == null) {
                return v2;
            }
            else {
                --v2;
                continue;
            }

            break;
        }

        return v3 ^ -1;
    }

    private int a(Object arg6, int arg7) {
        int v0 = this.b;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = c.a(this.i, v0, arg7);
        if(v2 < 0) {
            return v2;
        }

        if(arg6.equals(this.a[v2])) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.i[v3] != arg7) {
                break;
            }

            if(arg6.equals(this.a[v3])) {
                return v3;
            }
            else {
                ++v3;
                continue;
            }

            break;
        }

        --v2;
        while(v2 >= 0) {
            if(this.i[v2] != arg7) {
                break;
            }

            if(arg6.equals(this.a[v2])) {
                return v2;
            }
            else {
                --v2;
                continue;
            }

            break;
        }

        return v3 ^ -1;
    }

    private static void a(int[] arg7, Object[] arg8, int arg9) {
        Class v0;
        Object v1 = null;
        int v2 = 2;
        int v4 = 10;
        if(arg7.length == 8) {
            v0 = b.class;
            __monitor_enter(v0);
            try {
                if(b.h < v4) {
                    arg8[0] = b.g;
                    arg8[1] = arg7;
                    --arg9;
                    while(arg9 >= v2) {
                        arg8[arg9] = v1;
                        --arg9;
                    }

                    b.g = arg8;
                    ++b.h;
                }

                __monitor_exit(v0);
                return;
            label_27:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_27;
            }

            throw v7;
        }
        else {
            if(arg7.length != 4) {
                return;
            }

            v0 = b.class;
            __monitor_enter(v0);
            try {
                if(b.f < v4) {
                    arg8[0] = b.e;
                    arg8[1] = arg7;
                    --arg9;
                    while(arg9 >= v2) {
                        arg8[arg9] = v1;
                        --arg9;
                    }

                    b.e = arg8;
                    ++b.f;
                }

                __monitor_exit(v0);
                return;
            label_51:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_51;
            }

            throw v7;
        }
    }

    public int a(Object arg2) {
        int v2 = arg2 == null ? this.a() : this.a(arg2, arg2.hashCode());
        return v2;
    }

    public void a(int arg5) {
        if(this.i.length < arg5) {
            int[] v0 = this.i;
            Object[] v1 = this.a;
            this.d(arg5);
            if(this.b > 0) {
                System.arraycopy(v0, 0, this.i, 0, this.b);
                System.arraycopy(v1, 0, this.a, 0, this.b);
            }

            b.a(v0, v1, this.b);
        }
    }

    public boolean add(Object arg10) {
        int v4;
        int v2;
        int v1;
        if(arg10 == null) {
            v1 = this.a();
            v2 = 0;
        }
        else {
            v1 = arg10.hashCode();
            v2 = v1;
            v1 = this.a(arg10, v1);
        }

        if(v1 >= 0) {
            return 0;
        }

        v1 ^= -1;
        if(this.b >= this.i.length) {
            v4 = 4;
            if(this.b >= 8) {
                v4 = (this.b >> 1) + this.b;
            }
            else if(this.b >= v4) {
                v4 = 8;
            }

            int[] v3 = this.i;
            Object[] v6 = this.a;
            this.d(v4);
            if(this.i.length > 0) {
                System.arraycopy(v3, 0, this.i, 0, v3.length);
                System.arraycopy(v6, 0, this.a, 0, v6.length);
            }

            b.a(v3, v6, this.b);
        }

        if(v1 < this.b) {
            v4 = v1 + 1;
            System.arraycopy(this.i, v1, this.i, v4, this.b - v1);
            System.arraycopy(this.a, v1, this.a, v4, this.b - v1);
        }

        this.i[v1] = v2;
        this.a[v1] = arg10;
        ++this.b;
        return 1;
    }

    public boolean addAll(Collection arg3) {
        this.a(this.b + arg3.size());
        Iterator v3 = arg3.iterator();
        int v0;
        for(v0 = 0; v3.hasNext(); v0 |= this.add(v3.next())) {
        }

        return ((boolean)v0);
    }

    private h b() {
        if(this.j == null) {
            this.j = new h() {
                protected int a() {
                    return this.a.b;
                }

                protected int a(Object arg2) {
                    return this.a.a(arg2);
                }

                protected Object a(int arg1, int arg2) {
                    return this.a.a[arg1];
                }

                protected Object a(int arg1, Object arg2) {
                    throw new UnsupportedOperationException("not a map");
                }

                protected void a(int arg2) {
                    this.a.c(arg2);
                }

                protected void a(Object arg1, Object arg2) {
                    this.a.add(arg1);
                }

                protected int b(Object arg2) {
                    return this.a.a(arg2);
                }

                protected Map b() {
                    throw new UnsupportedOperationException("not a map");
                }

                protected void c() {
                    this.a.clear();
                }
            };
        }

        return this.j;
    }

    public Object b(int arg2) {
        return this.a[arg2];
    }

    public Object c(int arg7) {
        int v2;
        Object v0 = this.a[arg7];
        if(this.b <= 1) {
            b.a(this.i, this.a, this.b);
            this.i = b.c;
            this.a = b.d;
            this.b = 0;
        }
        else {
            int v4 = 8;
            if(this.i.length > v4 && this.b < this.i.length / 3) {
                if(this.b > v4) {
                    v4 = (this.b >> 1) + this.b;
                }

                int[] v1 = this.i;
                Object[] v5 = this.a;
                this.d(v4);
                --this.b;
                if(arg7 > 0) {
                    System.arraycopy(v1, 0, this.i, 0, arg7);
                    System.arraycopy(v5, 0, this.a, 0, arg7);
                }

                if(arg7 >= this.b) {
                    return v0;
                }

                v2 = arg7 + 1;
                System.arraycopy(v1, v2, this.i, arg7, this.b - arg7);
                System.arraycopy(v5, v2, this.a, arg7, this.b - arg7);
                return v0;
            }

            --this.b;
            if(arg7 < this.b) {
                v2 = arg7 + 1;
                System.arraycopy(this.i, v2, this.i, arg7, this.b - arg7);
                System.arraycopy(this.a, v2, this.a, arg7, this.b - arg7);
            }

            this.a[this.b] = null;
        }

        return v0;
    }

    public void clear() {
        if(this.b != 0) {
            b.a(this.i, this.a, this.b);
            this.i = b.c;
            this.a = b.d;
            this.b = 0;
        }
    }

    public boolean contains(Object arg1) {
        boolean v1 = this.a(arg1) >= 0 ? true : false;
        return v1;
    }

    public boolean containsAll(Collection arg2) {
        Iterator v2 = arg2.iterator();
        do {
            if(!v2.hasNext()) {
                return 1;
            }
        }
        while(this.contains(v2.next()));

        return 0;
    }

    private void d(int arg6) {
        Object[] v6_1;
        Class v3;
        Object v0 = null;
        if(arg6 == 8) {
            v3 = b.class;
            __monitor_enter(v3);
            try {
                if(b.g != null) {
                    v6_1 = b.g;
                    this.a = v6_1;
                    b.g = v6_1[0];
                    this.i = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --b.h;
                    __monitor_exit(v3);
                    return;
                }
                else {
                    __monitor_exit(v3);
                    goto label_51;
                label_25:
                    __monitor_exit(v3);
                    goto label_26;
                }

                goto label_51;
            }
            catch(Throwable v6) {
                goto label_25;
            }

        label_26:
            throw v6;
        }
        else if(arg6 == 4) {
            v3 = b.class;
            __monitor_enter(v3);
            try {
                if(b.e != null) {
                    v6_1 = b.e;
                    this.a = v6_1;
                    b.e = v6_1[0];
                    this.i = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --b.f;
                    __monitor_exit(v3);
                    return;
                }
                else {
                    __monitor_exit(v3);
                    goto label_51;
                label_49:
                    __monitor_exit(v3);
                    goto label_50;
                }

                goto label_51;
            }
            catch(Throwable v6) {
                goto label_49;
            }

        label_50:
            throw v6;
        }

    label_51:
        this.i = new int[arg6];
        this.a = new Object[arg6];
    }

    public boolean equals(Object arg5) {
        if(this == (((b)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Set)) {
            return 0;
        }

        if(this.size() != ((Set)arg5).size()) {
            return 0;
        }

        int v1 = 0;
        try {
            while(true) {
            label_11:
                if(v1 >= this.b) {
                    return 1;
                }

                if(((Set)arg5).contains(this.b(v1))) {
                    goto label_17;
                }

                return 0;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }

        return 0;
    label_17:
        ++v1;
        goto label_11;
        return 1;
    }

    public int hashCode() {
        int[] v0 = this.i;
        int v1 = this.b;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v1) {
            v3 += v0[v2];
            ++v2;
        }

        return v3;
    }

    public boolean isEmpty() {
        boolean v0 = this.b <= 0 ? true : false;
        return v0;
    }

    public Iterator iterator() {
        return this.b().e().iterator();
    }

    public boolean remove(Object arg1) {
        int v1 = this.a(arg1);
        if(v1 >= 0) {
            this.c(v1);
            return 1;
        }

        return 0;
    }

    public boolean removeAll(Collection arg3) {
        Iterator v3 = arg3.iterator();
        int v0;
        for(v0 = 0; v3.hasNext(); v0 |= this.remove(v3.next())) {
        }

        return ((boolean)v0);
    }

    public boolean retainAll(Collection arg5) {
        int v0 = this.b - 1;
        boolean v2 = false;
        while(v0 >= 0) {
            if(!arg5.contains(this.a[v0])) {
                this.c(v0);
                v2 = true;
            }

            --v0;
        }

        return v2;
    }

    public int size() {
        return this.b;
    }

    public Object[] toArray() {
        Object[] v0 = new Object[this.b];
        System.arraycopy(this.a, 0, v0, 0, this.b);
        return v0;
    }

    public Object[] toArray(Object[] arg4) {
        Object v4;
        if(arg4.length < this.b) {
            v4 = Array.newInstance(arg4.getClass().getComponentType(), this.b);
        }

        System.arraycopy(this.a, 0, v4, 0, this.b);
        if(((Object[])v4).length > this.b) {
            ((Object[])v4)[this.b] = null;
        }

        return ((Object[])v4);
    }

    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.b * 14);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.b; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            Object v2 = this.b(v1);
            if((((b)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Set)");
            }
        }

        v0.append('}');
        return v0.toString();
    }
}

