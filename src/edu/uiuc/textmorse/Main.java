package edu.uiuc.textmorse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Main extends Activity {
	public static final String PREFS_NAME = "MorseTextPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView t1 = (TextView) findViewById(R.id.textView1);
		TextView t2 = (TextView) findViewById(R.id.textView2);
		TextView t3 = (TextView) findViewById(R.id.textView3);
		Button t4 = (Button) findViewById(R.id.button1);
		CheckBox t5 = (CheckBox) findViewById(R.id.checkBox1);

		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto_Light.ttf");
		// ((TextView) v).setTypeface(custom_font);
		t1.setTypeface(custom_font);
		t2.setTypeface(custom_font);
		t3.setTypeface(custom_font);
		t4.setTypeface(custom_font);
		t5.setTypeface(custom_font);
		Intent intent = new Intent(this, TextMorseService.class);
		startService(intent);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean display = settings.getBoolean("display", true);

		if (!display)
			finish();
	}

	public void onClick(View view) {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("display",
				!((CheckBox) findViewById(R.id.checkBox1)).isChecked());
		editor.commit();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
