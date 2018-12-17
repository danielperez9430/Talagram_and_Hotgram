package com.google.ads.interactivemedia.v3.internal;

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

public final class gy {
    private final Map a;

    public gy(Map arg1) {
        super();
        this.a = arg1;
    }

    private hd a(Class arg2) {
        try {
            Constructor v2 = arg2.getDeclaredConstructor();
            if(!v2.isAccessible()) {
                v2.setAccessible(true);
            }

            return new hd(v2) {
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

    private hd a(Type arg2, Class arg3) {
        if(Collection.class.isAssignableFrom(arg3)) {
            if(SortedSet.class.isAssignableFrom(arg3)) {
                return new hd() {
                    public Object a() {
                        return new TreeSet();
                    }
                };
            }

            if(EnumSet.class.isAssignableFrom(arg3)) {
                return new hd(arg2) {
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
                            throw new gg(v1.toString());
                        }

                        v1 = new StringBuilder();
                        v1.append("Invalid EnumSet type: ");
                        v1.append(this.a.toString());
                        throw new gg(v1.toString());
                    }
                };
            }

            if(Set.class.isAssignableFrom(arg3)) {
                return new hd() {
                    public Object a() {
                        return new LinkedHashSet();
                    }
                };
            }

            if(Queue.class.isAssignableFrom(arg3)) {
                return new hd() {
                    public Object a() {
                        return new ArrayDeque();
                    }
                };
            }

            return new hd() {
                public Object a() {
                    return new ArrayList();
                }
            };
        }

        if(Map.class.isAssignableFrom(arg3)) {
            if(ConcurrentNavigableMap.class.isAssignableFrom(arg3)) {
                return new hd() {
                    public Object a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }

            if(ConcurrentMap.class.isAssignableFrom(arg3)) {
                return new hd() {
                    public Object a() {
                        return new ConcurrentHashMap();
                    }
                };
            }

            if(SortedMap.class.isAssignableFrom(arg3)) {
                return new hd() {
                    public Object a() {
                        return new TreeMap();
                    }
                };
            }

            if(((arg2 instanceof ParameterizedType)) && !String.class.isAssignableFrom(hw.a(((ParameterizedType)arg2).getActualTypeArguments()[0]).a())) {
                return new hd() {
                    public Object a() {
                        return new LinkedHashMap();
                    }
                };
            }

            return new hd() {
                public Object a() {
                    return new hc();
                }
            };
        }

        return null;
    }

    public hd a(hw arg3) {
        Type v0 = arg3.b();
        Class v3 = arg3.a();
        Object v1 = this.a.get(v0);
        if(v1 != null) {
            return new hd(((gb)v1), v0) {
                public Object a() {
                    return this.a.a(this.b);
                }
            };
        }

        v1 = this.a.get(v3);
        if(v1 != null) {
            return new hd(((gb)v1), v0) {
                public Object a() {
                    return this.a.a(this.b);
                }
            };
        }

        hd v1_1 = this.a(v3);
        if(v1_1 != null) {
            return v1_1;
        }

        v1_1 = this.a(v0, v3);
        if(v1_1 != null) {
            return v1_1;
        }

        return this.b(v0, v3);
    }

    private hd b(Type arg2, Class arg3) {
        return new hd(arg3, arg2) {
            private final hg d;

            public Object a() {
                try {
                    return this.d.a(this.a);
                }
                catch(Exception v0) {
                    StringBuilder v2 = new StringBuilder();
                    v2.append("Unable to invoke no-args constructor for ");
                    v2.append(this.b);
                    v2.append(". ");
                    v2.append("Register an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(v2.toString(), ((Throwable)v0));
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}

