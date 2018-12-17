package com.googlecode.mp4parser.authoring.tracks;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CleanInputStream extends FilterInputStream {
    int prev;
    int prevprev;

    public CleanInputStream(InputStream arg1) {
        super(arg1);
        this.prevprev = -1;
        this.prev = -1;
    }

    public boolean markSupported() {
        return 0;
    }

    public int read() {
        int v0 = super.read();
        if(v0 == 3 && this.prevprev == 0 && this.prev == 0) {
            this.prevprev = -1;
            this.prev = -1;
            v0 = super.read();
        }

        this.prevprev = this.prev;
        this.prev = v0;
        return v0;
    }

    public int read(byte[] arg5, int arg6, int arg7) {
        if(arg5 != null) {
            if(arg6 >= 0 && arg7 >= 0 && arg7 <= arg5.length - arg6) {
                if(arg7 == 0) {
                    return 0;
                }
                else {
                    int v0 = this.read();
                    int v1 = -1;
                    if(v0 == v1) {
                        return v1;
                    }
                    else {
                        arg5[arg6] = ((byte)v0);
                        v0 = 1;
                        while(true) {
                            if(v0 < arg7) {
                                try {
                                    int v2 = this.read();
                                    if(v2 == v1) {
                                    }
                                    else {
                                        arg5[arg6 + v0] = ((byte)v2);
                                        ++v0;
                                        continue;
                                    }
                                }
                                catch(IOException ) {
                                }
                            }
                            else {
                            }

                            return v0;
                        }

                        return v0;
                    }
                }
            }

            throw new IndexOutOfBoundsException();
        }

        throw new NullPointerException();
    }
}

