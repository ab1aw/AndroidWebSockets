package com.persistentsystems.socketclient.results;

import android.annotation.SuppressLint;

import com.persistentsystems.socketclient.containers.WrVariablePair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is a representation of all (available) responses
 * in a received network message.
 * <br/>
 * <b>
 * Note, this class is contained within a {@link WrJsonNetworkResults}.
 * </b>
 * </br>
 * Example:
 * <pre>
 * {
 *     '172.26.1.150': {
 *         'waverelay_name': 'My test node',
 *         'waverelay_gateway': '172.26.63.254',
 *         ...
 *     },
 *     '172.26.1.160': {
 *         ...
 *     },
 *     ...
 * }
 * </pre>
 */
public class WrJsonNetworkResult extends WrJsonResult {

    private final Map<String, List<WrVariablePair>> mNetworkMap;

    /**
     * Public constructor.
     * Parses received message into a Map&lt;String, List&lt;WrVariablePair&gt;&gt;.
     * Where the map key is the remote node's IP address and the list is pairs of variables/values.
     * @param xString string from websocket response.
     */
    @SuppressLint("NewApi")
    public WrJsonNetworkResult(String xString) {
        super(xString);
        mNetworkMap = new HashMap<>();
        try {
        // "ip_list":{"172.26.1.152":{"variables":{"waverelay_name":{"value":"Heller Test Name"}, {"waverelay_test":{"value":"Heller Test Name"}}}
        if (mRootObject.has("ip_list")) {
            JSONObject ip_list = mRootObject.getJSONObject("ip_list");
            for (Iterator<String> it = ip_list.keys(); it.hasNext(); ) {
                String e = it.next();
                JSONObject ip = null;
                    ip = ip_list.getJSONObject(e);
                JSONObject vars = ip.getJSONObject("variables");
                for (Iterator<String> iter = vars.keys(); iter.hasNext(); ) {
                    String varName = iter.next();
                    JSONObject varObj = vars.getJSONObject(varName);
                    mNetworkMap.computeIfAbsent(e, k -> new LinkedList<>());
                    mNetworkMap.get(e).add(new WrVariablePair(varName, varObj.getString("value")));
                }
            }
        }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the receiving node (the directly connected node) IP address.
     * @return ip address or null
     */
    public String getIp() {
        if (mRootObject.has("ip")) {
            try {
                return mRootObject.getString("ip");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Returns a Map of ips and {@link WrVariablePair}.
     * See constuctor for details.
     * @return map
     */
    public Map<String, List<WrVariablePair>> getNetworkValues() {
        return mNetworkMap;
    }
}
