package pl.emb.youtubeplayer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends YouTubeBaseActivity
        implements AdapterView.OnItemSelectedListener{

    YouTubePlayerView youTubePlayerView;
    private YouTubePlayer mYouTubePlayer;
    private String movieURL;
    private boolean isPaused = false;
    LinkedHashMap<String, String> moviesMap = new LinkedHashMap<String, String>();
    private YouTubePlayer.OnInitializedListener onInitializedListener;

    public boolean getPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void setMovieURL(String movieURL) {
        this.movieURL = movieURL;
    }

    public String getMovieURL() {
        return movieURL;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerSetup();
        youTubePlayerView = findViewById(R.id.youtube_view);
        createTitlesArray();
        spinnerInit(createTitlesArray());
    }

    private String[] createTitlesArray() {
        moviesMap.put("Bohemian Rhapsody", "fJ9rUzIMcZQ");
        moviesMap.put("I Want to Break Free", "f4Mc-NYPHaQ");
        moviesMap.put("We Will Rock You", "-tJYN-eG1zk-8h0");
        moviesMap.put("Radio Ga Ga", "azdwsXLmrHE");
        Set<String> moviesKeysSet = moviesMap.keySet();
        List<String> moviesTitlesList = new ArrayList<String>(moviesKeysSet);
        String[] moviesTitlesArray = new String[moviesTitlesList.size()];
        moviesTitlesList.toArray(moviesTitlesArray);
        return moviesTitlesArray;
    }

    private void spinnerInit(String[] moviesTitlesArray) {
        Spinner movieSpinner = (Spinner) findViewById(R.id.movieSpinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, moviesTitlesArray);
        movieSpinner.setAdapter(adapter);
        movieSpinner.setOnItemSelectedListener(this);
    }

    public void onClickPlayVideo(View view) {
        try {
            if (getPaused()) {
                mYouTubePlayer.play();
            }
            else {
                mYouTubePlayer.loadVideo(getMovieURL());
            }
        } catch (Throwable T) {
            Toast.makeText(getApplicationContext(), "Error during Video load",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setMovieURL(moviesMap.get(parent.getItemAtPosition(position)));
        System.out.println("OnItem");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void youTubePlayerSetup() {
        youTubePlayerView = findViewById(R.id.youtube_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                try {
                    mYouTubePlayer = youTubePlayer;
                    mYouTubePlayer.cueVideo(getMovieURL());
                    System.out.println("Git");
                } catch (Throwable T) {
                    Toast.makeText(getApplicationContext(),"Error during Video initialization",
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult
                                                        youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(),
                        "An error occured during initialization: " +
                                youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
                System.out.println("Failure");

            }
        };
        youTubePlayerView.initialize(Config.getYoutubeApiKey(), onInitializedListener);

    }

    public void onClickPauseBtn(View view) {
        mYouTubePlayer.pause();
        setPaused(true);
    }

    public void onClickStopBtn(View view) {
        mYouTubePlayer.release();
        mYouTubePlayer = null;
        youTubePlayerSetup();
        setPaused(false);

    }

}