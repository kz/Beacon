package in.iamkelv.beacon.Parent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.conversation.Conversation;
import com.qredo.device.android.conversation.ConversationManager;
import com.qredo.device.android.conversation.ConversationRef;
import com.qredo.device.android.conversation.callback.ConversationCreatedListener;
import com.qredo.device.android.rendezvous.Rendezvous;
import com.qredo.device.android.rendezvous.RendezvousCreationParams;
import com.qredo.device.android.rendezvous.RendezvousManager;
import com.qredo.device.android.rendezvous.RendezvousRef;
import com.qredo.device.android.rendezvous.callback.RendezvousCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.iamkelv.beacon.Qredo.Qredo;
import in.iamkelv.beacon.R;

public class ParentDisplayRendezvousActivity extends AppCompatActivity {

    @Bind(R.id.rendezvousTagLabel)
    TextView mRendezvousTagLabel;

    private String mRendezvousTag;
    private QredoClient mClient;
    private RendezvousRef mRendezvousRef;
    private ConversationRef mConversationRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_parent_display_rendezvous);

        // Get client
        mClient = Qredo.getInstance().getQredoClient();

        // Generate rendezvous tag
        mRendezvousTag = getString(R.string.ren_tag);

        // Create and listen for a rendezvous ref
        int duration = 3600; // Amount of time (seconds) until the rendezvous expires
        RendezvousManager mRendezvousManager = mClient.getRendezvousManager();

        RendezvousCreationParams.Builder builder =
                mRendezvousManager.creationParamsBuilder(mRendezvousTag);

        RendezvousCreationParams rendezvousCreationParams =
                builder.setResponseCountLimit(Rendezvous.ResponseCountLimit.UNLIMITED_RESPONSES).setDuration(duration).build();

        mRendezvousManager.create(rendezvousCreationParams,
                new RendezvousCallback<Rendezvous>() {

                    @Override
                    public void onSuccess(final Rendezvous rendezvous) {
                        mRendezvousRef = rendezvous.getRendezvousRef();
                        mRendezvousTagLabel.setText(mRendezvousTag);

                        // TODO: CALLBACK HELL
                        ConversationCreatedListener conversationCreatedListener = new ConversationCreatedListener() {

                            @Override
                            public void onReceived(Conversation conversation) {
                                Intent nextIntent = new Intent(ParentDisplayRendezvousActivity.this, ParentAddChildDetailsActivity.class);
                                startActivity(nextIntent);
                                finish();
                            }

                            @Override
                            public void onFailure(String error) {
                            }
                        };

                        ConversationManager conversationManager = mClient.getConversationManager();
                        conversationManager.addListener(mRendezvousRef, conversationCreatedListener);
                    }

                    @Override
                    public void onFailure(String reason) {
                    }
                });
    }
}
