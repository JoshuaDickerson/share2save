package com.example.share2save;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class AddImage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String Uri = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		setContentView(R.layout.activity_add_image);
		TextView textView = (TextView) findViewById(R.id.testText);
		textView.setText(Uri);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_image, menu);
		return true;
	}

}
