package org.telegram.messenger.audioinfo.mp3;

public class MP3Frame {
    final class CRC16 {
        private short crc;

        CRC16() {
            super();
            this.crc = -1;
        }

        public short getValue() {
            return this.crc;
        }

        public void reset() {
            this.crc = -1;
        }

        public void update(byte arg2) {
            this.update(arg2, 8);
        }

        public void update(int arg5, int arg6) {
            arg6 = 1 << arg6 - 1;
            do {
                int v2 = 0;
                int v1 = (this.crc & 32768) == 0 ? 1 : 0;
                if((arg5 & arg6) == 0) {
                    v2 = 1;
                }

                if((v1 ^ v2) != 0) {
                    this.crc = ((short)(this.crc << 1));
                    v1 = this.crc ^ 32773;
                }
                else {
                    v1 = this.crc << 1;
                }

                this.crc = ((short)v1);
                arg6 >>>= 1;
            }
            while(arg6 != 0);
        }
    }

    public class Header {
        private static final int[][] BITRATES = null;
        private static final int[][] BITRATES_COLUMN = null;
        private static final int[][] FREQUENCIES = null;
        private static final int MPEG_BITRATE_FREE = 0;
        private static final int MPEG_BITRATE_RESERVED = 15;
        public static final int MPEG_CHANNEL_MODE_MONO = 3;
        private static final int MPEG_FRQUENCY_RESERVED = 3;
        public static final int MPEG_LAYER_1 = 3;
        public static final int MPEG_LAYER_2 = 2;
        public static final int MPEG_LAYER_3 = 1;
        private static final int MPEG_LAYER_RESERVED = 0;
        public static final int MPEG_PROTECTION_CRC = 0;
        public static final int MPEG_VERSION_1 = 3;
        public static final int MPEG_VERSION_2 = 2;
        public static final int MPEG_VERSION_2_5 = 0;
        private static final int MPEG_VERSION_RESERVED = 1;
        private static final int[][] SIDE_INFO_SIZES;
        private static final int[][] SIZE_COEFFICIENTS;
        private static final int[] SLOT_SIZES;
        private final int bitrate;
        private final int channelMode;
        private final int frequency;
        private final int layer;
        private final int padding;
        private final int protection;
        private final int version;

        static {
            Header.FREQUENCIES = new int[][]{new int[]{11025, -1, 22050, 44100}, new int[]{12000, -1, 24000, 48000}, new int[]{8000, -1, 16000, 32000}, new int[]{-1, -1, -1, -1}};
            Header.BITRATES = new int[][]{new int[]{0, 0, 0, 0, 0}, new int[]{32000, 32000, 32000, 32000, 8000}, new int[]{64000, 48000, 40000, 48000, 16000}, new int[]{96000, 56000, 48000, 56000, 24000}, new int[]{128000, 64000, 56000, 64000, 32000}, new int[]{160000, 80000, 64000, 80000, 40000}, new int[]{192000, 96000, 80000, 96000, 48000}, new int[]{224000, 112000, 96000, 112000, 56000}, new int[]{256000, 128000, 112000, 128000, 64000}, new int[]{288000, 160000, 128000, 144000, 80000}, new int[]{320000, 192000, 160000, 160000, 96000}, new int[]{352000, 224000, 192000, 176000, 112000}, new int[]{384000, 256000, 224000, 192000, 128000}, new int[]{416000, 320000, 256000, 224000, 144000}, new int[]{448000, 384000, 320000, 256000, 160000}, new int[]{-1, -1, -1, -1, -1}};
            Header.BITRATES_COLUMN = new int[][]{new int[]{-1, 4, 4, 3}, new int[]{-1, -1, -1, -1}, new int[]{-1, 4, 4, 3}, new int[]{-1, 2, 1, 0}};
            Header.SIZE_COEFFICIENTS = new int[][]{new int[]{-1, 72, 144, 12}, new int[]{-1, -1, -1, -1}, new int[]{-1, 72, 144, 12}, new int[]{-1, 144, 144, 12}};
            Header.SLOT_SIZES = new int[]{-1, 1, 1, 4};
            Header.SIDE_INFO_SIZES = new int[][]{new int[]{17, -1, 17, 32}, new int[]{17, -1, 17, 32}, new int[]{17, -1, 17, 32}, new int[]{9, -1, 9, 17}};
        }

        public Header(int arg5, int arg6, int arg7) {
            super();
            int v1 = 3;
            this.version = arg5 >> 3 & v1;
            if(this.version != 1) {
                this.layer = arg5 >> 1 & v1;
                if(this.layer != 0) {
                    this.bitrate = arg6 >> 4 & 15;
                    if(this.bitrate != 15) {
                        if(this.bitrate != 0) {
                            this.frequency = arg6 >> 2 & v1;
                            if(this.frequency != v1) {
                                int v0 = 6;
                                this.channelMode = arg7 >> v0 & v1;
                                this.padding = arg6 >> 1 & 1;
                                this.protection = arg5 & 1;
                                if(this.protection == 0) {
                                }
                                else {
                                    v0 = 4;
                                }

                                if(this.layer == 1) {
                                    v0 += this.getSideInfoSize();
                                }

                                if(this.getFrameSize() >= v0) {
                                    return;
                                }

                                StringBuilder v6 = new StringBuilder();
                                v6.append("Frame size must be at least ");
                                v6.append(v0);
                                throw new MP3Exception(v6.toString());
                            }

                            throw new MP3Exception("Reserved frequency");
                        }

                        throw new MP3Exception("Free bitrate");
                    }

                    throw new MP3Exception("Reserved bitrate");
                }

                throw new MP3Exception("Reserved layer");
            }

            throw new MP3Exception("Reserved version");
        }

