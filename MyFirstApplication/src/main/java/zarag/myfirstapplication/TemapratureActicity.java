package zarag.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class TemapratureActicity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temaprature_acticity);
    }

    protected void switchBack(View view) {
        Intent goBackToActivity = new Intent(this, MainActivity.class);
        startActivity(goBackToActivity);
    }
}
