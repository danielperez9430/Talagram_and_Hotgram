package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class PsshAtomUtil {
    class PsshAtom {
        private final byte[] schemeData;
        private final UUID uuid;
        private final int version;

        public PsshAtom(UUID arg1, int arg2, byte[] arg3) {
            super();
            this.uuid = arg1;
            this.version = arg2;
            this.schemeData = arg3;
        }

        static UUID access$000(PsshAtom arg0) {
            return arg0.uuid;
        }

        static int access$100(PsshAtom arg0) {
            return arg0.version;
        }

        static byte[] access$200(PsshAtom arg0) {
            return arg0.schemeData;
        }
    }

    private static final String TAG = "PsshAtomUtil";

    private PsshAtomUtil() {
        super();
    }

    public static byte[] buildPsshAtom(UUID arg1, byte[] arg2) {
        return PsshAtomUtil.buildPsshAtom(arg1, null, arg2);
    }

    public static byte[] buildPsshAtom(UUID arg5, UUID[] arg6, byte[] arg7) {
        int v0 = 0;
        int v1 = arg7 != null ? arg7.length : 0;
        v1 += 32;
        if(arg6 != null) {
            v1 += arg6.length * 16 + 4;
        }

        ByteBuffer v2 = ByteBuffer.allocate(v1);
        v2.putInt(v1);
        v2.putInt(Atom.TYPE_pssh);
        v1 = arg6 != null ? 16777216 : 0;
        v2.putInt(v1);
        v2.putLong(arg5.getMostSignificantBits());
        v2.putLong(arg5.getLeastSignificantBits());
        if(arg6 != null) {
            v2.putInt(arg6.length);
            int v5 = arg6.length;
            while(v0 < v5) {
                UUID v1_1 = arg6[v0];
                v2.putLong(v1_1.getMostSignificantBits());
                v2.putLong(v1_1.getLeastSignificantBits());
                ++v0;
            }
        }

        if(arg7 != null && arg7.length != 0) {
            v2.putInt(arg7.length);
            v2.put(arg7);
        }

        return v2.array();
    }

    private static PsshAtom parsePsshAtom(byte[] arg9) {
        ParsableByteArray v0 = new ParsableByteArray(arg9);
        PsshAtom v1 = null;
        if(v0.limit() < 32) {
            return v1;
        }

        v0.setPosition(0);
        if(v0.readInt() != v0.bytesLeft() + 4) {
            return v1;
        }

        if(v0.readInt() != Atom.TYPE_pssh) {
            return v1;
        }

        int v2 = Atom.parseFullAtomVersion(v0.readInt());
        if(v2 > 1) {
            Log.w("PsshAtomUtil", "Unsupported pssh version: " + v2);
            return v1;
        }

        UUID v4 = new UUID(v0.readLong(), v0.readLong());
        if(v2 == 1) {
            v0.skipBytes(v0.readUnsignedIntToInt() * 16);
        }

        int v3 = v0.readUnsignedIntToInt();
        if(v3 != v0.bytesLeft()) {
            return v1;
        }

        byte[] v1_1 = new byte[v3];
        v0.readBytes(v1_1, 0, v3);
        return new PsshAtom(v4, v2, v1_1);
    }

    public static byte[] parseSchemeSpecificData(byte[] arg4, UUID arg5) {
        PsshAtom v4 = PsshAtomUtil.parsePsshAtom(arg4);
        byte[] v0 = null;
        if(v4 == null) {
            return v0;
        }

        if(arg5 != null && !arg5.equals(PsshAtom.access$000(v4))) {
            Log.w("PsshAtomUtil", "UUID mismatch. Expected: " + arg5 + ", got: " + PsshAtom.access$000(v4) + ".");
            return v0;
        }

        return PsshAtom.access$200(v4);
    }

    public static UUID parseUuid(byte[] arg0) {
        PsshAtom v0 = PsshAtomUtil.parsePsshAtom(arg0);
        if(v0 == null) {
            return null;
        }

        return PsshAtom.access$000(v0);
    }

    public static int parseVersion(byte[] arg0) {
        PsshAtom v0 = PsshAtomUtil.parsePsshAtom(arg0);
        if(v0 == null) {
            return -1;
        }

        return PsshAtom.access$100(v0);
    }
}

