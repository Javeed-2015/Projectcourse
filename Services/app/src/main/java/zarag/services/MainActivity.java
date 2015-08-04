package zarag.services;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean mBound;
    private MyService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.serviceBtn);

       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (mBound) {
                   Toast.makeText(getApplicationContext(), "random: " + mService.getRandomNumber(), Toast.LENGTH_LONG).show();
               } else {
                   Toast.makeText(getApplicationContext(), "Not bound", Toast.LENGTH_LONG).show();
               }
           }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(this, MyService.class);
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mBound) {
            unbindService(mConnection);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound = true;
            MyService.MyBinder mb = (MyService.MyBinder)service;
            mService = (MyService) mb.getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };
}
