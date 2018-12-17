package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FastParser {
    public class ParseException extends Exception {
        public ParseException(String arg1) {
            super(arg1);
        }

        public ParseException(String arg1, Throwable arg2) {
            super(arg1, arg2);
        }

        public ParseException(Throwable arg1) {
            super(arg1);
        }
    }

    interface zza {
        Object zzh(FastParser arg1, BufferedReader arg2);
    }

    private final char[] zzwq;
    private final char[] zzwr;
    private final char[] zzws;
    private final StringBuilder zzwt;
    private final StringBuilder zzwu;
    private static final char[] zzwv;
    private static final char[] zzww;
    private static final char[] zzwx;
    private static final char[] zzwy;
    private static final char[] zzwz;
    private static final char[] zzxa;
    private final Stack zzxb;
    private static final zza zzxc;
    private static final zza zzxd;
    private static final zza zzxe;
    private static final zza zzxf;
    private static final zza zzxg;
    private static final zza zzxh;
    private static final zza zzxi;
    private static final zza zzxj;

    static {
        FastParser.zzwv = new char[]{'u', 'l', 'l'};
        FastParser.zzww = new char[]{'r', 'u', 'e'};
        FastParser.zzwx = new char[]{'r', 'u', 'e', '\"'};
        FastParser.zzwy = new char[]{'a', 'l', 's', 'e'};
        FastParser.zzwz = new char[]{'a', 'l', 's', 'e', '\"'};
        FastParser.zzxa = new char[]{'\n'};
        FastParser.zzxc = new com.google.android.gms.common.server.response.zza();
        FastParser.zzxd = new zzb();
        FastParser.zzxe = new zzc();
        FastParser.zzxf = new zzd();
        FastParser.zzxg = new zze();
        FastParser.zzxh = new zzf();
        FastParser.zzxi = new zzg();
        FastParser.zzxj = new zzh();
    }

    public FastParser() {
        super();
        this.zzwq = new char[1];
        this.zzwr = new char[32];
        this.zzws = new char[1024];
        this.zzwt = new StringBuilder(32);
        this.zzwu = new StringBuilder(1024);
        this.zzxb = new Stack();
    }

    public void parse(InputStream arg5, FastJsonResponse arg6) {
        BufferedReader v0 = new BufferedReader(new InputStreamReader(arg5), 1024);
        try {
            this.zzxb.push(Integer.valueOf(0));
            char v5_2 = this.zzj(v0);
            if(v5_2 == 0) {
                goto label_56;
            }

            if(v5_2 == 91) {
                this.zzxb.push(Integer.valueOf(5));
                Map v5_3 = arg6.getFieldMappings();
                if(v5_3.size() == 1) {
                    Object v5_4 = v5_3.entrySet().iterator().next().getValue();
                    arg6.addConcreteTypeArrayInternal(((Field)v5_4), ((Field)v5_4).getOutputFieldName(), this.zza(v0, ((Field)v5_4)));
                }
                else {
                    goto label_52;
                }
            }
            else if(v5_2 == 123) {
                this.zzxb.push(Integer.valueOf(1));
                this.zza(v0, arg6);
            }
            else {
                StringBuilder v2 = new StringBuilder(19);
                v2.append("Unexpected token: ");
                v2.append(v5_2);
                throw new ParseException(v2.toString());
            }

            this.zzk(0);
        }
        catch(Throwable v5) {
            goto label_61;
        }
        catch(IOException v5_1) {
            goto label_63;
        }

        try {
            v0.close();
            return;
        }
        catch(IOException ) {
            Log.w("FastParser", "Failed to close reader while parsing.");
            return;
        }

        try {
        label_52:
            throw new ParseException("Object array response class must have a single Field");
        label_56:
            throw new ParseException("No data to parse");
        }
        catch(Throwable v5) {
        }
        catch(IOException v5_1) {
            try {
            label_63:
                throw new ParseException(((Throwable)v5_1));
            }
            catch(Throwable v5) {
            label_61:
                try {
                    v0.close();
                }
                catch(IOException ) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }

                throw v5;
            }
        }
    }

    public void parse(String arg2, FastJsonResponse arg3) {
        ByteArrayInputStream v0 = new ByteArrayInputStream(arg2.getBytes());
        try {
            this.parse(((InputStream)v0), arg3);
        }
        catch(Throwable v2) {
            try {
                v0.close();
            }
            catch(IOException ) {
                Log.w("FastParser", "Failed to close the input stream while parsing.");
            }

            throw v2;
        }

        try {
            v0.close();
            return;
        }
        catch(IOException ) {
            Log.w("FastParser", "Failed to close the input stream while parsing.");
            return;
        }
    }

    static int zza(FastParser arg0, BufferedReader arg1) {
        return arg0.zzd(arg1);
    }

    private final int zza(BufferedReader arg9, char[] arg10) {
        int v0_1;
        char v0 = this.zzj(arg9);
        if(v0 != 0) {
            int v1 = 44;
            if(v0 != v1) {
                if(v0 == 110) {
                    this.zzb(arg9, FastParser.zzwv);
                    return 0;
                }

                arg9.mark(1024);
                int v2 = -1;
                int v4 = 34;
                if(v0 == v4) {
                    v0_1 = 0;
                    v1 = 0;
                    while(true) {
                        if(v0_1 < arg10.length && arg9.read(arg10, v0_1, 1) != v2) {
                            char v6 = arg10[v0_1];
                            if(!Character.isISOControl(v6)) {
                                if(v6 == v4 && v1 == 0) {
                                    arg9.reset();
                                    arg9.skip(((long)(v0_1 + 1)));
                                    return v0_1;
                                }

                                if(v6 == 92) {
                                    v1 ^= 1;
                                }
                                else {
                                    v1 = 0;
                                }

                                ++v0_1;
                                continue;
                            }
                            else {
                                break;
                            }
                        }

                        goto label_69;
                    }

                    throw new ParseException("Unexpected control character while reading string");
                }
                else {
                    arg10[0] = v0;
                    v0_1 = 1;
                    while(true) {
                        if(v0_1 >= arg10.length) {
                            goto label_69;
                        }

                        if(arg9.read(arg10, v0_1, 1) == v2) {
                            goto label_69;
                        }

                        if(arg10[v0_1] != 125 && arg10[v0_1] != v1 && !Character.isWhitespace(arg10[v0_1])) {
                            if(arg10[v0_1] == 93) {
                            }
                            else {
                                ++v0_1;
                                continue;
                            }
                        }

                        break;
                    }

                    arg9.reset();
                    arg9.skip(((long)(v0_1 - 1)));
                    arg10[v0_1] = '\u0000';
                    return v0_1;
                }

            label_69:
                if(v0_1 == arg10.length) {
                    throw new ParseException("Absurdly long value");
                }

                throw new ParseException("Unexpected EOF");
            }

            throw new ParseException("Missing value");
        }

        throw new ParseException("Unexpected EOF");
    }

    private final String zza(BufferedReader arg5) {
        int v1 = 2;
        this.zzxb.push(Integer.valueOf(v1));
        char v0 = this.zzj(arg5);
        String v3 = null;
        if(v0 != 34) {
            if(v0 != 93) {
                if(v0 == 125) {
                    this.zzk(v1);
                    return v3;
                }

                StringBuilder v2 = new StringBuilder(19);
                v2.append("Unexpected token: ");
                v2.append(v0);
                throw new ParseException(v2.toString());
            }

            this.zzk(v1);
            this.zzk(1);
            this.zzk(5);
            return v3;
        }

        this.zzxb.push(Integer.valueOf(3));
        String v0_1 = FastParser.zzb(arg5, this.zzwr, this.zzwt, ((char[])v3));
        this.zzk(3);
        if(this.zzj(arg5) == 58) {
            return v0_1;
        }

        throw new ParseException("Expected key/value separator");
    }

    private final String zza(BufferedReader arg3, char[] arg4, StringBuilder arg5, char[] arg6) {
        int v0 = this.zzj(arg3);
        if(v0 != 34) {
            if(v0 == 110) {
                this.zzb(arg3, FastParser.zzwv);
                return null;
            }

            throw new ParseException("Expected string");
        }

        return FastParser.zzb(arg3, arg4, arg5, arg6);
    }

    private final ArrayList zza(BufferedReader arg9, Field arg10) {
        StringBuilder v10;
        ArrayList v0 = new ArrayList();
        char v1 = this.zzj(arg9);
        int v2 = 93;
        int v3 = 5;
        if(v1 == v2) {
            goto label_67;
        }

        if(v1 == 110) {
            goto label_62;
        }

        int v4 = 19;
        int v5 = 123;
        if(v1 != v5) {
            goto label_53;
        }

        Stack v1_1;
        for(v1_1 = this.zzxb; true; v1_1 = this.zzxb) {
            v1_1.push(Integer.valueOf(1));
            try {
                FastJsonResponse v1_2 = arg10.newConcreteTypeInstance();
                if(!this.zza(arg9, v1_2)) {
                    return v0;
                }

                v0.add(v1_2);
            }
            catch(IllegalAccessException v9) {
                throw new ParseException("Error instantiating inner object", ((Throwable)v9));
            }
            catch(InstantiationException v9_1) {
                throw new ParseException("Error instantiating inner object", ((Throwable)v9_1));
            }

            v1 = this.zzj(arg9);
            if(v1 != 44) {
                if(v1 == v2) {
                    this.zzk(v3);
                    return v0;
                }

                v10 = new StringBuilder(v4);
                v10.append("Unexpected token: ");
                v10.append(v1);
                throw new ParseException(v10.toString());
            }

            if(this.zzj(arg9) != v5) {
                break;
            }
        }

        throw new ParseException("Expected start of next object in array");
        return v0;
    label_53:
        v10 = new StringBuilder(v4);
        v10.append("Unexpected token: ");
        v10.append(v1);
        throw new ParseException(v10.toString());
    label_62:
        this.zzb(arg9, FastParser.zzwv);
        this.zzk(v3);
        return null;
    label_67:
        this.zzk(v3);
        return v0;
    }

    private final boolean zza(BufferedReader arg14, FastJsonResponse arg15) {
        StringBuilder v0_2;
        char v7_2;
        String v0_1;
        String v15_1;
        HashMap v5_4;
        byte[] v5_2;
        int v5;
        Object v1_1;
        Map v0 = arg15.getFieldMappings();
        String v1 = this.zza(arg14);
        if(v1 == null) {
            this.zzk(1);
            return 0;
        }

        ArrayList v4 = null;
        while(true) {
        label_8:
            if(v1 == null) {
                goto label_271;
            }

            v1_1 = v0.get(v1);
            if(v1_1 != null) {
                break;
            }

            v1 = this.zzb(arg14);
        }

        int v6 = 4;
        this.zzxb.push(Integer.valueOf(v6));
        int v7 = 123;
        int v8 = 44;
        int v9 = 125;
        int v10 = 110;
        switch(((Field)v1_1).getTypeIn()) {
            case 0: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setIntegers(((Field)v1_1), this.zza(arg14, FastParser.zzxc));
                    goto label_251;
                }

                arg15.setInteger(((Field)v1_1), this.zzd(arg14));
                break;
            }
            case 1: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setBigIntegers(((Field)v1_1), this.zza(arg14, FastParser.zzxi));
                    goto label_251;
                }

                arg15.setBigInteger(((Field)v1_1), this.zzf(arg14));
                break;
            }
            case 2: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setLongs(((Field)v1_1), this.zza(arg14, FastParser.zzxd));
                    goto label_251;
                }

                arg15.setLong(((Field)v1_1), this.zze(arg14));
                break;
            }
            case 3: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setFloats(((Field)v1_1), this.zza(arg14, FastParser.zzxe));
                }
                else {
                    arg15.setFloat(((Field)v1_1), this.zzg(arg14));
                }

                break;
            }
            case 4: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setDoubles(((Field)v1_1), this.zza(arg14, FastParser.zzxf));
                    goto label_251;
                }

                arg15.setDouble(((Field)v1_1), this.zzh(arg14));
                break;
            }
            case 5: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setBigDecimals(((Field)v1_1), this.zza(arg14, FastParser.zzxj));
                    goto label_251;
                }

                arg15.setBigDecimal(((Field)v1_1), this.zzi(arg14));
                break;
            }
            case 6: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setBooleans(((Field)v1_1), this.zza(arg14, FastParser.zzxg));
                    goto label_251;
                }
                else {
                    arg15.setBoolean(((Field)v1_1), this.zza(arg14, false));
                    goto label_251;
                label_271:
                    PostProcessor v14_2 = arg15.getPostProcessor();
                    if(v14_2 != null) {
                        v14_2.postProcess(arg15);
                    }

                    this.zzk(1);
                    return 1;
                    do {
                    label_99:
                        v7 = this.zzj(arg14);
                        if(v7 == 0) {
                            goto label_159;
                        }

                        v10 = 34;
                        if(v7 == v10) {
                            String v7_1 = FastParser.zzb(arg14, this.zzwr, this.zzwt, ((char[])v4));
                            if(this.zzj(arg14) != 58) {
                                v15_1 = "No map value found for key ";
                                v0_1 = String.valueOf(v7_1);
                                v15_1 = v0_1.length() != 0 ? v15_1.concat(v0_1) : new String(v15_1);
                                throw new ParseException(v15_1);
                            }
                            else {
                                if(this.zzj(arg14) != v10) {
                                    v15_1 = "Expected String value for key ";
                                    v0_1 = String.valueOf(v7_1);
                                    v15_1 = v0_1.length() != 0 ? v15_1.concat(v0_1) : new String(v15_1);
                                    throw new ParseException(v15_1);
                                }

                                v5_4.put(v7_1, FastParser.zzb(arg14, this.zzwr, this.zzwt, ((char[])v4)));
                                v7_2 = this.zzj(arg14);
                                if(v7_2 == v8) {
                                    continue;
                                }

                                break;
                            }
                        }
                        else if(v7 != v9) {
                            continue;
                        }

                        goto label_105;
                    }
                    while(true);

                    if(v7_2 == v9) {
                    label_105:
                        this.zzk(1);
                        goto label_147;
                    }

                    v0_2 = new StringBuilder(48);
                    v0_2.append("Unexpected character while parsing string map: ");
                    v0_2.append(v7_2);
                    throw new ParseException(v0_2.toString());
                label_159:
                    throw new ParseException("Unexpected EOF");
                }

            label_147:
                arg15.setStringMap(((Field)v1_1), ((Map)v5_4));
                break;
            }
            case 7: {
                if(((Field)v1_1).isTypeInArray()) {
                    arg15.setStrings(((Field)v1_1), this.zza(arg14, FastParser.zzxh));
                    goto label_251;
                }

                arg15.setString(((Field)v1_1), this.zzc(arg14));
                break;
            }
            case 8: {
                v5_2 = Base64Utils.decode(this.zza(arg14, this.zzws, this.zzwu, FastParser.zzxa));
            label_178:
                arg15.setDecodedBytes(((Field)v1_1), v5_2);
                break;
            }
            case 9: {
                v5_2 = Base64Utils.decodeUrlSafe(this.zza(arg14, this.zzws, this.zzwu, FastParser.zzxa));
                goto label_178;
            }
            case 10: {
                v5 = this.zzj(arg14);
                if(v5 == v10) {
                    this.zzb(arg14, FastParser.zzwv);
                    Map v5_3 = ((Map)v4);
                }
                else if(v5 == v7) {
                    this.zzxb.push(Integer.valueOf(1));
                    v5_4 = new HashMap();
                    goto label_99;
                }
                else {
                    throw new ParseException("Expected start of a map object");
                }

                goto label_147;
            }
            case 11: {
                if(((Field)v1_1).isTypeInArray()) {
                    v5 = this.zzj(arg14);
                    if(v5 == v10) {
                        this.zzb(arg14, FastParser.zzwv);
                        arg15.addConcreteTypeArrayInternal(((Field)v1_1), ((Field)v1_1).getOutputFieldName(), v4);
                        goto label_251;
                    }

                    this.zzxb.push(Integer.valueOf(5));
                    if(v5 == 91) {
                        arg15.addConcreteTypeArrayInternal(((Field)v1_1), ((Field)v1_1).getOutputFieldName(), this.zza(arg14, ((Field)v1_1)));
                        goto label_251;
                    }

                    throw new ParseException("Expected array start");
                }

                v5 = this.zzj(arg14);
                if(v5 == v10) {
                    this.zzb(arg14, FastParser.zzwv);
                    arg15.addConcreteTypeInternal(((Field)v1_1), ((Field)v1_1).getOutputFieldName(), ((FastJsonResponse)v4));
                    goto label_251;
                }

                this.zzxb.push(Integer.valueOf(1));
                if(v5 == v7) {
                    try {
                        FastJsonResponse v5_1 = ((Field)v1_1).newConcreteTypeInstance();
                        this.zza(arg14, v5_1);
                        arg15.addConcreteTypeInternal(((Field)v1_1), ((Field)v1_1).getOutputFieldName(), v5_1);
                        goto label_251;
                    }
                    catch(IllegalAccessException v14) {
                        throw new ParseException("Error instantiating inner object", ((Throwable)v14));
                    }
                    catch(InstantiationException v14_1) {
                        throw new ParseException("Error instantiating inner object", ((Throwable)v14_1));
                    }
                }

                throw new ParseException("Expected start of object");
            }
            default: {
                int v15 = ((Field)v1_1).getTypeIn();
                StringBuilder v1_2 = new StringBuilder(30);
                v1_2.append("Invalid field type ");
                v1_2.append(v15);
                throw new ParseException(v1_2.toString());
            }
        }

    label_251:
        this.zzk(v6);
        this.zzk(2);
        char v1_3 = this.zzj(arg14);
        if(v1_3 != v8) {
            if(v1_3 == v9) {
                v1 = ((String)v4);
                goto label_8;
            }

            v0_2 = new StringBuilder(55);
            v0_2.append("Expected end of object or field separator, but found: ");
            v0_2.append(v1_3);
            throw new ParseException(v0_2.toString());
        }

        v1 = this.zza(arg14);
        goto label_8;
    }

    private final ArrayList zza(BufferedReader arg5, zza arg6) {
        int v0 = this.zzj(arg5);
        if(v0 == 110) {
            this.zzb(arg5, FastParser.zzwv);
            return null;
        }

        if(v0 != 91) {
            goto label_33;
        }

        int v1 = 5;
        this.zzxb.push(Integer.valueOf(v1));
        ArrayList v0_1 = new ArrayList();
        while(true) {
            arg5.mark(1024);
            int v2 = this.zzj(arg5);
            if(v2 == 0) {
                goto label_29;
            }

            if(v2 == 44) {
                continue;
            }

            if(v2 == 93) {
                break;
            }

            arg5.reset();
            v0_1.add(arg6.zzh(this, arg5));
        }

        this.zzk(v1);
        return v0_1;
    label_29:
        throw new ParseException("Unexpected EOF");
    label_33:
        throw new ParseException("Expected start of array");
    }

    static boolean zza(FastParser arg0, BufferedReader arg1, boolean arg2) {
        return arg0.zza(arg1, false);
    }

    private final boolean zza(BufferedReader arg5, boolean arg6) {
        char[] v6;
        char v1;
        while(true) {
            v1 = this.zzj(arg5);
            if(v1 != 34) {
                goto label_4;
            }

            if(1 != 0) {
                break;
            }
        }

        throw new ParseException("No boolean value found in string");
    label_4:
        if(v1 != 102) {
            if(v1 != 110) {
                if(v1 == 116) {
                    v6 = 1 != 0 ? FastParser.zzwx : FastParser.zzww;
                    this.zzb(arg5, v6);
                    return 1;
                }

                StringBuilder v0 = new StringBuilder(19);
                v0.append("Unexpected token: ");
                v0.append(v1);
                throw new ParseException(v0.toString());
            }

            this.zzb(arg5, FastParser.zzwv);
            return 0;
        }

        v6 = 1 != 0 ? FastParser.zzwz : FastParser.zzwy;
        this.zzb(arg5, v6);
        return 0;
    }

    private final void zzb(BufferedReader arg7, char[] arg8) {
        int v1;
        for(v1 = 0; true; v1 += v2) {
            if(v1 >= arg8.length) {
                return;
            }

            int v2 = arg7.read(this.zzwr, 0, arg8.length - v1);
            if(v2 == -1) {
                break;
            }

            int v3;
            for(v3 = 0; true; ++v3) {
                if(v3 >= v2) {
                    goto label_23;
                }

                if(arg8[v3 + v1] != this.zzwr[v3]) {
                    break;
                }
            }

            throw new ParseException("Unexpected character");
        label_23:
        }

        throw new ParseException("Unexpected EOF");
    }

    private static String zzb(BufferedReader arg9, char[] arg10, StringBuilder arg11, char[] arg12) {
        int v6;
        arg11.setLength(0);
        arg9.mark(arg10.length);
        int v1 = 0;
        int v2;
        for(v2 = 0; true; v2 = v4) {
            int v3 = arg9.read(arg10);
            if(v3 == -1) {
                break;
            }

            int v4 = v2;
            v2 = v1;
            for(v1 = 0; v1 < v3; ++v1) {
                char v5 = arg10[v1];
                if(Character.isISOControl(v5)) {
                    if(arg12 != null) {
                        v6 = 0;
                        while(true) {
                            if(v6 >= arg12.length) {
                                goto label_27;
                            }
                            else if(arg12[v6] == v5) {
                                v6 = 1;
                            }
                            else {
                                ++v6;
                                continue;
                            }

                            break;
                        }
                    }
                    else {
                    label_27:
                        v6 = 0;
                    }

                    if(v6 != 0) {
                        goto label_34;
                    }

                    throw new ParseException("Unexpected control character while reading string");
                }

            label_34:
                if(v5 == 34 && v2 == 0) {
                    arg11.append(arg10, 0, v1);
                    arg9.reset();
                    arg9.skip(((long)(v1 + 1)));
                    if(v4 != 0) {
                        return JsonUtils.unescapeString(arg11.toString());
                    }
                    else {
                        return arg11.toString();
                    }
                }

                if(v5 == 92) {
                    v2 ^= 1;
                    v4 = 1;
                }
                else {
                    v2 = 0;
                }
            }

            arg11.append(arg10, 0, v3);
            arg9.mark(arg10.length);
            v1 = v2;
        }

        throw new ParseException("Unexpected EOF while parsing string");
    }

    private final String zzb(BufferedReader arg15) {
        StringBuilder v1_1;
        char v0_1;
        int v8;
        int v7;
        arg15.mark(1024);
        int v0 = this.zzj(arg15);
        int v1 = 92;
        int v2 = 18;
        int v3 = 125;
        int v4 = 44;
        int v5 = 34;
        if(v0 == v5) {
            v7 = -1;
            if(arg15.read(this.zzwq) == v7) {
                goto label_139;
            }

            v0 = this.zzwq[0];
            v8 = 0;
            while(true) {
                if(v0 == v5) {
                    if(v8 != 0) {
                    }
                    else {
                        goto label_99;
                    }
                }

                if(v0 == v1) {
                    v8 ^= 1;
                }
                else {
                    v8 = 0;
                }

                if(arg15.read(this.zzwq) == v7) {
                    goto label_135;
                }

                v0_1 = this.zzwq[0];
                if(Character.isISOControl(v0_1)) {
                    break;
                }
            }

            throw new ParseException("Unexpected control character while reading string");
        label_135:
            throw new ParseException("Unexpected EOF while parsing string");
        }
        else if(v0 != v4) {
            v7 = 32;
            v8 = 91;
            int v9 = 1;
            if(v0 == v8) {
                int v10 = 5;
                this.zzxb.push(Integer.valueOf(v10));
                arg15.mark(v7);
                v7 = 93;
                if(this.zzj(arg15) != v7) {
                    arg15.reset();
                    v0 = 0;
                    int v11 = 0;
                    while(true) {
                        if(v9 > 0) {
                            char v12 = this.zzj(arg15);
                            if(v12 == 0) {
                                goto label_81;
                            }
                            else if(!Character.isISOControl(v12)) {
                                if(v12 == v5 && v0 == 0) {
                                    v11 ^= 1;
                                }

                                if(v12 == v8 && v11 == 0) {
                                    ++v9;
                                }

                                if(v12 == v7 && v11 == 0) {
                                    --v9;
                                }

                                if(v12 == v1 && v11 != 0) {
                                    v0 ^= 1;
                                    continue;
                                }

                                v0 = 0;
                                continue;
                            }
                            else {
                                break;
                            }
                        }

                        goto label_52;
                    }

                    throw new ParseException("Unexpected control character while reading array");
                label_81:
                    throw new ParseException("Unexpected EOF while parsing array");
                }

            label_52:
                this.zzk(v10);
            }
            else if(v0 != 123) {
                arg15.reset();
                this.zza(arg15, this.zzws);
            }
            else {
                this.zzxb.push(Integer.valueOf(1));
                arg15.mark(v7);
                v0_1 = this.zzj(arg15);
                if(v0_1 != v3) {
                    if(v0_1 == v5) {
                        arg15.reset();
                        this.zza(arg15);
                        do {
                        }
                        while(this.zzb(arg15) != null);
                    }
                    else {
                        goto label_35;
                    }
                }

                this.zzk(1);
                goto label_99;
            label_35:
                v1_1 = new StringBuilder(v2);
                v1_1.append("Unexpected token ");
                v1_1.append(v0_1);
                throw new ParseException(v1_1.toString());
            }
        }
        else {
            throw new ParseException("Missing value");
        }

    label_99:
        v0_1 = this.zzj(arg15);
        v1 = 2;
        if(v0_1 != v4) {
            if(v0_1 == v3) {
                this.zzk(v1);
                return null;
            }

            v1_1 = new StringBuilder(v2);
            v1_1.append("Unexpected token ");
            v1_1.append(v0_1);
            throw new ParseException(v1_1.toString());
        }

        this.zzk(v1);
        return this.zza(arg15);
    label_139:
        throw new ParseException("Unexpected EOF while parsing string");
    }

    static long zzb(FastParser arg0, BufferedReader arg1) {
        return arg0.zze(arg1);
    }

    private final String zzc(BufferedReader arg4) {
        return this.zza(arg4, this.zzwr, this.zzwt, null);
    }

    static float zzc(FastParser arg0, BufferedReader arg1) {
        return arg0.zzg(arg1);
    }

    private final int zzd(BufferedReader arg10) {
        int v0;
        int v5;
        int v3;
        int v2;
        int v10 = this.zza(arg10, this.zzws);
        if(v10 == 0) {
            return 0;
        }

        char[] v1 = this.zzws;
        if(v10 <= 0) {
            goto label_68;
        }

        if(v1[0] == 45) {
            v2 = 1;
            v3 = 1;
            v5 = -2147483648;
        }
        else {
            v2 = 0;
            v3 = 0;
            v5 = -2147483647;
        }

        int v6 = 10;
        if(v2 < v10) {
            v0 = v2 + 1;
            v2 = Character.digit(v1[v2], v6);
            if(v2 >= 0) {
                v2 = -v2;
            }
            else {
                throw new ParseException("Unexpected non-digit character");
            }
        }
        else {
            v0 = v2;
            v2 = 0;
        }

        while(true) {
            if(v0 >= v10) {
                goto label_59;
            }

            int v7 = v0 + 1;
            v0 = Character.digit(v1[v0], v6);
            if(v0 < 0) {
                goto label_55;
            }

            if(v2 < -214748364) {
                goto label_51;
            }

            v2 *= 10;
            if(v2 < v5 + v0) {
                break;
            }

            v2 -= v0;
            v0 = v7;
        }

        throw new ParseException("Number too large");
    label_51:
        throw new ParseException("Number too large");
    label_55:
        throw new ParseException("Unexpected non-digit character");
    label_59:
        if(v3 != 0) {
            if(v0 > 1) {
                return v2;
            }

            throw new ParseException("No digits to parse");
        }

        return -v2;
    label_68:
        throw new ParseException("No number to parse");
    }

    static double zzd(FastParser arg0, BufferedReader arg1) {
        return arg0.zzh(arg1);
    }

    private final long zze(BufferedReader arg18) {
        long v11;
        int v3;
        int v2_1;
        int v9;
        long v6;
        FastParser v0 = this;
        int v1 = v0.zza(arg18, v0.zzws);
        long v2 = 0;
        if(v1 == 0) {
            return v2;
        }

        char[] v4 = v0.zzws;
        if(v1 <= 0) {
            goto label_72;
        }

        int v5 = 0;
        if(v4[0] == 45) {
            v6 = -9223372036854775808L;
            v5 = 1;
            v9 = 1;
        }
        else {
            v6 = -9223372036854775807L;
            v9 = 0;
        }

        int v10 = 10;
        if(v5 < v1) {
            v2_1 = v5 + 1;
            v3 = Character.digit(v4[v5], v10);
            if(v3 >= 0) {
                v11 = ((long)(-v3));
            }
            else {
                throw new ParseException("Unexpected non-digit character");
            }
        }
        else {
            v11 = v2;
            v2_1 = v5;
        }

        while(true) {
            if(v2_1 >= v1) {
                goto label_63;
            }

            v3 = v2_1 + 1;
            v2_1 = Character.digit(v4[v2_1], v10);
            if(v2_1 < 0) {
                goto label_59;
            }

            if(v11 < -922337203685477580L) {
                goto label_55;
            }

            v11 *= 10;
            long v13 = ((long)v2_1);
            if(v11 < v6 + v13) {
                break;
            }

            v11 -= v13;
            v2_1 = v3;
        }

        throw new ParseException("Number too large");
    label_55:
        throw new ParseException("Number too large");
    label_59:
        throw new ParseException("Unexpected non-digit character");
    label_63:
        if(v9 != 0) {
            if(v2_1 > 1) {
                return v11;
            }

            throw new ParseException("No digits to parse");
        }

        return -v11;
    label_72:
        throw new ParseException("No number to parse");
    }

    static String zze(FastParser arg0, BufferedReader arg1) {
        return arg0.zzc(arg1);
    }

    private final BigInteger zzf(BufferedReader arg5) {
        int v5 = this.zza(arg5, this.zzws);
        if(v5 == 0) {
            return null;
        }

        return new BigInteger(new String(this.zzws, 0, v5));
    }

    static BigInteger zzf(FastParser arg0, BufferedReader arg1) {
        return arg0.zzf(arg1);
    }

    private final float zzg(BufferedReader arg4) {
        int v4 = this.zza(arg4, this.zzws);
        if(v4 == 0) {
            return 0;
        }

        return Float.parseFloat(new String(this.zzws, 0, v4));
    }

    static BigDecimal zzg(FastParser arg0, BufferedReader arg1) {
        return arg0.zzi(arg1);
    }

    private final double zzh(BufferedReader arg4) {
        int v4 = this.zza(arg4, this.zzws);
        if(v4 == 0) {
            return 0;
        }

        return Double.parseDouble(new String(this.zzws, 0, v4));
    }

    private final BigDecimal zzi(BufferedReader arg5) {
        int v5 = this.zza(arg5, this.zzws);
        if(v5 == 0) {
            return null;
        }

        return new BigDecimal(new String(this.zzws, 0, v5));
    }

    private final char zzj(BufferedReader arg4) {
        int v1 = -1;
        if(arg4.read(this.zzwq) == v1) {
            return 0;
        }

        do {
            if(!Character.isWhitespace(this.zzwq[0])) {
                goto label_14;
            }
        }
        while(arg4.read(this.zzwq) != v1);

        return 0;
    label_14:
        return this.zzwq[0];
    }

    private final void zzk(int arg5) {
        int v1 = 46;
        if(!this.zzxb.isEmpty()) {
            int v0 = this.zzxb.pop().intValue();
            if(v0 == arg5) {
                return;
            }

            StringBuilder v3 = new StringBuilder(v1);
            v3.append("Expected state ");
            v3.append(arg5);
            v3.append(" but had ");
            v3.append(v0);
            throw new ParseException(v3.toString());
        }

        StringBuilder v2 = new StringBuilder(v1);
        v2.append("Expected state ");
        v2.append(arg5);
        v2.append(" but had empty stack");
        throw new ParseException(v2.toString());
    }
}

