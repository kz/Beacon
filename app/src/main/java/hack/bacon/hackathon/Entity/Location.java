package hack.bacon.hackathon.Entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getDateTimeString() {
        Date date = new Date(mTimestamp * 1L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
