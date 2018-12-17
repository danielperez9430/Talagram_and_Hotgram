package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.amr.AmrExtractor;
import com.google.android.exoplayer2.extractor.flv.FlvExtractor;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.extractor.ogg.OggExtractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.extractor.wav.WavExtractor;
import java.lang.reflect.Constructor;

public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final Constructor FLAC_EXTRACTOR_CONSTRUCTOR;
    private int fragmentedMp4Flags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private int tsMode;

    static {
        Constructor v0_1;
        try {
            v0_1 = Class.forName("com.google.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor();
        }
        catch(Exception v0) {
            throw new RuntimeException("Error instantiating FLAC extension", ((Throwable)v0));
        }
        catch(ClassNotFoundException ) {
            v0_1 = null;
        }

        DefaultExtractorsFactory.FLAC_EXTRACTOR_CONSTRUCTOR = v0_1;
    }

    public DefaultExtractorsFactory() {
        super();
        this.tsMode = 1;
    }

    public Extractor[] createExtractors() {
        Extractor[] v0_2;
        __monitor_enter(this);
        try {
            int v1 = 12;
            int v0_1 = DefaultExtractorsFactory.FLAC_EXTRACTOR_CONSTRUCTOR == null ? 12 : 13;
            v0_2 = new Extractor[v0_1];
            v0_2[0] = new MatroskaExtractor(this.matroskaFlags);
            v0_2[1] = new FragmentedMp4Extractor(this.fragmentedMp4Flags);
            v0_2[2] = new Mp4Extractor(this.mp4Flags);
            v0_2[3] = new Mp3Extractor(this.mp3Flags);
            v0_2[4] = new AdtsExtractor();
            v0_2[5] = new Ac3Extractor();
            v0_2[6] = new TsExtractor(this.tsMode, this.tsFlags);
            v0_2[7] = new FlvExtractor();
            v0_2[8] = new OggExtractor();
            v0_2[9] = new PsExtractor();
            v0_2[10] = new WavExtractor();
            v0_2[11] = new AmrExtractor();
            if(DefaultExtractorsFactory.FLAC_EXTRACTOR_CONSTRUCTOR != null) {
                try {
                    v0_2[v1] = DefaultExtractorsFactory.FLAC_EXTRACTOR_CONSTRUCTOR.newInstance();
                }
                catch(Exception v0_3) {
                    try {
                        throw new IllegalStateException("Unexpected error creating FLAC extractor", ((Throwable)v0_3));
                    }
                    catch(Throwable v0) {
                    label_77:
                        __monitor_exit(this);
                        throw v0;
                    }
                }
            }
        }
        catch(Throwable v0) {
            goto label_77;
        }

        __monitor_exit(this);
        return v0_2;
    }

    public DefaultExtractorsFactory setFragmentedMp4ExtractorFlags(int arg1) {
        __monitor_enter(this);
        try {
            this.fragmentedMp4Flags = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return this;
    }

    public DefaultExtractorsFactory setMatroskaExtractorFlags(int arg1) {
        __monitor_enter(this);
        try {
            this.matroskaFlags = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return this;
    }

    public DefaultExtractorsFactory setMp3ExtractorFlags(int arg1) {
        __monitor_enter(this);
        try {
            this.mp3Flags = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return this;
    }

    public DefaultExtractorsFactory setMp4ExtractorFlags(int arg1) {
        __monitor_enter(this);
        try {
            this.mp4Flags = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return this;
    }

    public DefaultExtractorsFactory setTsExtractorFlags(int arg1) {
        __monitor_enter(this);
        try {
            this.tsFlags = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return this;
    }

    public DefaultExtractorsFactory setTsExtractorMode(int arg1) {
        __monitor_enter(this);
        try {
            this.tsMode = arg1;
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
        return this;
    }
}

