package hack.bacon.hackathon.Entity;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private double mLatitude;
    private double mLongitude;
    private long mTimestamp;

    public Location(double mLatitude, double mLongitude, long mTimestamp) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mTimestamp = mTimestamp;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public long getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
