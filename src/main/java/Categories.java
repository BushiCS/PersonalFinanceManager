import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Categories {
    private String category;
    private int sum;
    private Map<String, Integer> categories = new HashMap<>();

    public Categories() {
    }

    public void setCategories(Map<String, Integer> categories) {
        this.categories = categories;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public int getSum() {
        return sum;
    }

    @JsonIgnore
    public Map<String, Integer> getCategories() {
        return categories;
    }


    public void setCategoryFromTSV(String title, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (title.equals(entry.getKey())) {
                category = (entry.getValue());
                break;
            } else {
                category = "другое";
            }
        }
    }

    public void setCategories() {
        if (categories.containsKey(category)) {
            categories.put(category, categories.get(category) + sum);
        } else {
            categories.put(category, sum);
        }
    }

    public String calcMaxCategory(Map<String, Integer> categories) {
        int sumMax = 0;
        Optional<Integer> max = categories.values().stream().max(Integer::compare);
        if (max.isPresent()) {
            sumMax = max.get();
        }
        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            if (entry.getValue().equals(sumMax)) {
                sum = sumMax;
                category = entry.getKey();
            }
        }
        return "{\"maxCategory\":" + "{\"category\":" + "\"" + category + "\"," + "\"sum\":" + sum + "}}";
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
