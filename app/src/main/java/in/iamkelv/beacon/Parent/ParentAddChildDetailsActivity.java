package in.iamkelv.beacon.Parent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.conversation.Conversation;
import com.qredo.device.android.conversation.ConversationManager;
import com.qredo.device.android.conversation.ConversationRef;
import com.qredo.device.android.conversation.callback.ConversationCallback;
import com.qredo.device.android.conversationmessage.ConversationMessage;
import com.qredo.device.android.conversationmessage.ConversationMessageManager;
import com.qredo.device.android.conversationmessage.ConversationMessageRef;
import com.qredo.device.android.conversationmessage.callback.ConversationMessageCallback;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.iamkelv.beacon.Qredo.Qredo;
import in.iamkelv.beacon.R;

public class ParentAddChildDetailsActivity extends AppCompatActivity {

    private QredoClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_add_child_details);
        ButterKnife.bind(this);
        mClient = Qredo.getInstance().getQredoClient();
    }

    @OnClick(R.id.proceedButton)
    public void proceed(Button button) {
        ConversationManager conversationManager = mClient.getConversationManager();
        conversationManager.list(getString(R.string.ren_tag), new ConversationCallback<Set<Conversation>>() {
            @Override
            public void onSuccess(Set<Conversation> conversationList) {
                for (Conversation conversation : conversationList) {
                    ConversationRef conversationRef = conversation.getConversationRef();

                    ConversationMessage message = new ConversationMessage("connect-parent");

                    ConversationMessageManager conversationMessageManager = mClient.getConversationMessageManager();
                    conversationMessageManager.send(conversationRef, message,
                            new ConversationMessageCallback<ConversationMessageRef>() {

                                @Override
                                public void onSuccess(ConversationMessageRef conversationMessageRef) {
                                    Intent parentIntent = new Intent(ParentAddChildDetailsActivity.this, ParentActivity.class);
                                    startActivity(parentIntent);
                                    finish();
                                }

                                @Override
                                public void onFailure(String reason) {
                                }

                            });

                    break;
                }
            }

            @Override
            public void onFailure(String reason) {
            }
        });
    }
}

