package hack.bacon.hackathon.Parent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hack.bacon.hackathon.R;

public class ParentMainActivity extends AppCompatActivity {

    @Bind(R.id.firstEntryBackgroundImage)
    ImageView firstEntryBackgroundImage;
    @Bind(R.id.firstEntryName)
    TextView firstEntryName;
    @Bind(R.id.firstEntryLocationTextView)
    TextView firstEntryLocationTextView;
    @Bind(R.id.firstEntryLastUpdatedTextView)
    TextView firstEntryLastUpdatedTextView;
    @Bind(R.id.firstEntryCardView)
    CardView firstEntryCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.firstEntryCardView)
    public void onFirstEntryCardViewClick() {
    }
}
