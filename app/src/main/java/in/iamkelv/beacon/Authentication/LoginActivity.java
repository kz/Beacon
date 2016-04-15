package in.iamkelv.beacon.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qredo.device.android.ItemMetadata;
import com.qredo.device.android.QredoClient;
import com.qredo.device.android.QredoConnection;
import com.qredo.device.android.vault.VaultItemHeader;
import com.qredo.device.android.vault.VaultManager;
import com.qredo.device.android.vault.callback.VaultCallback;
import com.qredo.device.android.vault.callback.VaultItemHeaderMatcher;

import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.iamkelv.beacon.Child.ChildActivity;
import in.iamkelv.beacon.Parent.ParentActivity;
import in.iamkelv.beacon.Qredo.Qredo;
import in.iamkelv.beacon.R;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.usernameTextBox)
    EditText usernameTextBox;
    @Bind(R.id.passwordTextBox)
    EditText passwordTextBox;
    @Bind(R.id.loginButton)
    Button loginButton;
    @Bind(R.id.registerButton)
    Button registerButton;

    private String mUsername;
    private String mPassword;
    private String mName;
    private boolean mIsParent;

    private VaultManager mVaultManager;

    private QredoConnection mConnection = new QredoConnection() {
        @Override
        public void onSuccess(QredoClient client) {
            Qredo.getInstance().setQredoClient(client);

            mVaultManager = client.getVaultManager();

            mVaultManager.findHeaders(new VaultItemHeaderMatcher() {
                @Override
                public boolean match(VaultItemHeader vaultItemHeader) {

                    ItemMetadata vaultItemMetaData = vaultItemHeader.getItemMetadata();
                    String type = vaultItemMetaData.get("type");
                    return type.contains("userDetails");
                }
            }, new VaultCallback<Set<VaultItemHeader>>() {
                @Override
                public void onFailure(String s) {
                }

                @Override
                public void onSuccess(Set<VaultItemHeader> vaultItemHeaders) {
                    for (VaultItemHeader vaultItemHeader : vaultItemHeaders) {
                        ItemMetadata itemMetadata = vaultItemHeader.getItemMetadata();
                        mIsParent = (Integer.parseInt(itemMetadata.get("isParent")) == 1);
                        break;
                    }

                    if (mIsParent) {
                        Intent parentIntent = new Intent(LoginActivity.this, ParentActivity.class);
                        startActivity(parentIntent);
                        finish();
                    } else {
                        Intent childIntent = new Intent(LoginActivity.this, ChildActivity.class);
                        startActivity(childIntent);
                        finish();
                    }

                }
            });
        }

        @Override
        public void onFailure(String reason) {
            Toast.makeText(LoginActivity.this, "Login failed. Try again.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnected() {
            Toast.makeText(LoginActivity.this, "Login failed. Try again.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginButton)
    public void attemptLogin(Button button) {
        mUsername = usernameTextBox.getText().toString();
        mPassword = passwordTextBox.getText().toString();

        // Validate input
        if (mUsername.matches("") || mPassword.matches("")) {
            Toast.makeText(this, "Ensure that no fields are blank and try again.", Toast.LENGTH_SHORT).show();
        }

        QredoClient.bind(getString(R.string.app_secret), mUsername, mPassword, this, mConnection);
    }

    @OnClick(R.id.registerButton)
    public void register(Button button) {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }

}
