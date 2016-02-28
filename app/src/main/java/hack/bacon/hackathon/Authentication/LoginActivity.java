package hack.bacon.hackathon.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qredo.device.android.QredoClient;
import com.qredo.device.android.QredoConnection;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hack.bacon.hackathon.Child.ChildMainActivity;
import hack.bacon.hackathon.Parent.ParentMainActivity;
import hack.bacon.hackathon.Qredo.Qredo;
import hack.bacon.hackathon.R;

public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.usernameTextView)
    TextView usernameTextView;
    @Bind(R.id.passwordTextView)
    TextView passwordTextView;
    @Bind(R.id.usernameTextBox)
    EditText usernameTextBox;
    @Bind(R.id.passwordTextBox)
    EditText passwordTextBox;
    @Bind(R.id.loginBtn)
    Button loginBtn;
    @Bind(R.id.registerBtn)
    Button registerBtn;

    private boolean mIsParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private QredoConnection mConnection = new QredoConnection() {
        @Override public void onSuccess(QredoClient client){
            Qredo.getInstance().setQredoClient(client);

            if (mIsParent) {
                Intent nextIntent = new Intent(LoginActivity.this, ParentMainActivity.class);
                startActivity(nextIntent);
                finish();
            } else {
                Intent nextIntent = new Intent(LoginActivity.this, ChildMainActivity.class);
                startActivity(nextIntent);
                finish();
            }
        }
        @Override  public void onFailure(String reason) {
            Toast.makeText(LoginActivity.this, "Login failure. [Qredo Error]", Toast.LENGTH_SHORT).show();
        }
        @Override public void onDisconnected() {
            Toast.makeText(LoginActivity.this, "[Qredo Disconnected]", Toast.LENGTH_SHORT).show();
        }
    };


    @OnClick(R.id.loginBtn)
    public void onLoginClick(Button button) {
        // TODO: Remove hardcoded logins
        if (usernameTextBox.getText().toString().equals("parent")) {
            mIsParent = true;

            // Attempt Qredo connection
            QredoClient.bind(getString(R.string.app_secret), "parent", "parent", this, mConnection);
        } else if (usernameTextBox.getText().toString().equals("child")) {
            mIsParent = false;

            // Attempt Qredo connection
            QredoClient.bind(getString(R.string.app_secret), "child", "child", this, mConnection);
        } else {
            Toast.makeText(this, "Please enter a valid username and password.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.registerBtn)
    public void onRegisterClick(Button button) {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