        public int getBitrate() {
            return Header.BITRATES[this.bitrate][Header.BITRATES_COLUMN[this.version][this.layer]];
        }

        public int getChannelMode() {
            return this.channelMode;
        }

        public int getDuration() {
            return ((int)this.getTotalDuration(((long)this.getFrameSize())));
        }

        public int getFrameSize() {
            return (Header.SIZE_COEFFICIENTS[this.version][this.layer] * this.getBitrate() / this.getFrequency() + this.padding) * Header.SLOT_SIZES[this.layer];
        }

        public int getFrequency() {
            return Header.FREQUENCIES[this.frequency][this.version];
        }

        public int getLayer() {
            return this.layer;
        }

        public int getProtection() {
            return this.protection;
        }

        public int getSampleCount() {
            if(this.layer == 3) {
                return 384;
            }

            return 1152;
        }

        public int getSideInfoSize() {
            return Header.SIDE_INFO_SIZES[this.channelMode][this.version];
        }

        public long getTotalDuration(long arg3) {
            long v0 = (((long)this.getSampleCount())) * arg3 * 1000 / (((long)(this.getFrameSize() * this.getFrequency())));
            int v4 = 3;
            if(this.getVersion() != v4 && this.getChannelMode() == v4) {
                v0 /= 2;
            }

            return v0;
        }

        public int getVBRIOffset() {
            return 36;
        }

        public int getVersion() {
            return this.version;
        }

        public int getXingOffset() {
            return this.getSideInfoSize() + 4;
        }

        public boolean isCompatible(Header arg3) {
            boolean v3 = this.layer != arg3.layer || this.version != arg3.version || this.frequency != arg3.frequency || this.channelMode != arg3.channelMode ? false : true;
            return v3;
        }
    }

    private final byte[] bytes;
    private final Header header;

    MP3Frame(Header arg1, byte[] arg2) {
        super();
        this.header = arg1;
        this.bytes = arg2;
    }

    public Header getHeader() {
        return this.header;
    }

    public int getNumberOfFrames() {
        int v1;
        int v0;
        if(this.isXingFrame()) {
            v0 = this.header.getXingOffset();
            if((this.bytes[v0 + 7] & 1) != 0) {
                v1 = (this.bytes[v0 + 8] & 255) << 24 | (this.bytes[v0 + 9] & 255) << 16 | (this.bytes[v0 + 10] & 255) << 8;
                v0 = this.bytes[v0 + 11];
                goto label_29;
            }
        }
        else if(this.isVBRIFrame()) {
            v0 = this.header.getVBRIOffset();
            v1 = (this.bytes[v0 + 14] & 255) << 24 | (this.bytes[v0 + 15] & 255) << 16 | (this.bytes[v0 + 16] & 255) << 8;
            v0 = this.bytes[v0 + 17];
        label_29:
            return v0 & 255 | v1;
        }

        return -1;
    }

    public int getSize() {
        return this.bytes.length;
    }

    boolean isChecksumError() {
        boolean v1 = false;
        if(this.header.getProtection() == 0 && this.header.getLayer() == 1) {
            CRC16 v0 = new CRC16();
            v0.update(this.bytes[2]);
            v0.update(this.bytes[3]);
            int v3 = this.header.getSideInfoSize();
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                v0.update(this.bytes[v4 + 6]);
            }

            if(((this.bytes[4] & 255) << 8 | this.bytes[5] & 255) != v0.getValue()) {
                v1 = true;
            }
        }

        return v1;
    }

    boolean isVBRIFrame() {
        int v0 = this.header.getVBRIOffset();
        boolean v3 = false;
        if(this.bytes.length < v0 + 26) {
            return 0;
        }

        if(this.bytes[v0] == 86 && this.bytes[v0 + 1] == 66 && this.bytes[v0 + 2] == 82 && this.bytes[v0 + 3] == 73) {
            v3 = true;
        }

        return v3;
    }

    boolean isXingFrame() {
        int v0 = this.header.getXingOffset();
        if(this.bytes.length < v0 + 12) {
            return 0;
        }

        if(v0 >= 0) {
            if(this.bytes.length < v0 + 8) {
            }
            else {
                int v4 = 110;
                if(this.bytes[v0] == 88 && this.bytes[v0 + 1] == 105 && this.bytes[v0 + 2] == v4 && this.bytes[v0 + 3] == 103) {
                    return 1;
                }

                if(this.bytes[v0] != 73) {
                    return 0;
                }

                if(this.bytes[v0 + 1] != v4) {
                    return 0;
                }

                if(this.bytes[v0 + 2] != 102) {
                    return 0;
                }

                if(this.bytes[v0 + 3] != 111) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }
}

