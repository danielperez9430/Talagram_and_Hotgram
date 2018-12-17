package com.persianswitch.sdk.base.db.phoenix;

import android.content.ContentValues;
import android.database.Cursor;

public interface FieldConverter {
    Object a(Cursor arg1, int arg2);

    void a(String arg1, Object arg2, ContentValues arg3);

    ColumnType a();
}

