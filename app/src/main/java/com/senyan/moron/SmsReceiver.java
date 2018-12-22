package com.senyan.moron;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[]  pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
//            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String format = intent.getExtras().getString("format");
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i], format);

            String sender = smsMessage.getDisplayOriginatingAddress();
            //Check the sender to filter messages which we require to read

            //if (sender.equals("GADGETSAINT"))
            //{
            JSONObject message = new JSONObject();
            try {
                message.put("body", smsMessage.getMessageBody());
                message.put("timestamp", smsMessage.getTimestampMillis());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //String messageBody = smsMessage.getMessageBody();

                //Pass the message text to interface
            try {
                mListener.messageReceived(message.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //}
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}