package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class EntityBuffer extends AbstractDataBuffer {
    private boolean zzoa;
    private ArrayList zzob;

    protected EntityBuffer(DataHolder arg1) {
        super(arg1);
        this.zzoa = false;
    }

    public final Object get(int arg2) {
        this.zzck();
        return this.getEntry(this.zzi(arg2), this.getChildCount(arg2));
    }

    protected int getChildCount(int arg6) {
        if(arg6 >= 0) {
            if(arg6 == this.zzob.size()) {
            }
            else {
                int v1 = arg6 == this.zzob.size() - 1 ? this.mDataHolder.getCount() : this.zzob.get(arg6 + 1).intValue();
                v1 -= this.zzob.get(arg6).intValue();
                if(v1 == 1) {
                    arg6 = this.zzi(arg6);
                    int v2 = this.mDataHolder.getWindowIndex(arg6);
                    String v3 = this.getChildDataMarkerColumn();
                    if(v3 != null && this.mDataHolder.getString(v3, arg6, v2) == null) {
                        return 0;
                    }
                }

                return v1;
            }
        }

        return 0;
    }

    protected String getChildDataMarkerColumn() {
        return null;
    }

    public int getCount() {
        this.zzck();
        return this.zzob.size();
    }

    protected abstract Object getEntry(int arg1, int arg2);

    protected abstract String getPrimaryDataMarkerColumn();

    private final void zzck() {
        int v5;
        __monitor_enter(this);
        try {
            if(!this.zzoa) {
                int v0_1 = this.mDataHolder.getCount();
                this.zzob = new ArrayList();
                if(v0_1 > 0) {
                    this.zzob.add(Integer.valueOf(0));
                    String v2 = this.getPrimaryDataMarkerColumn();
                    String v4 = this.mDataHolder.getString(v2, 0, this.mDataHolder.getWindowIndex(0));
                    int v3 = 1;
                    while(true) {
                        if(v3 < v0_1) {
                            v5 = this.mDataHolder.getWindowIndex(v3);
                            String v6 = this.mDataHolder.getString(v2, v3, v5);
                            if(v6 != null) {
                                if(!v6.equals(v4)) {
                                    this.zzob.add(Integer.valueOf(v3));
                                    v4 = v6;
                                }

                                ++v3;
                                continue;
                            }
                            else {
                                break;
                            }
                        }

                        goto label_53;
                    }

                    StringBuilder v4_1 = new StringBuilder(String.valueOf(v2).length() + 78);
                    v4_1.append("Missing value for markerColumn: ");
                    v4_1.append(v2);
                    v4_1.append(", at row: ");
                    v4_1.append(v3);
                    v4_1.append(", for window: ");
                    v4_1.append(v5);
                    throw new NullPointerException(v4_1.toString());
                }

            label_53:
                this.zzoa = true;
            }

            __monitor_exit(this);
            return;
        label_57:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_57;
        }

        throw v0;
    }

    final int zzi(int arg4) {
        if(arg4 >= 0 && arg4 < this.zzob.size()) {
            return this.zzob.get(arg4).intValue();
        }

        StringBuilder v2 = new StringBuilder(53);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(v2.toString());
    }
}

