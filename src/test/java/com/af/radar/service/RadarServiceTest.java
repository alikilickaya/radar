package com.af.radar.service;

import com.af.radar.constants.TestFileConstants;
import com.af.radar.enums.AlienType;
import com.af.radar.model.Alien;
import com.af.radar.model.Image;
import com.af.radar.model.Result;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by AKilickaya
 */
public class RadarServiceTest {

    @Test
    public void testDetect() throws Exception {
        double givenAccuracy = 0.93;
        Alien orc = AlienService.getAlienByType(AlienType.ORC);
        Alien goblin = AlienService.getAlienByType(AlienType.GOBLIN);
        int extendWidthLength = AlienService.findLongestRowLengthOfAliens(Arrays.asList(orc, goblin)) - 1;
        int extendHeightLength = AlienService.findLongestColumnLengthOfAliens(Arrays.asList(orc, goblin)) - 1;
        Image extendedSpace = SpaceService.getExtendedSpaceMatrixFromFile(TestFileConstants.SPACE_FILE_PATH, extendWidthLength, extendHeightLength);

        List<Result> detectedOrcList = RadarService.detect(extendedSpace, orc, givenAccuracy);
        List<Result> detectedGoblinList = RadarService.detect(extendedSpace, goblin, givenAccuracy);
        List<Result> allDetectedAlienList = new ArrayList<>(detectedOrcList.size() + detectedGoblinList.size());
        allDetectedAlienList.addAll(detectedOrcList);
        allDetectedAlienList.addAll(detectedGoblinList);

        assertNotNull(allDetectedAlienList);
        assertEquals(1, allDetectedAlienList.size());
        Result result = allDetectedAlienList.get(0);
        double totalCharAccuracy = result.getComparisonResult().getTotalCharAccuracy();
        double alienCharAccuracy = result.getComparisonResult().getAlienCharAccuracy();

        assertEquals(true, alienCharAccuracy >= givenAccuracy);
        assertEquals(true, totalCharAccuracy >= givenAccuracy);
        assertEquals(AlienType.GOBLIN, result.getAlienType());
    }

}