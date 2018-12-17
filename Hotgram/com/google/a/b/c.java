package com.google.a.b;

import com.google.a.c.a;
import com.google.a.m;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class c {
    private final Map a;

    public c(Map arg1) {
        super();
        this.a = arg1;
    }

    private h a(Class arg2) {
        try {
            Constructor v2 = arg2.getDeclaredConstructor();
            if(!v2.isAccessible()) {
                v2.setAccessible(true);
            }

            return new h(v2) {
                public Object a() {
                    StringBuilder v2;
                    Object[] v0 = null;
                    try {
                        return this.a.newInstance(v0);
                    }
                    catch(IllegalAccessException v0_1) {
                        throw new AssertionError(v0_1);
                    }
                    catch(InvocationTargetException v0_2) {
                        v2 = new StringBuilder();
                        v2.append("Failed to invoke ");
                        v2.append(this.a);
                        v2.append(" with no args");
                        throw new RuntimeException(v2.toString(), v0_2.getTargetException());
                    }
                    catch(InstantiationException v0_3) {
                        v2 = new StringBuilder();
                        v2.append("Failed to invoke ");
                        v2.append(this.a);
                        v2.append(" with no args");
                        throw new RuntimeException(v2.toString(), ((Throwable)v0_3));
                    }
                }
            };
        }
        catch(NoSuchMethodException ) {
            return null;
        }
    }

    private h a(Type arg2, Class arg3) {
        if(Collection.class.isAssignableFrom(arg3)) {
            if(SortedSet.class.isAssignableFrom(arg3)) {
                return new h() {
                    public Object a() {
                        return new TreeSet();
                    }
                };
            }

            if(EnumSet.class.isAssignableFrom(arg3)) {
                return new h(arg2) {
                    public Object a() {
                        StringBuilder v1;
                        if((this.a instanceof ParameterizedType)) {
                            Type v0 = this.a.getActualTypeArguments()[0];
                            if((v0 instanceof Class)) {
                                return EnumSet.noneOf(((Class)v0));
                            }

                            v1 = new StringBuilder();
                            v1.append("Invalid EnumSet type: ");
                            v1.append(this.a.toString());
                            throw new m(v1.toString());
                        }

                        v1 = new StringBuilder();
                        v1.append("Invalid EnumSet type: ");
                        v1.append(this.a.toString());
                        throw new m(v1.toString());
                    }
                };
            }

            if(Set.class.isAssignableFrom(arg3)) {
                return new h() {
                    public Object a() {
                        return new LinkedHashSet();
                    }
                };
            }

            if(Queue.class.isAssignableFrom(arg3)) {
                return new h() {
                    public Object a() {
                        return new ArrayDeque();
                    }
                };
            }

            return new h() {
                public Object a() {
                    return new ArrayList();
                }
            };
        }

        if(Map.class.isAssignableFrom(arg3)) {
            if(ConcurrentNavigableMap.class.isAssignableFrom(arg3)) {
                return new h() {
                    public Object a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }

            if(ConcurrentMap.class.isAssignableFrom(arg3)) {
                return new h() {
                    public Object a() {
                        return new ConcurrentHashMap();
                    }
                };
            }

            if(SortedMap.class.isAssignableFrom(arg3)) {
                return new h() {
                    public Object a() {
                        return new TreeMap();
                    }
                };
            }

            if(((arg2 instanceof ParameterizedType)) && !String.class.isAssignableFrom(a.a(((ParameterizedType)arg2).getActualTypeArguments()[0]).a())) {
                return new h() {
                    public Object a() {
                        return new LinkedHashMap();
                    }
                };
            }

            return new h() {
                public Object a() {
                    return new g();
                }
            };
        }

        return null;
    }

    public h a(a arg3) {
        Type v0 = arg3.b();
        Class v3 = arg3.a();
        Object v1 = this.a.get(v0);
        if(v1 != null) {
            return new h(((com.google.a.h)v1), v0) {
                public Object a() {
                    return this.a.a(this.b);
                }
            };
        }

        v1 = this.a.get(v3);
        if(v1 != null) {
            return new h(((com.google.a.h)v1), v0) {
                public Object a() {
                    return this.a.a(this.b);
                }
            };
        }

        h v1_1 = this.a(v3);
        if(v1_1 != null) {
            return v1_1;
        }

        v1_1 = this.a(v0, v3);
        if(v1_1 != null) {
            return v1_1;
        }

        return this.b(v0, v3);
    }

    private h b(Type arg2, Class arg3) {
        return new h(arg3, arg2) {
            private final k d;

            public Object a() {
                try {
                    return this.d.a(this.a);
                }
                catch(Exception v0) {
                    StringBuilder v2 = new StringBuilder();
                    v2.append("Unable to invoke no-args constructor for ");
                    v2.append(this.b);
                    v2.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(v2.toString(), ((Throwable)v0));
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}

