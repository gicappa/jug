package jug;

import java.util.List;
import java.util.Map;

public interface Inventory {
    Map<String, List<String>> from(List<String[]> parsedCSV);
}
