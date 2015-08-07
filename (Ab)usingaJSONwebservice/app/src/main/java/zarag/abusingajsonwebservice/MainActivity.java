package zarag.abusingajsonwebservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button putBtn, rcvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The following "fix" allows for network activity in the main thread, is really a hack and should be avoided
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        callPutListener();
        callRecieveListener();
    }

    private void callRecieveListener() {
        rcvBtn = (Button)findViewById(R.id.rcvBtn);
        rcvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultHttpClient client = new DefaultHttpClient(new BasicHttpParams());
                HttpPost post = new HttpPost("http://mobi.randomsort.net/service.php?auth=secretkey&action=get");
                post.setHeader("Content-type", "application/json");

                InputStream is = null;
                String result = null;
                try {
                    HttpResponse res = client.execute(post);
                    HttpEntity ent = res.getEntity();
                    is = ent.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    //Our JSON string:
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    JSONObject j = new JSONObject(result);
                    String y = j.getString("y");
                    int x    = j.getInt("x");
                    Toast.makeText(getApplicationContext(), y + ":, " + x, Toast.LENGTH_LONG).show();
                } catch(Exception e) {
                    Log.e("Web", "Exception received", e);
                }
            }
        });
    }

    private void callPutListener() {
        putBtn = (Button)findViewById(R.id.putBtn);
        putBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefaultHttpClient client = new DefaultHttpClient(new BasicHttpParams());
                HttpPost post = new HttpPost("http://mobi.randomsort.net/service.php?auth=secretkey&action=put");

                JSONObject send = new JSONObject();
                try {
                    String val = "android";
                    ArrayList<BasicNameValuePair> nvp = new ArrayList<BasicNameValuePair>();
                    nvp.add(new BasicNameValuePair("value", val));
                    post.setEntity(new UrlEncodedFormEntity(nvp));
                } catch (Exception e) {
                    Log.e("Web", "JSON", e);
                }

                InputStream is = null;
                String result = null;
                try {
                    HttpResponse res = client.execute(post);
                    HttpEntity ent = res.getEntity();
                    is = ent.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    //Our JSON string:
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    JSONObject j = new JSONObject(result);
                    String returned = j.getString("result");
                    Toast.makeText(getApplicationContext(), returned, Toast.LENGTH_LONG).show();

                } catch(Exception e) {
                    Log.e("Web", "Exception received", e);
                }
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
}
