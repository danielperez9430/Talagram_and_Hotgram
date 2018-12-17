package com.persianswitch.sdk.base.db.phoenix;

import android.content.ContentValues;
import android.database.Cursor;

public interface EntityConverter {
    Object a(Cursor arg1);

    void a(Object arg1, ContentValues arg2);
}

