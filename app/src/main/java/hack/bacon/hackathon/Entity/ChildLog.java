package hack.bacon.hackathon.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildLog {
    private String mType;
    private String mSource;
    private String mRecipient;
    private long mTimestamp;

    public ChildLog(String mType, String mSource, String mRecipient, long mTimestamp) {
        this.mType = mType;
        this.mSource = mSource;
        this.mRecipient = mRecipient;
        this.mTimestamp = mTimestamp;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }

    public String getRecipient() {
        return mRecipient;
    }

    public void setRecipient(String mRecipient) {
        this.mRecipient = mRecipient;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getDateTimeString() {
        Date date = new Date(mTimestamp * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
