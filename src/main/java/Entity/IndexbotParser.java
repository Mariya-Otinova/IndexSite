package Entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class IndexbotParser extends Indexbot {

    public Document parseDocument(Site site) {
        String URL = site.getUrl();
        Document document = new Document("Remote");
        try {
            document = Jsoup.connect(URL).get();
        } catch (Exception ex) {
            System.out.println("Не удалось получить страницу сайта -->" + ex.getMessage());
        } return document;
    }

    private String preprocessingDocument(Document document) {
        String STOCK =  document.text(); //Удаление html тегов
        STOCK = STOCK.toLowerCase(); //Приведение к нижнему регистру
        StringBuilder builder = new StringBuilder(); //Оставляем только слова через пробелы
        for (char c : STOCK.toCharArray()) {
            if (Character.isLetter(c) | Character.isWhitespace(c)) {
                builder.append(c);
            }
        }
        STOCK = builder.toString();
        return STOCK;
    }

    private int getUniqwords(String string) {
        int UNIQWORDS;
        String[] arrayWords = string.split(" ");
        ArrayList<String> arrayUniqWords = new ArrayList<String>();
        for (String st : arrayWords) {
            if (!arrayUniqWords.contains(st)) {
                arrayUniqWords.add(st);
            }
        }
        UNIQWORDS = arrayUniqWords.size();
        arrayUniqWords.clear();
        return UNIQWORDS;
    }

    private int getWords(String string) {
        int WORDS;
        String[] arrayWords = string.split(" ");
        WORDS = arrayWords.length;
        return WORDS;
    }

    private int getLatinWords(String string) {
        int LATINWORDS = 0;
        String[] arrayWords = string.split(" ");
        for (String st : arrayWords) {
            if (st.matches("^[a-z]+$")) {
                LATINWORDS++;
            }
        }
        return LATINWORDS;
    }

    private int getCyrillicWords(String string) {
        int CYRILLICWORDS = 0;
        String[] arrayWords = string.split(" ");
        for (String st : arrayWords) {
            if (st.matches("^[а-яё]+$")) {
                CYRILLICWORDS++;
            }
        }
        return CYRILLICWORDS;
    }


    public static String getFreqValueList(String[] arrayValue) {
        String FREQVALUELIST;
        Arrays.sort(arrayValue);
        ArrayList<String> listValues = new ArrayList<String>(Arrays.asList(arrayValue));
        class ValueAndFreq {
            String VALUE;
            int FREQ;
            public ValueAndFreq(String VALUE, int FREQ) {
                this.VALUE = VALUE;
                this.FREQ = FREQ;
            }
            public int getFREQ() {
                return FREQ;
            }
            @Override
            public String toString() {
                return VALUE + "=" + FREQ;
            }
        }
        ArrayList<ValueAndFreq> valueAndFreq = new ArrayList<ValueAndFreq>();
        ArrayList<String> arrayUniqValues = new ArrayList<String>();
        for (String st : arrayValue) {
            if (!arrayUniqValues.contains(st)) {
                int COUNT = 0;
                int i = 0;
                while (listValues.get(i).equals(st)) {
                    COUNT++;
                    if (i < listValues.size()-1) { i++; } else { break; }
                }
                for (int j=0; j<COUNT; j++) {
                    listValues.remove(0);
                }
                valueAndFreq.add(new ValueAndFreq(st,COUNT));
                arrayUniqValues.add(st);
            }
        }
        Comparator<ValueAndFreq> comparator = Comparator.comparing(obj -> obj.getFREQ());
        Collections.sort(valueAndFreq, comparator.reversed());
        FREQVALUELIST = valueAndFreq.toString();
        valueAndFreq.clear();
        arrayUniqValues.clear();
        return FREQVALUELIST;
    }


    private String getFreqWordList(String string) {
        String[] arrayWords = string.split(" ");
        String FREQWORDLIST = getFreqValueList(arrayWords);
        return FREQWORDLIST;
    }

    private String getFreqLetter(String string) {
        String[] letter = StringUtils.deleteWhitespace(string).split("");
        String FREQLETTER = getFreqValueList(letter);
        return FREQLETTER;
    }

    public void rateStatistics(Indexbot indexbot, Site site) {
        Document document = parseDocument(site);
        String STOCK = preprocessingDocument(document);
        indexbot.setUniqwords(getUniqwords(STOCK));
        indexbot.setWords(getWords(STOCK));
        indexbot.setLatinWords(getLatinWords(STOCK));
        indexbot.setCyrillicWords(getCyrillicWords(STOCK));
        indexbot.setFreqWordList(getFreqWordList(STOCK));
        indexbot.setFreqLetter(getFreqLetter(STOCK));
    }
}
