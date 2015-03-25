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
		
		// Get the location manager.
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Ask for the last known location or ...
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); // Kind of iffy (sometimes returns null)
		if (location != null){
			TextView tv = (TextView) findViewById(R.id.gps);
			tv.setText("Lat: " + location.getLatitude()
			+ "\nLong: " + location.getLongitude());
		}
		// ... request every time the location changes.
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener); // Use this in app, works more reliably.
	}

	private final LocationListener listener = new LocationListener() {
		// Whenever location changes, android calls onLocationChanged.
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
