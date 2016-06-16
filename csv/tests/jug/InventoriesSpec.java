package jug;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class InventoriesSpec {

    private List<Inventory> is;

    @Before
    public void before() {
        is = Arrays.asList(
                new VincenzoInventory(),
                new GicappaInventoryOne(),
                new GicappaInventoryTwo(),
                new SimoneInventoryEasy(),
                new SimoneInventoryComplex()
        );
    }
    static String CSV_TEXT_INPUT =
            "tv,led\n" +
                    ",oled\n" +
                    ",led\n" +
                    ",3d\n" +
                    ",4k\n" +
                    "frigorifero,doppia porta\n" +
                    ",da incasso\n" +
                    ",no frost";


    public List<String[]> parsedCSV() {
        return Arrays.asList(CSV_TEXT_INPUT.split("\n")).stream().map(line -> line.split(",")).collect(Collectors.toList());
    }

    @Test
    public void it_transform_a_list_of_array_in_a_Map_of_String_and_ListString() {
        is.stream()
                .forEach(i -> {
                            System.out.println("\n\nTesting Implementation " + i.getClass());
                            Map<String, List<String>> actual = i.from(parsedCSV());

                            assertThat(actual, is(expectedMap()));

                            System.out.println("OK : actual = " + actual);
                        }
                );
    }
    
    private Map<String, List<String>> expectedMap() {
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("tv",
                Arrays.asList("led", "oled", "led", "3d", "4k"));

        expected.put("frigorifero",
                Arrays.asList("doppia porta", "da incasso", "no frost"));

        return expected;
    }
}
