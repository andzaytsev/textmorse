package edu.uiuc.booksearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity {
    public final static String EXTRA_MESSAGE = "edu.uiuc.booksearch.isbn";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View view) {
		Intent intent = new Intent(this, SearchActivity.class);
		EditText editText = (EditText) findViewById(R.id.isbn);
		intent.putExtra(EXTRA_MESSAGE, editText.getText().toString());
		startActivity(intent);
	}

}
