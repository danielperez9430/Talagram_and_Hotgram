package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
    private final int height;
    private final int width;

    public UrlTileProvider(int arg1, int arg2) {
        super();
        this.width = arg1;
        this.height = arg2;
    }

    public final Tile getTile(int arg6, int arg7, int arg8) {
        Tile v7;
        URL v6 = this.getTileUrl(arg6, arg7, arg8);
        if(v6 == null) {
            return UrlTileProvider.NO_TILE;
        }

        try {
            arg8 = this.width;
            int v0 = this.height;
            InputStream v6_1 = v6.openStream();
            ByteArrayOutputStream v1 = new ByteArrayOutputStream();
            byte[] v2 = new byte[4096];
            while(true) {
                int v3 = v6_1.read(v2);
                if(v3 == -1) {
                    break;
                }

                ((OutputStream)v1).write(v2, 0, v3);
            }

            v7 = new Tile(arg8, v0, v1.toByteArray());
        }
        catch(IOException ) {
            v7 = null;
        }

        return v7;
    }

    public abstract URL getTileUrl(int arg1, int arg2, int arg3);
}

