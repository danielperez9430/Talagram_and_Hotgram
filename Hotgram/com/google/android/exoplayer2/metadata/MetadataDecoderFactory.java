package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.scte35.SpliceInfoDecoder;

public interface MetadataDecoderFactory {
    final class com.google.android.exoplayer2.metadata.MetadataDecoderFactory$1 implements MetadataDecoderFactory {
        com.google.android.exoplayer2.metadata.MetadataDecoderFactory$1() {
            super();
        }

        public MetadataDecoder createDecoder(Format arg3) {
            int v3_1;
            String v3 = arg3.sampleMimeType;
            int v0 = v3.hashCode();
            if(v0 != -1248341703) {
                if(v0 != 1154383568) {
                    if(v0 != 1652648887) {
                        goto label_24;
                    }
                    else if(v3.equals("application/x-scte35")) {
                        v3_1 = 2;
                    }
                    else {
                        goto label_24;
                    }
                }
                else if(v3.equals("application/x-emsg")) {
                    v3_1 = 1;
                }
                else {
                    goto label_24;
                }
            }
            else if(v3.equals("application/id3")) {
                v3_1 = 0;
            }
            else {
            label_24:
                v3_1 = -1;
            }

            switch(v3_1) {
                case 0: {
                    goto label_36;
                }
                case 1: {
                    goto label_33;
                }
                case 2: {
                    goto label_30;
                }
            }

            throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
        label_33:
            return new EventMessageDecoder();
        label_36:
            return new Id3Decoder();
        label_30:
            return new SpliceInfoDecoder();
        }

        public boolean supportsFormat(Format arg2) {
            String v2 = arg2.sampleMimeType;
            boolean v2_1 = ("application/id3".equals(v2)) || ("application/x-emsg".equals(v2)) || ("application/x-scte35".equals(v2)) ? true : false;
            return v2_1;
        }
    }

    public static final MetadataDecoderFactory DEFAULT;

    static {
        MetadataDecoderFactory.DEFAULT = new com.google.android.exoplayer2.metadata.MetadataDecoderFactory$1();
    }

    MetadataDecoder createDecoder(Format arg1);

    boolean supportsFormat(Format arg1);
}

