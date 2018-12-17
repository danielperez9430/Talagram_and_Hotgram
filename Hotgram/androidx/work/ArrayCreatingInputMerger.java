package androidx.work;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public final class ArrayCreatingInputMerger extends i {
    public ArrayCreatingInputMerger() {
        super();
    }

    private Object a(Object arg3) {
        Object v0 = Array.newInstance(arg3.getClass(), 1);
        Array.set(v0, 0, arg3);
        return v0;
    }

    private Object a(Object arg5, Object arg6) {
        int v0 = Array.getLength(arg5);
        int v1 = Array.getLength(arg6);
        Object v2 = Array.newInstance(arg5.getClass().getComponentType(), v0 + v1);
        System.arraycopy(arg5, 0, v2, 0, v0);
        System.arraycopy(arg6, 0, v2, v0, v1);
        return v2;
    }

    public e a(List arg10) {
        a v0 = new a();
        HashMap v1 = new HashMap();
        Iterator v10 = arg10.iterator();
    label_5:
        if(v10.hasNext()) {
            Iterator v2 = v10.next().a().entrySet().iterator();
            while(true) {
                if(!v2.hasNext()) {
                    goto label_5;
                }

                Object v3 = v2.next();
                Object v4 = ((Map$Entry)v3).getKey();
                v3 = ((Map$Entry)v3).getValue();
                Class v5 = v3.getClass();
                if(((Map)v1).containsKey(v4)) {
                    Object v6 = ((Map)v1).get(v4);
                    Class v7 = v6.getClass();
                    if(!v7.equals(v5)) {
                        if((v7.isArray()) && (v7.getComponentType().equals(v5))) {
                            v3 = this.c(v6, v3);
                            goto label_47;
                        }

                        if((v5.isArray()) && (v5.getComponentType().equals(v7))) {
                            v3 = this.c(v3, v6);
                            goto label_47;
                        }

                        break;
                    }
                    else if(v7.isArray()) {
                        v3 = this.a(v6, v3);
                    }
                    else {
                        v3 = this.b(v6, v3);
                    }
                }
                else if(v5.isArray()) {
                }
                else {
                    v3 = this.a(v3);
                }

            label_47:
                ((Map)v1).put(v4, v3);
            }

            throw new IllegalArgumentException();
        }

        v0.a(((Map)v1));
        return v0.a();
    }

    private Object b(Object arg3, Object arg4) {
        Object v0 = Array.newInstance(arg3.getClass(), 2);
        Array.set(v0, 0, arg3);
        Array.set(v0, 1, arg4);
        return v0;
    }

    private Object c(Object arg4, Object arg5) {
        int v0 = Array.getLength(arg4);
        Object v1 = Array.newInstance(arg5.getClass(), v0 + 1);
        System.arraycopy(arg4, 0, v1, 0, v0);
        Array.set(v1, v0, arg5);
        return v1;
    }
}

