package zarag.awesomemp3player;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private ImageButton playBtn, pauseBtn, stopBtn;
    private Button createPlayListBtn;
    private Handler seekHandler = new Handler();
    private Boolean stopped = false;
    String song ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<File> mySongs = findSongs (Environment.getExternalStorageDirectory());

        if(mySongs.size()> 0)
        {
            for (int i = 3 ; i < 4; i++)
            {
                Toast.makeText(getApplicationContext(), mySongs.get(i).getPath(),Toast.LENGTH_SHORT).show();
                song = mySongs.get(i).getPath();
            }

        }


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

        createPlayListBtn = (Button)findViewById(R.id.createPlayListBtn);
        createPlayListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playListScreen = new Intent(getApplicationContext(), CreatePlaylist.class);
                startActivity(playListScreen);
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

    public ArrayList<File> findSongs(File root){
        ArrayList<File> listOfFile = new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile :files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                listOfFile.addAll(findSongs(singleFile));
            }
            else {
                if(singleFile.getName().endsWith(".mp3"))
                {
                    listOfFile.add(singleFile);
                }
            }
        }
        return listOfFile;
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
