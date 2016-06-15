package jug;

import org.apache.commons.collections.map.LinkedMap;

import java.util.List;
import java.util.Map;

public class InventoryTwo implements Inventory {

    public Map<String, List<String>> from(List<String[]> parsedCSV) {
        return parsedCSV.stream()
                .collect(LinkedMap::new,
                        (acc, e) -> {
                            if (e[0] == null) e[0] = (String) acc.lastKey();
                            acc.put(e[0], e[1]);
                        },
                        (acc1, acc2) -> acc1.putAll(acc2)
                );
    }
}
