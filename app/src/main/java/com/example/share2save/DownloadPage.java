package com.example.share2save;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.share2save.model.Constants;

public class DownloadPage extends Activity {
	private TextView textView;
	private ListView listView1;
    Logger log = LoggerFactory.getLogger(DownloadPage.class);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_page);
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    ConnectivityManager connMgr = (ConnectivityManager)
		getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			LinkItem[] items = null;
			try {
                if(message != null) {
                    items = new BackgroundTask().execute(message).get();
                }else{
                    items = new BackgroundTask().execute("").get();
                }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {

			}
			LinkAdapter adapter = new LinkAdapter(this, R.layout.listview_item_row, items);
			listView1 = (ListView)findViewById(R.id.listView1);
			View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
			listView1.setAdapter(adapter);
			listView1.setClickable(true);
			listView1.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					LinkItem li = (LinkItem) listView1.getItemAtPosition(position);
					Uri uri = Uri.parse(li.title);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			});
			
		}else{
			message = "not connected";
			textView = new TextView(this);
			textView.setTextSize(12);
			textView.setText(message);
			setContentView(textView);
		}
	}

	private OnItemClickListener function() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_download_page, menu);
		return true;
	}
	
	
	public class BackgroundTask extends AsyncTask<String, Integer, LinkItem[]>{
		@Override
		protected LinkItem[] doInBackground(String... urls) {
			String returned =  postData(urls[0]);
//			JSONObjectJsonObj = new JSONObject(returned);
			String[] titleArray = returned.split("ZZZZZ");
			LinkItem items[] = new LinkItem[titleArray.length];
			for(int ii=0; ii<titleArray.length; ii++){
				items[ii] = new LinkItem(titleArray[ii]);
			}
			
			return items;
		}
		
		public String postData(String search){
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Constants.HOST);
			String contentAsString = "";
			try{
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("searchValue", search));
				nameValuePairs.add(new BasicNameValuePair("userEmail", "joshuajdickerson@gmail.com"));
                UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(nameValuePairs);
                log.debug(urlEntity.toString());
				httppost.setEntity(urlEntity);
				
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
                // this will restrict how much json we can get at a time
				contentAsString = readIt(is, 10000);
			} catch (Exception e) {
				log.error("Exception thrown trying to post a search term", e);
			}
			 
		     return contentAsString;
		}
		
		// Reads an InputStream and converts it to a String.
		public String readIt(InputStream stream, int len) throws IOException {
		    Reader reader = null;
		    reader = new InputStreamReader(stream, "UTF-8");
		    char[] buffer = new char[len];
		    reader.read(buffer);
		    return new String(buffer);
		}
		
	}

}
