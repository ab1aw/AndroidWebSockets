package com.persistentsystems.socketclient.messaging;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is an (internal) API compliant message builder to build network messages.
 */
public class WrJsonNetworkMessageBuilder extends WrJsonMessageBuilder {
    private boolean mRequireAll = false;

    /**
     * Stores the `require all` api command into this message.
     * @param xValue require all
     */
    public void setRequireAll(boolean xValue) {
        mRequireAll = xValue;
    }

    @Override
    protected JSONObject buildRootObject() {
        JSONObject r = super.buildRootObject();
        try {
            r.put("require_all_nodes", mRequireAll);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }
}
