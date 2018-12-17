package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Arrays;
import java.util.Comparator;

@Class(creator="FlagCreator") @Reserved(value={1}) public final class zzi extends AbstractSafeParcelable implements Comparable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final String name;
    @Field(id=3) private final long zzab;
    @Field(id=4) private final boolean zzac;
    @Field(id=5) private final double zzad;
    @Field(id=6) private final String zzae;
    @Field(id=7) private final byte[] zzaf;
    @Field(id=8) private final int zzag;
    @Field(id=9) public final int zzah;
    private static final Comparator zzai;

    static {
        zzi.CREATOR = new zzk();
        zzi.zzai = new zzj();
    }

    @Constructor public zzi(@Param(id=2) String arg1, @Param(id=3) long arg2, @Param(id=4) boolean arg4, @Param(id=5) double arg5, @Param(id=6) String arg7, @Param(id=7) byte[] arg8, @Param(id=8) int arg9, @Param(id=9) int arg10) {
        super();
        this.name = arg1;
        this.zzab = arg2;
        this.zzac = arg4;
        this.zzad = arg5;
        this.zzae = arg7;
        this.zzaf = arg8;
        this.zzag = arg9;
        this.zzah = arg10;
    }

    private static int compare(int arg0, int arg1) {
        if(arg0 < arg1) {
            return -1;
        }

        if(arg0 == arg1) {
            return 0;
        }

        return 1;
    }

    public final int compareTo(Object arg9) {
        int v0 = this.name.compareTo(((zzi)arg9).name);
        if(v0 != 0) {
            return v0;
        }

        v0 = zzi.compare(this.zzag, ((zzi)arg9).zzag);
        if(v0 != 0) {
            return v0;
        }

        int v1 = -1;
        int v3 = 0;
        switch(this.zzag) {
            case 1: {
                goto label_78;
            }
            case 2: {
                goto label_71;
            }
            case 3: {
                goto label_67;
            }
            case 4: {
                goto label_57;
            }
            case 5: {
                goto label_26;
            }
        }

        v0 = this.zzag;
        StringBuilder v2 = new StringBuilder(31);
        v2.append("Invalid enum value: ");
        v2.append(v0);
        throw new AssertionError(v2.toString());
    label_67:
        return Double.compare(this.zzad, ((zzi)arg9).zzad);
    label_71:
        boolean v0_1 = this.zzac;
        if(v0_1 == ((zzi)arg9).zzac) {
            return 0;
        }

        if(v0_1) {
            return 1;
        }

        return v1;
    label_57:
        String v0_2 = this.zzae;
        String v9 = ((zzi)arg9).zzae;
        if(v0_2 == v9) {
            return 0;
        }

        if(v0_2 == null) {
            return v1;
        }

        if(v9 == null) {
            return 1;
        }

        return v0_2.compareTo(v9);
    label_26:
        if(this.zzaf == ((zzi)arg9).zzaf) {
            return 0;
        }

        if(this.zzaf == null) {
            return v1;
        }

        if(((zzi)arg9).zzaf == null) {
            return 1;
        }

        while(v3 < Math.min(this.zzaf.length, ((zzi)arg9).zzaf.length)) {
            v0 = this.zzaf[v3] - ((zzi)arg9).zzaf[v3];
            if(v0 != 0) {
                return v0;
            }

            ++v3;
        }

        return zzi.compare(this.zzaf.length, ((zzi)arg9).zzaf.length);
    label_78:
        long v4 = this.zzab;
        long v6 = ((zzi)arg9).zzab;
        if(v4 < v6) {
            return v1;
        }

        if(v4 == v6) {
            return 0;
        }

        return 1;
    }

    public final boolean equals(Object arg8) {
        if(((arg8 instanceof zzi)) && (zzn.equals(this.name, ((zzi)arg8).name)) && this.zzag == ((zzi)arg8).zzag) {
            if(this.zzah != ((zzi)arg8).zzah) {
            }
            else {
                switch(this.zzag) {
                    case 1: {
                        goto label_46;
                    }
                    case 2: {
                        goto label_41;
                    }
                    case 3: {
                        goto label_36;
                    }
                    case 4: {
                        goto label_32;
                    }
                    case 5: {
                        goto label_28;
                    }
                }

                int v0 = this.zzag;
                StringBuilder v2 = new StringBuilder(31);
                v2.append("Invalid enum value: ");
                v2.append(v0);
                throw new AssertionError(v2.toString());
            label_36:
                if(this.zzad == ((zzi)arg8).zzad) {
                    return 1;
                }
                else {
                    return 0;
                label_41:
                    if(this.zzac == ((zzi)arg8).zzac) {
                        return 1;
                    }
                    else {
                        return 0;
                    label_28:
                        return Arrays.equals(this.zzaf, ((zzi)arg8).zzaf);
                    label_46:
                        if(this.zzab == ((zzi)arg8).zzab) {
                            return 1;
                        label_32:
                            return zzn.equals(this.zzae, ((zzi)arg8).zzae);
                        }
                    }
                }
            }
        }

        return 0;
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("Flag(");
        v0.append(this.name);
        v0.append(", ");
        switch(this.zzag) {
            case 1: {
                goto label_50;
            }
            case 2: {
                goto label_47;
            }
            case 3: {
                goto label_44;
            }
            case 4: {
                goto label_37;
            }
            case 5: {
                goto label_27;
            }
        }

        String v1 = this.name;
        int v2 = this.zzag;
        StringBuilder v4 = new StringBuilder(String.valueOf(v1).length() + 27);
        v4.append("Invalid type: ");
        v4.append(v1);
        v4.append(", ");
        v4.append(v2);
        throw new AssertionError(v4.toString());
    label_50:
        v0.append(this.zzab);
        goto label_52;
    label_37:
        v0.append("\'");
        v1 = this.zzae;
        goto label_40;
    label_27:
        if(this.zzaf == null) {
            v1 = "null";
        }
        else {
            v0.append("\'");
            v1 = Base64.encodeToString(this.zzaf, 3);
        label_40:
            v0.append(v1);
            v1 = "\'";
        }

        v0.append(v1);
        goto label_52;
    label_44:
        v0.append(this.zzad);
        goto label_52;
    label_47:
        v0.append(this.zzac);
    label_52:
        v0.append(", ");
        v0.append(this.zzag);
        v0.append(", ");
        v0.append(this.zzah);
        v0.append(")");
        return v0.toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.name, false);
        SafeParcelWriter.writeLong(arg5, 3, this.zzab);
        SafeParcelWriter.writeBoolean(arg5, 4, this.zzac);
        SafeParcelWriter.writeDouble(arg5, 5, this.zzad);
        SafeParcelWriter.writeString(arg5, 6, this.zzae, false);
        SafeParcelWriter.writeByteArray(arg5, 7, this.zzaf, false);
        SafeParcelWriter.writeInt(arg5, 8, this.zzag);
        SafeParcelWriter.writeInt(arg5, 9, this.zzah);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }
}

