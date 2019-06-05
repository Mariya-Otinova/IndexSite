package Entity;

import java.util.Date;

public class ModelStat extends Indexbot {
    private Date tmsCreated;
    private Date tmsLastIndex;
    private String url;

    public ModelStat() {}

    public ModelStat(int uniqwords, int words, int latinWords, int cyrillicWords, String freqWordList, String freqLetter, Date tmsCreated, Date tmsLastIndex, String url) {
        super(uniqwords, words, latinWords, cyrillicWords, freqWordList, freqLetter);
        this.tmsCreated = tmsCreated;
        this.tmsLastIndex = tmsLastIndex;
        this.url = url;
    }

    public Date getTmsCreated() {
        return tmsCreated;
    }

    public void setTmsCreated(Date tmsCreated) {
        this.tmsCreated = tmsCreated;
    }

    public Date getTmsLastIndex() {
        return tmsLastIndex;
    }

    public void setTmsLastIndex(Date tmsLastIndex) {
        this.tmsLastIndex = tmsLastIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ModelStat{" +
                "tmsCreated=" + tmsCreated +
                ", tmsLastIndex=" + tmsLastIndex +
                ", url=" + url +
                ", uniqwords=" + uniqwords +
                ", words=" + words +
                ", latinWords=" + latinWords +
                ", cyrillicWords=" + cyrillicWords +
                ", freqWordList='" + freqWordList + '\'' +
                ", freqLetter='" + freqLetter + '\'' +
                '}';
    }
}
