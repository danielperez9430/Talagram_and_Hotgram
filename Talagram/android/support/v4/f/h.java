package android.support.v4.f;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class h {
    final class a implements Iterator {
        final int a;
        int b;
        int c;
        boolean d;

        a(h arg2, int arg3) {
            this.e = arg2;
            super();
            this.d = false;
            this.a = arg3;
            this.b = arg2.a();
        }

        public boolean hasNext() {
            boolean v0 = this.c < this.b ? true : false;
            return v0;
        }

        public Object next() {
            if(this.hasNext()) {
                Object v0 = this.e.a(this.c, this.a);
                ++this.c;
                this.d = true;
                return v0;
            }

            throw new NoSuchElementException();
        }

        public void remove() {
            if(this.d) {
                --this.c;
                --this.b;
                this.d = false;
                this.e.a(this.c);
                return;
            }

            throw new IllegalStateException();
        }
    }

    final class b implements Set {
        b(h arg1) {
            this.a = arg1;
            super();
        }

        public boolean a(Map$Entry arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean add(Object arg1) {
            return this.a(((Map$Entry)arg1));
        }

        public boolean addAll(Collection arg5) {
            int v0 = this.a.a();
            Iterator v5 = arg5.iterator();
            while(v5.hasNext()) {
                Object v1 = v5.next();
                this.a.a(((Map$Entry)v1).getKey(), ((Map$Entry)v1).getValue());
            }

            boolean v5_1 = v0 != this.a.a() ? true : false;
            return v5_1;
        }

        public void clear() {
            this.a.c();
        }

        public boolean contains(Object arg4) {
            if(!(arg4 instanceof Map$Entry)) {
                return 0;
            }

            int v0 = this.a.a(((Map$Entry)arg4).getKey());
            if(v0 < 0) {
                return 0;
            }

            return c.a(this.a.a(v0, 1), ((Map$Entry)arg4).getValue());
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

        public boolean equals(Object arg1) {
            return h.a(((Set)this), arg1);
        }

        public int hashCode() {
            int v0 = this.a.a() - 1;
            int v3 = 0;
            while(v0 >= 0) {
                Object v4 = this.a.a(v0, 0);
                Object v5 = this.a.a(v0, 1);
                int v4_1 = v4 == null ? 0 : v4.hashCode();
                int v5_1 = v5 == null ? 0 : v5.hashCode();
                v3 += v4_1 ^ v5_1;
                --v0;
            }

            return v3;
        }

        public boolean isEmpty() {
            boolean v0 = this.a.a() == 0 ? true : false;
            return v0;
        }

        public Iterator iterator() {
            return new d(this.a);
        }

        public boolean remove(Object arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.a.a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public Object[] toArray(Object[] arg1) {
            throw new UnsupportedOperationException();
        }
    }

    final class android.support.v4.f.h$c implements Set {
        android.support.v4.f.h$c(h arg1) {
            this.a = arg1;
            super();
        }

        public boolean add(Object arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.a.c();
        }

        public boolean contains(Object arg2) {
            boolean v2 = this.a.a(arg2) >= 0 ? true : false;
            return v2;
        }

        public boolean containsAll(Collection arg2) {
            return h.a(this.a.b(), arg2);
        }

        public boolean equals(Object arg1) {
            return h.a(((Set)this), arg1);
        }

        public int hashCode() {
            int v0 = this.a.a() - 1;
            int v2 = 0;
            while(v0 >= 0) {
                Object v3 = this.a.a(v0, 0);
                int v3_1 = v3 == null ? 0 : v3.hashCode();
                v2 += v3_1;
                --v0;
            }

            return v2;
        }

        public boolean isEmpty() {
            boolean v0 = this.a.a() == 0 ? true : false;
            return v0;
        }

        public Iterator iterator() {
            return new a(this.a, 0);
        }

        public boolean remove(Object arg2) {
            int v2 = this.a.a(arg2);
            if(v2 >= 0) {
                this.a.a(v2);
                return 1;
            }

            return 0;
        }

        public boolean removeAll(Collection arg2) {
            return h.b(this.a.b(), arg2);
        }

        public boolean retainAll(Collection arg2) {
            return h.c(this.a.b(), arg2);
        }

        public int size() {
            return this.a.a();
        }

        public Object[] toArray() {
            return this.a.b(0);
        }

        public Object[] toArray(Object[] arg3) {
            return this.a.a(arg3, 0);
        }
    }

    final class d implements Iterator, Map$Entry {
        int a;
        int b;
        boolean c;

        d(h arg2) {
            this.d = arg2;
            super();
            this.c = false;
            this.a = arg2.a() - 1;
            this.b = -1;
        }

        public Map$Entry a() {
            if(this.hasNext()) {
                ++this.b;
                this.c = true;
                return this;
            }

            throw new NoSuchElementException();
        }

        public boolean equals(Object arg5) {
            if(this.c) {
                boolean v1 = false;
                if(!(arg5 instanceof Map$Entry)) {
                    return 0;
                }

                if((c.a(((Map$Entry)arg5).getKey(), this.d.a(this.b, 0))) && (c.a(((Map$Entry)arg5).getValue(), this.d.a(this.b, 1)))) {
                    v1 = true;
                }

                return v1;
            }

            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Object getKey() {
            if(this.c) {
                return this.d.a(this.b, 0);
            }

            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Object getValue() {
            if(this.c) {
                return this.d.a(this.b, 1);
            }

            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            boolean v0 = this.b < this.a ? true : false;
            return v0;
        }

        public int hashCode() {
            if(this.c) {
                int v2 = 0;
                Object v0 = this.d.a(this.b, 0);
                Object v1 = this.d.a(this.b, 1);
                int v0_1 = v0 == null ? 0 : v0.hashCode();
                if(v1 == null) {
                }
                else {
                    v2 = v1.hashCode();
                }

                return v0_1 ^ v2;
            }

            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Object next() {
            return this.a();
        }

        public void remove() {
            if(this.c) {
                this.d.a(this.b);
                --this.b;
                --this.a;
                this.c = false;
                return;
            }

            throw new IllegalStateException();
        }

        public Object setValue(Object arg3) {
            if(this.c) {
                return this.d.a(this.b, arg3);
            }

            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }

    final class e implements Collection {
        e(h arg1) {
            this.a = arg1;
            super();
        }

        public boolean add(Object arg1) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection arg1) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.a.c();
        }

        public boolean contains(Object arg2) {
            boolean v2 = this.a.b(arg2) >= 0 ? true : false;
            return v2;
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

        public boolean isEmpty() {
            boolean v0 = this.a.a() == 0 ? true : false;
            return v0;
        }

        public Iterator iterator() {
            return new a(this.a, 1);
        }

        public boolean remove(Object arg2) {
            int v2 = this.a.b(arg2);
            if(v2 >= 0) {
                this.a.a(v2);
                return 1;
            }

            return 0;
        }

        public boolean removeAll(Collection arg6) {
            int v0 = this.a.a();
            int v1 = 0;
            boolean v2 = false;
            while(v1 < v0) {
                if(arg6.contains(this.a.a(v1, 1))) {
                    this.a.a(v1);
                    --v1;
                    --v0;
                    v2 = true;
                }

                ++v1;
            }

            return v2;
        }

        public boolean retainAll(Collection arg6) {
            int v0 = this.a.a();
            int v1 = 0;
            boolean v2 = false;
            while(v1 < v0) {
                if(!arg6.contains(this.a.a(v1, 1))) {
                    this.a.a(v1);
                    --v1;
                    --v0;
                    v2 = true;
                }

                ++v1;
            }

            return v2;
        }

        public int size() {
            return this.a.a();
        }

        public Object[] toArray() {
            return this.a.b(1);
        }

        public Object[] toArray(Object[] arg3) {
            return this.a.a(arg3, 1);
        }
    }

    b b;
    android.support.v4.f.h$c c;
    e d;

    h() {
        super();
    }

    public static boolean a(Map arg1, Collection arg2) {
        Iterator v2 = arg2.iterator();
        do {
            if(!v2.hasNext()) {
                return 1;
            }
        }
        while(arg1.containsKey(v2.next()));

        return 0;
    }

    public static boolean a(Set arg4, Object arg5) {
        boolean v0 = true;
        if(arg4 == (((Set)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Set)) {
            return 0;
        }

        try {
            if(arg4.size() != ((Set)arg5).size()) {
                return false;
            }
            else if(arg4.containsAll(((Collection)arg5))) {
            }
            else {
                return false;
            }
        }
        catch(ClassCastException ) {
            return 0;
        }

        return v0;
    }

    protected abstract int a();

    protected abstract int a(Object arg1);

    protected abstract Object a(int arg1, int arg2);

    protected abstract Object a(int arg1, Object arg2);

    protected abstract void a(int arg1);

    protected abstract void a(Object arg1, Object arg2);

    public Object[] a(Object[] arg4, int arg5) {
        Object v4;
        int v0 = this.a();
        if(arg4.length < v0) {
            v4 = Array.newInstance(arg4.getClass().getComponentType(), v0);
        }

        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            ((Object[])v4)[v1] = this.a(v1, arg5);
        }

        if(((Object[])v4).length > v0) {
            ((Object[])v4)[v0] = null;
        }

        return ((Object[])v4);
    }

    public static boolean b(Map arg2, Collection arg3) {
        int v0 = arg2.size();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            arg2.remove(v3.next());
        }

        boolean v2 = v0 != arg2.size() ? true : false;
        return v2;
    }

    protected abstract int b(Object arg1);

    protected abstract Map b();

    public Object[] b(int arg5) {
        int v0 = this.a();
        Object[] v1 = new Object[v0];
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1[v2] = this.a(v2, arg5);
        }

        return v1;
    }

    public static boolean c(Map arg3, Collection arg4) {
        int v0 = arg3.size();
        Iterator v1 = arg3.keySet().iterator();
        while(v1.hasNext()) {
            if(arg4.contains(v1.next())) {
                continue;
            }

            v1.remove();
        }

        boolean v3 = v0 != arg3.size() ? true : false;
        return v3;
    }

    protected abstract void c();

    public Set d() {
        if(this.b == null) {
            this.b = new b(this);
        }

        return this.b;
    }

    public Set e() {
        if(this.c == null) {
            this.c = new android.support.v4.f.h$c(this);
        }

        return this.c;
    }

    public Collection f() {
        if(this.d == null) {
            this.d = new e(this);
        }

        return this.d;
    }
}

