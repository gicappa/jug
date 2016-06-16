package jug;

import org.apache.commons.collections.map.LinkedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GicappaInventoryTwo implements Inventory {

    public Map<String, List<String>> from(List<String[]> parsedCSV) {
        return parsedCSV.stream().sequential()
                .collect(LinkedMap::new,
                        (acc, e) -> {
                            if (e[0] == null || e[0].isEmpty())
                                e[0] = (String) acc.lastKey();

                            List<String> values = (List<String>) acc.get(e[0]);
                            if (values == null) values = new ArrayList<>();
                            values.add(e[1]);

                            acc.put(e[0], values);
                        },
                        (acc1, acc2) -> acc1.putAll(acc2)
                );
    }
}
