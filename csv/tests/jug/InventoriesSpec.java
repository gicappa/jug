package jug;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InventoriesSpec {

    private List<Inventory> is;

    @Before
    public void before() {
        is = Arrays.asList(
                new InventoryOne(),
                new InventoryTwo(),
                new SimoneInventoryEasy(),
                new SimoneInventoryComplex()
        );
    }

    @Test
    public void it_transform_a_list_of_array_in_a_Map_of_String_and_ListString() {
        is.stream()
                .forEach(i -> {
                            System.out.println("\n\nTesting Implementation " + i.getClass());
                            Map<String, List<String>> actual = i.from(csvFixture());
                            assertThat(actual, is(expectedMap()));

                            System.out.println("OK : actual = " + actual);
                        }
                );


    }

    private Map<String, List<String>> expectedMap() {
        Map<String, List<String>> expected = new HashMap<>();

        expected.put("tv",
                Arrays.asList("led", "oled", "3d", "4k"));

        expected.put("frigorifero",
                Arrays.asList("doppia porta", "da incasso", "no frost"));

        return expected;
    }

    /**
     * tv, led
     * ,oled
     * ,3d
     * ,4k
     * frigorifero,doppia porta
     * ,da incasso
     * ,no frost
     *
     * @return
     */
    private List<String[]> csvFixture() {


        ArrayList<String[]> csvFixture = new ArrayList<>();
        csvFixture.add(new String[]{"tv", "led"});
        csvFixture.add(new String[]{null, "oled"});
        csvFixture.add(new String[]{null, "3d"});
        csvFixture.add(new String[]{null, "4k"});
        csvFixture.add(new String[]{"frigorifero", "doppia porta"});
        csvFixture.add(new String[]{null, "da incasso"});
        csvFixture.add(new String[]{null, "no frost"});
        return csvFixture;
    }
}
