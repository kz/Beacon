package hack.bacon.hackathon.Qredo;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.QredoConnection;

public class Qredo {
    private static Qredo qredoInstance = null;

    private QredoClient qredoClient;

    public static Qredo getInstance() {
        if (qredoInstance == null) {
            qredoInstance = new Qredo();
        }

        return qredoInstance;

    }

    private Qredo() {
        this.qredoClient = null;
    }

    public QredoClient getQredoClient() {
        return this.qredoClient;
    }

    public void setQredoClient(QredoClient client) {
        this.qredoClient = client;
    }
}