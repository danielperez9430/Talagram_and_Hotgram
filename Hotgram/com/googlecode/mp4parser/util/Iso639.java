package com.googlecode.mp4parser.util;

import java.util.HashMap;
import java.util.Map;

public class Iso639 {
    static Map threeToTwo;
    static Map twoToThree;

    static {
        Iso639.twoToThree = new HashMap();
        Iso639.threeToTwo = new HashMap();
        Iso639.doublePut("ab", "abk");
        Iso639.doublePut("aa", "aar");
        Iso639.doublePut("af", "afr");
        Iso639.doublePut("ak", "aka");
        Iso639.doublePut("sq", "sqi");
        Iso639.doublePut("am", "amh");
        Iso639.doublePut("ar", "ara");
        Iso639.doublePut("an", "arg");
        Iso639.doublePut("hy", "hye");
        Iso639.doublePut("as", "asm");
        Iso639.doublePut("av", "ava");
        Iso639.doublePut("ae", "ave");
        Iso639.doublePut("ay", "aym");
        Iso639.doublePut("az", "aze");
        Iso639.doublePut("bm", "bam");
        Iso639.doublePut("ba", "bak");
        Iso639.doublePut("eu", "eus");
        Iso639.doublePut("be", "bel");
        Iso639.doublePut("bn", "ben");
        Iso639.doublePut("bh", "bih");
        Iso639.doublePut("bi", "bis");
        Iso639.doublePut("bs", "bos");
        Iso639.doublePut("br", "bre");
        Iso639.doublePut("bg", "bul");
        Iso639.doublePut("my", "mya");
        Iso639.doublePut("ca", "cat");
        Iso639.doublePut("ch", "cha");
        Iso639.doublePut("ce", "che");
        Iso639.doublePut("ny", "nya");
        Iso639.doublePut("zh", "zho");
        Iso639.doublePut("cv", "chv");
        Iso639.doublePut("kw", "cor");
        Iso639.doublePut("co", "cos");
        Iso639.doublePut("cr", "cre");
        Iso639.doublePut("hr", "hrv");
        Iso639.doublePut("cs", "ces");
        Iso639.doublePut("da", "dan");
        Iso639.doublePut("dv", "div");
        Iso639.doublePut("nl", "nld");
        Iso639.doublePut("dz", "dzo");
        Iso639.doublePut("en", "eng");
        Iso639.doublePut("eo", "epo");
        Iso639.doublePut("et", "est");
        Iso639.doublePut("ee", "ewe");
        Iso639.doublePut("fo", "fao");
        Iso639.doublePut("fj", "fij");
        Iso639.doublePut("fi", "fin");
        Iso639.doublePut("fr", "fra");
        Iso639.doublePut("ff", "ful");
        Iso639.doublePut("gl", "glg");
        Iso639.doublePut("ka", "kat");
        Iso639.doublePut("de", "deu");
        Iso639.doublePut("el", "ell");
        Iso639.doublePut("gn", "grn");
        Iso639.doublePut("gu", "guj");
        Iso639.doublePut("ht", "hat");
        Iso639.doublePut("ha", "hau");
        Iso639.doublePut("he", "heb");
        Iso639.doublePut("hz", "her");
        Iso639.doublePut("hi", "hin");
        Iso639.doublePut("ho", "hmo");
        Iso639.doublePut("hu", "hun");
        Iso639.doublePut("ia", "ina");
        Iso639.doublePut("id", "ind");
        Iso639.doublePut("ie", "ile");
        Iso639.doublePut("ga", "gle");
        Iso639.doublePut("ig", "ibo");
        Iso639.doublePut("ik", "ipk");
        Iso639.doublePut("io", "ido");
        Iso639.doublePut("is", "isl");
        Iso639.doublePut("it", "ita");
        Iso639.doublePut("iu", "iku");
        Iso639.doublePut("ja", "jpn");
        Iso639.doublePut("jv", "jav");
        Iso639.doublePut("kl", "kal");
        Iso639.doublePut("kn", "kan");
        Iso639.doublePut("kr", "kau");
        Iso639.doublePut("ks", "kas");
        Iso639.doublePut("kk", "kaz");
        Iso639.doublePut("km", "khm");
        Iso639.doublePut("ki", "kik");
        Iso639.doublePut("rw", "kin");
        Iso639.doublePut("ky", "kir");
        Iso639.doublePut("kv", "kom");
        Iso639.doublePut("kg", "kon");
        Iso639.doublePut("ko", "kor");
        Iso639.doublePut("ku", "kur");
        Iso639.doublePut("kj", "kua");
        Iso639.doublePut("la", "lat");
        Iso639.doublePut("lb", "ltz");
        Iso639.doublePut("lg", "lug");
        Iso639.doublePut("li", "lim");
        Iso639.doublePut("ln", "lin");
        Iso639.doublePut("lo", "lao");
        Iso639.doublePut("lt", "lit");
        Iso639.doublePut("lu", "lub");
        Iso639.doublePut("lv", "lav");
        Iso639.doublePut("gv", "glv");
        Iso639.doublePut("mk", "mkd");
        Iso639.doublePut("mg", "mlg");
        Iso639.doublePut("ms", "msa");
        Iso639.doublePut("ml", "mal");
        Iso639.doublePut("mt", "mlt");
        Iso639.doublePut("mi", "mri");
        Iso639.doublePut("mr", "mar");
        Iso639.doublePut("mh", "mah");
        Iso639.doublePut("mn", "mon");
        Iso639.doublePut("na", "nau");
        Iso639.doublePut("nv", "nav");
        Iso639.doublePut("nd", "nde");
        Iso639.doublePut("ne", "nep");
        Iso639.doublePut("ng", "ndo");
        Iso639.doublePut("nb", "nob");
        Iso639.doublePut("nn", "nno");
        Iso639.doublePut("no", "nor");
        Iso639.doublePut("ii", "iii");
        Iso639.doublePut("nr", "nbl");
        Iso639.doublePut("oc", "oci");
        Iso639.doublePut("oj", "oji");
        Iso639.doublePut("cu", "chu");
        Iso639.doublePut("om", "orm");
        Iso639.doublePut("or", "ori");
        Iso639.doublePut("os", "oss");
        Iso639.doublePut("pa", "pan");
        Iso639.doublePut("pi", "pli");
        Iso639.doublePut("fa", "fas");
        Iso639.doublePut("pl", "pol");
        Iso639.doublePut("ps", "pus");
        Iso639.doublePut("pt", "por");
        Iso639.doublePut("qu", "que");
        Iso639.doublePut("rm", "roh");
        Iso639.doublePut("rn", "run");
        Iso639.doublePut("ro", "ron");
        Iso639.doublePut("ru", "rus");
        Iso639.doublePut("sa", "san");
        Iso639.doublePut("sc", "srd");
        Iso639.doublePut("sd", "snd");
        Iso639.doublePut("se", "sme");
        Iso639.doublePut("sm", "smo");
        Iso639.doublePut("sg", "sag");
        Iso639.doublePut("sr", "srp");
        Iso639.doublePut("gd", "gla");
        Iso639.doublePut("sn", "sna");
        Iso639.doublePut("si", "sin");
        Iso639.doublePut("sk", "slk");
        Iso639.doublePut("sl", "slv");
        Iso639.doublePut("so", "som");
        Iso639.doublePut("st", "sot");
        Iso639.doublePut("es", "spa");
        Iso639.doublePut("su", "sun");
        Iso639.doublePut("sw", "swa");
        Iso639.doublePut("ss", "ssw");
        Iso639.doublePut("sv", "swe");
        Iso639.doublePut("ta", "tam");
        Iso639.doublePut("te", "tel");
        Iso639.doublePut("tg", "tgk");
        Iso639.doublePut("th", "tha");
        Iso639.doublePut("ti", "tir");
        Iso639.doublePut("bo", "bod");
        Iso639.doublePut("tk", "tuk");
        Iso639.doublePut("tl", "tgl");
        Iso639.doublePut("tn", "tsn");
        Iso639.doublePut("to", "ton");
        Iso639.doublePut("tr", "tur");
        Iso639.doublePut("ts", "tso");
        Iso639.doublePut("tt", "tat");
        Iso639.doublePut("tw", "twi");
        Iso639.doublePut("ty", "tah");
        Iso639.doublePut("ug", "uig");
        Iso639.doublePut("uk", "ukr");
        Iso639.doublePut("ur", "urd");
        Iso639.doublePut("uz", "uzb");
        Iso639.doublePut("ve", "ven");
        Iso639.doublePut("vi", "vie");
        Iso639.doublePut("vo", "vol");
        Iso639.doublePut("wa", "wln");
        Iso639.doublePut("cy", "cym");
        Iso639.doublePut("wo", "wol");
        Iso639.doublePut("fy", "fry");
        Iso639.doublePut("xh", "xho");
        Iso639.doublePut("yi", "yid");
        Iso639.doublePut("yo", "yor");
        Iso639.doublePut("za", "zha");
        Iso639.doublePut("zu", "zul");
    }

    public Iso639() {
        super();
    }

    public static String convert2to3(String arg1) {
        return Iso639.twoToThree.get(arg1);
    }

    public static String convert3to2(String arg1) {
        return Iso639.threeToTwo.get(arg1);
    }

    private static void doublePut(String arg1, String arg2) {
        Iso639.twoToThree.put(arg1, arg2);
        Iso639.threeToTwo.put(arg2, arg1);
    }
}

