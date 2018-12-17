package com.google.android.exoplayer2.extractor.ts;

import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class Id3Reader implements ElementaryStreamReader {
    private static final int ID3_HEADER_SIZE = 10;
    private static final String TAG = "Id3Reader";
    private final ParsableByteArray id3Header;
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private long sampleTimeUs;
    private boolean writingSample;

    public Id3Reader() {
        super();
        this.id3Header = new ParsableByteArray(10);
    }

    public void consume(ParsableByteArray arg8) {
        if(!this.writingSample) {
            return;
        }

        int v0 = arg8.bytesLeft();
        int v2 = 10;
        if(this.sampleBytesRead < v2) {
            int v1 = Math.min(v0, 10 - this.sampleBytesRead);
            System.arraycopy(arg8.data, arg8.getPosition(), this.id3Header.data, this.sampleBytesRead, v1);
            if(this.sampleBytesRead + v1 == v2) {
                this.id3Header.setPosition(0);
                if(73 == this.id3Header.readUnsignedByte() && 68 == this.id3Header.readUnsignedByte()) {
                    if(51 != this.id3Header.readUnsignedByte()) {
                    }
                    else {
                        this.id3Header.skipBytes(3);
                        this.sampleSize = this.id3Header.readSynchSafeInt() + v2;
                        goto label_48;
                    }
                }

                Log.w("Id3Reader", "Discarding invalid ID3 tag");
                this.writingSample = false;
                return;
            }
        }

    label_48:
        v0 = Math.min(v0, this.sampleSize - this.sampleBytesRead);
        this.output.sampleData(arg8, v0);
        this.sampleBytesRead += v0;
    }

    public void createTracks(ExtractorOutput arg4, TrackIdGenerator arg5) {
        arg5.generateNewId();
        this.output = arg4.track(arg5.getTrackId(), 4);
        this.output.format(Format.createSampleFormat(arg5.getFormatId(), "application/id3", null, -1, null));
    }

    public void packetFinished() {
        if((this.writingSample) && this.sampleSize != 0) {
            if(this.sampleBytesRead != this.sampleSize) {
            }
            else {
                this.output.sampleMetadata(this.sampleTimeUs, 1, this.sampleSize, 0, null);
                this.writingSample = false;
            }
        }
    }

    public void packetStarted(long arg1, boolean arg3) {
        if(!arg3) {
            return;
        }

        this.writingSample = true;
        this.sampleTimeUs = arg1;
        this.sampleSize = 0;
        this.sampleBytesRead = 0;
    }

    public void seek() {
        this.writingSample = false;
    }
}

