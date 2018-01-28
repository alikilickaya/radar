package com.af.radar.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by AKilickaya
 */
public class Image {
    private final int rowLength;
    private final int columnLength;
    private final char[][] matrix;

    public Image(char[][] matrix) {
        this.rowLength = matrix[0].length;
        this.columnLength = matrix.length;
        this.matrix = matrix;
    }

    public final int getRowLength() {
        return rowLength;
    }

    public final int getColumnLength() {
        return columnLength;
    }

    public final char[][] getMatrix() {
        return matrix;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (rowLength != image.rowLength) return false;
        if (columnLength != image.columnLength) return false;
        return Arrays.deepEquals(matrix, image.matrix);
    }

    @Override
    public final int hashCode() {
        int result = Objects.hash(rowLength, columnLength);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "rowLength=" + rowLength +
                ", columnLength=" + columnLength +
                ", matrix=" + Arrays.toString(matrix) +
                '}';
    }
}
