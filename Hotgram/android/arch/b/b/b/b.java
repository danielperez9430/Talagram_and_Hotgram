package android.arch.b.b.b;

import android.database.Cursor;
import android.os.Build$VERSION;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class b {
    public class a {
        public final String a;
        public final String b;
        public final int c;
        public final boolean d;
        public final int e;

        public a(String arg1, String arg2, boolean arg3, int arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.d = arg3;
            this.e = arg4;
            this.c = a.a(arg2);
        }

        private static int a(String arg2) {
            int v0 = 5;
            if(arg2 == null) {
                return v0;
            }

            arg2 = arg2.toUpperCase(Locale.US);
            if(arg2.contains("INT")) {
                return 3;
            }

            if(!arg2.contains("CHAR") && !arg2.contains("CLOB")) {
                if(arg2.contains("TEXT")) {
                }
                else if(arg2.contains("BLOB")) {
                    return v0;
                }
                else {
                    if(!arg2.contains("REAL") && !arg2.contains("FLOA")) {
                        if(arg2.contains("DOUB")) {
                        }
                        else {
                            return 1;
                        }
                    }

                    return 4;
                }
            }

            return 2;
        }

        public boolean a() {
            boolean v0 = this.e > 0 ? true : false;
            return v0;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((a)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(Build$VERSION.SDK_INT >= 20) {
                        if(this.e != ((a)arg5).e) {
                            return 0;
                        }
                    }
                    else if(this.a() != ((a)arg5).a()) {
                        return 0;
                    }

                    if(!this.a.equals(((a)arg5).a)) {
                        return 0;
                    }

                    if(this.d != ((a)arg5).d) {
                        return 0;
                    }

                    if(this.c == ((a)arg5).c) {
                    }
                    else {
                        v0 = false;
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            int v0 = (this.a.hashCode() * 31 + this.c) * 31;
            int v1 = this.d ? 1231 : 1237;
            return (v0 + v1) * 31 + this.e;
        }

        public String toString() {
            return "Column{name=\'" + this.a + '\'' + ", type=\'" + this.b + '\'' + ", affinity=\'" + this.c + '\'' + ", notNull=" + this.d + ", primaryKeyPosition=" + this.e + '}';
        }
    }

    public class android.arch.b.b.b.b$b {
        public final String a;
        public final String b;
        public final String c;
        public final List d;
        public final List e;

        public android.arch.b.b.b.b$b(String arg1, String arg2, String arg3, List arg4, List arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = Collections.unmodifiableList(arg4);
            this.e = Collections.unmodifiableList(arg5);
        }

        public boolean equals(Object arg4) {
            if(this == (((android.arch.b.b.b.b$b)arg4))) {
                return 1;
            }

            if(arg4 != null) {
                if(this.getClass() != arg4.getClass()) {
                }
                else if(!this.a.equals(((android.arch.b.b.b.b$b)arg4).a)) {
                    return 0;
                }
                else if(!this.b.equals(((android.arch.b.b.b.b$b)arg4).b)) {
                    return 0;
                }
                else if(!this.c.equals(((android.arch.b.b.b.b$b)arg4).c)) {
                    return 0;
                }
                else if(!this.d.equals(((android.arch.b.b.b.b$b)arg4).d)) {
                    return 0;
                }
                else {
                    return this.e.equals(((android.arch.b.b.b.b$b)arg4).e);
                }
            }

            return 0;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31 + this.b.hashCode()) * 31 + this.c.hashCode()) * 31 + this.d.hashCode()) * 31 + this.e.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable=\'" + this.a + '\'' + ", onDelete=\'" + this.b + '\'' + ", onUpdate=\'" + this.c + '\'' + ", columnNames=" + this.d + ", referenceColumnNames=" + this.e + '}';
        }
    }

    class c implements Comparable {
        final int a;
        final int b;
        final String c;
        final String d;

        c(int arg1, int arg2, String arg3, String arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
        }

        public int a(c arg3) {
            int v0 = this.a - arg3.a;
            if(v0 == 0) {
                v0 = this.b - arg3.b;
            }

            return v0;
        }

        public int compareTo(Object arg1) {
            return this.a(((c)arg1));
        }
    }

    public class d {
        public final String a;
        public final boolean b;
        public final List c;

        public d(String arg1, boolean arg2, List arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public boolean equals(Object arg4) {
            if(this == (((d)arg4))) {
                return 1;
            }

            if(arg4 != null) {
                if(this.getClass() != arg4.getClass()) {
                }
                else if(this.b != ((d)arg4).b) {
                    return 0;
                }
                else if(!this.c.equals(((d)arg4).c)) {
                    return 0;
                }
                else if(this.a.startsWith("index_")) {
                    return ((d)arg4).a.startsWith("index_");
                }
                else {
                    return this.a.equals(((d)arg4).a);
                }
            }

            return 0;
        }

        public int hashCode() {
            String v0 = this.a.startsWith("index_") ? "index_" : this.a;
            int v0_1 = v0.hashCode();
            return (v0_1 * 31 + this.b) * 31 + this.c.hashCode();
        }

        public String toString() {
            return "Index{name=\'" + this.a + '\'' + ", unique=" + this.b + ", columns=" + this.c + '}';
        }
    }

    public final String a;
    public final Map b;
    public final Set c;
    public final Set d;

    public b(String arg1, Map arg2, Set arg3, Set arg4) {
        super();
        this.a = arg1;
        this.b = Collections.unmodifiableMap(arg2);
        this.c = Collections.unmodifiableSet(arg3);
        Set v1 = arg4 == null ? null : Collections.unmodifiableSet(arg4);
        this.d = v1;
    }

    private static d a(android.arch.b.a.b arg6, String arg7, boolean arg8) {
        d v1_1;
        StringBuilder v0 = new StringBuilder();
        v0.append("PRAGMA index_xinfo(`");
        v0.append(arg7);
        v0.append("`)");
        Cursor v6 = arg6.b(v0.toString());
        try {
            int v0_1 = v6.getColumnIndex("seqno");
            int v1 = v6.getColumnIndex("cid");
            int v2 = v6.getColumnIndex("name");
            int v3 = -1;
            if(v0_1 != v3 && v1 != v3) {
                if(v2 == v3) {
                }
                else {
                    TreeMap v3_1 = new TreeMap();
                    while(v6.moveToNext()) {
                        if(v6.getInt(v1) < 0) {
                        }
                        else {
                            v3_1.put(Integer.valueOf(v6.getInt(v0_1)), v6.getString(v2));
                        }
                    }

                    ArrayList v0_2 = new ArrayList(v3_1.size());
                    ((List)v0_2).addAll(v3_1.values());
                    v1_1 = new d(arg7, arg8, ((List)v0_2));
                    goto label_39;
                }
            }

            goto label_41;
        }
        catch(Throwable v7) {
            goto label_45;
        }

    label_39:
        v6.close();
        return v1_1;
    label_45:
        v6.close();
        throw v7;
    label_41:
        v6.close();
        return null;
    }

    public static b a(android.arch.b.a.b arg3, String arg4) {
        return new b(arg4, b.c(arg3, arg4), b.b(arg3, arg4), b.d(arg3, arg4));
    }

    private static List a(Cursor arg12) {
        int v0 = arg12.getColumnIndex("id");
        int v1 = arg12.getColumnIndex("seq");
        int v2 = arg12.getColumnIndex("from");
        int v3 = arg12.getColumnIndex("to");
        int v4 = arg12.getCount();
        ArrayList v5 = new ArrayList();
        int v6;
        for(v6 = 0; v6 < v4; ++v6) {
            arg12.moveToPosition(v6);
            ((List)v5).add(new c(arg12.getInt(v0), arg12.getInt(v1), arg12.getString(v2), arg12.getString(v3)));
        }

        Collections.sort(((List)v5));
        return ((List)v5);
    }

    private static Set b(android.arch.b.a.b arg18, String arg19) {
        int v9;
        HashSet v0 = new HashSet();
        StringBuilder v1 = new StringBuilder();
        v1.append("PRAGMA foreign_key_list(`");
        v1.append(arg19);
        v1.append("`)");
        Cursor v1_1 = arg18.b(v1.toString());
        try {
            int v2 = v1_1.getColumnIndex("id");
            int v3 = v1_1.getColumnIndex("seq");
            int v4 = v1_1.getColumnIndex("table");
            int v5 = v1_1.getColumnIndex("on_delete");
            int v6 = v1_1.getColumnIndex("on_update");
            List v7 = b.a(v1_1);
            int v8 = v1_1.getCount();
            v9 = 0;
            while(true) {
            label_26:
                if(v9 >= v8) {
                    goto label_60;
                }

                v1_1.moveToPosition(v9);
                if(v1_1.getInt(v3) != 0) {
                }
                else {
                    int v10 = v1_1.getInt(v2);
                    ArrayList v15 = new ArrayList();
                    ArrayList v14 = new ArrayList();
                    Iterator v11 = v7.iterator();
                    while(v11.hasNext()) {
                        Object v12 = v11.next();
                        if(((c)v12).a != v10) {
                            continue;
                        }

                        ((List)v15).add(((c)v12).c);
                        ((List)v14).add(((c)v12).d);
                    }

                    ((Set)v0).add(new android.arch.b.b.b.b$b(v1_1.getString(v4), v1_1.getString(v5), v1_1.getString(v6), ((List)v15), v14));
                }

                break;
            }
        }
        catch(Throwable v0_1) {
            goto label_63;
        }

        ++v9;
        goto label_26;
    label_60:
        v1_1.close();
        return ((Set)v0);
    label_63:
        v1_1.close();
        throw v0_1;
    }

    private static Map c(android.arch.b.a.b arg9, String arg10) {
        StringBuilder v0 = new StringBuilder();
        v0.append("PRAGMA table_info(`");
        v0.append(arg10);
        v0.append("`)");
        Cursor v9 = arg9.b(v0.toString());
        HashMap v10 = new HashMap();
        try {
            if(v9.getColumnCount() > 0) {
                int v0_1 = v9.getColumnIndex("name");
                int v1 = v9.getColumnIndex("type");
                int v2 = v9.getColumnIndex("notnull");
                int v3 = v9.getColumnIndex("pk");
                while(v9.moveToNext()) {
                    String v4 = v9.getString(v0_1);
                    String v5 = v9.getString(v1);
                    boolean v6 = v9.getInt(v2) != 0 ? true : false;
                    ((Map)v10).put(v4, new a(v4, v5, v6, v9.getInt(v3)));
                }
            }
        }
        catch(Throwable v10_1) {
            v9.close();
            throw v10_1;
        }

        v9.close();
        return ((Map)v10);
    }

    private static Set d(android.arch.b.a.b arg8, String arg9) {
        d v5_1;
        HashSet v4_1;
        Set v3;
        StringBuilder v0 = new StringBuilder();
        v0.append("PRAGMA index_list(`");
        v0.append(arg9);
        v0.append("`)");
        Cursor v9 = arg8.b(v0.toString());
        try {
            int v0_1 = v9.getColumnIndex("name");
            int v1 = v9.getColumnIndex("origin");
            int v2 = v9.getColumnIndex("unique");
            v3 = null;
            int v4 = -1;
            if(v0_1 != v4 && v1 != v4) {
                if(v2 == v4) {
                }
                else {
                    v4_1 = new HashSet();
                    while(true) {
                    label_23:
                        if(!v9.moveToNext()) {
                            goto label_42;
                        }
                        else if(!"c".equals(v9.getString(v1))) {
                            continue;
                        }
                        else {
                            String v5 = v9.getString(v0_1);
                            boolean v7 = true;
                            if(v9.getInt(v2) == 1) {
                            }
                            else {
                                v7 = false;
                            }

                            v5_1 = b.a(arg8, v5, v7);
                            if(v5_1 != null) {
                                goto label_40;
                            }

                            goto label_38;
                        }
                    }
                }
            }

            goto label_44;
        }
        catch(Throwable v8) {
            goto label_47;
        }

    label_38:
        v9.close();
        return v3;
        try {
        label_40:
            v4_1.add(v5_1);
            goto label_23;
        }
        catch(Throwable v8) {
            goto label_47;
        }

    label_42:
        v9.close();
        return ((Set)v4_1);
    label_47:
        v9.close();
        throw v8;
    label_44:
        v9.close();
        return v3;
    }

    public boolean equals(Object arg5) {
        if(this == (((b)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.a != null) {
                    if(!this.a.equals(((b)arg5).a)) {
                        return 0;
                    }
                }
                else if(((b)arg5).a != null) {
                    return 0;
                }

                if(this.b != null) {
                    if(!this.b.equals(((b)arg5).b)) {
                        return 0;
                    }
                }
                else if(((b)arg5).b != null) {
                    return 0;
                }

                if(this.c != null) {
                    if(!this.c.equals(((b)arg5).c)) {
                        return 0;
                    }
                }
                else if(((b)arg5).c != null) {
                    return 0;
                }

                if(this.d != null) {
                    if(((b)arg5).d == null) {
                    }
                    else {
                        return this.d.equals(((b)arg5).d);
                    }
                }

                return 1;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.a != null ? this.a.hashCode() : 0;
        v0 *= 31;
        int v2 = this.b != null ? this.b.hashCode() : 0;
        v0 = (v0 + v2) * 31;
        if(this.c != null) {
            v1 = this.c.hashCode();
        }

        return v0 + v1;
    }

    public String toString() {
        return "TableInfo{name=\'" + this.a + '\'' + ", columns=" + this.b + ", foreignKeys=" + this.c + ", indices=" + this.d + '}';
    }
}

