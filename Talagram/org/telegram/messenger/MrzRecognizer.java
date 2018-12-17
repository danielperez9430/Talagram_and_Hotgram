package org.telegram.messenger;

import android.content.res.AssetManager;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector$Builder;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.util.Calendar;
import java.util.HashMap;

public class MrzRecognizer {
    public class Result {
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_UNKNOWN = 0;
        public static final int TYPE_DRIVER_LICENSE = 4;
        public static final int TYPE_ID = 2;
        public static final int TYPE_INTERNAL_PASSPORT = 3;
        public static final int TYPE_PASSPORT = 1;
        public int birthDay;
        public int birthMonth;
        public int birthYear;
        public boolean doesNotExpire;
        public int expiryDay;
        public int expiryMonth;
        public int expiryYear;
        public String firstName;
        public int gender;
        public String issuingCountry;
        public String lastName;
        public boolean mainCheckDigitIsValid;
        public String middleName;
        public String nationality;
        public String number;
        public String rawMRZ;
        public int type;

        public Result() {
            super();
        }
    }

    public MrzRecognizer() {
        super();
    }

    private static native Rect[][] binarizeAndFindCharacters(Bitmap arg0, Bitmap arg1) {
    }

    private static String capitalize(String arg5) {
        if(arg5 == null) {
            return null;
        }

        char[] v5 = arg5.toCharArray();
        int v2 = 0;
        int v3 = 1;
        while(v2 < v5.length) {
            if(v3 == 0 && (Character.isLetter(v5[v2]))) {
                v5[v2] = Character.toLowerCase(v5[v2]);
            }
            else if(v5[v2] == 32) {
                v3 = 1;
            }
            else {
                v3 = 0;
            }

            ++v2;
        }

        return new String(v5);
    }

    private static int checksum(String arg7) {
        int v4;
        char[] v7 = arg7.toCharArray();
        int[] v0 = new int[]{7, 3, 1};
        int v2 = 0;
        int v3 = 0;
        while(v2 < v7.length) {
            int v5 = 48;
            if(v7[v2] < v5 || v7[v2] > 57) {
                v5 = 65;
                if(v7[v2] >= v5 && v7[v2] <= 90) {
                    v4 = v7[v2] - v5 + 10;
                    goto label_28;
                }

                v4 = 0;
            }
            else {
                v4 = v7[v2] - v5;
            }

        label_28:
            v3 += v4 * v0[v2 % v0.length];
            ++v2;
        }

        return v3 % 10;
    }

    private static String cyrillicToLatin(String arg4) {
        String[] v0 = new String[33];
        int v2 = 0;
        v0[0] = "A";
        v0[1] = "B";
        v0[2] = "V";
        v0[3] = "G";
        v0[4] = "D";
        v0[5] = "E";
        v0[6] = "E";
        v0[7] = "ZH";
        v0[8] = "Z";
        v0[9] = "I";
        v0[10] = "I";
        v0[11] = "K";
        v0[12] = "L";
        v0[13] = "M";
        v0[14] = "N";
        v0[15] = "O";
        v0[16] = "P";
        v0[17] = "R";
        v0[18] = "S";
        v0[19] = "T";
        v0[20] = "U";
        v0[21] = "F";
        v0[22] = "KH";
        v0[23] = "TS";
        v0[24] = "CH";
        v0[25] = "SH";
        v0[26] = "SHCH";
        v0[27] = "IE";
        v0[28] = "Y";
        v0[29] = "";
        v0[30] = "E";
        v0[31] = "IU";
        v0[32] = "IA";
        while(v2 < v0.length) {
            int v3 = v2 + 1;
            arg4 = arg4.replace("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".substring(v2, v3), v0[v2]);
            v2 = v3;
        }

        return arg4;
    }

    private static native int[] findCornerPoints(Bitmap arg0) {
    }

