package com.example.share2save;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.share2save.model.Tag;
import com.example.share2save.worker.ApiRequestWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;


public class MainActivity extends Activity {
    private Logger log = LoggerFactory.getLogger(MainActivity.class);
    private ApiRequestWorker requestWorker = new ApiRequestWorker();
    public final static String EXTRA_MESSAGE = "com.example.myfistapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
	    String action = intent.getAction();
	    String type = intent.getType();
	    
	    if (Intent.ACTION_SEND.equals(action) && type != null){
	    	 if ("text/plain".equals(type)) {
	             handleSendText(intent); // Handle text being sent
	         } else if (type.startsWith("image/")) {
	             handleSendImage(intent); // Handle single image being sent
	         }
	     }else {

	     }
	    
	}
//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void performSearch(View view){
		Intent intent = new Intent(this, DownloadPage.class);
		// get our EditText obj by ID 
		EditText editText = (EditText) findViewById(R.id.edit_message);
		// convert message to string
		String message = editText.getText().toString();
		// add extended data to the intent
		intent.putExtra(EXTRA_MESSAGE,  message);
		// start the intent
		startActivity(intent);
	}

    public void showAll(View view) throws Exception {
        Intent intent = new Intent(this, ApiRequestWorker.class);
//        String nullVal = null;
        intent.putExtra(EXTRA_MESSAGE,  ApiRequestWorker.GET_BOOKMARKS);
//        // start the intent
        startActivity(intent);

//        requestWorker.getBookmarks(new ArrayList<String>());
//        )
    }
	
	public void handleSendText(Intent intent){
		String message = "nothing";
		// get the string (url) from the intent
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
	    if (sharedText != null) {
	    	Intent newIntent = new Intent(this, AddBookmark.class);
	    	newIntent.putExtra(EXTRA_MESSAGE, sharedText);
	    	startActivity(newIntent);
	    }
		
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		setContentView(textView);
		this.finish();
	}
	
	public void handleSendImage(Intent intent){
		Uri imageUri = null;
		
		Uri streamUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
		String uri =streamUri.toString();
		
		Intent newIntent = new Intent(this, AddImage.class);
    	newIntent.putExtra(EXTRA_MESSAGE, uri);
//    	message = sharedText;
    	startActivity(newIntent);
	}

}
