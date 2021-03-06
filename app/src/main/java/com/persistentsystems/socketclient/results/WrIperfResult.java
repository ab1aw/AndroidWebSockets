package com.persistentsystems.socketclient.results;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents an Iperf response from the API.
 */
public class WrIperfResult {

    private JSONObject mRootObject = null;
    private JSONObject mThroughput = null;

    /**
     * Public constructor
     * @param xString JSON response, as string, from the socket
     */
    public WrIperfResult(String xString) {
        try {
            mRootObject = new JSONObject(xString);
            if (!mRootObject.has("throughput_test")) {
                throw new JSONException("Object does not contain a throughput_test object");
            }
            mThroughput = mRootObject.getJSONObject("throughput_test");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the originally parsed object incase additional
     * information is required.
     * @return json object
     */
    public JSONObject getJsonObject() {
        return mRootObject;
    }

    @Override
    public String toString() {
        return mRootObject.toString();
    }

    /**
     * Is this an iperf receive message?
     * @return is an rx message
     */
    public boolean isRx() {
        return mThroughput.has("bw_rx");
    }

    /**
     * Is this an iperf transmit message?
     * @return is a tx message
     */
    public boolean isTx() {
        return mThroughput.has("bw_tx");
    }

    /**
     * Returns a bool if this is the 'final'
     * iperf message received from the api.
     * @return final
     */
    public boolean isFinal() {
        try {
            return mThroughput.getBoolean("finalAvg");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the bandwidth for this given timeslot
     * or -1 on error.
     * @return bandwidth or -1 on error
     */
    public double getBw() {
        try {
            if (mThroughput.has("bw_rx")) {
                return mThroughput.getDouble("bw_rx");

            } else if (mThroughput.has("bw_tx")) {
                return mThroughput.getDouble("bw_tx");
            }
            return -1.0;
        } catch (JSONException e) {
            e.printStackTrace();
            return -1.0;
        }
    }

    /**
     * Returns the 'interval' for this message.
     * Typically formatted like `1.0-2.0`
     * @return time slice seconds
     */
    public String getSeconds() {
        try {
            if (mThroughput.has("interval")) {
                return mThroughput.getString("interval");
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
