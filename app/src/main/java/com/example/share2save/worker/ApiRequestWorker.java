package com.example.share2save.worker;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.share2save.LinkAdapter;
import com.example.share2save.LinkItem;
import com.example.share2save.MainActivity;
import com.example.share2save.R;
import com.example.share2save.model.Bookmark;
import com.example.share2save.model.BookmarkResponseObject;
import com.example.share2save.model.Constants;
import com.example.share2save.persistence.BookmarkDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by josh on 3/15/14.
 */
public class ApiRequestWorker extends Activity {
    public static final int GET_BOOKMARKS = 0;
    private Logger log = LoggerFactory.getLogger(ApiRequestWorker.class);
    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private TextView textView;
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Bookmark> bookmarks = newArrayList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_page);
        Intent intent = getIntent();
        Integer requestToUse = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            switch (requestToUse) {
                case 0:
                    try {
                        URI uri = new URI(Constants.HOST + "/bookmark?userId=1&token=2");
                        bookmarks = new RequestTask().execute(uri).get();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                    break;
            }

            LinkItem[] items = new LinkItem[bookmarks.size()];
            for(int ii=0; ii< bookmarks.size(); ii++){
                LinkItem item = new LinkItem();
                item.title = bookmarks.get(ii).getTitle();
                item.url = bookmarks.get(ii).getUrl();
                items[ii] = item;
            }

            LinkAdapter adapter = new LinkAdapter(this, R.layout.listview_item_row, items);
            listView1 = (ListView)findViewById(R.id.listView1);
            View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
            listView1.setAdapter(adapter);
            listView1.setClickable(true);
            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    LinkItem li = (LinkItem) listView1.getItemAtPosition(position);
                    Uri uri = Uri.parse(li.url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            BookmarkDAO dao = new BookmarkDAO(this);
            dao.insertBookmarks(bookmarks);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_download_page, menu);
        return true;
    }


    public class RequestTask extends AsyncTask<URI, Integer, List<Bookmark>> {

        public List<Bookmark> getBookmarks(List<String> tags) throws Exception {

            String url = Constants.HOST + "/bookmark?userId=1&token=2";
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            String contentAsString = readIt(is, 10000).trim();
            log.info(contentAsString);
            return BookmarkMapper.mapResponseToBookmarkList(gson.fromJson(contentAsString, BookmarkResponseObject.class));

        }

        // Reads an InputStream and converts it to a String.
        public String readIt(InputStream stream, int len) throws IOException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }

        @Override
        protected List<Bookmark> doInBackground(URI... params) {
            try {
                return getBookmarks(new ArrayList<String>());
            } catch (Exception e) {
                log.error("async task threw exception", e);
            }

            return null;
        }
    }
}
