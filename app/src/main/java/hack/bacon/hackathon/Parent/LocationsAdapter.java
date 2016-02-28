package hack.bacon.hackathon.Parent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hack.bacon.hackathon.Entity.Location;
import hack.bacon.hackathon.R;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class LocationsAdapter extends
        RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView timeTextView;
        public TextView latitudeTextView;
        public TextView longitudeTextView;
        public Button mapButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            latitudeTextView = (TextView) itemView.findViewById(R.id.latitudetextView);
            longitudeTextView = (TextView) itemView.findViewById(R.id.longitudeTextView);
            mapButton = (Button) itemView.findViewById(R.id.mapButton);
        }
    }

    private List<Location> mLocations;

    public LocationsAdapter(List<Location> locations) {
        mLocations = locations;
    }

    @Override
    public LocationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_location, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(LocationsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Location location = mLocations.get(position);

        // Set item views based on the data model
        TextView latitudeTextView = viewHolder.latitudeTextView;
        latitudeTextView.setText(String.format(latitudeTextView.getText().toString(),
                String.valueOf(location.getLongitude())));

        TextView longitudeTextView = viewHolder.longitudeTextView;
        longitudeTextView.setText(String.format(longitudeTextView.getText().toString(),
                String.valueOf(location.getLongitude())));

        TextView timeTextView = viewHolder.timeTextView;
        timeTextView.setText(String.format(timeTextView.getText().toString(), location.getDateTimeString()));

        Button mapButton = viewHolder.mapButton;
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }
}