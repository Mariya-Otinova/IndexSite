package Entity;

public class Site {

    private String url;
    private int id;

    public Site() {}

    public Site(String url, int id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Site{" +
                "url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
