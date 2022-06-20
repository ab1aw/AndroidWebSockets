package com.persistentsystems.socketclient.messaging;

import com.persistentsystems.socketclient.messaging.interfaces.AbstractWrMessageBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is an (internal) class for build an API compliant Iperf message.
 */
public class WrJsonIperfMessageBuilder extends AbstractWrMessageBuilder {

    private final String TCP_CMD = "tcp_test";
    private final JSONObject mRoot;

    /**
     * Public constructor.
     */
    public WrJsonIperfMessageBuilder() {
        super();
        mRoot = new JSONObject();
        try {
            mRoot.put("protocol_version", PROTO);
            mRoot.put("msgtype", REQ);
            mRoot.put("command", TCP_CMD);
            mRoot.put("token", WrMessageToken.getIperfToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject buildRootObject() {
        try {
            mRoot.put("password", mAuth.getPassword());
            mRoot.put("username", mAuth.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mRoot;
    }

    @Override
    public String build() {
        return buildRootObject().toString();
    }

    /**
     * This determines if the iperf test performed was transmit only.
     * @return true if yes, false if no.
     */
    public boolean isTxOnly() {
        try {
            return mRoot.has("tx_only") && mRoot.getBoolean("tx_only");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This tells the API to only perform a TX test.
     * @param xTx transmit only?
     * @return `this` so messages can be built easier.
     */
    public WrJsonIperfMessageBuilder setTxOnly(boolean xTx) {
        try {
            mRoot.put("tx_only", xTx);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * This tells the API which remote (IP) to perform the iperf test to.
     * @param xTarget ip
     * @return `this` so messages can be built easier.
     */
    public WrJsonIperfMessageBuilder setTarget(String xTarget) {
        try {
            mRoot.put("ip", xTarget);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Retreives the targeted IP from the response messages.
     * @return ip
     */
    public String getTargetIp() {
        if (mRoot.has("ip")) {
            try {
                return mRoot.getString("ip");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * Retreives a bool of if a detailed iperf report was run
     * @return detailed
     */
    public boolean isDetailed() {
        try {
            return mRoot.has("detailed_report") && mRoot.getBoolean("detailed_report");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This tells the API to run a detailed report and pull more iperf information.
     * @param xDetailed
     * @return `this` so messages can be built easier.
     */
    public WrJsonIperfMessageBuilder setDetailed(boolean xDetailed) {
        try {
            mRoot.put("detailed_report", xDetailed);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * This tells iperf to return additional metadata about the communication.
     * See usermanual/documentation, however this generally means neighbor information
     * as well as location information.
     * This is not required to be set.
     * @param xMetaData true to pull more info
     * @return `this` so messages can be built easier.
     */
    public WrJsonIperfMessageBuilder setMetaData(boolean xMetaData) {
        try {
            mRoot.put("meta_data", xMetaData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Determines if the received message has metadata
     * @return true/false
     */
    public boolean hasMetaData() {
        try {
            return mRoot.has("meta_data") && mRoot.getBoolean("meta_data");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the length of this particular iperf test
     * @return length in seconds
     */
    public int getLength() {
        if (mRoot.has("time_seconds")) {
            try {
                return mRoot.getInt("time_seconds");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * Sets the length, in seconds, to run this iperf test.
     * Note that if {@link #setTxOnly(boolean)} is set to false (or unset),
     * the total iperf length will be double the lenght set here.
     * @param xLength length in seconds
     * @return `this` so messages can be built easier.
     */
    public WrJsonIperfMessageBuilder setLength(int xLength) {
        try {
            mRoot.put("time_seconds", xLength);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

}
