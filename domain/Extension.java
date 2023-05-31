package domain;

import java.io.Serializable;
import java.util.SortedMap;

public class Extension implements Serializable {
    private boolean ext;
    private long dateBegin;
    private long dateEnd;
    private boolean remind;
    private SortedMap<Integer, Boolean> reminder;

    public Extension(boolean ext, long dateBegin, long dateEnd, boolean remind, SortedMap<Integer, Boolean> reminder) {
        this.ext = ext;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.remind = remind;
        this.reminder = reminder;
    }

    public boolean isExt() {return ext;}
    public void setExt(boolean ext) {this.ext = ext;}
    public long getDateBegin() {return dateBegin;}
    public void setDateBegin(long dateBegin) {this.dateBegin = dateBegin;}
    public long getDateEnd() {return dateEnd;}
    public void setDateEnd(long dateEnd) {this.dateEnd = dateEnd;}
    public boolean isRemind() {return remind;}
    public void setRemind(boolean remind) {this.remind = remind;}
    public SortedMap<Integer, Boolean> getReminder() {return reminder;}
    public void setReminder(SortedMap<Integer, Boolean> reminder) {this.reminder = reminder;}
}
