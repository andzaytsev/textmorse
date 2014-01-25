package edu.uiuc.booksearch;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity {
	public final static String EXTRA_MESSAGE = "edu.uiuc.booksearch.isbn";
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initMorse();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		String str = ((EditText) findViewById(R.id.isbn)).getText().toString();
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		int MAXLENGTH = 1000;
		long[] pattern = new long[MAXLENGTH];
		for (int i = 0; i < pattern.length; ++i)
			pattern[i] = 0;
		int i = 1;
		for (char letter : str.toCharArray()) {
			letter = Character.toLowerCase(letter);
			String dnd = mcode.get((Character)letter);
			for (char d : dnd.toCharArray()) {
				if (d == '.')
					pattern[i++] = 200;
				else if (d == '-')
					pattern[i++] = 600;
				pattern[i++] = 200;
			}
			pattern[i - 1] = 600;
		}
		v.vibrate(pattern, -1);
	}

}
