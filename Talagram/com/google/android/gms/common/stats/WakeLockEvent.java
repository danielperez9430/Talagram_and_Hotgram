package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import java.util.List;
import javax.annotation.Nullable;

@Class(creator="WakeLockEventCreator") public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getTimeout", id=16) private final long mTimeout;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getTimeMillis", id=2) private final long zzxv;
    @Field(getter="getEventType", id=11) private int zzxw;
    @Field(getter="getElapsedRealtime", id=8) private final long zzyd;
    private long zzyf;
    @Field(getter="getWakeLockName", id=4) private final String zzyq;
    @Field(getter="getSecondaryWakeLockName", id=10) private final String zzyr;
    @Field(getter="getCodePackage", id=17) private final String zzys;
    @Field(getter="getWakeLockType", id=5) private final int zzyt;
    @Field(getter="getCallingPackages", id=6) private final List zzyu;
    @Field(getter="getEventKey", id=12) private final String zzyv;
    @Field(getter="getDeviceState", id=14) private int zzyw;
    @Field(getter="getHostPackage", id=13) private final String zzyx;
    @Field(getter="getBeginPowerPercentage", id=15) private final float zzyy;

    static {
        WakeLockEvent.CREATOR = new WakeLockEventCreator();
    }

    @Constructor WakeLockEvent(@Param(id=1) int arg4, @Param(id=2) long arg5, @Param(id=11) int arg7, @Param(id=4) String arg8, @Param(id=5) int arg9, @Param(id=6) List arg10, @Param(id=12) String arg11, @Param(id=8) long arg12, @Param(id=14) int arg14, @Param(id=10) String arg15, @Param(id=13) String arg16, @Param(id=15) float arg17, @Param(id=16) long arg18, @Param(id=17) String arg20) {
        super();
        this.zzal = arg4;
        this.zzxv = arg5;
        this.zzxw = arg7;
        this.zzyq = arg8;
        this.zzyr = arg15;
        this.zzys = arg20;
        this.zzyt = arg9;
        this.zzyf = -1;
        this.zzyu = arg10;
        this.zzyv = arg11;
        this.zzyd = arg12;
        this.zzyw = arg14;
        this.zzyx = arg16;
        this.zzyy = arg17;
        this.mTimeout = arg18;
    }

    public WakeLockEvent(long arg19, int arg21, String arg22, int arg23, List arg24, String arg25, long arg26, int arg28, String arg29, String arg30, float arg31, long arg32, String arg34) {
        this(2, arg19, arg21, arg22, arg23, arg24, arg25, arg26, arg28, arg29, arg30, arg31, arg32, arg34);
    }

    public WakeLockEvent(WakeLockEvent arg19) {
        this(arg19.zzal, ((StatsEvent)arg19).getTimeMillis(), ((StatsEvent)arg19).getEventType(), arg19.getWakeLockName(), arg19.getWakeLockType(), arg19.getCallingPackages(), ((StatsEvent)arg19).getEventKey(), ((StatsEvent)arg19).getElapsedRealtime(), arg19.getDeviceState(), arg19.getSecondaryWakeLockName(), arg19.getHostPackage(), arg19.getBeginPowerPercentage(), ((StatsEvent)arg19).getTimeout(), arg19.getCodePackage());
    }

    public final StatsEvent ReconstructCloseEvent(StatsEvent arg6) {
        if((arg6 instanceof WakeLockEvent)) {
            arg6 = new WakeLockEvent(((WakeLockEvent)arg6)).setEventType(((StatsEvent)this).getEventType()).setDurationMillis(((StatsEvent)this).getElapsedRealtime() - arg6.getElapsedRealtime());
        }

        return arg6;
    }

    public static boolean checkEventType(StatsEvent arg2) {
        if(7 != arg2.getEventType() && 8 != arg2.getEventType() && 9 != arg2.getEventType() && 10 != arg2.getEventType() && 11 != arg2.getEventType()) {
            if(12 == arg2.getEventType()) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final float getBeginPowerPercentage() {
        return this.zzyy;
    }

    @Nullable public final List getCallingPackages() {
        return this.zzyu;
    }

    public final String getCodePackage() {
        return this.zzys;
    }

    public final int getDeviceState() {
        return this.zzyw;
    }

    public final long getDurationMillis() {
        return this.zzyf;
    }

    public final long getElapsedRealtime() {
        return this.zzyd;
    }

    public final String getEventKey() {
        return this.zzyv;
    }

    public final int getEventType() {
        return this.zzxw;
    }

    public final String getHostPackage() {
        return this.zzyx;
    }

    public final String getSecondaryWakeLockName() {
        return this.zzyr;
    }

    public final String getSpecificString() {
        String v0 = this.getWakeLockName();
        int v1 = this.getWakeLockType();
        String v2 = this.getCallingPackages() == null ? "" : TextUtils.join(",", this.getCallingPackages());
        int v3 = this.getDeviceState();
        String v4 = this.getSecondaryWakeLockName() == null ? "" : this.getSecondaryWakeLockName();
        String v5 = this.getHostPackage() == null ? "" : this.getHostPackage();
        float v6 = this.getBeginPowerPercentage();
        String v7 = this.getCodePackage() == null ? "" : this.getCodePackage();
        StringBuilder v9 = new StringBuilder(String.valueOf(v0).length() + 45 + String.valueOf(v2).length() + String.valueOf(v4).length() + String.valueOf(v5).length() + String.valueOf(v7).length());
        v9.append("\t");
        v9.append(v0);
        v9.append("\t");
        v9.append(v1);
        v9.append("\t");
        v9.append(v2);
        v9.append("\t");
        v9.append(v3);
        v9.append("\t");
        v9.append(v4);
        v9.append("\t");
        v9.append(v5);
        v9.append("\t");
        v9.append(v6);
        v9.append("\t");
        v9.append(v7);
        return v9.toString();
    }

    public final long getTimeMillis() {
        return this.zzxv;
    }

    public final long getTimeout() {
        return this.mTimeout;
    }

    public final String getWakeLockName() {
        return this.zzyq;
    }

    public final int getWakeLockType() {
        return this.zzyt;
    }

    public final StatsEvent markTimeOut() {
        return this.markTimeOut();
    }

    public final WakeLockEvent markTimeOut() {
        int v0;
        if(this.mTimeout != 0) {
            this.zzyf = this.mTimeout;
        }

        if(7 == this.zzxw) {
            v0 = 9;
            goto label_9;
        }
        else if(10 == this.zzxw) {
            v0 = 12;
        label_9:
            this.zzxw = v0;
        }

        return this;
    }

    public final StatsEvent setDurationMillis(long arg1) {
        this.zzyf = arg1;
        return this;
    }

    public final StatsEvent setEventType(int arg1) {
        this.zzxw = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeLong(arg5, 2, ((StatsEvent)this).getTimeMillis());
        SafeParcelWriter.writeString(arg5, 4, this.getWakeLockName(), false);
        SafeParcelWriter.writeInt(arg5, 5, this.getWakeLockType());
        SafeParcelWriter.writeStringList(arg5, 6, this.getCallingPackages(), false);
        SafeParcelWriter.writeLong(arg5, 8, ((StatsEvent)this).getElapsedRealtime());
        SafeParcelWriter.writeString(arg5, 10, this.getSecondaryWakeLockName(), false);
        SafeParcelWriter.writeInt(arg5, 11, ((StatsEvent)this).getEventType());
        SafeParcelWriter.writeString(arg5, 12, ((StatsEvent)this).getEventKey(), false);
        SafeParcelWriter.writeString(arg5, 13, this.getHostPackage(), false);
        SafeParcelWriter.writeInt(arg5, 14, this.getDeviceState());
        SafeParcelWriter.writeFloat(arg5, 15, this.getBeginPowerPercentage());
        SafeParcelWriter.writeLong(arg5, 16, ((StatsEvent)this).getTimeout());
        SafeParcelWriter.writeString(arg5, 17, this.getCodePackage(), false);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }
}

