package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.EOFException;

public final class Id3Peeker {
    private final ParsableByteArray scratch;

    public Id3Peeker() {
        super();
        this.scratch = new ParsableByteArray(10);
    }

    public Metadata peekId3Data(ExtractorInput arg8, FramePredicate arg9) {
        int v4;
        Metadata v1 = null;
        int v2 = 0;
        try {
            while(true) {
            label_3:
                v4 = 10;
                arg8.peekFully(this.scratch.data, 0, v4);
                break;
            }
        }
        catch(EOFException ) {
            goto label_33;
        }

        this.scratch.setPosition(0);
        if(this.scratch.readUnsignedInt24() == Id3Decoder.ID3_TAG) {
            this.scratch.skipBytes(3);
            int v3 = this.scratch.readSynchSafeInt();
            int v5 = v3 + 10;
            if(v1 == null) {
                byte[] v1_1 = new byte[v5];
                System.arraycopy(this.scratch.data, 0, v1_1, 0, v4);
                arg8.peekFully(v1_1, v4, v3);
                v1 = new Id3Decoder(arg9).decode(v1_1, v5);
            }
            else {
                arg8.advancePeekPosition(v3);
            }

            v2 += v5;
            goto label_3;
        }

    label_33:
        arg8.resetPeekPosition();
        arg8.advancePeekPosition(v2);
        return v1;
    }
}

