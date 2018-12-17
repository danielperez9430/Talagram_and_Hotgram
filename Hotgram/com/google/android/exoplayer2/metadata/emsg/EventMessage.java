package com.google.android.exoplayer2.metadata.emsg;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class EventMessage implements Entry {
    final class com.google.android.exoplayer2.metadata.emsg.EventMessage$1 implements Parcelable$Creator {
        com.google.android.exoplayer2.metadata.emsg.EventMessage$1() {
            super();
        }

        public EventMessage createFromParcel(Parcel arg2) {
            return new EventMessage(arg2);
        }

        public Object createFromParcel(Parcel arg1) {
            return this.createFromParcel(arg1);
        }

        public EventMessage[] newArray(int arg1) {
            return new EventMessage[arg1];
        }

        public Object[] newArray(int arg1) {
            return this.newArray(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    public final long durationMs;
    private int hashCode;
    public final long id;
    public final byte[] messageData;
    public final long presentationTimeUs;
    public final String schemeIdUri;
    public final String value;

    static {
        EventMessage.CREATOR = new com.google.android.exoplayer2.metadata.emsg.EventMessage$1();
    }

    EventMessage(Parcel arg3) {
        super();
        this.schemeIdUri = Util.castNonNull(arg3.readString());
        this.value = Util.castNonNull(arg3.readString());
        this.presentationTimeUs = arg3.readLong();
        this.durationMs = arg3.readLong();
        this.id = arg3.readLong();
        this.messageData = Util.castNonNull(arg3.createByteArray());
    }

    public EventMessage(String arg1, String arg2, long arg3, long arg5, byte[] arg7, long arg8) {
        super();
        this.schemeIdUri = arg1;
        this.value = arg2;
        this.durationMs = arg3;
        this.id = arg5;
        this.messageData = arg7;
        this.presentationTimeUs = arg8;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if(this == (((EventMessage)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else {
                if(this.presentationTimeUs != ((EventMessage)arg8).presentationTimeUs || this.durationMs != ((EventMessage)arg8).durationMs || this.id != ((EventMessage)arg8).id || !Util.areEqual(this.schemeIdUri, ((EventMessage)arg8).schemeIdUri) || !Util.areEqual(this.value, ((EventMessage)arg8).value) || !Arrays.equals(this.messageData, ((EventMessage)arg8).messageData)) {
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
        if(this.hashCode == 0) {
            int v0 = 527;
            int v2 = 0;
            int v1 = this.schemeIdUri != null ? this.schemeIdUri.hashCode() : 0;
            v0 = (v0 + v1) * 31;
            if(this.value != null) {
                v2 = this.value.hashCode();
            }

            this.hashCode = ((((v0 + v2) * 31 + (((int)(this.presentationTimeUs ^ this.presentationTimeUs >>> 32)))) * 31 + (((int)(this.durationMs ^ this.durationMs >>> 32)))) * 31 + (((int)(this.id ^ this.id >>> 32)))) * 31 + Arrays.hashCode(this.messageData);
        }

        return this.hashCode;
    }

    public String toString() {
        return "EMSG: scheme=" + this.schemeIdUri + ", id=" + this.id + ", value=" + this.value;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeString(this.schemeIdUri);
        arg3.writeString(this.value);
        arg3.writeLong(this.presentationTimeUs);
        arg3.writeLong(this.durationMs);
        arg3.writeLong(this.id);
        arg3.writeByteArray(this.messageData);
    }
}

