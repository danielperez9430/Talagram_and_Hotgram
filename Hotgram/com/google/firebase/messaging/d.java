package com.google.firebase.messaging;

import java.util.Locale;

public final class d extends Exception {
    private final int a;

    d(String arg8) {
        super(arg8);
        int v0 = 4;
        if(arg8 != null) {
            arg8 = arg8.toLowerCase(Locale.US);
            int v5 = -1;
            switch(arg8.hashCode()) {
                case -1743242157: {
                    if(arg8.equals("service_not_available")) {
                        v5 = 3;
                    }
                    else {
                    }

                    break;
                }
                case -1290953729: {
                    if(arg8.equals("toomanymessages")) {
                        v5 = 4;
                    }
                    else {
                    }

                    break;
                }
                case -920906446: {
                    if(arg8.equals("invalid_parameters")) {
                        v5 = 0;
                    }
                    else {
                    }

                    break;
                }
                case -617027085: {
                    if(arg8.equals("messagetoobig")) {
                        v5 = 2;
                    }
                    else {
                    }

                    break;
                }
                case -95047692: {
                    if(arg8.equals("missing_to")) {
                        v5 = 1;
                    }
                    else {
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            switch(v5) {
                case 0: 
                case 1: {
                    goto label_43;
                }
                case 2: {
                    goto label_41;
                }
                case 3: {
                    goto label_39;
                }
                case 4: {
                    goto label_46;
                }
            }

            goto label_45;
        label_39:
            v0 = 3;
            goto label_46;
        label_41:
            v0 = 2;
            goto label_46;
        label_43:
            v0 = 1;
        }
        else {
        label_45:
            v0 = 0;
        }

    label_46:
        this.a = v0;
    }
}

