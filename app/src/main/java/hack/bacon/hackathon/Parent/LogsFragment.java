package hack.bacon.hackathon.Parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.Random;

import hack.bacon.hackathon.Entity.ChildLog;
import hack.bacon.hackathon.Entity.Location;
import hack.bacon.hackathon.R;

public class LogsFragment extends Fragment {
    private static final String CHILD_REN_TAG = "";

    private String childRenTag;

    protected LogsAdapter mAdapter;

    protected RecyclerView mRecyclerView;

    protected ArrayList<ChildLog> mDataset;

    public LogsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param childRenTag Child's Rendezvous Tag.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogsFragment newInstance(String childRenTag) {
        LogsFragment fragment = new LogsFragment();
        Bundle args = new Bundle();
        args.putString(CHILD_REN_TAG, childRenTag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            childRenTag = getArguments().getString(CHILD_REN_TAG);
        }

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_logs, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.logsRecyclerView);
        mRecyclerView.setAdapter(new LogsAdapter(mDataset));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    // TODO: Remove hardcoded dataset
    private void initDataset() {
        mDataset = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String[] types = {"SMS", "Call"};
            Random random = new Random();
            String type = types[random.nextInt(1)];
            RandomStringUtils randomStringUtils = new RandomStringUtils();
            String source = "+44788492738";
            String recipient = "+4478" + randomStringUtils.randomNumeric(8);
            mDataset.add(new ChildLog(type, source, recipient, System.currentTimeMillis()));
        }
    }
}
