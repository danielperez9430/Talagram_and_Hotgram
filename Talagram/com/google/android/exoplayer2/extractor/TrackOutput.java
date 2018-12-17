package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

public interface TrackOutput {
    public final class CryptoData {
        public final int clearBlocks;
        public final int cryptoMode;
        public final int encryptedBlocks;
        public final byte[] encryptionKey;

        public CryptoData(int arg1, byte[] arg2, int arg3, int arg4) {
            super();
            this.cryptoMode = arg1;
            this.encryptionKey = arg2;
            this.encryptedBlocks = arg3;
            this.clearBlocks = arg4;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((CryptoData)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.cryptoMode != ((CryptoData)arg5).cryptoMode || this.encryptedBlocks != ((CryptoData)arg5).encryptedBlocks || this.clearBlocks != ((CryptoData)arg5).clearBlocks || !Arrays.equals(this.encryptionKey, ((CryptoData)arg5).encryptionKey)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return ((this.cryptoMode * 31 + Arrays.hashCode(this.encryptionKey)) * 31 + this.encryptedBlocks) * 31 + this.clearBlocks;
        }
    }

    void format(Format arg1);

    int sampleData(ExtractorInput arg1, int arg2, boolean arg3);

    void sampleData(ParsableByteArray arg1, int arg2);

    void sampleMetadata(long arg1, int arg2, int arg3, int arg4, CryptoData arg5);
}

