package org.telegram.messenger;

import java.math.BigInteger;
import org.telegram.tgnet.TLRPC$TL_inputCheckPasswordSRP;
import org.telegram.tgnet.TLRPC$TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow;

public class SRPHelper {
    public SRPHelper() {
        super();
    }

    public static byte[] getBigIntegerBytes(BigInteger arg5) {
        byte[] v0;
        byte[] v5 = arg5.toByteArray();
        int v2 = 256;
        if(v5.length > v2) {
            v0 = new byte[v2];
            System.arraycopy(v5, 1, v0, 0, v2);
            return v0;
        }

        if(v5.length < v2) {
            v0 = new byte[v2];
            System.arraycopy(v5, 0, v0, 256 - v5.length, v5.length);
            int v3;
            for(v3 = 0; v3 < 256 - v5.length; ++v3) {
                v0[v3] = 0;
            }

            return v0;
        }

        return v5;
    }

    public static BigInteger getV(byte[] arg4, TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow arg5) {
        BigInteger v0 = BigInteger.valueOf(((long)arg5.g));
        SRPHelper.getBigIntegerBytes(v0);
        return v0.modPow(new BigInteger(1, SRPHelper.getX(arg4, arg5)), new BigInteger(1, arg5.p));
    }

    public static byte[] getVBytes(byte[] arg2, TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow arg3) {
        if(!Utilities.isGoodPrime(arg3.p, arg3.g)) {
            return null;
        }

        return SRPHelper.getBigIntegerBytes(SRPHelper.getV(arg2, arg3));
    }

    public static byte[] getX(byte[] arg6, TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow arg7) {
        return Utilities.computeSHA256(new byte[][]{arg7.salt2, Utilities.computePBKDF2(Utilities.computeSHA256(new byte[][]{arg7.salt2, Utilities.computeSHA256(new byte[][]{arg7.salt1, arg6, arg7.salt1}), arg7.salt2}), arg7.salt1), arg7.salt2});
    }

    public static TL_inputCheckPasswordSRP startCheck(byte[] arg16, long arg17, byte[] arg19, TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow arg20) {
        byte[] v0 = arg16;
        byte[] v1 = arg19;
        TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow v2 = arg20;
        TL_inputCheckPasswordSRP v3 = null;
        if(v0 != null && v1 != null && v1.length != 0) {
            if(!Utilities.isGoodPrime(v2.p, v2.g)) {
            }
            else {
                BigInteger v4 = BigInteger.valueOf(((long)v2.g));
                byte[] v5 = SRPHelper.getBigIntegerBytes(v4);
                BigInteger v6 = new BigInteger(1, v2.p);
                int v7 = 2;
                byte[][] v9 = new byte[v7][];
                v9[0] = v2.p;
                v9[1] = v5;
                BigInteger v10 = new BigInteger(1, Utilities.computeSHA256(v9));
                BigInteger v9_1 = new BigInteger(1, v0);
                v0 = new byte[256];
                Utilities.random.nextBytes(v0);
                BigInteger v12 = new BigInteger(1, v0);
                v0 = SRPHelper.getBigIntegerBytes(v4.modPow(v12, v6));
                BigInteger v13 = new BigInteger(1, v1);
                if(v13.compareTo(BigInteger.ZERO) > 0) {
                    if(v13.compareTo(v6) >= 0) {
                    }
                    else {
                        v1 = SRPHelper.getBigIntegerBytes(v13);
                        byte[][] v14 = new byte[v7][];
                        v14[0] = v0;
                        v14[1] = v1;
                        BigInteger v15 = new BigInteger(1, Utilities.computeSHA256(v14));
                        if(v15.compareTo(BigInteger.ZERO) == 0) {
                            return v3;
                        }
                        else {
                            v4 = v13.subtract(v10.multiply(v4.modPow(v9_1, v6)).mod(v6));
                            if(v4.compareTo(BigInteger.ZERO) < 0) {
                                v4 = v4.add(v6);
                            }

                            if(!Utilities.isGoodGaAndGb(v4, v6)) {
                                return v3;
                            }

                            byte[] v3_1 = Utilities.computeSHA256(SRPHelper.getBigIntegerBytes(v4.modPow(v12.add(v15.multiply(v9_1)), v6)));
                            byte[] v4_1 = Utilities.computeSHA256(v2.p);
                            v5 = Utilities.computeSHA256(v5);
                            int v6_1;
                            for(v6_1 = 0; v6_1 < v4_1.length; ++v6_1) {
                                v4_1[v6_1] = ((byte)(v5[v6_1] ^ v4_1[v6_1]));
                            }

                            TL_inputCheckPasswordSRP v5_1 = new TL_inputCheckPasswordSRP();
                            byte[][] v6_2 = new byte[6][];
                            v6_2[0] = v4_1;
                            v6_2[1] = Utilities.computeSHA256(v2.salt1);
                            v6_2[v7] = Utilities.computeSHA256(v2.salt2);
                            v6_2[3] = v0;
                            v6_2[4] = v1;
                            v6_2[5] = v3_1;
                            v5_1.M1 = Utilities.computeSHA256(v6_2);
                            v5_1.A = v0;
                            v5_1.srp_id = arg17;
                            return v5_1;
                        }
                    }
                }
            }
        }

        return v3;
    }
}

