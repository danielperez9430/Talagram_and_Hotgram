package com.coremedia.iso;

import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.util.Path;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public class BoxReplacer {
    static {
    }

    public BoxReplacer() {
        super();
    }

    public static void replace(Map arg7, File arg8) {
        IsoFile v0 = new IsoFile(new FileDataSourceImpl(new RandomAccessFile(arg8, "r").getChannel()));
        HashMap v1 = new HashMap();
        HashMap v2 = new HashMap();
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v3 = v7.next();
            Box v4 = Path.getPath(((Container)v0), ((Map$Entry)v3).getKey());
            ((Map)v1).put(Path.createPath(v4), ((Map$Entry)v3).getValue());
            ((Map)v2).put(Path.createPath(v4), Long.valueOf(v4.getOffset()));
        }

        v0.close();
        FileChannel v3_1 = new RandomAccessFile(arg8, "rw").getChannel();
        Iterator v4_1 = ((Map)v1).keySet().iterator();
        while(v4_1.hasNext()) {
            Object v7_1 = v4_1.next();
            Object v8 = ((Map)v1).get(v7_1);
            v3_1.position(((Map)v2).get(v7_1).longValue());
            ((Box)v8).getBox(((WritableByteChannel)v3_1));
        }

        v3_1.close();
    }
}

