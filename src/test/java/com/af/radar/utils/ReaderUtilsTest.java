package com.af.radar.utils;

import com.af.radar.constants.TestFileConstants;
import com.af.radar.exceptions.RadarException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by AKilickaya
 */
public class ReaderUtilsTest {

    @Test
    public void testConvertFileToList() throws RadarException {
        List<String> spaceRowList = ReaderUtils.convertFileToList(TestFileConstants.SPACE_FILE_PATH);
        assertNotNull(spaceRowList);
        assertFalse(spaceRowList.isEmpty());
        assertEquals(9, spaceRowList.size());
        assertEquals(10, spaceRowList.get(0).length());
    }

}