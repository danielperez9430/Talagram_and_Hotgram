package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public static volatile DispatchQueue globalQueue;
    protected static final char[] hexArray;
    public static Pattern pattern;
    public static volatile DispatchQueue phoneBookQueue;
    public static SecureRandom random;
    public static volatile DispatchQueue searchQueue;
    public static volatile DispatchQueue stageQueue;

    static {
        Utilities.pattern = Pattern.compile("[\\-0-9]+");
        Utilities.random = new SecureRandom();
        Utilities.stageQueue = new DispatchQueue("stageQueue");
        Utilities.globalQueue = new DispatchQueue("globalQueue");
        Utilities.searchQueue = new DispatchQueue("searchQueue");
        Utilities.phoneBookQueue = new DispatchQueue("photoBookQueue");
        Utilities.hexArray = "0123456789ABCDEF".toCharArray();
        try {
            FileInputStream v1 = new FileInputStream(new File("/dev/urandom"));
            byte[] v0_1 = new byte[1024];
            v1.read(v0_1);
            v1.close();
            Utilities.random.setSeed(v0_1);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public Utilities() {
        super();
    }

    public static String MD5(String arg6) {
        String v0 = null;
        if(arg6 == null) {
            return v0;
        }

        try {
            byte[] v6_1 = MessageDigest.getInstance("MD5").digest(AndroidUtilities.getStringBytes(arg6));
            StringBuilder v1 = new StringBuilder();
            int v2;
            for(v2 = 0; v2 < v6_1.length; ++v2) {
                v1.append(Integer.toHexString(v6_1[v2] & 255 | 256).substring(1, 3));
            }

            return v1.toString();
        }
        catch(NoSuchAlgorithmException v6) {
            FileLog.e(((Throwable)v6));
            return v0;
        }
    }

    public static native void aesCbcEncryption(ByteBuffer arg0, byte[] arg1, byte[] arg2, int arg3, int arg4, int arg5) {
    }

    private static native void aesCbcEncryptionByteArray(byte[] arg0, byte[] arg1, byte[] arg2, int arg3, int arg4, int arg5, int arg6) {
    }

    public static void aesCbcEncryptionByteArraySafe(byte[] arg7, byte[] arg8, byte[] arg9, int arg10, int arg11, int arg12, int arg13) {
        // Method was not decompiled
    }

    public static native void aesCtrDecryption(ByteBuffer arg0, byte[] arg1, byte[] arg2, int arg3, int arg4) {
    }

    public static native void aesCtrDecryptionByteArray(byte[] arg0, byte[] arg1, byte[] arg2, int arg3, int arg4, int arg5) {
    }

    private static native void aesIgeEncryption(ByteBuffer arg0, byte[] arg1, byte[] arg2, boolean arg3, int arg4, int arg5) {
    }

    public static void aesIgeEncryption(ByteBuffer arg6, byte[] arg7, byte[] arg8, boolean arg9, boolean arg10, int arg11, int arg12) {
        // Method was not decompiled
    }

    public static native int argon2(int arg0) {
    }

    public static boolean arraysEquals(byte[] arg5, int arg6, byte[] arg7, int arg8) {
        if(arg5 != null && arg7 != null && arg6 >= 0 && arg8 >= 0 && arg5.length - arg6 <= arg7.length - arg8 && arg5.length - arg6 >= 0) {
            if(arg7.length - arg8 < 0) {
            }
            else {
                int v1 = arg6;
                boolean v2 = true;
                while(v1 < arg5.length) {
                    if(arg5[v1 + arg6] != arg7[v1 + arg8]) {
                        v2 = false;
                    }

                    ++v1;
                }

                return v2;
            }
        }

        return 0;
    }

    public static native void blurBitmap(Object arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    }

    public static String bytesToHex(byte[] arg6) {
        if(arg6 == null) {
            return "";
        }

        char[] v0 = new char[arg6.length * 2];
        int v1;
        for(v1 = 0; v1 < arg6.length; ++v1) {
            int v2 = arg6[v1] & 255;
            int v3 = v1 * 2;
            v0[v3] = Utilities.hexArray[v2 >>> 4];
            v0[v3 + 1] = Utilities.hexArray[v2 & 15];
        }

        return new String(v0);
    }

    public static long bytesToLong(byte[] arg7) {
        return ((((long)arg7[7])) << 56) + (((((long)arg7[6])) & 255) << 48) + (((((long)arg7[5])) & 255) << 40) + (((((long)arg7[4])) & 255) << 32) + (((((long)arg7[3])) & 255) << 24) + (((((long)arg7[2])) & 255) << 16) + (((((long)arg7[1])) & 255) << 8) + ((((long)arg7[0])) & 255);
    }

    public static native void calcCDT(ByteBuffer arg0, int arg1, int arg2, ByteBuffer arg3) {
    }

    public static native void clearDir(String arg0, int arg1, long arg2) {
    }

    public static byte[] computePBKDF2(byte[] arg2, byte[] arg3) {
        byte[] v0 = new byte[64];
        Utilities.pbkdf2(arg2, arg3, v0, 100000);
        return v0;
    }

    public static byte[] computeSHA1(byte[] arg2) {
        return Utilities.computeSHA1(arg2, 0, arg2.length);
    }

    public static byte[] computeSHA1(ByteBuffer arg2) {
        return Utilities.computeSHA1(arg2, 0, arg2.limit());
    }

    public static byte[] computeSHA1(ByteBuffer arg3, int arg4, int arg5) {
        byte[] v4_2;
        int v0 = arg3.position();
        int v1 = arg3.limit();
        try {
            MessageDigest v2 = MessageDigest.getInstance("SHA-1");
            arg3.position(arg4);
            arg3.limit(arg5);
            v2.update(arg3);
            v4_2 = v2.digest();
        }
        catch(Throwable v4) {
        label_20:
            arg3.limit(v1);
            arg3.position(v0);
            throw v4;
        }
        catch(Exception v4_1) {
            try {
                FileLog.e(((Throwable)v4_1));
            }
            catch(Throwable v4) {
                goto label_20;
            }

            arg3.limit(v1);
            arg3.position(v0);
            return new byte[20];
        }

        arg3.limit(v1);
        arg3.position(v0);
        return v4_2;
    }

    public static byte[] computeSHA1(byte[] arg1, int arg2, int arg3) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("SHA-1");
            v0.update(arg1, arg2, arg3);
            return v0.digest();
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
            return new byte[20];
        }
    }

    public static byte[] computeSHA256(byte[] arg1, int arg2, int arg3) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("SHA-256");
            v0.update(arg1, arg2, arg3);
            return v0.digest();
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
            return new byte[32];
        }
    }

    public static byte[] computeSHA256(byte[] arg2) {
        return Utilities.computeSHA256(arg2, 0, arg2.length);
    }

    public static byte[] computeSHA256(byte[] arg3, int arg4, int arg5, ByteBuffer arg6, int arg7, int arg8) {
        int v0 = arg6.position();
        int v1 = arg6.limit();
        try {
            MessageDigest v2 = MessageDigest.getInstance("SHA-256");
            v2.update(arg3, arg4, arg5);
            arg6.position(arg7);
            arg6.limit(arg8);
            v2.update(arg6);
            arg3 = v2.digest();
        }
        catch(Throwable v3) {
        label_21:
            arg6.limit(v1);
            arg6.position(v0);
            throw v3;
        }
        catch(Exception v3_1) {
            try {
                FileLog.e(((Throwable)v3_1));
            }
            catch(Throwable v3) {
                goto label_21;
            }

            arg6.limit(v1);
            arg6.position(v0);
            return new byte[32];
        }

        arg6.limit(v1);
        arg6.position(v0);
        return arg3;
    }

    public static byte[] computeSHA256(byte[][] arg5) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("SHA-256");
            int v2;
            for(v2 = 0; v2 < arg5.length; ++v2) {
                v0.update(arg5[v2], 0, arg5[v2].length);
            }

            return v0.digest();
        }
        catch(Exception v5) {
            FileLog.e(((Throwable)v5));
            return new byte[32];
        }
    }

    public static byte[] computeSHA512(byte[] arg3) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("SHA-512");
            v0.update(arg3, 0, arg3.length);
            return v0.digest();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
            return new byte[64];
        }
    }

    public static byte[] computeSHA512(byte[] arg3, byte[] arg4) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("SHA-512");
            v0.update(arg3, 0, arg3.length);
            v0.update(arg4, 0, arg4.length);
            return v0.digest();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
            return new byte[64];
        }
    }

    public static byte[] computeSHA512(byte[] arg3, byte[] arg4, byte[] arg5) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("SHA-512");
            v0.update(arg3, 0, arg3.length);
            v0.update(arg4, 0, arg4.length);
            v0.update(arg5, 0, arg5.length);
            return v0.digest();
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
            return new byte[64];
        }
    }

    public static native int convertVideoFrame(ByteBuffer arg0, ByteBuffer arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
    }

    public static native long getDirSize(String arg0, int arg1) {
    }

    public static byte[] hexToBytes(String arg7) {
        if(arg7 == null) {
            return null;
        }

        int v0 = arg7.length();
        byte[] v1 = new byte[v0 / 2];
        int v2;
        for(v2 = 0; v2 < v0; v2 += 2) {
            v1[v2 / 2] = ((byte)((Character.digit(arg7.charAt(v2), 16) << 4) + Character.digit(arg7.charAt(v2 + 1), 16)));
        }

        return v1;
    }

    public static boolean isGoodGaAndGb(BigInteger arg3, BigInteger arg4) {
        long v0 = 1;
        boolean v3 = arg3.compareTo(BigInteger.valueOf(v0)) <= 0 || arg3.compareTo(arg4.subtract(BigInteger.valueOf(v0))) >= 0 ? false : true;
        return v3;
    }

    public static boolean isGoodPrime(byte[] arg9, int arg10) {
        int v0 = 2;
        boolean v1 = false;
        if(arg10 >= v0) {
            int v2 = 7;
            if(arg10 > v2) {
            }
            else if(arg9.length == 256) {
                if(arg9[0] >= 0) {
                }
                else {
                    BigInteger v3 = new BigInteger(1, arg9);
                    if(arg10 != v0) {
                        int v5 = 3;
                        if(arg10 != v5) {
                            v0 = 5;
                            if(arg10 == v0) {
                                arg10 = v3.mod(BigInteger.valueOf(5)).intValue();
                                if(arg10 != 1 && arg10 != 4) {
                                    return 0;
                                }
                            }
                            else {
                                int v6 = 6;
                                if(arg10 == v6) {
                                    arg10 = v3.mod(BigInteger.valueOf(24)).intValue();
                                    if(arg10 != 19 && arg10 != 23) {
                                        return 0;
                                    }
                                }
                                else if(arg10 == v2) {
                                    arg10 = v3.mod(BigInteger.valueOf(7)).intValue();
                                    if(arg10 != v5 && arg10 != v0 && arg10 != v6) {
                                        return 0;
                                    }
                                }
                            }
                        }
                        else if(v3.mod(BigInteger.valueOf(3)).intValue() != v0) {
                            return 0;
                        }
                    }
                    else if(v3.mod(BigInteger.valueOf(8)).intValue() != v2) {
                        return 0;
                    }

                    if(Utilities.bytesToHex(arg9).equals("C71CAEB9C6B1C9048E6C522F70F13F73980D40238E3E21C14934D037563D930F48198A0AA7C14058229493D22530F4DBFA336F6E0AC925139543AED44CCE7C3720FD51F69458705AC68CD4FE6B6B13ABDC9746512969328454F18FAF8C595F642477FE96BB2A941D5BCD1D4AC8CC49880708FA9B378E3C4F3A9060BEE67CF9A4A4A695811051907E162753B56B0F6B410DBA74D8A84B2A14B3144E0EF1284754FD17ED950D5965B4B9DD46582DB1178D169C6BC465B0D6FF9CA3928FEF5B9AE4E418FC15E83EBEA0F87FA9FF5EED70050DED2849F47BF959D956850CE929851F0D8115F635B105EE2E4E15D04B2454BF6F4FADF034B10403119CD8E3B92FCC5B")) {
                        return 1;
                    }

                    BigInteger v9 = v3.subtract(BigInteger.valueOf(1)).divide(BigInteger.valueOf(2));
                    arg10 = 30;
                    if(!v3.isProbablePrime(arg10)) {
                        return v1;
                    }

                    if(!v9.isProbablePrime(arg10)) {
                        return v1;
                    }

                    v1 = true;
                }
            }
        }

        return v1;
    }

    public static native boolean loadWebpImage(Bitmap arg0, ByteBuffer arg1, int arg2, BitmapFactory$Options arg3, boolean arg4) {
    }

    public static Integer parseInt(String arg3) {
        if(arg3 == null) {
            return Integer.valueOf(0);
        }

        Integer v1 = Integer.valueOf(0);
        try {
            Matcher v3_1 = Utilities.pattern.matcher(((CharSequence)arg3));
            if(!v3_1.find()) {
                return v1;
            }

            v1 = Integer.valueOf(Integer.parseInt(v3_1.group(0)));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        return v1;
    }

    public static String parseIntToString(String arg1) {
        Matcher v1 = Utilities.pattern.matcher(((CharSequence)arg1));
        if(v1.find()) {
            return v1.group(0);
        }

        return null;
    }

    public static Long parseLong(String arg3) {
        long v0 = 0;
        if(arg3 == null) {
            return Long.valueOf(v0);
        }

        Long v0_1 = Long.valueOf(v0);
        try {
            Matcher v3_1 = Utilities.pattern.matcher(((CharSequence)arg3));
            if(!v3_1.find()) {
                return v0_1;
            }

            v0_1 = Long.valueOf(Long.parseLong(v3_1.group(0)));
        }
        catch(Exception v3) {
            FileLog.e(((Throwable)v3));
        }

        return v0_1;
    }

    private static native int pbkdf2(byte[] arg0, byte[] arg1, byte[] arg2, int arg3) {
    }

    public static native int pinBitmap(Bitmap arg0) {
    }

    public static native String readlink(String arg0) {
    }

    public static native void unpinBitmap(Bitmap arg0) {
    }
}

