package pl.emb.youtubeplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends YouTubeBaseActivity implements AdapterView.OnItemSelectedListener {

    YouTubePlayerView youTubePlayerView;
    //private Config config = new Config();

    private String movieURL;

    public void setMovieURL(String movieURL) {
        this.movieURL = movieURL;
    }

    public String getMovieURL() {
        return movieURL;
    }
    LinkedHashMap<String, String> moviesMap = new LinkedHashMap<String, String>();

    private YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView = findViewById(R.id.youtube_view);
        moviesMap.put("Program meczowy #66", "5IQ_X1ZmgGw");
        moviesMap.put("Program meczowy #65", "KD8zBYodbyw");
        moviesMap.put("Ośma liga mistrzow odc. 17", "coBucG1-8h0");
        Set<String> moviesKeysSet = moviesMap.keySet();
        List<String> moviesTitlesList = new ArrayList<String>(moviesKeysSet);
        String[] moviesTitlesArray = new String[moviesTitlesList.size()];
        moviesTitlesList.toArray(moviesTitlesArray);

//        for (int i = 0; i < moviesMap.size(); i++) {
//            moviesTitles[i] = moviesMap.g
//        }
        Spinner movieSpinner = (Spinner) findViewById(R.id.movieSpinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_spinner_dropdown_item, moviesTitlesArray);
        movieSpinner.setAdapter(adapter);
        movieSpinner.setOnItemSelectedListener(this);
        //config = new Config();
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.loadVideo(getMovieURL());
                        System.out.println("Git");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                        System.out.println("Failure");

                    }
                };

    }

    public void onClickPlayVideo(View view) {
        youTubePlayerView.initialize(Config.getYoutubeApiKey(), onInitializedListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setMovieURL(moviesMap.get(parent.getItemAtPosition(position)));
        System.out.println("OnItem");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}