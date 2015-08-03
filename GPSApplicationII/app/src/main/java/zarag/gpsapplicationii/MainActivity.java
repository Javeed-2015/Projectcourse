package zarag.gpsapplicationii;

import android.app.Activity;
import android.content.Context;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Location currLocation;
    private List<Location> locations;
    private TextView travelDistance;
    private Button addBtn, removeBtn;

    // private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currLocation = null;
        locations = new ArrayList<Location>();

        travelDistance = (TextView)findViewById(R.id.travelDistanceTxt);
        addBtn = (Button)findViewById(R.id.addBtn);
        removeBtn = (Button)findViewById(R.id.removeBtn);

        travelDistance.setText("0");

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currLocation = new Location(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //locationManager.addGpsStatusListener((GpsStatus.Listener) locationListener);

        addLocation();
        removeLastLocation();
    }

    private void addLocation() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currLocation != null && !locations.contains(currLocation)) {
                    locations.add(currLocation);
                    // set the new distance
                    Float tmpDistance = refreshDistance(Float.parseFloat(travelDistance.getText().toString()),
                            currLocation.distanceTo(locations.get(locations.size() - 2)));
                    travelDistance.setText(String.valueOf(tmpDistance));
                }
            }
        });
    }

    private void removeLastLocation() {
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!locations.isEmpty()) {
                    // delete the last location
                    locations.remove(locations.size() - 1);
                }
            }
        });
    }

    private float refreshDistance(float oldDistance, float newDistance) {
        return oldDistance + newDistance;
    }
}
