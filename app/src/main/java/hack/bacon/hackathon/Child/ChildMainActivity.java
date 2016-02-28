package hack.bacon.hackathon.Child;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.vault.VaultItem;
import com.qredo.device.android.vault.VaultItemRef;
import com.qredo.device.android.vault.VaultManager;
import com.qredo.device.android.vault.callback.VaultCallback;

import hack.bacon.hackathon.Location.AlarmReceiver;
import hack.bacon.hackathon.Location.LocationService;
import hack.bacon.hackathon.Qredo.Qredo;
import hack.bacon.hackathon.R;

public class ChildMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_main);

        QredoClient client = Qredo.getInstance().getQredoClient();

        VaultItem item = new VaultItem("child-location");
        VaultManager vaultManager = client.getVaultManager();

        item.putMetadata("type", "child-location");
        item.putMetadata("timestamp", String.valueOf(System.currentTimeMillis()));
        item.putMetadata("latitude", "51.5185092");
        item.putMetadata("longitude", "-0.088304");

        vaultManager.put(item, new VaultCallback<VaultItemRef>() {

            @Override
            public void onSuccess(VaultItemRef vaultItemRef) {
                Log.i("BEACON", "LOCATION SAVED.");
            }

            @Override
            public void onFailure(String reason) {
                Log.e("BEACON", "FAILURE. Reason: " + reason);
            }

        });


    }

    public void launchLocationService() {
        Intent locationIntent = new Intent(this, LocationService.class);
        startService(locationIntent);
    }

    // Setup a recurring alarm every half hour
    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis(); // Alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                5 * 60 * 1000, pIntent);
    }

}
