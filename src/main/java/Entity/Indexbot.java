package Entity;

public class Indexbot {
    protected int uniqwords;
    protected int words;
    protected int latinWords;
    protected int cyrillicWords;
    protected String freqWordList;
    protected String freqLetter;

    public Indexbot() {}

    public Indexbot(int uniqwords, int words, int latinWords, int cyrillicWords, String freqWordList, String freqLetter) {
        this.uniqwords = uniqwords;
        this.words = words;
        this.latinWords = latinWords;
        this.cyrillicWords = cyrillicWords;
        this.freqWordList = freqWordList;
        this.freqLetter = freqLetter;
    }

    public int getUniqwords() {
        return uniqwords;
    }

    public void setUniqwords(int uniqwords) {
        this.uniqwords = uniqwords;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getLatinWords() {
        return latinWords;
    }

    public void setLatinWords(int latinWords) {
        this.latinWords = latinWords;
    }

    public int getCyrillicWords() {
        return cyrillicWords;
    }

    public void setCyrillicWords(int cyrillicWords) {
        this.cyrillicWords = cyrillicWords;
    }

    public String getFreqWordList() {
        return freqWordList;
    }

    public void setFreqWordList(String freqWordList) {
        this.freqWordList = freqWordList;
    }

    public String getFreqLetter() {
        return freqLetter;
    }

    public void setFreqLetter(String freqLetter) {
        this.freqLetter = freqLetter;
    }

    @Override
    public String toString() {
        return "Indexbot{" +
                "uniqwords=" + uniqwords +
                ", words=" + words +
                ", latinWords=" + latinWords +
                ", cyrillicWords=" + cyrillicWords +
                ", freqWordList='" + freqWordList + '\'' +
                ", freqLetter='" + freqLetter + '\'' +
                '}';
    }
}
