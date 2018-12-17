package com.google.android.gms.maps.model;

public interface TileProvider {
    public static final Tile NO_TILE;

    static {
        TileProvider.NO_TILE = new Tile(-1, -1, null);
    }

    Tile getTile(int arg1, int arg2, int arg3);
}

