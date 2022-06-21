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

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.DiscoveryListener;
import android.net.nsd.NsdServiceInfo;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    public String tag = "MY_APP";
    WrBlockingSocket c1;

    String debugMessage = "AbhiAndroid\n";

    TextView textView;
    private static Context context;

    public final Handler myTextHandler = new Handler(new Handler.Callback() {
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

        MainActivity.context = getApplicationContext();
        
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.simpleTextView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText(debugMessage); //set text for text view

        new Thread() {
            public void run() {

                Log.v(tag, "Step one");
                IntentFilter filter = new IntentFilter("android.net.conn.TETHER_STATE_CHANGED");
                MainActivity.registerReceiver(this, filter);


                try {

                    // This does not work.  It gets stuck:
                    //getTetheringInfo(tag);

                    {
                        Log.v(tag, "Step two");
                        Runtime runtime = Runtime.getRuntime();
                        Process proc = runtime.exec("ip neigh show");
                        proc.waitFor();
                        BufferedReader reader;
                        reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            Log.v(tag, "Step two B");

                            String[] clientInfo = line.split(" +");
                            if(!clientInfo[3].equalsIgnoreCase("type")) {
                                Log.v(tag, "Step two C");
                                String mac = clientInfo[3];
                                String ip = clientInfo[0];
                                String msg = "ip: " + ip + " Mac: " + mac;
                                Log.d(tag, "IP : " + ip);
                                Log.d(tag, "Mac : " + mac);

                                Message stringMessage = Message.obtain(myTextHandler);
                                stringMessage.obj = "\nreader got this = " + msg;
                                stringMessage.sendToTarget();
                            }
                        }

                    }

                    /*
                    // Does not work in newer versions of Android;  10+
                    {
                        Log.v(tag, "Step two");
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\ngetUSBThetheredIP() = " + getUSBThetheredIP();
                        stringMessage.sendToTarget();
                    }
*/


                    {
                        Log.v(tag, "Step three");
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\nUtils.getMACAddress(wlan0) = " + Utils.getMACAddress("wlan0");
                        stringMessage.sendToTarget();
                    }

                    {
                        Log.v(tag, "Step four");
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\nUtils.getMACAddress(eth0) = " + Utils.getMACAddress("eth0");
                        stringMessage.sendToTarget();
                    }

                    {
                        Log.v(tag, "Step five");
                        Message stringMessage = Message.obtain(myTextHandler);
                        stringMessage.obj = "\nUtils.getIPAddress(true) = " + Utils.getIPAddress(true);
                        stringMessage.sendToTarget();
                    }

                    {
                        Message stringMessage = Message.obtain(myTextHandler);

                        Process p = Runtime.getRuntime().exec("/system/bin/ip address");

                        stringMessage.obj = "\ngetOutputStream = " + p.getOutputStream().toString();
                        stringMessage.sendToTarget();
                    }


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


    private static void registerReceiver(Thread thread, IntentFilter filter) {
        Log.v("MY_APP", "call registerReceiver()");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Got getUSBThetheredIP from:
    // https://stackoverflow.com/questions/11506160/how-to-get-the-system-ip-address-after-usb-tethering-of-android-phone

    public static String getUSBThetheredIP() {
        Log.v("MY_APP", "getUSBThetheredIP() #1");

        BufferedReader bufferedReader = null;
        String ips="";

        try {
            Log.v("MY_APP", "getUSBThetheredIP() #2");
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            Log.v("MY_APP", "getUSBThetheredIP() #3");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Log.v("MY_APP", "getUSBThetheredIP() #4...");
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4) {
                    String ip = splitted[0];
                    String mac = splitted[3];
                    if (mac.matches("..:..:..:..:..:..")) {
                        if (mac.matches("00:00:00:00:00:00")) {
                            Log.d("MY_APP", "Wrong:" + mac + " : " + ip);
                        } else {
                            Log.d("MY_APP", "Correct:" + mac + " : " + ip);
                            ips = ip;
                            //break;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
            e.printStackTrace();
        } finally{
            try {
                Log.v("MY_APP", "getUSBThetheredIP() #5");
                bufferedReader.close();
            } catch (IOException e) {
                Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                e.printStackTrace();
            }
        }
        return ips;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

/*
    public void initializeDiscoveryListener() {

        // Instantiate a new DiscoveryListener
        DiscoveryListener discoveryListener = new NsdManager.DiscoveryListener() {

            // Called as soon as service discovery begins.
            @Override
            public void onDiscoveryStarted(String regType) {
                Log.d("MY_APP", "Service discovery started");
            }

            @Override
            public void onServiceFound(NsdServiceInfo service) {
                // A service was found! Do something with it.
                Log.d("MY_APP", "Service discovery success : " + service);
                Log.d("MY_APP", "Service Type is : " + service.getServiceType());
            }

            @Override
            public void onServiceLost(NsdServiceInfo service) {
                // When the network service is no longer available.
                // Internal bookkeeping code goes here.
                Log.e("MY_APP", "service lost: " + service);
            }

            @Override
            public void onDiscoveryStopped(String serviceType) {
                Log.i("MY_APP", "Discovery stopped: " + serviceType);
            }

            @Override
            public void onStartDiscoveryFailed(String serviceType, int errorCode) {
                Log.e("MY_APP", "Discovery failed: Error code:" + errorCode);
            }

            @Override
            public void onStopDiscoveryFailed(String serviceType, int errorCode) {
                Log.e("MY_APP", "Discovery failed: Error code:" + errorCode);
            }
        };
    }
*/


    // From here:
    // https://android.develop-bugs.com/article/25534620/Detect+USB+tethering+on+android
    public void getTetheringInfo(String tag) {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d(tag,"test enable usb tethering 1");
        String[] available = null;
        int code=-1;
        Method[] wmMethods = cm.getClass().getDeclaredMethods();
        Log.d(tag,"test enable usb tethering 2");
        for(Method method: wmMethods){
            Log.d(tag,"test enable usb tethering 3");
            if(method.getName().equals("getTetherableIfaces")){
                Log.d(tag,"test enable usb tethering 4");
                try {
                    available = (String[]) method.invoke(cm);
                    break;
                } catch (IllegalArgumentException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                } catch (IllegalAccessException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                } catch (InvocationTargetException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                }
            }
        }

        Log.d(tag,"test enable usb tethering 5");

        for(Method method: wmMethods){
            Log.d(tag,"test enable usb tethering 6");
            if(method.getName().equals("tether")){
                Log.d(tag,"test enable usb tethering 7");
                try {
                    Log.d(tag,"test enable usb tethering 8");
                    // Gets stuck in the following call:
                    code = (Integer) method.invoke(cm, available[0]);
                    Log.d(tag,"test enable usb tethering 9");
                } catch (IllegalArgumentException e) {
// TODO Auto-generated catch block
                    Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
// TODO Auto-generated catch block
                    Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
// TODO Auto-generated catch block
                    Log.e("MY_APP", "\nEXCEPTION! " + e.getMessage() + "\n : " + e.getCause() + "\n : " + e.toString());
                    e.printStackTrace();
                }
                break;
            }
        }

        Log.d(tag,"test enable usb tethering 10");

        if (code==0)
            Log.d(tag,"Enable usb tethering successfully!");
        else
            Log.d(tag,"Enable usb tethering failed!");

    }
}
