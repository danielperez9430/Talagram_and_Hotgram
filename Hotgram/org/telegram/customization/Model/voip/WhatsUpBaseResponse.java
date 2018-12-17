package org.telegram.customization.Model.voip;

import org.telegram.customization.Model.WhatsupModel;

public class WhatsUpBaseResponse {
    WhatsupModel data;

    public WhatsUpBaseResponse() {
        super();
    }

    public WhatsupModel getData() {
        return this.data;
    }

    public void setData(WhatsupModel arg1) {
        this.data = arg1;
    }
}

