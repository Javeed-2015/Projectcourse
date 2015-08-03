package zarag.gpsapplicationii;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private Location currLocation;
    private List<Location> locations;
    private TextView travelDistance;
    private Button addBtn, removeBtn;

    private LocationListener locationListener;
    private LocationManager locationManager;

    private float distance, lastDistance;
    private String walked = "Distance already walked: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        currLocation = new Location(locationManager.GPS_PROVIDER);
        locations = new ArrayList<Location>();

        travelDistance = (TextView)findViewById(R.id.travelDistanceTxt);
        addBtn = (Button)findViewById(R.id.addBtn);
        removeBtn = (Button)findViewById(R.id.removeBtn);

        distance = 0;
        travelDistance.setText(walked + String.valueOf(distance));

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currLocation = location;
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

        addLocation();
        removeLastLocation();

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
    }

    private void addLocation() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currLocation != null && !locations.contains(currLocation)) {
                    locations.add(currLocation);
                    if(locations.size() > 1) {
                        // set the new distance
                        lastDistance = currLocation.distanceTo(locations.get(locations.size()-2));
                        distance += lastDistance ;
                        travelDistance.setText(walked + String.valueOf(distance));
                    }
                }
            }
        });
    }

    private void removeLastLocation() {
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(locations != null) {
                    // delete the last location
                    locations.remove(locations.size() - 1);
                    if(locations.size() > 1) {
                        distance -= lastDistance;
                        lastDistance = distance;
                    } else {
                        lastDistance = 0;
                        distance = 0;
                    }
                    travelDistance.setText(walked + String.valueOf(distance));
                }
            }
        });
    }
}
