package org.telegram.a;

public class c {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public String i;
    public boolean j;
    public boolean k;

    public c() {
        super();
    }

    String a(String arg13, String arg14, String arg15) {
        int v9;
        StringBuilder v0 = new StringBuilder(20);
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        while(v2 < this.i.length()) {
            int v7 = this.i.charAt(v2);
            if(v7 != 35) {
                int v10 = 110;
                int v11 = 99;
                if(v7 == 40) {
                    if(v3 >= arg13.length()) {
                        goto label_35;
                    }

                    v5 = 1;
                }
                else if(v7 == v11) {
                    if(arg14 != null) {
                        v0.append(arg14);
                    }

                    v4 = 1;
                    goto label_69;
                }
                else if(v7 != v10) {
                }
                else {
                    if(arg15 != null) {
                        v0.append(arg15);
                    }

                    v6 = 1;
                    goto label_69;
                }

            label_35:
                if(v7 == 32 && v2 > 0) {
                    v9 = v2 - 1;
                    if(this.i.charAt(v9) == v10 && arg15 == null) {
                        goto label_69;
                    }

                    if(this.i.charAt(v9) != v11) {
                        goto label_47;
                    }

                    if(arg14 == null) {
                        goto label_69;
                    }
                }

            label_47:
                v9 = 41;
                if(v3 >= arg13.length()) {
                    if(v5 == 0) {
                    }
                    else if(v7 == v9) {
                        goto label_52;
                    }

                    goto label_69;
                }

            label_52:
                v0.append(this.i.substring(v2, v2 + 1));
                if(v7 != v9) {
                    goto label_69;
                }

                v5 = 0;
            }
            else {
                if(v3 < arg13.length()) {
                    v7 = v3 + 1;
                    v0.append(arg13.substring(v3, v7));
                    v3 = v7;
                    goto label_69;
                }

                if(v5 == 0) {
                    goto label_69;
                }

                v0.append(" ");
            }

        label_69:
            ++v2;
        }

        if(arg14 != null && v4 == 0) {
            v0.insert(0, String.format("%s ", arg14));
        }
        else if(arg15 != null && v6 == 0) {
            v0.insert(0, arg15);
        }

        return v0.toString();
    }
}

