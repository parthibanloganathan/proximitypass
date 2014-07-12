package android.proximitypass;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class SMSService extends Service {
    private final static String TAG = "SMSService";
    private SMSReceiver receiver;
    private IntentFilter intentFilter;

    @Override
    public void onCreate() {
        Log.e(TAG, "Started service");

        super.onCreate();

        receiver = new SMSReceiver();
        intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(2147483647);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "On started service");

        receiver = new SMSReceiver();
        intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(2147483647);
        registerReceiver(receiver, intentFilter);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
