package com.coremedia.iso.boxes.fragment;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;

public class SampleFlags {
    private byte is_leading;
    private byte reserved;
    private int sampleDegradationPriority;
    private byte sampleDependsOn;
    private byte sampleHasRedundancy;
    private byte sampleIsDependedOn;
    private boolean sampleIsDifferenceSample;
    private byte samplePaddingValue;

    public SampleFlags() {
        super();
    }

    public SampleFlags(ByteBuffer arg7) {
        super();
        long v0 = IsoTypeReader.readUInt32(arg7);
        this.reserved = ((byte)(((int)((-268435456 & v0) >> 28))));
        this.is_leading = ((byte)(((int)((201326592 & v0) >> 26))));
        this.sampleDependsOn = ((byte)(((int)((50331648 & v0) >> 24))));
        this.sampleIsDependedOn = ((byte)(((int)((12582912 & v0) >> 22))));
        this.sampleHasRedundancy = ((byte)(((int)((3145728 & v0) >> 20))));
        this.samplePaddingValue = ((byte)(((int)((917504 & v0) >> 17))));
        boolean v7 = (65536 & v0) >> 16 > 0 ? true : false;
        this.sampleIsDifferenceSample = v7;
        this.sampleDegradationPriority = ((int)(v0 & 65535));
    }

    public boolean equals(Object arg5) {
        if(this == (((SampleFlags)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.is_leading != ((SampleFlags)arg5).is_leading) {
                return 0;
            }
            else if(this.reserved != ((SampleFlags)arg5).reserved) {
                return 0;
            }
            else if(this.sampleDegradationPriority != ((SampleFlags)arg5).sampleDegradationPriority) {
                return 0;
            }
            else if(this.sampleDependsOn != ((SampleFlags)arg5).sampleDependsOn) {
                return 0;
            }
            else if(this.sampleHasRedundancy != ((SampleFlags)arg5).sampleHasRedundancy) {
                return 0;
            }
            else if(this.sampleIsDependedOn != ((SampleFlags)arg5).sampleIsDependedOn) {
                return 0;
            }
            else if(this.sampleIsDifferenceSample != ((SampleFlags)arg5).sampleIsDifferenceSample) {
                return 0;
            }
            else if(this.samplePaddingValue != ((SampleFlags)arg5).samplePaddingValue) {
                return 0;
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    public void getContent(ByteBuffer arg5) {
        IsoTypeWriter.writeUInt32(arg5, (((long)(this.reserved << 28))) | 0 | (((long)(this.is_leading << 26))) | (((long)(this.sampleDependsOn << 24))) | (((long)(this.sampleIsDependedOn << 22))) | (((long)(this.sampleHasRedundancy << 20))) | (((long)(this.samplePaddingValue << 17))) | (((long)(this.sampleIsDifferenceSample << 16))) | (((long)this.sampleDegradationPriority)));
    }

    public int getReserved() {
        return this.reserved;
    }

    public int getSampleDegradationPriority() {
        return this.sampleDegradationPriority;
    }

    public int getSampleDependsOn() {
        return this.sampleDependsOn;
    }

    public int getSampleHasRedundancy() {
        return this.sampleHasRedundancy;
    }

    public int getSampleIsDependedOn() {
        return this.sampleIsDependedOn;
    }

    public int getSamplePaddingValue() {
        return this.samplePaddingValue;
    }

    public int hashCode() {
        return ((((((this.reserved * 31 + this.is_leading) * 31 + this.sampleDependsOn) * 31 + this.sampleIsDependedOn) * 31 + this.sampleHasRedundancy) * 31 + this.samplePaddingValue) * 31 + this.sampleIsDifferenceSample) * 31 + this.sampleDegradationPriority;
    }

    public boolean isSampleIsDifferenceSample() {
        return this.sampleIsDifferenceSample;
    }

    public void setReserved(int arg1) {
        this.reserved = ((byte)arg1);
    }

    public void setSampleDegradationPriority(int arg1) {
        this.sampleDegradationPriority = arg1;
    }

    public void setSampleDependsOn(int arg1) {
        this.sampleDependsOn = ((byte)arg1);
    }

    public void setSampleHasRedundancy(int arg1) {
        this.sampleHasRedundancy = ((byte)arg1);
    }

    public void setSampleIsDependedOn(int arg1) {
        this.sampleIsDependedOn = ((byte)arg1);
    }

    public void setSampleIsDifferenceSample(boolean arg1) {
        this.sampleIsDifferenceSample = arg1;
    }

    public void setSamplePaddingValue(int arg1) {
        this.samplePaddingValue = ((byte)arg1);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("SampleFlags{reserved=");
        v0.append(this.reserved);
        v0.append(", isLeading=");
        v0.append(this.is_leading);
        v0.append(", depOn=");
        v0.append(this.sampleDependsOn);
        v0.append(", isDepOn=");
        v0.append(this.sampleIsDependedOn);
        v0.append(", hasRedundancy=");
        v0.append(this.sampleHasRedundancy);
        v0.append(", padValue=");
        v0.append(this.samplePaddingValue);
        v0.append(", isDiffSample=");
        v0.append(this.sampleIsDifferenceSample);
        v0.append(", degradPrio=");
        v0.append(this.sampleDegradationPriority);
        v0.append('}');
        return v0.toString();
    }
}

