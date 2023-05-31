package domain;

import java.io.Serializable;

public class Contract implements Serializable {
    private long dateBegin;
    private long dateEnd;
    private int id;
    private Extension ext;

    public Contract(long dateBegin, long dateEnd, int id, Extension ext) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.id = id;
        this.ext = ext;
    }

    public long getDateBegin() {return dateBegin;}
    public void setDateBegin(long dateBegin) {this.dateBegin = dateBegin;}
    public long getDateEnd() {return dateEnd;}
    public void setDateEnd(long dateEnd) {this.dateEnd = dateEnd;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Extension getExt() {return ext;}
    public void setExt(Extension ext) {this.ext = ext;}
}
