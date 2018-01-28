package com.af.radar.utils;

import com.af.radar.model.Coordinate;
import com.af.radar.model.Image;
import com.af.radar.model.Result;
import com.af.radar.service.SpaceService;

import java.util.List;

/**
 * Created by AKilickaya
 */
public final class PrintUtils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String COLUMN_FORMAT = "%2s%15s%15s%13s%20s%20s";
    private static final String TABLE_LINE = "=============================================================================================";
    private static boolean[][] alienFlagMatrix = new boolean[SpaceService.getExtendedSpace().getColumnLength()][SpaceService.getExtendedSpace().getRowLength()];

    private PrintUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Prints detected aliens to console
     *
     * @param resultList
     */
    public final static void print(List<Result> resultList) {
        printResultTable(resultList);
        System.out.println("\n");
        printInvaders(resultList);
        System.out.println();
        System.out.println("####################################### Detected Aliens on Extended Space Image #######################################");
        System.out.println();
        setAlienFlagMatrix(resultList);
        printSpaceMatrix();
    }

    private final static void printInvaders(List<Result> resultList) {
        System.out.println("Detected Aliens List");
        System.out.println(TABLE_LINE);
        for (int j = 0; j < resultList.size(); j++) {
            String id = String.valueOf(j + 1);
            System.out.println("Alien Id: " + id);
            System.out.println("#################");
            printMatrix(resultList.get(j).getDetectedMatrix());
            System.out.println();
        }
        System.out.println(TABLE_LINE);
    }

    private final static void printResultTable(List<Result> resultList) {
        System.out.println("\n");
        System.out.println("Detected Aliens Table");
        System.out.println(TABLE_LINE);
        System.out.format(COLUMN_FORMAT, "Id", "X Coordinate", "Y Coordinate", "Alien Type", "TotalCharAccuracy", "AlienCharAccuracy");
        System.out.println();
        for (int i = 0; i < resultList.size(); i++) {
            Result result = resultList.get(i);
            String id = String.valueOf(i + 1);
            System.out.format("%2s%15s%15s%13s%20s%20s", id, result.getCoordinate().getX(), result.getCoordinate().getY(),
                    result.getAlienType().getName(), result.getComparisonResult().getTotalCharAccuracy(),
                    result.getComparisonResult().getAlienCharAccuracy());
            System.out.println();
        }
        System.out.println(TABLE_LINE);
    }

    private final static void printMatrix(char[][] matrix) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                System.out.print(matrix[x][y]);
            }
            System.out.println();
        }
    }

    private final static void printSpaceMatrix() {
        Image space = SpaceService.getExtendedSpace();
        for (int x = 0; x < space.getColumnLength(); x++) {
            for (int y = 0; y < space.getRowLength(); y++) {
                if (alienFlagMatrix[x][y]) {
                    System.out.print(ANSI_RED + space.getMatrix()[x][y] + ANSI_RESET);
                } else {
                    System.out.print(space.getMatrix()[x][y]);
                }
            }
            System.out.println();
        }
    }

    private final static void setAlienFlagMatrix(List<Result> resultList) {
        for (Result result : resultList) {
            addDetectedAlienToFlagMatrix(result);
        }
    }

    private final static void addDetectedAlienToFlagMatrix(Result result) {
        Coordinate coordinate = result.getCoordinate();
        int columnLength = result.getDetectedMatrix().length;
        int rowLength = result.getDetectedMatrix()[0].length;
        for (int x = coordinate.getX(); x < columnLength + coordinate.getX(); x++) {
            for (int y = coordinate.getY(); y < rowLength + coordinate.getY(); y++) {
                alienFlagMatrix[x][y] = true;
            }
        }
    }
}
