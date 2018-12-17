package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.cea.Cea608Decoder;
import com.google.android.exoplayer2.text.cea.Cea708Decoder;
import com.google.android.exoplayer2.text.dvb.DvbDecoder;
import com.google.android.exoplayer2.text.pgs.PgsDecoder;
import com.google.android.exoplayer2.text.ssa.SsaDecoder;
import com.google.android.exoplayer2.text.subrip.SubripDecoder;
import com.google.android.exoplayer2.text.ttml.TtmlDecoder;
import com.google.android.exoplayer2.text.tx3g.Tx3gDecoder;
import com.google.android.exoplayer2.text.webvtt.Mp4WebvttDecoder;
import com.google.android.exoplayer2.text.webvtt.WebvttDecoder;

public interface SubtitleDecoderFactory {
    final class com.google.android.exoplayer2.text.SubtitleDecoderFactory$1 implements SubtitleDecoderFactory {
        com.google.android.exoplayer2.text.SubtitleDecoderFactory$1() {
            super();
        }

        public SubtitleDecoder createDecoder(Format arg3) {
            int v0_1;
            String v0 = arg3.sampleMimeType;
            switch(v0.hashCode()) {
                case -1248334819: {
                    if(!v0.equals("application/pgs")) {
                        goto label_59;
                    }

                    v0_1 = 10;
                    break;
                }
                case 691401887: {
                    if(!v0.equals("application/x-quicktime-tx3g")) {
                        goto label_59;
                    }

                    v0_1 = 5;
                    break;
                }
                case 1566015601: {
                    if(!v0.equals("application/cea-608")) {
                        goto label_59;
                    }

                    v0_1 = 6;
                    break;
                }
                case 1693976202: {
                    if(!v0.equals("application/ttml+xml")) {
                        goto label_59;
                    }

                    v0_1 = 3;
                    break;
                }
                case -1351681404: {
                    if(!v0.equals("application/dvbsubs")) {
                        goto label_59;
                    }

                    v0_1 = 9;
                    break;
                }
                case -1004728940: {
                    if(!v0.equals("text/vtt")) {
                        goto label_59;
                    }

                    v0_1 = 0;
                    break;
                }
                case 930165504: {
                    if(!v0.equals("application/x-mp4-cea-608")) {
                        goto label_59;
                    }

                    v0_1 = 7;
                    break;
                }
                case 1668750253: {
                    if(!v0.equals("application/x-subrip")) {
                        goto label_59;
                    }

                    v0_1 = 4;
                    break;
                }
                case -1026075066: {
                    if(!v0.equals("application/x-mp4-vtt")) {
                        goto label_59;
                    }

                    v0_1 = 2;
                    break;
                }
                case 822864842: {
                    if(!v0.equals("text/x-ssa")) {
                        goto label_59;
                    }

                    v0_1 = 1;
                    break;
                }
                case 1566016562: {
                    if(v0.equals("application/cea-708")) {
                        v0_1 = 8;
                        goto label_60;
                    }

                label_59:
                    v0_1 = -1;
                    break;
                }
                default: {
                    goto label_59;
                }
            }

        label_60:
            switch(v0_1) {
                case 0: {
                    goto label_99;
                }
                case 1: {
                    goto label_95;
                }
                case 2: {
                    goto label_92;
                }
                case 3: {
                    goto label_89;
                }
                case 4: {
                    goto label_86;
                }
                case 5: {
                    goto label_82;
                }
                case 6: 
                case 7: {
                    goto label_77;
                }
                case 8: {
                    goto label_72;
                }
                case 9: {
                    goto label_68;
                }
                case 10: {
                    goto label_65;
                }
            }

            throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
        label_65:
            return new PgsDecoder();
        label_82:
            return new Tx3gDecoder(arg3.initializationData);
        label_99:
            return new WebvttDecoder();
        label_68:
            return new DvbDecoder(arg3.initializationData);
        label_86:
            return new SubripDecoder();
        label_72:
            return new Cea708Decoder(arg3.accessibilityChannel, arg3.initializationData);
        label_89:
            return new TtmlDecoder();
        label_92:
            return new Mp4WebvttDecoder();
        label_77:
            return new Cea608Decoder(arg3.sampleMimeType, arg3.accessibilityChannel);
        label_95:
            return new SsaDecoder(arg3.initializationData);
        }

        public boolean supportsFormat(Format arg2) {
            String v2 = arg2.sampleMimeType;
            boolean v2_1 = ("text/vtt".equals(v2)) || ("text/x-ssa".equals(v2)) || ("application/ttml+xml".equals(v2)) || ("application/x-mp4-vtt".equals(v2)) || ("application/x-subrip".equals(v2)) || ("application/x-quicktime-tx3g".equals(v2)) || ("application/cea-608".equals(v2)) || ("application/x-mp4-cea-608".equals(v2)) || ("application/cea-708".equals(v2)) || ("application/dvbsubs".equals(v2)) || ("application/pgs".equals(v2)) ? true : false;
            return v2_1;
        }
    }

    public static final SubtitleDecoderFactory DEFAULT;

    static {
        SubtitleDecoderFactory.DEFAULT = new com.google.android.exoplayer2.text.SubtitleDecoderFactory$1();
    }

    SubtitleDecoder createDecoder(Format arg1);

    boolean supportsFormat(Format arg1);
}

