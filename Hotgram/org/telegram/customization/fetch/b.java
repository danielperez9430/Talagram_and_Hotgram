package org.telegram.customization.fetch;

final class b {
    static int a(String arg2) {
        int v0 = -101;
        if(arg2 == null) {
            return v0;
        }

        if(!arg2.equalsIgnoreCase("FNC")) {
            if(arg2.equalsIgnoreCase("open failed: ENOENT (No such file or directory)")) {
            }
            else if(arg2.equalsIgnoreCase("TI")) {
                return -103;
            }
            else if(arg2.equalsIgnoreCase("DIE")) {
                return -118;
            }
            else {
                if(!arg2.equalsIgnoreCase("recvfrom failed: ETIMEDOUT (Connection timed out)")) {
                    if(arg2.equalsIgnoreCase("timeout")) {
                    }
                    else {
                        if(!arg2.equalsIgnoreCase("java.io.IOException: 404")) {
                            if(arg2.contains("No address associated with hostname")) {
                            }
                            else if(arg2.contains("Unable to resolve host")) {
                                return -105;
                            }
                            else if(arg2.equalsIgnoreCase("open failed: EACCES (Permission denied)")) {
                                return -107;
                            }
                            else {
                                if(!arg2.equalsIgnoreCase("write failed: ENOSPC (No space left on device)")) {
                                    if(arg2.equalsIgnoreCase("database or disk is full (code 13)")) {
                                    }
                                    else if(arg2.contains("SSRV:")) {
                                        return -110;
                                    }
                                    else if(arg2.contains("column _file_path is not unique")) {
                                        return -113;
                                    }
                                    else {
                                        return v0;
                                    }
                                }

                                return -108;
                            }
                        }

                        return -106;
                    }
                }

                return -104;
            }
        }

        return -102;
    }
}

