package test.exploration;

import exploration.Position;
import exploration.Zone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import save.SaveUtils;

import java.io.File;

public class ZoneTest {

    Zone zone;

    @BeforeEach
    public void beforeEach() {
        SaveUtils.copyMap(SaveUtils.DEFAULT_MAP, SaveUtils.TEMP_MAP);
        zone = new Zone(new Position(3, 7, 8));
    }

    @AfterEach
    public void afterEach() {
        SaveUtils.deleteTempFiles();
    }

    @Test
    public void testZoneDisplay() {
        Assertions.assertEquals("###########\r\n" +
                                        "#.........#\r\n" +
                                        "#.C....C..#\r\n" +
                                        "#.........#\r\n" +
                                        "#......C..#\r\n" +
                                        "..........#\r\n" +
                                        "#.........#\r\n" +
                                        "#..C....P.#\r\n" +
                                        "#.........#\r\n" +
                                        "#......C..#\r\n" +
                                        "#####.#####\r\n",
                zone.display());
    }

    @Test
    public void testZoneLinks() {
        Assertions.assertEquals(new Position(6, 0, 5),
                zone.link(new Position(3, 10, 5)));
    }

    @Test
    public void testZoneLinkNotFound() {
        Assertions.assertNull(zone.link(new Position(3, 5, 10)));
    }
}
