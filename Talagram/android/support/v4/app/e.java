package android.support.v4.app;

import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class e extends o implements h {
    final class a {
        int a;
        Fragment b;
        int c;
        int d;
        int e;
        int f;

        a() {
            super();
        }

        a(int arg1, Fragment arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    final m a;
    ArrayList b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    boolean i;
    boolean j;
    String k;
    boolean l;
    int m;
    int n;
    CharSequence o;
    int p;
    CharSequence q;
    ArrayList r;
    ArrayList s;
    boolean t;
    ArrayList u;

    public e(m arg2) {
        super();
        this.b = new ArrayList();
        this.j = true;
        this.m = -1;
        this.t = false;
        this.a = arg2;
    }

    void a(a arg2) {
        this.b.add(arg2);
        arg2.c = this.c;
        arg2.d = this.d;
        arg2.e = this.e;
        arg2.f = this.f;
    }

    void a(int arg7) {
        if(!this.i) {
            return;
        }

        if(m.a) {
            Log.v("FragmentManager", "Bump nesting in " + this + " by " + arg7);
        }

        int v0 = this.b.size();
        int v1_1;
        for(v1_1 = 0; v1_1 < v0; ++v1_1) {
            Object v2 = this.b.get(v1_1);
            if(((a)v2).b != null) {
                ((a)v2).b.mBackStackNesting += arg7;
                if(m.a) {
                    Log.v("FragmentManager", "Bump nesting of " + ((a)v2).b + " to " + ((a)v2).b.mBackStackNesting);
                }
            }
        }
    }

    private void a(int arg4, Fragment arg5, String arg6, int arg7) {
        StringBuilder v7;
        Class v0 = arg5.getClass();
        int v1 = v0.getModifiers();
        if(!v0.isAnonymousClass() && (Modifier.isPublic(v1)) && (!v0.isMemberClass() || (Modifier.isStatic(v1)))) {
            arg5.mFragmentManager = this.a;
            if(arg6 != null) {
                if(arg5.mTag != null) {
                    if(arg6.equals(arg5.mTag)) {
                    }
                    else {
                        v7 = new StringBuilder();
                        v7.append("Can\'t change tag of fragment ");
                        v7.append(arg5);
                        v7.append(": was ");
                        v7.append(arg5.mTag);
                        v7.append(" now ");
                        v7.append(arg6);
                        throw new IllegalStateException(v7.toString());
                    }
                }

                arg5.mTag = arg6;
            }

            if(arg4 != 0) {
                if(arg4 != -1) {
                    if(arg5.mFragmentId != 0) {
                        if(arg5.mFragmentId == arg4) {
                        }
                        else {
                            v7 = new StringBuilder();
                            v7.append("Can\'t change container ID of fragment ");
                            v7.append(arg5);
                            v7.append(": was ");
                            v7.append(arg5.mFragmentId);
                            v7.append(" now ");
                            v7.append(arg4);
                            throw new IllegalStateException(v7.toString());
                        }
                    }

                    arg5.mFragmentId = arg4;
                    arg5.mContainerId = arg4;
                }
                else {
                    v7 = new StringBuilder();
                    v7.append("Can\'t add fragment ");
                    v7.append(arg5);
                    v7.append(" with tag ");
                    v7.append(arg6);
                    v7.append(" to container view with no id");
                    throw new IllegalArgumentException(v7.toString());
                }
            }

            this.a(new a(arg7, arg5));
            return;
        }

        StringBuilder v5 = new StringBuilder();
        v5.append("Fragment ");
        v5.append(v0.getCanonicalName());
        v5.append(" must be a public static class to be  properly recreated from");
        v5.append(" instance state.");
        throw new IllegalStateException(v5.toString());
    }

    int a(boolean arg4) {
        if(!this.l) {
            if(m.a) {
                Log.v("FragmentManager", "Commit: " + this);
                PrintWriter v1_1 = new PrintWriter(new android.support.v4.f.e("FragmentManager"));
                this.a("  ", null, v1_1, null);
                v1_1.close();
            }

            this.l = true;
            int v0 = this.i ? this.a.a(this) : -1;
            this.m = v0;
            this.a.a(((h)this), arg4);
            return this.m;
        }

        throw new IllegalStateException("commit already called");
    }

    public void a(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
        this.a(arg1, arg3, true);
    }

    Fragment a(ArrayList arg14, Fragment arg15) {
        Fragment v1 = arg15;
        int v15;
        for(v15 = 0; v15 < this.b.size(); ++v15) {
            Object v2 = this.b.get(v15);
            Fragment v4 = null;
            int v5 = 9;
            switch(((a)v2).a) {
                case 2: {
                    Fragment v3 = ((a)v2).b;
                    int v7 = v3.mContainerId;
                    int v8 = arg14.size() - 1;
                    Fragment v9 = v1;
                    int v1_1 = v15;
                    v15 = 0;
                    while(v8 >= 0) {
                        Object v10 = arg14.get(v8);
                        if(((Fragment)v10).mContainerId == v7) {
                            if((((Fragment)v10)) == v3) {
                                v15 = 1;
                            }
                            else {
                                if((((Fragment)v10)) == v9) {
                                    this.b.add(v1_1, new a(v5, ((Fragment)v10)));
                                    ++v1_1;
                                    v9 = v4;
                                }

                                a v11 = new a(3, ((Fragment)v10));
                                v11.c = ((a)v2).c;
                                v11.e = ((a)v2).e;
                                v11.d = ((a)v2).d;
                                v11.f = ((a)v2).f;
                                this.b.add(v1_1, v11);
                                arg14.remove(v10);
                                ++v1_1;
                            }
                        }

                        --v8;
                    }

                    if(v15 != 0) {
                        this.b.remove(v1_1);
                        --v1_1;
                    }
                    else {
                        ((a)v2).a = 1;
                        arg14.add(v3);
                    }

                    v15 = v1_1;
                    v1 = v9;
                    break;
                }
                case 3: 
                case 6: {
                    arg14.remove(((a)v2).b);
                    if(((a)v2).b != v1) {
                        goto label_84;
                    }

                    this.b.add(v15, new a(v5, ((a)v2).b));
                    ++v15;
                    v1 = v4;
                    break;
                }
                case 1: 
                case 7: {
                    arg14.add(((a)v2).b);
                    break;
                }
                case 8: {
                    this.b.add(v15, new a(v5, v1));
                    ++v15;
                    v1 = ((a)v2).b;
                    break;
                }
                default: {
                    break;
                }
            }

        label_84:
        }

        return v1;
    }

    public o a() {
        if(!this.i) {
            this.j = false;
            return this;
        }

        throw new IllegalStateException("This transaction is already being added to the back stack");
    }

    public o a(int arg2, Fragment arg3) {
        return this.a(arg2, arg3, null);
    }

    public o a(int arg2, Fragment arg3, String arg4) {
        if(arg2 != 0) {
            this.a(arg2, arg3, arg4, 2);
            return this;
        }

        throw new IllegalArgumentException("Must use non-zero containerViewId");
    }

    public o a(Fragment arg3) {
        this.a(new a(3, arg3));
        return this;
    }

    public o a(Fragment arg3, String arg4) {
        this.a(0, arg3, arg4, 1);
        return this;
    }

    void a(c arg4) {
        int v0;
        for(v0 = 0; v0 < this.b.size(); ++v0) {
            Object v1 = this.b.get(v0);
            if(e.b(((a)v1))) {
                ((a)v1).b.setOnStartEnterTransitionListener(arg4);
            }
        }
    }

    public void a(String arg6, PrintWriter arg7, boolean arg8) {
        String v3_1;
        if(arg8) {
            arg7.print(arg6);
            arg7.print("mName=");
            arg7.print(this.k);
            arg7.print(" mIndex=");
            arg7.print(this.m);
            arg7.print(" mCommitted=");
            arg7.println(this.l);
            if(this.g != 0) {
                arg7.print(arg6);
                arg7.print("mTransition=#");
                arg7.print(Integer.toHexString(this.g));
                arg7.print(" mTransitionStyle=#");
                arg7.println(Integer.toHexString(this.h));
            }

            if(this.c != 0 || this.d != 0) {
                arg7.print(arg6);
                arg7.print("mEnterAnim=#");
                arg7.print(Integer.toHexString(this.c));
                arg7.print(" mExitAnim=#");
                arg7.println(Integer.toHexString(this.d));
            }

            if(this.e != 0 || this.f != 0) {
                arg7.print(arg6);
                arg7.print("mPopEnterAnim=#");
                arg7.print(Integer.toHexString(this.e));
                arg7.print(" mPopExitAnim=#");
                arg7.println(Integer.toHexString(this.f));
            }

            if(this.n != 0 || this.o != null) {
                arg7.print(arg6);
                arg7.print("mBreadCrumbTitleRes=#");
                arg7.print(Integer.toHexString(this.n));
                arg7.print(" mBreadCrumbTitleText=");
                arg7.println(this.o);
            }

            if(this.p == 0 && this.q == null) {
                goto label_85;
            }

            arg7.print(arg6);
            arg7.print("mBreadCrumbShortTitleRes=#");
            arg7.print(Integer.toHexString(this.p));
            arg7.print(" mBreadCrumbShortTitleText=");
            arg7.println(this.q);
        }

    label_85:
        if(!this.b.isEmpty()) {
            arg7.print(arg6);
            arg7.println("Operations:");
            StringBuilder v0 = new StringBuilder();
            v0.append(arg6);
            v0.append("    ");
            v0.toString();
            int v0_1 = this.b.size();
            int v1;
            for(v1 = 0; v1 < v0_1; ++v1) {
                Object v2 = this.b.get(v1);
                switch(((a)v2).a) {
                    case 0: {
                        v3_1 = "NULL";
                        break;
                    }
                    case 1: {
                        v3_1 = "ADD";
                        break;
                    }
                    case 2: {
                        v3_1 = "REPLACE";
                        break;
                    }
                    case 3: {
                        v3_1 = "REMOVE";
                        break;
                    }
                    case 4: {
                        v3_1 = "HIDE";
                        break;
                    }
                    case 5: {
                        v3_1 = "SHOW";
                        break;
                    }
                    case 6: {
                        v3_1 = "DETACH";
                        break;
                    }
                    case 7: {
                        v3_1 = "ATTACH";
                        break;
                    }
                    case 8: {
                        v3_1 = "SET_PRIMARY_NAV";
                        break;
                    }
                    case 9: {
                        v3_1 = "UNSET_PRIMARY_NAV";
                        break;
                    }
                    default: {
                        v3_1 = "cmd=" + ((a)v2).a;
                        break;
                    }
                }

                arg7.print(arg6);
                arg7.print("  Op #");
                arg7.print(v1);
                arg7.print(": ");
                arg7.print(v3_1);
                arg7.print(" ");
                arg7.println(((a)v2).b);
                if(arg8) {
                    if(((a)v2).c != 0 || ((a)v2).d != 0) {
                        arg7.print(arg6);
                        arg7.print("enterAnim=#");
                        arg7.print(Integer.toHexString(((a)v2).c));
                        arg7.print(" exitAnim=#");
                        arg7.println(Integer.toHexString(((a)v2).d));
                    }

                    if(((a)v2).e == 0 && ((a)v2).f == 0) {
                        goto label_174;
                    }

                    arg7.print(arg6);
                    arg7.print("popEnterAnim=#");
                    arg7.print(Integer.toHexString(((a)v2).e));
                    arg7.print(" popExitAnim=#");
                    arg7.println(Integer.toHexString(((a)v2).f));
                }

            label_174:
            }
        }
    }

    boolean a(ArrayList arg11, int arg12, int arg13) {
        if(arg13 == arg12) {
            return 0;
        }

        int v1 = this.b.size();
        int v2 = 0;
        int v3 = -1;
        while(v2 < v1) {
            Object v4 = this.b.get(v2);
            int v4_1 = ((a)v4).b != null ? ((a)v4).b.mContainerId : 0;
            if(v4_1 != 0 && v4_1 != v3) {
                for(v3 = arg12; v3 < arg13; ++v3) {
                    Object v5 = arg11.get(v3);
                    int v6 = ((e)v5).b.size();
                    int v7;
                    for(v7 = 0; v7 < v6; ++v7) {
                        Object v8 = ((e)v5).b.get(v7);
                        int v8_1 = ((a)v8).b != null ? ((a)v8).b.mContainerId : 0;
                        if(v8_1 == v4_1) {
                            return 1;
                        }
                    }
                }

                v3 = v4_1;
            }

            ++v2;
        }

        return 0;
    }

    public boolean a(ArrayList arg4, ArrayList arg5) {
        if(m.a) {
            Log.v("FragmentManager", "Run: " + this);
        }

        arg4.add(this);
        arg5.add(Boolean.valueOf(false));
        if(this.i) {
            this.a.b(this);
        }

        return 1;
    }

    boolean b(int arg6) {
        int v0 = this.b.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.b.get(v2);
            int v3_1 = ((a)v3).b != null ? ((a)v3).b.mContainerId : 0;
            if(v3_1 != 0 && v3_1 == arg6) {
                return 1;
            }
        }

        return 0;
    }

    private static boolean b(a arg1) {
        Fragment v1 = arg1.b;
        boolean v1_1 = v1 == null || !v1.mAdded || v1.mView == null || (v1.mDetached) || (v1.mHidden) || !v1.isPostponed() ? false : true;
        return v1_1;
    }

    Fragment b(ArrayList arg5, Fragment arg6) {
        int v0;
        for(v0 = 0; v0 < this.b.size(); ++v0) {
            Object v1 = this.b.get(v0);
            int v2 = ((a)v1).a;
            if(v2 != 1) {
                if(v2 != 3) {
                    switch(v2) {
                        case 6: {
                            goto label_17;
                        }
                        case 7: {
                            goto label_20;
                        }
                        case 8: {
                            goto label_15;
                        }
                        case 9: {
                            goto label_13;
                        }
                    }

                    goto label_22;
                label_13:
                    arg6 = ((a)v1).b;
                    goto label_22;
                label_15:
                    arg6 = null;
                    goto label_22;
                }

            label_17:
                arg5.add(((a)v1).b);
            }
            else {
            label_20:
                arg5.remove(((a)v1).b);
            }

        label_22:
        }

        return arg6;
    }

    public void b() {
        if(this.u != null) {
            int v0 = 0;
            int v1 = this.u.size();
            while(v0 < v1) {
                this.u.get(v0).run();
                ++v0;
            }

            this.u = null;
        }
    }

    void b(boolean arg7) {
        int v0;
        for(v0 = this.b.size() - 1; v0 >= 0; --v0) {
            Object v2 = this.b.get(v0);
            Fragment v3 = ((a)v2).b;
            if(v3 != null) {
                v3.setNextTransition(m.d(this.g), this.h);
            }

            int v4 = ((a)v2).a;
            if(v4 != 1) {
                switch(v4) {
                    case 3: {
                        goto label_53;
                    }
                    case 4: {
                        goto label_48;
                    }
                    case 5: {
                        goto label_43;
                    }
                    case 6: {
                        goto label_38;
                    }
                    case 7: {
                        goto label_33;
                    }
                    case 8: {
                        goto label_29;
                    }
                    case 9: {
                        goto label_26;
                    }
                }

                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("Unknown cmd: ");
                v0_1.append(((a)v2).a);
                throw new IllegalArgumentException(v0_1.toString());
            label_33:
                v3.setNextAnim(((a)v2).f);
                this.a.k(v3);
                goto label_63;
            label_53:
                v3.setNextAnim(((a)v2).e);
                this.a.a(v3, false);
                goto label_63;
            label_38:
                v3.setNextAnim(((a)v2).e);
                this.a.l(v3);
                goto label_63;
            label_26:
                this.a.o(v3);
                goto label_63;
            label_43:
                v3.setNextAnim(((a)v2).f);
                this.a.i(v3);
                goto label_63;
            label_29:
                this.a.o(null);
                goto label_63;
            label_48:
                v3.setNextAnim(((a)v2).e);
                this.a.j(v3);
            }
            else {
                v3.setNextAnim(((a)v2).f);
                this.a.h(v3);
            }

        label_63:
            if(!this.t && ((a)v2).a != 3 && v3 != null) {
                this.a.e(v3);
            }
        }

        if(!this.t && (arg7)) {
            this.a.a(this.a.l, true);
        }
    }

    public int c() {
        return this.a(false);
    }

    public int d() {
        return this.a(true);
    }

    public void e() {
        this.a();
        this.a.b(((h)this), false);
    }

    void f() {
        int v0 = this.b.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v4 = this.b.get(v2);
            Fragment v5 = ((a)v4).b;
            if(v5 != null) {
                v5.setNextTransition(this.g, this.h);
            }

            int v6 = ((a)v4).a;
            if(v6 != 1) {
                switch(v6) {
                    case 3: {
                        goto label_53;
                    }
                    case 4: {
                        goto label_48;
                    }
                    case 5: {
                        goto label_43;
                    }
                    case 6: {
                        goto label_38;
                    }
                    case 7: {
                        goto label_33;
                    }
                    case 8: {
                        goto label_30;
                    }
                    case 9: {
                        goto label_26;
                    }
                }

                StringBuilder v1 = new StringBuilder();
                v1.append("Unknown cmd: ");
                v1.append(((a)v4).a);
                throw new IllegalArgumentException(v1.toString());
            label_33:
                v5.setNextAnim(((a)v4).c);
                this.a.l(v5);
                goto label_62;
            label_53:
                v5.setNextAnim(((a)v4).d);
                this.a.h(v5);
                goto label_62;
            label_38:
                v5.setNextAnim(((a)v4).d);
                this.a.k(v5);
                goto label_62;
            label_26:
                this.a.o(null);
                goto label_62;
            label_43:
                v5.setNextAnim(((a)v4).c);
                this.a.j(v5);
                goto label_62;
            label_30:
                this.a.o(v5);
                goto label_62;
            label_48:
                v5.setNextAnim(((a)v4).d);
                this.a.i(v5);
            }
            else {
                v5.setNextAnim(((a)v4).c);
                this.a.a(v5, false);
            }

        label_62:
            if(!this.t && ((a)v4).a != 1 && v5 != null) {
                this.a.e(v5);
            }
        }

        if(!this.t) {
            this.a.a(this.a.l, true);
        }
    }

    boolean g() {
        int v1;
        for(v1 = 0; v1 < this.b.size(); ++v1) {
            if(e.b(this.b.get(v1))) {
                return 1;
            }
        }

        return 0;
    }

    public String h() {
        return this.k;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(128);
        v0.append("BackStackEntry{");
        v0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this.m >= 0) {
            v0.append(" #");
            v0.append(this.m);
        }

        if(this.k != null) {
            v0.append(" ");
            v0.append(this.k);
        }

        v0.append("}");
        return v0.toString();
    }
}

