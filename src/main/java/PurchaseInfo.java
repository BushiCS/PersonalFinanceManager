import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseInfo {
    private final String title;
    private final String date;
    private final int sum;

    public PurchaseInfo(@JsonProperty("title") String title,
                        @JsonProperty("date") String date,
                        @JsonProperty("sum") int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "{\"title\": "
                + " \"" + title + '\"'
                + ", \"date\": "
                + "\"" + date + '\"'
                + ", \"sum\": "
                + sum
                + '}';
    }
}
