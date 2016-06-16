package jug;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimoneInventoryComplex implements Inventory {

    public Map<String, List<String>> from(List<String[]> parsedCSV) {
        return parsedCSV.stream().sequential()
                .collect(
                        LinkedHashMap<String, List<String>>::new,

                        (map, a) -> map.computeIfAbsent(
                                StringUtils.isNotEmpty(a[0]) ? a[0] : new ArrayList<>(map.keySet()).get(map.size() - 1),
                                (k) -> new ArrayList<>()).add(a[1]),

                        (mx, m2) -> m2.forEach((k1, v) -> mx.merge(k1, v,
                                (strings, strings2) -> Stream.concat(strings.stream(), strings2.stream())
                                        .collect(Collectors.toList()))));
    }
}
