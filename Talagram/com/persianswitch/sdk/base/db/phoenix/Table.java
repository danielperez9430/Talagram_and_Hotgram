package com.persianswitch.sdk.base.db.phoenix;

import android.database.sqlite.SQLiteDatabase;

public abstract class Table {
    public Table() {
        super();
    }

    public abstract String a();

    public void a(SQLiteDatabase arg9) {
        StringBuilder v1_1;
        String v0 = this.a();
        if(v0 != null) {
            Column[] v1 = this.c();
            if(v1 != null) {
                StringBuilder v2 = new StringBuilder();
                v2.append("CREATE TABLE ");
                v2.append(this.a());
                v2.append("( ");
                int v3 = 0;
                int v4 = 0;
                while(v3 < v1.length) {
                    Column v5 = v1[v3];
                    if(v5.b()) {
                        ++v4;
                    }

                    v2.append(v5.a());
                    if(v3 < v1.length - 1) {
                        v2.append(", ");
                    }

                    ++v3;
                }

                v2.append(" )");
                if(v4 != 0) {
                    if(v4 <= 1) {
                        arg9.execSQL(v2.toString());
                        return;
                    }

                    v1_1 = new StringBuilder();
                    v1_1.append("Table ");
                    v1_1.append(v0);
                    v1_1.append(" has more than one primary key!");
                    throw new IllegalStateException(v1_1.toString());
                }

                v1_1 = new StringBuilder();
                v1_1.append("Table ");
                v1_1.append(v0);
                v1_1.append(" has not any primary key!");
                throw new IllegalStateException(v1_1.toString());
            }

            throw new IllegalStateException("getColumns() can not be null!");
        }

        throw new IllegalStateException("getName() can not be null!");
    }

    public abstract Column b();

    public void b(SQLiteDatabase arg4) {
        String v0 = this.a();
        if(v0 != null) {
            arg4.execSQL(" DELETE FROM " + v0);
            return;
        }

        throw new IllegalStateException("getName() can not be null!");
    }

    public abstract Column[] c();

    public abstract EntityConverter d();
}

