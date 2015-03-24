package ualberta.cmput301.mocklocationtester;

import java.util.Date;

import com.example.gps.R;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class MockLocationTesterActivity extends Activity {

	public static final String MOCK_PROVIDER = "mockLocationProvider";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null){
			TextView tv = (TextView) findViewById(R.id.gps);
			tv.setText("Lat: " + location.getLatitude()
			+ "\nLong: " + location.getLongitude());
		}
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
	}

	private final LocationListener listener = new LocationListener() {
		public void onLocationChanged (Location location) {
			TextView tv = (TextView) findViewById(R.id.gps);
			if (location != null) {
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				Date date = new Date(location.getTime());
				
				tv.setText("The location is: \nLatitude: " + lat
						+ "\nLongitude: " + lng
						+ "\n at time: " + date.toString());
			} else {
				tv.setText("Cannot get the location");
			}
		}
		
		public void onProviderDisabled (String provider) {
			
		}
		
		public  void onProviderEnabled (String provider) {
			
		}
		
		public void onStatusChanged (String provider, int status, Bundle extras) {
			
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
