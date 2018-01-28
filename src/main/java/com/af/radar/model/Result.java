package com.af.radar.model;

import com.af.radar.enums.AlienType;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by AKilickaya
 */
public final class Result {
    private final AlienType alienType;
    private final Coordinate coordinate;
    private final ComparisonResult comparisonResult;
    private final char[][] detectedMatrix;

    public Result(AlienType alienType, Coordinate coordinate, ComparisonResult comparisonResult, char[][] matrix) {
        this.alienType = alienType;
        this.coordinate = coordinate;
        this.comparisonResult = comparisonResult;
        this.detectedMatrix = matrix;

    }

    public final Coordinate getCoordinate() {
        return coordinate;
    }

    public final AlienType getAlienType() {
        return alienType;
    }

    public final char[][] getDetectedMatrix() {
        return detectedMatrix;
    }

    public final ComparisonResult getComparisonResult() {
        return comparisonResult;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (alienType != result.alienType) return false;
        if (!coordinate.equals(result.coordinate)) return false;
        if (!comparisonResult.equals(result.comparisonResult)) return false;
        return Arrays.deepEquals(detectedMatrix, result.detectedMatrix);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(alienType, coordinate, comparisonResult, detectedMatrix);
    }

    @Override
    public final String toString() {
        return "Result{" +
                "alienType=" + alienType +
                ", coordinate=" + coordinate +
                ", comparisonResult=" + comparisonResult +
                ", detectedMatrix=" + Arrays.toString(detectedMatrix) +
                '}';
    }
}
