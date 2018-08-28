package com.lwtwka.basal.comsprot;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class youtube_data extends ActionBarActivity {
    /*
    public static final String API_KEY = "AIzaSyA5Prb6dLjI0YcfMQhZuGT5-_Kt68ZgDiQ";
    YouTube youtube;
    */
    @Override
    protected void onResume() {
        super.onResume();
        /*
        JsonFactory JSON_FACTORY=new GsonFactory();
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName("youtube-cmdline-search-sample").build();
        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");
            //search.setTopicId("Cy0thWiV-eA");
            search.setQ("android");
            search.setKey(API_KEY);
            search.setType("video");
            search.setFields("items(id/videoId)");

            long NUMBER_OF_VIDEOS_RETURNED = 1;
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            List<String> videoIds = new ArrayList<String>();
            if (searchResultList != null) {

                // Merge video IDs
                for (SearchResult searchResult : searchResultList) {
                    videoIds.add(searchResult.getId().getVideoId());
                }
                Joiner stringJoiner = Joiner.on(',');
                String videoId = stringJoiner.join(videoIds);

                // Call the YouTube Data API's youtube.videos.list method to
                // retrieve the resources that represent the specified videos.
                YouTube.Videos.List listVideosRequest = youtube.videos().list("snippet, recordingDetails").setId(videoId);
                VideoListResponse listResponse = listVideosRequest.execute();

                List<Video> videoList = listResponse.getItems();

                if (videoList != null) {
                    prettyPrint(videoList.iterator(), "android");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


    }
    /*
    private static void prettyPrint(Iterator<Video> iteratorVideoResults, String query) {

        if (!iteratorVideoResults.hasNext()) {
            Log.v("no query"," There aren't any results for your query.");
        }

        while (iteratorVideoResults.hasNext()) {

            Video singleVideo = iteratorVideoResults.next();

            Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
            GeoPoint location = singleVideo.getRecordingDetails().getLocation();

            Log.v(" Video Id" ,singleVideo.getId());
            Log.v(" Title: " , singleVideo.getSnippet().getTitle());
            Log.v(" Location: " , location.getLatitude() + ", " + location.getLongitude());
            Log.v(" Thumbnail: ", thumbnail.getUrl());

        }
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_youtube_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
