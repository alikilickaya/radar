package com.af.radar.utils;

import com.af.radar.enums.AlienType;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Alien;
import com.af.radar.model.ComparisonResult;
import com.af.radar.model.Coordinate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by AKilickaya
 */
public class MatrixUtilsTest {
    private char[][] matrixA = {{'o', 'o', '-'}, {'-', 'o', 'o'}, {'-', 'o', '-'}};

    @Test
    public void testGetPartitionOfMatrix() throws RadarException {
        char[][] partitionOfMatrix = MatrixUtils.getPartitionOfMatrix(matrixA, 2, 2, new Coordinate(1, 1));

        assertEquals(2, partitionOfMatrix.length);
        assertEquals(2, partitionOfMatrix[0].length);
        assertEquals('o', partitionOfMatrix[0][0]);
    }

    @Test
    public void testCompareMatrixByAlien() throws RadarException {
        Alien alien = new Alien(matrixA, AlienType.GOBLIN, 5);
        char[][] matrix = {{'-', 'o', 'o'}, {'-', 'o', 'o'}, {'-', 'o', '-'}};
        ComparisonResult comparisonResult = MatrixUtils.compareMatrixByAlien(matrix, alien);

        assertNotNull(comparisonResult);
        double expectedTotalCharAccuracy = (double) 7 / 9;
        double expectedAlienCharAccuracy = (double) 4 / 5;
        assertEquals(expectedTotalCharAccuracy, comparisonResult.getTotalCharAccuracy(), 0);
        assertEquals(expectedAlienCharAccuracy, comparisonResult.getAlienCharAccuracy(), 0);
    }

    @Test
    public void testConvertListToMatrix() throws RadarException {
        List<String> list = Arrays.asList("-o-", "o-o", "oo-");
        char[][] matrix = MatrixUtils.convertListToMatrix(list);

        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);
        assertEquals('-', matrix[0][0]);
    }

}