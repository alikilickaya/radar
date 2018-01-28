package com.af.radar.service;

import com.af.radar.constants.TestFileConstants;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Image;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by AKilickaya
 */
public class SpaceServiceTest {

    @Test
    public void testGetExtendedSpaceMatrixFromFile() throws RadarException {
        Image extendedSpace = SpaceService.getExtendedSpaceMatrixFromFile(TestFileConstants.SPACE_FILE_PATH, 10, 7);
        assertEquals(23, extendedSpace.getColumnLength());
        assertEquals(30, extendedSpace.getRowLength());
        assertEquals('-', extendedSpace.getMatrix()[0][0]);
    }
}