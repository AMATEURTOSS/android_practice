package kr.ft_seoul.automatically_enter_authentication_number;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class smsReceiver extends BroadcastReceiver
{
    final String TAG = "SMSReceiver";

    private static String CHANNEL_ID = "channel_yochoi";
    private static String CHANNEL_NAME = "Channel_yochoi";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "onReceive start");
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction()))
        {
            String ret = "";
            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessage = new SmsMessage[messages.length];

            for (int i = 0; i < messages.length; i++)
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
            String message = smsMessage[0].getMessageBody().toString();
            Log.d(TAG, message);
            if (message.contains("인증번호") || message.contains("인증 번호"))
            {
                ret = manage_str(message);
                Log.d(TAG, "certifying_num: " + ret);
                if (ret != null)
                {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("인증번호", ret);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, "인증번호가 복사되었습니다.", Toast.LENGTH_LONG).show();
                }
                if (ret == null)
                    Toast.makeText(context, "인증번호 복사 실패.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static String manage_str(String message)
    {
        int start = -1;
        int end = -1;
        char tmp;
        String ret = "";
        for (int i = 0; i < message.length(); i++)
        {
            tmp = message.charAt(i);
            if (!(start == -1) && !(tmp >= '0' && tmp <= '9'))
            {
                end = i;
                ret = message.substring(start, end);
                if (ret.length() < 4)
                {
                    start = -1;
                    end = -1;
                    ret = null;
                }
                else
                    return (ret);
            }
            if (start == -1 && tmp >= '0' && tmp <= '9')
                start = i;
        }
        return (null);
    }
}
