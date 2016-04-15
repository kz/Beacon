package in.iamkelv.beacon.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.QredoConnection;
import com.qredo.device.android.vault.VaultItem;
import com.qredo.device.android.vault.VaultItemRef;
import com.qredo.device.android.vault.VaultManager;
import com.qredo.device.android.vault.callback.VaultCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.iamkelv.beacon.Child.ChildEnterRendezvousActivity;
import in.iamkelv.beacon.Parent.ParentDisplayRendezvousActivity;
import in.iamkelv.beacon.Qredo.Qredo;
import in.iamkelv.beacon.R;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.nameTextBox)
    EditText nameTextBox;
    @Bind(R.id.usernameTextBox)
    EditText usernameTextBox;
    @Bind(R.id.passwordTextBox)
    EditText passwordTextBox;
    @Bind(R.id.parentRadio)
    RadioButton parentRadio;
    @Bind(R.id.childRadio)
    RadioButton childRadio;

    private String mName;
    private String mUsername;
    private String mPassword;
    private Boolean mIsParent;
    private VaultManager mVaultManager;

    private QredoConnection mConnection = new QredoConnection() {
        @Override
        public void onSuccess(QredoClient client) {
            Qredo.getInstance().setQredoClient(client);
            if (parentRadio.isChecked()) {
                mIsParent = true;
            }

            mVaultManager = client.getVaultManager();

            VaultItem item = new VaultItem("userDetails");
            item.putMetadata("type", "userDetails");
            item.putMetadata("name", mName);
            item.putMetadata("isParent", String.valueOf((mIsParent) ? 1 : 0));

            mVaultManager.put(item, new VaultCallback<VaultItemRef>() {
                @Override
                public void onSuccess(VaultItemRef vaultItemRef) {
                    if (mIsParent) {
                        Intent parentIntent = new Intent(RegisterActivity.this, ParentDisplayRendezvousActivity.class);
                        startActivity(parentIntent);
                        finish();
                    } else {
                        Intent childIntent = new Intent(RegisterActivity.this, ChildEnterRendezvousActivity.class);
                        startActivity(childIntent);
                        finish();
                    }
                }

                @Override
                public void onFailure(String reason) {
                    Toast.makeText(RegisterActivity.this, "Registration failed. Try again with different credentials.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onFailure(String reason) {
            Toast.makeText(RegisterActivity.this, "Registration failed. Try again with different credentials.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnected() {
            Toast.makeText(RegisterActivity.this, "Registration failed. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.registerButton)
    public void attemptRegistration(Button button) {
        mName = nameTextBox.getText().toString();
        mUsername = usernameTextBox.getText().toString();
        mPassword = passwordTextBox.getText().toString();
        // Validate presence of text in fields
        if (mName.matches("") || mUsername.matches("") || mPassword.matches("")) {
            Toast.makeText(this, "Ensure that no fields are blank and try again.", Toast.LENGTH_SHORT).show();
        }

        // Attempt Qredo connection
        QredoClient.bind(getString(R.string.app_secret), mUsername, mPassword, RegisterActivity.this, mConnection);
    }

}
