package jug;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimoneInventoryEasy implements Inventory {

    @Override
    public Map<String, List<String>> from(List<String[]> parsedCSV) {

        Map<String, List<String>> map = new LinkedHashMap<>();

        parsedCSV.stream().sequential().forEach(a -> map.computeIfAbsent(
                StringUtils.isNotEmpty(a[0]) ? a[0] : new ArrayList<>(map.keySet()).get(map.size() - 1),
                (k) -> new ArrayList<>()).add(a[1]));

        return map;
    }
}

