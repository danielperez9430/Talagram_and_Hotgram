package com.persianswitch.sdk.base.db.phoenix;

import android.content.ContentValues;
import android.database.Cursor;
import com.persianswitch.sdk.base.db.phoenix.query.Where;
import com.persianswitch.sdk.base.db.phoenix.query.WhereCondition$SimpleOperatorCondition;
import com.persianswitch.sdk.base.utils.strings.StringUtils;

public class Column {
    private final String a;
    private final ColumnType b;
    private final FieldConverter c;
    private final Class d;
    private final boolean e;
    private final String f;

    public Column(String arg3, Class arg4) {
        this(arg3, arg4, false, "");
    }

    public Column(String arg1, Class arg2, boolean arg3, String arg4) {
        super();
        this.a = arg1;
        this.d = arg2;
        this.c = FieldConverters.a(this.d);
        this.b = ColumnType.a(this.d);
        this.e = arg3;
        this.f = arg4;
    }

    public Column(String arg2, Class arg3, boolean arg4) {
        this(arg2, arg3, arg4, "");
    }

    public Where a(Object arg4) {
        return new Where(new SimpleOperatorCondition(this, " = ", arg4));
    }

    public Object a(Cursor arg3) {
        return this.c.a(arg3, arg3.getColumnIndexOrThrow(this.a));
    }

    String a() {
        String v0 = " ";
        Object[] v1 = new Object[3];
        v1[0] = this.a;
        v1[1] = this.b;
        String v2 = this.e ? "PRIMARY KEY" : "";
        v1[2] = v2;
        return StringUtils.a(v0, v1);
    }

    public void a(Object arg3, ContentValues arg4) {
        this.c.a(this.a, arg3, arg4);
    }

    boolean b() {
        return this.e;
    }

    public String c() {
        return this.a;
    }

    public ColumnType d() {
        return this.b;
    }
}

