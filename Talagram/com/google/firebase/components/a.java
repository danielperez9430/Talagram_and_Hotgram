package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@KeepForSdk public final class a {
    @KeepForSdk public class com.google.firebase.components.a$a {
        private final Set a;
        private final Set b;
        private int c;
        private d d;
        private Set e;

        com.google.firebase.components.a$a(Class arg1, Class[] arg2, byte arg3) {
            this(arg1, arg2);
        }

        private com.google.firebase.components.a$a(Class arg4, Class[] arg5) {
            super();
            this.a = new HashSet();
            this.b = new HashSet();
            int v0 = 0;
            this.c = 0;
            this.e = new HashSet();
            Preconditions.checkNotNull(arg4, "Null interface");
            this.a.add(arg4);
            int v4 = arg5.length;
            while(v0 < v4) {
                Preconditions.checkNotNull(arg5[v0], "Null interface");
                ++v0;
            }

            Collections.addAll(this.a, ((Object[])arg5));
        }

        @KeepForSdk public com.google.firebase.components.a$a a(d arg2) {
            this.d = Preconditions.checkNotNull(arg2, "Null factory");
            return this;
        }

        private com.google.firebase.components.a$a a(int arg3) {
            boolean v0 = this.c == 0 ? true : false;
            Preconditions.checkState(v0, "Instantiation type has already been set.");
            this.c = arg3;
            return this;
        }

        @KeepForSdk public com.google.firebase.components.a$a a() {
            return this.a(1);
        }

        @KeepForSdk public com.google.firebase.components.a$a a(f arg3) {
            Preconditions.checkNotNull(arg3, "Null dependency");
            Preconditions.checkArgument(this.a.contains(arg3.a()) ^ 1, "Components are not allowed to depend on interfaces they themselves provide.");
            this.b.add(arg3);
            return this;
        }

        @KeepForSdk public com.google.firebase.components.a$a b() {
            return this.a(2);
        }

        @KeepForSdk public a c() {
            boolean v0 = this.d != null ? true : false;
            Preconditions.checkState(v0, "Missing required property: factory.");
            return new a(new HashSet(this.a), new HashSet(this.b), this.c, this.d, this.e, 0);
        }
    }

    private final Set a;
    private final Set b;
    private final int c;
    private final d d;
    private final Set e;

    private a(Set arg1, Set arg2, int arg3, d arg4, Set arg5) {
        super();
        this.a = Collections.unmodifiableSet(arg1);
        this.b = Collections.unmodifiableSet(arg2);
        this.c = arg3;
        this.d = arg4;
        this.e = Collections.unmodifiableSet(arg5);
    }

    a(Set arg1, Set arg2, int arg3, d arg4, Set arg5, byte arg6) {
        this(arg1, arg2, arg3, arg4, arg5);
    }

    @KeepForSdk @SafeVarargs public static a a(Object arg0, Class arg1, Class[] arg2) {
        return a.a(arg1, arg2).a(new j(arg0)).c();
    }

    @KeepForSdk public static com.google.firebase.components.a$a a(Class arg3) {
        return new com.google.firebase.components.a$a(arg3, new Class[0], 0);
    }

    @KeepForSdk public static com.google.firebase.components.a$a a(Class arg2, Class[] arg3) {
        return new com.google.firebase.components.a$a(arg2, arg3, 0);
    }

    static final Object a(Object arg0) {
        return arg0;
    }

    public final Set a() {
        return this.a;
    }

    public final Set b() {
        return this.b;
    }

    public final d c() {
        return this.d;
    }

    public final Set d() {
        return this.e;
    }

    public final boolean e() {
        if(this.c == 1) {
            return 1;
        }

        return 0;
    }

    public final boolean f() {
        if(this.c == 2) {
            return 1;
        }

        return 0;
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder("Component<");
        v0.append(Arrays.toString(this.a.toArray()));
        v0.append(">{");
        v0.append(this.c);
        v0.append(", deps=");
        v0.append(Arrays.toString(this.b.toArray()));
        v0.append("}");
        return v0.toString();
    }
}

