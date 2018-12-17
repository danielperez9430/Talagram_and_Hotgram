package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.v4.d.b$b;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class d extends h {
    d() {
        super();
    }

    private File a(ParcelFileDescriptor arg4) {
        File v0 = null;
        try {
            StringBuilder v1 = new StringBuilder();
            v1.append("/proc/self/fd/");
            v1.append(arg4.getFd());
            String v4 = Os.readlink(v1.toString());
            if(!OsConstants.S_ISREG(Os.stat(v4).st_mode)) {
                return v0;
            }

            return new File(v4);
        }
        catch(ErrnoException ) {
            return v0;
        }
    }

    public Typeface a(Context arg5, CancellationSignal arg6, b[] arg7, int arg8) {
        Throwable v3;
        FileInputStream v7_2;
        Typeface v5_1;
        ParcelFileDescriptor v6;
        Typeface v1 = null;
        if(arg7.length < 1) {
            return v1;
        }

        b v7 = this.a(arg7, arg8);
        ContentResolver v8 = arg5.getContentResolver();
        try {
            v6 = v8.openFileDescriptor(v7.a(), "r", arg6);
        }
        catch(IOException ) {
            return v1;
        }

        try {
            File v7_1 = this.a(v6);
            if(v7_1 != null) {
                if(!v7_1.canRead()) {
                }
                else {
                    v5_1 = Typeface.createFromFile(v7_1);
                    if(v6 != null) {
                        goto label_17;
                    }

                    return v5_1;
                }
            }

            goto label_19;
        }
        catch(Throwable v5) {
            goto label_45;
        }
        catch(Throwable v5) {
            goto label_42;
        }

        try {
        label_17:
            v6.close();
        }
        catch(IOException ) {
            return v1;
        }

        return v5_1;
        try {
        label_19:
            v7_2 = new FileInputStream(v6.getFileDescriptor());
        }
        catch(Throwable v5) {
            goto label_45;
        }
        catch(Throwable v5) {
            goto label_42;
        }

        try {
            v5_1 = super.a(arg5, ((InputStream)v7_2));
            goto label_23;
        }
        catch(Throwable v5) {
            v8_1 = ((Throwable)v1);
        }
        catch(Throwable v5) {
            try {
                throw v5;
            }
            catch(Throwable v8_1) {
                v3 = v8_1;
                v8_1 = v5;
                v5 = v3;
            }
        }

        if(v8_1 != null) {
            try {
                v7_2.close();
                goto label_40;
            }
            catch(Throwable ) {
                goto label_40;
            }
            catch(Throwable v5) {
                goto label_42;
            }
        }
        else {
            try {
                v7_2.close();
            label_40:
                throw v5;
            label_23:
                v7_2.close();
                if(v6 == null) {
                    return v5_1;
                }

                goto label_25;
            }
            catch(Throwable v5) {
            label_42:
                v7_3 = ((Throwable)v1);
                goto label_50;
            }
            catch(Throwable v5) {
                try {
                label_45:
                    throw v5;
                }
                catch(Throwable v7_3) {
                    v3 = v7_3;
                    v7_3 = v5;
                    v5 = v3;
                    goto label_50;
                }
            }
        }

        goto label_40;
    label_50:
        if(v6 != null) {
            if(v7_3 != null) {
                try {
                    v6.close();
                    goto label_55;
                }
                catch(Throwable ) {
                    goto label_55;
                }
                catch(IOException ) {
                    return v1;
                }
            }
            else {
                try {
                    v6.close();
                label_55:
                    throw v5;
                label_25:
                    v6.close();
                    return v5_1;
                }
                catch(IOException ) {
                    return v1;
                }
            }
        }

        goto label_55;
        return v5_1;
    }
}