    private static HashMap getCountriesMap() {
        HashMap v0 = new HashMap();
        v0.put("AFG", "AF");
        v0.put("ALA", "AX");
        v0.put("ALB", "AL");
        v0.put("DZA", "DZ");
        v0.put("ASM", "AS");
        v0.put("AND", "AD");
        v0.put("AGO", "AO");
        v0.put("AIA", "AI");
        v0.put("ATA", "AQ");
        v0.put("ATG", "AG");
        v0.put("ARG", "AR");
        v0.put("ARM", "AM");
        v0.put("ABW", "AW");
        v0.put("AUS", "AU");
        v0.put("AUT", "AT");
        v0.put("AZE", "AZ");
        v0.put("BHS", "BS");
        v0.put("BHR", "BH");
        v0.put("BGD", "BD");
        v0.put("BRB", "BB");
        v0.put("BLR", "BY");
        v0.put("BEL", "BE");
        v0.put("BLZ", "BZ");
        v0.put("BEN", "BJ");
        v0.put("BMU", "BM");
        v0.put("BTN", "BT");
        v0.put("BOL", "BO");
        v0.put("BES", "BQ");
        v0.put("BIH", "BA");
        v0.put("BWA", "BW");
        v0.put("BVT", "BV");
        v0.put("BRA", "BR");
        v0.put("IOT", "IO");
        v0.put("BRN", "BN");
        v0.put("BGR", "BG");
        v0.put("BFA", "BF");
        v0.put("BDI", "BI");
        v0.put("CPV", "CV");
        v0.put("KHM", "KH");
        v0.put("CMR", "CM");
        v0.put("CAN", "CA");
        v0.put("CYM", "KY");
        v0.put("CAF", "CF");
        v0.put("TCD", "TD");
        v0.put("CHL", "CL");
        v0.put("CHN", "CN");
        v0.put("CXR", "CX");
        v0.put("CCK", "CC");
        v0.put("COL", "CO");
        v0.put("COM", "KM");
        v0.put("COG", "CG");
        v0.put("COD", "CD");
        v0.put("COK", "CK");
        v0.put("CRI", "CR");
        v0.put("CIV", "CI");
        v0.put("HRV", "HR");
        v0.put("CUB", "CU");
        v0.put("CUW", "CW");
        v0.put("CYP", "CY");
        v0.put("CZE", "CZ");
        v0.put("DNK", "DK");
        v0.put("DJI", "DJ");
        v0.put("DMA", "DM");
        v0.put("DOM", "DO");
        v0.put("ECU", "EC");
        v0.put("EGY", "EG");
        v0.put("SLV", "SV");
        v0.put("GNQ", "GQ");
        v0.put("ERI", "ER");
        v0.put("EST", "EE");
        v0.put("ETH", "ET");
        v0.put("FLK", "FK");
        v0.put("FRO", "FO");
        v0.put("FJI", "FJ");
        v0.put("FIN", "FI");
        v0.put("FRA", "FR");
        v0.put("GUF", "GF");
        v0.put("PYF", "PF");
        v0.put("ATF", "TF");
        v0.put("GAB", "GA");
        v0.put("GMB", "GM");
        v0.put("GEO", "GE");
        v0.put("D<<", "DE");
        v0.put("GHA", "GH");
        v0.put("GIB", "GI");
        v0.put("GRC", "GR");
        v0.put("GRL", "GL");
        v0.put("GRD", "GD");
        v0.put("GLP", "GP");
        v0.put("GUM", "GU");
        v0.put("GTM", "GT");
        v0.put("GGY", "GG");
        v0.put("GIN", "GN");
        v0.put("GNB", "GW");
        v0.put("GUY", "GY");
        v0.put("HTI", "HT");
        v0.put("HMD", "HM");
        v0.put("VAT", "VA");
        v0.put("HND", "HN");
        v0.put("HKG", "HK");
        v0.put("HUN", "HU");
        v0.put("ISL", "IS");
        v0.put("IND", "IN");
        v0.put("IDN", "ID");
        v0.put("IRN", "IR");
        v0.put("IRQ", "IQ");
        v0.put("IRL", "IE");
        v0.put("IMN", "IM");
        v0.put("ISR", "IL");
        v0.put("ITA", "IT");
        v0.put("JAM", "JM");
        v0.put("JPN", "JP");
        v0.put("JEY", "JE");
        v0.put("JOR", "JO");
        v0.put("KAZ", "KZ");
        v0.put("KEN", "KE");
        v0.put("KIR", "KI");
        v0.put("PRK", "KP");
        v0.put("KOR", "KR");
        v0.put("KWT", "KW");
        v0.put("KGZ", "KG");
        v0.put("LAO", "LA");
        v0.put("LVA", "LV");
        v0.put("LBN", "LB");
        v0.put("LSO", "LS");
        v0.put("LBR", "LR");
        v0.put("LBY", "LY");
        v0.put("LIE", "LI");
        v0.put("LTU", "LT");
        v0.put("LUX", "LU");
        v0.put("MAC", "MO");
        v0.put("MKD", "MK");
        v0.put("MDG", "MG");
        v0.put("MWI", "MW");
        v0.put("MYS", "MY");
        v0.put("MDV", "MV");
        v0.put("MLI", "ML");
        v0.put("MLT", "MT");
        v0.put("MHL", "MH");
        v0.put("MTQ", "MQ");
        v0.put("MRT", "MR");
        v0.put("MUS", "MU");
        v0.put("MYT", "YT");
        v0.put("MEX", "MX");
        v0.put("FSM", "FM");
        v0.put("MDA", "MD");
        v0.put("MCO", "MC");
        v0.put("MNG", "MN");
        v0.put("MNE", "ME");
        v0.put("MSR", "MS");
        v0.put("MAR", "MA");
        v0.put("MOZ", "MZ");
        v0.put("MMR", "MM");
        v0.put("NAM", "NA");
        v0.put("NRU", "NR");
        v0.put("NPL", "NP");
        v0.put("NLD", "NL");
        v0.put("NCL", "NC");
        v0.put("NZL", "NZ");
        v0.put("NIC", "NI");
        v0.put("NER", "NE");
        v0.put("NGA", "NG");
        v0.put("NIU", "NU");
        v0.put("NFK", "NF");
        v0.put("MNP", "MP");
        v0.put("NOR", "NO");
        v0.put("OMN", "OM");
        v0.put("PAK", "PK");
        v0.put("PLW", "PW");
        v0.put("PSE", "PS");
        v0.put("PAN", "PA");
        v0.put("PNG", "PG");
        v0.put("PRY", "PY");
        v0.put("PER", "PE");
        v0.put("PHL", "PH");
        v0.put("PCN", "PN");
        v0.put("POL", "PL");
        v0.put("PRT", "PT");
        v0.put("PRI", "PR");
        v0.put("QAT", "QA");
        v0.put("REU", "RE");
        v0.put("ROU", "RO");
        v0.put("RUS", "RU");
        v0.put("RWA", "RW");
        v0.put("BLM", "BL");
        v0.put("SHN", "SH");
        v0.put("KNA", "KN");
        v0.put("LCA", "LC");
        v0.put("MAF", "MF");
        v0.put("SPM", "PM");
        v0.put("VCT", "VC");
        v0.put("WSM", "WS");
        v0.put("SMR", "SM");
        v0.put("STP", "ST");
        v0.put("SAU", "SA");
        v0.put("SEN", "SN");
        v0.put("SRB", "RS");
        v0.put("SYC", "SC");
        v0.put("SLE", "SL");
        v0.put("SGP", "SG");
        v0.put("SXM", "SX");
        v0.put("SVK", "SK");
        v0.put("SVN", "SI");
        v0.put("SLB", "SB");
        v0.put("SOM", "SO");
        v0.put("ZAF", "ZA");
        v0.put("SGS", "GS");
        v0.put("SSD", "SS");
        v0.put("ESP", "ES");
        v0.put("LKA", "LK");
        v0.put("SDN", "SD");
        v0.put("SUR", "SR");
        v0.put("SJM", "SJ");
        v0.put("SWZ", "SZ");
        v0.put("SWE", "SE");
        v0.put("CHE", "CH");
        v0.put("SYR", "SY");
        v0.put("TWN", "TW");
        v0.put("TJK", "TJ");
        v0.put("TZA", "TZ");
        v0.put("THA", "TH");
        v0.put("TLS", "TL");
        v0.put("TGO", "TG");
        v0.put("TKL", "TK");
        v0.put("TON", "TO");
        v0.put("TTO", "TT");
        v0.put("TUN", "TN");
        v0.put("TUR", "TR");
        v0.put("TKM", "TM");
        v0.put("TCA", "TC");
        v0.put("TUV", "TV");
        v0.put("UGA", "UG");
        v0.put("UKR", "UA");
        v0.put("ARE", "AE");
        v0.put("GBR", "GB");
        v0.put("USA", "US");
        v0.put("UMI", "UM");
        v0.put("URY", "UY");
        v0.put("UZB", "UZ");
        v0.put("VUT", "VU");
        v0.put("VEN", "VE");
        v0.put("VNM", "VN");
        v0.put("VGB", "VG");
        v0.put("VIR", "VI");
        v0.put("WLF", "WF");
        v0.put("ESH", "EH");
        v0.put("YEM", "YE");
        v0.put("ZMB", "ZM");
        v0.put("ZWE", "ZW");
        return v0;
    }

