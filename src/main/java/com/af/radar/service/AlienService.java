package com.af.radar.service;

import com.af.radar.constants.ErrorConstants;
import com.af.radar.constants.GeneralConstants;
import com.af.radar.enums.AlienType;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Alien;
import com.af.radar.utils.ReaderUtils;

import java.util.List;

/**
 * Created by AKilickaya
 */
public final class AlienService {
    private AlienService() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Reads Alien file by type and initialize an Alien object then returns it.
     *
     * @param alienType alien type.
     * @return Alien object
     * @throws RadarException with ERR108 errorCode {@link ErrorConstants#ERR108}
     * @see AlienType
     */
    public static final Alien getAlienByType(AlienType alienType) throws RadarException {
        String filePath = getFilePath(alienType);
        List<String> alienRowList = ReaderUtils.convertResourceFileToList(filePath);

        return convertListToAlien(alienRowList, alienType);
    }

    /**
     * @param alienList
     * @return the row length of Alien which has the longest row length in alienList
     * @see Alien
     */
    public static final int findLongestRowLengthOfAliens(List<Alien> alienList) {
        int longestRowLength = 0;
        if (alienList != null) {
            for (Alien alien : alienList) {
                if (alien.getRowLength() > longestRowLength) {
                    longestRowLength = alien.getRowLength();
                }
            }
        }

        return longestRowLength;
    }

    /**
     * @param alienList
     * @return the column length of Alien which has the longest column length in alienList
     * @see Alien
     */
    public static final int findLongestColumnLengthOfAliens(List<Alien> alienList) {
        int longestColumnLength = 0;
        if (alienList != null) {
            for (Alien alien : alienList) {
                if (alien.getColumnLength() > longestColumnLength) {
                    longestColumnLength = alien.getColumnLength();
                }
            }
        }

        return longestColumnLength;
    }

    /**
     * converting alienRowList to an Alien object. Creating Alien object with rowLength, columnLength,
     * totalCharCount, char[][] matrix and totalAlienCharCount ('o') then returns the Alien object.
     *
     * @param alienRowList String list which is converted to an Alien object
     * @param alienType    is used while creating Alien object
     * @return Alien object
     * @throws RadarException with ERR104 errorCode. {@link ErrorConstants#ERR104}
     * @see AlienType
     */
    private static final Alien convertListToAlien(List<String> alienRowList, AlienType alienType) throws RadarException {
        int rowLength = alienRowList.get(0).length();
        int columnLength = alienRowList.size();
        int alienCharCount = 0;
        char[][] matrix = new char[columnLength][rowLength];

        for (int x = 0; x < columnLength; x++) {
            for (int y = 0; y < rowLength; y++) {
                matrix[x][y] = alienRowList.get(x).charAt(y);
                if (GeneralConstants.ALIEN_CHAR == matrix[x][y]) {
                    alienCharCount++;
                }
            }
        }

        if (alienCharCount == 0) {
            throw new RadarException(ErrorConstants.ERR104,
                    "There is no valid align char and should be at least 1 'o' character");
        }
        return new Alien(matrix, alienType, alienCharCount);
    }

    /**
     * @param alienType
     * @return the filePath of given Alien's file
     * @see AlienType
     */
    private static final String getFilePath(AlienType alienType) {
        return "/" + alienType.getName() + GeneralConstants.TXT;

    }
}
