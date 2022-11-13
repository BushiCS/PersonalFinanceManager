import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CategoriesTest {
    Categories maxCategory = new Categories();
    Map<String, Integer> categories = new HashMap<>();

    @Test
    void calcMaxCategoryTest() {
        categories.put("еда", 330);
        categories.put("одежда", 1000);
        categories.put("финансы", 500);
        Assertions.assertEquals("{\"maxCategory\":" + "{\"category\":" + "\"одежда\"," + "\"sum\":1000}}", maxCategory.calcMaxCategory(categories));
    }
}
