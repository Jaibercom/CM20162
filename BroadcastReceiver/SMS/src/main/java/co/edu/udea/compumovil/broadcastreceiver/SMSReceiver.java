package co.edu.udea.compumovil.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = "SMSBroadcastReceiver";

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d(TAG, "onReceive");


        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        String address = "";

        if (bundle != null) {

            //--retrieve the SMS message received--
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                address = msgs[i].getOriginatingAddress();
                str += "SMS from " + address;
                str += ": ";
                str += msgs[i].getMessageBody().toString();
                str += "\n";

                Toast.makeText(context, str, Toast.LENGTH_LONG).show();
            }

        }
    }
}
