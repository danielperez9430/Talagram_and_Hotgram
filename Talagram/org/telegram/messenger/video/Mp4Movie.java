package org.telegram.messenger.video;

import android.media.MediaCodec$BufferInfo;
import android.media.MediaFormat;
import com.googlecode.mp4parser.util.Matrix;
import java.io.File;
import java.util.ArrayList;

public class Mp4Movie {
    private File cacheFile;
    private int height;
    private Matrix matrix;
    private ArrayList tracks;
    private int width;

    public Mp4Movie() {
        super();
        this.matrix = Matrix.ROTATE_0;
        this.tracks = new ArrayList();
    }

    public void addSample(int arg2, long arg3, MediaCodec$BufferInfo arg5) {
        if(arg2 >= 0) {
            if(arg2 >= this.tracks.size()) {
            }
            else {
                this.tracks.get(arg2).addSample(arg3, arg5);
            }
        }
    }

    public int addTrack(MediaFormat arg4, boolean arg5) {
        this.tracks.add(new Track(this.tracks.size(), arg4, arg5));
        return this.tracks.size() - 1;
    }

    public File getCacheFile() {
        return this.cacheFile;
    }

    public int getHeight() {
        return this.height;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public ArrayList getTracks() {
        return this.tracks;
    }

    public int getWidth() {
        return this.width;
    }

    public void setCacheFile(File arg1) {
        this.cacheFile = arg1;
    }

    public void setRotation(int arg2) {
        Matrix v2;
        if(arg2 == 0) {
            v2 = Matrix.ROTATE_0;
            goto label_2;
        }
        else if(arg2 == 90) {
            v2 = Matrix.ROTATE_90;
            goto label_2;
        }
        else if(arg2 == 180) {
            v2 = Matrix.ROTATE_180;
            goto label_2;
        }
        else if(arg2 == 270) {
            v2 = Matrix.ROTATE_270;
        label_2:
            this.matrix = v2;
        }
    }

    public void setSize(int arg1, int arg2) {
        this.width = arg1;
        this.height = arg2;
    }
}

