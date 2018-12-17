package com.google.a.b;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class b {
    final class a implements Serializable, GenericArrayType {
        private final Type a;

        public a(Type arg1) {
            super();
            this.a = b.d(arg1);
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof GenericArrayType) || !b.a(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type getGenericComponentType() {
            return this.a;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return b.f(this.a) + "[]";
        }
    }

    final class com.google.a.b.b$b implements Serializable, ParameterizedType {
        private final Type a;
        private final Type b;
        private final Type[] c;

        public com.google.a.b.b$b(Type arg5, Type arg6, Type[] arg7) {
            // Method was not decompiled
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof ParameterizedType) || !b.a(((Type)this), ((Type)arg2)) ? false : true;
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
            return Arrays.hashCode(this.c) ^ this.b.hashCode() ^ b.a(this.a);
        }

        public String toString() {
            int v0 = this.c.length;
            if(v0 == 0) {
                return b.f(this.b);
            }

            StringBuilder v1 = new StringBuilder((v0 + 1) * 30);
            v1.append(b.f(this.b));
            v1.append("<");
            v1.append(b.f(this.c[0]));
            int v2;
            for(v2 = 1; v2 < v0; ++v2) {
                v1.append(", ");
                v1.append(b.f(this.c[v2]));
            }

            v1.append(">");
            return v1.toString();
        }
    }

    final class c implements Serializable, WildcardType {
        private final Type a;
        private final Type b;

        public c(Type[] arg4, Type[] arg5) {
            Class v4;
            super();
            boolean v1 = true;
            boolean v0 = arg5.length <= 1 ? true : false;
            com.google.a.b.a.a(v0);
            v0 = arg4.length == 1 ? true : false;
            com.google.a.b.a.a(v0);
            if(arg5.length == 1) {
                com.google.a.b.a.a(arg5[0]);
                b.h(arg5[0]);
                if(arg4[0] == Object.class) {
                }
                else {
                    v1 = false;
                }

                com.google.a.b.a.a(v1);
                this.b = b.d(arg5[0]);
                v4 = Object.class;
            }
            else {
                com.google.a.b.a.a(arg4[0]);
                b.h(arg4[0]);
                this.b = null;
                Type v4_1 = b.d(arg4[0]);
            }

            this.a = ((Type)v4);
        }

        public boolean equals(Object arg2) {
            boolean v2 = !(arg2 instanceof WildcardType) || !b.a(((Type)this), ((Type)arg2)) ? false : true;
            return v2;
        }

        public Type[] getLowerBounds() {
            Type[] v0 = this.b != null ? new Type[]{this.b} : b.a;
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

            v0.append(b.f(v1));
            return v0.toString();
        }
    }

    static final Type[] a;

    static {
        b.a = new Type[0];
    }

    static int a(Object arg0) {
        int v0 = arg0 != null ? arg0.hashCode() : 0;
        return v0;
    }

    private static int a(Object[] arg3, Object arg4) {
        int v0 = arg3.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.equals(arg3[v1])) {
                return v1;
            }
        }

        throw new NoSuchElementException();
    }

    private static Class a(TypeVariable arg1) {
        GenericDeclaration v1 = arg1.getGenericDeclaration();
        if((v1 instanceof Class)) {
        }
        else {
            Class v1_1 = null;
        }

        return ((Class)v1);
    }

    public static GenericArrayType a(Type arg1) {
        return new a(arg1);
    }

    public static ParameterizedType a(Type arg1, Type arg2, Type[] arg3) {
        return new com.google.a.b.b$b(arg1, arg2, arg3);
    }

    public static Type a(Type arg1, Class arg2) {
        arg1 = b.b(arg1, arg2, Collection.class);
        if((arg1 instanceof WildcardType)) {
            arg1 = ((WildcardType)arg1).getUpperBounds()[0];
        }

        if((arg1 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg1).getActualTypeArguments()[0];
        }

        return Object.class;
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
                    return b.a(arg4.getGenericInterfaces()[v0], v3[v0], arg5);
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
                    return b.a(arg4.getGenericSuperclass(), v3_1, arg5);
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

    public static Type a(Type arg1, Class arg2, Type arg3) {
        return b.a(arg1, arg2, arg3, new HashSet());
    }

    private static Type a(Type arg8, Class arg9, Type arg10, Collection arg11) {
        // Method was not decompiled
    }

    static Type a(Type arg1, Class arg2, TypeVariable arg3) {
        Class v0 = b.a(arg3);
        if(v0 == null) {
            return arg3;
        }

        arg1 = b.a(arg1, arg2, v0);
        if((arg1 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg1).getActualTypeArguments()[b.a(v0.getTypeParameters(), arg3)];
        }

        return arg3;
    }

    static boolean a(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }

    public static boolean a(Type arg4, Type arg5) {
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

            if(!b.a(((ParameterizedType)arg4).getOwnerType(), ((ParameterizedType)arg5).getOwnerType()) || !((ParameterizedType)arg4).getRawType().equals(((ParameterizedType)arg5).getRawType()) || !Arrays.equals(((ParameterizedType)arg4).getActualTypeArguments(), ((ParameterizedType)arg5).getActualTypeArguments())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        if((arg4 instanceof GenericArrayType)) {
            if(!(arg5 instanceof GenericArrayType)) {
                return 0;
            }

            return b.a(((GenericArrayType)arg4).getGenericComponentType(), ((GenericArrayType)arg5).getGenericComponentType());
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

    static Type b(Type arg1, Class arg2, Class arg3) {
        com.google.a.b.a.a(arg3.isAssignableFrom(arg2));
        return b.a(arg1, arg2, b.a(arg1, arg2, arg3));
    }

    public static WildcardType b(Type arg2) {
        Type[] v2 = (arg2 instanceof WildcardType) ? ((WildcardType)arg2).getUpperBounds() : new Type[]{arg2};
        return new c(v2, b.a);
    }

    public static Type[] b(Type arg4, Class arg5) {
        Type[] v4;
        int v3 = 2;
        if(arg4 == Properties.class) {
            v4 = new Type[v3];
            v4[0] = String.class;
            v4[1] = String.class;
            return v4;
        }

        arg4 = b.b(arg4, arg5, Map.class);
        if((arg4 instanceof ParameterizedType)) {
            return ((ParameterizedType)arg4).getActualTypeArguments();
        }

        v4 = new Type[v3];
        v4[0] = Object.class;
        v4[1] = Object.class;
        return v4;
    }

    public static WildcardType c(Type arg4) {
        Type[] v4 = (arg4 instanceof WildcardType) ? ((WildcardType)arg4).getLowerBounds() : new Type[]{arg4};
        return new c(new Type[]{Object.class}, v4);
    }

    public static Type d(Type arg3) {
        a v3;
        if((arg3 instanceof Class)) {
            if(((Class)arg3).isArray()) {
                v3 = new a(b.d(((Class)arg3).getComponentType()));
            }

            return ((Type)v3);
        }

        if((arg3 instanceof ParameterizedType)) {
            return new com.google.a.b.b$b(((ParameterizedType)arg3).getOwnerType(), ((ParameterizedType)arg3).getRawType(), ((ParameterizedType)arg3).getActualTypeArguments());
        }

        if((arg3 instanceof GenericArrayType)) {
            return new a(((GenericArrayType)arg3).getGenericComponentType());
        }

        if((arg3 instanceof WildcardType)) {
            return new c(((WildcardType)arg3).getUpperBounds(), ((WildcardType)arg3).getLowerBounds());
        }

        return arg3;
    }

    public static Class e(Type arg4) {
        if((arg4 instanceof Class)) {
            return arg4;
        }

        if((arg4 instanceof ParameterizedType)) {
            arg4 = ((ParameterizedType)arg4).getRawType();
            com.google.a.b.a.a(arg4 instanceof Class);
            return ((Class)arg4);
        }

        if((arg4 instanceof GenericArrayType)) {
            return Array.newInstance(b.e(((GenericArrayType)arg4).getGenericComponentType()), 0).getClass();
        }

        if((arg4 instanceof TypeVariable)) {
            return Object.class;
        }

        if((arg4 instanceof WildcardType)) {
            return b.e(((WildcardType)arg4).getUpperBounds()[0]);
        }

        String v0 = arg4 == null ? "null" : arg4.getClass().getName();
        StringBuilder v2 = new StringBuilder();
        v2.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
        v2.append(arg4);
        v2.append("> is of type ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }

    public static String f(Type arg1) {
        String v1 = (arg1 instanceof Class) ? ((Class)arg1).getName() : arg1.toString();
        return v1;
    }

    public static Type g(Type arg1) {
        Class v1;
        if((arg1 instanceof GenericArrayType)) {
            arg1 = ((GenericArrayType)arg1).getGenericComponentType();
        }
        else {
            v1 = ((Class)arg1).getComponentType();
        }

        return ((Type)v1);
    }

    static void h(Type arg1) {
        boolean v1 = !(arg1 instanceof Class) || !((Class)arg1).isPrimitive() ? true : false;
        com.google.a.b.a.a(v1);
    }
}

