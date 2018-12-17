package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Atom {
    final class ContainerAtom extends Atom {
        public final List containerChildren;
        public final long endPosition;
        public final List leafChildren;

        public ContainerAtom(int arg1, long arg2) {
            super(arg1);
            this.endPosition = arg2;
            this.leafChildren = new ArrayList();
            this.containerChildren = new ArrayList();
        }

        public void add(ContainerAtom arg2) {
            this.containerChildren.add(arg2);
        }

        public void add(LeafAtom arg2) {
            this.leafChildren.add(arg2);
        }

        public int getChildAtomOfTypeCount(int arg6) {
            int v0 = this.leafChildren.size();
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            while(v2 < v0) {
                if(this.leafChildren.get(v2).type == arg6) {
                    ++v3;
                }

                ++v2;
            }

            v0 = this.containerChildren.size();
            while(v1 < v0) {
                if(this.containerChildren.get(v1).type == arg6) {
                    ++v3;
                }

                ++v1;
            }

            return v3;
        }

        public ContainerAtom getContainerAtomOfType(int arg5) {
            int v0 = this.containerChildren.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.containerChildren.get(v1);
                if(((ContainerAtom)v2).type == arg5) {
                    return ((ContainerAtom)v2);
                }
            }

            return null;
        }

        public LeafAtom getLeafAtomOfType(int arg5) {
            int v0 = this.leafChildren.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.leafChildren.get(v1);
                if(((LeafAtom)v2).type == arg5) {
                    return ((LeafAtom)v2);
                }
            }

            return null;
        }

        public String toString() {
            return ContainerAtom.getAtomTypeString(this.type) + " leaves: " + Arrays.toString(this.leafChildren.toArray()) + " containers: " + Arrays.toString(this.containerChildren.toArray());
        }
    }

    final class LeafAtom extends Atom {
        public final ParsableByteArray data;

        public LeafAtom(int arg1, ParsableByteArray arg2) {
            super(arg1);
            this.data = arg2;
        }
    }

    public static final int DEFINES_LARGE_SIZE = 1;
    public static final int EXTENDS_TO_END_SIZE = 0;
    public static final int FULL_HEADER_SIZE = 12;
    public static final int HEADER_SIZE = 8;
    public static final int LONG_HEADER_SIZE = 16;
    public static final int TYPE_TTML;
    public static final int TYPE__mp3;
    public static final int TYPE_ac_3;
    public static final int TYPE_alac;
    public static final int TYPE_avc1;
    public static final int TYPE_avc3;
    public static final int TYPE_avcC;
    public static final int TYPE_c608;
    public static final int TYPE_camm;
    public static final int TYPE_co64;
    public static final int TYPE_ctts;
    public static final int TYPE_d263;
    public static final int TYPE_dac3;
    public static final int TYPE_data;
    public static final int TYPE_ddts;
    public static final int TYPE_dec3;
    public static final int TYPE_dtsc;
    public static final int TYPE_dtse;
    public static final int TYPE_dtsh;
    public static final int TYPE_dtsl;
    public static final int TYPE_ec_3;
    public static final int TYPE_edts;
    public static final int TYPE_elst;
    public static final int TYPE_emsg;
    public static final int TYPE_enca;
    public static final int TYPE_encv;
    public static final int TYPE_esds;
    public static final int TYPE_frma;
    public static final int TYPE_ftyp;
    public static final int TYPE_hdlr;
    public static final int TYPE_hev1;
    public static final int TYPE_hvc1;
    public static final int TYPE_hvcC;
    public static final int TYPE_ilst;
    public static final int TYPE_lpcm;
    public static final int TYPE_mdat;
    public static final int TYPE_mdhd;
    public static final int TYPE_mdia;
    public static final int TYPE_mean;
    public static final int TYPE_mehd;
    public static final int TYPE_meta;
    public static final int TYPE_minf;
    public static final int TYPE_moof;
    public static final int TYPE_moov;
    public static final int TYPE_mp4a;
    public static final int TYPE_mp4v;
    public static final int TYPE_mvex;
    public static final int TYPE_mvhd;
    public static final int TYPE_name;
    public static final int TYPE_pasp;
    public static final int TYPE_proj;
    public static final int TYPE_pssh;
    public static final int TYPE_s263;
    public static final int TYPE_saio;
    public static final int TYPE_saiz;
    public static final int TYPE_samr;
    public static final int TYPE_sawb;
    public static final int TYPE_sbgp;
    public static final int TYPE_schi;
    public static final int TYPE_schm;
    public static final int TYPE_senc;
    public static final int TYPE_sgpd;
    public static final int TYPE_sidx;
    public static final int TYPE_sinf;
    public static final int TYPE_sowt;
    public static final int TYPE_st3d;
    public static final int TYPE_stbl;
    public static final int TYPE_stco;
    public static final int TYPE_stpp;
    public static final int TYPE_stsc;
    public static final int TYPE_stsd;
    public static final int TYPE_stss;
    public static final int TYPE_stsz;
    public static final int TYPE_stts;
    public static final int TYPE_stz2;
    public static final int TYPE_sv3d;
    public static final int TYPE_tenc;
    public static final int TYPE_tfdt;
    public static final int TYPE_tfhd;
    public static final int TYPE_tkhd;
    public static final int TYPE_traf;
    public static final int TYPE_trak;
    public static final int TYPE_trex;
    public static final int TYPE_trun;
    public static final int TYPE_tx3g;
    public static final int TYPE_udta;
    public static final int TYPE_uuid;
    public static final int TYPE_vmhd;
    public static final int TYPE_vp08;
    public static final int TYPE_vp09;
    public static final int TYPE_vpcC;
    public static final int TYPE_wave;
    public static final int TYPE_wvtt;
    public final int type;

    static {
        Atom.TYPE_ftyp = Util.getIntegerCodeForString("ftyp");
        Atom.TYPE_avc1 = Util.getIntegerCodeForString("avc1");
        Atom.TYPE_avc3 = Util.getIntegerCodeForString("avc3");
        Atom.TYPE_hvc1 = Util.getIntegerCodeForString("hvc1");
        Atom.TYPE_hev1 = Util.getIntegerCodeForString("hev1");
        Atom.TYPE_s263 = Util.getIntegerCodeForString("s263");
        Atom.TYPE_d263 = Util.getIntegerCodeForString("d263");
        Atom.TYPE_mdat = Util.getIntegerCodeForString("mdat");
        Atom.TYPE_mp4a = Util.getIntegerCodeForString("mp4a");
        Atom.TYPE__mp3 = Util.getIntegerCodeForString(".mp3");
        Atom.TYPE_wave = Util.getIntegerCodeForString("wave");
        Atom.TYPE_lpcm = Util.getIntegerCodeForString("lpcm");
        Atom.TYPE_sowt = Util.getIntegerCodeForString("sowt");
        Atom.TYPE_ac_3 = Util.getIntegerCodeForString("ac-3");
        Atom.TYPE_dac3 = Util.getIntegerCodeForString("dac3");
        Atom.TYPE_ec_3 = Util.getIntegerCodeForString("ec-3");
        Atom.TYPE_dec3 = Util.getIntegerCodeForString("dec3");
        Atom.TYPE_dtsc = Util.getIntegerCodeForString("dtsc");
        Atom.TYPE_dtsh = Util.getIntegerCodeForString("dtsh");
        Atom.TYPE_dtsl = Util.getIntegerCodeForString("dtsl");
        Atom.TYPE_dtse = Util.getIntegerCodeForString("dtse");
        Atom.TYPE_ddts = Util.getIntegerCodeForString("ddts");
        Atom.TYPE_tfdt = Util.getIntegerCodeForString("tfdt");
        Atom.TYPE_tfhd = Util.getIntegerCodeForString("tfhd");
        Atom.TYPE_trex = Util.getIntegerCodeForString("trex");
        Atom.TYPE_trun = Util.getIntegerCodeForString("trun");
        Atom.TYPE_sidx = Util.getIntegerCodeForString("sidx");
        Atom.TYPE_moov = Util.getIntegerCodeForString("moov");
        Atom.TYPE_mvhd = Util.getIntegerCodeForString("mvhd");
        Atom.TYPE_trak = Util.getIntegerCodeForString("trak");
        Atom.TYPE_mdia = Util.getIntegerCodeForString("mdia");
        Atom.TYPE_minf = Util.getIntegerCodeForString("minf");
        Atom.TYPE_stbl = Util.getIntegerCodeForString("stbl");
        Atom.TYPE_avcC = Util.getIntegerCodeForString("avcC");
        Atom.TYPE_hvcC = Util.getIntegerCodeForString("hvcC");
        Atom.TYPE_esds = Util.getIntegerCodeForString("esds");
        Atom.TYPE_moof = Util.getIntegerCodeForString("moof");
        Atom.TYPE_traf = Util.getIntegerCodeForString("traf");
        Atom.TYPE_mvex = Util.getIntegerCodeForString("mvex");
        Atom.TYPE_mehd = Util.getIntegerCodeForString("mehd");
        Atom.TYPE_tkhd = Util.getIntegerCodeForString("tkhd");
        Atom.TYPE_edts = Util.getIntegerCodeForString("edts");
        Atom.TYPE_elst = Util.getIntegerCodeForString("elst");
        Atom.TYPE_mdhd = Util.getIntegerCodeForString("mdhd");
        Atom.TYPE_hdlr = Util.getIntegerCodeForString("hdlr");
        Atom.TYPE_stsd = Util.getIntegerCodeForString("stsd");
        Atom.TYPE_pssh = Util.getIntegerCodeForString("pssh");
        Atom.TYPE_sinf = Util.getIntegerCodeForString("sinf");
        Atom.TYPE_schm = Util.getIntegerCodeForString("schm");
        Atom.TYPE_schi = Util.getIntegerCodeForString("schi");
        Atom.TYPE_tenc = Util.getIntegerCodeForString("tenc");
        Atom.TYPE_encv = Util.getIntegerCodeForString("encv");
        Atom.TYPE_enca = Util.getIntegerCodeForString("enca");
        Atom.TYPE_frma = Util.getIntegerCodeForString("frma");
        Atom.TYPE_saiz = Util.getIntegerCodeForString("saiz");
        Atom.TYPE_saio = Util.getIntegerCodeForString("saio");
        Atom.TYPE_sbgp = Util.getIntegerCodeForString("sbgp");
        Atom.TYPE_sgpd = Util.getIntegerCodeForString("sgpd");
        Atom.TYPE_uuid = Util.getIntegerCodeForString("uuid");
        Atom.TYPE_senc = Util.getIntegerCodeForString("senc");
        Atom.TYPE_pasp = Util.getIntegerCodeForString("pasp");
        Atom.TYPE_TTML = Util.getIntegerCodeForString("TTML");
        Atom.TYPE_vmhd = Util.getIntegerCodeForString("vmhd");
        Atom.TYPE_mp4v = Util.getIntegerCodeForString("mp4v");
        Atom.TYPE_stts = Util.getIntegerCodeForString("stts");
        Atom.TYPE_stss = Util.getIntegerCodeForString("stss");
        Atom.TYPE_ctts = Util.getIntegerCodeForString("ctts");
        Atom.TYPE_stsc = Util.getIntegerCodeForString("stsc");
        Atom.TYPE_stsz = Util.getIntegerCodeForString("stsz");
        Atom.TYPE_stz2 = Util.getIntegerCodeForString("stz2");
        Atom.TYPE_stco = Util.getIntegerCodeForString("stco");
        Atom.TYPE_co64 = Util.getIntegerCodeForString("co64");
        Atom.TYPE_tx3g = Util.getIntegerCodeForString("tx3g");
        Atom.TYPE_wvtt = Util.getIntegerCodeForString("wvtt");
        Atom.TYPE_stpp = Util.getIntegerCodeForString("stpp");
        Atom.TYPE_c608 = Util.getIntegerCodeForString("c608");
        Atom.TYPE_samr = Util.getIntegerCodeForString("samr");
        Atom.TYPE_sawb = Util.getIntegerCodeForString("sawb");
        Atom.TYPE_udta = Util.getIntegerCodeForString("udta");
        Atom.TYPE_meta = Util.getIntegerCodeForString("meta");
        Atom.TYPE_ilst = Util.getIntegerCodeForString("ilst");
        Atom.TYPE_mean = Util.getIntegerCodeForString("mean");
        Atom.TYPE_name = Util.getIntegerCodeForString("name");
        Atom.TYPE_data = Util.getIntegerCodeForString("data");
        Atom.TYPE_emsg = Util.getIntegerCodeForString("emsg");
        Atom.TYPE_st3d = Util.getIntegerCodeForString("st3d");
        Atom.TYPE_sv3d = Util.getIntegerCodeForString("sv3d");
        Atom.TYPE_proj = Util.getIntegerCodeForString("proj");
        Atom.TYPE_vp08 = Util.getIntegerCodeForString("vp08");
        Atom.TYPE_vp09 = Util.getIntegerCodeForString("vp09");
        Atom.TYPE_vpcC = Util.getIntegerCodeForString("vpcC");
        Atom.TYPE_camm = Util.getIntegerCodeForString("camm");
        Atom.TYPE_alac = Util.getIntegerCodeForString("alac");
    }

    public Atom(int arg1) {
        super();
        this.type = arg1;
    }

    public static String getAtomTypeString(int arg2) {
        return "" + (((char)(arg2 >> 24 & 255))) + (((char)(arg2 >> 16 & 255))) + (((char)(arg2 >> 8 & 255))) + (((char)(arg2 & 255)));
    }

    public static int parseFullAtomFlags(int arg1) {
        return arg1 & 16777215;
    }

    public static int parseFullAtomVersion(int arg0) {
        return arg0 >> 24 & 255;
    }

    public String toString() {
        return Atom.getAtomTypeString(this.type);
    }
}

