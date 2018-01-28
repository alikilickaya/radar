package com.af.radar.service;

import com.af.radar.constants.ErrorConstants;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.*;
import com.af.radar.utils.MatrixUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKilickaya
 */
public final class RadarService {

    private RadarService() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Detect Alien's matrix inside space matrix
     *
     * @param space     matrix of extended space image file
     * @param alien     is tried to be detected
     * @param threshold desired minimum accuracy value for detection
     * @return detected result list
     * @throws RadarException with ERR105 errorCode {@link ErrorConstants#ERR105}
     */
    public static final List<Result> detect(Image space, Alien alien, double threshold) throws RadarException {
        List<Result> detectionList = new ArrayList<>();

        for (int x = 0; x < space.getColumnLength() - alien.getColumnLength() + 1; x++) {
            for (int y = 0; y < space.getRowLength() - alien.getRowLength() + 1; y++) {
                Coordinate currentCoordinate = new Coordinate(x, y);
                char[][] partitionOfSpace = MatrixUtils.getPartitionOfMatrix(space.getMatrix(), alien.getColumnLength(), alien.getRowLength(), currentCoordinate);
                ComparisonResult comparisonResult = MatrixUtils.compareMatrixByAlien(partitionOfSpace, alien);
                if (checkCompareResult(comparisonResult, threshold)) {
                    Result result = new Result(alien.getType(), currentCoordinate, comparisonResult, partitionOfSpace);
                    detectionList.add(result);
                }
            }
        }

        return detectionList;
    }

    /**
     * Checking if the comparison result which includes totalCharAccuracy and alienCharAccuracy is greater than threshold accuracy value.
     *
     * @param comparisonResult result of comparision between partitionOfSpace and Alien's matrix
     * @param threshold        desired minimum accuracy value
     * @return true if result is desired.
     * @see ComparisonResult
     */
    private static final boolean checkCompareResult(ComparisonResult comparisonResult, double threshold) {
        boolean desiredResult = false;
        if (comparisonResult.getTotalCharAccuracy() >= threshold &&
                comparisonResult.getAlienCharAccuracy() >= threshold) {
            desiredResult = true;
        }

        return desiredResult;
    }

}
