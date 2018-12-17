package android.arch.b.a.a;

import android.arch.b.a.f;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;

class e extends d implements f {
    private final SQLiteStatement a;

    e(SQLiteStatement arg1) {
        super(((SQLiteProgram)arg1));
        this.a = arg1;
    }

    public int a() {
        return this.a.executeUpdateDelete();
    }

    public long b() {
        return this.a.executeInsert();
    }
}

