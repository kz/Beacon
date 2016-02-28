package hack.bacon.hackathon.Parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hack.bacon.hackathon.Entity.Location;
import hack.bacon.hackathon.R;

public class LocationsFragment extends Fragment {
    private static final String CHILD_REN_TAG = "";

    private String childRenTag;

    protected LocationsAdapter mAdapter;

    protected RecyclerView mRecyclerView;

    protected ArrayList<Location> mDataset;

    public LocationsFragment() {
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
    public static LocationsFragment newInstance(String childRenTag) {
        LocationsFragment fragment = new LocationsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_locations, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.locationsRecyclerView);
        mRecyclerView.setAdapter(new LocationsAdapter(mDataset));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    // TODO: Remove hardcoded dataset
    private void initDataset() {
        mDataset = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mDataset.add(new Location(5.03, 5.03, 1456617243));
            mDataset.add(new Location(5.03, 5.03, 1456617244));
            mDataset.add(new Location(5.03, 5.03, 1456617245));
        }
    }
}
