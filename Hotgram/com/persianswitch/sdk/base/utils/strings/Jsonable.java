package com.persianswitch.sdk.base.utils.strings;

import org.json.JSONException;

public interface Jsonable {
    public final class JsonParseException extends JSONException {
        public JsonParseException(String arg1) {
            super(arg1);
        }
    }

    public final class JsonWriteException extends JSONException {
        public JsonWriteException(String arg1) {
            super(arg1);
        }
    }

}

