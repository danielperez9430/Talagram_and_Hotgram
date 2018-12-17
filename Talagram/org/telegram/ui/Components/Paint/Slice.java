package org.telegram.ui.Components.Paint;

import android.graphics.RectF;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;

public class Slice {
    private RectF bounds;
    private File file;

    public Slice(ByteBuffer arg2, RectF arg3, DispatchQueue arg4) {
        super();
        this.bounds = arg3;
        try {
            this.file = File.createTempFile("paint", ".bin", ApplicationLoader.applicationContext.getCacheDir());
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        if(this.file == null) {
            return;
        }

        this.storeData(arg2);
    }

    public void cleanResources() {
        if(this.file != null) {
            this.file.delete();
            this.file = null;
        }
    }

    public RectF getBounds() {
        return new RectF(this.bounds);
    }

    public ByteBuffer getData() {
        int v0 = 1024;
        try {
            byte[] v1 = new byte[v0];
            byte[] v0_2 = new byte[v0];
            FileInputStream v2 = new FileInputStream(this.file);
            ByteArrayOutputStream v3 = new ByteArrayOutputStream();
            Inflater v4 = new Inflater(true);
            while(true) {
                int v5 = v2.read(v1);
                if(v5 != -1) {
                    v4.setInput(v1, 0, v5);
                }

                while(true) {
                    v5 = v4.inflate(v0_2, 0, v0_2.length);
                    if(v5 == 0) {
                        break;
                    }

                    v3.write(v0_2, 0, v5);
                }

                if(v4.finished()) {
                    v4.end();
                    ByteBuffer v0_3 = ByteBuffer.wrap(v3.toByteArray(), 0, v3.size());
                    v3.close();
                    v2.close();
                    return v0_3;
                }

                v4.needsInput();
            }
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
            return null;
        }
    }

    public int getHeight() {
        return ((int)this.bounds.height());
    }

    public int getWidth() {
        return ((int)this.bounds.width());
    }

    public int getX() {
        return ((int)this.bounds.left);
    }

    public int getY() {
        return ((int)this.bounds.top);
    }

    private void storeData(ByteBuffer arg5) {
        try {
            byte[] v0 = arg5.array();
            FileOutputStream v1 = new FileOutputStream(this.file);
            Deflater v2 = new Deflater(1, true);
            v2.setInput(v0, arg5.arrayOffset(), arg5.remaining());
            v2.finish();
            byte[] v5_1 = new byte[1024];
            while(!v2.finished()) {
                v1.write(v5_1, 0, v2.deflate(v5_1));
            }

            v2.end();
            v1.close();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
        }
    }
}

