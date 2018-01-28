package com.af.radar.service;

import com.af.radar.enums.AlienType;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Alien;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by AKilickaya
 */
public class AlienServiceTest {

    Alien orc;
    Alien goblin;

    @Before
    public void setUp() throws RadarException {
        orc = AlienService.getAlienByType(AlienType.ORC);
        goblin = AlienService.getAlienByType(AlienType.GOBLIN);
    }

    @Test
    public void testGetAlienByType() throws RadarException {
        assertNotNull(orc);
        assertNotNull(goblin);
        assertEquals(8, orc.getColumnLength());
        assertEquals(11, orc.getRowLength());
        assertEquals(46, orc.getAlienCharCount());
    }

    @Test
    public void testFindLongestRowLengthOfAliens() {
        int longestRowLength = AlienService.findLongestRowLengthOfAliens(Arrays.asList(orc, goblin));
        assertEquals(11, longestRowLength);
    }

    @Test
    public void testFindLongestColumnLengthOfAliens() {
        int longestRowLength = AlienService.findLongestColumnLengthOfAliens(Arrays.asList(orc, goblin));
        assertEquals(8, longestRowLength);
    }
}