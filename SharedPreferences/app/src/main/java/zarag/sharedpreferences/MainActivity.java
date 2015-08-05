package zarag.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    public static final String SAVED_VALUE = "savedValue";
    SharedPreferences preferences;
    Button saveBtn;
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        et = (EditText)findViewById(R.id.editText);
        tv = (TextView)findViewById(R.id.textView);

        preferences = getSharedPreferences("preferences", 0);
        String s = preferences.getString(SAVED_VALUE, "This would be null!");
        tv.setText(s);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newString = et.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SAVED_VALUE, newString);
                editor.commit();
            }
        });
    }

}
