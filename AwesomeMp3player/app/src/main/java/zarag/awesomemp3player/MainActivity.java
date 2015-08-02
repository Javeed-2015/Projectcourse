package zarag.awesomemp3player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private ImageButton playBtn, pauseBtn, stopBtn;
    private Handler seekHandler = new Handler();
    private Boolean stopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String song = "http://mobi.randomsort.net/wp-content/uploads/2015/07/filetoplay.mp3";
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(song));

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());

        playBtn = (ImageButton)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(song);
            }
        });

        pauseBtn = (ImageButton)findViewById(R.id.pauseBtn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        stopBtn = (ImageButton)findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
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

    // start the media player
    private void start(String song) {
        if(!mediaPlayer.isPlaying()) {
            if(stopped) {
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(song));
                    mediaPlayer.prepare();
                    stopped = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            drawSeekBar();
            mediaPlayer.start();
        }
    }

    // pause the media player
    private void pause() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    // stop the media player and reset the seek bar
    private void stop() {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.stop();
        stopped = true;
    }

    // update the seek bar while the sound is playing
    private void drawSeekBar() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    seekHandler.postDelayed(this, 1000);
                }
            }
        });
    }
}