    private static int getNumber(char arg1) {
        if(arg1 == 79) {
            return 0;
        }

        if(arg1 == 73) {
            return 1;
        }

        if(arg1 == 66) {
            return 8;
        }

        return arg1 - 48;
    }

    private static void parseBirthDate(String arg4, Result arg5) {
        int v0;
        int v1 = 2;
        try {
            arg5.birthYear = Integer.parseInt(arg4.substring(0, v1));
            if(arg5.birthYear < Calendar.getInstance().get(1) % 100 - 5) {
                v0 = arg5.birthYear + 2000;
            }
            else {
                goto label_15;
            }

        label_17:
            arg5.birthYear = v0;
            arg5.birthMonth = Integer.parseInt(arg4.substring(v1, 4));
            arg5.birthDay = Integer.parseInt(arg4.substring(4));
            return;
        label_15:
            v0 = arg5.birthYear + 1900;
            goto label_17;
        }
        catch(NumberFormatException ) {
            return;
        }
    }

    private static void parseExpiryDate(String arg2, Result arg3) {
        try {
            if("<<<<<<".equals(arg2)) {
                arg3.doesNotExpire = true;
            }
            else {
                arg3.expiryYear = Integer.parseInt(arg2.substring(0, 2)) + 2000;
                arg3.expiryMonth = Integer.parseInt(arg2.substring(2, 4));
                arg3.expiryDay = Integer.parseInt(arg2.substring(4));
            }

            return;
        }
        catch(NumberFormatException ) {
            return;
        }
    }

    private static int parseGender(char arg1) {
        if(arg1 != 70) {
            if(arg1 != 77) {
                return 0;
            }

            return 1;
        }

        return 2;
    }

    private static native String performRecognition(Bitmap arg0, int arg1, int arg2, AssetManager arg3) {
    }

    public static Result recognize(Bitmap arg1, boolean arg2) {
        Result v0;
        if(arg2) {
            v0 = MrzRecognizer.recognizeBarcode(arg1);
            if(v0 != null) {
                return v0;
            }
        }

        try {
            v0 = MrzRecognizer.recognizeMRZ(arg1);
            if(v0 == null) {
                goto label_7;
            }
        }
        catch(Exception ) {
            goto label_7;
        }

        return v0;
    label_7:
        if(!arg2) {
            Result v1 = MrzRecognizer.recognizeBarcode(arg1);
            if(v1 != null) {
                return v1;
            }
        }

        return null;
    }

