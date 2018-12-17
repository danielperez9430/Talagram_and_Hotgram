package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;

public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzxu;

    public CursorWrapper(Cursor arg4) {
        super(arg4);
        int v0;
        for(v0 = 0; v0 < 10; ++v0) {
            if(!(arg4 instanceof android.database.CursorWrapper)) {
                break;
            }

            arg4 = ((android.database.CursorWrapper)arg4).getWrappedCursor();
        }

        if(!(arg4 instanceof AbstractWindowedCursor)) {
            String v1 = "Unknown type: ";
            String v4 = String.valueOf(arg4.getClass().getName());
            v4 = v4.length() != 0 ? v1.concat(v4) : new String(v1);
            throw new IllegalArgumentException(v4);
        }

        this.zzxu = ((AbstractWindowedCursor)arg4);
    }

    public void fillWindow(int arg2, CursorWindow arg3) {
        this.zzxu.fillWindow(arg2, arg3);
    }

    public CursorWindow getWindow() {
        return this.zzxu.getWindow();
    }

    public AbstractWindowedCursor getWrappedCursor() {
        return this.zzxu;
    }

    public Cursor getWrappedCursor() {
        return this.getWrappedCursor();
    }

    public boolean onMove(int arg2, int arg3) {
        return this.zzxu.onMove(arg2, arg3);
    }

    public void setWindow(CursorWindow arg2) {
        this.zzxu.setWindow(arg2);
    }
}

