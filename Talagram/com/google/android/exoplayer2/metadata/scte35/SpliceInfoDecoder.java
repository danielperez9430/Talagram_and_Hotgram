package com.google.android.exoplayer2.metadata.scte35;

import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.nio.ByteBuffer;

public final class SpliceInfoDecoder implements MetadataDecoder {
    private static final int TYPE_PRIVATE_COMMAND = 255;
    private static final int TYPE_SPLICE_INSERT = 5;
    private static final int TYPE_SPLICE_NULL = 0;
    private static final int TYPE_SPLICE_SCHEDULE = 4;
    private static final int TYPE_TIME_SIGNAL = 6;
    private final ParsableByteArray sectionData;
    private final ParsableBitArray sectionHeader;
    private TimestampAdjuster timestampAdjuster;

    public SpliceInfoDecoder() {
        super();
        this.sectionData = new ParsableByteArray();
        this.sectionHeader = new ParsableBitArray();
    }

    public Metadata decode(MetadataInputBuffer arg8) {
        SpliceScheduleCommand v4_2;
        if(this.timestampAdjuster == null || arg8.subsampleOffsetUs != this.timestampAdjuster.getTimestampOffsetUs()) {
            this.timestampAdjuster = new TimestampAdjuster(arg8.timeUs);
            this.timestampAdjuster.adjustSampleTimestamp(arg8.timeUs - arg8.subsampleOffsetUs);
        }

        ByteBuffer v8 = arg8.data;
        byte[] v0 = v8.array();
        int v8_1 = v8.limit();
        this.sectionData.reset(v0, v8_1);
        this.sectionHeader.reset(v0, v8_1);
        this.sectionHeader.skipBits(39);
        long v1 = (((long)this.sectionHeader.readBits(1))) << 32 | (((long)this.sectionHeader.readBits(32)));
        this.sectionHeader.skipBits(20);
        v8_1 = this.sectionHeader.readBits(12);
        int v3 = this.sectionHeader.readBits(8);
        TimeSignalCommand v4 = null;
        this.sectionData.skipBytes(14);
        if(v3 == 0) {
            SpliceNullCommand v4_4 = new SpliceNullCommand();
        }
        else if(v3 != 255) {
            switch(v3) {
                case 4: {
                    goto label_61;
                }
                case 5: {
                    goto label_57;
                }
                case 6: {
                    goto label_53;
                }
            }

            goto label_69;
        label_53:
            v4 = TimeSignalCommand.parseFromSection(this.sectionData, v1, this.timestampAdjuster);
            goto label_69;
        label_57:
            SpliceInsertCommand v4_1 = SpliceInsertCommand.parseFromSection(this.sectionData, v1, this.timestampAdjuster);
            goto label_69;
        label_61:
            v4_2 = SpliceScheduleCommand.parseFromSection(this.sectionData);
        }
        else {
            PrivateCommand v4_3 = PrivateCommand.parseFromSection(this.sectionData, v8_1, v1);
        }

    label_69:
        Metadata v0_1 = (((TimeSignalCommand)v4_2)) == null ? new Metadata(new Entry[0]) : new Metadata(new Entry[]{((TimeSignalCommand)v4_2)});
        return v0_1;
    }
}

