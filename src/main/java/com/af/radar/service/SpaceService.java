package com.af.radar.service;

import com.af.radar.constants.ErrorConstants;
import com.af.radar.constants.GeneralConstants;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Image;
import com.af.radar.utils.MatrixUtils;
import com.af.radar.utils.ReaderUtils;
import com.af.radar.utils.StringUtils;

import java.util.List;

/**
 * Created by AKilickaya
 */
public final class SpaceService {

    private static Image extendedSpace = null;

    private SpaceService() {
        throw new IllegalAccessError("Utility class");
    }

    public static final Image getExtendedSpace() {
        return extendedSpace;
    }

    /**
     * Extending given space image matrix with empt space character('-') with desired extensionWidth and extensionHeight lenghts.
     *
     * @param filePath        of the space image file
     * @param extensionWidth  desired extension length will be added left and rigth to given space radar image
     * @param extensionHeight desired extension length will be added above and bottom to given space radar image
     * @return char[][] extended space matrix
     * @throws RadarException with ERR107 errorCode
     */
    public static final Image getExtendedSpaceMatrixFromFile(String filePath, int extensionWidth, int extensionHeight) throws RadarException {
        if (extendedSpace == null) {
            List<String> spaceRowList;
            try {
                spaceRowList = ReaderUtils.convertFileToList(filePath);
            } catch (RadarException e) {
                throw new RadarException(ErrorConstants.ERR107, "Could not found Space image file with given path");
            }
            extendListElements(spaceRowList, extensionWidth, GeneralConstants.SPACE_CHAR);
            extendListSize(spaceRowList, extensionHeight, GeneralConstants.SPACE_CHAR);
            extendedSpace = new Image(MatrixUtils.convertListToMatrix(spaceRowList));
        }

        return extendedSpace;
    }

    /**
     * Desired lenght of desired characters are added to beginning and end of each element of list
     *
     * @param list            String list
     * @param extensionLength desired extension length is added to beginning and end of each element of list
     * @param character       desired character to be filled by
     */
    private static final void extendListElements(List<String> list, int extensionLength, char character) {
        String extendStringForRows = StringUtils.getStringWithLengthAndFilledWithCharacter(extensionLength, character);
        for (int i = 0; i < list.size(); i++) {
            StringBuilder builder = new StringBuilder();
            String row = list.get(i);
            row = builder.append(extendStringForRows).append(row).append(extendStringForRows).toString();
            list.set(i, row);
        }
    }


    /**
     * Desired number of elements are added to beginning and end of the list. The elements that are added to list are
     * constants Strings filled by desired character.
     *
     * @param list            String list
     * @param extensionLength desired extension length is added to beginning and end of each element of list
     * @param character       desired character to be filled by
     */
    private static final void extendListSize(List<String> list, int extensionLength, char character) {
        String extendStringForColums = StringUtils.getStringWithLengthAndFilledWithCharacter(list.get(0).length(), character);
        for (int i = 0; i < extensionLength; i++) {
            list.add(i, extendStringForColums);
            list.add(extendStringForColums);
        }
    }
}
