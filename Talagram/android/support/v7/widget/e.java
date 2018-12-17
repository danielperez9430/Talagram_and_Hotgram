package android.support.v7.widget;

import java.util.ArrayList;
import java.util.List;

class e implements a {
    interface android.support.v7.widget.e$a {
        w a(int arg1);

        void a(int arg1, int arg2, Object arg3);

        void a(b arg1);

        void a(int arg1, int arg2);

        void b(int arg1, int arg2);

        void b(b arg1);

        void c(int arg1, int arg2);

        void d(int arg1, int arg2);
    }

    class b {
        int a;
        int b;
        Object c;
        int d;

        b(int arg1, int arg2, int arg3, Object arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.d = arg3;
            this.c = arg4;
        }

        String a() {
            int v0 = this.a;
            if(v0 == 4) {
                return "up";
            }

            if(v0 == 8) {
                return "mv";
            }

            switch(v0) {
                case 1: {
                    return "add";
                }
                case 2: {
                    return "rm";
                }
            }

            return "??";
        }

        public boolean equals(Object arg5) {
            if(this == (((b)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else if(this.a != ((b)arg5).a) {
                    return 0;
                }
                else {
                    if(this.a == 8 && Math.abs(this.d - this.b) == 1 && this.d == ((b)arg5).b && this.b == ((b)arg5).d) {
                        return 1;
                    }

                    if(this.d != ((b)arg5).d) {
                        return 0;
                    }

                    if(this.b != ((b)arg5).b) {
                        return 0;
                    }

                    if(this.c != null) {
                        if(!this.c.equals(((b)arg5).c)) {
                            return 0;
                        }
                    }
                    else if(((b)arg5).c != null) {
                        return 0;
                    }

                    return 1;
                }
            }

            return 0;
        }

        public int hashCode() {
            return (this.a * 31 + this.b) * 31 + this.d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + this.a() + ",s:" + this.b + "c:" + this.d + ",p:" + this.c + "]";
        }
    }

    final ArrayList a;
    final ArrayList b;
    final android.support.v7.widget.e$a c;
    Runnable d;
    final boolean e;
    final at f;
    private android.support.v4.f.k$a g;
    private int h;

    e(android.support.v7.widget.e$a arg2) {
        this(arg2, false);
    }

    e(android.support.v7.widget.e$a arg3, boolean arg4) {
        super();
        this.g = new android.support.v4.f.k$b(30);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.h = 0;
        this.c = arg3;
        this.e = arg4;
        this.f = new at(((a)this));
    }

    public b a(int arg2, int arg3, int arg4, Object arg5) {
        b v0_1;
        Object v0 = this.g.a();
        if(v0 == null) {
            v0_1 = new b(arg2, arg3, arg4, arg5);
        }
        else {
            ((b)v0).a = arg2;
            ((b)v0).b = arg3;
            ((b)v0).d = arg4;
            ((b)v0).c = arg5;
        }

        return v0_1;
    }

    public void a(b arg2) {
        if(!this.e) {
            arg2.c = null;
            this.g.a(arg2);
        }
    }

    int a(int arg5, int arg6) {
        int v0 = this.b.size();
        while(arg6 < v0) {
            Object v1 = this.b.get(arg6);
            if(((b)v1).a != 8) {
                if(((b)v1).b > arg5) {
                    goto label_38;
                }

                if(((b)v1).a == 2) {
                    if(arg5 < ((b)v1).b + ((b)v1).d) {
                        return -1;
                    }

                    arg5 -= ((b)v1).d;
                    goto label_38;
                }

                if(((b)v1).a != 1) {
                    goto label_38;
                }

                arg5 += ((b)v1).d;
            }
            else if(((b)v1).b == arg5) {
                arg5 = ((b)v1).d;
            }
            else {
                if(((b)v1).b < arg5) {
                    --arg5;
                }

                if(((b)v1).d > arg5) {
                    goto label_38;
                }

                ++arg5;
            }

        label_38:
            ++arg6;
        }

        return arg5;
    }

    void a(b arg3, int arg4) {
        this.c.a(arg3);
        int v0 = arg3.a;
        if(v0 == 2) {
            this.c.a(arg4, arg3.d);
        }
        else if(v0 == 4) {
            this.c.a(arg4, arg3.d, arg3.c);
        }
        else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    void a() {
        this.a(this.a);
        this.a(this.b);
        this.h = 0;
    }

    void a(List arg4) {
        int v0 = arg4.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.a(arg4.get(v1));
        }

        arg4.clear();
    }

    boolean a(int arg2) {
        boolean v2 = (arg2 & this.h) != 0 ? true : false;
        return v2;
    }

    boolean a(int arg5, int arg6, int arg7) {
        boolean v0 = false;
        if(arg5 == arg6) {
            return 0;
        }

        if(arg7 == 1) {
            this.a.add(this.a(8, arg5, arg6, null));
            this.h |= 8;
            if(this.a.size() == 1) {
                v0 = true;
            }

            return v0;
        }

        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    boolean a(int arg5, int arg6, Object arg7) {
        boolean v0 = false;
        if(arg6 < 1) {
            return 0;
        }

        this.a.add(this.a(4, arg5, arg6, arg7));
        this.h |= 4;
        if(this.a.size() == 1) {
            v0 = true;
        }

        return v0;
    }

    private void b(b arg1) {
        this.g(arg1);
    }

    int b(int arg2) {
        return this.a(arg2, 0);
    }

    void b() {
        this.f.a(this.a);
        int v0 = this.a.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.a.get(v1);
            int v3 = ((b)v2).a;
            if(v3 == 4) {
                this.d(((b)v2));
            }
            else if(v3 != 8) {
                switch(v3) {
                    case 1: {
                        goto label_18;
                    }
                    case 2: {
                        goto label_16;
                    }
                }

                goto label_23;
            label_18:
                this.f(((b)v2));
                goto label_23;
            label_16:
                this.c(((b)v2));
            }
            else {
                this.b(((b)v2));
            }

        label_23:
            if(this.d != null) {
                this.d.run();
            }
        }

        this.a.clear();
    }

    boolean b(int arg5, int arg6) {
        boolean v0 = false;
        if(arg6 < 1) {
            return 0;
        }

        this.a.add(this.a(1, arg5, arg6, null));
        this.h |= 1;
        if(this.a.size() == 1) {
            v0 = true;
        }

        return v0;
    }

    private void c(b arg11) {
        int v6_1;
        int v7;
        Object v6;
        int v0 = arg11.b;
        int v1 = arg11.b + arg11.d;
        int v2 = arg11.b;
        int v4 = 0;
        int v5;
        for(v5 = -1; true; v5 = v6_1) {
            v6 = null;
            v7 = 2;
            if(v2 >= v1) {
                break;
            }

            if(this.c.a(v2) != null || (this.d(v2))) {
                if(v5 == 0) {
                    this.e(this.a(v7, v0, v4, v6));
                    v5 = 1;
                }
                else {
                    v5 = 0;
                }

                v6_1 = 1;
            }
            else {
                if(v5 == 1) {
                    this.g(this.a(v7, v0, v4, v6));
                    v5 = 1;
                }
                else {
                    v5 = 0;
                }

                v6_1 = 0;
            }

            if(v5 != 0) {
                v2 -= v4;
                v1 -= v4;
                v4 = 1;
            }
            else {
                ++v4;
            }

            ++v2;
        }

        if(v4 != arg11.d) {
            this.a(arg11);
            arg11 = this.a(v7, v0, v4, v6);
        }

        if(v5 == 0) {
            this.e(arg11);
        }
        else {
            this.g(arg11);
        }
    }

    public int c(int arg6) {
        int v0 = this.a.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.a.get(v1);
            int v3 = ((b)v2).a;
            if(v3 != 8) {
                switch(v3) {
                    case 1: {
                        goto label_22;
                    }
                    case 2: {
                        goto label_11;
                    }
                }

                goto label_37;
            label_22:
                if(((b)v2).b <= arg6) {
                    arg6 += ((b)v2).d;
                    goto label_37;
                label_11:
                    if(((b)v2).b <= arg6) {
                        if(((b)v2).b + ((b)v2).d > arg6) {
                            return -1;
                        }
                        else {
                            arg6 -= ((b)v2).d;
                        }
                    }
                }
            }
            else if(((b)v2).b == arg6) {
                arg6 = ((b)v2).d;
            }
            else {
                if(((b)v2).b < arg6) {
                    --arg6;
                }

                if(((b)v2).d > arg6) {
                    goto label_37;
                }

                ++arg6;
            }

        label_37:
        }

        return arg6;
    }

