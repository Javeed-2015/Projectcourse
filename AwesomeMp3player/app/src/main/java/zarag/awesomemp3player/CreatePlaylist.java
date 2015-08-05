package zarag.awesomemp3player;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreatePlaylist extends Activity {

    Button playlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);

        playlistBtn = (Button)findViewById(R.id.playlistBtn);
        playlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
