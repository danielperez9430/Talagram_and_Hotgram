package org.telegram.messenger;

import java.util.Locale;
import org.telegram.tgnet.TLRPC$InputEncryptedFile;
import org.telegram.tgnet.TLRPC$InputFile;

public class VideoEditedInfo {
    public int bitrate;
    public InputEncryptedFile encryptedFile;
    public long endTime;
    public long estimatedDuration;
    public long estimatedSize;
    public InputFile file;
    public int framerate;
    public byte[] iv;
    public byte[] key;
    public boolean muted;
    public int originalHeight;
    public String originalPath;
    public int originalWidth;
    public int resultHeight;
    public int resultWidth;
    public int rotationValue;
    public boolean roundVideo;
    public long startTime;

    public VideoEditedInfo() {
        super();
        this.framerate = 24;
    }

    public String getString() {
        return String.format(Locale.US, "-1_%d_%d_%d_%d_%d_%d_%d_%d_%d_%s", Long.valueOf(this.startTime), Long.valueOf(this.endTime), Integer.valueOf(this.rotationValue), Integer.valueOf(this.originalWidth), Integer.valueOf(this.originalHeight), Integer.valueOf(this.bitrate), Integer.valueOf(this.resultWidth), Integer.valueOf(this.resultHeight), Integer.valueOf(this.framerate), this.originalPath);
    }

    public boolean needConvert() {
        boolean v0;
        if(this.roundVideo) {
            if(this.roundVideo) {
                if(this.startTime > 0) {
                    goto label_16;
                }
                else if(this.endTime != -1 && this.endTime != this.estimatedDuration) {
                    goto label_16;
                }
            }

            v0 = false;
        }
        else {
        label_16:
            v0 = true;
        }

        return v0;
    }

    public boolean parseString(String arg8) {
        int v5;
        int v3;
        String[] v8_1;
        int v2 = 6;
        if(arg8.length() < v2) {
            return 0;
        }

        try {
            v8_1 = arg8.split("_");
            v3 = 10;
            if(v8_1.length >= v3) {
                this.startTime = Long.parseLong(v8_1[1]);
                this.endTime = Long.parseLong(v8_1[2]);
                this.rotationValue = Integer.parseInt(v8_1[3]);
                this.originalWidth = Integer.parseInt(v8_1[4]);
                this.originalHeight = Integer.parseInt(v8_1[5]);
                this.bitrate = Integer.parseInt(v8_1[v2]);
                this.resultWidth = Integer.parseInt(v8_1[7]);
                this.resultHeight = Integer.parseInt(v8_1[8]);
                v5 = 9;
                if(v8_1.length >= 11) {
                    goto label_45;
                }

                goto label_48;
            }

            return 1;
        }
        catch(Exception v8) {
            goto label_77;
        }

        try {
        label_45:
            this.framerate = Integer.parseInt(v8_1[v5]);
            goto label_48;
        }
        catch(Exception ) {
            try {
            label_48:
                if(this.framerate <= 0 || this.framerate > 400) {
                    this.framerate = 25;
                    v3 = 9;
                }

                while(true) {
                label_56:
                    if(v3 >= v8_1.length) {
                        return 1;
                    }

                    String v0 = this.originalPath == null ? v8_1[v3] : this.originalPath + "_" + v8_1[v3];
                    this.originalPath = v0;
                    break;
                }
            }
            catch(Exception v8) {
                goto label_77;
            }

            ++v3;
            goto label_56;
        }

    label_77:
        FileLog.e(((Throwable)v8));
        return 0;
    }
}

