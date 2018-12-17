package org.telegram.messenger.audioinfo.mp3;

public class ID3v2FrameHeader {
    private int bodySize;
    private boolean compression;
    private int dataLengthIndicator;
    private boolean encryption;
    private String frameId;
    private int headerSize;
    private boolean unsynchronization;

    public ID3v2FrameHeader(ID3v2TagBody arg14) {
        int v2_1;
        int v12;
        int v8;
        int v3_1;
        super();
        long v0 = arg14.getPosition();
        ID3v2DataInput v2 = arg14.getData();
        int v4 = 4;
        int v5 = 3;
        int v6 = 2;
        String v3 = arg14.getTagHeader().getVersion() == v6 ? new String(v2.readFully(v5), "ISO-8859-1") : new String(v2.readFully(v4), "ISO-8859-1");
        this.frameId = v3;
        int v7 = 8;
        if(arg14.getTagHeader().getVersion() == v6) {
            v3_1 = (v2.readByte() & 255) << 16 | (v2.readByte() & 255) << v7 | v2.readByte() & 255;
        }
        else if(arg14.getTagHeader().getVersion() == v5) {
            v3_1 = v2.readInt();
        }
        else {
            v3_1 = v2.readSyncsafeInt();
        }

        this.bodySize = v3_1;
        if(arg14.getTagHeader().getVersion() > v6) {
            v2.readByte();
            v3_1 = v2.readByte();
            int v9 = 64;
            boolean v10 = false;
            if(arg14.getTagHeader().getVersion() == v5) {
                v7 = 128;
                v6 = 0;
                v8 = 32;
                v12 = 0;
            }
            else {
                v8 = 64;
                v9 = 4;
                v12 = 1;
            }

            boolean v7_1 = (v7 & v3_1) != 0 ? true : false;
            this.compression = v7_1;
            boolean v6_1 = (v6 & v3_1) != 0 ? true : false;
            this.unsynchronization = v6_1;
            if((v3_1 & v9) != 0) {
                v10 = true;
            }

            this.encryption = v10;
            if(arg14.getTagHeader().getVersion() == v5) {
                if(this.compression) {
                    this.dataLengthIndicator = v2.readInt();
                    this.bodySize -= v4;
                }

                if(this.encryption) {
                    v2.readByte();
                    --this.bodySize;
                }

                if((v3_1 & v8) == 0) {
                    goto label_121;
                }

                v2.readByte();
                v2_1 = this.bodySize - 1;
            }
            else {
                if((v3_1 & v8) != 0) {
                    v2.readByte();
                    --this.bodySize;
                }

                if(this.encryption) {
                    v2.readByte();
                    --this.bodySize;
                }

                if((v3_1 & v12) == 0) {
                    goto label_121;
                }

                this.dataLengthIndicator = v2.readSyncsafeInt();
                v2_1 = this.bodySize - v4;
            }

            this.bodySize = v2_1;
        }

    label_121:
        this.headerSize = ((int)(arg14.getPosition() - v0));
    }

    public int getBodySize() {
        return this.bodySize;
    }

    public int getDataLengthIndicator() {
        return this.dataLengthIndicator;
    }

    public String getFrameId() {
        return this.frameId;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public boolean isCompression() {
        return this.compression;
    }

    public boolean isEncryption() {
        return this.encryption;
    }

    public boolean isPadding() {
        boolean v0 = false;
        int v1;
        for(v1 = 0; v1 < this.frameId.length(); ++v1) {
            if(this.frameId.charAt(0) != 0) {
                return 0;
            }
        }

        if(this.bodySize == 0) {
            v0 = true;
        }

        return v0;
    }

    public boolean isUnsynchronization() {
        return this.unsynchronization;
    }

    public boolean isValid() {
        boolean v0 = false;
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= this.frameId.length()) {
                goto label_25;
            }

            if(this.frameId.charAt(v1) < 65 || this.frameId.charAt(v1) > 90) {
                if(this.frameId.charAt(v1) >= 48) {
                    if(this.frameId.charAt(v1) > 57) {
                    }
                    else {
                        goto label_22;
                    }
                }

                return 0;
            }

        label_22:
        }

        return 0;
    label_25:
        if(this.bodySize > 0) {
            v0 = true;
        }

        return v0;
    }

    public String toString() {
        return String.format("%s[id=%s, bodysize=%d]", this.getClass().getSimpleName(), this.frameId, Integer.valueOf(this.bodySize));
    }
}

