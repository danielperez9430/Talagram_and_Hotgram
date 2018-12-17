package f;

import e.e;
import e.r;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import okhttp3.ad;

final class o {
    final class a implements GenericArrayType {
        private final Type a;

        a(Type arg1) {
            super();
            this.a = arg1;
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof GenericArrayType) || !o.a(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type getGenericComponentType() {
            return this.a;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return o.b(this.a) + "[]";
        }
    }

    final class b implements ParameterizedType {
        private final Type a;
        private final Type b;
        private final Type[] c;

        b(Type arg5, Type arg6, Type[] arg7) {
            // Method was not decompiled
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof ParameterizedType) || !o.a(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type[] getActualTypeArguments() {
            // Method was not decompiled
        }

        public Type getOwnerType() {
            return this.a;
        }

        public Type getRawType() {
            return this.b;
        }

        public int hashCode() {
            int v0 = Arrays.hashCode(this.c) ^ this.b.hashCode();
            int v1 = this.a != null ? this.a.hashCode() : 0;
            return v0 ^ v1;
        }

        public String toString() {
            if(this.c.length == 0) {
                return o.b(this.b);
            }

            int v2 = 1;
            StringBuilder v0 = new StringBuilder((this.c.length + 1) * 30);
            v0.append(o.b(this.b));
            v0.append("<");
            v0.append(o.b(this.c[0]));
            while(v2 < this.c.length) {
                v0.append(", ");
                v0.append(o.b(this.c[v2]));
                ++v2;
            }

            v0.append(">");
            return v0.toString();
        }
    }

    final class c implements WildcardType {
        private final Type a;
        private final Type b;

        c(Type[] arg4, Type[] arg5) {
            Class v4;
            super();
            if(arg5.length > 1) {
                goto label_41;
            }

            if(arg4.length != 1) {
                goto label_38;
            }

            if(arg5.length == 1) {
                if(arg5[0] != null) {
                    o.c(arg5[0]);
                    if(arg4[0] == Object.class) {
                        this.b = arg5[0];
                        v4 = Object.class;
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                }
                else {
                    throw new NullPointerException();
                }
            }
            else if(arg4[0] != null) {
                o.c(arg4[0]);
                this.b = null;
                Type v4_1 = arg4[0];
            }
            else {
                goto label_35;
            }

            this.a = ((Type)v4);
            return;
        label_35:
            throw new NullPointerException();
        label_38:
            throw new IllegalArgumentException();
        label_41:
            throw new IllegalArgumentException();
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof WildcardType) || !o.a(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type[] getLowerBounds() {
            Type[] v0 = this.b != null ? new Type[]{this.b} : o.a;
            return v0;
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.a};
        }

        public int hashCode() {
            int v0 = this.b != null ? this.b.hashCode() + 31 : 1;
            return v0 ^ this.a.hashCode() + 31;
        }

        public String toString() {
            Type v1;
            StringBuilder v0;
            if(this.b != null) {
                v0 = new StringBuilder();
                v0.append("? super ");
                v1 = this.b;
            }
            else if(this.a == Object.class) {
                return "?";
            }
            else {
                v0 = new StringBuilder();
                v0.append("? extends ");
                v1 = this.a;
            }

            v0.append(o.b(v1));
            return v0.toString();
        }
    }

    static final Type[] a;

    static {
        o.a = new Type[0];
    }

    static Object a(@Nullable Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }

    static void a(Class arg1) {
        if(arg1.isInterface()) {
            if(arg1.getInterfaces().length <= 0) {
                return;
            }

            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }

        throw new IllegalArgumentException("API declarations must be interfaces.");
    }

