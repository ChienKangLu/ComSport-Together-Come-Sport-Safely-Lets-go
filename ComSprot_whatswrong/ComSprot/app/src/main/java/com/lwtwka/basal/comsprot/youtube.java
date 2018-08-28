package com.lwtwka.basal.comsprot;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class youtube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,YouTubeThumbnailView.OnInitializedListener {

    public static final String API_KEY = "AIzaSyA5Prb6dLjI0YcfMQhZuGT5-_Kt68ZgDiQ";

    //http://youtu.be/<VIDEO_ID>
    public static final String VIDEO_ID = "nrNTKy0L8jk";
    private YouTubePlayer youTubePlayer;
    private YouTubeThumbnailLoader youTubeThumbnailLoader;
   // JsonFactory JSON_FACTORY=new GsonFactory();//°Õ°Õ°Õ°Õ¸ÑªR¾¹
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName("youtube-cmdline-search-sample").build();
        String topicsId = "";
        //String queryTerm ="";
        YouTube.Search.List search=null;
        try {
            search = youtube.search().list("id,snippet");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String apiKey = "";
        search.setKey(apiKey);
        //search.setQ(queryTerm);
        if (topicsId.length() > 0) {
            search.setTopicId(topicsId);
        }
        search.setType("video");
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        long NUMBER_OF_TOPICS_RETURNED = 5;
        search.setMaxResults(NUMBER_OF_TOPICS_RETURNED);
        SearchListResponse searchResponse=null;
        try {
            searchResponse = search.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            Iterator<SearchResult> iteratorSearchResults=searchResultList.iterator();
            while (iteratorSearchResults.hasNext()) {

                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();

                // Confirm that the result represents a video. Otherwise, the
                // item will not contain a video ID.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                    System.out.println(" Video Id" + rId.getVideoId());
                    System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                    System.out.println(" Thumbnail: " + thumbnail.getUrl());
                    System.out.println("\n-------------------------------------------------------------\n");
                }
            }
        } else {
            System.out.println("There were no results for your query.");
        }



        /** attaching layout xml **/
        setContentView(R.layout.activity_youtube);
        /** Initializing YouTube player view **/
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
        YouTubeThumbnailView youTubeThumbnailView= (YouTubeThumbnailView)findViewById(R.id.youtubethumbnailview);
        youTubeThumbnailView.initialize(API_KEY, this);
        youTubeThumbnailView.setClickable(true);
        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (youTubePlayer != null) {
                    // youTubePlayer.play();
                    //  youTubePlayer.setFullscreen(true);

                    //startActivity(YouTubeIntents.createPlayVideoIntentWithOptions(getApplicationContext(), VIDEO_ID, false, false));
                    ff();

                }
            }
        });


        //TextView aa=(TextView)findViewById(R.id.textView6);
           // aa.setText(""+youTubePlayer.getDurationMillis());
    }
    public void ff(){
        startActivity(YouTubeStandalonePlayer.createVideoIntent(this, API_KEY, VIDEO_ID, 0, true, true));
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        youTubePlayer = player;

        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {

            Log.v("«¢«¢«¢«¢«¢«¢«¢:",""+youTubePlayer.getDurationMillis());
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }


        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }

        @Override
        public void onVideoStarted() {
        }
    };


    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader thumbnailLoader) {
        youTubeThumbnailLoader = thumbnailLoader;
     //   thumbnailLoader.setOnThumbnailLoadedListener(new ThumbnailLoadedListener());

        youTubeThumbnailLoader.setVideo(VIDEO_ID);
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
