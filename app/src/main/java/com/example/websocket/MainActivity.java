package com.example.websocket;

//import com.neovisionaries.ws.client.WebSocketException;
import com.persistentsystems.socketclient.auth.WrAuth;
import com.persistentsystems.socketclient.containers.WrGetVariableList;
import com.persistentsystems.socketclient.containers.WrSetVariableList;
import com.persistentsystems.socketclient.except.WebSocketTimeout;
import com.persistentsystems.socketclient.net.WrIpAddress;
import com.persistentsystems.socketclient.results.WrJsonNetworkResult;
import com.persistentsystems.socketclient.results.WrJsonNetworkResults;
import com.persistentsystems.socketclient.results.WrJsonResult;
import com.persistentsystems.socketclient.sockets.WrBlockingSocket;
//import netscape.javascript.JSObject;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    String tag = "MY_APP";
    WrBlockingSocket c1;

    String debugMessage = "AbhiAndroid\n";

    TextView textView;

    private final Handler myTextHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message stringMessage) {
            textView.append((String) stringMessage.obj);
            return true;
        }
    });

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.simpleTextView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText(debugMessage); //set text for text view

        new Thread() {
            public void run() {

                try {
                    Log.v(tag, "call executeCommand()");
                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\ncall executeCommand()";
                        stringMessage.sendToTarget();
                    }

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\ncall executeCommand() = " + executeCommand());
                        stringMessage.sendToTarget();
                    }
                    executeCommand();

                    c1 = new WrBlockingSocket();
                    Log.v(tag, "call c1.isOpen() = " + c1.isOpen());
                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\ncall c1.isOpen() = " + c1.isOpen());
                        stringMessage.sendToTarget();
                    }

                    c1.setAuth(new WrAuth().setPassword("password").setUserName("factory"));
                    c1.setIpAddress(new WrIpAddress().set("10.3.1.254").setPort(443));
                    Log.v(tag, "call c1.connectBlocking()");
                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\ncall c1.connectBlocking()";
                        stringMessage.sendToTarget();
                    }

                    c1.connectBlocking();
                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\n....returned from call to c1.connectBlocking()";
                        stringMessage.sendToTarget();
                    }

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\ncall c1.isOpen() = " + c1.isOpen());
                        stringMessage.sendToTarget();
                    }

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\ncall c1.getAuth().getPassword() = " + c1.getAuth().getPassword());
                        stringMessage.sendToTarget();
                    }

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\ncall c1.getAuth().getUserName() = " + c1.getAuth().getUserName());
                        stringMessage.sendToTarget();
                    }

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\ncall c1.getIpAddress() = " + c1.getIpAddress());
                        stringMessage.sendToTarget();
                    }

                    WrJsonNetworkResults wrJsonNetworkResults = c1.networkGet("protocol_version");
                    Log.v(tag, "wrJsonNetworkResults = " + wrJsonNetworkResults);

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\nwrJsonNetworkResults = " + wrJsonNetworkResults);
                        stringMessage.sendToTarget();
                    }

                    JSONObject jsObject = wrJsonNetworkResults.getJson();
                    Iterator<String> itr;
                    itr = jsObject.keys();
                    for (Iterator<String> it = itr; it.hasNext(); ) {
                        Message stringMessage = Message.obtain(myTextHandler);
                        String s = it.next();
                        stringMessage.obj = ("\n    iterator string = " + s);
                        stringMessage.sendToTarget();
                    }


                    Object o = jsObject.get("protocol_version");
                    Log.v(tag, "\n    protocol_version getCanonicalName  = " + o.getClass().getCanonicalName());
                    Log.v(tag, "\n    protocol_version value             = " + o);

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\n    protocol_version getCanonicalName  = " + o.getClass().getCanonicalName() + "\n    protocol_version value             = " + o);
                        stringMessage.sendToTarget();
                    }

                    Object o2 = jsObject.get("unit_id");
                    Log.v(tag, "\n    unit_id getCanonicalName  = " + o2.getClass().getCanonicalName());
                    Log.v(tag, "\n    unit_id value             = " + o2);

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\n    unit_id getCanonicalName  = " + o2.getClass().getCanonicalName() + "\n    unit_id value             = " + o2);
                        stringMessage.sendToTarget();
                    }

                    JSONObject jsObject2 = jsObject.getJSONObject("unit_id");
                    Log.v(tag, "\n    jsObject2 getCanonicalName = " + jsObject2.getClass().getCanonicalName());
                    Log.v(tag, "\n    jsObject2 value            = " + jsObject2);

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\n    jsObject2 getCanonicalName = " + jsObject2.getClass().getCanonicalName() + "\n    jsObject2 value            = " + jsObject2);
                        stringMessage.sendToTarget();
                    }

                    Iterator<String> itr2 = jsObject2.keys();
                    for (Iterator<String> it = itr2; it.hasNext(); ) {
                        String s = it.next();
                        Log.v(tag, "\n    iterator string = " + s);

                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\n    iterator string = " + s);
                        stringMessage.sendToTarget();
                    }

                    Object o3 = jsObject2.getString("management_ip");
                    Log.v(tag, "\n\n    management_ip getCanonicalName = " + o3.getClass().getCanonicalName());
                    Log.v(tag, "\n    management_ip value            = " + o3);
                    Log.v(tag, "\n\n\n The IP address of the radio is = " + jsObject.getJSONObject("unit_id").getString("management_ip"));

                    {
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = ("\n\n    management_ip getCanonicalName = " + o3.getClass().getCanonicalName() +
                                "\n    management_ip value            = " +
                                o3 +
                                "\n\n\n The IP address of the radio is = " + jsObject.getJSONObject("unit_id").getString("management_ip"));
                        stringMessage.sendToTarget();
                    }


                } catch (Exception e) {
                    e.printStackTrace();

//                    stringMessage.obj = ("\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
//                    stringMessage.sendToTarget();
                }
            }
        }.start();

    }

    private boolean executeCommand(){
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 10.3.1.254");
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue "+mExitValue);
            if(mExitValue==0){
                return true;
            }else{
                return false;
            }
        }
        catch (InterruptedException ignore)
        {
            ignore.printStackTrace();
            System.out.println(" Exception:"+ignore);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(" Exception:"+e);
        }
        return false;
    }

}