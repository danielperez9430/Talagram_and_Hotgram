package f;

import f.b.p;
import f.b.q;
import f.b.r;
import f.b.u;
import f.b.x;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.ac;
import okhttp3.ad;
import okhttp3.s;
import okhttp3.t;
import okhttp3.v;

final class n {
    final class a {
        final m a;
        final Method b;
        final Annotation[] c;
        final Annotation[][] d;
        final Type[] e;
        Type f;
        boolean g;
        boolean h;
        boolean i;
        boolean j;
        boolean k;
        boolean l;
        String m;
        boolean n;
        boolean o;
        boolean p;
        String q;
        s r;
        v s;
        Set t;
        i[] u;
        e v;
        c w;

        a(m arg1, Method arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg2.getAnnotations();
            this.e = arg2.getGenericParameterTypes();
            this.d = arg2.getParameterAnnotations();
        }

        public n a() {
            Type v4;
            this.w = this.b();
            this.f = this.w.a();
            if(this.f != l.class && this.f != ac.class) {
                this.v = this.c();
                Annotation[] v0 = this.c;
                int v1 = v0.length;
                int v3;
                for(v3 = 0; v3 < v1; ++v3) {
                    this.a(v0[v3]);
                }

                if(this.m != null) {
                    if(!this.n) {
                        if(this.p) {
                            throw this.a("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                        }
                        else if(!this.o) {
                        }
                        else {
                            throw this.a("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                        }
                    }

                    int v0_1 = this.d.length;
                    this.u = new i[v0_1];
                    v1 = 0;
                    goto label_44;
                }
                else {
                    throw this.a("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
                    while(true) {
                    label_44:
                        if(v1 >= v0_1) {
                            goto label_67;
                        }

                        v4 = this.e[v1];
                        if(o.d(v4)) {
                            goto label_62;
                        }

                        Annotation[] v3_1 = this.d[v1];
                        if(v3_1 == null) {
                            break;
                        }

                        this.u[v1] = this.a(v1, v4, v3_1);
                        ++v1;
                    }

                    throw this.a(v1, "No Retrofit annotation found.", new Object[0]);
                label_62:
                    throw this.a(v1, "Parameter type must not include a type variable or wildcard: %s", new Object[]{v4});
                label_67:
                    if(this.q == null) {
                        if(this.l) {
                        }
                        else {
                            throw this.a("Missing either @%s URL or @Url parameter.", new Object[]{this.m});
                        }
                    }

                    if(!this.o && !this.p && !this.n) {
                        if(!this.i) {
                        }
                        else {
                            throw this.a("Non-body HTTP method cannot contain @Body.", new Object[0]);
                        }
                    }

                    if(this.o) {
                        if(this.g) {
                        }
                        else {
                            throw this.a("Form-encoded method must contain at least one @Field.", new Object[0]);
                        }
                    }

                    if(this.p) {
                        if(this.h) {
                        }
                        else {
                            throw this.a("Multipart method must contain at least one @Part.", new Object[0]);
                        }
                    }

                    return new n(this);
                }
            }

            StringBuilder v0_2 = new StringBuilder();
            v0_2.append("\'");
            v0_2.append(o.a(this.f).getName());
            v0_2.append("\' is not a valid response body type. Did you mean ResponseBody?");
            throw this.a(v0_2.toString(), new Object[0]);
        }

        private i a(int arg6, Type arg7, Annotation[] arg8) {
            int v0 = arg8.length;
            i v3 = null;
            int v2;
            for(v2 = 0; true; ++v2) {
                if(v2 >= v0) {
                    goto label_18;
                }

                i v4 = this.a(arg6, arg7, arg8, arg8[v2]);
                if(v4 == null) {
                }
                else if(v3 == null) {
                    v3 = v4;
                }
                else {
                    break;
                }
            }

            throw this.a(arg6, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
        label_18:
            if(v3 != null) {
                return v3;
            }

            throw this.a(arg6, "No Retrofit annotation found.", new Object[0]);
        }

        private i a(int arg8, Type arg9, Annotation[] arg10, Annotation arg11) {
            e v10_1;
            Type v0_2;
            Class v0_1;
            StringBuilder v9;
            Class v3;
            boolean v11;
            String v0;
            if((arg11 instanceof x)) {
                if(!this.l) {
                    if(!this.j) {
                        if(!this.k) {
                            if(this.q == null) {
                                this.l = true;
                                if(arg9 != t.class && arg9 != String.class && arg9 != URI.class && (!(arg9 instanceof Class) || !"android.net.Uri".equals(((Class)arg9).getName()))) {
                                    throw this.a(arg8, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                                }

                                return new f.i$m();
                            }

                            throw this.a(arg8, "@Url cannot be used with @%s URL", new Object[]{this.m});
                        }

                        throw this.a(arg8, "A @Url parameter must not come after a @Query", new Object[0]);
                    }

                    throw this.a(arg8, "@Path parameters may not be used with @Url.", new Object[0]);
                }

                throw this.a(arg8, "Multiple @Url method annotations found.", new Object[0]);
            }

            if((arg11 instanceof f.b.s)) {
                if(!this.k) {
                    if(!this.l) {
                        if(this.q != null) {
                            this.j = true;
                            v0 = ((f.b.s)arg11).a();
                            this.a(arg8, v0);
                            return new h(v0, this.a.c(arg9, arg10), ((f.b.s)arg11).b());
                        }

                        throw this.a(arg8, "@Path can only be used with relative url on @%s", new Object[]{this.m});
                    }

                    throw this.a(arg8, "@Path parameters may not be used with @Url.", new Object[0]);
                }

                throw this.a(arg8, "A @Path parameter must not come after a @Query.", new Object[0]);
            }

            if((arg11 instanceof f.b.t)) {
                v0 = ((f.b.t)arg11).a();
                v11 = ((f.b.t)arg11).b();
                v3 = o.a(arg9);
                this.k = true;
                if(Iterable.class.isAssignableFrom(v3)) {
                    if((arg9 instanceof ParameterizedType)) {
                        return new f.i$i(v0, this.a.c(o.a(0, ((ParameterizedType)arg9)), arg10), v11).a();
                    }

                    v9 = new StringBuilder();
                    v9.append(v3.getSimpleName());
                    v9.append(" must include generic type (e.g., ");
                    v9.append(v3.getSimpleName());
                    v9.append("<String>)");
                    throw this.a(arg8, v9.toString(), new Object[0]);
                }

                if(v3.isArray()) {
                    return new f.i$i(v0, this.a.c(n.a(v3.getComponentType()), arg10), v11).b();
                }

                return new f.i$i(v0, this.a.c(arg9, arg10), v11);
            }

            if((arg11 instanceof f.b.v)) {
                v11 = ((f.b.v)arg11).a();
                v0_1 = o.a(arg9);
                this.k = true;
                if(Iterable.class.isAssignableFrom(v0_1)) {
                    if((arg9 instanceof ParameterizedType)) {
                        return new k(this.a.c(o.a(0, ((ParameterizedType)arg9)), arg10), v11).a();
                    }

                    v9 = new StringBuilder();
                    v9.append(v0_1.getSimpleName());
                    v9.append(" must include generic type (e.g., ");
                    v9.append(v0_1.getSimpleName());
                    v9.append("<String>)");
                    throw this.a(arg8, v9.toString(), new Object[0]);
                }

                if(v0_1.isArray()) {
                    return new k(this.a.c(n.a(v0_1.getComponentType()), arg10), v11).b();
                }

                return new k(this.a.c(arg9, arg10), v11);
            }

            if((arg11 instanceof u)) {
                v0_1 = o.a(arg9);
                if(Map.class.isAssignableFrom(v0_1)) {
                    arg9 = o.b(arg9, v0_1, Map.class);
                    if((arg9 instanceof ParameterizedType)) {
                        v0_2 = o.a(0, ((ParameterizedType)arg9));
                        if(String.class == v0_2) {
                            return new j(this.a.c(o.a(1, ((ParameterizedType)arg9)), arg10), ((u)arg11).a());
                        }

                        v9 = new StringBuilder();
                        v9.append("@QueryMap keys must be of type String: ");
                        v9.append(v0_2);
                        throw this.a(arg8, v9.toString(), new Object[0]);
                    }

                    throw this.a(arg8, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }

                throw this.a(arg8, "@QueryMap parameter type must be Map.", new Object[0]);
            }

            if((arg11 instanceof f.b.i)) {
                String v11_1 = ((f.b.i)arg11).a();
                v0_1 = o.a(arg9);
                if(Iterable.class.isAssignableFrom(v0_1)) {
                    if((arg9 instanceof ParameterizedType)) {
                        return new d(v11_1, this.a.c(o.a(0, ((ParameterizedType)arg9)), arg10)).a();
                    }

                    v9 = new StringBuilder();
                    v9.append(v0_1.getSimpleName());
                    v9.append(" must include generic type (e.g., ");
                    v9.append(v0_1.getSimpleName());
                    v9.append("<String>)");
                    throw this.a(arg8, v9.toString(), new Object[0]);
                }

                if(v0_1.isArray()) {
                    return new d(v11_1, this.a.c(n.a(v0_1.getComponentType()), arg10)).b();
                }

                return new d(v11_1, this.a.c(arg9, arg10));
            }

            if((arg11 instanceof f.b.j)) {
                Class v11_2 = o.a(arg9);
                if(Map.class.isAssignableFrom(v11_2)) {
                    arg9 = o.b(arg9, v11_2, Map.class);
                    if((arg9 instanceof ParameterizedType)) {
                        Type v11_3 = o.a(0, ((ParameterizedType)arg9));
                        if(String.class == v11_3) {
                            return new f.i$e(this.a.c(o.a(1, ((ParameterizedType)arg9)), arg10));
                        }

                        v9 = new StringBuilder();
                        v9.append("@HeaderMap keys must be of type String: ");
                        v9.append(v11_3);
                        throw this.a(arg8, v9.toString(), new Object[0]);
                    }

                    throw this.a(arg8, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }

                throw this.a(arg8, "@HeaderMap parameter type must be Map.", new Object[0]);
            }

            if((arg11 instanceof f.b.c)) {
                if(this.o) {
                    v0 = ((f.b.c)arg11).a();
                    v11 = ((f.b.c)arg11).b();
                    this.g = true;
                    Class v1 = o.a(arg9);
                    if(Iterable.class.isAssignableFrom(v1)) {
                        if((arg9 instanceof ParameterizedType)) {
                            return new b(v0, this.a.c(o.a(0, ((ParameterizedType)arg9)), arg10), v11).a();
                        }

                        v9 = new StringBuilder();
                        v9.append(v1.getSimpleName());
                        v9.append(" must include generic type (e.g., ");
                        v9.append(v1.getSimpleName());
                        v9.append("<String>)");
                        throw this.a(arg8, v9.toString(), new Object[0]);
                    }

                    if(v1.isArray()) {
                        return new b(v0, this.a.c(n.a(v1.getComponentType()), arg10), v11).b();
                    }

                    return new b(v0, this.a.c(arg9, arg10), v11);
                }

                throw this.a(arg8, "@Field parameters can only be used with form encoding.", new Object[0]);
            }

            if((arg11 instanceof f.b.d)) {
                if(this.o) {
                    v0_1 = o.a(arg9);
                    if(Map.class.isAssignableFrom(v0_1)) {
                        arg9 = o.b(arg9, v0_1, Map.class);
                        if((arg9 instanceof ParameterizedType)) {
                            v0_2 = o.a(0, ((ParameterizedType)arg9));
                            if(String.class == v0_2) {
                                e v8 = this.a.c(o.a(1, ((ParameterizedType)arg9)), arg10);
                                this.g = true;
                                return new f.i$c(v8, ((f.b.d)arg11).a());
                            }

                            v9 = new StringBuilder();
                            v9.append("@FieldMap keys must be of type String: ");
                            v9.append(v0_2);
                            throw this.a(arg8, v9.toString(), new Object[0]);
                        }

                        throw this.a(arg8, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }

                    throw this.a(arg8, "@FieldMap parameter type must be Map.", new Object[0]);
                }

                throw this.a(arg8, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            }

            if((arg11 instanceof q)) {
                if(this.p) {
                    this.h = true;
                    v0 = ((q)arg11).a();
                    v3 = o.a(arg9);
                    if(v0.isEmpty()) {
                        if(Iterable.class.isAssignableFrom(v3)) {
                            if((arg9 instanceof ParameterizedType)) {
                                if(okhttp3.w$b.class.isAssignableFrom(o.a(o.a(0, ((ParameterizedType)arg9))))) {
                                    return f.i$l.a.a();
                                }

                                throw this.a(arg8, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                            }

                            v9 = new StringBuilder();
                            v9.append(v3.getSimpleName());
                            v9.append(" must include generic type (e.g., ");
                            v9.append(v3.getSimpleName());
                            v9.append("<String>)");
                            throw this.a(arg8, v9.toString(), new Object[0]);
                        }

                        if(v3.isArray()) {
                            if(okhttp3.w$b.class.isAssignableFrom(v3.getComponentType())) {
                                return f.i$l.a.b();
                            }

                            throw this.a(arg8, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }

                        if(okhttp3.w$b.class.isAssignableFrom(v3)) {
                            return f.i$l.a;
                        }

                        throw this.a(arg8, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }

                    String[] v4 = new String[4];
                    v4[0] = "Content-Disposition";
                    v4[1] = "form-data; name=\"" + v0 + "\"";
                    v4[2] = "Content-Transfer-Encoding";
                    v4[3] = ((q)arg11).b();
                    s v11_4 = s.a(v4);
                    if(Iterable.class.isAssignableFrom(v3)) {
                        if((arg9 instanceof ParameterizedType)) {
                            arg9 = o.a(0, ((ParameterizedType)arg9));
                            if(!okhttp3.w$b.class.isAssignableFrom(o.a(arg9))) {
                                return new f(v11_4, this.a.a(arg9, arg10, this.c)).a();
                            }

                            throw this.a(arg8, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }

                        v9 = new StringBuilder();
                        v9.append(v3.getSimpleName());
                        v9.append(" must include generic type (e.g., ");
                        v9.append(v3.getSimpleName());
                        v9.append("<String>)");
                        throw this.a(arg8, v9.toString(), new Object[0]);
                    }

                    if(v3.isArray()) {
                        Class v9_1 = n.a(v3.getComponentType());
                        if(!okhttp3.w$b.class.isAssignableFrom(v9_1)) {
                            return new f(v11_4, this.a.a(((Type)v9_1), arg10, this.c)).b();
                        }

                        throw this.a(arg8, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }

                    if(!okhttp3.w$b.class.isAssignableFrom(v3)) {
                        return new f(v11_4, this.a.a(arg9, arg10, this.c));
                    }

                    throw this.a(arg8, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                }

                throw this.a(arg8, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            }

            if((arg11 instanceof r)) {
                if(this.p) {
                    this.h = true;
                    v0_1 = o.a(arg9);
                    if(Map.class.isAssignableFrom(v0_1)) {
                        arg9 = o.b(arg9, v0_1, Map.class);
                        if((arg9 instanceof ParameterizedType)) {
                            v0_2 = o.a(0, ((ParameterizedType)arg9));
                            if(String.class == v0_2) {
                                arg9 = o.a(1, ((ParameterizedType)arg9));
                                if(!okhttp3.w$b.class.isAssignableFrom(o.a(arg9))) {
                                    return new g(this.a.a(arg9, arg10, this.c), ((r)arg11).a());
                                }

                                throw this.a(arg8, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                            }

                            v9 = new StringBuilder();
                            v9.append("@PartMap keys must be of type String: ");
                            v9.append(v0_2);
                            throw this.a(arg8, v9.toString(), new Object[0]);
                        }

                        throw this.a(arg8, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }

                    throw this.a(arg8, "@PartMap parameter type must be Map.", new Object[0]);
                }

                throw this.a(arg8, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            }

            if((arg11 instanceof f.b.a)) {
                if(!this.o && !this.p) {
                    if(!this.i) {
                        try {
                            v10_1 = this.a.a(arg9, arg10, this.c);
                        }
                        catch(RuntimeException v10) {
                            throw this.a(((Throwable)v10), arg8, "Unable to create @Body converter for %s", new Object[]{arg9});
                        }

                        this.i = true;
                        return new f.i$a(v10_1);
                    }
                    else {
                        throw this.a(arg8, "Multiple @Body method annotations found.", new Object[0]);
                    }
                }

                throw this.a(arg8, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
            }

            return null;
        }

        private RuntimeException a(int arg2, String arg3, Object[] arg4) {
            StringBuilder v0 = new StringBuilder();
            v0.append(arg3);
            v0.append(" (parameter #");
            v0.append(arg2 + 1);
            v0.append(")");
            return this.a(v0.toString(), arg4);
        }

        private void a(int arg5, String arg6) {
            Object[] v0;
            int v3 = 2;
            if(n.b.matcher(((CharSequence)arg6)).matches()) {
                if(this.t.contains(arg6)) {
                    return;
                }

                v0 = new Object[v3];
                v0[0] = this.q;
                v0[1] = arg6;
                throw this.a(arg5, "URL \"%s\" does not contain \"{%s}\".", v0);
            }

            v0 = new Object[v3];
            v0[0] = n.a.pattern();
            v0[1] = arg6;
            throw this.a(arg5, "@Path parameter name must match %s. Found: %s", v0);
        }

        private RuntimeException a(Throwable arg2, int arg3, String arg4, Object[] arg5) {
            StringBuilder v0 = new StringBuilder();
            v0.append(arg4);
            v0.append(" (parameter #");
            v0.append(arg3 + 1);
            v0.append(")");
            return this.a(arg2, v0.toString(), arg5);
        }

        private RuntimeException a(String arg2, Object[] arg3) {
            return this.a(null, arg2, arg3);
        }

        private RuntimeException a(Throwable arg2, String arg3, Object[] arg4) {
            arg3 = String.format(arg3, arg4);
            StringBuilder v0 = new StringBuilder();
            v0.append(arg3);
            v0.append("\n    for method ");
            v0.append(this.b.getDeclaringClass().getSimpleName());
            v0.append(".");
            v0.append(this.b.getName());
            return new IllegalArgumentException(v0.toString(), arg2);
        }

        private s a(String[] arg9) {
            String v4;
            okhttp3.s$a v0 = new okhttp3.s$a();
            int v1 = arg9.length;
            int v3 = 0;
            while(true) {
                if(v3 >= v1) {
                    goto label_40;
                }

                v4 = arg9[v3];
                int v5 = v4.indexOf(58);
                if(v5 != -1 && v5 != 0 && v5 != v4.length() - 1) {
                    String v6 = v4.substring(0, v5);
                    v4 = v4.substring(v5 + 1).trim();
                    if("Content-Type".equalsIgnoreCase(v6)) {
                        v v5_1 = v.a(v4);
                        if(v5_1 != null) {
                            this.s = v5_1;
                        }
                        else {
                            throw this.a("Malformed content type: %s", new Object[]{v4});
                        }
                    }
                    else {
                        v0.a(v6, v4);
                    }

                    ++v3;
                    continue;
                }

                break;
            }

            throw this.a("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", new Object[]{v4});
        label_40:
            return v0.a();
        }

        private void a(String arg4, String arg5, boolean arg6) {
            if(this.m == null) {
                this.m = arg4;
                this.n = arg6;
                if(arg5.isEmpty()) {
                    return;
                }

                int v4 = arg5.indexOf(63);
                if(v4 != -1 && v4 < arg5.length() - 1) {
                    arg4 = arg5.substring(v4 + 1);
                    if(!n.a.matcher(((CharSequence)arg4)).find()) {
                    }
                    else {
                        throw this.a("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", new Object[]{arg4});
                    }
                }

                this.q = arg5;
                this.t = n.a(arg5);
                return;
            }

            throw this.a("Only one HTTP method is allowed. Found: %s and %s.", new Object[]{this.m, arg4});
        }

        private void a(Annotation arg4) {
            String v4;
            String v0;
            if((arg4 instanceof f.b.b)) {
                v0 = "DELETE";
                v4 = ((f.b.b)arg4).a();
                goto label_5;
            }
            else if((arg4 instanceof f.b.f)) {
                v0 = "GET";
                v4 = ((f.b.f)arg4).a();
                goto label_5;
            }
            else if((arg4 instanceof f.b.g)) {
                this.a("HEAD", ((f.b.g)arg4).a(), false);
                if(Void.class.equals(this.f)) {
                }
                else {
                    throw this.a("HEAD method must use Void as response type.", new Object[0]);
                }
            }
            else {
                if((arg4 instanceof f.b.n)) {
                    v0 = "PATCH";
                    v4 = ((f.b.n)arg4).a();
                }
                else if((arg4 instanceof f.b.o)) {
                    v0 = "POST";
                    v4 = ((f.b.o)arg4).a();
                }
                else if((arg4 instanceof p)) {
                    v0 = "PUT";
                    v4 = ((p)arg4).a();
                }
                else {
                    goto label_43;
                }

                this.a(v0, v4, true);
                return;
            label_43:
                if((arg4 instanceof f.b.m)) {
                    v0 = "OPTIONS";
                    v4 = ((f.b.m)arg4).a();
                label_5:
                    this.a(v0, v4, false);
                    return;
                }

                if((arg4 instanceof f.b.h)) {
                    this.a(((f.b.h)arg4).a(), ((f.b.h)arg4).b(), ((f.b.h)arg4).c());
                    return;
                }

                if((arg4 instanceof f.b.k)) {
                    String[] v4_1 = ((f.b.k)arg4).a();
                    if(v4_1.length != 0) {
                        this.r = this.a(v4_1);
                        return;
                    }

                    throw this.a("@Headers annotation is empty.", new Object[0]);
                }

                if((arg4 instanceof f.b.l)) {
                    if(!this.o) {
                        this.p = true;
                        return;
                    }

                    throw this.a("Only one encoding annotation is allowed.", new Object[0]);
                }

                if(!(arg4 instanceof f.b.e)) {
                    return;
                }

                if(!this.p) {
                    this.o = true;
                    return;
                }

                throw this.a("Only one encoding annotation is allowed.", new Object[0]);
            }
        }

        private c b() {
            Type v0 = this.b.getGenericReturnType();
            if(!o.d(v0)) {
                if(v0 != Void.TYPE) {
                    Annotation[] v1 = this.b.getAnnotations();
                    try {
                        return this.a.a(v0, v1);
                    }
                    catch(RuntimeException v1_1) {
                        throw this.a(((Throwable)v1_1), "Unable to create call adapter for %s", new Object[]{v0});
                    }
                }

                throw this.a("Service methods cannot return void.", new Object[0]);
            }

            throw this.a("Method return type must not include a type variable or wildcard: %s", new Object[]{v0});
        }

        private e c() {
            Annotation[] v0 = this.b.getAnnotations();
            try {
                return this.a.b(this.f, v0);
            }
            catch(RuntimeException v0_1) {
                throw this.a(((Throwable)v0_1), "Unable to create converter for %s", new Object[]{this.f});
            }
        }
    }

    static final Pattern a;
    static final Pattern b;
    private final okhttp3.e$a c;
    private final c d;
    private final t e;
    private final e f;
    private final String g;
    private final String h;
    private final s i;
    private final v j;
    private final boolean k;
    private final boolean l;
    private final boolean m;
    private final i[] n;

    static {
        n.a = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
        n.b = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");
    }

    n(a arg2) {
        super();
        this.c = arg2.a.a();
        this.d = arg2.w;
        this.e = arg2.a.b();
        this.f = arg2.v;
        this.g = arg2.m;
        this.h = arg2.q;
        this.i = arg2.r;
        this.j = arg2.s;
        this.k = arg2.n;
        this.l = arg2.o;
        this.m = arg2.p;
        this.n = arg2.u;
    }

    Object a(f.b arg2) {
        return this.d.a(arg2);
    }

    static Class a(Class arg1) {
        if(Boolean.TYPE == arg1) {
            return Boolean.class;
        }

        if(Byte.TYPE == arg1) {
            return Byte.class;
        }

        if(Character.TYPE == arg1) {
            return Character.class;
        }

        if(Double.TYPE == arg1) {
            return Double.class;
        }

        if(Float.TYPE == arg1) {
            return Float.class;
        }

        if(Integer.TYPE == arg1) {
            return Integer.class;
        }

        if(Long.TYPE == arg1) {
            return Long.class;
        }

        if(Short.TYPE == arg1) {
            arg1 = Short.class;
        }

        return arg1;
    }

    static Set a(String arg2) {
        Matcher v2 = n.a.matcher(((CharSequence)arg2));
        LinkedHashSet v0 = new LinkedHashSet();
        while(v2.find()) {
            ((Set)v0).add(v2.group(1));
        }

        return ((Set)v0);
    }

    Object a(ad arg2) {
        return this.f.b(arg2);
    }

    okhttp3.e a(@Nullable Object[] arg11) {
        f.k v9 = new f.k(this.g, this.e, this.h, this.i, this.j, this.k, this.l, this.m);
        i[] v0 = this.n;
        int v1 = 0;
        int v2 = arg11 != null ? arg11.length : 0;
        if(v2 == v0.length) {
            while(v1 < v2) {
                v0[v1].a(v9, arg11[v1]);
                ++v1;
            }

            return this.c.a(v9.a());
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Argument count (");
        v1_1.append(v2);
        v1_1.append(") doesn\'t match expected count (");
        v1_1.append(v0.length);
        v1_1.append(")");
        throw new IllegalArgumentException(v1_1.toString());
    }
}

