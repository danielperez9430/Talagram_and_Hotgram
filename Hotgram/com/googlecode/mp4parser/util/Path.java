package com.googlecode.mp4parser.util;

import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.AbstractContainerBox;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Path {
    static Pattern component;

    static {
        Path.component = Pattern.compile("(....|\\.\\.)(\\[(.*)\\])?");
    }

    private Path() {
        super();
    }

    public static String createPath(Box arg1) {
        return Path.createPath(arg1, "");
    }

    private static String createPath(Box arg7, String arg8) {
        Container v0 = arg7.getParent();
        Iterator v1 = v0.getBoxes().iterator();
        int v3 = 0;
        while(true) {
            if(v1.hasNext()) {
                Object v4 = v1.next();
                if(!((Box)v4).getType().equals(arg7.getType())) {
                    continue;
                }

                if((((Box)v4)) != arg7) {
                    ++v3;
                    continue;
                }
            }
            else {
            }

            break;
        }

        StringBuilder v1_1 = new StringBuilder(String.valueOf(String.format("/%s[%d]", arg7.getType(), Integer.valueOf(v3))));
        v1_1.append(arg8);
        String v7 = v1_1.toString();
        if((v0 instanceof Box)) {
            v7 = Path.createPath(((Box)v0), v7);
        }

        return v7;
    }

    public static Box getPath(Container arg1, String arg2) {
        Box v1_1;
        List v1 = Path.getPaths(arg1, arg2, true);
        if(v1.isEmpty()) {
            v1_1 = null;
        }
        else {
            Object v1_2 = v1.get(0);
        }

        return v1_1;
    }

    public static Box getPath(AbstractContainerBox arg1, String arg2) {
        Box v1_1;
        List v1 = Path.getPaths(arg1, arg2, true);
        if(v1.isEmpty()) {
            v1_1 = null;
        }
        else {
            Object v1_2 = v1.get(0);
        }

        return v1_1;
    }

    public static Box getPath(Box arg1, String arg2) {
        List v1 = Path.getPaths(arg1, arg2, true);
        if(v1.isEmpty()) {
            arg1 = null;
        }
        else {
            Object v1_1 = v1.get(0);
        }

        return arg1;
    }

    public static List getPaths(Container arg1, String arg2) {
        return Path.getPaths(arg1, arg2, false);
    }

    private static List getPaths(Box arg0, String arg1, boolean arg2) {
        return Path.getPaths(arg0, arg1, arg2);
    }

    private static List getPaths(Container arg0, String arg1, boolean arg2) {
        return Path.getPaths(arg0, arg1, arg2);
    }

    private static List getPaths(AbstractContainerBox arg0, String arg1, boolean arg2) {
        return Path.getPaths(arg0, arg1, arg2);
    }

    public static List getPaths(Box arg1, String arg2) {
        return Path.getPaths(arg1, arg2, false);
    }

    private static List getPaths(Object arg7, String arg8, boolean arg9) {
        String v3;
        Container v7;
        if(arg8.startsWith("/")) {
            String v0 = arg8.substring(1);
            while((arg7 instanceof Box)) {
                v7 = ((Box)v7).getParent();
            }

            arg8 = v0;
        }

        if(arg8.length() == 0) {
            if((v7 instanceof Box)) {
                return Collections.singletonList(v7);
            }

            throw new RuntimeException("Result of path expression seems to be the root container. This is not allowed!");
        }

        int v2 = 0;
        if(arg8.contains("/")) {
            v3 = arg8.substring(arg8.indexOf(47) + 1);
            arg8 = arg8.substring(0, arg8.indexOf(47));
        }
        else {
            v3 = "";
        }

        Matcher v0_1 = Path.component.matcher(((CharSequence)arg8));
        if(!v0_1.matches()) {
            goto label_82;
        }

        arg8 = v0_1.group(1);
        if("..".equals(arg8)) {
            if((v7 instanceof Box)) {
                return Path.getPaths(((Box)v7).getParent(), v3, arg9);
            }

            return Collections.emptyList();
        }

        if(!(v7 instanceof Container)) {
            goto label_80;
        }

        int v4 = -1;
        int v0_2 = v0_1.group(2) != null ? Integer.parseInt(v0_1.group(3)) : -1;
        LinkedList v1 = new LinkedList();
        Iterator v7_1 = v7.getBoxes().iterator();
        do {
        label_63:
            if(!v7_1.hasNext()) {
                goto label_65;
            }

            Object v5 = v7_1.next();
            if(((Box)v5).getType().matches(arg8)) {
                if(v0_2 == v4 || v0_2 == v2) {
                    ((List)v1).addAll(Path.getPaths(((Box)v5), v3, arg9));
                }

                ++v2;
            }

            if(!arg9 && v0_2 < 0) {
                goto label_63;
            }
        }
        while(((List)v1).isEmpty());

        return ((List)v1);
    label_65:
        return ((List)v1);
    label_80:
        return Collections.emptyList();
    label_82:
        StringBuilder v9 = new StringBuilder(String.valueOf(arg8));
        v9.append(" is invalid path.");
        throw new RuntimeException(v9.toString());
    }

    public static boolean isContained(Box arg0, String arg1) {
        return Path.getPaths(arg0, arg1).contains(arg0);
    }
}

