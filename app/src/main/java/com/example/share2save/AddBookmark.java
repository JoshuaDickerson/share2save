package com.example.share2save;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddBookmark extends Activity {
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bookmark);
		Intent intent = getIntent();
		// get our URL from the intent
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		EditText bookmark = (EditText) findViewById(R.id.edit_bookmark);
		EditText title = (EditText) findViewById(R.id.edit_title);
		EditText tags = (EditText) findViewById(R.id.edit_tags);
		// set our input as that text
		bookmark.setText(message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_bookmark, menu);
		return true;
	}
	
	public void performAddBookmark(View view){
		
		String[] stringArray = new String[3];
		EditText bookmark = (EditText) findViewById(R.id.edit_bookmark);
		EditText title = (EditText) findViewById(R.id.edit_title);
		EditText tags = (EditText) findViewById(R.id.edit_tags);
		stringArray[0] = bookmark.getText().toString();
		stringArray[1] = title.getText().toString();
		stringArray[2] = tags.getText().toString();
		
		ConnectivityManager connMgr = (ConnectivityManager) 
		getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			new BackgroundTask().execute(stringArray);
			Log.w("its working", "we have network");
			
		}else{
			String message = "not connected";
		}
		
		this.finish();
		
		
	}
	
	public class BackgroundTask extends AsyncTask<String[], Integer, String>{
		
		protected String doInBackground(String[]... stringArray) {
			Log.w("inside", "doInBackground");
			return postData(stringArray);
		}
		
		protected void onPostExecute(String result){
			//textView.setText(result.toString());
			
		}
		
		
		public String postData(String[]... stringArray){
			Log.w("inside","postData");
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://www.slimdowndesign.com/chrome_extensions/sharetosave/index.php");
			
			try{
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
				nameValuePairs.add(new BasicNameValuePair("userEmail", "joshuajdickerson@gmail.com"));
				nameValuePairs.add(new BasicNameValuePair("url", stringArray[0][0]));
				nameValuePairs.add(new BasicNameValuePair("dataType", "bookmark"));
				nameValuePairs.add(new BasicNameValuePair("tag", stringArray[0][2]));
				nameValuePairs.add(new BasicNameValuePair("tabTitle", stringArray[0][1]));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				Log.w("response:", response.toString());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.w("didnt", "work");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.w("didnt", "work");
				e.printStackTrace();
			} finally {
				
			}
			return "worked";
		}
		
		// Reads an InputStream and converts it to a String.
		public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		    Reader reader = null;
		    reader = new InputStreamReader(stream, "UTF-8");        
		    char[] buffer = new char[len];
		    reader.read(buffer);
		    return new String(buffer);
		}

//		@Override
//		protected String doInBackground(String[]... params) {
//			// TODO Auto-generated method stub
//			return null;
//		}

		
	}

}
