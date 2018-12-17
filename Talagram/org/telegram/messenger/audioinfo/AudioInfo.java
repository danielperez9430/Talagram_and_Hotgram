package org.telegram.messenger.audioinfo;

import android.graphics.Bitmap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import org.telegram.messenger.audioinfo.m4a.M4AInfo;
import org.telegram.messenger.audioinfo.mp3.MP3Info;

public abstract class AudioInfo {
    protected String album;
    protected String albumArtist;
    protected String artist;
    protected String brand;
    protected String comment;
    protected boolean compilation;
    protected String composer;
    protected String copyright;
    protected Bitmap cover;
    protected short disc;
    protected short discs;
    protected long duration;
    protected String genre;
    protected String grouping;
    protected String lyrics;
    protected Bitmap smallCover;
    protected String title;
    protected short track;
    protected short tracks;
    protected String version;
    protected short year;

    public AudioInfo() {
        super();
    }

    public String getAlbum() {
        return this.album;
    }

    public String getAlbumArtist() {
        return this.albumArtist;
    }

    public String getArtist() {
        return this.artist;
    }

    public static AudioInfo getAudioInfo(File arg5) {
        int v0 = 12;
        AudioInfo v1 = null;
        try {
            byte[] v0_1 = new byte[v0];
            RandomAccessFile v2 = new RandomAccessFile(arg5, "r");
            v2.readFully(v0_1, 0, 8);
            v2.close();
            BufferedInputStream v2_1 = new BufferedInputStream(new FileInputStream(arg5));
            if(v0_1[4] == 102 && v0_1[5] == 116 && v0_1[6] == 121 && v0_1[7] == 112) {
                return new M4AInfo(((InputStream)v2_1));
            }

            if(!arg5.getAbsolutePath().endsWith("mp3")) {
                return v1;
            }

            return new MP3Info(((InputStream)v2_1), arg5.length());
        }
        catch(Exception ) {
            return v1;
        }
    }

    public String getBrand() {
        return this.brand;
    }

    public String getComment() {
        return this.comment;
    }

    public String getComposer() {
        return this.composer;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public Bitmap getCover() {
        return this.cover;
    }

    public short getDisc() {
        return this.disc;
    }

    public short getDiscs() {
        return this.discs;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getGrouping() {
        return this.grouping;
    }

    public String getLyrics() {
        return this.lyrics;
    }

    public Bitmap getSmallCover() {
        return this.smallCover;
    }

    public String getTitle() {
        return this.title;
    }

    public short getTrack() {
        return this.track;
    }

    public short getTracks() {
        return this.tracks;
    }

    public String getVersion() {
        return this.version;
    }

    public short getYear() {
        return this.year;
    }

    public boolean isCompilation() {
        return this.compilation;
    }
}

