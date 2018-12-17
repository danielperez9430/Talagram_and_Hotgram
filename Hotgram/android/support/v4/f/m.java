package android.support.v4.f;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class m {
    static Object[] b;
    static int c;
    static Object[] d;
    static int e;
    int[] f;
    Object[] g;
    int h;

    public m() {
        super();
        this.f = c.a;
        this.g = c.c;
        this.h = 0;
    }

    public m(int arg1) {
        super();
        if(arg1 == 0) {
            this.f = c.a;
            this.g = c.c;
        }
        else {
            this.e(arg1);
        }

        this.h = 0;
    }

    public m(m arg1) {
        this();
        if(arg1 != null) {
            this.a(arg1);
        }
    }

    public void a(m arg5) {
        int v0 = arg5.h;
        this.a(this.h + v0);
        int v2 = 0;
        if(this.h != 0) {
            while(v2 < v0) {
                this.put(arg5.b(v2), arg5.c(v2));
                ++v2;
            }
        }
        else if(v0 > 0) {
            System.arraycopy(arg5.f, 0, this.f, 0, v0);
            System.arraycopy(arg5.g, 0, this.g, 0, v0 << 1);
            this.h = v0;
        }
    }

    private static int a(int[] arg0, int arg1, int arg2) {
        try {
            return c.a(arg0, arg1, arg2);
        }
        catch(ArrayIndexOutOfBoundsException ) {
            throw new ConcurrentModificationException();
        }
    }

    private static void a(int[] arg7, Object[] arg8, int arg9) {
        Class v0;
        Object v1 = null;
        int v2 = 2;
        int v4 = 10;
        if(arg7.length == 8) {
            v0 = a.class;
            __monitor_enter(v0);
            try {
                if(m.e < v4) {
                    arg8[0] = m.d;
                    arg8[1] = arg7;
                    int v7_1;
                    for(v7_1 = (arg9 << 1) - 1; v7_1 >= v2; --v7_1) {
                        arg8[v7_1] = v1;
                    }

                    m.d = arg8;
                    ++m.e;
                }

                __monitor_exit(v0);
                return;
            label_28:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_28;
            }

            throw v7;
        }
        else {
            if(arg7.length != 4) {
                return;
            }

            v0 = a.class;
            __monitor_enter(v0);
            try {
                if(m.c < v4) {
                    arg8[0] = m.b;
                    arg8[1] = arg7;
                    for(v7_1 = (arg9 << 1) - 1; v7_1 >= v2; --v7_1) {
                        arg8[v7_1] = v1;
                    }

                    m.b = arg8;
                    ++m.c;
                }

                __monitor_exit(v0);
                return;
            label_53:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_53;
            }

            throw v7;
        }
    }

    int a() {
        int v0 = this.h;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = m.a(this.f, v0, 0);
        if(v2 < 0) {
            return v2;
        }

        if(this.g[v2 << 1] == null) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.f[v3] != 0) {
                break;
            }

            if(this.g[v3 << 1] == null) {
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
            if(this.f[v2] != 0) {
                break;
            }

            if(this.g[v2 << 1] == null) {
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

    public int a(Object arg2) {
        int v2 = arg2 == null ? this.a() : this.a(arg2, arg2.hashCode());
        return v2;
    }

    int a(Object arg7, int arg8) {
        int v0 = this.h;
        int v1 = -1;
        if(v0 == 0) {
            return v1;
        }

        int v2 = m.a(this.f, v0, arg8);
        if(v2 < 0) {
            return v2;
        }

        if(arg7.equals(this.g[v2 << 1])) {
            return v2;
        }

        int v3 = v2 + 1;
        while(v3 < v0) {
            if(this.f[v3] != arg8) {
                break;
            }

            if(arg7.equals(this.g[v3 << 1])) {
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
            if(this.f[v2] != arg8) {
                break;
            }

            if(arg7.equals(this.g[v2 << 1])) {
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

    public Object a(int arg3, Object arg4) {
        arg3 = (arg3 << 1) + 1;
        Object v0 = this.g[arg3];
        this.g[arg3] = arg4;
        return v0;
    }

    public void a(int arg6) {
        int v0 = this.h;
        if(this.f.length < arg6) {
            int[] v1 = this.f;
            Object[] v2 = this.g;
            this.e(arg6);
            if(this.h > 0) {
                System.arraycopy(v1, 0, this.f, 0, v0);
                System.arraycopy(v2, 0, this.g, 0, v0 << 1);
            }

            m.a(v1, v2, v0);
        }

        if(this.h == v0) {
            return;
        }

        throw new ConcurrentModificationException();
    }

    public Object b(int arg2) {
        return this.g[arg2 << 1];
    }

    int b(Object arg6) {
        int v0 = this.h * 2;
        Object[] v1 = this.g;
        if(arg6 == null) {
            int v6 = 1;
            while(v6 < v0) {
                if(v1[v6] == null) {
                    return v6 >> 1;
                }
                else {
                    v6 += 2;
                    continue;
                }

                return -1;
            }
        }
        else {
            int v3 = 1;
            while(v3 < v0) {
                if(arg6.equals(v1[v3])) {
                    return v3 >> 1;
                }
                else {
                    v3 += 2;
                    continue;
                }

                return -1;
            }
        }

        return -1;
    }

    public Object c(int arg2) {
        return this.g[(arg2 << 1) + 1];
    }

    public void clear() {
        if(this.h > 0) {
            int[] v0 = this.f;
            Object[] v1 = this.g;
            int v2 = this.h;
            this.f = c.a;
            this.g = c.c;
            this.h = 0;
            m.a(v0, v1, v2);
        }

        if(this.h <= 0) {
            return;
        }

        throw new ConcurrentModificationException();
    }

    public boolean containsKey(Object arg1) {
        boolean v1 = this.a(arg1) >= 0 ? true : false;
        return v1;
    }

    public boolean containsValue(Object arg1) {
        boolean v1 = this.b(arg1) >= 0 ? true : false;
        return v1;
    }

    public Object d(int arg11) {
        int v1 = arg11 << 1;
        Object v0 = this.g[v1 + 1];
        int v2 = this.h;
        int v3 = 0;
        if(v2 <= 1) {
            m.a(this.f, this.g, v2);
            this.f = c.a;
            this.g = c.c;
        }
        else {
            int v5 = v2 - 1;
            int v7 = 8;
            if(this.f.length <= v7 || this.h >= this.f.length / 3) {
                if(arg11 < v5) {
                    int v6_1 = arg11 + 1;
                    int v8_1 = v5 - arg11;
                    System.arraycopy(this.f, v6_1, this.f, arg11, v8_1);
                    System.arraycopy(this.g, v6_1 << 1, this.g, v1, v8_1 << 1);
                }

                v1 = v5 << 1;
                this.g[v1] = null;
                this.g[v1 + 1] = null;
            }
            else {
                if(v2 > v7) {
                    v7 = v2 + (v2 >> 1);
                }

                int[] v6 = this.f;
                Object[] v8 = this.g;
                this.e(v7);
                if(v2 == this.h) {
                    if(arg11 > 0) {
                        System.arraycopy(v6, 0, this.f, 0, arg11);
                        System.arraycopy(v8, 0, this.g, 0, v1);
                    }

                    if(arg11 >= v5) {
                        goto label_70;
                    }

                    v3 = arg11 + 1;
                    int v9 = v5 - arg11;
                    System.arraycopy(v6, v3, this.f, arg11, v9);
                    System.arraycopy(v8, v3 << 1, this.g, v1, v9 << 1);
                    goto label_70;
                }

                throw new ConcurrentModificationException();
            }

        label_70:
            v3 = v5;
        }

        if(v2 == this.h) {
            this.h = v3;
            return v0;
        }

        throw new ConcurrentModificationException();
    }

    private void e(int arg6) {
        Object[] v6_1;
        Class v3;
        Object v0 = null;
        if(arg6 == 8) {
            v3 = a.class;
            __monitor_enter(v3);
            try {
                if(m.d != null) {
                    v6_1 = m.d;
                    this.g = v6_1;
                    m.d = v6_1[0];
                    this.f = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --m.e;
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
            v3 = a.class;
            __monitor_enter(v3);
            try {
                if(m.b != null) {
                    v6_1 = m.b;
                    this.g = v6_1;
                    m.b = v6_1[0];
                    this.f = v6_1[1];
                    v6_1[1] = v0;
                    v6_1[0] = v0;
                    --m.c;
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
        this.f = new int[arg6];
        this.g = new Object[arg6 << 1];
    }

    public boolean equals(Object arg7) {
        Object v5;
        Object v4;
        Object v3;
        if(this == (((m)arg7))) {
            return 1;
        }

        if(!(arg7 instanceof m)) {
            goto label_28;
        }

        if(this.size() != ((m)arg7).size()) {
            return 0;
        }

        int v1 = 0;
        try {
            while(true) {
            label_11:
                if(v1 >= this.h) {
                    return 1;
                }

                v3 = this.b(v1);
                v4 = this.c(v1);
                v5 = ((m)arg7).get(v3);
                if(v4 == null) {
                    if(v5 == null && (((m)arg7).containsKey(v3))) {
                        goto label_24;
                    }

                    return 0;
                }
                else {
                    if(v4.equals(v5)) {
                        goto label_24;
                    }

                    return 0;
                }

                goto label_24;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }

        return 0;
    label_24:
        ++v1;
        goto label_11;
        return 1;
    label_28:
        if(!(arg7 instanceof Map)) {
            return 0;
        }

        if(this.size() != ((Map)arg7).size()) {
            return 0;
        }

        v1 = 0;
        try {
            while(true) {
            label_35:
                if(v1 >= this.h) {
                    return 1;
                }

                v3 = this.b(v1);
                v4 = this.c(v1);
                v5 = ((Map)arg7).get(v3);
                if(v4 == null) {
                    if(v5 == null && (((Map)arg7).containsKey(v3))) {
                        goto label_48;
                    }

                    return 0;
                }
                else {
                    if(v4.equals(v5)) {
                        goto label_48;
                    }

                    return 0;
                }

                goto label_48;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }

        return 0;
    label_48:
        ++v1;
        goto label_35;
        return 1;
    }

    public Object get(Object arg2) {
        int v2 = this.a(arg2);
        return v2 >= 0 ? this.g[(v2 << 1) + 1] : null;
    }

    public int hashCode() {
        int[] v0 = this.f;
        Object[] v1 = this.g;
        int v2 = this.h;
        int v3 = 0;
        int v5 = 1;
        int v6 = 0;
        while(v3 < v2) {
            Object v7 = v1[v5];
            int v8 = v0[v3];
            int v7_1 = v7 == null ? 0 : v7.hashCode();
            v6 += v7_1 ^ v8;
            ++v3;
            v5 += 2;
        }

        return v6;
    }

    public boolean isEmpty() {
        boolean v0 = this.h <= 0 ? true : false;
        return v0;
    }

    public Object put(Object arg10, Object arg11) {
        int v3;
        int v2;
        int v0 = this.h;
        if(arg10 == null) {
            v2 = this.a();
            v3 = 0;
        }
        else {
            v2 = arg10.hashCode();
            v3 = v2;
            v2 = this.a(arg10, v2);
        }

        if(v2 >= 0) {
            int v10 = (v2 << 1) + 1;
            Object v0_1 = this.g[v10];
            this.g[v10] = arg11;
            return v0_1;
        }

        v2 ^= -1;
        if(v0 >= this.f.length) {
            int v4 = 4;
            if(v0 >= 8) {
                v4 = (v0 >> 1) + v0;
            }
            else if(v0 >= v4) {
                v4 = 8;
            }

            int[] v5 = this.f;
            Object[] v6 = this.g;
            this.e(v4);
            if(v0 == this.h) {
                if(this.f.length > 0) {
                    System.arraycopy(v5, 0, this.f, 0, v5.length);
                    System.arraycopy(v6, 0, this.g, 0, v6.length);
                }

                m.a(v5, v6, v0);
                goto label_50;
            }

            throw new ConcurrentModificationException();
        }

    label_50:
        if(v2 < v0) {
            int v5_1 = v2 + 1;
            System.arraycopy(this.f, v2, this.f, v5_1, v0 - v2);
            System.arraycopy(this.g, v2 << 1, this.g, v5_1 << 1, this.h - v2 << 1);
        }

        if(v0 == this.h && v2 < this.f.length) {
            this.f[v2] = v3;
            int v1 = v2 << 1;
            this.g[v1] = arg10;
            this.g[v1 + 1] = arg11;
            ++this.h;
            return null;
        }

        throw new ConcurrentModificationException();
    }

    public Object remove(Object arg1) {
        int v1 = this.a(arg1);
        if(v1 >= 0) {
            return this.d(v1);
        }

        return null;
    }

    public int size() {
        return this.h;
    }

    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }

        StringBuilder v0 = new StringBuilder(this.h * 28);
        v0.append('{');
        int v1;
        for(v1 = 0; v1 < this.h; ++v1) {
            if(v1 > 0) {
                v0.append(", ");
            }

            Object v2 = this.b(v1);
            if((((m)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Map)");
            }

            v0.append('=');
            v2 = this.c(v1);
            if((((m)v2)) != this) {
                v0.append(v2);
            }
            else {
                v0.append("(this Map)");
            }
        }

        v0.append('}');
        return v0.toString();
    }
}

