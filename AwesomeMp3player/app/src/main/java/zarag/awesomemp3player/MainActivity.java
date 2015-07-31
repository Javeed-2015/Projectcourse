package zarag.awesomemp3player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    SeekBar seekBar;
    ImageButton playBtn, pauseBtn, stopBtn;
    MediaPlayer mediaPlayer;
    Handler seekHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load the song and create a media player
        String song = "http://mobi.randomsort.net/wp-content/uploads/2015/07/filetoplay.mp3";
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(song));

        // init seek bar
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());

        // start the song after click
        playBtn = (ImageButton)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    drawSeekBar();
                }
            }
        });

        // pause the song
        pauseBtn = (ImageButton)findViewById(R.id.pauseBtn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });


        // stop the song
        stopBtn = (ImageButton)findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
        });

        // the awesome seek bar =)
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) { // thanks for the tip johan =)
                    seekBar.setProgress(i);
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // auto generated, but not needed yet
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // auto generated, but not needed yet
            }
        });
    }

    // update the seek bar while the sound is playing
    private void drawSeekBar() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null ){
                   seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
                seekHandler.postDelayed(this, 1000);
            }
        });
    }
}
