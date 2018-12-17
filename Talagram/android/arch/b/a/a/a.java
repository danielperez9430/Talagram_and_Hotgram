package android.arch.b.a.a;

import android.arch.b.a.b;
import android.arch.b.a.f;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteQuery;
import java.util.List;

class a implements b {
    private static final String[] a;
    private static final String[] b;
    private final SQLiteDatabase c;

    static {
        a.a = new String[]{"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
        a.b = new String[0];
    }

    a(SQLiteDatabase arg1) {
        super();
        this.c = arg1;
    }

    public f a(String arg3) {
        return new e(this.c.compileStatement(arg3));
    }

    public Cursor a(android.arch.b.a.e arg5) {
        return this.c.rawQueryWithFactory(new SQLiteDatabase$CursorFactory(arg5) {
            public Cursor newCursor(SQLiteDatabase arg2, SQLiteCursorDriver arg3, String arg4, SQLiteQuery arg5) {
                this.a.a(new d(((SQLiteProgram)arg5)));
                return new SQLiteCursor(arg3, arg4, arg5);
            }
        }, arg5.a(), a.b, null);
    }

    public void a() {
        this.c.beginTransaction();
    }

    public Cursor b(String arg2) {
        return this.a(new android.arch.b.a.a(arg2));
    }

    public void b() {
        this.c.endTransaction();
    }

    public void c() {
        this.c.setTransactionSuccessful();
    }

    public void c(String arg2) {
        this.c.execSQL(arg2);
    }

    public void close() {
        this.c.close();
    }

    public boolean d() {
        return this.c.inTransaction();
    }

    public boolean e() {
        return this.c.isOpen();
    }

    public String f() {
        return this.c.getPath();
    }

    public List g() {
        return this.c.getAttachedDbs();
    }
}

