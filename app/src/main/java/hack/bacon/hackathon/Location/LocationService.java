package hack.bacon.hackathon.Location;

import android.app.IntentService;
import android.content.Intent;

public class LocationService extends IntentService {

    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    // Must create a default constructor
    public LocationService() {
        super("beacon-location-service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {

    }
}