package com.af.radar.utils;

import com.af.radar.constants.GeneralConstants;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Alien;
import com.af.radar.model.ComparisonResult;
import com.af.radar.model.Coordinate;

import java.util.List;

/**
 * Created by AKilickaya
 */
public final class MatrixUtils {

    private MatrixUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * returns partitionOfMatrix from given coordinate(x, y) with given columnLength(height)
     * and rowLength(width)
     *
     * @param matrix       is getting partition from
     * @param columnLength partition matrix of column length(height)
     * @param rowLength    partition matrix of row length(width)
     * @param coordinate   beginnig coordinate of partition matrix on matrix
     * @return char[columnLength][rowLength] matrix
     * @throws RadarException with ERR105 errorCode
     */
    public static final char[][] getPartitionOfMatrix(char[][] matrix, int columnLength, int rowLength, Coordinate coordinate) throws RadarException {
        char[][] partitionOfSpace = new char[columnLength][rowLength];

        for (int x = 0; x < columnLength; x++) {
            for (int y = 0; y < rowLength; y++) {
                partitionOfSpace[x][y] = matrix[coordinate.getX() + x][coordinate.getY() + y];
            }
        }

        return partitionOfSpace;
    }


    /**
     * Comparing matrix with Alien's matrix. While the comparison process, matched any character('-' or 'o') and
     * matched alien character('o') is counting. Then initializing ComparisonResult with matchedCharCount, matchedAlienCharCount
     * totalCharAccuracy, alienCharAccuracy.
     * <p>
     * totalCharAccuracy = matchedCharCount / totalCharCount of Alien
     * alienCharAccuracy = matchedAlienCharCount / totalAlienCharCount of Alien
     *
     * @param matrix is comparing with Alien's matrix
     * @param alien  is comparing with matrix
     * @return ComparisonResult
     */
    public static final ComparisonResult compareMatrixByAlien(char[][] matrix, Alien alien) {
        int rowLength = matrix[0].length;
        int matchedCharCount = 0;
        int matchedAlienCharCount = 0;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < rowLength; y++) {
                if (matrix[x][y] == alien.getMatrix()[x][y]) {
                    if (GeneralConstants.ALIEN_CHAR == matrix[x][y]) {
                        matchedAlienCharCount++;
                    }
                    matchedCharCount++;
                }
            }
        }

        return new ComparisonResult(matchedCharCount, matchedAlienCharCount, alien.getTotalCharCount(), alien.getAlienCharCount());
    }

    /**
     * Converting String list to a char[][] matrix.
     *
     * @param list String list
     * @return char[][] matrix
     */
    public static final char[][] convertListToMatrix(List<String> list) {
        int rowLength = list.get(0).length();
        int columnLength = list.size();
        char[][] matrix = new char[columnLength][rowLength];

        for (int x = 0; x < columnLength; x++) {
            for (int y = 0; y < rowLength; y++) {
                matrix[x][y] = list.get(x).charAt(y);
            }
        }

        return matrix;
    }
}
