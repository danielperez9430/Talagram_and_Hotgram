package c.b.c;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class a extends RuntimeException {
    final class c.b.c.a$a extends RuntimeException {
        c.b.c.a$a() {
            super();
        }

        public String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }

    abstract class b {
        b() {
            super();
        }

        abstract void a(Object arg1);
    }

    final class c extends b {
        private final PrintStream a;

        c(PrintStream arg1) {
            super();
            this.a = arg1;
        }

        void a(Object arg2) {
            this.a.println(arg2);
        }
    }

    final class d extends b {
        private final PrintWriter a;

        d(PrintWriter arg1) {
            super();
            this.a = arg1;
        }

        void a(Object arg2) {
            this.a.println(arg2);
        }
    }

    private final List a;
    private final String b;
    private Throwable c;

    public a(Iterable arg5) {
        super();
        LinkedHashSet v0 = new LinkedHashSet();
        ArrayList v1 = new ArrayList();
        if(arg5 != null) {
            Iterator v5 = arg5.iterator();
            while(v5.hasNext()) {
                Object v2 = v5.next();
                if((v2 instanceof a)) {
                    ((Set)v0).addAll(((a)v2).a());
                }
                else {
                    if(v2 == null) {
                        NullPointerException v2_1 = new NullPointerException("Throwable was null!");
                    }

                    ((Set)v0).add(v2);
                }
            }
        }
        else {
            ((Set)v0).add(new NullPointerException("errors was null"));
        }

        if(!((Set)v0).isEmpty()) {
            ((List)v1).addAll(((Collection)v0));
            this.a = Collections.unmodifiableList(((List)v1));
            this.b = this.a.size() + " exceptions occurred. ";
            return;
        }

        throw new IllegalArgumentException("errors is empty");
    }

    public a(Throwable[] arg2) {
        List v2 = arg2 == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(((Object[])arg2));
        this(((Iterable)v2));
    }

    public List a() {
        return this.a;
    }

    private void a(b arg8) {
        StringBuilder v0 = new StringBuilder(128);
        v0.append(this);
        char v1 = '\n';
        v0.append(v1);
        StackTraceElement[] v2 = this.getStackTrace();
        int v3 = v2.length;
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            StackTraceElement v5 = v2[v4];
            v0.append("\tat ");
            v0.append(v5);
            v0.append(v1);
        }

        Iterator v1_1 = this.a.iterator();
        for(v3 = 1; v1_1.hasNext(); ++v3) {
            Object v4_1 = v1_1.next();
            v0.append("  ComposedException ");
            v0.append(v3);
            v0.append(" :\n");
            this.a(v0, ((Throwable)v4_1), "\t");
        }

        arg8.a(v0.toString());
    }

    private void a(StringBuilder arg6, Throwable arg7, String arg8) {
        arg6.append(arg8);
        arg6.append(arg7);
        char v8 = '\n';
        arg6.append(v8);
        StackTraceElement[] v0 = arg7.getStackTrace();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            StackTraceElement v3 = v0[v2];
            arg6.append("\t\tat ");
            arg6.append(v3);
            arg6.append(v8);
        }

        if(arg7.getCause() != null) {
            arg6.append("\tCaused by: ");
            this.a(arg6, arg7.getCause(), "");
        }
    }

    Throwable a(Throwable arg3) {
        Throwable v0 = arg3.getCause();
        if(v0 != null && this.c != v0) {
            while(true) {
                arg3 = v0.getCause();
                if(arg3 != null) {
                    if(arg3 == v0) {
                    }
                    else {
                        v0 = arg3;
                        continue;
                    }
                }

                return v0;
            }

            return v0;
        }

        return arg3;
    }

    private List b(Throwable arg3) {
        ArrayList v0 = new ArrayList();
        Throwable v1 = arg3.getCause();
        if(v1 != null && v1 != arg3) {
            while(true) {
                ((List)v0).add(v1);
                arg3 = v1.getCause();
                if(arg3 == null) {
                    break;
                }

                if(arg3 == v1) {
                    break;
                }

                v1 = arg3;
            }
        }

        return ((List)v0);
    }

    public Throwable getCause() {
        RuntimeException v4_1;
        Object v4;
        __monitor_enter(this);
        try {
            if(this.c == null) {
                c.b.c.a$a v0_1 = new c.b.c.a$a();
                HashSet v1 = new HashSet();
                Iterator v2 = this.a.iterator();
                c.b.c.a$a v3 = v0_1;
                while(true) {
                label_10:
                    if(v2.hasNext()) {
                        v4 = v2.next();
                        if(((Set)v1).contains(v4)) {
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        goto label_33;
                    }

                    goto label_34;
                }

                ((Set)v1).add(v4);
                Iterator v5 = this.b(((Throwable)v4)).iterator();
                goto label_19;
            label_33:
                this.c = ((Throwable)v0_1);
                goto label_34;
            label_19:
                while(v5.hasNext()) {
                    Object v6 = v5.next();
                    if(((Set)v1).contains(v6)) {
                        v4_1 = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                    }
                    else {
                        ((Set)v1).add(v6);
                    }
                }

                try {
                    ((Throwable)v3).initCause(((Throwable)v4_1));
                    goto label_31;
                }
                catch(Throwable ) {
                label_31:
                    Throwable v3_1 = this.a(((Throwable)v3));
                    goto label_10;
                }
            }

        label_34:
            v0 = this.c;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0;
    }

    public String getMessage() {
        return this.b;
    }

    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream arg2) {
        this.a(new c(arg2));
    }

    public void printStackTrace(PrintWriter arg2) {
        this.a(new d(arg2));
    }
}

