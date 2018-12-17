package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class p {
    class a {
        public Fragment a;
        public boolean b;
        public e c;
        public Fragment d;
        public boolean e;
        public e f;

        a() {
            super();
        }
    }

    private static final int[] a;
    private static final r b;
    private static final r c;

    static {
        r v0_1;
        p.a = new int[]{0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
        if(Build$VERSION.SDK_INT >= 21) {
            q v0 = new q();
        }
        else {
            v0_1 = null;
        }

        p.b = v0_1;
        p.c = p.a();
    }

    private static r a() {
        try {
            return Class.forName("android.support.f.e").getDeclaredConstructor().newInstance();
        }
        catch(Exception ) {
            return null;
        }
    }

    private static a a(a arg0, SparseArray arg1, int arg2) {
        if(arg0 == null) {
            arg0 = new a();
            arg1.put(arg2, arg0);
        }

        return arg0;
    }

    private static r a(Fragment arg2, Fragment arg3) {
        Object v2;
        ArrayList v0 = new ArrayList();
        if(arg2 != null) {
            Object v1 = arg2.getExitTransition();
            if(v1 != null) {
                v0.add(v1);
            }

            v1 = arg2.getReturnTransition();
            if(v1 != null) {
                v0.add(v1);
            }

            v2 = arg2.getSharedElementReturnTransition();
            if(v2 == null) {
                goto label_12;
            }

            v0.add(v2);
        }

    label_12:
        if(arg3 != null) {
            v2 = arg3.getEnterTransition();
            if(v2 != null) {
                v0.add(v2);
            }

            v2 = arg3.getReenterTransition();
            if(v2 != null) {
                v0.add(v2);
            }

            v2 = arg3.getSharedElementEnterTransition();
            if(v2 == null) {
                goto label_22;
            }

            v0.add(v2);
        }

    label_22:
        r v3 = null;
        if(v0.isEmpty()) {
            return v3;
        }

        if(p.b != null && (p.a(p.b, ((List)v0)))) {
            return p.b;
        }

        if(p.c != null && (p.a(p.c, ((List)v0)))) {
            return p.c;
        }

        if(p.b == null && p.c == null) {
            return v3;
        }

        throw new IllegalArgumentException("Invalid Transition types");
    }

    private static boolean a(r arg4, List arg5) {
        int v0 = arg5.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(!arg4.a(arg5.get(v2))) {
                return 0;
            }
        }

        return 1;
    }

    private static android.support.v4.f.a a(int arg9, ArrayList arg10, ArrayList arg11, int arg12, int arg13) {
        ArrayList v1_1;
        ArrayList v2_1;
        android.support.v4.f.a v0 = new android.support.v4.f.a();
        --arg13;
        while(arg13 >= arg12) {
            Object v1 = arg10.get(arg13);
            if(!((e)v1).b(arg9)) {
            }
            else {
                boolean v2 = arg11.get(arg13).booleanValue();
                if(((e)v1).r != null) {
                    int v3 = ((e)v1).r.size();
                    if(v2) {
                        v2_1 = ((e)v1).r;
                        v1_1 = ((e)v1).s;
                    }
                    else {
                        ArrayList v8 = ((e)v1).r;
                        v2_1 = ((e)v1).s;
                        v1_1 = v8;
                    }

                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        Object v5 = v1_1.get(v4);
                        Object v6 = v2_1.get(v4);
                        Object v7 = v0.remove(v6);
                        if(v7 != null) {
                            v0.put(v5, v7);
                        }
                        else {
                            v0.put(v5, v6);
                        }
                    }
                }
            }

            --arg13;
        }

        return v0;
    }

    static android.support.v4.f.a a(r arg3, android.support.v4.f.a arg4, Object arg5, a arg6) {
        String v0_2;
        ArrayList v3_1;
        ae v6;
        Fragment v0 = arg6.a;
        View v1 = v0.getView();
        if(!arg4.isEmpty() && arg5 != null) {
            if(v1 == null) {
            }
            else {
                android.support.v4.f.a v5 = new android.support.v4.f.a();
                arg3.a(((Map)v5), v1);
                e v3 = arg6.c;
                if(arg6.b) {
                    v6 = v0.getExitTransitionCallback();
                    v3_1 = v3.r;
                }
                else {
                    v6 = v0.getEnterTransitionCallback();
                    v3_1 = v3.s;
                }

                if(v3_1 != null) {
                    v5.a(((Collection)v3_1));
                    v5.a(arg4.values());
                }

                if(v6 != null) {
                    v6.a(((List)v3_1), ((Map)v5));
                    int v6_1;
                    for(v6_1 = v3_1.size() - 1; v6_1 >= 0; --v6_1) {
                        Object v0_1 = v3_1.get(v6_1);
                        Object v1_1 = v5.get(v0_1);
                        if(v1_1 == null) {
                            v0_2 = p.a(arg4, ((String)v0_1));
                            if(v0_2 != null) {
                                arg4.remove(v0_2);
                            }
                        }
                        else if(!((String)v0_1).equals(t.q(((View)v1_1)))) {
                            v0_2 = p.a(arg4, ((String)v0_1));
                            if(v0_2 != null) {
                                arg4.put(v0_2, t.q(((View)v1_1)));
                            }
                        }
                    }
                }
                else {
                    p.a(arg4, v5);
                }

                return v5;
            }
        }

        arg4.clear();
        return null;
    }

    private static String a(android.support.v4.f.a arg3, String arg4) {
        int v0 = arg3.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.equals(arg3.c(v1))) {
                return arg3.b(v1);
            }
        }

        return null;
    }

    private static void a(android.support.v4.f.a arg2, android.support.v4.f.a arg3) {
        int v0;
        for(v0 = arg2.size() - 1; v0 >= 0; --v0) {
            if(!arg3.containsKey(arg2.c(v0))) {
                arg2.d(v0);
            }
        }
    }

    static View a(android.support.v4.f.a arg0, a arg1, Object arg2, boolean arg3) {
        e v1 = arg1.c;
        if(arg2 != null && arg0 != null && v1.r != null && !v1.r.isEmpty()) {
            ArrayList v1_1 = arg3 ? v1.r : v1.s;
            Object v1_2 = v1_1.get(0);
            return arg0.get(v1_2);
        }

        return null;
    }

    private static Object a(r arg0, Fragment arg1, Fragment arg2, boolean arg3) {
        if(arg1 != null) {
            if(arg2 == null) {
            }
            else {
                Object v1 = arg3 ? arg2.getSharedElementReturnTransition() : arg1.getSharedElementEnterTransition();
                return arg0.c(arg0.b(v1));
            }
        }

        return null;
    }

    private static Object a(r arg0, Fragment arg1, boolean arg2) {
        if(arg1 == null) {
            return null;
        }

        Object v1 = arg2 ? arg1.getReenterTransition() : arg1.getEnterTransition();
        return arg0.b(v1);
    }

    private static Object a(r arg16, ViewGroup arg17, View arg18, android.support.v4.f.a arg19, a arg20, ArrayList arg21, ArrayList arg22, Object arg23, Object arg24) {
        View v5_1;
        Rect v7_1;
        Object v14;
        r v6 = arg16;
        View v0 = arg18;
        android.support.v4.f.a v1 = arg19;
        a v7 = arg20;
        ArrayList v2 = arg21;
        ArrayList v3 = arg22;
        Object v8 = arg23;
        Fragment v9 = v7.a;
        Fragment v10 = v7.d;
        if(v9 != null) {
            v9.getView().setVisibility(0);
        }

        Object v4 = null;
        if(v9 != null) {
            if(v10 == null) {
            }
            else {
                boolean v11 = v7.b;
                Object v5 = arg19.isEmpty() ? v4 : p.a(v6, v9, v10, v11);
                android.support.v4.f.a v12 = p.b(v6, v1, v5, v7);
                android.support.v4.f.a v13 = p.a(v6, v1, v5, v7);
                if(arg19.isEmpty()) {
                    if(v12 != null) {
                        v12.clear();
                    }

                    if(v13 != null) {
                        v13.clear();
                    }

                    v14 = v4;
                }
                else {
                    p.a(v2, v12, arg19.keySet());
                    p.a(v3, v13, arg19.values());
                    v14 = v5;
                }

                if(v8 == null && arg24 == null && v14 == null) {
                    return v4;
                }

                p.a(v9, v10, v11, v12, true);
                if(v14 != null) {
                    v3.add(v0);
                    v6.a(v14, v0, v2);
                    p.a(arg16, v14, arg24, v12, v7.e, v7.f);
                    Rect v0_1 = new Rect();
                    View v1_1 = p.a(v13, v7, v8, v11);
                    if(v1_1 != null) {
                        v6.a(v8, v0_1);
                    }

                    v7_1 = v0_1;
                    v5_1 = v1_1;
                }
                else {
                    v5_1 = ((View)v4);
                    v7_1 = ((Rect)v5_1);
                }

                aa.a(arg17, new Runnable(v9, v10, v11, v13, v5_1, arg16, v7_1) {
                    public void run() {
                        p.a(this.a, this.b, this.c, this.d, false);
                        if(this.e != null) {
                            this.f.a(this.e, this.g);
                        }
                    }
                });
                return v14;
            }
        }

        return v4;
    }

    private static void a(ArrayList arg3, android.support.v4.f.a arg4, Collection arg5) {
        int v0;
        for(v0 = arg4.size() - 1; v0 >= 0; --v0) {
            Object v1 = arg4.c(v0);
            if(arg5.contains(t.q(((View)v1)))) {
                arg3.add(v1);
            }
        }
    }

    static void a(Fragment arg3, Fragment arg4, boolean arg5, android.support.v4.f.a arg6, boolean arg7) {
        ae v3 = arg5 ? arg4.getEnterTransitionCallback() : arg3.getEnterTransitionCallback();
        if(v3 != null) {
            ArrayList v4 = new ArrayList();
            ArrayList v5 = new ArrayList();
            int v0 = 0;
            int v1 = arg6 == null ? 0 : arg6.size();
            while(v0 < v1) {
                v5.add(arg6.b(v0));
                v4.add(arg6.c(v0));
                ++v0;
            }

            List v6 = null;
            if(arg7) {
                v3.a(((List)v5), ((List)v4), v6);
                return;
            }

            v3.b(((List)v5), ((List)v4), v6);
        }
    }

    private static void a(r arg1, Object arg2, Object arg3, android.support.v4.f.a arg4, boolean arg5, e arg6) {
        if(arg6.r != null && !arg6.r.isEmpty()) {
            ArrayList v5 = arg5 ? arg6.s : arg6.r;
            Object v5_1 = v5.get(0);
            Object v4 = arg4.get(v5_1);
            arg1.a(arg2, ((View)v4));
            if(arg3 == null) {
                return;
            }

            arg1.a(arg3, ((View)v4));
        }
    }

    private static Object a(r arg0, Object arg1, Object arg2, Object arg3, Fragment arg4, boolean arg5) {
        boolean v4;
        if(arg1 == null || arg2 == null || arg4 == null) {
            v4 = true;
        }
        else if(arg5) {
            v4 = arg4.getAllowReturnTransitionOverlap();
        }
        else {
            v4 = arg4.getAllowEnterTransitionOverlap();
        }

        Object v0 = v4 ? arg0.a(arg2, arg1, arg3) : arg0.b(arg2, arg1, arg3);
        return v0;
    }

    static ArrayList a(r arg1, Object arg2, Fragment arg3, ArrayList arg4, View arg5) {
        ArrayList v0;
        if(arg2 != null) {
            v0 = new ArrayList();
            View v3 = arg3.getView();
            if(v3 != null) {
                arg1.a(v0, v3);
            }

            if(arg4 != null) {
                v0.removeAll(((Collection)arg4));
            }

            if(v0.isEmpty()) {
                return v0;
            }

            v0.add(arg5);
            arg1.a(arg2, v0);
        }
        else {
            v0 = null;
        }

        return v0;
    }

    private static void a(e arg15, android.support.v4.app.e$a arg16, SparseArray arg17, boolean arg18, boolean arg19) {
        int v12;
        int v13;
        boolean v1_2;
        e v0 = arg15;
        android.support.v4.app.e$a v1 = arg16;
        SparseArray v2 = arg17;
        boolean v3 = arg18;
        Fragment v10 = v1.b;
        if(v10 == null) {
            return;
        }

        int v11 = v10.mContainerId;
        if(v11 == 0) {
            return;
        }

        int v1_1 = v3 ? p.a[v1.a] : v1.a;
        boolean v4 = false;
        if(v1_1 != 1) {
            switch(v1_1) {
                case 4: {
                    if(arg19) {
                        if(!v10.mHiddenChanged) {
                            goto label_60;
                        }
                        else if(!v10.mAdded) {
                            goto label_60;
                        }
                        else if(v10.mHidden) {
                            goto label_58;
                        }
                        else {
                            goto label_60;
                        }
                    }
                    else if(!v10.mAdded) {
                        goto label_60;
                    }
                    else if(!v10.mHidden) {
                        goto label_58;
                    }
                    else {
                        goto label_60;
                    }

                    goto label_84;
                }
                case 5: {
                    if(!arg19) {
                        v1_2 = v10.mHidden;
                        goto label_81;
                    }
                    else if(!v10.mHiddenChanged) {
                        goto label_80;
                    }
                    else if(v10.mHidden) {
                        goto label_80;
                    }
                    else if(v10.mAdded) {
                        goto label_78;
                    }
                    else {
                        goto label_80;
                    }
                }
                case 3: 
                case 6: {
                    if(arg19) {
                        if(v10.mAdded) {
                            goto label_60;
                        }
                        else if(v10.mView == null) {
                            goto label_60;
                        }
                        else if(v10.mView.getVisibility() == 0) {
                            if(v10.mPostponedAlpha < 0f) {
                                goto label_60;
                            }

                            goto label_58;
                        }
                        else {
                            goto label_60;
                        }
                    }
                    else if(!v10.mAdded) {
                    label_60:
                        v1_1 = 0;
                    label_67:
                        v13 = v1_1;
                        v1_1 = 0;
                        v12 = 1;
                    }
                    else if(!v10.mHidden) {
                    label_58:
                        v1_1 = 1;
                        goto label_67;
                    }
                    else {
                        goto label_60;
                    }

                    goto label_84;
                }
                case 7: {
                    goto label_71;
                }
                default: {
                    v1_1 = 0;
                    goto label_21;
                }
            }
        }
        else {
        label_71:
            if(arg19) {
                v1_2 = v10.mIsNewlyAdded;
                goto label_81;
            }
            else if(v10.mAdded) {
            label_80:
                v1_2 = false;
            label_81:
                v4 = v1_2;
                v1_1 = 1;
            label_21:
                v12 = 0;
                v13 = 0;
            }
            else if(!v10.mHidden) {
            label_78:
                v1_2 = true;
                goto label_81;
            }
            else {
                goto label_80;
            }
        }

    label_84:
        Object v6 = v2.get(v11);
        if(v4) {
            a v6_1 = p.a(((a)v6), v2, v11);
            v6_1.a = v10;
            v6_1.b = v3;
            v6_1.c = v0;
        }

        Object v14 = v6;
        Fragment v9 = null;
        if(!arg19 && v1_1 != 0) {
            if(v14 != null && ((a)v14).d == v10) {
                ((a)v14).d = v9;
            }

            m v4_1 = v0.a;
            if(v10.mState >= 1) {
                goto label_113;
            }

            if(v4_1.l < 1) {
                goto label_113;
            }

            if(v0.t) {
                goto label_113;
            }

            v4_1.f(v10);
            v4_1.a(v10, 1, 0, 0, false);
        }

    label_113:
        if(v13 != 0 && (v14 == null || ((a)v14).d == null)) {
            a v14_1 = p.a(((a)v14), v2, v11);
            v14_1.d = v10;
            v14_1.e = v3;
            v14_1.f = v0;
        }

        if(!arg19 && v12 != 0 && v14 != null && ((a)v14).a == v10) {
            ((a)v14).a = null;
        }
    }

    public static void a(e arg4, SparseArray arg5, boolean arg6) {
        int v0 = arg4.b.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            p.a(arg4, arg4.b.get(v2), arg5, false, arg6);
        }
    }

    private static void a(m arg19, int arg20, a arg21, View arg22, android.support.v4.f.a arg23) {
        m v0 = arg19;
        a v4 = arg21;
        View v9 = arg22;
        View v0_1 = v0.n.a() ? v0.n.a(arg20) : null;
        View v10 = v0_1;
        if(v10 == null) {
            return;
        }

        Fragment v11 = v4.a;
        Fragment v12 = v4.d;
        r v13 = p.a(v12, v11);
        if(v13 == null) {
            return;
        }

        boolean v14 = v4.b;
        boolean v0_2 = v4.e;
        ArrayList v15 = new ArrayList();
        ArrayList v8 = new ArrayList();
        Object v7 = p.a(v13, v11, v14);
        View v1 = v10;
        Object v16 = p.b(v13, v12, v0_2);
        View v18 = v10;
        ArrayList v10_1 = v8;
        Object v8_1 = p.a(v13, ((ViewGroup)v1), arg22, arg23, arg21, v8, v15, v7, v16);
        Object v6 = v7;
        if(v6 != null || v8_1 != null) {
            v7 = v16;
        }
        else {
            v7 = v16;
            if(v7 == null) {
                return;
            }
        }

        ArrayList v5 = p.a(v13, v7, v12, v10_1, v9);
        ArrayList v9_1 = p.a(v13, v6, v11, v15, v9);
        p.a(v9_1, 4);
        Fragment v4_1 = v11;
        ArrayList v11_1 = v5;
        Object v14_1 = p.a(v13, v6, v7, v8_1, v4_1, v14);
        if(v14_1 != null) {
            p.a(v13, v7, v12, v11_1);
            ArrayList v12_1 = v13.a(v15);
            v13.a(v14_1, v6, v9_1, v7, v11_1, v8_1, v15);
            v13.a(v18, v14_1);
            v13.a(v18, v10_1, v15, v12_1, arg23);
            p.a(v9_1, 0);
            v13.a(v8_1, v10_1, v15);
        }
    }

    static void a(ArrayList arg2, int arg3) {
        if(arg2 == null) {
            return;
        }

        int v0;
        for(v0 = arg2.size() - 1; v0 >= 0; --v0) {
            arg2.get(v0).setVisibility(arg3);
        }
    }

    private static void a(r arg1, Object arg2, Fragment arg3, ArrayList arg4) {
        if(arg3 != null && arg2 != null && (arg3.mAdded) && (arg3.mHidden) && (arg3.mHiddenChanged)) {
            arg3.setHideReplaced(true);
            arg1.b(arg2, arg3.getView(), arg4);
            aa.a(arg3.mContainer, new Runnable(arg4) {
                public void run() {
                    p.a(this.a, 4);
                }
            });
        }
    }

    static void a(m arg7, ArrayList arg8, ArrayList arg9, int arg10, int arg11, boolean arg12) {
        if(arg7.l < 1) {
            return;
        }

        SparseArray v0 = new SparseArray();
        int v1;
        for(v1 = arg10; v1 < arg11; ++v1) {
            Object v2 = arg8.get(v1);
            if(arg9.get(v1).booleanValue()) {
                p.b(((e)v2), v0, arg12);
            }
            else {
                p.a(((e)v2), v0, arg12);
            }
        }

        if(v0.size() != 0) {
            View v1_1 = new View(arg7.m.i());
            int v2_1 = v0.size();
            int v3;
            for(v3 = 0; v3 < v2_1; ++v3) {
                int v4 = v0.keyAt(v3);
                android.support.v4.f.a v5 = p.a(v4, arg8, arg9, arg10, arg11);
                Object v6 = v0.valueAt(v3);
                if(arg12) {
                    p.a(arg7, v4, ((a)v6), v1_1, v5);
                }
                else {
                    p.b(arg7, v4, ((a)v6), v1_1, v5);
                }
            }
        }
    }

    private static void a(r arg10, ViewGroup arg11, Fragment arg12, View arg13, ArrayList arg14, Object arg15, ArrayList arg16, Object arg17, ArrayList arg18) {
        aa.a(((View)arg11), new Runnable(arg15, arg10, arg13, arg12, arg14, arg16, arg18, arg17) {
            public void run() {
                if(this.a != null) {
                    this.b.c(this.a, this.c);
                    this.f.addAll(p.a(this.b, this.a, this.d, this.e, this.c));
                }

                if(this.g != null) {
                    if(this.h != null) {
                        ArrayList v0 = new ArrayList();
                        v0.add(this.c);
                        this.b.b(this.h, this.g, v0);
                    }

                    this.g.clear();
                    this.g.add(this.c);
                }
            }
        });
    }

    private static android.support.v4.f.a b(r arg3, android.support.v4.f.a arg4, Object arg5, a arg6) {
        ArrayList v3_1;
        ae v5_1;
        if(!arg4.isEmpty()) {
            if(arg5 == null) {
            }
            else {
                Fragment v5 = arg6.d;
                android.support.v4.f.a v0 = new android.support.v4.f.a();
                arg3.a(((Map)v0), v5.getView());
                e v3 = arg6.f;
                if(arg6.e) {
                    v5_1 = v5.getEnterTransitionCallback();
                    v3_1 = v3.s;
                }
                else {
                    v5_1 = v5.getExitTransitionCallback();
                    v3_1 = v3.r;
                }

                v0.a(((Collection)v3_1));
                if(v5_1 != null) {
                    v5_1.a(((List)v3_1), ((Map)v0));
                    int v5_2;
                    for(v5_2 = v3_1.size() - 1; v5_2 >= 0; --v5_2) {
                        Object v6 = v3_1.get(v5_2);
                        Object v1 = v0.get(v6);
                        if(v1 == null) {
                            arg4.remove(v6);
                        }
                        else if(!((String)v6).equals(t.q(((View)v1)))) {
                            arg4.put(t.q(((View)v1)), arg4.remove(v6));
                        }
                    }
                }
                else {
                    arg4.a(v0.keySet());
                }

                return v0;
            }
        }

        arg4.clear();
        return null;
    }

    private static Object b(r arg0, Fragment arg1, boolean arg2) {
        if(arg1 == null) {
            return null;
        }

        Object v1 = arg2 ? arg1.getReturnTransition() : arg1.getExitTransition();
        return arg0.b(v1);
    }

    public static void b(e arg3, SparseArray arg4, boolean arg5) {
        if(!arg3.a.n.a()) {
            return;
        }

        int v0;
        for(v0 = arg3.b.size() - 1; v0 >= 0; --v0) {
            p.a(arg3, arg3.b.get(v0), arg4, true, arg5);
        }
    }

    private static void b(m arg21, int arg22, a arg23, View arg24, android.support.v4.f.a arg25) {
        Object v0_2;
        m v0 = arg21;
        a v9 = arg23;
        View v10 = arg24;
        android.support.v4.f.a v11 = arg25;
        View v13 = v0.n.a() ? v0.n.a(arg22) : null;
        if(v13 == null) {
            return;
        }

        Fragment v14 = v9.a;
        Fragment v15 = v9.d;
        r v8 = p.a(v15, v14);
        if(v8 == null) {
            return;
        }

        boolean v0_1 = v9.b;
        boolean v1 = v9.e;
        Object v7 = p.a(v8, v14, v0_1);
        Object v6 = p.b(v8, v15, v1);
        ArrayList v5 = new ArrayList();
        ArrayList v16 = new ArrayList();
        ArrayList v17 = v5;
        Object v18 = v6;
        r v12 = v8;
        v6 = p.b(v8, v13, arg24, arg25, arg23, v5, v16, v7, v18);
        Object v8_1 = v7;
        if(v8_1 != null || v6 != null) {
            v0_2 = v18;
        }
        else {
            v0_2 = v18;
            if(v0_2 == null) {
                return;
            }
        }

        ArrayList v15_1 = p.a(v12, v0_2, v15, v17, v10);
        Object v20 = v15_1 == null || (v15_1.isEmpty()) ? null : v0_2;
        v12.b(v8_1, v10);
        Object v9_1 = p.a(v12, v8_1, v20, v6, v14, v9.b);
        if(v9_1 != null) {
            v17 = new ArrayList();
            v12.a(v9_1, v8_1, v17, v20, v15_1, v6, v16);
            p.a(v12, v13, v14, arg24, v16, v8_1, v17, v20, v15_1);
            v12.a(v13, v16, ((Map)v11));
            v12.a(((ViewGroup)v13), v9_1);
            v12.a(((ViewGroup)v13), v16, ((Map)v11));
        }
    }

    private static Object b(r arg17, ViewGroup arg18, View arg19, android.support.v4.f.a arg20, a arg21, ArrayList arg22, ArrayList arg23, Object arg24, Object arg25) {
        Rect v15;
        Object v14;
        Object v1;
        android.support.v4.f.a v13;
        r v6 = arg17;
        a v7 = arg21;
        ArrayList v10 = arg22;
        Object v11 = arg24;
        Fragment v8 = v7.a;
        Fragment v9 = v7.d;
        Object v0 = null;
        if(v8 != null) {
            if(v9 == null) {
            }
            else {
                boolean v12 = v7.b;
                if(arg20.isEmpty()) {
                    v13 = arg20;
                    v1 = v0;
                }
                else {
                    v1 = p.a(v6, v8, v9, v12);
                    v13 = arg20;
                }

                android.support.v4.f.a v3 = p.b(v6, v13, v1, v7);
                if(arg20.isEmpty()) {
                    v14 = v0;
                }
                else {
                    v10.addAll(v3.values());
                    v14 = v1;
                }

                if(v11 == null && arg25 == null && v14 == null) {
                    return v0;
                }

                p.a(v8, v9, v12, v3, true);
                if(v14 != null) {
                    v15 = new Rect();
                    v6.a(v14, arg19, v10);
                    p.a(arg17, v14, arg25, v3, v7.e, v7.f);
                    if(v11 != null) {
                        v6.a(v11, v15);
                    }
                }
                else {
                    v15 = ((Rect)v0);
                }

                aa.a(arg18, new Runnable(arg17, arg20, v14, arg21, arg23, arg19, v8, v9, v12, arg22, arg24, v15) {
                    public void run() {
                        android.support.v4.f.a v0 = p.a(this.a, this.b, this.c, this.d);
                        if(v0 != null) {
                            this.e.addAll(v0.values());
                            this.e.add(this.f);
                        }

                        p.a(this.g, this.h, this.i, v0, false);
                        if(this.c != null) {
                            this.a.a(this.c, this.j, this.e);
                            View v0_1 = p.a(v0, this.d, this.k, this.i);
                            if(v0_1 != null) {
                                this.a.a(v0_1, this.l);
                            }
                        }
                    }
                });
                return v14;
            }
        }

        return v0;
    }
}

