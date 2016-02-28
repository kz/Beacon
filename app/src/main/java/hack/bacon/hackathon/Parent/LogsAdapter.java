package hack.bacon.hackathon.Parent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hack.bacon.hackathon.Entity.ChildLog;
import hack.bacon.hackathon.Entity.Location;
import hack.bacon.hackathon.R;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class LogsAdapter extends
        RecyclerView.Adapter<LogsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView timeTextView;
        public TextView typeTextView;
        public TextView sourceTextView;
        public TextView recipientTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            typeTextView = (TextView) itemView.findViewById(R.id.typeTextView);
            sourceTextView = (TextView) itemView.findViewById(R.id.sourceTextView);
            recipientTextView = (TextView) itemView.findViewById(R.id.recipientTextView);
        }
    }

    private List<ChildLog> mChildLog;

    public LogsAdapter(List<ChildLog> childLog) {
        mChildLog = childLog;
    }

    @Override
    public LogsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View logView = inflater.inflate(R.layout.item_log, parent, false);

        // Return a new holder instance
        return new ViewHolder(logView);
    }

    @Override
    public void onBindViewHolder(LogsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ChildLog childLog = mChildLog.get(position);

        // Set item views based on the data model
        TextView typeTextView = viewHolder.typeTextView;
        typeTextView.setText(String.format(typeTextView.getText().toString(),
                String.valueOf(childLog.getType())));

        TextView sourceTextView = viewHolder.sourceTextView;
        sourceTextView.setText(String.format(sourceTextView.getText().toString(),
                String.valueOf(childLog.getSource())));

        TextView recipientTextView = viewHolder.recipientTextView;
        recipientTextView.setText(String.format(recipientTextView.getText().toString(),
                String.valueOf(childLog.getRecipient())));

        TextView timeTextView = viewHolder.timeTextView;
        timeTextView.setText(String.format(timeTextView.getText().toString(), childLog.getDateTimeString()));
    }

    @Override
    public int getItemCount() {
        return mChildLog.size();
    }
}