package com.af.radar;

import com.af.radar.constants.ErrorConstants;
import com.af.radar.enums.AlienType;
import com.af.radar.exceptions.RadarException;
import com.af.radar.model.Alien;
import com.af.radar.model.Image;
import com.af.radar.model.Result;
import com.af.radar.service.AlienService;
import com.af.radar.service.RadarService;
import com.af.radar.service.SpaceService;
import com.af.radar.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The application is a radar that detects known aliens in given space image file.
 * <p>
 * Parameter:
 * The application takes 2 parameters as input.
 * 1) Space Image File Path:
 * 2) accuracy:
 * It is a double value which is greater than 0 or less than or equal to 1.
 * It is used for desired threshold accuracy value.
 * <p>
 * How it works:
 * The application will read the radar image file from the given file path and will read known Aliens from files.
 * radar image is extended in all directions(left, right, above, bottom) to detect edge cases.
 * The app will be taking partitions with the same width and height of each Alien from radar image and comparing both matrices.
 * It will count total matched characters and total matched alien characters('o') then calculate totalCharAccuracy and alienCharAccuracy.
 * Alien is detected when totalCharAccuracy and alienCharAccuracy greater than or equal to given accuracy value.
 * The application will print detected Alien info including coordinate from the extended space image, accuracy, alien type and partition of space image.
 * It will also print the detected Aliens and show them red colored on the extended space image.
 *
 * @author AKilickaya
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            validateArgs(args);
            double accuracy = getAndValidateAccuracyParameter(args[1]);
            String spaceImageFilePath = args[0];
            PrintUtils.print(getDetectionList(accuracy, spaceImageFilePath));
        } catch (RadarException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }
    }

    private static final double getAndValidateAccuracyParameter(String accuracyInput) throws RadarException {
        double accuracy;
        try {
            accuracy = Double.valueOf(accuracyInput);
            if (accuracy <= 0 || accuracy > 1) {
                throw new RadarException(ErrorConstants.ERR102,
                        "accuracy parameter should be greater than 0 or less than or equal to 1");
            }
        } catch (NumberFormatException e) {
            throw new RadarException(ErrorConstants.ERR103,
                    "accuracy parameter should be double");
        }


        return accuracy;
    }

    private static final List<Result> getDetectionList(double accuracy, String spaceImageFilePath) throws RadarException {
        Alien orc = AlienService.getAlienByType(AlienType.ORC);
        Alien goblin = AlienService.getAlienByType(AlienType.GOBLIN);
        Image extendedSpace = getExtendedSpaceMatrix(spaceImageFilePath, Arrays.asList(orc, goblin));

        List<Result> detectedOrcList = RadarService.detect(extendedSpace, orc, accuracy);
        List<Result> detectedGoblinList = RadarService.detect(extendedSpace, goblin, accuracy);
        List<Result> allDetectedAlienList = new ArrayList<>(detectedOrcList.size() + detectedGoblinList.size());
        allDetectedAlienList.addAll(detectedOrcList);
        allDetectedAlienList.addAll(detectedGoblinList);

        return allDetectedAlienList;
    }

    private static final Image getExtendedSpaceMatrix(String spaceImageFilePath, List<Alien> alienList) throws RadarException {
        int extendWidthLength = AlienService.findLongestRowLengthOfAliens(alienList) - 1;
        int extendHeightLength = AlienService.findLongestColumnLengthOfAliens(alienList) - 1;

        return SpaceService.getExtendedSpaceMatrixFromFile(spaceImageFilePath, extendWidthLength, extendHeightLength);
    }

    private static final void validateArgs(String[] args) throws RadarException {
        if (args.length != 2) {
            throw new RadarException(ErrorConstants.ERR101,
                    "2 parameters(1. space image file path, 2.accuracy value) should be passed to run application");
        }
    }
}
