//package in.iamkelv.beacon.ListenerService;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.util.Log;
//
//import com.qredo.device.android.QredoClient;
//import com.qredo.device.android.QredoConnection;
//import com.qredo.device.android.conversation.Conversation;
//import com.qredo.device.android.conversation.ConversationManager;
//import com.qredo.device.android.conversation.ConversationRef;
//import com.qredo.device.android.conversation.callback.ConversationCallback;
//import com.qredo.device.android.conversationmessage.ConversationMessage;
//import com.qredo.device.android.conversationmessage.ConversationMessageManager;
//import com.qredo.device.android.conversationmessage.callback.ConversationMessageListener;
//import com.qredo.device.android.rendezvous.Rendezvous;
//import com.qredo.device.android.rendezvous.RendezvousManager;
//import com.qredo.device.android.rendezvous.callback.RendezvousCallback;
//
//import java.util.Set;
//
//import in.iamkelv.beacon.R;
//
//
//public class ListenerService extends IntentService {
//
//    private QredoConnection mConnection;
//    private QredoClient mClient;
//    private ConversationManager mConversationManager;
//
//    // Must create a default constructor
//    public ListenerService() {
//        // Used to name the worker thread, important only for debugging.
//        super("beacon-service");
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate(); // if you override onCreate(), make sure to call super().
//        // If a Context object is needed, call getApplicationContext() here.
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        // This describes what will happen when service is triggered
//        QredoClient.bind(getString(R.string.app_secret), getString(R.string.username), getString(R.string.password), getApplicationContext(), mConnection);
//
//    }
//
//    private QredoConnection mConnection = new QredoConnection() {
//        @Override
//        public void onSuccess(QredoClient client) {
//            mClient = client;
//
//            mConversationManager = mClient.getConversationManager();
//            mConversationManager.list(getString(R.string.ren_tag), new ConversationCallback<Set<Conversation>>() {
//                @Override
//                public void onSuccess(Set<Conversation> conversations) {
//                    for (Conversation conversation : conversations) {
//                        ConversationRef conversationRef = conversation.getConversationRef();
//
//                        ConversationMessageListener conversationMessageListener = new ConversationMessageListener() {
//                            @Override
//                            public void onCounterpartLeft(ConversationMessage conversationMessage) {
//
//                            }
//
//                            @Override
//                            public void onFailure(String s) {
//                            }
//
//                            @Override
//                            public void onReceived(ConversationMessage conversationMessage) {
//                                if (!conversationMessage.isMine()) {
//
//                                }
//                            }
//                        };
//
//                        ConversationMessageManager conversationMessageManager = mClient.getConversationMessageManager();
//                        conversationMessageManager.addListener
//                                (conversationRef, conversationMessageListener);
//
//                        break;
//                    }
//                }
//
//                @Override
//                public void onFailure(String reason) {
//                }
//            });
//        }
//
//        @Override
//        public void onFailure(String reason) {
//        }
//
//        @Override
//        public void onDisconnected() {
//        }
//    };
//}