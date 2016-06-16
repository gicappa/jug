package jug;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class VincenzoInventory implements Inventory {

    @Override
    public Map<String, List<String>> from(List<String[]> parsedCSV) {
        AtomicReference<String> atomicKey = new AtomicReference<>();
        atomicKey.set("");

        return parsedCSV
                .stream()
                .sequential()
                .collect(Collectors.groupingBy(v -> {

                            if (atomicKey.get().isEmpty() && v[0].isEmpty())
                                throw new RuntimeException("key cannot be empty");

                            if (atomicKey.get().isEmpty())
                                atomicKey.set(v[0]);
                            else if (v[0].isEmpty())
                                v[0] = atomicKey.get();
                            else
                                atomicKey.set(v[0]);
                            return v[0];

                        },
                        Collectors.mapping(v -> v[1], Collectors.toList())
                ));
    }
}