    void c() {
        int v0 = this.b.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.c.b(this.b.get(v2));
        }

        this.a(this.b);
        this.h = 0;
    }

    boolean c(int arg6, int arg7) {
        boolean v0 = false;
        if(arg7 < 1) {
            return 0;
        }

        this.a.add(this.a(2, arg6, arg7, null));
        this.h |= 2;
        if(this.a.size() == 1) {
            v0 = true;
        }

        return v0;
    }

    boolean d() {
        boolean v0 = this.a.size() > 0 ? true : false;
        return v0;
    }

    private boolean d(int arg8) {
        int v0 = this.b.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.b.get(v2);
            if(((b)v3).a == 8) {
                if(this.a(((b)v3).d, v2 + 1) == arg8) {
                    return 1;
                }
            }
            else if(((b)v3).a == 1) {
                int v4 = ((b)v3).b + ((b)v3).d;
                int v3_1 = ((b)v3).b;
                while(v3_1 < v4) {
                    if(this.a(v3_1, v2 + 1) == arg8) {
                        return 1;
                    }
                    else {
                        ++v3_1;
                        continue;
                    }

                    break;
                }
            }
        }

        return 0;
    }

    private int d(int arg7, int arg8) {
        int v4;
        int v0;
        for(v0 = this.b.size() - 1; true; --v0) {
            int v2 = 8;
            if(v0 < 0) {
                break;
            }

            Object v3 = this.b.get(v0);
            int v5 = 2;
            if(((b)v3).a == v2) {
                if(((b)v3).b < ((b)v3).d) {
                    v2 = ((b)v3).b;
                    v4 = ((b)v3).d;
                }
                else {
                    v2 = ((b)v3).d;
                    v4 = ((b)v3).b;
                }

                if(arg7 >= v2 && arg7 <= v4) {
                    if(v2 == ((b)v3).b) {
                        if(arg8 == 1) {
                            v2 = ((b)v3).d + 1;
                            goto label_26;
                        }
                        else if(arg8 == v5) {
                            v2 = ((b)v3).d - 1;
                        label_26:
                            ((b)v3).d = v2;
                        }

                        ++arg7;
                    }
                    else {
                        if(arg8 == 1) {
                            v2 = ((b)v3).b + 1;
                            goto label_37;
                        }
                        else if(arg8 == v5) {
                            v2 = ((b)v3).b - 1;
                        label_37:
                            ((b)v3).b = v2;
                        }

                        --arg7;
                    }

                    goto label_83;
                }

                if(arg7 >= ((b)v3).b) {
                    goto label_83;
                }

                if(arg8 == 1) {
                    ++((b)v3).b;
                    v2 = ((b)v3).d + 1;
                }
                else if(arg8 == v5) {
                    --((b)v3).b;
                    v2 = ((b)v3).d - 1;
                }
                else {
                    goto label_83;
                }

                ((b)v3).d = v2;
            }
            else {
                if(((b)v3).b <= arg7) {
                    if(((b)v3).a == 1) {
                        arg7 -= ((b)v3).d;
                        goto label_83;
                    }

                    if(((b)v3).a != v5) {
                        goto label_83;
                    }

                    arg7 += ((b)v3).d;
                    goto label_83;
                }

                if(arg8 == 1) {
                    v2 = ((b)v3).b + 1;
                }
                else if(arg8 == v5) {
                    v2 = ((b)v3).b - 1;
                }
                else {
                    goto label_83;
                }

                ((b)v3).b = v2;
            }

        label_83:
        }

        for(arg8 = this.b.size() - 1; arg8 >= 0; --arg8) {
            Object v0_1 = this.b.get(arg8);
            if(((b)v0_1).a == v2) {
                if(((b)v0_1).d == ((b)v0_1).b) {
                }
                else if(((b)v0_1).d >= 0) {
                    goto label_105;
                }

                goto label_98;
            }
            else if(((b)v0_1).d <= 0) {
            label_98:
                this.b.remove(arg8);
                this.a(((b)v0_1));
            }

        label_105:
        }

        return arg7;
    }

    private void d(b arg10) {
        int v6;
        int v0 = arg10.b;
        int v1 = arg10.b + arg10.d;
        int v2 = arg10.b;
        int v4 = -1;
        int v5 = v0;
        v0 = 0;
        while(true) {
            v6 = 4;
            if(v2 >= v1) {
                break;
            }

            if(this.c.a(v2) != null || (this.d(v2))) {
                if(v4 == 0) {
                    this.e(this.a(v6, v5, v0, arg10.c));
                    v5 = v2;
                    v0 = 0;
                }

                v4 = 1;
            }
            else {
                if(v4 == 1) {
                    this.g(this.a(v6, v5, v0, arg10.c));
                    v5 = v2;
                    v0 = 0;
                }

                v4 = 0;
            }

            ++v0;
            ++v2;
        }

        if(v0 != arg10.d) {
            Object v1_1 = arg10.c;
            this.a(arg10);
            arg10 = this.a(v6, v5, v0, v1_1);
        }

        if(v4 == 0) {
            this.e(arg10);
        }
        else {
            this.g(arg10);
        }
    }

    private void e(b arg13) {
        if(arg13.a != 1 && arg13.a != 8) {
            int v0 = this.d(arg13.b, arg13.a);
            int v2 = arg13.b;
            int v3 = arg13.a;
            int v4 = 2;
            int v5 = 4;
            if(v3 == v4) {
                v3 = 0;
            }
            else if(v3 == v5) {
                v3 = 1;
            }
            else {
                StringBuilder v1 = new StringBuilder();
                v1.append("op should be remove or update.");
                v1.append(arg13);
                throw new IllegalArgumentException(v1.toString());
            }

            int v7 = v0;
            int v8 = v2;
            v0 = 1;
            v2 = 1;
            while(v0 < arg13.d) {
                int v9 = this.d(arg13.b + v3 * v0, arg13.a);
                int v10 = arg13.a;
                if(v10 != v4) {
                    if(v10 == v5) {
                        if(v9 != v7 + 1) {
                            goto label_42;
                        }

                        goto label_46;
                    }
                    else {
                        goto label_42;
                    }
                }
                else if(v9 == v7) {
                label_46:
                    v10 = 1;
                }
                else {
                label_42:
                    v10 = 0;
                }

                if(v10 != 0) {
                    ++v2;
                }
                else {
                    b v7_1 = this.a(arg13.a, v7, v2, arg13.c);
                    this.a(v7_1, v8);
                    this.a(v7_1);
                    if(arg13.a == v5) {
                        v8 += v2;
                    }

                    v7 = v9;
                    v2 = 1;
                }

                ++v0;
            }

            Object v0_1 = arg13.c;
            this.a(arg13);
            if(v2 > 0) {
                arg13 = this.a(arg13.a, v7, v2, v0_1);
                this.a(arg13, v8);
                this.a(arg13);
            }

            return;
        }

        throw new IllegalArgumentException("should not dispatch add or move for pre layout");
    }

    void e() {
        this.c();
        int v0 = this.a.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.a.get(v2);
            int v4 = ((b)v3).a;
            if(v4 == 4) {
                this.c.b(((b)v3));
                this.c.a(((b)v3).b, ((b)v3).d, ((b)v3).c);
            }
            else if(v4 != 8) {
                switch(v4) {
                    case 1: {
                        goto label_22;
                    }
                    case 2: {
                        goto label_15;
                    }
                }

                goto label_43;
            label_22:
                this.c.b(((b)v3));
                this.c.c(((b)v3).b, ((b)v3).d);
                goto label_43;
            label_15:
                this.c.b(((b)v3));
                this.c.a(((b)v3).b, ((b)v3).d);
            }
            else {
                this.c.b(((b)v3));
                this.c.d(((b)v3).b, ((b)v3).d);
            }

        label_43:
            if(this.d != null) {
                this.d.run();
            }
        }

        this.a(this.a);
        this.h = 0;
    }

    private void f(b arg1) {
        this.g(arg1);
    }

    boolean f() {
        boolean v0 = (this.b.isEmpty()) || (this.a.isEmpty()) ? false : true;
        return v0;
    }

    private void g(b arg4) {
        this.b.add(arg4);
        int v0 = arg4.a;
        if(v0 == 4) {
            this.c.a(arg4.b, arg4.d, arg4.c);
        }
        else if(v0 != 8) {
            switch(v0) {
                case 1: {
                    goto label_22;
                }
                case 2: {
                    goto label_17;
                }
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Unknown update op type for ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        label_17:
            this.c.b(arg4.b, arg4.d);
            return;
        label_22:
            this.c.c(arg4.b, arg4.d);
        }
        else {
            this.c.d(arg4.b, arg4.d);
        }
    }
}

