package hack.bacon.hackathon.Authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hack.bacon.hackathon.R;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.registerButton)
    Button registerButton;
    @Bind(R.id.usernameEditText)
    EditText usernameEditText;
    @Bind(R.id.passwordEditText)
    EditText passwordEditText;
    @Bind(R.id.usernameTextView)
    TextView usernameTextView;
    @Bind(R.id.passwordTextView)
    TextView passwordTextView;
    @Bind(R.id.parentRadio)
    RadioButton parentRadio;
    @Bind(R.id.childRadio)
    RadioButton childRadio;
    @Bind(R.id.parentOrChildGroup)
    RadioGroup parentOrChildGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.registerButton)
    public void onClick() {

    }
}
