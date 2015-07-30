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

    SeekBar sb;
    ImageButton playBtn, pauseBtn, stopBtn;
    MediaPlayer mp;
    Handler seekHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load the song and create a media player
        String song = "http://mobi.randomsort.net/wp-content/uploads/2015/07/filetoplay.mp3";
        mp = MediaPlayer.create(getApplicationContext(), Uri.parse(song));
        sb = (SeekBar)findViewById(R.id.seekBar);
        sb.setMax(mp.getDuration());

        // start the song after click
        playBtn = (ImageButton)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mp.isPlaying()) {
                    mp.start();
                    drawSeekBar();
                }
            }
        });

        // pause the song
        pauseBtn = (ImageButton)findViewById(R.id.pauseBtn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()) {
                    mp.pause();
                }
            }
        });

        // stop the song
        stopBtn = (ImageButton)findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying()) {
                    mp.pause();
                    mp.seekTo(0);
                }
            }
        });

        // the awesome seek bar =)
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

               // sb.jumpDrawablesToCurrentState();

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

    public void drawSeekBar() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mp != null ){
                    sb.setProgress(mp.getCurrentPosition());
                }
                seekHandler.postDelayed(this, 1000);
            }
        });
    }
}
