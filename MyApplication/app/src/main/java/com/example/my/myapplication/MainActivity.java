package com.example.my.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawView dw = (DrawView)findViewById(R.id.drawView1);
        dw.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event){

                DrawView thisView = (DrawView) v;
                thisView.setPoint((int)event.getX(), (int)event.getY());
                thisView.invalidate();
                return false;
            }
        });
    }
}


