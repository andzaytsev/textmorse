package edu.uiuc.textmorse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class Main extends Activity {
	public static final String PREFS_NAME = "MorseTextPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
