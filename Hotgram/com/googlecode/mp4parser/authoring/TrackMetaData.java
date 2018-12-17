package com.googlecode.mp4parser.authoring;

import com.googlecode.mp4parser.util.Matrix;
import java.util.Date;

public class TrackMetaData implements Cloneable {
    private Date creationTime;
    private int group;
    private double height;
    private String language;
    int layer;
    private Matrix matrix;
    private Date modificationTime;
    private long timescale;
    private long trackId;
    private float volume;
    private double width;

    public TrackMetaData() {
        super();
        this.language = "eng";
        this.modificationTime = new Date();
        this.creationTime = new Date();
        this.matrix = Matrix.ROTATE_0;
        this.trackId = 1;
        this.group = 0;
    }

    public Object clone() {
        try {
            return super.clone();
        }
        catch(CloneNotSupportedException ) {
            return null;
        }
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public int getGroup() {
        return this.group;
    }

    public double getHeight() {
        return this.height;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getLayer() {
        return this.layer;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public Date getModificationTime() {
        return this.modificationTime;
    }

    public long getTimescale() {
        return this.timescale;
    }

    public long getTrackId() {
        return this.trackId;
    }

    public float getVolume() {
        return this.volume;
    }

    public double getWidth() {
        return this.width;
    }

    public void setCreationTime(Date arg1) {
        this.creationTime = arg1;
    }

    public void setGroup(int arg1) {
        this.group = arg1;
    }

    public void setHeight(double arg1) {
        this.height = arg1;
    }

    public void setLanguage(String arg1) {
        this.language = arg1;
    }

    public void setLayer(int arg1) {
        this.layer = arg1;
    }

    public void setMatrix(Matrix arg1) {
        this.matrix = arg1;
    }

    public void setModificationTime(Date arg1) {
        this.modificationTime = arg1;
    }

    public void setTimescale(long arg1) {
        this.timescale = arg1;
    }

    public void setTrackId(long arg1) {
        this.trackId = arg1;
    }

    public void setVolume(float arg1) {
        this.volume = arg1;
    }

    public void setWidth(double arg1) {
        this.width = arg1;
    }
}

