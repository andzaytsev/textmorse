package edu.uiuc.textmorse;

import java.util.HashMap;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.telephony.SmsMessage;

public class TextMorseService extends Service {
	private BroadcastReceiver smsListener = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(
					"android.provider.Telephony.SMS_RECEIVED")) {
				Bundle bundle = intent.getExtras();
				SmsMessage[] msgs = null;
				if (bundle != null) {
					try {
						Object[] pdus = (Object[]) bundle.get("pdus");
						msgs = new SmsMessage[pdus.length];
						for (int i = 0; i < msgs.length; i++) {
							msgs[i] = SmsMessage
									.createFromPdu((byte[]) pdus[i]);
							String msgBody = msgs[i].getMessageBody();
							TextMorseService.this.vibrateMorse(msgBody);
						}
					} catch (Exception e) {
					}
				}
			}
		}
	};

	public HashMap<Character, String> mcode = new HashMap<Character, String>();

	public void initMorse() {
		mcode.put('a', ".-");
		mcode.put('b', "-...");
		mcode.put('c', "-.-.");
		mcode.put('d', "-..");
		mcode.put('e', ".");
		mcode.put('f', "..-.");
		mcode.put('g', "--.");
		mcode.put('h', "....");
		mcode.put('i', "..");
		mcode.put('j', ".---");
		mcode.put('k', "-.-");
		mcode.put('l', ".-..");
		mcode.put('m', "--");
		mcode.put('n', "-.");
		mcode.put('o', "---");
		mcode.put('p', ".--.");
		mcode.put('q', "--.-");
		mcode.put('r', ".-.");
		mcode.put('s', "...");
		mcode.put('t', "-");
		mcode.put('u', "..-");
		mcode.put('v', "...-");
		mcode.put('w', ".--");
		mcode.put('x', "-..-");
		mcode.put('y', "-.--");
		mcode.put('z', "--..");
		mcode.put('0', "-----");
		mcode.put('1', ".----");
		mcode.put('2', "..---");
		mcode.put('3', "...--");
		mcode.put('4', "....-");
		mcode.put('5', ".....");
		mcode.put('6', ".----");
		mcode.put('7', "..---");
		mcode.put('8', "...--");
		mcode.put('9', "....-");
	}
	
	public void vibrateMorse(String str) {
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		int MAXLENGTH = 1000;
		long[] pattern = new long[MAXLENGTH];
		for (int i = 0; i < pattern.length; ++i)
			pattern[i] = 0;
		int i = 1;
		// Whether the previous char is a space, to avoid multiple spaces
		boolean prevspace = true;
		final int unit = 150;
		for (char letter : str.toCharArray()) {
			letter = Character.toLowerCase(letter);
			String dnd = mcode.get((Character) letter);
			if (dnd == null) {
				if (!prevspace) {
					pattern[i++] = 0;
					pattern[i++] = 7 * unit;
				}
				prevspace = true;
			} else {
				for (char d : dnd.toCharArray()) {
					if (d == '.')
						pattern[i++] = unit;
					else if (d == '-')
						pattern[i++] = 3 * unit;
					pattern[i++] = unit;
					prevspace = false;
				}
				pattern[i - 1] = 3 * unit;
			}
		}
		v.vibrate(pattern, -1);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    initMorse();IntentFilter iff = new IntentFilter();
		iff.addAction("android.provider.Telephony.SMS_RECEIVED");
		this.registerReceiver(this.smsListener, iff);
	    return Service.START_NOT_STICKY;
	  }
	
	@Override
	public void onDestroy() {
		this.unregisterReceiver(smsListener);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