    public static Result recognize(byte[] arg9, int arg10, int arg11, int arg12) {
        Bitmap v1 = Bitmap.createBitmap(arg10, arg11, Bitmap$Config.ARGB_8888);
        MrzRecognizer.setYuvBitmapPixels(v1, arg9);
        Matrix v6 = new Matrix();
        v6.setRotate(((float)arg12));
        int v9 = Math.min(arg10, arg11);
        int v0 = Math.round((((float)v9)) * 0.704f);
        arg12 = arg12 == 90 || arg12 == 270 ? 1 : 0;
        int v2 = arg12 != 0 ? arg10 / 2 - v0 / 2 : 0;
        int v3 = arg12 != 0 ? 0 : arg11 / 2 - v0 / 2;
        int v4 = arg12 != 0 ? v0 : v9;
        int v5 = arg12 != 0 ? v9 : v0;
        return MrzRecognizer.recognize(Bitmap.createBitmap(v1, v2, v3, v4, v5, v6, false), false);
    }

    private static Result recognizeBarcode(Bitmap arg10) {
        BarcodeDetector v0 = new Builder(ApplicationLoader.applicationContext).build();
        int v2 = 1500;
        if(arg10.getWidth() > v2 || arg10.getHeight() > v2) {
            float v1 = 1500f / (((float)Math.max(arg10.getWidth(), arg10.getHeight())));
            arg10 = Bitmap.createScaledBitmap(arg10, Math.round((((float)arg10.getWidth())) * v1), Math.round((((float)arg10.getHeight())) * v1), true);
        }

        SparseArray v10 = v0.detect(new com.google.android.gms.vision.Frame$Builder().setBitmap(arg10).build());
        int v0_1 = 0;
        int v1_1 = 0;
        while(v1_1 < v10.size()) {
            Object v2_1 = v10.valueAt(v1_1);
            int v6 = 6;
            int v7 = 2;
            int v8 = 4;
            if(((Barcode)v2_1).valueFormat == 12 && ((Barcode)v2_1).driverLicense != null) {
                Result v10_1 = new Result();
                v1_1 = "ID".equals(((Barcode)v2_1).driverLicense.documentType) ? 2 : 4;
                v10_1.type = v1_1;
                String v1_2 = ((Barcode)v2_1).driverLicense.issuingCountry;
                int v4 = v1_2.hashCode();
                int v9 = -1;
                if(v4 != 66480) {
                    if(v4 != 84323) {
                        goto label_73;
                    }
                    else if(v1_2.equals("USA")) {
                        v1_1 = 0;
                    }
                    else {
                        goto label_73;
                    }
                }
                else if(v1_2.equals("CAN")) {
                    v1_1 = 1;
                }
                else {
                label_73:
                    v1_1 = -1;
                }

                switch(v1_1) {
                    case 0: {
                        goto label_78;
                    }
                    case 1: {
                        goto label_76;
                    }
                }

                goto label_81;
            label_76:
                v1_2 = "CA";
                goto label_79;
            label_78:
                v1_2 = "US";
            label_79:
                v10_1.issuingCountry = v1_2;
                v10_1.nationality = v1_2;
            label_81:
                v10_1.firstName = MrzRecognizer.capitalize(((Barcode)v2_1).driverLicense.firstName);
                v10_1.lastName = MrzRecognizer.capitalize(((Barcode)v2_1).driverLicense.lastName);
                v10_1.middleName = MrzRecognizer.capitalize(((Barcode)v2_1).driverLicense.middleName);
                v10_1.number = ((Barcode)v2_1).driverLicense.licenseNumber;
                if(((Barcode)v2_1).driverLicense.gender != null) {
                    v1_2 = ((Barcode)v2_1).driverLicense.gender;
                    switch(v1_2.hashCode()) {
                        case 49: {
                            if(v1_2.equals("1")) {
                                v9 = 0;
                            }
                            else {
                            }

                            break;
                        }
                        case 50: {
                            if(v1_2.equals("2")) {
                                v9 = 1;
                            }
                            else {
                            }

                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    switch(v9) {
                        case 0: {
                            goto label_117;
                        }
                        case 1: {
                            goto label_115;
                        }
                    }

                    goto label_118;
                label_115:
                    v10_1.gender = v7;
                    goto label_118;
                label_117:
                    v10_1.gender = 1;
                }

            label_118:
                if("USA".equals(v10_1.issuingCountry)) {
                    v0_1 = 4;
                    v6 = 2;
                    v8 = 0;
                }

                try {
                    int v3 = 8;
                    if(((Barcode)v2_1).driverLicense.birthDate != null && ((Barcode)v2_1).driverLicense.birthDate.length() == v3) {
                        v10_1.birthYear = Integer.parseInt(((Barcode)v2_1).driverLicense.birthDate.substring(v0_1, v0_1 + 4));
                        v10_1.birthMonth = Integer.parseInt(((Barcode)v2_1).driverLicense.birthDate.substring(v8, v8 + 2));
                        v10_1.birthDay = Integer.parseInt(((Barcode)v2_1).driverLicense.birthDate.substring(v6, v6 + 2));
                    }

                    if(((Barcode)v2_1).driverLicense.expiryDate != null && ((Barcode)v2_1).driverLicense.expiryDate.length() == v3) {
                        v10_1.expiryYear = Integer.parseInt(((Barcode)v2_1).driverLicense.expiryDate.substring(v0_1, v0_1 + 4));
                        v10_1.expiryMonth = Integer.parseInt(((Barcode)v2_1).driverLicense.expiryDate.substring(v8, v8 + 2));
                        v10_1.expiryDay = Integer.parseInt(((Barcode)v2_1).driverLicense.expiryDate.substring(v6, v6 + 2));
                    }

                    return v10_1;
                }
                catch(NumberFormatException ) {
                    return v10_1;
                }
            }

            if(((Barcode)v2_1).valueFormat == 7 && ((Barcode)v2_1).format == 2048 && (((Barcode)v2_1).rawValue.matches("^[A-Za-z0-9=]+$"))) {
                try {
                    String[] v2_2 = new String(Base64.decode(((Barcode)v2_1).rawValue, 0), "windows-1251").split("\\|");
                    if(v2_2.length >= 10) {
                        Result v4_1 = new Result();
                        v4_1.type = v8;
                        v4_1.issuingCountry = "RU";
                        v4_1.nationality = "RU";
                        v4_1.number = v2_2[0];
                        v4_1.expiryYear = Integer.parseInt(v2_2[v7].substring(0, v8));
                        v4_1.expiryMonth = Integer.parseInt(v2_2[v7].substring(v8, v6));
                        v4_1.expiryDay = Integer.parseInt(v2_2[v7].substring(v6));
                        v4_1.lastName = MrzRecognizer.capitalize(MrzRecognizer.cyrillicToLatin(v2_2[3]));
                        v4_1.firstName = MrzRecognizer.capitalize(MrzRecognizer.cyrillicToLatin(v2_2[v8]));
                        v4_1.middleName = MrzRecognizer.capitalize(MrzRecognizer.cyrillicToLatin(v2_2[5]));
                        v4_1.birthYear = Integer.parseInt(v2_2[v6].substring(0, v8));
                        v4_1.birthMonth = Integer.parseInt(v2_2[v6].substring(v8, v6));
                        v4_1.birthDay = Integer.parseInt(v2_2[v6].substring(v6));
                        return v4_1;
                    }

                    goto label_244;
                }
                catch(Exception ) {
                label_244:
                    ++v1_1;
                    continue;
                }
            }

            goto label_244;
        }

        return null;
    }

    private static Result recognizeMRZ(Bitmap arg28) {
        String v2_3;
        String v4_5;
        int v14;
        int v13_1;
        int v9_2;
        Rect v6_1;
        Paint v3_4;
        int v8_1;
        Rect[][] v4_3;
        Bitmap v5_4;
        int v12_1;
        Matrix v4_1;
        Canvas v2;
        Bitmap v3;
        float v1;
        Bitmap v0 = arg28;
        if(arg28.getWidth() > 512 || arg28.getHeight() > 512) {
            v1 = 512f / (((float)Math.max(arg28.getWidth(), arg28.getHeight())));
            v3 = Bitmap.createScaledBitmap(v0, Math.round((((float)arg28.getWidth())) * v1), Math.round((((float)arg28.getHeight())) * v1), true);
        }
        else {
            v1 = 1f;
            v3 = v0;
        }

        int[] v3_1 = MrzRecognizer.findCornerPoints(v3);
        float v4 = 1f / v1;
        int v1_1 = 6;
        int v5 = 3;
        int v6 = 5;
        int v7 = 2;
        if(v3_1 != null) {
            Point v9 = new Point(v3_1[0], v3_1[1]);
            Point v10 = new Point(v3_1[v7], v3_1[v5]);
            Point v11 = new Point(v3_1[4], v3_1[v6]);
            Point v12 = new Point(v3_1[v1_1], v3_1[7]);
            if(v10.x < v9.x) {
            }
            else {
                Point v26 = v10;
                v10 = v9;
                v9 = v26;
                Point v27 = v12;
                v12 = v11;
                v11 = v27;
            }

            double v5_1 = Math.hypot(((double)(v9.x - v10.x)), ((double)(v9.y - v10.y)));
            double v1_2 = Math.hypot(((double)(v11.x - v12.x)), ((double)(v11.y - v12.y)));
            double v7_1 = Math.hypot(((double)(v12.x - v10.x)), ((double)(v12.y - v10.y)));
            Point v16 = v11;
            Point v17 = v12;
            double v11_1 = Math.hypot(((double)(v11.x - v9.x)), ((double)(v11.y - v9.y)));
            double v13 = v5_1 / v7_1;
            v5_1 /= v11_1;
            v7_1 = v1_2 / v7_1;
            v1_2 /= v11_1;
            if(v13 < 1.35) {
                goto label_248;
            }

            if(v13 > 1.75) {
                goto label_248;
            }

            if(v7_1 < 1.35) {
                goto label_248;
            }

            if(v7_1 > 1.75) {
                goto label_248;
            }

            if(v5_1 < 1.35) {
                goto label_248;
            }

            if(v5_1 > 1.75) {
                goto label_248;
            }

            if(v1_2 < 1.35) {
                goto label_248;
            }

            if(v1_2 > 1.75) {
                goto label_248;
            }

            Bitmap v1_3 = Bitmap.createBitmap(1024, ((int)Math.round(1024 / ((v13 + v5_1 + v7_1 + v1_2) / 4))), Bitmap$Config.ARGB_8888);
            v2 = new Canvas(v1_3);
            float[] v3_2 = new float[]{0f, 0f, ((float)v1_3.getWidth()), 0f, ((float)v1_3.getWidth()), ((float)v1_3.getHeight()), 0f, ((float)v1_3.getHeight())};
            float[] v5_2 = new float[]{(((float)v10.x)) * v4, (((float)v10.y)) * v4, (((float)v9.x)) * v4, (((float)v9.y)) * v4, (((float)v16.x)) * v4, (((float)v16.y)) * v4, (((float)v17.x)) * v4, (((float)v17.y)) * v4};
            v4_1 = new Matrix();
            v4_1.setPolyToPoly(v5_2, 0, v3_2, 0, v5_2.length >> 1);
            v2.drawBitmap(v0, v4_1, new Paint(2));
            v0 = v1_3;
        }
        else {
            if(arg28.getWidth() <= 1500 && arg28.getHeight() <= 1500) {
                goto label_248;
            }

            v1 = 1500f / (((float)Math.max(arg28.getWidth(), arg28.getHeight())));
            v0 = Bitmap.createScaledBitmap(v0, Math.round((((float)arg28.getWidth())) * v1), Math.round((((float)arg28.getHeight())) * v1), true);
        }

    label_248:
        Result v1_4 = null;
        Bitmap v4_2 = ((Bitmap)v1_4);
        Rect[][] v5_3 = v1_4;
        int v2_1 = 0;
        int v3_3 = 0;
        int v11_2 = 0;
        while(true) {
            v12_1 = 30;
            if(v2_1 >= 3) {
                break;
            }

            switch(v2_1) {
                case 1: {
                    goto label_273;
                }
                case 2: {
                    goto label_261;
                }
            }

            Matrix v9_1 = ((Matrix)v1_4);
            goto label_285;
        label_273:
            v4_1 = new Matrix();
            v4_1.setRotate(1f, ((float)(v0.getWidth() / 2)), ((float)(v0.getHeight() / 2)));
            goto label_284;
        label_261:
            v4_1 = new Matrix();
            v4_1.setRotate(-1f, ((float)(v0.getWidth() / 2)), ((float)(v0.getHeight() / 2)));
        label_284:
            v9_1 = v4_1;
        label_285:
            v4_2 = v9_1 != null ? Bitmap.createBitmap(v0, 0, 0, v0.getWidth(), v0.getHeight(), v9_1, true) : v0;
            v5_4 = Bitmap.createBitmap(v4_2.getWidth(), v4_2.getHeight(), Bitmap$Config.ALPHA_8);
            v4_3 = MrzRecognizer.binarizeAndFindCharacters(v4_2, v5_4);
            if(v4_3 == null) {
                return v1_4;
            }

            v6 = v4_3.length;
            v7 = v3_3;
            for(v3_3 = 0; v3_3 < v6; ++v3_3) {
                android.graphics.Rect[] v8 = v4_3[v3_3];
                v7 = Math.max(v8.length, v7);
                if(v8.length > 0) {
                    ++v11_2;
                }
            }

            v8_1 = 2;
            if(v11_2 >= v8_1 && v7 >= v12_1) {
                v3_3 = v7;
                goto label_329;
            }

            ++v2_1;
            v3_3 = v7;
            Bitmap v26_1 = v5_4;
            v5_3 = v4_3;
            v4_2 = v26_1;
        }

        v8_1 = 2;
        Rect[][] v26_2 = v5_3;
        v5_4 = v4_2;
        v4_3 = v26_2;
    label_329:
        if(v3_3 >= v12_1) {
            if(v11_2 < v8_1) {
            }
            else {
                v0 = Bitmap.createBitmap(v4_3[0].length * 10, v4_3.length * 15, Bitmap$Config.ALPHA_8);
                v2 = new Canvas(v0);
                v3_4 = new Paint(2);
                v6_1 = new Rect(0, 0, 10, 15);
                v7 = v4_3.length;
                v8_1 = 0;
                v9_2 = 0;
                goto label_353;
            }
        }

        return v1_4;
    label_353:
        while(v8_1 < v7) {
            android.graphics.Rect[] v10_1 = v4_3[v8_1];
            v11_2 = v10_1.length;
            v13_1 = 0;
            v14 = 0;
            while(v13_1 < v11_2) {
                Rect v15 = v10_1[v13_1];
                v12_1 = v14 * 10;
                v1_1 = v9_2 * 15;
                v6_1.set(v12_1, v1_1, v12_1 + 10, v1_1 + 15);
                v2.drawBitmap(v5_4, v15, v6_1, v3_4);
                ++v14;
                ++v13_1;
                v7 = v7;
                v10_1 = v10_1;
            }

            ++v9_2;
            ++v8_1;
        }

        String v0_1 = MrzRecognizer.performRecognition(v0, v4_3.length, v4_3[0].length, ApplicationLoader.applicationContext.getAssets());
        if(v0_1 == null) {
            return null;
        }

        String[] v0_2 = TextUtils.split(v0_1, "\n");
        v1_4 = new Result();
        if(v0_2.length >= 2 && v0_2[0].length() >= 30 && v0_2[1].length() == v0_2[0].length()) {
            v1_4.rawMRZ = TextUtils.join("\n", ((Object[])v0_2));
            HashMap v3_5 = MrzRecognizer.getCountriesMap();
            int v4_4 = v0_2[0].charAt(0);
            char v6_2 = '1';
            v7 = 27;
            char v8_2 = 'I';
            char v9_3 = ' ';
            char v10_2 = '<';
            char v11_3 = 'O';
            char v12_2 = '0';
            if(v4_4 == 80) {
                v1_4.type = 1;
                if(v0_2[0].length() == 44) {
                    v13_1 = 5;
                    v1_4.issuingCountry = v0_2[0].substring(2, v13_1);
                    v4_4 = v0_2[0].indexOf("<<", 6);
                    if(v4_4 != -1) {
                        v1_4.lastName = v0_2[0].substring(v13_1, v4_4).replace(v10_2, v9_3).replace(v12_2, v11_3).trim();
                        v1_4.firstName = v0_2[0].substring(v4_4 + 2).replace(v10_2, v9_3).replace(v12_2, v11_3).trim();
                        if(v1_4.firstName.contains("   ")) {
                            v5 = 0;
                            v1_4.firstName = v1_4.firstName.substring(0, v1_4.firstName.indexOf("   "));
                        }
                        else {
                            goto label_468;
                        }
                    }
                    else {
                    label_468:
                        v5 = 0;
                    }

                    v4_5 = v0_2[1].substring(v5, 9).replace(v10_2, v9_3).replace(v11_3, v12_2).trim();
                    if(MrzRecognizer.checksum(v4_5) == MrzRecognizer.getNumber(v0_2[1].charAt(9))) {
                        v1_4.number = v4_5;
                    }

                    v1_4.nationality = v0_2[1].substring(10, 13);
                    v4_5 = v0_2[1].substring(13, 19).replace(v11_3, v12_2).replace(v8_2, v6_2);
                    if(MrzRecognizer.checksum(v4_5) == MrzRecognizer.getNumber(v0_2[1].charAt(19))) {
                        MrzRecognizer.parseBirthDate(v4_5, v1_4);
                    }

                    v1_4.gender = MrzRecognizer.parseGender(v0_2[1].charAt(20));
                    v4_5 = v0_2[1].substring(21, v7).replace(v11_3, v12_2).replace(v8_2, v6_2);
                    if(MrzRecognizer.checksum(v4_5) == MrzRecognizer.getNumber(v0_2[1].charAt(v7)) || v0_2[1].charAt(v7) == v10_2) {
                        MrzRecognizer.parseExpiryDate(v4_5, v1_4);
                    }

                    if(!"RUS".equals(v1_4.issuingCountry) || v0_2[0].charAt(1) != 78) {
                        v1_4.firstName = v1_4.firstName.replace('8', 'B');
                        v1_4.lastName = v1_4.lastName.replace('8', 'B');
                    }
                    else {
                        v1_4.type = 3;
                        String[] v4_6 = v1_4.firstName.split(" ");
                        v1_4.firstName = MrzRecognizer.cyrillicToLatin(MrzRecognizer.russianPassportTranslit(v4_6[0]));
                        if(v4_6.length > 1) {
                            v1_4.middleName = MrzRecognizer.cyrillicToLatin(MrzRecognizer.russianPassportTranslit(v4_6[1]));
                        }

                        v1_4.lastName = MrzRecognizer.cyrillicToLatin(MrzRecognizer.russianPassportTranslit(v1_4.lastName));
                        if(v1_4.number == null) {
                            goto label_577;
                        }

                        v1_4.number = v1_4.number.substring(0, 3) + v0_2[1].charAt(28) + v1_4.number.substring(3);
                    }

                label_577:
                    v1_4.lastName = MrzRecognizer.capitalize(v1_4.lastName);
                    v1_4.firstName = MrzRecognizer.capitalize(v1_4.firstName);
                    v1_4.middleName = MrzRecognizer.capitalize(v1_4.middleName);
                }
            }
            else {
                if(v4_4 != v8_2 && v4_4 != 65) {
                    if(v4_4 == 67) {
                    }
                    else {
                        return null;
                    }
                }

                v2_1 = 2;
                v1_4.type = v2_1;
                if(v0_2.length == 3) {
                    v14 = 30;
                    if(v0_2[0].length() != v14) {
                        goto label_682;
                    }
                    else if(v0_2[v2_1].length() == v14) {
                        v1_4.issuingCountry = v0_2[0].substring(v2_1, 5);
                        v2_3 = v0_2[0].substring(5, 14).replace(v10_2, v9_3).replace(v11_3, v12_2).trim();
                        if(MrzRecognizer.checksum(v2_3) == v0_2[0].charAt(14) - v12_2) {
                            v1_4.number = v2_3;
                        }

                        v4_5 = v0_2[1].substring(0, 6).replace(v11_3, v12_2).replace(v8_2, v6_2);
                        if(MrzRecognizer.checksum(v4_5) == MrzRecognizer.getNumber(v0_2[1].charAt(6))) {
                            MrzRecognizer.parseBirthDate(v4_5, v1_4);
                        }

                        v1_4.gender = MrzRecognizer.parseGender(v0_2[1].charAt(7));
                        v4_5 = v0_2[1].substring(8, 14).replace(v11_3, v12_2).replace(v8_2, v6_2);
                        if(MrzRecognizer.checksum(v4_5) == MrzRecognizer.getNumber(v0_2[1].charAt(14)) || v0_2[1].charAt(14) == v10_2) {
                            MrzRecognizer.parseExpiryDate(v4_5, v1_4);
                        }

                        v1_4.nationality = v0_2[1].substring(15, 18);
                        v2_1 = 2;
                        v4_4 = v0_2[v2_1].indexOf("<<");
                        if(v4_4 == -1) {
                            goto label_821;
                        }

                        v1_4.lastName = v0_2[v2_1].substring(0, v4_4).replace(v10_2, v9_3).trim();
                        v1_4.firstName = v0_2[v2_1].substring(v4_4 + v2_1).replace(v10_2, v9_3).trim();
                    }
                    else {
                        goto label_682;
                    }
                }
                else {
                label_682:
                    v5 = 2;
                    if(v0_2.length != v5) {
                        goto label_821;
                    }

                    if(v0_2[0].length() != 36) {
                        goto label_821;
                    }

                    v1_4.issuingCountry = v0_2[0].substring(v5, 5);
                    if(("FRA".equals(v1_4.issuingCountry)) && v4_4 == v8_2 && v0_2[0].charAt(1) == 68) {
                        v1_4.nationality = "FRA";
                        v1_4.lastName = v0_2[0].substring(5, 30).replace(v10_2, v9_3).trim();
                        v1_4.firstName = v0_2[1].substring(13, v7).replace("<<", ", ").replace(v10_2, v9_3).trim();
                        v2_3 = v0_2[1].substring(0, 12).replace(v11_3, v12_2);
                        if(MrzRecognizer.checksum(v2_3) == MrzRecognizer.getNumber(v0_2[1].charAt(12))) {
                            v1_4.number = v2_3;
                        }

                        v2_3 = v0_2[1].substring(v7, 33).replace(v11_3, v12_2).replace(v8_2, v6_2);
                        if(MrzRecognizer.checksum(v2_3) == MrzRecognizer.getNumber(v0_2[1].charAt(33))) {
                            MrzRecognizer.parseBirthDate(v2_3, v1_4);
                        }

                        v1_4.gender = MrzRecognizer.parseGender(v0_2[1].charAt(34));
                        v1_4.doesNotExpire = true;
                        goto label_821;
                    }

                    v4_4 = v0_2[0].indexOf("<<");
                    if(v4_4 != -1) {
                        v1_4.lastName = v0_2[0].substring(5, v4_4).replace(v10_2, v9_3).trim();
                        v1_4.firstName = v0_2[0].substring(v4_4 + 2).replace(v10_2, v9_3).trim();
                    }

                    v2_3 = v0_2[1].substring(0, 9).replace(v10_2, v9_3).replace(v11_3, v12_2).trim();
                    if(MrzRecognizer.checksum(v2_3) == MrzRecognizer.getNumber(v0_2[1].charAt(9))) {
                        v1_4.number = v2_3;
                    }

                    v1_4.nationality = v0_2[1].substring(10, 13);
                    v2_3 = v0_2[1].substring(13, 19).replace(v11_3, v12_2).replace(v8_2, v6_2);
                    if(MrzRecognizer.checksum(v2_3) == MrzRecognizer.getNumber(v0_2[1].charAt(19))) {
                        MrzRecognizer.parseBirthDate(v2_3, v1_4);
                    }

                    v1_4.gender = MrzRecognizer.parseGender(v0_2[1].charAt(20));
                    v2_3 = v0_2[1].substring(21, v7).replace(v11_3, v12_2).replace(v8_2, v6_2);
                    if(MrzRecognizer.checksum(v2_3) != MrzRecognizer.getNumber(v0_2[1].charAt(v7)) && v0_2[1].charAt(v7) != v10_2) {
                        goto label_821;
                    }

                    MrzRecognizer.parseExpiryDate(v2_3, v1_4);
                }

            label_821:
                v1_4.firstName = MrzRecognizer.capitalize(v1_4.firstName.replace(v12_2, v11_3).replace('8', 'B'));
                v1_4.lastName = MrzRecognizer.capitalize(v1_4.lastName.replace(v12_2, v11_3).replace('8', 'B'));
            }

            if((TextUtils.isEmpty(v1_4.firstName)) && (TextUtils.isEmpty(v1_4.lastName))) {
                return null;
            }

            v1_4.issuingCountry = v3_5.get(v1_4.issuingCountry);
            v1_4.nationality = v3_5.get(v1_4.nationality);
            return v1_4;
        }

        return null;
    }

    private static String russianPassportTranslit(String arg3) {
        char[] v3 = arg3.toCharArray();
        int v0;
        for(v0 = 0; v0 < v3.length; ++v0) {
            int v1 = "ABVGDE2JZIQKLMNOPRSTUFHC34WXY9678".indexOf(v3[v0]);
            if(v1 != -1) {
                v3[v0] = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".charAt(v1);
            }
        }

        return new String(v3);
    }

    private static native void setYuvBitmapPixels(Bitmap arg0, byte[] arg1) {
    }
}

