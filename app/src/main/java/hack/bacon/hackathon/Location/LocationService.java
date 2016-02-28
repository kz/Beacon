package hack.bacon.hackathon.Location;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.QredoConnection;
import com.qredo.device.android.vault.VaultItem;
import com.qredo.device.android.vault.VaultItemRef;
import com.qredo.device.android.vault.VaultManager;
import com.qredo.device.android.vault.callback.VaultCallback;

import hack.bacon.hackathon.R;

public class LocationService extends IntentService {

    private VaultManager mVaultManager;

    // Must create a default constructor
    public LocationService() {
        super("beacon-location-service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private QredoConnection mConnection = new QredoConnection() {
        @Override public void onSuccess(QredoClient client){
            VaultItem item = new VaultItem("child-location");
            mVaultManager = client.getVaultManager();

            item.putMetadata("type", "child-location");
            item.putMetadata("timestamp", String.valueOf(System.currentTimeMillis()));
            item.putMetadata("latitude", "51.5185092");
            item.putMetadata("longitude", "-0.088304");

            mVaultManager.put(item, new VaultCallback<VaultItemRef>() {

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
        @Override  public void onFailure(String reason) {
            Log.e("BEACON", "FAILURE. Reason: " + reason);
        }
        @Override public void onDisconnected() {
            Log.w("BEACON", "DISCONNECTED.");
        }
    };


    @Override
    protected void onHandleIntent(Intent intent) {
        QredoClient.bind(getString(R.string.app_secret), "parent", "parent", this, mConnection);
    }
}