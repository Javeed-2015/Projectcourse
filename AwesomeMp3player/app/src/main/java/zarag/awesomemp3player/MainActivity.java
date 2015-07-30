package zarag.awesomemp3player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    SeekBar sb;
    ImageButton playBtn, pauseBtn, stopBtn;
    //MediaController mc;
    //int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // load the song and create a media player
        String song = "http://mobi.randomsort.net/wp-content/uploads/2015/07/filetoplay.mp3";
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), Uri.parse(song));

        // start the song after click
        playBtn = (ImageButton)findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mp.isPlaying()) {
                    mp.start();
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
                if(mp.isPlaying()) {
                    mp.pause();
                    mp.seekTo(0);
                }
            }
        });

        // the awesome seek bar =)
        sb = (SeekBar)findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               // sb.jumpDrawablesToCurrentState();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //      mp.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