    static Class a(Type arg3) {
        o.a(arg3, "type == null");
        if((arg3 instanceof Class)) {
            return arg3;
        }

        if((arg3 instanceof ParameterizedType)) {
            arg3 = ((ParameterizedType)arg3).getRawType();
            if((arg3 instanceof Class)) {
                return ((Class)arg3);
            }

            throw new IllegalArgumentException();
        }

        if((arg3 instanceof GenericArrayType)) {
            return Array.newInstance(o.a(((GenericArrayType)arg3).getGenericComponentType()), 0).getClass();
        }

        if((arg3 instanceof TypeVariable)) {
            return Object.class;
        }

        if((arg3 instanceof WildcardType)) {
            return o.a(((WildcardType)arg3).getUpperBounds()[0]);
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
        v1.append(arg3);
        v1.append("> is of type ");
        v1.append(arg3.getClass().getName());
        throw new IllegalArgumentException(v1.toString());
    }

    static Type a(int arg4, ParameterizedType arg5) {
        Type[] v0 = arg5.getActualTypeArguments();
        if(arg4 >= 0 && arg4 < v0.length) {
            Type v4 = v0[arg4];
            if((v4 instanceof WildcardType)) {
                v4 = ((WildcardType)v4).getUpperBounds()[0];
            }

            return v4;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("Index ");
        v2.append(arg4);
        v2.append(" not in range [0,");
        v2.append(v0.length);
        v2.append(") for ");
        v2.append(arg5);
        throw new IllegalArgumentException(v2.toString());
    }

    private static int a(Object[] arg2, Object arg3) {
        int v0;
        for(v0 = 0; v0 < arg2.length; ++v0) {
            if(arg3.equals(arg2[v0])) {
                return v0;
            }
        }

        throw new NoSuchElementException();
    }

    private static Class a(TypeVariable arg1) {
        Class v1_1;
        GenericDeclaration v1 = arg1.getGenericDeclaration();
        if((v1 instanceof Class)) {
        }
        else {
            v1_1 = null;
        }

        return v1_1;
    }

    static Type a(Type arg3, Class arg4, Class arg5) {
        if(arg5 == arg4) {
            return arg3;
        }

        if(arg5.isInterface()) {
            Class[] v3 = arg4.getInterfaces();
            int v0 = 0;
            int v1 = v3.length;
            while(v0 < v1) {
                if(v3[v0] == arg5) {
                    return arg4.getGenericInterfaces()[v0];
                }
                else if(arg5.isAssignableFrom(v3[v0])) {
                    return o.a(arg4.getGenericInterfaces()[v0], v3[v0], arg5);
                }
                else {
                    ++v0;
                    continue;
                }

                break;
            }
        }

        if(!arg4.isInterface()) {
            while(arg4 != Object.class) {
                Class v3_1 = arg4.getSuperclass();
                if(v3_1 == arg5) {
                    return arg4.getGenericSuperclass();
                }
                else if(arg5.isAssignableFrom(v3_1)) {
                    return o.a(arg4.getGenericSuperclass(), v3_1, arg5);
                }
                else {
                    arg4 = v3_1;
                    continue;
                }

                return arg5;
            }
        }

        return arg5;
    }

    static Type a(Type arg8, Class arg9, Type arg10) {
        // Method was not decompiled
    }

    private static Type a(Type arg1, Class arg2, TypeVariable arg3) {
        Class v0 = o.a(arg3);
        if(v0 == null) {
            return arg3;
        }

        arg1 = o.a(arg1, arg2, v0);
        if((arg1 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg1).getActualTypeArguments()[o.a(v0.getTypeParameters(), arg3)];
        }

        return arg3;
    }

    static ad a(ad arg4) {
        e.c v0 = new e.c();
        arg4.c().a(((r)v0));
        return ad.a(arg4.a(), arg4.b(), ((e)v0));
    }

    static void a(Throwable arg1) {
        if(!(arg1 instanceof VirtualMachineError)) {
            if(!(arg1 instanceof ThreadDeath)) {
                if(!(arg1 instanceof LinkageError)) {
                    return;
                }

                throw arg1;
            }

            throw arg1;
        }

        throw arg1;
    }

    static boolean a(Type arg4, Type arg5) {
        boolean v0 = true;
        if(arg4 == arg5) {
            return 1;
        }

        if((arg4 instanceof Class)) {
            return arg4.equals(arg5);
        }

        if((arg4 instanceof ParameterizedType)) {
            if(!(arg5 instanceof ParameterizedType)) {
                return 0;
            }

            Type v1 = ((ParameterizedType)arg4).getOwnerType();
            Type v3 = ((ParameterizedType)arg5).getOwnerType();
            if(v1 == v3 || (v1.equals(v3))) {
                if(!((ParameterizedType)arg4).getRawType().equals(((ParameterizedType)arg5).getRawType()) || !Arrays.equals(((ParameterizedType)arg4).getActualTypeArguments(), ((ParameterizedType)arg5).getActualTypeArguments())) {
                label_28:
                    v0 = false;
                }
                else {
                }
            }
            else if(v1 == null) {
                goto label_28;
            }
            else {
                goto label_28;
            }

            return v0;
        }

        if((arg4 instanceof GenericArrayType)) {
            if(!(arg5 instanceof GenericArrayType)) {
                return 0;
            }

            return o.a(((GenericArrayType)arg4).getGenericComponentType(), ((GenericArrayType)arg5).getGenericComponentType());
        }

        if((arg4 instanceof WildcardType)) {
            if(!(arg5 instanceof WildcardType)) {
                return 0;
            }

            if(!Arrays.equals(((WildcardType)arg4).getUpperBounds(), ((WildcardType)arg5).getUpperBounds()) || !Arrays.equals(((WildcardType)arg4).getLowerBounds(), ((WildcardType)arg5).getLowerBounds())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        if((arg4 instanceof TypeVariable)) {
            if(!(arg5 instanceof TypeVariable)) {
                return 0;
            }

            if(((TypeVariable)arg4).getGenericDeclaration() != ((TypeVariable)arg5).getGenericDeclaration() || !((TypeVariable)arg4).getName().equals(((TypeVariable)arg5).getName())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    static boolean a(Annotation[] arg4, Class arg5) {
        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg5.isInstance(arg4[v2])) {
                return 1;
            }
        }

        return 0;
    }

    static Type b(Type arg1, Class arg2, Class arg3) {
        if(arg3.isAssignableFrom(arg2)) {
            return o.a(arg1, arg2, o.a(arg1, arg2, arg3));
        }

        throw new IllegalArgumentException();
    }

    static String b(Type arg1) {
        String v1 = (arg1 instanceof Class) ? ((Class)arg1).getName() : arg1.toString();
        return v1;
    }

    static void c(Type arg1) {
        if((arg1 instanceof Class)) {
            if(!((Class)arg1).isPrimitive()) {
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    static boolean d(@Nullable Type arg5) {
        if((arg5 instanceof Class)) {
            return 0;
        }

        if((arg5 instanceof ParameterizedType)) {
            Type[] v5 = ((ParameterizedType)arg5).getActualTypeArguments();
            int v0 = v5.length;
            int v3;
            for(v3 = 0; v3 < v0; ++v3) {
                if(o.d(v5[v3])) {
                    return 1;
                }
            }

            return 0;
        }

        if((arg5 instanceof GenericArrayType)) {
            return o.d(((GenericArrayType)arg5).getGenericComponentType());
        }

        if((arg5 instanceof TypeVariable)) {
            return 1;
        }

        if((arg5 instanceof WildcardType)) {
            return 1;
        }

        String v0_1 = arg5 == null ? "null" : arg5.getClass().getName();
        StringBuilder v2 = new StringBuilder();
        v2.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
        v2.append(arg5);
        v2.append("> is of type ");
        v2.append(v0_1);
        throw new IllegalArgumentException(v2.toString());
    }

    static Type e(Type arg1) {
        if((arg1 instanceof ParameterizedType)) {
            return o.a(0, ((ParameterizedType)arg1));
        }

        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}

