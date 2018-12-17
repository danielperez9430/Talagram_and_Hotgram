package com.googlecode.mp4parser.authoring;

import com.googlecode.mp4parser.util.Matrix;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Movie {
    Matrix matrix;
    List tracks;

    public Movie() {
        super();
        this.matrix = Matrix.ROTATE_0;
        this.tracks = new LinkedList();
    }

    public Movie(List arg2) {
        super();
        this.matrix = Matrix.ROTATE_0;
        this.tracks = new LinkedList();
        this.tracks = arg2;
    }

    public void addTrack(Track arg4) {
        if(this.getTrackByTrackId(arg4.getTrackMetaData().getTrackId()) != null) {
            arg4.getTrackMetaData().setTrackId(this.getNextTrackId());
        }

        this.tracks.add(arg4);
    }

    public static long gcd(long arg3, long arg5) {
        if(arg5 == 0) {
            return arg3;
        }

        return Movie.gcd(arg5, arg3 % arg5);
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public long getNextTrackId() {
        Iterator v0 = this.tracks.iterator();
        long v1;
        for(v1 = 0; v0.hasNext(); v1 = ((Track)v3).getTrackMetaData().getTrackId()) {
            Object v3 = v0.next();
            if(v1 >= ((Track)v3).getTrackMetaData().getTrackId()) {
                continue;
            }
        }

        return v1 + 1;
    }

    public long getTimescale() {
        long v0 = this.getTracks().iterator().next().getTrackMetaData().getTimescale();
        Iterator v2 = this.getTracks().iterator();
        while(v2.hasNext()) {
            v0 = Movie.gcd(v2.next().getTrackMetaData().getTimescale(), v0);
        }

        return v0;
    }

    public Track getTrackByTrackId(long arg6) {
        Object v1;
        Iterator v0 = this.tracks.iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(((Track)v1).getTrackMetaData().getTrackId() != arg6);

        return ((Track)v1);
    }

    public List getTracks() {
        return this.tracks;
    }

    public void setMatrix(Matrix arg1) {
        this.matrix = arg1;
    }

    public void setTracks(List arg1) {
        this.tracks = arg1;
    }

    public String toString() {
        String v0 = "Movie{ ";
        Iterator v1 = this.tracks.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            StringBuilder v3 = new StringBuilder(String.valueOf(v0));
            v3.append("track_");
            v3.append(((Track)v2).getTrackMetaData().getTrackId());
            v3.append(" (");
            v3.append(((Track)v2).getHandler());
            v3.append(") ");
            v0 = v3.toString();
        }

        StringBuilder v1_1 = new StringBuilder(String.valueOf(v0));
        v1_1.append('}');
        return v1_1.toString();
    }
}

