package org.telegram.messenger.audioinfo.mp3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.telegram.messenger.audioinfo.util.PositionInputStream;

public class ID3v2TagHeader {
    private boolean compression;
    private int footerSize;
    private int headerSize;
    private int paddingSize;
    private int revision;
    private int totalTagSize;
    private boolean unsynchronization;
    private int version;

    public ID3v2TagHeader(InputStream arg2) {
        this(new PositionInputStream(arg2));
    }

    ID3v2TagHeader(PositionInputStream arg12) {
        int v0_2;
        StringBuilder v0_1;
        super();
        boolean v0 = false;
        this.version = 0;
        this.revision = 0;
        this.headerSize = 0;
        this.totalTagSize = 0;
        this.paddingSize = 0;
        this.footerSize = 0;
        long v1 = arg12.getPosition();
        ID3v2DataInput v3 = new ID3v2DataInput(((InputStream)arg12));
        int v5 = 3;
        String v4 = new String(v3.readFully(v5), "ISO-8859-1");
        if("ID3".equals(v4)) {
            this.version = v3.readByte();
            int v6 = 2;
            int v7 = 4;
            if(this.version != v6 && this.version != v5) {
                if(this.version == v7) {
                }
                else {
                    v0_1 = new StringBuilder();
                    v0_1.append("Unsupported ID3v2 version: ");
                    v0_1.append(this.version);
                    throw new ID3v2Exception(v0_1.toString());
                }
            }

            this.revision = v3.readByte();
            int v4_1 = v3.readByte();
            int v9 = 10;
            this.totalTagSize = v3.readSyncsafeInt() + v9;
            if(this.version == v6) {
                boolean v3_1 = (v4_1 & 128) != 0 ? true : false;
                this.unsynchronization = v3_1;
                if((v4_1 & 64) != 0) {
                    v0 = true;
                }

                this.compression = v0;
            }
            else {
                if((v4_1 & 128) != 0) {
                    v0 = true;
                }

                this.unsynchronization = v0;
                if((v4_1 & 64) != 0) {
                    if(this.version == v5) {
                        v0_2 = v3.readInt();
                        v3.readByte();
                        v3.readByte();
                        this.paddingSize = v3.readInt();
                        v0_2 += -6;
                    }
                    else {
                        v0_2 = v3.readSyncsafeInt() - v7;
                    }

                    v3.skipFully(((long)v0_2));
                }

                if(this.version < v7) {
                    goto label_89;
                }

                if((v4_1 & 16) == 0) {
                    goto label_89;
                }

                this.footerSize = v9;
                this.totalTagSize += v9;
            }

        label_89:
            this.headerSize = ((int)(arg12.getPosition() - v1));
            return;
        }

        v0_1 = new StringBuilder();
        v0_1.append("Invalid ID3 identifier: ");
        v0_1.append(v4);
        throw new ID3v2Exception(v0_1.toString());
    }

    public int getFooterSize() {
        return this.footerSize;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public int getPaddingSize() {
        return this.paddingSize;
    }

    public int getRevision() {
        return this.revision;
    }

    public int getTotalTagSize() {
        return this.totalTagSize;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isCompression() {
        return this.compression;
    }

    public boolean isUnsynchronization() {
        return this.unsynchronization;
    }

    public ID3v2TagBody tagBody(InputStream arg9) {
        if(!this.compression) {
            if(this.version < 4 && (this.unsynchronization)) {
                byte[] v0 = new ID3v2DataInput(arg9).readFully(this.totalTagSize - this.headerSize);
                int v1 = -1;
                int v2 = v0.length;
                int v4 = 0;
                int v5 = 0;
                int v6 = 0;
                while(v4 < v2) {
                    byte v7 = v0[v4];
                    if(v5 == 0 || v7 != 0) {
                        v0[v6] = v7;
                        ++v6;
                    }

                    v5 = v7 == v1 ? 1 : 0;
                    ++v4;
                }

                return new ID3v2TagBody(new ByteArrayInputStream(v0, 0, v6), ((long)this.headerSize), v6, this);
            }

            return new ID3v2TagBody(arg9, ((long)this.headerSize), this.totalTagSize - this.headerSize - this.footerSize, this);
        }

        throw new ID3v2Exception("Tag compression is not supported");
    }

    public String toString() {
        return String.format("%s[version=%s, totalTagSize=%d]", this.getClass().getSimpleName(), Integer.valueOf(this.version), Integer.valueOf(this.totalTagSize));
    }
}

