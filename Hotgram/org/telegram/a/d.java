package org.telegram.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d {
    public int a;
    public ArrayList b;
    public boolean c;
    public boolean d;
    public static Pattern e;

    static {
        d.e = Pattern.compile("[0-9]+");
    }

    public d() {
        super();
        this.b = new ArrayList();
    }

    String a(String arg7, String arg8, String arg9, boolean arg10) {
        Object v0_2;
        Iterator v10;
        Object v3;
        String v2 = null;
        if(arg7.length() >= this.a) {
            int v1 = 0;
            Matcher v0 = d.e.matcher(arg7.substring(0, this.a));
            if(v0.find()) {
                v1 = Integer.parseInt(v0.group(0));
            }

            Iterator v0_1 = this.b.iterator();
            do {
            label_15:
                if(!v0_1.hasNext()) {
                    goto label_53;
                }

                v3 = v0_1.next();
                if(v1 < ((c)v3).a) {
                    goto label_15;
                }

                if(v1 > ((c)v3).b) {
                    goto label_15;
                }

                if(arg7.length() > ((c)v3).d) {
                    goto label_15;
                }

                if(arg10) {
                    if(((((c)v3).g & 3) != 0 || arg9 != null || arg8 != null) && (arg9 == null || (((c)v3).g & 1) == 0)) {
                        if(arg8 == null) {
                            goto label_15;
                        }

                        if((((c)v3).g & 2) == 0) {
                            goto label_15;
                        }
                    }

                    return ((c)v3).a(arg7, arg8, arg9);
                }

                if(arg9 == null && arg8 == null) {
                    break;
                }

                if(arg9 != null && (((c)v3).g & 1) != 0) {
                    break;
                }

                if(arg8 == null) {
                    goto label_15;
                }
            }
            while((((c)v3).g & 2) == 0);

            return ((c)v3).a(arg7, arg8, arg9);
        label_53:
            if(arg10) {
                return v2;
            }

            if(arg8 != null) {
                v10 = this.b.iterator();
                goto label_57;
            }

            if(arg9 == null) {
                return v2;
            }

            v10 = this.b.iterator();
            do {
            label_76:
                if(!v10.hasNext()) {
                    return v2;
                }

                v0_2 = v10.next();
                if(v1 < ((c)v0_2).a) {
                    goto label_76;
                }

                if(v1 > ((c)v0_2).b) {
                    goto label_76;
                }

                if(arg7.length() > ((c)v0_2).d) {
                    goto label_76;
                }

                if(arg8 == null) {
                    break;
                }
            }
            while((((c)v0_2).g & 2) == 0);

            return ((c)v0_2).a(arg7, arg8, arg9);
            do {
            label_57:
                if(!v10.hasNext()) {
                    return v2;
                }

                v0_2 = v10.next();
                if(v1 < ((c)v0_2).a) {
                    goto label_57;
                }

                if(v1 > ((c)v0_2).b) {
                    goto label_57;
                }

                if(arg7.length() > ((c)v0_2).d) {
                    goto label_57;
                }

                if(arg9 == null) {
                    break;
                }
            }
            while((((c)v0_2).g & 1) == 0);

            return ((c)v0_2).a(arg7, arg8, arg9);
        }

        return v2;
    }
}

