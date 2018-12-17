package com.coremedia.iso.boxes;

public interface FullBox extends Box {
    int getFlags();

    int getVersion();

    void setFlags(int arg1);

    void setVersion(int arg1);
}

