package com.googlecode.mp4parser.srt;

import com.googlecode.mp4parser.authoring.tracks.TextTrackImpl$Line;
import com.googlecode.mp4parser.authoring.tracks.TextTrackImpl;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class SrtParser {
    public SrtParser() {
        super();
    }

    private static long parse(String arg13) {
        return Long.parseLong(arg13.split(":")[0].trim()) * 60 * 60 * 1000 + Long.parseLong(arg13.split(":")[1].trim()) * 60 * 1000 + Long.parseLong(arg13.split(":")[2].split(",")[0].trim()) * 1000 + Long.parseLong(arg13.split(":")[2].split(",")[1].trim());
    }

    public static TextTrackImpl parse(InputStream arg9) {
        LineNumberReader v0 = new LineNumberReader(new InputStreamReader(arg9, "UTF-8"));
        TextTrackImpl v9 = new TextTrackImpl();
        while(v0.readLine() != null) {
            String v1 = v0.readLine();
            String v8 = "";
            while(true) {
                String v2 = v0.readLine();
                if(v2 != null) {
                    if(v2.trim().equals("")) {
                    }
                    else {
                        StringBuilder v3 = new StringBuilder(String.valueOf(v8));
                        v3.append(v2);
                        v3.append("\n");
                        v8 = v3.toString();
                        continue;
                    }
                }

                break;
            }

            v9.getSubs().add(new Line(SrtParser.parse(v1.split("-->")[0]), SrtParser.parse(v1.split("-->")[1]), v8));
        }

        return v9;
    }
}

