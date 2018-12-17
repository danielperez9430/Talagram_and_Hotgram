package com.coremedia.iso;

import com.coremedia.iso.boxes.MovieBox;
import com.googlecode.mp4parser.BasicContainer;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.annotations.DoNotParseDetail;
import com.googlecode.mp4parser.util.Logger;
import java.io.Closeable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;

@DoNotParseDetail public class IsoFile extends BasicContainer implements Closeable {
    private static Logger LOG;

    static {
        IsoFile.LOG = Logger.getLogger(IsoFile.class);
    }

    public IsoFile(DataSource arg3) {
        this(arg3, new PropertyBoxParserImpl(new String[0]));
    }

    public IsoFile(String arg3) {
        this(new FileDataSourceImpl(new File(arg3)));
    }

    public IsoFile(DataSource arg3, BoxParser arg4) {
        super();
        this.initContainer(arg3, arg3.size(), arg4);
    }

    public static String bytesToFourCC(byte[] arg3) {
        int v0 = 4;
        byte[] v1 = new byte[v0];
        if(arg3 != null) {
            System.arraycopy(arg3, 0, v1, 0, Math.min(arg3.length, v0));
        }

        try {
            return new String(v1, "ISO-8859-1");
        }
        catch(UnsupportedEncodingException v3) {
            throw new Error("Required character encoding is missing", ((Throwable)v3));
        }
    }

    public void close() {
        this.dataSource.close();
    }

    public static byte[] fourCCtoBytes(String arg4) {
        int v0 = 4;
        byte[] v1 = new byte[v0];
        if(arg4 != null) {
            int v2;
            for(v2 = 0; v2 < Math.min(v0, arg4.length()); ++v2) {
                v1[v2] = ((byte)arg4.charAt(v2));
            }
        }

        return v1;
    }

    public void getBox(WritableByteChannel arg1) {
        this.writeContainer(arg1);
    }

    public MovieBox getMovieBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof MovieBox));

        return ((MovieBox)v1);
    }

    public long getSize() {
        return this.getContainerSize();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("model(");
        v0.append(this.dataSource.toString());
        v0.append(")");
        return v0.toString();
    }
}

