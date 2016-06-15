package jug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class InventoryOne implements Inventory {

    public Map<String, List<String>> from(List<String[]> parsedCSV) {
        return parsedCSV.stream()
                .collect(ArrayList<String[]>::new,
                        (acc, e) ->  acc.add(normalizeElement(acc,e)),
                        (acc1, acc2) -> acc1.addAll(acc2)).stream()
                .collect(Collectors.groupingBy(k -> k[0], mapping(v -> v[1], toList())));
    }

    public String[] normalizeElement(List<String[]> acc, String[] e) {
        e[0] = e[0] == null ? acc.get(acc.size() - 1)[0] : e[0];
        return e;
    }
}
