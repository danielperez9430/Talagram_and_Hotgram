package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import javax.annotation.Nullable;

@Class(creator="ConnectionEventCreator") public final class ConnectionEvent extends StatsEvent {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getTimeMillis", id=2) private final long zzxv;
    @Field(getter="getEventType", id=12) private int zzxw;
    @Field(getter="getCallingProcess", id=4) private final String zzxx;
    @Field(getter="getCallingService", id=5) private final String zzxy;
    @Field(getter="getTargetProcess", id=6) private final String zzxz;
    @Field(getter="getTargetService", id=7) private final String zzya;
    @Field(getter="getStackTrace", id=8) private final String zzyb;
    @Field(getter="getEventKey", id=13) private final String zzyc;
    @Field(getter="getElapsedRealtime", id=10) private final long zzyd;
    @Field(getter="getHeapAlloc", id=11) private final long zzye;
    private long zzyf;

    static {
        ConnectionEvent.CREATOR = new ConnectionEventCreator();
    }

    @Constructor ConnectionEvent(@Param(id=1) int arg1, @Param(id=2) long arg2, @Param(id=12) int arg4, @Param(id=4) String arg5, @Param(id=5) String arg6, @Param(id=6) String arg7, @Param(id=7) String arg8, @Param(id=8) String arg9, @Param(id=13) String arg10, @Param(id=10) long arg11, @Param(id=11) long arg13) {
        super();
        this.zzal = arg1;
        this.zzxv = arg2;
        this.zzxw = arg4;
        this.zzxx = arg5;
        this.zzxy = arg6;
        this.zzxz = arg7;
        this.zzya = arg8;
        this.zzyf = -1;
        this.zzyb = arg9;
        this.zzyc = arg10;
        this.zzyd = arg11;
        this.zzye = arg13;
    }

    public ConnectionEvent(long arg16, int arg18, String arg19, String arg20, String arg21, String arg22, String arg23, String arg24, long arg25, long arg27) {
        this(1, arg16, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg27);
    }

    public ConnectionEvent(ConnectionEvent arg16) {
        this(arg16.zzal, ((StatsEvent)arg16).getTimeMillis(), ((StatsEvent)arg16).getEventType(), arg16.getCallingProcess(), arg16.getCallingService(), arg16.getTargetProcess(), arg16.getTargetService(), arg16.getStackTrace(), ((StatsEvent)arg16).getEventKey(), ((StatsEvent)arg16).getElapsedRealtime(), arg16.getHeapAlloc());
    }

    public final StatsEvent ReconstructCloseEvent(StatsEvent arg6) {
        if((arg6 instanceof ConnectionEvent)) {
            arg6 = new ConnectionEvent(((ConnectionEvent)arg6)).setEventType(((StatsEvent)this).getEventType()).setDurationMillis(((StatsEvent)this).getElapsedRealtime() - arg6.getElapsedRealtime());
        }

        return arg6;
    }

    public static boolean checkEventType(StatsEvent arg3) {
        if(2 != arg3.getEventType() && 3 != arg3.getEventType() && 4 != arg3.getEventType() && 1 != arg3.getEventType() && 6 != arg3.getEventType() && 13 != arg3.getEventType() && 14 != arg3.getEventType()) {
            if(15 == arg3.getEventType()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final String getCallingProcess() {
        return this.zzxx;
    }

    public final String getCallingService() {
        return this.zzxy;
    }

    public final long getDurationMillis() {
        return this.zzyf;
    }

    public final long getElapsedRealtime() {
        return this.zzyd;
    }

    public final String getEventKey() {
        return this.zzyc;
    }

    public final int getEventType() {
        return this.zzxw;
    }

    public final long getHeapAlloc() {
        return this.zzye;
    }

    public final String getSpecificString() {
        String v0 = this.getCallingProcess();
        String v1 = this.getCallingService();
        String v2 = this.getTargetProcess();
        String v3 = this.getTargetService();
        String v4 = this.zzyb == null ? "" : this.zzyb;
        long v5 = this.getHeapAlloc();
        StringBuilder v8 = new StringBuilder(String.valueOf(v0).length() + 26 + String.valueOf(v1).length() + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v4).length());
        v8.append("\t");
        v8.append(v0);
        v8.append("/");
        v8.append(v1);
        v8.append("\t");
        v8.append(v2);
        v8.append("/");
        v8.append(v3);
        v8.append("\t");
        v8.append(v4);
        v8.append("\t");
        v8.append(v5);
        return v8.toString();
    }

    @Nullable public final String getStackTrace() {
        return this.zzyb;
    }

    public final String getTargetProcess() {
        return this.zzxz;
    }

    public final String getTargetService() {
        return this.zzya;
    }

    public final long getTimeMillis() {
        return this.zzxv;
    }

    public final long getTimeout() {
        return 0;
    }

    public final ConnectionEvent markTimeOut() {
        this.zzxw = 6;
        return this;
    }

    public final StatsEvent markTimeOut() {
        return this.markTimeOut();
    }

    public final ConnectionEvent setDurationMillis(long arg1) {
        this.zzyf = arg1;
        return this;
    }

    public final StatsEvent setDurationMillis(long arg1) {
        return this.setDurationMillis(arg1);
    }

    public final ConnectionEvent setEventType(int arg1) {
        this.zzxw = arg1;
        return this;
    }

    public final StatsEvent setEventType(int arg1) {
        return this.setEventType(arg1);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeLong(arg5, 2, ((StatsEvent)this).getTimeMillis());
        SafeParcelWriter.writeString(arg5, 4, this.getCallingProcess(), false);
        SafeParcelWriter.writeString(arg5, 5, this.getCallingService(), false);
        SafeParcelWriter.writeString(arg5, 6, this.getTargetProcess(), false);
        SafeParcelWriter.writeString(arg5, 7, this.getTargetService(), false);
        SafeParcelWriter.writeString(arg5, 8, this.getStackTrace(), false);
        SafeParcelWriter.writeLong(arg5, 10, ((StatsEvent)this).getElapsedRealtime());
        SafeParcelWriter.writeLong(arg5, 11, this.getHeapAlloc());
        SafeParcelWriter.writeInt(arg5, 12, ((StatsEvent)this).getEventType());
        SafeParcelWriter.writeString(arg5, 13, ((StatsEvent)this).getEventKey(), false);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }
}

