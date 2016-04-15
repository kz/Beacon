package in.iamkelv.beacon.Child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.conversation.ConversationRef;
import com.qredo.device.android.rendezvous.RendezvousManager;
import com.qredo.device.android.rendezvous.callback.RendezvousCallback;

import butterknife.OnClick;
import in.iamkelv.beacon.Qredo.Qredo;
import in.iamkelv.beacon.R;

public class ChildEnterRendezvousActivity extends AppCompatActivity {

    private QredoClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_enter_rendezvous);

        mClient = Qredo.getInstance().getQredoClient();
    }

    @OnClick(R.id.rendezvousButton)
    public void proceed(Button button) {
        RendezvousManager rendezvousManager = mClient.getRendezvousManager();

        rendezvousManager.respond(getString(R.string.ren_tag), new RendezvousCallback<ConversationRef>() {

            @Override
            public void onSuccess(ConversationRef conversationRef) {
                Intent childIntent = new Intent(ChildEnterRendezvousActivity.this, ChildActivity.class);
                startActivity(childIntent);
                finish();
            }

            @Override
            public void onFailure(String reason) {
            }
        });
    }
}
