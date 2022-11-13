import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Categories {
    private String category;
    private int sum;
    private Map<String, Integer> categories = new HashMap<>();

    ObjectMapper mapper = new ObjectMapper();

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

    @JsonTypeName ("maxCategory")
    @JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
    static class MaxCategory {
        String category;
        int sum;

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    public String calcMaxCategory(Map<String, Integer> categories) {
        MaxCategory maxCategory = new MaxCategory();
        int sumMax = 0;
        Optional<Integer> max = categories.values().stream().max(Integer::compare);
        if (max.isPresent()) {
            sumMax = max.get();
        }
        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            if (entry.getValue().equals(sumMax)) {
                maxCategory.setSum(sumMax);
                maxCategory.setCategory(entry.getKey());
            }
        }
        try {
            return mapper.writeValueAsString(maxCategory);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // return "{\"maxCategory\":" + "{\"category\":" + "\"" + category + "\"," + "\"sum\":" + sum + "}}";
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
