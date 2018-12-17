package c.a.a.a.a.d;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class g extends h {
    public g(Context arg1, File arg2, String arg3, String arg4) {
        super(arg1, arg2, arg3, arg4);
    }

    public OutputStream a(File arg3) {
        return new GZIPOutputStream(new FileOutputStream(arg3));
    }
}

