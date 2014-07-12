package android.proximitypass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    private final static String TAG = "SMSReceiver";

    public SMSReceiver() {
        super();
        Log.e(TAG, "Created receiver");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "SMS received!");

        Bundle myBundle = intent.getExtras();
        SmsMessage [] messages = null;
        String strMessage = "";

        if (myBundle != null) {
            Object [] pdus = (Object[]) myBundle.get("pdus");
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                strMessage += " : ";
                strMessage += messages[i].getMessageBody();
                strMessage += "\n";
            }

            Log.e(TAG, "Message: " + strMessage);

            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
