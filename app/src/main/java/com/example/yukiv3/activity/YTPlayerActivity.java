package com.example.yukiv3.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;


import com.example.yukiv3.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBarListener;

import org.jetbrains.annotations.NotNull;

public class YTPlayerActivity extends AppCompatActivity {

    public YouTubePlayerView ytPlayer;
    public TextView videoTitle;
    public int time = 0;
    public TextView displayTime;
    public Button button;

    public YouTubePlayerSeekBar youTubePlayerSeekBar;

    LinearLayout lin;


//    public YTPlayerActivity(YouTubePlayerSeekBar youTubePlayerSeekBar) {
//        this.youTubePlayerSeekBar = youTubePlayerSeekBar;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytplayer);

        ytPlayer = findViewById(R.id.yt_player);
        videoTitle = findViewById(R.id.video_title);
        displayTime = findViewById(R.id.timeStamp);
        youTubePlayerSeekBar = findViewById(R.id.youtube_player_seekbar);
        lin = findViewById(R.id.linearLayout);
        button = findViewById(R.id.button);


        lin.removeAllViews();




        Intent data = getIntent();


        final String videoId = data.getStringExtra("video_id");
        String video_title = data.getStringExtra("video_title");
        ytPlayer.addYouTubePlayerListener(youTubePlayerSeekBar);



        ytPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NotNull final YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId,0);
//      doesnt do anything          youTubePlayer.seekTo(time);

                youTubePlayerSeekBar.setYoutubePlayerSeekBarListener(new YouTubePlayerSeekBarListener() {
                    @Override
                    public void seekTo(float time) {

                        youTubePlayer.seekTo(time);


                    }
                });








            }

            @Override
            public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                displayTime.setText(String.valueOf(second));
            }






        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView textView = new TextView(YTPlayerActivity.this);

                textView.setText(displayTime.getText());




                final EditText editText = new EditText(YTPlayerActivity.this);

                Button buttonView = new Button(YTPlayerActivity.this);

                buttonView.setText("clear");

                ytPlayer.addYouTubePlayerListener(youTubePlayerSeekBar);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String s = textView.getText().toString();
                        final Float f= Float.parseFloat(s);
//                        editText.setText("Hello world!");

//                        youTubePlayerSeekBar.getSeekBar().setProgress(2);

                        youTubePlayerSeekBar.getYoutubePlayerSeekBarListener().seekTo(f);
//                        youTubePlayerSeekBar.setYoutubePlayerSeekBarListener(new YouTubePlayerSeekBarListener() {
//                            @Override
//                            public void seekTo(float v) {
//                                seekTo(v);
//                            }
//                        });
//



                    }
                });

                lin.addView(textView);
                lin.addView(editText);
                lin.addView(buttonView);

            }
        });




//this is where it would be set
        videoTitle.setText(video_title);





    }
}
