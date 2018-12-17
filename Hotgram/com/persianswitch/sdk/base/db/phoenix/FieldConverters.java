package com.persianswitch.sdk.base.db.phoenix;

import android.content.ContentValues;
import android.database.Cursor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class FieldConverters {
    public class BigDecimalConverter implements FieldConverter {
        public BigDecimalConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.a;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((BigDecimal)arg2), arg3);
        }

        public void a(String arg1, BigDecimal arg2, ContentValues arg3) {
            arg3.put(arg1, arg2.toString());
        }

        public BigDecimal b(Cursor arg2, int arg3) {
            BigDecimal v2 = arg2.isNull(arg3) ? null : new BigDecimal(arg2.getString(arg3));
            return v2;
        }
    }

    public class BigIntegerConverter implements FieldConverter {
        public BigIntegerConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.a;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((BigInteger)arg2), arg3);
        }

        public void a(String arg1, BigInteger arg2, ContentValues arg3) {
            arg3.put(arg1, arg2.toString());
        }

        public BigInteger b(Cursor arg2, int arg3) {
            BigInteger v2 = arg2.isNull(arg3) ? null : new BigInteger(arg2.getString(arg3));
            return v2;
        }
    }

    public class BlobArrayConverter implements FieldConverter {
        public BlobArrayConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.d;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((byte[])arg2), arg3);
        }

        public void a(String arg1, byte[] arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public byte[] b(Cursor arg1, int arg2) {
            return arg1.getBlob(arg2);
        }
    }

    public class BooleanConverter implements FieldConverter {
        public BooleanConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.b;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Boolean arg2, ContentValues arg3) {
            arg3.put(arg1, Integer.valueOf(arg2.booleanValue()));
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Boolean)arg2), arg3);
        }

        public Boolean b(Cursor arg1, int arg2) {
            int v1 = arg1.getInt(arg2);
            boolean v2 = true;
            if(v1 == 1) {
            }
            else {
                v2 = false;
            }

            return Boolean.valueOf(v2);
        }
    }

    public class ByteConverter implements FieldConverter {
        public ByteConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.b;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Byte arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Byte)arg2), arg3);
        }

        public Byte b(Cursor arg1, int arg2) {
            return Byte.valueOf(((byte)arg1.getInt(arg2)));
        }
    }

    public class DateConverter implements FieldConverter {
        public DateConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.b;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Date)arg2), arg3);
        }

        public void a(String arg3, Date arg4, ContentValues arg5) {
            arg5.put(arg3, Long.valueOf(arg4.getTime()));
        }

        public Date b(Cursor arg2, int arg3) {
            Date v2 = arg2.isNull(arg3) ? null : new Date(arg2.getLong(arg3));
            return v2;
        }
    }

    public class DoubleConverter implements FieldConverter {
        public DoubleConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.c;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Double arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Double)arg2), arg3);
        }

        public Double b(Cursor arg1, int arg2) {
            return Double.valueOf(arg1.getDouble(arg2));
        }
    }

    public class FloatConverter implements FieldConverter {
        public FloatConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.c;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Float arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Float)arg2), arg3);
        }

        public Float b(Cursor arg1, int arg2) {
            return Float.valueOf(arg1.getFloat(arg2));
        }
    }

    public class IntegerConverter implements FieldConverter {
        public IntegerConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.b;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Integer arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Integer)arg2), arg3);
        }

        public Integer b(Cursor arg1, int arg2) {
            return Integer.valueOf(arg1.getInt(arg2));
        }
    }

    public class LongConverter implements FieldConverter {
        public LongConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.b;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Long arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Long)arg2), arg3);
        }

        public Long b(Cursor arg1, int arg2) {
            return Long.valueOf(arg1.getLong(arg2));
        }
    }

    public class ShortConverter implements FieldConverter {
        public ShortConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.b;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((Short)arg2), arg3);
        }

        public void a(String arg1, Short arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public Short b(Cursor arg1, int arg2) {
            return Short.valueOf(arg1.getShort(arg2));
        }
    }

    public class StringConverter implements FieldConverter {
        public StringConverter() {
            super();
        }

        public ColumnType a() {
            return ColumnType.a;
        }

        public Object a(Cursor arg1, int arg2) {
            return this.b(arg1, arg2);
        }

        public void a(String arg1, Object arg2, ContentValues arg3) {
            this.a(arg1, ((String)arg2), arg3);
        }

        public void a(String arg1, String arg2, ContentValues arg3) {
            arg3.put(arg1, arg2);
        }

        public String b(Cursor arg1, int arg2) {
            return arg1.getString(arg2);
        }
    }

    public FieldConverters() {
        super();
    }

    public static FieldConverter a(Class arg3) {
        if(arg3 == byte[].class) {
            return new BlobArrayConverter();
        }

        if(arg3 == Boolean.class) {
            return new BooleanConverter();
        }

        if(arg3 == String.class) {
            return new StringConverter();
        }

        if(arg3 == Byte.class) {
            return new ByteConverter();
        }

        if(arg3 == Integer.class) {
            return new IntegerConverter();
        }

        if(arg3 == Short.class) {
            return new ShortConverter();
        }

        if(arg3 == Long.class) {
            return new LongConverter();
        }

        if(arg3 == Float.class) {
            return new FloatConverter();
        }

        if(arg3 == Double.class) {
            return new DoubleConverter();
        }

        if(arg3 == Date.class) {
            return new DateConverter();
        }

        if(arg3 == BigDecimal.class) {
            return new BigDecimalConverter();
        }

        if(arg3 == BigInteger.class) {
            return new BigDecimalConverter();
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("You must implement your own FieldConverter for type");
        v1.append(arg3.getName());
        throw new RuntimeException(v1.toString());
    }
}

