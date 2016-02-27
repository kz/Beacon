package hack.bacon.hackathon.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hack.bacon.hackathon.Child.ChildMainActivity;
import hack.bacon.hackathon.Parent.ParentMainActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.loginBtn)
    public void onLoginClick(Button button) {
        // TODO: Remove hardcoded logins
        if (usernameTextBox.getText().toString().equals("parent")) {
            // Open ParentMainActivity
            Intent parentIntent = new Intent(LoginActivity.this, ParentMainActivity.class);
            startActivity(parentIntent);
            finish();
        } else if (usernameTextBox.getText().toString().equals("child")) {
            // Open ChildMainActivity
            Intent childIntent = new Intent(LoginActivity.this, ChildMainActivity.class);
            startActivity(childIntent);
            finish();
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
