package zarag.drawingapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawView drawView = (DrawView)findViewById(R.id.drawView);
        drawView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DrawView thisView = (DrawView) view;
                thisView.setPoint(motionEvent.getX(), motionEvent.getY());
                thisView.invalidate();

                return false;
            }
        });

        EditText dotSize = (EditText)findViewById(R.id.dotSize);
        dotSize.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

              //  if(keyEvent.equals(KeyEvent.KEYCODE_ENTER) && view.) {

               // }

                return false;
            }
        });
    }
}
