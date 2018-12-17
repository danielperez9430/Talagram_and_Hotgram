package com.persianswitch.sdk.base.db.phoenix;

public enum ColumnType {
    public static final enum ColumnType a;
    public static final enum ColumnType b;
    public static final enum ColumnType c;
    public static final enum ColumnType d;
    private final String e;

    static {
        ColumnType.a = new ColumnType("TEXT", 0, "TEXT");
        ColumnType.b = new ColumnType("INTEGER", 1, "INTEGER");
        ColumnType.c = new ColumnType("REAL", 2, "REAL");
        ColumnType.d = new ColumnType("BLOB", 3, "BLOB");
        ColumnType.f = new ColumnType[]{ColumnType.a, ColumnType.b, ColumnType.c, ColumnType.d};
    }

    private ColumnType(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.e = arg3;
    }

    public static ColumnType a(Class arg0) {
        return FieldConverters.a(arg0).a();
    }

    public static ColumnType valueOf(String arg1) {
        return Enum.valueOf(ColumnType.class, arg1);
    }

    public static ColumnType[] values() {
        // Method was not decompiled
    }
}

