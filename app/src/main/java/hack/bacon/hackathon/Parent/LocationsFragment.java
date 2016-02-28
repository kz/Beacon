package hack.bacon.hackathon.Parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qredo.device.android.ItemMetadata;
import com.qredo.device.android.QredoClient;
import com.qredo.device.android.vault.VaultItemHeader;
import com.qredo.device.android.vault.VaultManager;
import com.qredo.device.android.vault.callback.VaultCallback;
import com.qredo.device.android.vault.callback.VaultItemHeaderMatcher;

import java.util.ArrayList;
import java.util.Set;

import hack.bacon.hackathon.Entity.Location;
import hack.bacon.hackathon.Qredo.Qredo;
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

/*    private void initDataset() {
        mDataset = new ArrayList<>();

        QredoClient client = Qredo.getInstance().getQredoClient();

        VaultManager vaultManager = client.getVaultManager();

        vaultManager.findHeaders(new VaultItemHeaderMatcher() {
            @Override
            public boolean match(VaultItemHeader vaultItemHeader) {
                ItemMetadata vaultItemMetaData = vaultItemHeader.getItemMetadata();
                String type = vaultItemMetaData.get("type");
                return type.contains("child-location");
            }
        }, new VaultCallback<Set<VaultItemHeader>>() {
            @Override
            public void onFailure(String s) {
                Log.e("BEACON", "FAILURE. Reason: ");
            }

            @Override
            public void onSuccess(Set<VaultItemHeader> vaultItemHeaders) {
                for (VaultItemHeader vaultItemHeader : vaultItemHeaders) {
                    ItemMetadata itemMetaData = vaultItemHeader.getItemMetadata();
                    double latitude = Double.parseDouble(itemMetaData.get("latitude"));
                    double longitude = Double.parseDouble(itemMetaData.get("longitude"));
                    long timestamp = Long.parseLong(itemMetaData.get("timestamp"));

                    mDataset.add(new Location(latitude, longitude, timestamp));
                }
            }
        });
    }*/
}
