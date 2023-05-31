package domain;

public class Extension {
    private boolean ext;
    private long dateBegin;
    private long dateEnd;
    private boolean remind;
    private int[] reminder;

    public Extension(boolean ext, long dateBegin, long dateEnd, boolean remind, int[] reminder) {
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
    public int[] getReminder() {return reminder;}
    public void setReminder(int[] reminder) {this.reminder = reminder;}
}
